package Client;

import org.productivity.java.syslog4j.Syslog;
import org.productivity.java.syslog4j.SyslogIF;
import org.productivity.java.syslog4j.impl.net.tcp.TCPNetSyslogConfig;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URLDecoder;
import java.util.Properties;

public class Tcpclient {
    public static void main(String[] ars) throws IOException {
        System.out.println("服务器启动中");
        //  创建服务器端ServerSocket类型的服务端Socket对象  端口号要和客户端一致
        ServerSocket sSocket = new ServerSocket(10003);
        //  通过服务器端socket对象获取对应连接进入的客户端socket对象
        //  具体连入的客户端(创建的socket)  accpet()方法
        Socket socket = sSocket.accept();  // 阻塞状态

        //  确定连入者
        //String name = socket.getInetAddress().getHostAddress();
        //  通过客户端具体的客户端socket对象与其对应的客户端进行交互
        InputStream in = socket.getInputStream();

        //  注: 通过socket 通道中的字节流按照1024个字节(缓冲区大小) 持续不断的读取socket通道中的信息
        byte[] buf = new byte[1024];
        int len = 0;
        while((len = in.read(buf)) != -1){
            String data = new String(buf,0,len);
            System.out.println(data+"*************************************");
        }
        // 关闭服务
        socket.close();
        sSocket.close();
    }







}

