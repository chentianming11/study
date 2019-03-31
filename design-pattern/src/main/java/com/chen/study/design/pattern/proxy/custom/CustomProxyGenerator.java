package com.chen.study.design.pattern.proxy.custom;

/**
 * @author 陈添明
 * @date 2019/3/31
 */
public class CustomProxyGenerator {

    /**
     *  获取不同系统的换行符
     */
    private static String lineSeparator = System.lineSeparator();

    public static String generateProxyClass(String className, Class<?>[] interfaces) {

        StringBuffer buffer = new StringBuffer();
        // import 目标接口 假设interfaces只有一个接口
        buffer.append("import " + interfaces[0].getCanonicalName() + ";" + lineSeparator);
        buffer.append("import com.chen.study.design.pattern.proxy.custom.CustomInvocationHandler;\n" +
                "import java.lang.reflect.Method;\n" +
                "import com.chen.study.design.pattern.proxy.custom.CustomProxy;" + lineSeparator);
        buffer.append("import java.lang.reflect.UndeclaredThrowableException;" + lineSeparator);

        // 定义类
        buffer.append("public final class " + className +" extends CustomProxy implements "
                + interfaces[0].getSimpleName() +" {" + lineSeparator);

        // 构造方法
        buffer.append("public " + className + "(CustomInvocationHandler h)  {\n" +
                "        super(h);\n" +
                "    }" + lineSeparator);

        // 字段
        buffer.append("private static Method m3;" + lineSeparator);

        // 静态域初始化字段
        buffer.append("static {" + lineSeparator);
        buffer.append(" try {" + lineSeparator);
        buffer.append("m3 = Class.forName(\"com.chen.study.design.pattern.proxy.BusinessInterface\").getMethod(\"execute\");");
        buffer.append("        } catch (NoSuchMethodException var2) {\n" +
                "            throw new NoSuchMethodError(var2.getMessage());\n" +
                "        } catch (ClassNotFoundException var3) {\n" +
                "            throw new NoClassDefFoundError(var3.getMessage());\n" +
                "        }" + lineSeparator);
        buffer.append("}" + lineSeparator);

        // 接口中的方法
        buffer.append("public final void execute()  {\n" +
                "        try {\n" +
                "            super.h.invoke(this, m3, (Object[])null);\n" +
                "        } catch (RuntimeException | Error var2) {\n" +
                "            throw var2;\n" +
                "        } catch (Throwable var3) {\n" +
                "            throw new UndeclaredThrowableException(var3);\n" +
                "        }\n" +
                "    }" + lineSeparator);

        buffer.append("}" + lineSeparator);
        return buffer.toString();
    }
}
