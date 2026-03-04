class Solution {
    public int myAtoi(String s) {
        if (s == null || s.length() == 0) return 0;

        int i = 0;
        int n = s.length();
        
        // 1️⃣ Skip leading spaces
        while (i < n && s.charAt(i) == ' ') {
            i++;
        }

        // 2️⃣ Check sign
        int sign = 1;
        if (i < n && (s.charAt(i) == '+' || s.charAt(i) == '-')) {
            sign = (s.charAt(i) == '-') ? -1 : 1;
            i++;
        }

        // 3️⃣ Convert digits
        long result = 0;  // use long to detect overflow

        while (i < n && Character.isDigit(s.charAt(i))) {
            result = result * 10 + (s.charAt(i) - '0');

            // 4️⃣ Handle overflow
            if (sign * result > Integer.MAX_VALUE)
                return Integer.MAX_VALUE;
            if (sign * result < Integer.MIN_VALUE)
                return Integer.MIN_VALUE;

            i++;
        }

        return (int)(sign * result);
    }
}