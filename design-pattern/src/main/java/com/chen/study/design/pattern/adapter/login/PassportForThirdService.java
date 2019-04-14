package com.chen.study.design.pattern.adapter.login;

import com.chen.study.design.pattern.adapter.login.adapter.LoginAdapter;
import com.chen.study.design.pattern.adapter.login.adapter.LoginForQQAdapter;
import com.chen.study.design.pattern.adapter.login.adapter.LoginForWechatAdapter;
import lombok.SneakyThrows;

import java.lang.reflect.Constructor;

/**
 *
 * @author 陈添明
 * @date 2019/4/14
 */
public class PassportForThirdService implements IPassportForThird {

    private SiginService siginService;

    public PassportForThirdService(SiginService siginService) {
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
        return processLogin(LoginForQQAdapter.class, id);
    }
    /**
     * 微信登录
     *
     * @param id
     */
    @Override
    public ResultMsg loginForWechat(String id) {
        return processLogin(LoginForWechatAdapter.class, id);
    }

    @SneakyThrows
    private ResultMsg processLogin(Class<? extends LoginAdapter> clz, Object... params) {
        Constructor<? extends LoginAdapter> constructor = clz.getConstructor(SiginService.class);
        LoginAdapter loginAdapter = constructor.newInstance(siginService);
        if (loginAdapter.support(loginAdapter)) {
            return loginAdapter.login(params);
        }
        return null;
    }
}
