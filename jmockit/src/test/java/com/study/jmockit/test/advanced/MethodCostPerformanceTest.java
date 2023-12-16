package com.study.jmockit.test.advanced;

import com.study.jmockit.cover.ISayHello;
import com.study.jmockit.cover.SayHello;
import mockit.Invocation;
import mockit.Mock;
import mockit.MockUp;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

//通过在mock时做AOP测试方法的时间性能
public class MethodCostPerformanceTest {
 
    // 测试SayHello类每个方法的时间性能
    @Test
    public void testSayHelloCostPerformance() {
        // 把方法的调用时间记录到costMap中。key是方法名称，value是平均调用时间
        Map<String, Long> costMap = new HashMap<String, Long>();
        new MockUp<SayHello>() {
            @Mock
            public Object $advice(Invocation invocation) {
                long a = System.currentTimeMillis();
                Object result = invocation.proceed();
                long cost = System.currentTimeMillis() - a;
                // 把某方法的平均调用时间记录下来
                String methodName = invocation.getInvokedMember().getName();
                Long preCost = costMap.get(methodName);
                if (preCost == null) {
                    costMap.put(methodName, cost);
                } else {
                    costMap.put(methodName, (preCost + cost) / 2);
                }
                return result;
            }
        };
        SayHello sayHello = new SayHello();
        sayHello.sayHello("david", ISayHello.MALE);
        sayHello.sayHello("lucy", ISayHello.FEMALE);
        for (Iterator<String> iterator = costMap.keySet().iterator(); iterator.hasNext();) {
            String methodName = (String) iterator.next();
            // 期望每个方法的调用时间不超过20ms
            Assert.assertTrue(costMap.get(methodName) < 20);
        }
    }
 
}
