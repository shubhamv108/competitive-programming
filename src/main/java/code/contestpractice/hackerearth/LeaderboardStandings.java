package code.contestpractice.hackerearth;

import java.io.*;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

public class LeaderboardStandings {
    public static void main(String[] args) throws IOException {
        java.io.BufferedReader br = new java.io.BufferedReader(new java.io.InputStreamReader(System.in));
        Integer N = Integer.parseInt(br.readLine());

        class Contestant {
        	String name;
            int totalSubmission;
            int totalTime;
            Contestant(String name, int t) {
            	this.name = name;
                this.totalTime = t;
                this.totalSubmission = 1;    
            }
            void increementSubmission() {
                this.totalSubmission++;
            }
            Contestant updateTotalTime(Integer t) {
                this.totalTime += t;
                return this;
            }
			
        }
    	
        Map<String, Contestant> m = new HashMap<>();
        String[] line;
        while(N-- > 0) {
            line = br.readLine().split(" ");
            String c = line[0];
            Integer t = Integer.parseInt(line[1]);
            if(!m.containsKey(c)) {
                   m.put(c,new Contestant(c, t));
            } else {
                m.get(c).updateTotalTime(t).increementSubmission();
            }                
        }
        
        Comparator<Contestant> c = (Contestant c1, Contestant c2) -> {
        	if(c1.totalSubmission > c2.totalSubmission) {
        		return -1;
        	} else {
        		if(c1.totalSubmission == c2.totalSubmission) {
        			if(c1.totalTime < c2.totalTime) {
        				return -1;
        			} else {
        				if(c1.totalTime == c2.totalTime) {
        					if(c1.name.compareTo(c2.name) < 0) {
        						return -1;
        					}
        				}
        			}
        		}
        	}
			return 1;
        };

        TreeSet<Contestant> rankingTree = new TreeSet<>(c);
        m.values().stream().forEach(e -> rankingTree.add(e));
        int rank = 1;
        for(Contestant con : rankingTree) {
        	System.out.println((rank++) + " " + con.name);
        }
    }
}