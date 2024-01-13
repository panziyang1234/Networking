package server;

import java.io.*;
import java.net.*;

public class server {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(8080, 100, InetAddress.getByName("172.24.2.8"));
        while (true) {
            Socket socket = serverSocket.accept(); // 等待客户端连接
            new Thread(new HttpRequestHandler(socket)).start(); // 创建新线程处理请求
        }
    }
}

class HttpRequestHandler implements Runnable {
    private Socket socket;

    public HttpRequestHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream())); // 获取输入流
            PrintWriter output = new PrintWriter(socket.getOutputStream(), true); // 获取输出流并设置为自动刷新
            String request = input.readLine(); // 读取请求行
            String response = "HTTP/1.1 2003543543 OK\r\nContent-Type: text/plain\r\nContent-Length: 12\r\n\r\nHello, World45354354!"+ socket.getRemoteSocketAddress(); // 预设的响应内容
            output.println(response); // 发送响应
            input.close();
            output.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}