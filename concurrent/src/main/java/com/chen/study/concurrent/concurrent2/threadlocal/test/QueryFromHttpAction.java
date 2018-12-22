package com.chen.study.concurrent.concurrent2.threadlocal.test;

/**
 * @author 陈添明
 * @date 2018/9/23
 */
public class QueryFromHttpAction {


    public void execute() {
        Context context = ActionContext.getActionContext().getContext();
        String name = context.getName();
        String cardId = getCardId(name);

        context.setCardId(cardId);
    }

    private String getCardId(String name){
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return "2452363563456---" + Thread.currentThread().getName();
    }
}
