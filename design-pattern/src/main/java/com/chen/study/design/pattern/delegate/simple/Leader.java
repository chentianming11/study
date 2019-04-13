package com.chen.study.design.pattern.delegate.simple;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 陈添明
 * @date 2019/4/13
 */
public class Leader {

    private Map<String, IEmployee> register = new HashMap<>();

    public Leader() {
        register.put("加密", new EmployeeA());
        register.put("架构", new EmployeeB());
    }

    /**
     * leader根据不同的命令，将任务分发给不同的员工
     * @param command
     */
    public void doing(String command) {
        register.get(command).doing(command);
    }
}
