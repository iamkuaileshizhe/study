package cn.com.trying.socket;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
/**
* @Title: businesshanderThread
* @Description: socket建立后进行业务处理线程类
* @author huxx
* @date 2019/12/10 下午3:49
* @update
*/
public class BusinesshanderThread extends  Thread {
    private Socket socket ;
    public BusinesshanderThread(Socket socket){
        this.socket = socket;
    }
    @Override
    public void run(){
        BufferedInputStream bif=null;
        DataInputStream dis=null;
        InputStream in=null;
        try{
            in = socket.getInputStream();
            String info="";
            bif=new BufferedInputStream(in);
            dis=new DataInputStream(bif);
            byte[] bytes=new byte[1024];
            int len = 0;
            while((len=dis.read(bytes))!=-1) {
                String tempStr=new String(bytes,0,len);
                info = tempStr;
                System.out.println("-------------------"+info);
                if(len == 0){
                    in = socket.getInputStream();
                }
            }
        }catch (IOException e) {
            e.printStackTrace();
        }

    }
}
