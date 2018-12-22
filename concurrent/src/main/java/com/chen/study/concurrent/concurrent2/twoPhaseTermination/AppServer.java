package com.chen.study.concurrent.concurrent2.twoPhaseTermination;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author 陈添明
 * @date 2018/9/23
 */
public class AppServer extends Thread {

    private int port;

    private static final int DEFAULT_PORT = 12722;

    private volatile boolean start = true;

    private List<ClientHandler> clientHandlers = new ArrayList<>();

    private static ExecutorService executorService = Executors.newFixedThreadPool(5);

    private ServerSocket server;

    public AppServer(int port) {
        this.port = port;
    }

    public AppServer(){
        this(DEFAULT_PORT);
    }


    @Override
    public void run() {
        try {
            server = new ServerSocket(port);
            System.out.println("服务端启动了。。");
            while (start){
                Socket client = server.accept();
                ClientHandler clientHandler = new ClientHandler(client);
                executorService.execute(clientHandler);
                clientHandlers.add(clientHandler);

            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            this.dispose();
        }
    }

    private void dispose() {
        clientHandlers.forEach(ClientHandler::stop);

        executorService.shutdown();
    }


    public void shutdown() throws IOException {
        this.start = false;
        server.close();
        this.interrupt();
    }
}
