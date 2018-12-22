package com.chen.study.concurrent.concurrent2.threadlocal.test;

/**
 * @author 陈添明
 * @date 2018/9/23
 */
public class ActionContext {

    private static ThreadLocal<Context> threadLocal = ThreadLocal.withInitial(() -> new Context());


    private ActionContext() {
    }

    private static class ContextHolder {
        private static ActionContext actionContext = new ActionContext();

    }

    public static ActionContext getActionContext() {
        return ContextHolder.actionContext;
    }

    public Context getContext() {
        return threadLocal.get();
    }
}
