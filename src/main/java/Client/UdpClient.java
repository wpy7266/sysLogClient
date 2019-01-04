package Client;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;

public class UdpClient {
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        //创建客户端  + 端口 (指定：我的数据从哪个端口出去)
        DatagramSocket client = null;
        DatagramPacket packet = null;
        byte[] data = null;
        try {
            while (true){
            client = new DatagramSocket(6666);
            //准备数据
            String msg = "agramPacket(data, data.length, new Inet完成打包mPacket(data, data.length, new Inet完成ta, data.length, new InetSocketAdd";
            data = msg.getBytes();
            //将数据打包 DatagramPacket + 服务器地址及端口号 来完成打包
            packet = new DatagramPacket(data, data.length, new InetSocketAddress("127.0.0.1", 5142));
            //发送
            client.send(packet);
            //释放资源
            client.close();}
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

}
