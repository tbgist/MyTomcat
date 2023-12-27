package com.example;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;


public class TomcatServer {
    public static void main(String[] args)throws IOException{
        //开启ServerSocket服务，设置端口号为8080
        ServerSocket serverSocket=new ServerSocket(8080);
        System.out.println("======服务启动成功========");
        //当服务没有关闭时
        while(!serverSocket.isClosed()){
            //使用socket进行通信
            Socket socket=serverSocket.accept();
            //收到客户端请求，为每个连接分配一个线程
            RequestHandler requestHandler = new RequestHandler(socket);
            new Thread(requestHandler).start();
        }
        serverSocket.close();
    }
}
