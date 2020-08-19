package com.changsheng.diytomcat;

import cn.hutool.core.util.NetUtil;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author changshengee
 */
public class BootStrap {
    public static void main(String[] args) throws IOException {
        int port = 18080;
        if (!NetUtil.isUsableLocalPort(port)) {
            System.out.println(port + ":address is already used by another application");
            return;
        }
        ServerSocket serverSocket = new ServerSocket(port);
        while (true) {
            Socket socket = serverSocket.accept();
            InputStream in = socket.getInputStream();
            int buffSize = 1024;
            byte[] buffer = new byte[buffSize];
            in.read(buffer);
            String requestString = new String(buffer, "UTF-8");
            System.out.println("Browser input info:\r\n" + requestString);
            OutputStream out = socket.getOutputStream();
            String responseHeader = "HTTP/1.1 200 OK\r\n" + "ContentType:text/xml\r\n\r\n";
            String responseString = "Hello World";
            responseString = responseHeader + responseString;
            out.write(responseString.getBytes());
            out.flush();
            socket.close();
        }


    }
}
