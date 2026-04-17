import java.util.*;

class Solution {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> current = new ArrayList<>();

        backtrack(nums, 0, current, result);
        return result;
    }

    private void backtrack(int[] nums, int index, List<Integer> current, List<List<Integer>> result) {
        // add current subset
        result.add(new ArrayList<>(current));

        for (int i = index; i < nums.length; i++) {
            // include nums[i]
            current.add(nums[i]);

            // move forward
            backtrack(nums, i + 1, current, result);

            // backtrack (remove last element)
            current.remove(current.size() - 1);
        }
    }
}