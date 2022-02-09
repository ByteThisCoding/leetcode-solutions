package problems.p49;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solution {

    /**
     * For each group, sort str chars, then add to existing or new groups
     */
    public List<List<String>> groupAnagrams(String[] strs) {
        // list of lists to return
        List<List<String>> anagrams = new ArrayList<>();

        // keep track of which positions "reduced" anagrams appear in
        Map<String, Integer> sortedAnagramMap = new HashMap<>();

        // for each item, sortChars, check against map
        for (int i = 0; i < strs.length; i++) {
            String sortedStr = sortStringChars(strs[i]);
            if (sortedAnagramMap.containsKey(sortedStr)) {
                // add to existing group
                int groupIndex = sortedAnagramMap.get(sortedStr);
                anagrams.get(groupIndex).add(strs[i]);
            } else {
                // create a new group and add it
                ArrayList<String> newGroup = new ArrayList<>();
                newGroup.add(strs[i]);

                // update map entry
                sortedAnagramMap.put(sortedStr, anagrams.size());

                // add entire new group
                anagrams.add(newGroup);
            }
        }

        return anagrams;
    }

    /**
     * Sort a string's characters and return that as a new string
     */
    private String sortStringChars(String str) {
        char[] chars = str.toCharArray();
        Arrays.sort(chars);
        return new String(chars);
    }
}
