package com.chen.study.design.pattern.adapter.login;

/**
 * 第三方登录适配器
 * 实现目标接口，持有被适配对象的引用
 *
 * @author 陈添明
 * @date 2019/4/14
 */
public class PassportForThirdAdapter implements IPassportForThird {


    SiginService siginService;

    public PassportForThirdAdapter(SiginService siginService) {
        this.siginService = siginService;
    }

    /**
     * QQ登录
     *
     * @param id
     * @return
     */
    @Override
    public ResultMsg loginForQQ(String id) {
        //1、openId 是全局唯一，我们可以把它当做是一个用户名(加长)
        // 2、密码默认为 QQ_EMPTY
        // 3、注册(在原有系统里面创建一个用户)
        //4、调用原来的登录方法
        ResultMsg resultMsg = siginService.login("12345", "1111");
        return resultMsg;
    }

    /**
     * 微信登录
     *
     * @param id
     */
    @Override
    public ResultMsg loginForWechat(String id) {
        /**
         * 一堆微信登录的逻辑
         */
        ResultMsg resultMsg = siginService.login("12345", "1111");
        return resultMsg;
    }
}
