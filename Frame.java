import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public  class Frame {
    private byte[] crc ;
    private Data data;
    private byte[][] ans;
    private static final char[] HEX_CHAR = {'0', '1', '2', '3', '4', '5',
            '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};



    Frame(Data data){
        this.data = data;
    }
    public void CRC(){
        byte[][] data1 = data.getData();
        this.crc = new byte[data.getData().length];
        byte[] temp = Arrays.copyOf(data.getResourse(),14+data.getData().length);
        System.arraycopy(data.getDestination(),0,temp,6,6);
        for(int i=0;i<data1.length;i++){
            if((data.getLength())[i]!=0) {
                temp[12] =(byte)((data.getLength()[i] & 0xff00)>>4);
                temp[13] =(byte)((data.getLength()[i]) & 0x00ff);
            }else{
                temp[12] =(byte)((data.getData()[i].length & 0xff00)>>4);
                temp[13] =(byte)((data.getData()[i].length & 0x00ff));
            }
            System.arraycopy((data.getData())[i],0,temp,14,data.getData().length);
            crc[i] = CRC.getCRC(temp);
        }
    }
    public void pack(){
        this.ans = new byte[data.getData().length][];
        for(int i=0;i<data.getData().length;i++){
            ans[i] = new byte[26+(data.getData())[i].length];
            for(int j=0;j<7;j++)
                ans[i][j] = (byte)0xaa;
            ans[i][7] = (byte)0xAB;
            System.arraycopy(data.getResourse(),0,ans[i],8,6);
            System.arraycopy(data.getDestination(),0,ans[i],14,6);
            if(data.getLength()[i]!=0) {
                ans[i][20] =(byte)((data.getLength()[i] & 0xff00)>>4);
                ans[i][21] =(byte)((data.getLength()[i]) & 0x00ff);
            }else{
                ans[i][20] =(byte)((data.getData()[i].length & 0xff00)>>4);
                ans[i][21] =(byte)((data.getData()[i].length & 0x00ff));
            }
            System.arraycopy((data.getData())[i],0,ans[i],22,data.getData()[i].length);
            ans[i][26+data.getData()[i].length-1] = crc[i];
        }
    }

    public String getCrc() {
        return bytesToHex1(crc);
    }

    public String[] getAns() {
        String[] ans = new String[this.ans.length];
        for(int i=0;i<this.ans.length;i++){
            ans[i] = bytesToHex1(this.ans[i]);
        }
        return ans;
    }

    public static Frame process(String resource, String dest, String sdata, Frame frame){
        byte[] res = gb(resource);
        byte[] des = gb(dest);
        byte[] data = sdata.getBytes();
        frame = new Frame(new Data(res,des,data));
        frame.CRC();
        frame.pack();
        return frame;
    }

    private static byte[] gb(String s){
        String[] b =s.split(":");
        StringBuffer buffer = new StringBuffer();
        for(String temp : b){
            buffer.append(temp);
        }
        return htb(buffer.toString());
    }
    private static byte[] htb(String s) {
        int len = s.length();
        byte[] data = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4)
                    + Character.digit(s.charAt(i+1), 16));
        }
        return data;
    }
    public static String bytesToHex1(byte[] bytes) {
        // 一个byte为8位，可用两个十六进制位标识
        char[] buf = new char[bytes.length * 2];
        int a = 0;
        int index = 0;
        for(byte b : bytes) { // 使用除与取余进行转换
            if(b < 0) {
                a = 256 + b;
            } else {
                a = b;
            }

            buf[index++] = HEX_CHAR[a / 16];
            buf[index++] = HEX_CHAR[a % 16];
        }

        return new String(buf);
    }
}
