package person.liushuang;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.*;



public class main {
    public static void main(String[] args){
        String ip = "192.168.1.100";
        int port = 21210;
        byte[] receMsgs = new byte[1024];

        DatagramSocket datagramSocket;
        DatagramPacket datagramPacket;
        Socket socket = null;
        try {
            DatagramSocket sendSocket = new DatagramSocket();

//            InetAddress ip = InetAddress.getLocalHost();
//            datagramSocket = new DatagramSocket(port);
//            datagramPacket = new DatagramPacket(receMsgs, receMsgs.length);
//            datagramSocket.receive(datagramPacket);
//            System.out.println(receMsgs);


            datagramSocket = new DatagramSocket();
            InetAddress address = InetAddress.getByName(ip);
            byte[] receBuf = new byte[1024];
            String sendStr = "aaaaaaaaaaaaaaaaaa";
            int a = 0x1002;
            int[] packet_get_config = {0x1002, 0};

            Struct struct = new Struct();
            byte[] buf = new byte[8];
//            buf = struct.pack('>HH', 0x1002, 0);
//            byte[] buf = sendStr.getBytes();

            datagramPacket = new DatagramPacket(buf, buf.length, address, port);
            //datagramPacket = new DatagramPacket(packet_get_config, packet_get_config.length, address, port);
            datagramSocket.send(datagramPacket);
            DatagramPacket recePacket = new DatagramPacket(receBuf, receBuf.length);
            datagramSocket.receive(recePacket);
            int len = recePacket.getLength();
            System.out.println("length is :" + len);

            String receStr = new String(recePacket.getData(), 0 , recePacket.getLength());
            System.out.println("Client Rece Ack:" + receStr);
//            System.out.println(recePacket.getPort());


//            SocketAddress socketAddress = new InetSocketAddress(ip, port);
//            socket = new Socket(ip, port);
//            InputStream inputStream= socket.getInputStream();
//            BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
//            String server_info = null;
//            while((server_info=br.readLine())!=null){
//                System.out.println("服务端传过来的值："+server_info);
//            }


        }catch (Exception e){
            e.printStackTrace();
        }

    }

}
