package cn.com.trying.socket;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
/**
* @Title: SocketServerThread
* @Description:  socket服务端的线程处理类 使用多线程的方式并发处理多个socket连接
* @author huxx
* @date 2019/12/10 下午3:57
* @update
*/
public class SocketServerThread extends Thread    {

    private int port;

    public SocketServerThread(int port){
        this.port = port;
    }

    @Override
    public   void run() {

        try {

            ServerSocket server = new ServerSocket(port);

            while(true){
                System.out.println("wait client connect...");
                System.out.println("------------"+server.isClosed()+"---");
                Socket socket = server.accept();//此方法会堵塞

                //此处用线程的方式处理每个socket请求的业务处理
                BusinesshanderThread businesshanderThread = new BusinesshanderThread(socket);
                businesshanderThread.start();
//                sleep(1000);   //server.accept()是堵塞方法，不需要在sleep
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
