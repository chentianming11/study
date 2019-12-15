package com.chen.study.dubbo.provider;

import org.apache.dubbo.common.URL;
import org.apache.dubbo.rpc.Exporter;
import org.apache.dubbo.rpc.Invoker;
import org.apache.dubbo.rpc.Protocol;
import org.apache.dubbo.rpc.RpcException;

/**
 * 自定义协议
 * 总结:总的来说，思路和 SPI 是差不多。都是基于约定的路径下制定配置文件。目的，通过配置的方式轻松实现功能的扩展。
 *
 * @author 陈添明
 * @date 2019/12/15
 */
public class MyProcol implements Protocol {
    @Override
    public int getDefaultPort() {
        return 999;
    }

    //暴露服务（Dubbo-> ；）
    @Override
    public <T> Exporter<T> export(Invoker<T> invoker) throws RpcException {
        return null;
    }

    @Override
    public <T> Invoker<T> refer(Class<T> type, URL url) throws RpcException {
        return null;
    }

    @Override
    public void destroy() {

    }
}
