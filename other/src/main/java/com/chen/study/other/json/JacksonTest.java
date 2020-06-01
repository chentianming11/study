package com.chen.study.other.json;

import com.chen.study.other.property.Person;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.*;

import java.io.IOException;

/**
 * @author 陈添明
 */
public class JacksonTest {


    public static void main(String[] args) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        Person person = new Person()
                .setName("刘策划")
                .setJobId(3L)
                .setNikeName("ddddd")
                .setId(100L);
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL)
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        System.out.println("========== 序列化 ==========");
        String s = mapper.writeValueAsString(person);
        System.out.println(s);

        System.out.println("========== 反序列化【驼峰】 ==========");
        String s1 = "{\"id\":100,\"name\":\"刘策划\",\"jobId\":3,\"nikeName\":\"ddddd\",\"phone\":\"1352345432\"}";
        System.out.println(mapper.readValue(s1, Person.class));


        System.out.println("========== 反序列化【下划线】 ==========");
        String s2 = "{\"id\":100,\"name\":\"刘策划\",\"job_id\":3,\"nike_name\":\"ddddd\",\"phone\":\"1352345432\"}";
        System.out.println(mapper.readValue(s2, Person.class));
    }


    /**
     * 配置详解
     *
     * @return
     */
    private void config() {

        ObjectMapper objectMapper = new ObjectMapper();

        //这个特性，决定了解析器是否将自动关闭那些不属于parser自己的输入源。
        // 如果禁止，则调用应用不得不分别去关闭那些被用来创建parser的基础输入流InputStream和reader；
        //默认是true
        objectMapper.configure(JsonParser.Feature.AUTO_CLOSE_SOURCE, true);
        //是否允许解析使用Java/C++ 样式的注释（包括'/'+'*' 和'//' 变量）
        objectMapper.configure(JsonParser.Feature.ALLOW_COMMENTS, true);

        //设置为true时，属性名称不带双引号
        objectMapper.configure(JsonGenerator.Feature.QUOTE_FIELD_NAMES, false);
        //反序列化是是否允许属性名称不带双引号
        objectMapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);

        //是否允许单引号来包住属性名称和字符串值
        objectMapper.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);

        //是否允许JSON字符串包含非引号控制字符（值小于32的ASCII字符，包含制表符和换行符）
        objectMapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_CONTROL_CHARS, true);

        //是否允许JSON整数以多个0开始
        objectMapper.configure(JsonParser.Feature.ALLOW_NUMERIC_LEADING_ZEROS, true);

        //null的属性不序列化
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);

        //按字母顺序排序属性,默认false
        objectMapper.configure(MapperFeature.SORT_PROPERTIES_ALPHABETICALLY, true);

        //是否以类名作为根元素，可以通过@JsonRootName来自定义根元素名称,默认false
        objectMapper.configure(SerializationFeature.WRAP_ROOT_VALUE, true);

        //是否缩放排列输出,默认false
        objectMapper.configure(SerializationFeature.INDENT_OUTPUT, false);

        // 由于vo中缺少json的某个字段 不报错
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        //序列化Date日期时以timestamps输出，默认true
        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, true);

        //序列化枚举是否以toString()来输出，默认false，即默认以name()来输出
        objectMapper.configure(SerializationFeature.WRITE_ENUMS_USING_TO_STRING, true);

        //序列化枚举是否以ordinal()来输出，默认false
        objectMapper.configure(SerializationFeature.WRITE_ENUMS_USING_INDEX, false);

        //序列化单元素数组时不以数组来输出，默认false
        objectMapper.configure(SerializationFeature.WRITE_ENUMS_USING_TO_STRING, true);

        //序列化Map时对key进行排序操作，默认false
        objectMapper.configure(SerializationFeature.ORDER_MAP_ENTRIES_BY_KEYS, true);

        //序列化char[]时以json数组输出，默认false
        objectMapper.configure(SerializationFeature.WRITE_CHAR_ARRAYS_AS_JSON_ARRAYS, true);

        //序列化BigDecimal时是输出原始数字还是科学计数，默认false，即以toPlainString()科学计数方式来输出
        objectMapper.configure(SerializationFeature.WRITE_BIGDECIMAL_AS_PLAIN, true);


        objectMapper.setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE);


    }

}
