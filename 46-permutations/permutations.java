import java.util.*;

class Solution {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        boolean[] used = new boolean[nums.length];
        List<Integer> current = new ArrayList<>();

        backtrack(nums, used, current, result);
        return result;
    }

    private void backtrack(int[] nums, boolean[] used, 
                           List<Integer> current, List<List<Integer>> result) {

        // base case: full permutation formed
        if (current.size() == nums.length) {
            result.add(new ArrayList<>(current));
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            if (used[i]) continue;

            // choose
            used[i] = true;
            current.add(nums[i]);

            // explore
            backtrack(nums, used, current, result);

            // un-choose (backtrack)
            current.remove(current.size() - 1);
            used[i] = false;
        }
    }
}