package cn.com.trying.socket;
/**
* @Title: SocketTest3
* @Description: socket客户端模拟发送消息
* @author huxx
* @date 2019/12/10 下午4:03
* @update
*/
public class SocketTest3 {

    public static void main(String[] args) {

        SocketClientThread client2 = new SocketClientThread("test2","127.0.0.1",20001);
        client2.run();
    }
}
