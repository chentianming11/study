package com.chen.study.concurrent.concurrent2.active.objects;

/**
 * @author 陈添明
 * @date 2018/10/1
 */
public class DisplayStringRequest extends MethodRequest {


    private final String text;

    public DisplayStringRequest(Servant servant, String text) {
        super(servant, null);
        this.text = text;
    }

    @Override
    public void execute() {
        this.servant.displayString(text);
    }
}
