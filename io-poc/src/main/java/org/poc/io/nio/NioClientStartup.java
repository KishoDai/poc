package org.poc.io.nio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * 阻塞式I/O创建的客户端
 *
 * @author yangtao__anxpp.com
 * @version 1.0
 */
public class NioClientStartup {
    private static String DEFAULT_HOST = "127.0.0.1";
    private static int DEFAULT_PORT = 12345;
    private static NioClientHandler clientHandle;

    public static void start() {
        start(DEFAULT_HOST, DEFAULT_PORT);
    }

    public static synchronized void start(String ip, int port) {
        if (clientHandle != null)
            clientHandle.stop();
        clientHandle = new NioClientHandler(ip, port);
        new Thread(clientHandle, "Server").start();
    }

    //向服务器发送消息
    public static boolean sendMsg(String msg) throws Exception {
        if (msg.equals("q")) return false;
        clientHandle.sendMsg(msg);
        return true;
    }

    public static void main(String[] args) {
        start();
    }
}