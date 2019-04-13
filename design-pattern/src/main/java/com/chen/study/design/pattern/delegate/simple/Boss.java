package com.chen.study.design.pattern.delegate.simple;

/**
 * boss - 发送命令
 * @author 陈添明
 * @date 2019/4/13
 */
public class Boss {

    private Leader leader;

    public Boss(Leader leader) {
        this.leader = leader;
    }

    public void doing(String command) {
        leader.doing(command);
    }
}
