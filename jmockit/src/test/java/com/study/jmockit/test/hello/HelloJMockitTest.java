package com.study.jmockit.test.hello;

import com.study.jmockit.hello.HelloJMockit;
import mockit.Expectations;
import org.junit.Assert;
import org.junit.Test;

import java.util.Locale;

//HelloJMockit类的测试类
public class HelloJMockitTest { 
 
    /**
     * 测试场景：当前是在中国
     */
    @Test
    public void testSayHelloAtChina() {
        // 假设当前位置是在中国
        new Expectations(Locale.class) {
            {
                Locale.getDefault();
                result = Locale.CHINA;
            }
        };
        // 断言说中文
        Assert.assertTrue("你好，JMockit!".equals((new HelloJMockit()).sayHello()));
    }
 
    /**
     * 测试场景：当前是在美国
     */
    @Test
    public void testSayHelloAtUS() {
        // 假设当前位置是在美国
        new Expectations(Locale.class) {
            {
                Locale.getDefault();
                result = Locale.US;
            }
        };
        // 断言说英文
        Assert.assertTrue("Hello，JMockit!".equals((new HelloJMockit()).sayHello()));
    }
}