package com.chen.study.design.pattern.singleton.register;

import lombok.Getter;
import lombok.Setter;

/**
 * 枚举单例
 * @author 陈添明
 * @date 2019/3/30
 */
public enum  EnumSingleton {
    INSTANCE;

    /**
     * 任意字段或者方法
     */
    @Setter
    @Getter
    private Object field;
    public void method() {
        System.out.println("任意方法！");
    }
}
