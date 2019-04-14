package com.chen.study.design.pattern.adapter.login.adapter;

import com.chen.study.design.pattern.adapter.login.ResultMsg;
import com.chen.study.design.pattern.adapter.login.SiginService;

/**
 * @author 陈添明
 * @date 2019/4/14
 */
public abstract class LoginAdapter {

    protected SiginService siginService;

    public LoginAdapter(SiginService siginService) {
        this.siginService = siginService;
    }

    /**
     * 兼容校验
     *
     * @param adapter
     * @return
     */
   public abstract boolean support(Object adapter);

    /**
     * 登录接口
     * @param params
     * @return
     */
   public abstract ResultMsg login(Object... params);
}
