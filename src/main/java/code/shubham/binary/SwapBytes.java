package code.shubham.binary;

public class SwapBytes {
    public int swap(int value) {
        return  ((value & 0xFF) << 24)   |         // Move byte 0 to byte 3
                ((value & 0xFF00) << 8)  |         // Move byte 1 to byte 2
                ((value >>> 8) & 0xFF00) |         // Move byte 2 to byte 1
                ((value >>> 24) & 0xFF);           // Move byte 3 to byte 0
    }

    public static String toHexString(int value) {
        return String.format("0x%08X", value);
    }

    public static void main(String[] args) {
        System.out.println(toHexString(new SwapBytes().swap(0xFFEE)));
    }

}
