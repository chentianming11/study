package com.chen.study.design.pattern.adapter.login.adapter;

import com.chen.study.design.pattern.adapter.login.ResultMsg;
import com.chen.study.design.pattern.adapter.login.SiginService;

/**
 * @author 陈添明
 * @date 2019/4/14
 */
public class LoginForQQAdapter extends LoginAdapter {

    public LoginForQQAdapter(SiginService siginService) {
        super(siginService);
    }

    /**
     * 兼容校验
     * @param adapter
     * @return
     */
    @Override
    public boolean support(Object adapter) {
        return adapter instanceof LoginForQQAdapter;
    }

    /**
     * 登录接口
     * @param params
     * @return
     */
    @Override
    public ResultMsg login(Object... params) {
        //1、openId 是全局唯一，我们可以把它当做是一个用户名(加长)
        // 2、密码默认为 QQ_EMPTY
        // 3、注册(在原有系统里面创建一个用户)
        //4、调用原来的登录方法
        ResultMsg resultMsg = siginService.login("12345", "1111");
        return resultMsg;
    }
}
