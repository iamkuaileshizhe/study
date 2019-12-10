package cn.com.trying.socket;
/**
* @Title: SocketTest1
* @Description:  socket客户端模拟发送消息
* @author huxx
* @date 2019/12/10 下午4:03
* @update
*/
public class SocketTest1 {

    public static void main(String[] args) {

        SocketClientThread client = new SocketClientThread("test1","127.0.0.1",20001);
        client.run();

    }
}
