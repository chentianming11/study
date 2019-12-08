package com.chen.study.other.spi;

/**
 * 假设有一个接口, 但是当前项目并没有实现，其它项目中有该接口的不同实现
 * 现在，我期望在本项目通过动态加载其它项目的实现，只要依赖了该项目，就是用对应的实现
 *
 * @author 陈添明
 * @date 2019/12/8
 */
public interface IHelloService {

    String sayHello(String s);
}
