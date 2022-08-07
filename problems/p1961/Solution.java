package problems.p1961;

public class Solution {

    public boolean isPrefixString(String s, String[] words) {
        // keep track if we've found a word that doesn't fit
        // or not enough words to fill the string
        boolean violationFound = false;

        // keep track of the current position in the string
        int sIndex = 0;
        for (String word : words) {
            // calculate the end index of the "s" substring
            int wordLen = word.length();
            int sIndexWordEnd = sIndex + wordLen;

            // if "s" isn't long enough for this word, or the substrings aren't equal
            // then it's a violation
            if (s.length() < sIndexWordEnd || !s.substring(sIndex, sIndexWordEnd).equals(word)) {
                violationFound = true;
                break;
            }

            // prep for the next loop
            sIndex += wordLen;

            // stop searching if we're at the end of the "s"
            if (sIndex >= s.length()) {
                break;
            }
        }

        // if we haven't exhausted the chars in "s", we should return false
        if (sIndex < s.length()) {
            violationFound = true;
        }

        return !violationFound;
    }

}
