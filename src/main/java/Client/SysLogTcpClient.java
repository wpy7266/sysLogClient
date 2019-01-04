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

public class SysLogTcpClient {
    public static void main(String[] ars) throws IOException {
        Integer TCP_PORT = Integer.parseInt(System.getProperty("tcp_port").toString());
        String SYSLOG_ADDRESS = System.getProperty("syslog_address").toString();
        Integer SYSLOG_PORT = Integer.parseInt(System.getProperty("syslog_port").toString());
        System.out.println("服务器启动中");
        //  创建服务器端ServerSocket类型的服务端Socket对象  端口号要和客户端一致
        ServerSocket sSocket = new ServerSocket(TCP_PORT);
        //  通过服务器端socket对象获取对应连接进入的客户端socket对象
        System.out.println("tcp_port:"+TCP_PORT+" syslog_address:"+SYSLOG_ADDRESS+" syslog_port:"+SYSLOG_PORT);
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
             StringBuffer buffer = new StringBuffer();
             buffer.append("data:" + data);
             SysLogTcpClient log2 = new SysLogTcpClient();
             log2.sendLog(SYSLOG_ADDRESS,SYSLOG_PORT,buffer.toString(),7);
            //log2.sendLog("127.0.0.1",5555,buffer.toString(),1);
        }
        // 关闭服务
        socket.close();
        sSocket.close();
    }





    public static void sendLog(String host,int port,String log,int level){
        try {

            TCPNetSyslogConfig config = new TCPNetSyslogConfig();
//设置syslog服务器端地址
            config.setHost(host);
//设置syslog接收端口，默认514
            config.setPort(port);
//向多个多个ip发送日志不执行shutdo会导致同一个实例无法发送到多个地址
            Syslog.shutdown();
//获取syslog的操作类，使用udp协议。syslog支持"udp", "tcp", "unix_syslog", "unix_socket"协议
            SyslogIF syslog = Syslog.createInstance("tcp", config);
            syslog.log(level, URLDecoder.decode(log,"utf-8"));
            //System.out.println("syslog Server:"+host+":"+port);
/* 发送信息到服务器，2表示日志级别 范围为0~7的数字编码，表示了事件的严重程度。0最高，7最低
* syslog为每个事件赋予几个不同的优先级：
0 LOG_EMERG：紧急情况，需要立即通知技术人员。
1 LOG_ALERT：应该被立即改正的问题，如系统数据库被破坏，ISP连接丢失。
2 LOG_CRIT：重要情况，如硬盘错误，备用连接丢失。
3 LOG_ERR：错误，不是非常紧急，在一定时间内修复即可。
4 LOG_WARNING：警告信息，不是错误，比如系统磁盘使用了85%等。
5 LOG_NOTICE：不是错误情况，也不需要立即处理。
6 LOG_INFO：情报信息，正常的系统消息，比如骚扰报告，带宽数据等，不需要处理。
7 LOG_DEBUG：包含详细的开发情报的信息，通常只在调试一个程序时使用。
*/
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
