package cn.com.trying.socket;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
/**
* @Title: SocketClientThread
* @Description:  socket客户端线程处理线程类
* @author huxx
* @date 2019/12/10 下午4:01
* @update
*/
public class SocketClientThread extends Thread {

    private String serverIp ;
    private int serverPort;
    private  String flag = ""; //线程实例标识

    public SocketClientThread(String flag,String serverIp,int serverPort){
        this.flag = flag;
        this.serverIp = serverIp;
        this.serverPort = serverPort;
    }
    @Override
    public void run() {
        OutputStream os= null;
        try {
            Socket socket = new Socket(serverIp,serverPort);
            os= socket.getOutputStream();
            String in="";
            int i = 0;
            do {
                String  info = flag +"------"+System.currentTimeMillis();
                os.write(info.getBytes());
                os.flush();
                i+=1;
                System.out.println(flag + "------------------------"+i);
                sleep(1000 * 1);
            } while (true); //此处是模拟不停的发送消息
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
