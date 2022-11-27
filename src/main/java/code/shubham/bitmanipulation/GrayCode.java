package code.shubham.bitmanipulation;

import java.util.ArrayList;
import java.util.List;

class GrayCode {
    public List<Integer> grayCode(int n) {
        List<Integer> result = new ArrayList<>();
        int sequenceLength = 1 << n;
        for (int i = 0; i < sequenceLength; i++) {
            result.add(i ^ (i >> 1));
        }
        return result;
    }
    
    public static void main(String[] args) {
        new GrayCode().grayCode(2).forEach(System.out::println);
    }

}