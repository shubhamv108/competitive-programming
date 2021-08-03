package code.counting;

import java.util.ArrayList;
import java.util.List;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class RankTeamsByVote {

    class Solution {
        public String rankTeams(String[] votes) {

            Map<Character, int[]> map = new HashMap<>();
            int l = votes[0].length();
            for(String vote : votes){
                for(int i = 0; i < l; i++){
                    char c = vote.charAt(i);
                    map.putIfAbsent(c, new int[l]);
                    map.get(c)[i]++;
                }
            }

            List<Character> list = new ArrayList<>(map.keySet());
            Collections.sort(list, (a,b) -> {
                for(int i = 0; i < l; i++){
                    if(map.get(a)[i] != map.get(b)[i]){
                        return map.get(b)[i] - map.get(a)[i];
                    }
                }
                return a - b;
            });

            StringBuilder sb = new StringBuilder();
            for(char c : list){
                sb.append(c);
            }
            return sb.toString();
        }
    }

    public static void main(String[] args) {
        System.out.println(
                new RankTeamsByVote().new Solution().rankTeams(new String[] { "M","M","M","M" })
        );
    }

}
