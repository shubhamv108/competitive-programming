package code.contestpractice.techgig;
/*
 * Enter your code here. Read input from STDIN. Print your output to STDOUT.
 * Your class should be named CandidateCode.
 */

import java.io.*;
import java.util.*;
public class SortByOrg {
    public static void main(String args[] ) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] line = new String[2];
        Integer[] B = null;
        Map<Integer, Set<String>> map = new HashMap<>();
        int collegeCount = 0;
        while (line != null) {
            boolean found = false;
            String s = br.readLine();
            if (s.equals("")) break;
            if (s != null) {
                line = s.split(" ");
                for (Integer i : map.keySet()) {
                    Set<String> set = map.get(i);
                    if ( null != set && (set.contains(line[0])
                            || set.contains(line[1])) ) {
                        set.add(line[0]);
                        set.add(line[1]);
                        found = true;
                        break;
                    }

                }
                if (!found) {
                    Set<String> set = new HashSet<>();
                    map.put(++collegeCount, set);
                    set.add(line[0]);
                    set.add(line[1]);
                }
            } else {
                line = null;
            }
        }

        long result = 0;
        for (int i = 1; i < collegeCount; i++) {
            int a = map.get(i).size();
            for (int j = i+1; j <= collegeCount; j++) {
                int b = map.get(j).size();
                result += (a * b);
            }
        }
        System.out.println(result);
    }
}
