package com.chen.study.guava.newcollection;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;
import org.junit.Test;

import java.util.Map;
import java.util.Set;

/**
 * Table支持2种类型的键 行 - 列
 * 通过行 和 列 共同来映射一个值
 * Created by chen on 2018/2/25.
 */
public class TestTable {

    @Test
    public void testTable() {
        HashBasedTable<String, String, Integer> table = HashBasedTable.create();

        // 新增数据  行-列-值
        table.put("张三", "语文", 80);
        table.put("张三", "数学", 90);
        table.put("李四", "语文", 60);
        table.put("李四", "数学", 70);
        // 是否包含 行-列 键
        table.contains("张三", "语文");
        // 是否包含 列键
        table.containsColumn("语文");
        // 是否包含行键
        table.containsRow("张三");
        // 是否包含值
        table.containsValue(90);
        // 获取某个行键-列键对应的值
        System.out.println(table.get("张三", "语文"));
        // 移除某个行键-列键对应的值
        Integer i = table.remove("张三", "李四");
        // 获取单元格集合
        Set<Table.Cell<String, String, Integer>> cells = table.cellSet();
        cells.forEach(e -> System.out.println(e));
        // 获取某列的信息
        Map<String, Integer> 语文 = table.column("语文");
        System.out.println("语文" + 语文);
        // 可对行进行相同的操作
        System.out.println(table);
    }

}
