package com.chen.study.guava.reflection;

import com.google.common.reflect.TypeToken;
import org.junit.Test;

import java.util.ArrayList;

/**
 * Created by chen on 2018/3/4.
 */
public class TestTypeToken {

    /**
     * 说实话，我是一点都没看懂
     */
    @Test
    public void test1() {

        // 获取原始类的TypeToken
        TypeToken<String> stringTypeToken = TypeToken.of(String.class);

        // 获取含有泛型的类型的TypeToken
        TypeToken<ArrayList<String>> listTypeToken = new TypeToken<ArrayList<String>>() {
        };
        System.out.println(listTypeToken.getType());
        TypeToken<?> typeToken = listTypeToken.resolveType(ArrayList.class.getTypeParameters()[0]);
        System.out.println(typeToken.getType());


    }
}
