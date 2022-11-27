package code.shubham.binarysearch;

public class BinarySearchnBitManipulation {
    public static void main(String[] args) {
//        int arr[] = {2, 5, 6, 8, 10, 15, 18, 20};
//        int low = 0, high = 7, x = 6, sum = 0, mid;
//        while (high >= low)
//        {
//            mid = low + (high - low)/2;
//            if (x == arr[mid])
//                break;
//            if (x > arr[mid]) {
//                low = mid + 1;
//                sum |= 1;
//            }
//            else {
//                high = mid - 1;
//                sum ^= 1;
//            }
//            sum++;
//        }
//        int arr[] = {2, 5, 6, 8, 10, 15, 18, 20};
//        int low = 0, high = 7, x = 6, sum = 0, mid;
//        while (high >= low)
//        {
//            mid = low + (high - low)/2;
//            if (x == arr[mid])
//                break;
//            if (x > arr[mid]) {
//                low = mid + 1;
//                sum <<= 1;
//            }
//            else {
//                high = mid - 1;
//                sum >>= 1;
//            }
//            sum++;
//        }
        int arr[] = {2, 5, 6, 8, 10, 15, 18, 20};
        int low = 0, high = 7, x = 6, sum = 0, mid;
        while (high >= low) {
            mid = low + (high - low) / 2;
            if (x == arr[mid])
                break;
            if (x > arr[mid]) {
                low = mid + 1;
                sum |= 1;
            } else {
                high = mid - 1;
                sum ^= 1;
            }
            sum++;
        }
        System.out.println(sum);
    }
}
