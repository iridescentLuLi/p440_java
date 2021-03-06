package person.liushuang;

import person.liushuang.FFTpack.RealDoubleFFT;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.*;
import java.sql.Struct;
import java.util.Scanner;


public class main {

    String ip = "192.168.1.100";
    int port = 21210;

    byte[] receMsgs = new byte[1024];
    DatagramSocket datagramSocket;
    DatagramPacket datagramPacket;
    Socket socket = null;
    byte[] newbyte = new byte[44];
    Config config = new Config(newbyte);

    public main() throws JStruct.JStructException {
    }

    public void main(){
        System.out.println("写入配置1， 读取配置0 ：");
        Scanner in = new Scanner(System.in);
        int input = in.nextInt();
        if (input == 0){
            read_config();
        }
        else if (input == 1){
            write_config();
        }
    }
    public static void main(String[] args) throws JStruct.JStructException {
//        String ip = "192.168.1.100";
//        int port = 21210;
//        byte[] receMsgs = new byte[1024];
//
//        DatagramSocket datagramSocket;
//        DatagramPacket datagramPacket;
//        Socket socket = null;
        int a = 0;

        main gogogo = new main();
        gogogo.main();

////        try {
////            DatagramSocket sendSocket = new DatagramSocket();
////
//////            InetAddress ip = InetAddress.getLocalHost();
//////            datagramSocket = new DatagramSocket(port);
//////            datagramPacket = new DatagramPacket(receMsgs, receMsgs.length);
//////            datagramSocket.receive(datagramPacket);
//////            System.out.println(receMsgs);
////
////
////            datagramSocket = new DatagramSocket();
////            InetAddress address = InetAddress.getByName(ip);
////            Struct struct = new Struct();
////            byte[] buf;
////            long[] packet_get_config = {0x1002, 0};
////            buf = struct.pack(">HH", packet_get_config);
////
////
////            datagramPacket = new DatagramPacket(buf, buf.length, address, port);
////            //datagramPacket = new DatagramPacket(packet_get_config, packet_get_config.length, address, port);
////            datagramSocket.send(datagramPacket);
////            byte[] receBuf = new byte[1024];
////            DatagramPacket recePacket = new DatagramPacket(receBuf, receBuf.length);
////            datagramSocket.receive(recePacket);
////
//////            RealDoubleFFT.ft(sig);
////
////            int len = recePacket.getLength();
////            System.out.println("length is :" + len);
////            String receStr = new String(recePacket.getData(), 0 , recePacket.getLength());
////            System.out.println("Receive Ack:" + receStr);
//////            System.out.println(recePacket.getPort());
//
//
////            SocketAddress socketAddress = new InetSocketAddress(ip, port);
////            socket = new Socket(ip, port);
////            InputStream inputStream= socket.getInputStream();
////            BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
////            String server_info = null;
////            while((server_info=br.readLine())!=null){
////                System.out.println("服务端传过来的值："+server_info);
////            }
//
//
//        }catch (Exception e){
//            e.printStackTrace();
//        }

    }

    public void read_config(){
        try {
            InetAddress address = InetAddress.getByName(ip);

            byte[] buf;
            Object[] packet_get_config = {0x1002, 0};
//            JStruct js = new JStruct(">HH");
            buf = JStruct.pack(">HH", packet_get_config);
//            buf = js.pack(packet_get_config);
            datagramPacket = new DatagramPacket(buf, buf.length, address, port);


            datagramSocket = new DatagramSocket();
            datagramSocket.send(datagramPacket);
            byte[] receBuf = new byte[44];
            DatagramPacket recePacket = new DatagramPacket(receBuf, receBuf.length);
            datagramSocket.receive(recePacket);


            byte[] config_massage = recePacket.getData();
            Config current_config = new Config(config_massage);
            config = current_config;

            String lineEdit_distance_end = p440_config.ps2m(current_config.scan_end);
            System.out.println("distance = " + lineEdit_distance_end);

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void write_config(){
        //config write here
        int start = 0;
        int end = 20;
        long transmit_gain = 12;
        long base_int_index = 100;

        long[] T1 = p440_config.m2ps(start, end);
        config.scan_start = T1[0];
        config.scan_end = T1[1];
        config.transmit_gain = transmit_gain;
        config.base_int_index = base_int_index;

        try {
            InetAddress address = InetAddress.getByName(ip);
            datagramPacket = new DatagramPacket(config.to_bytes(), config.to_bytes().length, address, port);
            datagramSocket = new DatagramSocket();
            datagramSocket.send(datagramPacket);
            byte[] receBuf = new byte[1024];
            DatagramPacket recePacket = new DatagramPacket(receBuf, receBuf.length);
            datagramSocket.receive(recePacket);

            byte[] config_massage = recePacket.getData();
            Config current_config = new Config(config_massage);
            Object messtype = current_config.message_type;
            Object status = current_config.status;
            if ((long)messtype == 0x1101 && (long)status == 0){
                System.out.println("写入成功");
            }
            else {
                System.out.println("写入失败");
            }
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (SocketException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JStruct.JStructException e) {
            e.printStackTrace();
        }
    }
}
