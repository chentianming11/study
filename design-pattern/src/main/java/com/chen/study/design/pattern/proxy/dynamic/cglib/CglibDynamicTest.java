package com.chen.study.design.pattern.proxy.dynamic.cglib;

import net.sf.cglib.core.DebuggingClassWriter;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @author 陈添明
 * @date 2019/4/1
 */
public class CglibDynamicTest {
    public static void main(String[] args) {

        //利用 cglib 的代理类可以将内存中的 class 文件写入本地磁盘
        System.setProperty(DebuggingClassWriter.DEBUG_LOCATION_PROPERTY,
                CglibDynamicTest.class.getResource("").getPath());

        // 使用cglib生成代理对象
        BusinessNoInterface businessNoInterface = (BusinessNoInterface) Enhancer.create(
                BusinessNoInterface.class, new MethodInterceptor() {
                    @Override
                    public Object intercept(Object obj, Method method, Object[] args,
                                            MethodProxy proxy) throws Throwable {
                        System.out.println("前置拦截");
                        Object result = proxy.invokeSuper(obj, args);
                        System.out.println("后置拦截");
                        return result;
                    }
                });
        // 使用代理对象执行方法
        businessNoInterface.execute();
    }
}
