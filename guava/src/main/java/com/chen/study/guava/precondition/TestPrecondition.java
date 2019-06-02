package com.chen.study.guava.precondition;

import com.google.common.base.Preconditions;
import org.junit.Test;

/**
 * 前置条件：前置条件Preconditions提供静态方法来检查方法或构造函数，被调用是否给定适当的参数。
 * 它检查的先决条件。其方法失败抛出IllegalArgumentException。
 * Created by chen on 2018/2/23.
 */
public class TestPrecondition {

    @Test
    public void test1() {

        // checkNotNull: 检查 value 是否为 null，该方法直接返回 value，因此可以内嵌使用 checkNotNull。
        String o = Preconditions.checkNotNull("aaa", "参数不能为空");

        //checkArgument: 检查 boolean 是否为 true，用来检查传递给方法的参数。
        Preconditions.checkArgument(false, "表达式结果必须为true");


    }
}
