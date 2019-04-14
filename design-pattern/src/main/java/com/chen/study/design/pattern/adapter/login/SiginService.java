package com.chen.study.design.pattern.adapter.login;

/**
 * 之前的登录代码
 * 为了遵循开闭原则，老系统的代码我们不会去修改
 */
public class SiginService {
    /**
     * 注册方法
     *
     * @param username * @param password * @return
     */
    public ResultMsg regist(String username, String password) {
        return new ResultMsg(200, "注册成功", new Member());
    }

    /**
     * 登录的方法
     *
     * @param username * @param password * @return
     */
    public ResultMsg login(String username, String password) {
        return null;
    }
}