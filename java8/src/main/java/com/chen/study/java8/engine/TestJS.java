package com.chen.study.java8.engine;

import jdk.nashorn.api.scripting.ScriptObjectMirror;
import org.junit.Test;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Arrays;

/**
 * 测试JS引擎
 * Created by chen on 2018/2/28.
 */
public class TestJS {


    public static String fun1(String name) {
        System.out.format("Hi there from Java, %s", name);
        return "greetings from java";
    }

    public static void fun2(Object object) {
        System.out.println(object.getClass());
    }

    public static void fun3(ScriptObjectMirror mirror) {
        System.out.println(mirror.getClassName() + ": " +
                Arrays.toString(mirror.getOwnKeys(true)));
    }

    public static void fun4(ScriptObjectMirror person) {
        System.out.println("Full Name is: " + person.callMember("getFullName"));
    }

    /**
     * 在java中调用JS的方法
     *
     * @throws ScriptException
     * @throws FileNotFoundException
     * @throws NoSuchMethodException
     */
    @Test
    public void test1() throws ScriptException, FileNotFoundException, NoSuchMethodException {
        ScriptEngine scriptEngine = new ScriptEngineManager().getEngineByName("nashorn");
        String script = "print('hello world')";
        // 直接执行js字符串
        scriptEngine.eval(script);
        // 执行js脚本文件
        scriptEngine.eval(new FileReader("test.js"));
        // 将scriptEngine转换成invocable
        Invocable invocable = (Invocable) scriptEngine;

        Object o = invocable.invokeFunction("fun1", "陈添明");
        System.out.println(o);

        Object fun2 = invocable.invokeFunction("fun2", LocalDate.now());


    }

    /**
     * 在Js的方法中调用java的方法
     */
    @Test
    public void test2() throws IOException, ScriptException {
        ScriptEngine engine = new ScriptEngineManager().getEngineByName("nashorn");
        // 执行脚本
        Object o = engine.eval(new FileReader("test2.js"));

    }

    @Test
    public void test3() throws IOException, ScriptException {
        ScriptEngine engine = new ScriptEngineManager().getEngineByName("nashorn");
        // 执行脚本
        Object o = engine.eval(new FileReader("test3.js"));

    }

    @Test
    public void test4() throws IOException, ScriptException {
        ScriptEngine engine = new ScriptEngineManager().getEngineByName("nashorn");
        // 执行脚本
        Object o = engine.eval(new FileReader("other.js"));

    }


}
