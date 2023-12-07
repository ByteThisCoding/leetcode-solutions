import java.util.LinkedList;

class Solution {
    public boolean isValid(String s) {
        // use a stack to keep track of chars
        LinkedList <Character> chars = new LinkedList<>();

        for (int i=0; i<s.length(); i++) {
            char iChar = s.charAt(i);
            switch (iChar) {
                //case '{':
                case '}':
                    // ensure we're closing the correct item
                    if (i == 0 || chars.size() == 0 || chars.peek() != '{') {
                        return false;
                    }
                    chars.pop();
                    break;
                //case '[':
                case ']':
                    // ensure we're closing the correct item
                    if (i == 0 || chars.size() == 0 || chars.peek() != '[') {
                        return false;
                    }
                    chars.pop();
                    break;
                //case '(':
                case ')':
                    // ensure we're closing the correct item
                    if (i == 0 || chars.size() == 0 || chars.peek() != '(') {
                        return false;
                    }
                    chars.pop();
                    break;
                default:
                    chars.push(iChar);
                    break;
            }
        }

        return chars.size() == 0;
    }
}