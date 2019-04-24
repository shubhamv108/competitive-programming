package code.contestpractice.hackerearth.wissen;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// WA
public class FindingTheSubarrays {

// Warning: Printing unwanted or ill-formatted data to output will cause the test cases to fail


        public static void main(String args[] ) throws Exception {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            int n = Integer.valueOf(br.readLine());
            String[] line = br.readLine().split(" ");
            int[] arr = new int[n];
            for (int i = 0; i < n; i++) arr[i] = Integer.valueOf(line[i]);
            line = null;
            Map<Integer, List<Long>> m = new HashMap<>();
            for (int i = 0; i < n; i++) {
                m.put(i, new ArrayList<>());
                for (int j = i; j < n; j++) {
                    if (i == j) m.get(i).add((long) arr[j]);
                    else m.get(i).add(m.get(i).get(j - i - 1) + arr[j]);
                }
            }
            arr = null;
            List<int[]> result = new ArrayList<>();
            int count  = 0;
            boolean isValid = false;
            for (int i = 0; i < n; i++) {
                for (int j = i; j < n; j++) {
                    if (i == 0 && j < n - 1 && m.get(i).get(j-i)/(j+1-i) > m.get(j+1).get(n-1-(j+1))/(n-(j+1-i))) {
                        isValid = true;
                    } else if (i == n - 1 && m.get(i).get(j-i) > m.get(0).get(i-1)) {
                        isValid = true;
                    } else if (i > 0 && j < n - 1 && m.get(i).get(j-i)/(j+1-i) > (m.get(0).get(i-1) + m.get(j+1).get(n-1-(j+1)))/(n-(j+1-i))) {
                       isValid = true;
                    } else if (i == 0 && j == n - 1) {
                        isValid = true;
                    }
                    if (isValid) {
                        count++;
                        result.add(new int[] { i+1, j+1 });
                        isValid = !isValid;
                    }
                }
            }
            System.out.println(count);
            result.sort((a, b) -> {
                if (a[0] > b[0]) return 1;
                else if (a[0] <= b[0] && a[1] > b[1]) return 1;
                return -1;
            });
            result.stream().forEach (e -> System.out.println(e[0] + " " + e[1]));
        }
    }
