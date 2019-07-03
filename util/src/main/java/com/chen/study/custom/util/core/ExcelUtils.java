package com.chen.study.custom.util.core;

import lombok.experimental.UtilityClass;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.*;
import java.util.function.Function;

/**
 * @author 陈添明
 * @date 2018/10/11
 */
@UtilityClass
public class ExcelUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(ExcelUtils.class);

    public static final String DATE_FORMAT_PATTERN = "yyyy-MM-dd HH:mm:ss";

    public static <T> List<T> toList(File file,  LinkedHashMap<String, String> mapping, Class<T> clz) {
        // 根据excel文件创建workbook，能自动根据excel版本创建相应的workbook
        Workbook workbook;
        try {
            workbook = WorkbookFactory.create(file);
        } catch (Exception e) {
            throw new RuntimeException("创建workbook异常", e);
        }
        return toList(workbook, mapping, clz);

    }


    /**
     * 读取一个excel到List<T> 中
     * mapping的key为表头单元格名称，value为对应字段名称
     */
    public static <T> List<T> toList(InputStream inputStream,  LinkedHashMap<String, String> mapping, Class<T> clz) {
        // 根据excel文件创建workbook，能自动根据excel版本创建相应的workbook
        Workbook workbook = null;
        try {
            workbook = WorkbookFactory.create(inputStream);
        } catch (Exception e) {
            throw new RuntimeException("创建workbook异常", e);
        }
        return toList(workbook, mapping, clz);

    }

    /**
     * 读取一个excel到List<Map<String,Object>> 中
     * mapping的key为表头单元格名称，value为对应字段名称
     */
    public static List<Map<String, Object>> toList(File file,  LinkedHashMap<String, String> mapping) {

        // 根据excel文件创建workbook，能自动根据excel版本创建相应的workbook
        Workbook workbook = null;
        try {
            workbook = WorkbookFactory.create(file);
        } catch (Exception e) {
            throw new RuntimeException("创建workbook异常", e);
        }
        return toList(workbook, mapping);
    }


    /**
     * 读取一个excel到List<Map<String,Object>> 中
     * mapping的key为表头单元格名称，value为对应字段名称
     */
    public static List<Map<String, Object>> toList(InputStream inputStream,  LinkedHashMap<String, String> mapping) {

        // 根据excel文件创建workbook，能自动根据excel版本创建相应的workbook
        Workbook workbook = null;
        try {
            workbook = WorkbookFactory.create(inputStream);
        } catch (Exception e) {
            throw new RuntimeException("创建workbook异常", e);
        }
        return toList(workbook, mapping);

    }

    private static <T> List<T> toList(Workbook workbook,   LinkedHashMap<String, String> mapping, Class<T> clz) {
        // 获取第一个sheet
        Sheet sheet = workbook.getSheetAt(0);
        Map<Integer, String> indexFieldMapping = getIndexFieldMapping(mapping, sheet);
        int lastRowNum = sheet.getLastRowNum();
        LOGGER.info("总共加载" + lastRowNum + "行数据！");
        List<T> result = new ArrayList<>();
        try {
            // 根据有效列，读取有效列中的内容
            for (Row row : sheet) {
                // 跳过第一行标题
                if (row.getRowNum() == 0) {
                    continue;
                }
                T t = clz.newInstance();
                // 读取内容
                indexFieldMapping.keySet().forEach((columnIndex) -> {
                    String fieldStr = indexFieldMapping.get(columnIndex);
                    Cell cell = row.getCell(columnIndex);
                    Object cellValue = getCellValue(cell);
                    Field field = FieldUtils.getField(clz, fieldStr);
                    if (field != null) {
                        Class<?> type = field.getType();
                        if (!Objects.equals(type, cellValue.getClass())) {
                            // 类型不一致，进行类型转换
                            cellValue = transferType(cellValue, type);
                        }
                        try {
                            PropertyUtils.setProperty(t, fieldStr, cellValue);
                        } catch (Exception e) {
                            throw new RuntimeException("设置属性异常，属性名：" + fieldStr + "; 属性值：" + cellValue, e);
                        }
                    }
                });
                result.add(t);
            }
        } catch (Exception e) {
            throw new RuntimeException("读取excel失败", e);
        }
        return result;
    }

    /**
     * 类型转换
     *
     * @param cellValue
     * @param type
     * @return
     * @throws ParseException
     */
    private static Object transferType(Object cellValue, Class<?> type) {
        if (cellValue == null) {
            return null;
        }

        // Date转其它类型
        if (cellValue instanceof Date) {
            // Date -> string
            if (Objects.equals(type, String.class)) {
                cellValue = DateFormatUtils.format(((Date) cellValue), DATE_FORMAT_PATTERN);
            } else {
                throw new UnsupportedOperationException("不支持的类型转换，尝试将" + cellValue.getClass()
                        + "转换成" + type.getClass());
            }
        }

        // String 转其他类型
        if (cellValue instanceof String) {
            // string -> null
            if (Is.empty(cellValue)) {
                return null;
            }
            String cellValueStr = (String) cellValue;
            if (Objects.equals(type, Date.class)) {
                // string -> date
                try {
                    return DateUtils.parseDate(cellValueStr, DATE_FORMAT_PATTERN);
                } catch (ParseException e) {
                    throw new RuntimeException("日期转异常", e);
                }
            }
            if (Objects.equals(type, Integer.class)) {
                // string -> integer
                return Integer.valueOf((cellValueStr));
            }
            if (Objects.equals(type, Long.class)) {
                // string -> long
                return Long.valueOf((cellValueStr));
            }
            if (Objects.equals(type, Float.class)) {
                // string -> float
                return Float.valueOf((cellValueStr));
            }
            if (Objects.equals(type, Short.class)) {
                // string -> short
                return Short.valueOf((cellValueStr));
            }
            if (Objects.equals(type, Byte.class)) {
                // string -> byte
                return Byte.valueOf((cellValueStr));
            }
            if (Objects.equals(type, Double.class)) {
                // string -> double
                return Double.valueOf((cellValueStr));
            }
            if (Objects.equals(type, BigDecimal.class)) {
                // string -> bigDecimal
                return new BigDecimal(cellValueStr);
            }
            throw new UnsupportedOperationException("不支持的类型转换，尝试将" + cellValue.getClass()
                    + "转换成" + type.getClass());

        }

        // Number转其它类型
        if (cellValue instanceof Number) {
            if (Objects.equals(type, String.class)) {
                // number -> string
                return cellValue.toString();
            }
        }

        throw new UnsupportedOperationException("不支持的类型转换，尝试将" + cellValue.getClass()
                + "转换成" + type.getClass());

    }


    /**
     * mapping的key为表头单元格名称，value为对应字段名称
     * @param workbook 工作簿
     * @param mapping  表头和字段的映射关系
     * @return
     */
    private static List<Map<String, Object>> toList(Workbook workbook,  LinkedHashMap<String, String> mapping) {
        // 获取第一个sheet
        Sheet sheet = workbook.getSheetAt(0);
        Map<Integer, String> indexFiledMapping = getIndexFieldMapping(mapping, sheet);
        int lastRowNum = sheet.getLastRowNum();
        LOGGER.info("总共加载" + lastRowNum + "行数据！");
        List<Map<String, Object>> result = new ArrayList<>();
        // 根据有效列，读取有效列中的内容
        for (Row row : sheet) {
            // 跳过第一行标题
            if (row.getRowNum() == 0 || row == null) {
                continue;
            }
            Map<String, Object> rowData = new HashMap<>();
            // 读取内容
            indexFiledMapping.keySet().forEach(columnIndex -> {
                String filed = indexFiledMapping.get(columnIndex);
                Cell cell = row.getCell(columnIndex);
                Object cellValue = getCellValue(cell);
                rowData.putIfAbsent(filed, cellValue);
            });
            result.add(rowData);
        }
        return result;
    }

    /**
     * 获取索引-字段映射
     * mapping的key为表头单元格名称，value为对应字段名称
     * @param mapping 映射关系
     * @param sheet   表头sheet
     * @return
     */
    private static Map<Integer, String> getIndexFieldMapping( LinkedHashMap<String, String> mapping, Sheet sheet) {
        // 索引-字段映射
        Map<Integer, String> indexFiledMapping = new HashMap<>();
        // 读取表头 确定有效列
        Row headRow = sheet.getRow(0);
        mapping.forEach((head, field) -> {
            Integer index = findIndexOnRow(headRow, head);
            if (index != null) {
                indexFiledMapping.put(index, field);
                LOGGER.debug("索引index：{}; 对应字段名称：{}", index, field);
            }
        });
        return indexFiledMapping;
    }


    private static Integer findIndexOnRow(Row headRow, String key) {
        for (Cell cell : headRow) {
            if (Is.equals(key, cell.getStringCellValue())) {
                return cell.getColumnIndex();
            }
        }
        return null;
    }

    /**
     * 从cell中读取cellValue
     *
     * @param cell
     * @return
     */
    private static Object getCellValue(Cell cell) {
        // 根据cell内容格式 -- 分别读取其内容
        Object value;
        if (Objects.isNull(cell)) {
            return "";
        }
        switch (cell.getCellType()) {
            case Cell.CELL_TYPE_NUMERIC: {
                if (DateUtil.isCellDateFormatted(cell)) {
                    // 日期
                    value = cell.getDateCellValue();
                } else {
                    DecimalFormat decimalFormat = new DecimalFormat("###################.##");
                    value = decimalFormat.format(cell.getNumericCellValue());
                }
                break;
            }
            case Cell.CELL_TYPE_STRING: {
                value = cell.getStringCellValue();
                break;
            }
            case Cell.CELL_TYPE_BLANK: {
                // 空白
                value = "";
                break;
            }
            case Cell.CELL_TYPE_FORMULA: {
                // 公式
                value = cell.getRichStringCellValue().getString();
                break;
            }
            default: {
                value = cell.getStringCellValue();
            }
        }
        return value;
    }

    /**
     * 分页查询,生成SXSSFWorkbook
     * 用于分页查询导出
     * mapping的key为表头单元格名称，value为对应字段名称
     * @param pageSize
     * @param mapping  表头和字段名映射关系
     * @param function 使用pageNum查询出对应的分页数据
     * @return
     */
    public static SXSSFWorkbook createSXSSFWorkbookPagination(Integer pageSize,LinkedHashMap<String, String> mapping, Function<Integer, List<Map>> function) {
        SXSSFWorkbook workbook = new SXSSFWorkbook();
        Sheet detailSheet = workbook.createSheet("sheet1");
        ArrayList<String> heads = new ArrayList<>(mapping.keySet());
        ExcelUtils.sheetAppendRows(detailSheet, heads);
        Integer pageNum = 1;
        List<Map> result;
        do {
            result = function.apply(pageNum);
            pageNum++;
            result.forEach(item -> {
                List<String> sheetData = new ArrayList<>();
                for (int i = 0; i < heads.size(); i++) {
                    detailSheet.setColumnWidth(i, 10 * 512);
                    Object o = item.get(mapping.get(heads.get(i)));
                    if (Objects.isNull(o)) {
                        o = "";
                    }
                    if (o instanceof Date) {
                        o = DateFormatUtils.format((Date) o, DATE_FORMAT_PATTERN);
                    }
                    sheetData.add(String.valueOf(o));
                }
                ExcelUtils.sheetAppendRows(detailSheet, sheetData);
            });

        } while (!CollectionUtils.isEmpty(result) && result.size() == pageSize);
        return workbook;
    }


    /**
     * 生成SXSSFWorkbook
     * mapping的key为表头单元格名称，value为对应字段名称
     * @param mapping
     * @param result
     * @return
     */
    public static SXSSFWorkbook createSXSSFWorkbook( LinkedHashMap<String, String> mapping, List<Map> result) {
        SXSSFWorkbook workbook = new SXSSFWorkbook();
        Sheet detailSheet = workbook.createSheet("sheet1");
        Set<String> headSet = mapping.keySet();
        Collection<String> fieldColl = mapping.values();
        List<String> heads = new ArrayList<>(headSet);
        List<String> fields = new ArrayList<>(fieldColl);
        // 填充第一行
        ExcelUtils.sheetAppendRows(detailSheet, heads);
        result.forEach(item -> {
            List<String> sheetData = new ArrayList<>();
            for (int i = 0; i < fields.size(); i++) {
                detailSheet.setColumnWidth(i, 10 * 512);
                Object o = item.get(fields.get(i));
                if (Objects.isNull(o)) {
                    o = "";
                }
                if (o instanceof Date) {
                    o = DateFormatUtils.format((Date) o, DATE_FORMAT_PATTERN);
                }
                sheetData.add(String.valueOf(o));
            }
            ExcelUtils.sheetAppendRows(detailSheet, sheetData);
        });
        return workbook;
    }

    /**
     * 向excel的sheet中追加一行记录
     *
     * @param sheet
     * @param data
     */
    private static void sheetAppendRows(Sheet sheet, List<String> data) {
        Row row = sheet.createRow(sheet.getPhysicalNumberOfRows());
        for (int i = 0; i < data.size(); i++) {
            row.createCell(i).setCellValue(data.get(i) == null ? "" : data.get(i));
        }
    }

    /**
     * 导出excel
     */
    public static void export(HttpServletResponse response, SXSSFWorkbook workbook, String fileName) {
        try {
            DownloadUtils.downloadExcel(response, workbook, fileName + DateFormatUtils.format(new Date(), "yyyyMMdd") + ".xlsx");
        } catch (Exception e) {
            throw new RuntimeException("导出失败", e);
        }
    }
}
