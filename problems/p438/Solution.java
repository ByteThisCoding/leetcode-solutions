package problems.p438;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solution {

    /**
     * Find anagrams of "p" in "s"
     */
    public List<Integer> findAnagrams(String s, String p) {
        // keep a list of found indices
        List<Integer> foundIndices = new ArrayList<>();

        // base case, s.len < p.len
        if (s.length() < p.length()) {
            return foundIndices;
        }

        // use a map to check the remaining indices
        int remainingCharsCount = p.length();
        Map<Character, Integer> remainingChars = new HashMap<>();
        CircularBuffer<Character> foundChars = new CircularBuffer<>(p.length());
        // add the initial characters remaining
        for (int i = 0; i < p.length(); i++) {
            char iChar = p.charAt(i);
            remainingChars.put(iChar, remainingChars.getOrDefault(iChar, 0) + 1);
        }

        // iterate over "s" to find chars
        for (int i = 0; i < s.length(); i++) {
            char sChar = s.charAt(i);
            foundChars.push(sChar);

            // if this char is in "p", add to map
            if (remainingChars.containsKey(sChar)) {
                int existingRemainingCount = remainingChars.get(sChar);
                remainingChars.put(sChar, existingRemainingCount - 1);

                // only decrement if we haven't found "too many" of this letter
                if (existingRemainingCount > 0) {
                    remainingCharsCount--;

                    // if zero, check if we've found an anagram, and add if so
                    if (remainingCharsCount == 0) {
                        foundIndices.add(i - p.length() + 1);
                    }
                }
            } else if (i + p.length() >= s.length()) {
                // if there aren't enough chars left, exit now
                break;
            }

            // prep for next by dequeing and updating values
            if (foundChars.isFull()) {
                char dequeued = foundChars.remove();

                // dequeue and prepare for next loop
                if (remainingChars.containsKey(dequeued)) {
                    int newCount = remainingChars.get(dequeued) + 1;
                    remainingChars.put(dequeued, newCount);
                    if (newCount > 0) {
                        remainingCharsCount++;
                    }
                }
            }
        }

        return foundIndices;
    }
}
