package client;

import java.io.*;
import java.net.*;

public class client {
    public static void main(String[] args) {
        try {
            String serverIP = "172.24.2.8"; // 服务器绑定的IP地址
            int port = 8080; // 服务器监听的端口号

            Socket socket = new Socket(InetAddress.getByName("172.24.2.8"), port);
            // 构建HTTP请求
            String request = "GET /index.html HTTP/1.1\r\n" +
                    "Host: 172.24.2.8\r\n" +
                    "Connection: close\r\n\r\n";

            // 发送HTTP请求
            OutputStream outputStream = socket.getOutputStream();
            outputStream.write(request.getBytes());
            outputStream.flush();

            // 接收并打印响应
            InputStream inputStream = socket.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }

            // 关闭连接
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}