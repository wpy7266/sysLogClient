package Client;

import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class UdpServer {
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        //创建服务端 DatagramSocket类 + 端口（指定：我从哪个端口获取数据）
        DatagramSocket server = null;
        try {
            server = new DatagramSocket(5141);
            //准备接收数据 同样需要定义一个字节数组 work
            byte[] container = new byte[1024];
            DatagramPacket packet = new DatagramPacket(container, container.length);
            while(true) {
                //接收数据
                server.receive(packet);//阻塞方法
                //分析数据
                byte[] data = packet.getData();//获得客户端传输的真实数据
                System.out.println(new String(data));
            }

            //释放资源
            //server.close();


        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }


}

