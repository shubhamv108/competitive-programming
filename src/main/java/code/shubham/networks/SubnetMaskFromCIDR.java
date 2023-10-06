package code.shubham.networks;

import java.util.Arrays;

public class SubnetMaskFromCIDR {
    class Solution {

        String solve(String cidr) {
            int totalIPCount = getTotalIPCount(Integer.valueOf(cidr.split("/")[1]));
            long[] result = getSubnetMaskIpAddressTotalCount(totalIPCount);
            Arrays.stream(result).forEach(System.out::println);
            return "";
        }

        long[] getSubnetMaskIpAddressTotalCount(int rem) {
            long[] result = new long[4];
            long cur = 256;
            for (int i = 3; i > -1; i--) {
                if (rem > cur) {
                    rem -= cur;
                    result[i] = 0;
                } else {
                    result[i] = cur - rem;
                    break;
                }
                cur *= 256;
            }

            return result;
        }

        int getTotalIPCount(int netBits) {
            int hostBits = 32 - netBits, total= 0;
            float mul = 0.5f;
            while (hostBits-- > 0)
                total += (mul *= 2);
            return total;
        }
    }

    public static void main(String[] args) {
        System.out.println(
            new SubnetMaskFromCIDR().new Solution().solve("192.168.1.0/5")
        );
    }
}
