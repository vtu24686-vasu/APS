class Solution {
    public boolean halvesAreAlike(String s) {
        int n = s.length();
        int mid = n / 2;

        int count1 = 0, count2 = 0;

        for (int i = 0; i < mid; i++) {
            if (isVowel(s.charAt(i))) count1++;
        }

        for (int i = mid; i < n; i++) {
            if (isVowel(s.charAt(i))) count2++;
        }

        return count1 == count2;
    }

    private boolean isVowel(char c) {
        return "aeiouAEIOU".indexOf(c) != -1;
    }
}