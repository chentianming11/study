package com.chen.study.design.pattern.singleton.lazy;

/**
 * 推荐使用静态内部类实现单例模式
 * 第一次使用的时候才会进行实例化
 * @author 陈添明
 * @date 2018/9/9
 */
public class InnerClassSingletonPreventReflect {

    /**
     * 直接私有化构造器会存在反射攻击的情况
     * 因此还需要在构造器中判断, 防止反射攻击
     */
    private InnerClassSingletonPreventReflect(){
        if (InstanceHolder.INSTANCE != null) {
            throw new RuntimeException("不允许构建多个实例！");
        }
    }

    public static InnerClassSingletonPreventReflect getInstance(){
        // 在返回结果以前， 一定会先加载内部类
        return InstanceHolder.INSTANCE;
    }

    /**
     * 静态内部类在没有调用的时候，不会加载
     */
    private static class InstanceHolder {
        private static final InnerClassSingletonPreventReflect INSTANCE = new InnerClassSingletonPreventReflect();
    }

}
