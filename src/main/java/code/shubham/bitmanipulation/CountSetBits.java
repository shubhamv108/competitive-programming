package code.shubham.bitmanipulation;

public class CountSetBits {

    int count (int n) {
        int result = 0;
        for (int i = 0; i < 31; ++i) {
            int id = 1 << i;
            result += (n & id) == id ? 1 : 0;
        }
        return result;
    }

    int count2 (int n) {
        int result = 0;
        for (int i = 0; i < 31; ++i) {
            result += (n & 1);
            n >>= 1;
        }
        if (n == 1)
            ++result;
        return result;
    }

    int count3 (int n) {
        int result = 0;
        while (n != 0) {
            n = n & (n - 1);
            ++result;
        }
        return result;
    }


    public static void main(String[] args) {
        System.out.println(new CountSetBits().count3(7));
    }

}
