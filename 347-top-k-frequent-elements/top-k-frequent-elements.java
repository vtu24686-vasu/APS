import java.util.*;

class Solution {
    public int[] topKFrequent(int[] nums, int k) {
        
        // Step 1: Count frequency
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        // Step 2: Min Heap based on frequency
        PriorityQueue<Integer> minHeap = new PriorityQueue<>(
            (a, b) -> map.get(a) - map.get(b)
        );

        for (int num : map.keySet()) {
            minHeap.add(num);
            
            if (minHeap.size() > k) {
                minHeap.poll();
            }
        }

        // Step 3: Build result
        int[] result = new int[k];
        int i = 0;
        while (!minHeap.isEmpty()) {
            result[i++] = minHeap.poll();
        }

        return result;
    }
}