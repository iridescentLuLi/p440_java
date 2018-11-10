package person.liushuang;

import person.liushuang.FFTpack.RealDoubleFFT;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.*;


public class main {

    String ip = "192.168.1.100";
    int port = 21210;
    byte[] receMsgs = new byte[1024];

    DatagramSocket datagramSocket;
    DatagramPacket datagramPacket;
    Socket socket = null;

    public void main(String[] args){
//        String ip = "192.168.1.100";
//        int port = 21210;
//        byte[] receMsgs = new byte[1024];
//
//        DatagramSocket datagramSocket;
//        DatagramPacket datagramPacket;
//        Socket socket = null;

        read_config();

        try {
            DatagramSocket sendSocket = new DatagramSocket();

//            InetAddress ip = InetAddress.getLocalHost();
//            datagramSocket = new DatagramSocket(port);
//            datagramPacket = new DatagramPacket(receMsgs, receMsgs.length);
//            datagramSocket.receive(datagramPacket);
//            System.out.println(receMsgs);


            datagramSocket = new DatagramSocket();
            InetAddress address = InetAddress.getByName(ip);
            Struct struct = new Struct();
            byte[] buf;
            long[] packet_get_config = {0x1002, 0};
            buf = struct.pack(">HH", packet_get_config);


            datagramPacket = new DatagramPacket(buf, buf.length, address, port);
            //datagramPacket = new DatagramPacket(packet_get_config, packet_get_config.length, address, port);
            datagramSocket.send(datagramPacket);
            byte[] receBuf = new byte[1024];
            DatagramPacket recePacket = new DatagramPacket(receBuf, receBuf.length);
            datagramSocket.receive(recePacket);

//            RealDoubleFFT.ft(sig);

            int len = recePacket.getLength();
            System.out.println("length is :" + len);
            String receStr = new String(recePacket.getData(), 0 , recePacket.getLength());
            System.out.println("Receive Ack:" + receStr);
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

    public void read_config(){
        try {
            DatagramSocket sendSocket = new DatagramSocket();

            datagramSocket = new DatagramSocket();
            InetAddress address = InetAddress.getByName(ip);
            Struct struct = new Struct();
            byte[] buf;
            long[] packet_get_config = {0x1002, 0};
            buf = struct.pack(">HH", packet_get_config);


            datagramPacket = new DatagramPacket(buf, buf.length, address, port);
            datagramSocket.send(datagramPacket);
            byte[] receBuf = new byte[1024];
            DatagramPacket recePacket = new DatagramPacket(receBuf, receBuf.length);
            datagramSocket.receive(recePacket);


            byte[] config_massage = recePacket.getData();
            Config current_config = new Config(config_massage);

            String lineEdit_distance_end = p440_config.ps2m(current_config.scan_end);
            System.out.println(lineEdit_distance_end);

        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
