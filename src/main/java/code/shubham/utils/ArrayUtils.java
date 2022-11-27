package code.shubham.utils;


import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class Array {

    private Object[] a;
    private int n;

    Array (int n) { this.a = new Object[n]; this.n = n; }

    public Object[] set (Object[] a, int idx, Object value) { a[idx] = value; return a; }
    public Object get (Object[] a, int idx) { return a[idx]; }

    public Object[] rightRotate () {
        Object temp;
        temp = a[n - 1];
        for (int i = n - 1; i > 0; i--) a[i] = a[i - 1];
        a[0] = temp;
        return a;
    }

    public Object[] leftRotate (Object[] a) {
        Object temp;
        temp = a[0];
        for (int i = 0; i < n - 2; i++) a[i] = a[i + 1];
        a[n-1] = temp;
        return a;
    }

}

class Array2D {

    private Object[][] a;
    private int n;

    Array2D(int n, int m) { this.a = new Object[n][m]; this.n = n; }

    public Object[][] set (Object[][] a, int i, int j, Object value) { a[i][j] = value; return a; }
    public Object get (Object[][] a, int i, int j) { return a[i][j]; }
    public Object[][] set (Object[][] a, int i, Object[] value) { a[i] = value; return a; }
    public Object[] get (Object[][] a, int i) { return a[i]; }

    public int rows (Object[][] a) {
        return a.length;
    }

    public int columns (Object[][] a) {
        return a[0].length;
    }

}

public class ArrayUtils {

    public static Object[]   set (Object[] a, int idx, Object value)        { a[idx] = value; return a; }
    public static Object     get (Object[] a, int idx)                      { return a[idx]; }
    public static Object[][] set (Object[][] a, int i, int j, Object value) { a[i][j] = value; return a; }
    public static Object     get (Object[][] a, int i, int j)               { return a[i][j]; }
    public static Object[][] set (Object[][] a, int i, Object[] value)      { a[i] = value; return a; }
    public static Object[]   get (Object[][] a, int i)                      { return a[i]; }

    public static int rows (Object[][] a) {
        return a.length;
    }

    public static int rows (int[][] a) {
        return a.length;
    }

    public static int columns (Object[][] a) {
        return a[0].length;
    }

    public static int[][] fill2DWithMinusOne (int[][] a) {
        return fill(a, -1);
    }

    public static Object[] fillWithMinusOne (Object[] a) {
        return fill(a, -1);
    }

    public static Object[][] fill2DWithMinusOne (Object[][] a) {
        return fill(a, -1);
    }

    public static Object[] fill (Object[] a, Object v) {
        Arrays.fill(a, v);
        return a;
    }

    public static int[][] fill (int[][] a, int v) {
        IntStream.range(0, rows(a)).forEach(i -> Arrays.fill(a[i], v));
        return a;
    }

    public static Object[][] fill(Object[][] a, Object v) {
        IntStream.range(0, rows(a)).forEach(i -> Arrays.fill(a[i], v));
        return a;
    }

    public static Object[] rightRotate (Object[] a) {
        int n = a.length;
        Object temp;
        temp = a[n - 1];
        for (int i = n - 1; i > 0; i--) a[i] = a[i - 1];
        a[0] = temp;
        return a;
    }

    public static Object[] leftRotate (Object[] a) {
        int n = a.length;
        Object temp;
        temp = a[0];
        for (int i = 0; i < n - 2; i++) a[i] = a[i + 1];
        a[n-1] = temp;
        return a;
    }

    private static boolean hasWordHelper (char[][] grid, int R, int C, int r, int c, char[] word, int wi, int n) {
        if (grid[r][c] == word[wi]) {
            wi++;
            if (wi > n)
                return true;
        } else wi = 0;
        if (r < R && hasWordHelper(grid, R, C, r + 1, c, word, wi, n))
            return true;
        else if (c < C) return hasWordHelper(grid, R, C, r, c + 1, word, wi, n);
        return false;
    }

    public static boolean hasWord (char grid[][], char word[]) {
        char[] p = new char[word.length];
        return hasWordHelper(grid, grid.length - 1, grid[0].length - 1, 0, 0, word, 0, word.length - 1);
    }

    private static int minCostofTollTravelHelper (int[][] a, int R, int C, int r, int c, int cost) {
        if(c == C)
            return cost + a[r][c];
        int minPathCost = Integer.MAX_VALUE;
        int pathCost;
        for (int i = -1; i <= 1; i++) {
            int ri = r + i;
            if(ri >= 0 && ri <= R) {
                pathCost = minCostofTollTravelHelper(a, R, C, r + i, c + 1, cost + a[r][c]);
                minPathCost = Math.min(pathCost, minPathCost);
            }
        }
        return minPathCost;
    }

    public static int minCostOfTollTravel (int cost[][], int startLane){
        return minCostofTollTravelHelper(cost, cost.length-1, cost[0].length-1, startLane - 1, 0, 0);
    }

    /**
     * ToDo
     *
     * @param a
     * @param s
     * @return
     */
    public static boolean is4Sum(int[] a, int s) {
        class Pair {
            int x;
            int y;
            Pair (int x, int y) {
                this.x = x;
                this.y = y;
            }
        }
        Map<Integer, List<Pair>> sums = new HashMap<>();
        int l = a.length;
        for (int i=0;i<l;i++) {
            for (int j=i+1;j<l;j++) {
                int sum = a[i]+a[j];
                if (sums.containsKey(s-sum)) {
                    return true;
                } else {
                    if (!sums.containsKey(sum)) {
                        sums.put(sum, new ArrayList<>(Arrays.asList(new Pair(i, j))));
                    } else {
                        sums.get(sum).add(new Pair(i, j));
                    }
                }
            }
        }
        return false;
    }

    public static int maxSubArray(final List<Integer> A) {
        int sum = A.get(0);
        int largest = A.get(0);
        for (int i = 1; i < A.size(); i++) {
            sum = Math.max(A.get(i), A.get(i) + sum);
            largest = Math.max(sum, largest);
        }
        return largest;
    }

//    public static void main(String[] args) {
//        int k = 7;
//        int arr[] = { 0, 5, 0, 0, 1, 0 };
//        List<Integer> l =
//                new LinkedList<>(
//                        Arrays.asList(-120, -202, -293, -60, -261, -67, 10, 82, -334, -393, -428, -182, -138, -167, -465, -347, -39, -51, -61, -491, -216, -36, -281, -361, -271, -368, -122, -114, -53, -488, -327, -182, -221, -381, -431, -161, -59, -494, -406, -298, -268, -425, -88, -320, -371, -5, 36, 89, -194, -140, -278, -65, -38, -144, -407, -235, -426, -219, 62, -299, 1, -454, -247, -146, 24, 2, -59, -389, -77, -19, -311, 18, -442, -186, -334, 41, -84, 21, -100, 65, -491, 94, -346, -412, -371, 89, -56, -365, -249, -454, -226, -473, 91, -412, -30, -248, -36, -95, -395, -74, -432, 47, -259, -474, -409, -429, -215, -102, -63, 80, 65, 63, -452, -462, -449, 87, -319, -156, -82, 30, -102, 68, -472, -463, -212, -267, -302, -471, -245, -165, 43, -288, -379, -243, 35, -288, 62, 23, -444, -91, -24, -110, -28, -305, -81, -169, -348, -184, 79, -262, 13, -459, -345, 70, -24, -343, -308, -123, -310, -239, 83, -127, -482, -179, -11, -60, 35, -107, -389, -427, -210, -238, -184, 90, -211, -250, -147, -272, 43, -99, 87, -267, -270, -432, -272, -26, -327, -409, -353, -475, -210, -14, -145, -164, -300, -327, -138, -408, -421, -26, -375, -263, 7, -201, -22, -402, -241, 67, -334, -452, -367, -284, -95, -122, -444, -456, -152, 25, 21, 61, -320, -87, 98, 16, -124, -299, -415, -273, -200, -146, -437, -457, 75, 84, -233, -54, -292, -319, -99, -28, -97, -435, -479, -255, -234, -447, -157, 82, -450, 86, -478, -58, 9, -500, -87, 29, -286, -378, -466, 88, -366, -425, -38, -134, -184, 32, -13, -263, -371, -246, 33, -41, -192, -14, -311, -478, -374, -186, -353, -334, -265, -169, -418, 63, 77, 77, -197, -211, -276, -190, -68, -184, -185, -235, -31, -465, -297, -277, -456, -181, -219, -329, 40, -341, -476, 28, -313, -78, -165, -310, -496, -450, -318, -483, -22, -84, 83, -185, -140, -62, -114, -141, -189, -395, -63, -359, 26, -318, 86, -449, -419, -2, 81, -326, -339, -56, -123, 10, -463, 41, -458, -409, -314, -125, -495, -256, -388, 75, 40, -37, -449, -485, -487, -376, -262, 57, -321, -364, -246, -330, -36, -473, -482, -94, -63, -414, -159, -200, -13, -405, -268, -455, -293, -298, -416, -222, -207, -473, -377, -167, 56, -488, -447, -206, -215, -176, 76, -304, -163, -28, -210, -18, -484, 45, 10, 79, -441, -197, -16, -145, -422, -124, 79, -464, -60, -214, -457, -400, -36, 47, 8, -151, -489, -327, 85, -297, -395, -258, -31, -56, -500, -61, -18, -474, -426, -162, -79, 25, -361, -88, -241, -225, -367, -440, -200, 38, -248, -429, -284, -23, 19, -220, -105, -81, -269, -488, -204, -28, -138, 39, -389, 40, -263, -297, -400, -158, -310, -270, -107, -336, -164, 36, 11, -192, -359, -136, -230, -410, -66, 67, -396, -146, -158, -264, -13, -15, -425, 58, -25, -241, 85, -82, -49, -150, -37, -493, -284, -107, 93, -183, -60, -261, -310, -380 ));
//        System.out.println(maxSubArray(l));
//    }

    public static String largestNumber(final List<Integer> A) {
        Comparator<Integer> c = (a, b) -> {
            String ab = a + "" + b;
            String ba = b + "" + a;
            if (ab.compareTo(ba) > 0) return -1;
            else return 1;
        };
        Collections.sort(A, c);
        if (A.get(0) == 0) return "0";
        return A.stream().reduce(new StringBuilder(), (s, e) -> s.append(e), StringBuilder::append).toString();
    }

    private static int getRepeated (final List<Integer> a, Set<Integer> s, int l, int r) {
        for (int i=l; i<=r; i++) {
            if (s.contains(a.get(i))) {
                return a.get(i);
            }
            s.add(a.get(i));
        }
        return -1;
    }


    public static int repeatedNumber (final List<Integer> a) {
        int len = a.size();

        if(a.size() == 0) {
            return -1;
        }

        if(a.size() == 1) {
            return a.get(0);
        }

        int c1 = a.get(0);
        int c2 = a.get(1);
        int c1count = 0;
        int c2count = 0;

        for(int num : a) {
            if(c1 == num) {
                c1count++;
            } else if(c2 == num) {
                c2count++;
            } else if(c1count == 0) {
                c1 = num;
                c1count = 1;
            } else if(c2count == 0) {
                c2 = num;
                c2count = 1;
            } else {
                c1count--;
                c2count--;
            }
        }

        c1count = 0;
        c2count = 0;
        for(int num : a) {
            if(c1 == num) {
                c1count++;
            } else if(num == c2) {
                c2count++;
            }
        }

        if(c1count > len / 3) {
            return c1;
        } else if(c2count > len / 3) {
            return c2;
        } else {
            return -1;
        }
    }

    public static int segregateAllNegativeToLeftAnGetCountOfNegative (int arr[]) {
        int j = 0, i, temp;
        for(i = 0; i < arr.length; i++) {
            if (arr[i] <= 0) {
                temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
                j++;
            }
        }
        return j;
    }

    public static int pushNegativeToRight (ArrayList<Integer> A) {
        int j = A.size() - 1, t;
        for (int i = 0; i < A.size() && i <= j; i++) {
            if (A.get(i) < 0) {
                t = A.get(j);
                A.set(j, A.get(i));
                A.set(i, t);
                j--;
            }
        }
        return j + 1;
    }


    public static int firstMissingPositive(ArrayList<Integer> A) {
        int val, nextVal;
        for (int i = 0; i < A.size(); i++) {
            if (A.get(i) <= 0 || A.get(i) > A.size()) continue;
            val = A.get(i);
            while (A.get(val - 1) != val) {
                nextVal = A.get(val - 1);
                A.set(val - 1, val);
                val = nextVal;
                if (val <= 0 || val > A.size()) break;
            }
        }
        for (int i = 0; i < A.size(); i++) if (A.get(i) != i+1) return i + 1;
        return A.size() + 1;
    }

//    public static void main(String[] args) {
//        ArrayList<Integer> l = new ArrayList<>(
//                Arrays.asList(417, 929, 845, 462, 675, 175, 73, 867, 14, 201, 777, 407, 80, 882, 785, 563, 209, 261, 776, 362, 730, 74, 649, 465, 353, 801, 503, 154, 998, 286, 520, 692, 68, 805, 835, 210, 819, 341, 564, 215, 984, 643, 381, 793, 726, 213, 866, 706, 97, 538, 308, 797, 883, 59, 328, 743, 694, 607, 729, 821, 32, 672, 130, 13, 76, 724, 384, 444, 884, 192, 917, 75, 551, 96, 418, 840, 235, 433, 290, 954, 549, 950, 21, 711, 781, 132, 296, 44, 439, 164, 401, 505, 923, 136, 317, 548, 787, 224, 23, 185, 6, 350, 822, 457, 489, 133, 31, 830, 386, 671, 999, 255, 222, 944, 952, 637, 523, 494, 916, 95, 734, 908, 90, 541, 470, 941, 876, 264, 880, 761, 535, 738, 128, 772, 39, 553, 656, 603, 868, 292, 117, 966, 259, 619, 836, 818, 493, 592, 380, 500, 599, 839, 268, 67, 591, 126, 773, 635, 800, 842, 536, 668, 896, 260, 664, 506, 280, 435, 618, 398, 533, 647, 373, 713, 745, 478, 129, 844, 640, 886, 972, 62, 636, 79, 600, 263, 52, 719, 665, 376, 351, 623, 276, 66, 316, 813, 663, 831, 160, 237, 567, 928, 543, 508, 638, 487, 234, 997, 307, 480, 620, 890, 216, 147, 271, 989, 872, 994, 488, 291, 331, 8, 769, 481, 924, 166, 89, 824, -4, 590, 416, 17, 814, 728, 18, 673, 662, 410, 727, 667, 631, 660, 625, 683, 33, 436, 930, 91, 141, 948, 138, 113, 253, 56, 432, 744, 302, 211, 262, 968, 945, 396, 240, 594, 684, 958, 343, 879, 155, 395, 288, 550, 482, 557, 826, 598, 795, 914, 892, 690, 964, 981, 150, 179, 515, 205, 265, 823, 799, 190, 236, 24, 498, 229, 420, 753, 936, 191, 366, 935, 434, 311, 920, 167, 817, 220, 219, 741, -2, 674, 330, 909, 162, 443, 412, 974, 294, 864, 971, 760, 225, 681, 689, 608, 931, 427, 687, 466, 894, 303, 390, 242, 339, 252, 20, 218, 499, 232, 184, 490, 4, 957, 597, 477, 354, 677, 691, 25, 580, 897, 542, 186, 359, 346, 409, 655, 979, 853, 411, 344, 358, 559, 765, 383, 484, 181, 82, 514, 582, 593, 77, 228, 921, 348, 453, 274, 449, 106, 657, 783, 782, 811, 333, 305, 784, 581, 746, 858, 249, 479, 652, 270, 429, 614, 903, 102, 378, 575, 119, 196, 12, 990, 356, 277, 169, 70, 518, 282, 676, 137, 622, 616, 357, 913, 161, 3, 589, 327));
//        System.out.println(firstMissingPositive(l));
//    }


    public static void quickSort (int[] arr, int l, int r, boolean asc) {
        int idx = asc ?   partitionAsc(arr, l, r) : partitionDesc(arr, l, r);
        if (l < idx - 1)  quickSort (arr, l, idx-1, asc);
        if (idx < r)      quickSort (arr, idx, r, asc);
    }

    private static int partitionAsc (int[] arr, int l, int r) {
        int p = (l+r)/2;
        while(l <= r) {
            while (arr[l] < arr[p]) l++;
            while (arr[p] < arr[r]) r--;
            if (l <= r) {
                swap(arr, l, r);
                l++;
                r--;
            }
        }
        return l;
    }

    private static int partitionDesc (int[] arr, int l, int r) {
        int p = (l+r)/2;
        while(l <= r) {
            while (arr[l] > arr[p]) l++;
            while (arr[p] > arr[r]) r--;
            if (l <= r) {
                swap(arr, l, r);
                l++;
                r--;
            }
        }
        return l;
    }

    private static void swap (int[] arr, int l, int r) {
        int t  = arr[l];
        arr[l] = arr[r];
        arr[r] = t;
    }

    private static void quickSort(int[] arr) {
        quickSort(arr, 0, arr.length - 1, true);
    }

    public static int findKthLargest (int[] arr, int l, int r, int k) {
        int idx = partitionDesc(arr, l, r);
        if (idx == k - 1) return arr[idx];
        if (l < idx - 1)  return findKthLargest (arr, l, idx - 1, k);
        if (idx < r)      return findKthLargest (arr, idx, r, k);
        return Integer.MAX_VALUE;
    }

    public static int findKthSmallest (int[] arr, int l, int r, int k) {
        int idx = partitionAsc(arr, l, r);
        if (idx == k - 1) return arr[idx];
        if (l < idx - 1)  return findKthSmallest (arr, l, idx - 1, k);
        if (idx < r)      return findKthSmallest (arr, idx, r, k);
        return Integer.MAX_VALUE;
    }


//    public static void main(String[] args) {
//        int[] arr = {417, 929, 845, 462, 675, 175, 73, 867, 14, 201, 777, 407, 80, 882, 785, 563, 209, 261, 776, 362, 730, 74, 649, 465, 353, 801, 503, 154, 998, 286, 520, 692, 68, 805, 835, 210, 819, 341, 564, 215, 984, 643, 381, 793, 726, 213, 866, 706, 97, 538, 308, 797, 883, 59, 328, 743, 694, 607, 729, 821, 32, 672, 130, 13, 76, 724, 384, 444, 884, 192, 917, 75, 551, 96, 418, 840, 235, 433, 290, 954, 549, 950, 21, 711, 781, 132, 296, 44, 439, 164, 401, 505, 923, 136, 317, 548, 787, 224, 23, 185, 6, 350, 822, 457, 489, 133, 31, 830, 386, 671, 999, 255, 222, 944, 952, 637, 523, 494, 916, 95, 734, 908, 90, 541, 470, 941, 876, 264, 880, 761, 535, 738, 128, 772, 39, 553, 656, 603, 868, 292, 117, 966, 259, 619, 836, 818, 493, 592, 380, 500, 599, 839, 268, 67, 591, 126, 773, 635, 800, 842, 536, 668, 896, 260, 664, 506, 280, 435, 618, 398, 533, 647, 373, 713, 745, 478, 129, 844, 640, 886, 972, 62, 636, 79, 600, 263, 52, 719, 665, 376, 351, 623, 276, 66, 316, 813, 663, 831, 160, 237, 567, 928, 543, 508, 638, 487, 234, 997, 307, 480, 620, 890, 216, 147, 271, 989, 872, 994, 488, 291, 331, 8, 769, 481, 924, 166, 89, 824, -4, 590, 416, 17, 814, 728, 18, 673, 662, 410, 727, 667, 631, 660, 625, 683, 33, 436, 930, 91, 141, 948, 138, 113, 253, 56, 432, 744, 302, 211, 262, 968, 945, 396, 240, 594, 684, 958, 343, 879, 155, 395, 288, 550, 482, 557, 826, 598, 795, 914, 892, 690, 964, 981, 150, 179, 515, 205, 265, 823, 799, 190, 236, 24, 498, 229, 420, 753, 936, 191, 366, 935, 434, 311, 920, 167, 817, 220, 219, 741, -2, 674, 330, 909, 162, 443, 412, 974, 294, 864, 971, 760, 225, 681, 689, 608, 931, 427, 687, 466, 894, 303, 390, 242, 339, 252, 20, 218, 499, 232, 184, 490, 4, 957, 597, 477, 354, 677, 691, 25, 580, 897, 542, 186, 359, 346, 409, 655, 979, 853, 411, 344, 358, 559, 765, 383, 484, 181, 82, 514, 582, 593, 77, 228, 921, 348, 453, 274, 449, 106, 657, 783, 782, 811, 333, 305, 784, 581, 746, 858, 249, 479, 652, 270, 429, 614, 903, 102, 378, 575, 119, 196, 12, 990, 356, 277, 169, 70, 518, 282, 676, 137, 622, 616, 357, 913, 161, 3, 589, 327};
//        quickSort(arr);
//        Arrays.stream(arr).forEach(e -> System.out.print(e + " | "));
//    }

//    public static void main(String[] args) {
//
//        int arr[] = new int[]{12, 3, 5, 7, 4, 19, 26};
//        int k = 3;
//        System.out.println(findKthSmallest(arr, 0, arr.length - 1, k));
//    }

    private static void merge(int[] arr, int[] arri, int l, int m, int r) {
        for (int i = l; i <= r; i++) arri[i] = arr[i];
        int li = l;
        int ri = m + 1;
        int cur = l;

        while (li <= m && ri <= r) {
            if (arri[li] <= arri[ri]) arr[cur] = arri[li++];
            else                      arr[cur] = arri[ri++];
            cur++;
        }
        int rem = m - li;
        for (int i = 0; i <= rem; i++) arr[cur + i] = arri[li + i];
    }

    private static void mergeSort(int[] arr, int[] arri, int l, int r) {
        if (l < r) {
            int m = (l + r) / 2;
            mergeSort(arr, arri, l, m);
            mergeSort(arr, arri, m + 1, r);
            merge(arr, arri, l, m, r);
        }
    }

    public static void mergeSort(int[] arr) {
        int[] arri = new int[arr.length];
        mergeSort(arr, arri, 0, arr.length - 1);
    }

//    public static void main(String[] args) {
//        int[] arr = {417, 929, 845, 462, 675, 175, 73, 867, 14, 201, 777, 407, 80, 882, 785, 563, 209, 261, 776, 362, 730, 74, 649,
//                465, 353, 801, 503, 154, 998, 286, 520, 692, 68, 805, 835, 210, 819, 341, 564, 215, 984, 643, 381, 793, 726, 213,
//                866, 706, 97, 538, 308, 797, 883, 59, 328, 743, 694, 607, 729, 821, 32, 672, 130, 13, 76, 724, 384, 444, 884, 192,
//                917, 75, 551, 96, 418, 840, 235, 433, 290, 954, 549, 950, 21, 711, 781, 132, 296, 44, 439, 164, 401, 505, 923, 136,
//                317, 548, 787, 224, 23, 185, 6, 350, 822, 457, 489, 133, 31, 830, 386, 671, 999, 255, 222, 944, 952, 637, 523, 494,
//                916, 95, 734, 908, 90, 541, 470, 941, 876, 264, 880, 761, 535, 738, 128, 772, 39, 553, 656, 603, 868, 292, 117, 966,
//                259, 619, 836, 818, 493, 592, 380, 500, 599, 839, 268, 67, 591, 126, 773, 635, 800, 842, 536, 668, 896, 260, 664,
//                506, 280, 435, 618, 398, 533, 647, 373, 713, 745, 478, 129, 844, 640, 886, 972, 62, 636, 79, 600, 263, 52, 719, 665,
//                376, 351, 623, 276, 66, 316, 813, 663, 831, 160, 237, 567, 928, 543, 508, 638, 487, 234, 997, 307, 480, 620, 890, 216,
//                147, 271, 989, 872, 994, 488, 291, 331, 8, 769, 481, 924, 166, 89, 824, -4, 590, 416, 17, 814, 728, 18, 673, 662, 410,
//                727, 667, 631, 660, 625, 683, 33, 436, 930, 91, 141, 948, 138, 113, 253, 56, 432, 744, 302, 211, 262, 968, 945, 396,
//                240, 594, 684, 958, 343, 879, 155, 395, 288, 550, 482, 557, 826, 598, 795, 914, 892, 690, 964, 981, 150, 179, 515,
//                205, 265, 823, 799, 190, 236, 24, 498, 229, 420, 753, 936, 191, 366, 935, 434, 311, 920, 167, 817, 220, 219, 741,
//                -2, 674, 330, 909, 162, 443, 412, 974, 294, 864, 971, 760, 225, 681, 689, 608, 931, 427, 687, 466, 894, 303, 390,
//                242, 339, 252, 20, 218, 499, 232, 184, 490, 4, 957, 597, 477, 354, 677, 691, 25, 580, 897, 542, 186, 359, 346, 409,
//                655, 979, 853, 411, 344, 358, 559, 765, 383, 484, 181, 82, 514, 582, 593, 77, 228, 921, 348, 453, 274, 449, 106, 657,
//                783, 782, 811, 333, 305, 784, 581, 746, 858, 249, 479, 652, 270, 429, 614, 903, 102, 378, 575, 119, 196, 12, 990, 356,
//                277, 169, 70, 518, 282, 676, 137, 622, 616, 357, 913, 161, 3, 589, 327};
//        mergeSort(arr);
//        Arrays.stream(arr).forEach(e -> System.out.print(e + " | "));
//
//    }

    public static ArrayList<ArrayList<Integer>> generateNSquareMatrixSpitrally(int N) {
        ArrayList<ArrayList<Integer>> l = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            ArrayList<Integer> ll = new ArrayList<>();
            for (int j=0; j<N; j++) ll.add(0);
            l.add(ll);
        }
        int R = N - 1;
        int C = N - 1;
        int r = 0;
        int c = 0;
        int k = 1;
        while (r <= R && c <= C) {
            for (int i=c; i <= C; i++ )      l.get(r).set(i, k++);
            r++;
            for (int i=r; i<=R; i++)         l.get(i).set(C, k++);
            C--;
            if (r <= R) {
                for (int i = C; i >= c; i--) l.get(R).set(i, k++);
                R--;
            }
            if (c <= C) {
                for (int i = R; i >= r; i--) l.get(i).set(c, k++);
                c++;
            }
        }
        return l;
    }

    public static ArrayList<Integer> repeatedNumber2 (final List<Integer> A) {
        int val, nextVal;
        ArrayList<Integer> result = new ArrayList<>();
        for (int i=0; i<A.size();i++) {
            val = A.get(i);
            while(A.get(val - 1) != val) {
                nextVal = A.get(val - 1);
                A.set(val - 1, val);
                val = nextVal;
                if (A.get(val - 1) == val) {
                    result.add(val);
                    break;
                }
            }
        }
        for (int i=0;i<A.size(); i++) {
            if (A.get(i) != i + 1) {
                result.add(i+1);
            }
        }
        return result;
    }

    public static int pivotedSearch(final List<Integer> a, int b) {
        if (a.get(0) < a.get(a.size() - 1)) {
            return binarySearch(a, 0, a.size() - 1, b);
        }
        int pivot = pivot(a, 0, a.size() - 1);
        if (b == a.get(pivot)) {
            return pivot;
        } else if (b >= a.get(0) && b < a.get(pivot)) {
            return binarySearch(a, 0, pivot - 1, b);
        } else return binarySearch(a, pivot + 1, a.size() - 1, b);
    }

    private static int pivot (List<Integer> a, int l, int r) {
        if (l > r) return -1;
        int m = (l+r)/2;
        if (a.get(m) > a.get(m+1)) return m;
        int aa =             pivot (a, l, m-1);
        if (aa == -1) return pivot (a, m+1, r);
        return aa;
    }

    private static int binarySearch (List<Integer> a, int l, int r, int b) {
        if (l > r) return -1;
        int m = (l+r)/2;
        if (a.get(m) == b) return m;
        if (a.get(m) > b) return binarySearch(a, l, m - 1, b);
        return binarySearch(a, m+1, r, b);
    }

    public static int search (final List<Integer> a, int b) {
        if (null == a|| 0 == a.size()) return -1;
        if (a.get(0) == b) return 0;
        return search(a, 1, a.size() - 1, b);
    }

    private static int search (List<Integer> a, int l, int r, int b) {
        if (r < l) return -1;
        int m = (l+r)/2;
        if (a.get(m) == b) return m;
        if (a.get(l) <= a.get(m)) {
            if (a.get(l) <= b && b < a.get(m)) return search(a, l, m-1, b);
            return search(a, m+1, r, b);
        } else {
            if (b >= a.get(m) && b <= a.get(r)) return search (a, m + 1, r, b);
            return search(a, l, m-1, b);
        }
    }

    public static int numRange (ArrayList<Integer> A, int B, int C) {
        int i = 0;
        int j = 0;
        int sum = 0;
        int count = 0;

        while(i < A.size()) {
            sum = sum + A.get(j);
            if((sum >= B) && (sum <= C)) {
                count++;
                j++;
            }
            else if(sum < B) { j++; }
            else if(sum > C) {
                i++;
                j = i;
                sum = 0;
            }
            if(j == A.size()) {
                sum = 0;
                i++;
                j = i;
            }
        }

        return count;
    }

//    public static void main(String[] args) {
//        ArrayList<Integer> l = new ArrayList<>(Arrays.asList(10, 5, 1, 0, 2));
//        System.out.println(numRange(l, 6, 8));
//    }

    public static ArrayList<ArrayList<Integer>> getArrayListFrom2D(int[][] a) {
        ArrayList<ArrayList<Integer>> A = new ArrayList<>();
        for (int i = 0; i < a.length; i++) {
            ArrayList<Integer> B = new ArrayList<>();
            for (int j = 0; j< a[i].length; j++) B.add(a[i][j]);
            A.add(B);
        }
        return A;
    }

    public static int[] getIntArray (String[] line) {
        int[] arr = new int[line.length];
        IntStream.range(0, arr.length).forEach(i -> arr[i] = Integer.valueOf(line[i]));
        return arr;
    }

    public static int[] getIntArray (String line) {
        return getIntArray(line.split(" "));
    }

    public ArrayList<Integer> nextGreater(ArrayList<Integer> A) {
        Stack<Integer> s = new Stack<>();
        for (int i = 0; i < A.size(); i++) {
            while (!s.isEmpty() && A.get(s.peek()) < A.get(i)) A.set(s.pop(), A.get(i));
            s.push(i);
        }
        while (!s.isEmpty()) A.set(s.pop(), -1);
        return A;
    }

    private static ArrayList<ArrayList<Integer>> AA;
    public static ArrayList<ArrayList<Integer>> permute (ArrayList<Integer> A) {
        AA = new ArrayList<>();
        permute(A, 0);
        return AA;
    }

    private static void permute (ArrayList<Integer> A, int idx) {
        if (idx == A.size() - 1) AA.add(deepCopy(A));
        for (int i = idx; i < A.size(); i++) {
            if (shouldSwap(A, i, idx)) {
                swap(A, i, idx);
                permute(A, idx + 1);
                swap(A, i, idx);
            }
        }
    }

    private static boolean shouldSwap (ArrayList<Integer> A, int idx, int c) {
        for (int i = idx + 1; i < A.size(); i++) if (A.get(i) == A.get(c)) return false;
        return true;

    }

    private static void swap (ArrayList<Integer> A, int i, int j) {
        int temp = A.get(i);
        A.set(i, A.get(j));
        A.set(j, temp);
    }

    private static ArrayList<Integer> deepCopy (ArrayList<Integer> a) {
       return a.stream().collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
    }

//    public static void main(String[] args) {
//        System.out.println(permute(getNewArrayList(1, 2, 3)));
//    }

    private static ArrayList<Integer> getNewArrayList (int... n) {
        return Arrays.stream(n).collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
    }

    public static int longestConsecutive(final List<Integer> A) {
        Map<Integer, Integer> m =
                A.stream().collect(Collectors.toMap(e->e, e->-1, (u,v)->u));
        int count;
        int largestCount = 0;
        for (int i = 0 ; i < A.size(); i++) {
            count = getCount(m, A.get(i));
            if (count > largestCount) largestCount = count;
        }
        return largestCount;
    }

    private static int getCount (Map<Integer, Integer> m, int a) {
        if (!m.containsKey(a)) return 0;
        if (m.get(a) != -1) return m.get(a);
        m.put(a, 1 + getCount(m, a+1));
        return m.get(a);
    }

//    public static void main(String[] args) {
//        System.out.println(longestConsecutive(getNewArrayList(100, 4, 200, 1, 3, 2)));
//    }

    public static int[] mergeSortedArray (int[] A, int[] B) {
        int n = A.length;
        int m = B.length - n;
        int k = B.length - 1;
        while (n > 0 && m > 0) {
            if (A[n-1] > B[m-1]) {
                B[k--] = A[n-1];
                n--;
            } else {
                B[k--] = B[m-1];
                m--;
            }
        }
        while (n > 0) {
            B[k--] = A[n-1];
            n--;
        }
        return B;
    }

    public static void main(String[] args) {
        int[] A = {1, 2, 3, 6, 7};
        int[] B = {1, 2, 2, 4, 5, 8, -1, -1, -1, -1, -1};
        Arrays.stream(mergeSortedArray(A, B)).forEach(System.out::println);
    }

    public static Map<Integer, List<Long>> getSubarraySums (int[] arr) {
        Map<Integer, List<Long>> m = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            m.put(i, new ArrayList<>());
            for (int j = i; j < arr.length; j++) {
                if (i == j) m.get(i).add((long) arr[j]);
                else m.get(i).add(m.get(i).get(j - i - 1) + arr[j]);
            }
        }
        return m;
    }

    public int[] getIntegerArrayFrom (String[] line) {
        int[] arr = new int[line.length];
        for (int i = 0; i < line.length; i++) arr[i] = Integer.valueOf(line[i]);
        return arr;
    }

}
