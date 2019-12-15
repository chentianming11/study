package com.chen.study.dubbo.provider;

import org.apache.dubbo.common.extension.ExtensionLoader;
import org.apache.dubbo.rpc.Protocol;

/**
 * @author 陈添明
 * @date 2019/12/15
 */
public class SPIMain {

    //静态扩展点
    //自适应扩展点
    //激活扩展点
    public static void main(String[] args) {
        Protocol protocol = ExtensionLoader.getExtensionLoader(Protocol.class).getExtension("myprotocol");
        System.out.println(protocol.getDefaultPort());
    }
}
