import java.util.Map;
import java.util.HashMap;

class Solution {
    public int minSteps(String s, String t) {
        int numSteps = 0;
        
        // iterate over both strings and determine which strings need chars
        Map<Character, Integer> diffMap = new HashMap<>();
        for (int i=0; i<s.length(); i++) {
            diffMap.put(
                s.charAt(i),
                diffMap.getOrDefault(s.charAt(i), 0) + 1
            );
        }

        for (int i=0; i<t.length(); i++) {
            diffMap.put(
                t.charAt(i),
                diffMap.getOrDefault(t.charAt(i), 0) - 1
            );
        }

        // count the number of chars to add in
        for (Map.Entry<Character, Integer> entry : diffMap.entrySet()) {
            int count = entry.getValue();
            numSteps += Math.abs(count);
        }

        return numSteps;
    }
}