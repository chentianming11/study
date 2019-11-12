package com.chen.study.other.yml;

import org.junit.Test;
import org.yaml.snakeyaml.Yaml;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.LinkedHashMap;

/**
 * @author 陈添明
 * @date 2019/11/11
 */
public class YmlTest {

    @Test
    public void testLoad() {
        String yamlStr = "key: hello yaml";
        Yaml yaml = new Yaml();
        Object ret = yaml.load(yamlStr);
        System.out.println(ret);
    }

    @Test
    public void test2() throws FileNotFoundException {

        Yaml yaml = new Yaml();
        FileInputStream fileInputStream = new FileInputStream("/Users/mac/work/git/study/other/src/main/resources/test.yml");
        LinkedHashMap map = yaml.loadAs(fileInputStream, LinkedHashMap.class);
        Object pool = map.get("pool");
        System.out.println(pool);


    }
}
