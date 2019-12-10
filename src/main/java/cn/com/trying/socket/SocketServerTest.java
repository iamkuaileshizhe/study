package cn.com.trying.socket;

public class SocketServerTest {

    public static void main(String[] args) throws InterruptedException {
        SocketServerThread socketServerThread = new SocketServerThread(20001);
        socketServerThread.start();
    }
}
