package code.shubham.trees;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toList;

public class PartitionTreeIntoTwoAlmostEqualWeightedSubtrees {




    class Result {

        /*
         * Complete the 'mostBalancedPartition' function below.
         *
         * The function is expected to return an INTEGER.
         * The function accepts following parameters:
         *  1. INTEGER_ARRAY parent
         *  2. INTEGER_ARRAY files_size
         */

        static int result = Integer.MAX_VALUE;
        public static int mostBalancedPartition(List<Integer> p, List<Integer> fs) {
            HashMap<Integer, ArrayList<Integer>> kids = new HashMap<>();
            int[] bUp = new int[p.size()];

            for (int i = 0; i < fs.size(); i++) {
                kids.computeIfAbsent(p.get(i), e -> new ArrayList<>()).add(i);
                bUp[i] += fs.get(i);
                int j = i;
                while (p.get(j) != -1) {
                    bUp[p.get(j)] += bUp[i];
                    j = p.get(j);
                }
            }

//            System.out.print(kids);
//            Arrays.stream(bUp).forEach(e -> System.out.print(e + " "));

            recurse(0, 0, p, fs, kids, bUp);



            return result;
        }

        static void recurse(int i, int pWeight, List<Integer> p, List<Integer> fs, HashMap<Integer, ArrayList<Integer>> kids, int[] bUp) {

            ArrayList<Integer> kidz = kids.get(i);
            if (kidz != null)
                for (int k : kidz) {
                    result = Math.min(result, Math.abs((bUp[i] - bUp[k] + pWeight) - bUp[k]));
                    recurse(k, bUp[i] - bUp[k], p, fs, kids, bUp);
                }

        }


    }

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        int parentCount = Integer.parseInt(bufferedReader.readLine().trim());

        List<Integer> parent = IntStream.range(0, parentCount).mapToObj(i -> {
                    try {
                        return bufferedReader.readLine().replaceAll("\\s+$", "");
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                })
                .map(String::trim)
                .map(Integer::parseInt)
                .collect(toList());

        int files_sizeCount = Integer.parseInt(bufferedReader.readLine().trim());

        List<Integer> files_size = IntStream.range(0, files_sizeCount).mapToObj(i -> {
                    try {
                        return bufferedReader.readLine().replaceAll("\\s+$", "");
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                })
                .map(String::trim)
                .map(Integer::parseInt)
                .collect(toList());

        int result = Result.mostBalancedPartition(parent, files_size);

        System.out.println(String.valueOf(result));

        bufferedReader.close();
    }

}
