package com.chen.study.concurrent.concurrent2.twoPhaseTermination;

import java.io.IOException;

/**
 * @author 陈添明
 * @date 2018/9/23
 */
public class AppServerClient {

    public static void main(String[] args) throws IOException, InterruptedException {
        AppServer server = new AppServer(13345);
        server.start();

        // telnet localhost 13345

        Thread.sleep(30000);

        server.shutdown();
    }
}
