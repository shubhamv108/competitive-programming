package code.shubham.networks;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public class ZoneForIP {

    public class IPAddressZone {
        public static boolean isValidIP(String ip) {
            String[] ipParts = ip.split("\\.");
            if (ipParts.length != 4)
                return false;

            for (final String part : ipParts) {
                try {
                    int octet = Integer.parseInt(part);
                    if (octet < 0 || octet > 255)
                        return false;
                } catch (NumberFormatException e) {
                    return false;
                }
            }
            return true;
        }

        public static int getIPZone(String ip) {
            String[] ipParts = ip.split("\\.");
            int firstOctet = Integer.parseInt(ipParts[0]);

            if (firstOctet >= 0 && firstOctet <= 127) {
                return 1;
            } else if (firstOctet >= 128 && firstOctet <= 191) {
                return 2;
            } else if (firstOctet >= 192 && firstOctet <= 223) {
                return 3;
            } else if (firstOctet >= 224 && firstOctet <= 255) {
                return 4;
            } else {
                return -1;
            }
        }

        public static List<Integer> getZones(String[] ipAddresses) {
            List<Integer> zones = new ArrayList<>();
            for (String ip : ipAddresses) {
                if (isValidIP(ip)) {
                    zones.add(getIPZone(ip));
                } else {
                    zones.add(-1);
                }
            }
            return zones;
        }
    }

    class Solution2 {

        class Zone implements Comparable<Zone> {
            int[] start;
            int[] end;
            int zoneId;

            Zone(int zoneId, int[] start, int[] end) {
                this.start = start;
                this.end = end;
                this.zoneId = zoneId;
            }

            @Override
            public int compareTo(Zone o) {
                int r = compare(this.start, o.start);
                if (r != 0)
                    return r;
                return compare(this.end, o.end);
            }

            boolean has(int[] a) {
                return !(start[0] > a[0] || end[0] < a[0]) ||
                (start[1] > a[1] || end[1] < a[1]) ||
                (start[2] > a[2] || end[2] < a[2]) ||
                (start[3] > a[3] || end[3] < a[3]);
            }

            @Override
            public String toString() {
                return "Zone{" +
                        "start=" + Arrays.toString(start) +
                        ", end=" + Arrays.toString(end) +
                        ", zoneId=" + zoneId +
                        '}';
            }
        }

        int[] octet(String ip) {
            int[] octetParts = Arrays.stream(ip.split("\\.")).mapToInt(Integer::valueOf).toArray();
            this.isValidIP(octetParts);
            return octetParts;
        }

        int compare(int[] a, int[] b) {
            for (int i = 0; i < 4; ++i)
                if (a[i] != b[i])
                    return a[i] - b[i];
            return 0;
        }

        public boolean isValidIP(int[] octetParts) {
            if (octetParts.length != 4)
                return false;

            for (final int partOctet : octetParts) {
                try {
                    if (partOctet < 0 || partOctet > 255)
                        return false;
                } catch (NumberFormatException e) {
                    return false;
                }
            }
            return true;
        }

        int[] solve(String[][] A, String[] ips) {
            int[] result = new int[ips.length];
            Zone[] zones = IntStream.range(0, A.length)
                    .mapToObj(i -> new Zone(i, octet(A[i][0]), octet(A[i][1])))
                    .sorted()
                    .toArray(Zone[]::new);
            System.out.println(zones);
            for (String ip : ips) {
                int[] octet = octet(ip);

            }
            return result;
        }

        int search(Zone[] A, int[] t) {
            int l = 0, r = A.length - 1;
            while (l <= r) {
                int m = l + (r - l) / 2;
                if (A[m].has(t))
                    return m;
//                if (A[m].compareTo(m) )
            }
            return -1;
        }
    }

    public static void main(String[] args) {

    }

}
