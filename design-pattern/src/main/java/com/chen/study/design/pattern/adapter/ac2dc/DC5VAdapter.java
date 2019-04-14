package com.chen.study.design.pattern.adapter.ac2dc;

/**
 * 适配器实现目标接口，同时持有被适配对象
 * @author 陈添明
 * @date 2019/4/14
 */
public class DC5VAdapter implements IDC5 {


    IAC220 iac220;

    public DC5VAdapter(IAC220 iac220) {
        this.iac220 = iac220;
    }

    @Override
    public int outputDC5V() {
        int i = iac220.outputAC220V();
        int r = i / 44;
        System.out.println("输出直流" + r + "V");
        return r;
    }
}
