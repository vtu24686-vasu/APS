import java.util.*;

class Solution {
    public List<String> findAndReplacePattern(String[] words, String pattern) {
        List<String> result = new ArrayList<>();

        for (String word : words) {
            if (matches(word, pattern)) {
                result.add(word);
            }
        }

        return result;
    }

    private boolean matches(String word, String pattern) {
        if (word.length() != pattern.length()) return false;

        HashMap<Character, Character> map1 = new HashMap<>();
        HashMap<Character, Character> map2 = new HashMap<>();

        for (int i = 0; i < word.length(); i++) {
            char w = word.charAt(i);
            char p = pattern.charAt(i);

            if (map1.containsKey(w)) {
                if (map1.get(w) != p) return false;
            } else {
                map1.put(w, p);
            }

            if (map2.containsKey(p)) {
                if (map2.get(p) != w) return false;
            } else {
                map2.put(p, w);
            }
        }

        return true;
    }
}