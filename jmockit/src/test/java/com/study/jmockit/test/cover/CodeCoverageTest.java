package com.study.jmockit.test.cover;

import com.study.jmockit.cover.ISayHello;
import com.study.jmockit.cover.SayHello;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.List;

//代码覆盖率测试，观察覆盖率的计算方式,去target/coverage-report目录下，查看SayHello这个类的覆盖率
public class CodeCoverageTest {
    ISayHello sayHello = new SayHello();
 
    @Rule
    public ExpectedException thrown = ExpectedException.none();
 
    //测试 sayHello(String who, int gender);
    @Test
    public void testSayHello1() {
        Assert.assertTrue(sayHello.sayHello("david", ISayHello.MALE).equals("hello Mr david"));
        Assert.assertTrue(sayHello.sayHello("lucy", ISayHello.FEMALE).equals("hello Mrs lucy"));
        thrown.expect(IllegalArgumentException.class);
        sayHello.sayHello("david", 3);
    }
    //测试 sayHello(String[] who, int[] gender)
    @Test
    public void testSayHello2() {
        String[] who = new String[] { "david", "lucy" };
        int[] gender = new int[] { ISayHello.MALE, ISayHello.FEMALE };
        List<String> result = sayHello.sayHello(who, gender);
        Assert.assertTrue(result.get(0).equals("hello Mr david"));
        Assert.assertTrue(result.get(1).equals("hello Mrs lucy"));
    }
 
}
