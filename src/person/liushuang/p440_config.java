package person.liushuang;

class Config{
//    Object[] config_massage = {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
    Object[] config_massage;
    long message_type, message_id, node_id, scan_start, scan_end,
            scan_resolution, base_int_index, seg1, seg2, seg3,
            seg4, seg1_int, seg2_int, seg3_int, seg4_int,
            antenna_mode, transmit_gain, code_channel, persist_flag,
            time_stamp, status;


    public Config(){

    }
    private String struct_pattren = ">HHIiiHHHHHHBBBBBBBBII";
    public Config(byte[] frame_bytes) throws JStruct.JStructException {
//        config_massage = JStruct.unpack(struct_pattren, frame_bytes);
        config_massage = JStruct.unpack(">HHIiiHHHHHHBBBBBBBBII", frame_bytes);
        message_type = Long.valueOf(String.valueOf(config_massage[0])).longValue();
        message_id = Long.valueOf(String.valueOf(config_massage[1])).longValue();
        node_id = Long.valueOf(String.valueOf(config_massage[2])).longValue();
        scan_start = Long.valueOf(String.valueOf(config_massage[3])).longValue();
        scan_end = Long.valueOf(String.valueOf(config_massage[4])).longValue();
        scan_resolution = Long.valueOf(String.valueOf(config_massage[5])).longValue();
        base_int_index = Long.valueOf(String.valueOf(config_massage[6])).longValue();
        seg1 = Long.valueOf(String.valueOf(config_massage[7])).longValue();
        seg2 = Long.valueOf(String.valueOf(config_massage[8])).longValue();
        seg3 = Long.valueOf(String.valueOf(config_massage[9])).longValue();
        seg4 = Long.valueOf(String.valueOf(config_massage[10])).longValue();
        seg1_int = Long.valueOf(String.valueOf(config_massage[11])).longValue();
        seg2_int = Long.valueOf(String.valueOf(config_massage[12])).longValue();
        seg3_int = Long.valueOf(String.valueOf(config_massage[13])).longValue();
        seg4_int = Long.valueOf(String.valueOf(config_massage[14])).longValue();
        antenna_mode = Long.valueOf(String.valueOf(config_massage[15])).longValue();
        transmit_gain = Long.valueOf(String.valueOf(config_massage[16])).longValue();
        code_channel = Long.valueOf(String.valueOf(config_massage[17])).longValue();
        persist_flag = Long.valueOf(String.valueOf(config_massage[18])).longValue();
        time_stamp = Long.valueOf(String.valueOf(config_massage[19])).longValue();
        status = Long.valueOf(String.valueOf(config_massage[20])).longValue();
    }



    public byte[] to_bytes(){
        int persist_flag = 1;
        int message_type = 0x1001;
        int node_id = 100;
        int scan_resolution = 32;
        String struct_pattern = ">HHIiiHHHHHHBBBBBBBB";
        try {
            byte[] result = JStruct.pack(struct_pattern, this.config_massage);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new byte[0];
    }
}



public class p440_config{
    static String ps2m(long ps){
        double c = 0.29979;
        int dT0 = 10;
        double result = c * (ps / 1000 - dT0) / 2;
        new java.text.DecimalFormat("#.00").format(result);
        return result+"";
    }

    static long[] m2ps(int m1, int m2){
        double c = 0.29979;
        double dTmin = 1/ (512 * 1.024);
        double Tbin = 32 * dTmin;
        int dNBin = 96;
        int dT0 = 10; //ns related to antenna
        double T1 = 2 * m1 / c + dT0;
        double T2 = 2 * m2 / c + dT0;

        double Nbin = (T2 - T1) / Tbin;
        double Nseg = Math.ceil(Nbin / dNBin);
        Nbin = dNBin * Nseg;

        T1 = Math.floor(1000*dTmin* Math.floor(T1/dTmin)); // in ps
        T2 = Math.floor(1000*dTmin* Math.floor(T2/dTmin)); // in ps
        long[] result = {(long)T1, (long)T2};
        return result;
    }
}
