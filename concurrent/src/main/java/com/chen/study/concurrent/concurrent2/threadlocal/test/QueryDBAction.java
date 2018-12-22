package com.chen.study.concurrent.concurrent2.threadlocal.test;

/**
 * @author 陈添明
 * @date 2018/9/23
 */
public class QueryDBAction {

    public void execute(){
        try {
            Context context = ActionContext.getActionContext().getContext();
            Thread.sleep(1000);
            String name = "chen" + Thread.currentThread().getName();
            context.setName(name);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
