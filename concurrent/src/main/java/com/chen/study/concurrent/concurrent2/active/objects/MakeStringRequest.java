package com.chen.study.concurrent.concurrent2.active.objects;

/**
 * {@link ActiveObject#makeString(int, char)}
 * @author 陈添明
 * @date 2018/10/1
 */
public class MakeStringRequest extends MethodRequest {

    private final int count ;
    private final char fillChar;

    public MakeStringRequest(Servant servant, FutureResult futureResult,int count, char fillChar) {
        super(servant, futureResult);
        this.count = count;
        this.fillChar = fillChar;
    }

    @Override
    public void execute() {
        Result result = servant.makeString(count, fillChar);
        futureResult.setResult(result);

    }
}
