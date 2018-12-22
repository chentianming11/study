package com.chen.study.concurrent.concurrent2.threadlocal.test;

/**
 * @author 陈添明
 * @date 2018/9/23
 */
public class ExecutionTask implements Runnable {


    private QueryDBAction queryAction = new QueryDBAction();
    private QueryFromHttpAction httpAction = new QueryFromHttpAction();

    @Override
    public void run() {
//        Context context = new Context();

        // 从db中获取数据，（通过context获取参数，并将结果放入context）
        queryAction.execute();
        // 从http中获取数据，（通过context获取参数，并将结果放入context）
        httpAction.execute();
        Context context = ActionContext.getActionContext().getContext();
        System.out.println(" name=" + context.getName() + ", cardId=" + context.getCardId());

    }
}
