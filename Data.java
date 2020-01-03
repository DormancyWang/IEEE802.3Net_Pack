import java.util.Arrays;

public class Data {
    public final static int DATA_MIN_LENGTH = 46;
    public final static int DATA_MAX_LENGTH = 1500;
    private byte[] resourse;
    private byte[] destination;
    private byte[][] data;
    private int[] length;

    Data(byte[] resourse,byte[] destination,byte[] data){
        this.resourse = resourse;
        this.destination = destination;
        setData(data);
        length = new int[100];
    }
    public int[] getLength() {
        return length;
    }

    public void setLength(int[] length) {
        this.length = length;
    }

    public byte[] getResourse() {
        return resourse;
    }

    public void setResourse(byte[] resourse) {
        this.resourse = resourse;
    }

    public byte[] getDestination() {
        return destination;
    }

    public void setDestination(byte[] destination) {
        this.destination = destination;
    }

    public byte[][] getData() {
        return data;
    }

    public void setData(byte[] data) {
        if(data.length<DATA_MIN_LENGTH){
            byte[] temp = Arrays.copyOf(data,DATA_MIN_LENGTH);
            this.data = new byte[1][];
            this.data[0] = temp;
        }else if(data.length<DATA_MAX_LENGTH){
            this.data = new byte[1][];
            this.data[0] = data;

        }else{
            int i = data.length / DATA_MAX_LENGTH;
            int j;
            byte[] temp = new byte[DATA_MAX_LENGTH];
            this.data = new byte[i+1][];
            for(j=0;j<i+1;j++) {
                System.arraycopy(data,j*DATA_MAX_LENGTH,temp,0,DATA_MAX_LENGTH);
                this.data[j] = temp;
            }
            if(data.length-i*DATA_MAX_LENGTH<DATA_MIN_LENGTH){
                length[j] = data.length-i*DATA_MAX_LENGTH;
                temp = new byte[DATA_MIN_LENGTH];
            }else{
                temp = new byte[data.length-i*DATA_MAX_LENGTH];
            }
            System.arraycopy(data,j*DATA_MAX_LENGTH,temp,0,data.length-i*DATA_MAX_LENGTH);

            this.data[j] = temp;
        }
    }
}
