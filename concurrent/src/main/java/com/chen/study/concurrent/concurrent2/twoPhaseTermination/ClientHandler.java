package com.chen.study.concurrent.concurrent2.twoPhaseTermination;

import java.io.*;
import java.net.Socket;

/**
 * @author 陈添明
 * @date 2018/9/23
 */
public class ClientHandler implements Runnable {

    private final Socket socket;

    private volatile boolean running = true;



    public ClientHandler(Socket socket) {
        this.socket = socket;
    }


    @Override
    public void run() {


        try (InputStream inputStream = socket.getInputStream();
             OutputStream outputStream = socket.getOutputStream();
             BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
             PrintWriter printWriter = new PrintWriter(outputStream);
             ) {

            while (running){
                String message = br.readLine();
                if (message == null) {
                    break;
                }
                System.out.println("来自客户端的信息：" + message);
                printWriter.write("写入：" + message + "\n");
                printWriter.flush();
            }
        } catch (IOException e) {
            this.running = true;
        } finally {
            this.stop();
        }
    }

    public void stop(){
        if (!running){
            return;
        }
        this.running = false;
        try {
            this.socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
