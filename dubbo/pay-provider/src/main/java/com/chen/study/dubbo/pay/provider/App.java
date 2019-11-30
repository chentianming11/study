package com.chen.study.dubbo.pay.provider;

import org.apache.dubbo.container.Main;

import java.io.IOException;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) throws IOException {
        //Dubbo提供的启动类方法，它会启动dobbu中配置的多个container
        Main.main(new String[]{});
        //除了spring里面通过api的方式去加载springxml文件，还有其他方式？
    }
}
