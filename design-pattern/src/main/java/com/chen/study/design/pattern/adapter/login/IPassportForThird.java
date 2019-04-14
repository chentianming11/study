package com.chen.study.design.pattern.adapter.login;

/**
 * 第三方登录接口 -- 目标接口
 *
 * @author 陈添明
 * @date 2019/4/14
 */
public interface IPassportForThird {

    /**
     * QQ登录
     * @param id
     * @return
     */
    ResultMsg loginForQQ(String id);

    /**
     * 微信登录
     */
    ResultMsg loginForWechat(String id);
}
