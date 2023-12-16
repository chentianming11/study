package com.study.jmockit.test.common;

import com.study.jmockit.common.AnOrdinaryInterface;
import mockit.Mock;
import mockit.MockUp;
import org.junit.Assert;
import org.junit.Test;

//用MockUp来mock接口
public class InterfaceMockingByMockUpTest {
 
    @Test
    public void testInterfaceMockingByMockUp() {
        // 手工通过MockUp创建这个接口的实例
        AnOrdinaryInterface anOrdinaryInterface = new MockUp<AnOrdinaryInterface>(AnOrdinaryInterface.class) {
            // 对方法Mock
            @Mock
            public int method1() {
                return 10;
            }
 
            @Mock
            public int method2() {
                return 20;
            }
        }.getMockInstance();
 
        Assert.assertTrue(anOrdinaryInterface.method1() == 10);
        Assert.assertTrue(anOrdinaryInterface.method2() == 20);
    }
}