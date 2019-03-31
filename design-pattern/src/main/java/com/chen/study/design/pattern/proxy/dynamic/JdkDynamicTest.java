package com.chen.study.design.pattern.proxy.dynamic;

import com.chen.study.design.pattern.proxy.Business;
import com.chen.study.design.pattern.proxy.BusinessInterface;
import sun.misc.ProxyGenerator;

import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Proxy;

/**
 * @author 陈添明
 * @date 2019/3/31
 */
public class JdkDynamicTest {
    public static void main(String[] args) throws IOException {
        Business targetObject = new Business();
        // 不需要编写代理类，动态创建代理对象
        BusinessInterface proxyInstance = (BusinessInterface) Proxy.newProxyInstance(targetObject.getClass().getClassLoader(),
                targetObject.getClass().getInterfaces(),
                (proxy, method, args1) -> {
                    System.out.println("前置处理！");
                    Object o = method.invoke(targetObject, args1);
                    System.out.println("后置处理！");
                    return o;
                });
        proxyInstance.execute();

        //通过反编译工具可以查看源代码
        byte [] bytes = ProxyGenerator.generateProxyClass("$Proxy0",new Class[]{BusinessInterface.class});
        FileOutputStream os = new FileOutputStream("$Proxy0.class");
        os.write(bytes);
        os.close();
    }
}
