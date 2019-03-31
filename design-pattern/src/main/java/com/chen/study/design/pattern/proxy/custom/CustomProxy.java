package com.chen.study.design.pattern.proxy.custom;

import org.apache.commons.io.FileUtils;

import javax.tools.JavaCompiler;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;
import java.io.File;
import java.lang.reflect.Constructor;

/**
 * 自定义代理
 *
 * @author 陈添明
 * @date 2019/3/31
 */
public class CustomProxy {

   protected CustomInvocationHandler h;

    public CustomProxy(CustomInvocationHandler h) {
        this.h = h;
    }

    public static Object newProxyInstance(CustomClassLoader classLoader,
                                          Class<?>[] interfaces,
                                          CustomInvocationHandler h)
            throws Exception {
        // 1. 动态生成源代码.java 文件
        String src = CustomProxyGenerator.generateProxyClass("$Proxy0", interfaces);
        // 2. Java文件输出到磁盘
        String filePath = CustomProxy.class.getResource("").getPath();
        File f = new File(filePath + "$Proxy0.java");
        FileUtils.writeStringToFile(f, src, "utf-8");

        // 3. 将生成的Java编译成class
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        StandardJavaFileManager manage = compiler.getStandardFileManager(null,null,null);
        Iterable iterable = manage.getJavaFileObjects(f);
        JavaCompiler.CompilationTask task =
                compiler.getTask(null,manage,null,null,null,iterable);
        task.call();
        manage.close();

        // 4. 编译到的class加载到JVM中
        Class proxyClass = classLoader.findClass("$Proxy0");
        f.delete();
        // 5. 返回代理对象
        Constructor c = proxyClass.getConstructor(CustomInvocationHandler.class);
        Object o = c.newInstance(h);
        return o;
    }
}
