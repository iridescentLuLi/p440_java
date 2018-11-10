package person.liushuang;

class Config{
    private long[] config_massage;
    private String struct_pattren = ">HHIiiHHHHHHBBBBBBBBII";
    Struct struct = new Struct();
    public Config(byte[] frame_bytes){
        try {
            config_massage = struct.unpack(struct_pattren, frame_bytes);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    long message_type = config_massage[0];
    long message_id = config_massage[1];
    long node_id = config_massage[2];
    long scan_start = config_massage[3];
    long scan_end = config_massage[4];
    long scan_resolution = config_massage[5];
    long base_int_index = config_massage[6];
    long seg1 = config_massage[7];
    long seg2 = config_massage[8];
    long seg3 = config_massage[9];
    long seg4 = config_massage[10];
    long seg1_int = config_massage[11];
    long seg2_int = config_massage[12];
    long seg3_int = config_massage[13];
    long seg4_int = config_massage[14];
    long antenna_mode = config_massage[15];
    long transmit_gain = config_massage[16];
    long code_channel = config_massage[17];
    long persist_flag = config_massage[18];
    long time_stamp = config_massage[19];
    long status = config_massage[20];
}

public class p440_config{
    static String ps2m(long ps){
        double c = 0.29979;
        int dT0 = 10;
        double result = c * (ps / 1000 - dT0) / 2;
        new java.text.DecimalFormat("#.00").format(result);
        return result+"";
    }
}
