package code.shubham.contestpractice.hackerearth;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ChallengingTrack {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        String[] line;
        int n, p, h;
        while(t-- > 0) {
            line = br.readLine().split(" ");
            n = Integer.parseInt(line[0]);
            p = Integer.parseInt(line[1]);
            line = br.readLine().split(" ");
            int oddCounter  = 0;
            int evenCounter = 0;
            int i = 0;
            for (i=0;i<n;i++) {
                h = Integer.parseInt(line[i]);
                if ((h & 1) == 0) h = h - evenCounter;
                if ((h & 1) == 1) h = h - oddCounter;
                if(h > 0) {
                    int pp = p - h;
                    if(pp >= 0) p = pp; else break;
                }
                if( (i & 1) == 0) evenCounter++;
                if( (i & 1) == 1) oddCounter++;
            }
            if (i == n) System.out.println("Yes " + p);
            else System.out.println("No");
        }
    }
}
