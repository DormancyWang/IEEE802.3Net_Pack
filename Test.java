import java.util.Arrays;

public class Test {
   static Byte a = 0x07;

    public static void main(String[] args) {
        String a = "1a:43:23:32:7b";
        byte[] c = {0x1a,0x43,0x23,0x32,0x7b};
        String[] b =a.split(":");
        StringBuffer buffer = new StringBuffer();
        for(String temp : b){
            buffer.append(temp);
        }

    }
}
