package com.chen.study.concurrent.concurrent2.active.objects;

/**
 * 对应ActiveObject的每一个方法
 * @author 陈添明
 * @date 2018/10/1
 */
public abstract class MethodRequest {

    protected final Servant servant;

    protected final FutureResult futureResult;

    public MethodRequest(Servant servant, FutureResult futureResult) {
        this.servant = servant;
        this.futureResult = futureResult;
    }

    public abstract void execute();

}
