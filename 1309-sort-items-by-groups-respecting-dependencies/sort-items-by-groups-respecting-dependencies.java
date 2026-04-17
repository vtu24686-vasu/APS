import java.util.*;

class Solution {
    public int[] sortItems(int n, int m, int[] group, List<List<Integer>> beforeItems) {
        // Step 1: Assign unique group ids to ungrouped items
        for (int i = 0; i < n; i++) {
            if (group[i] == -1) {
                group[i] = m++;
            }
        }

        // Step 2: Build graphs
        List<List<Integer>> itemGraph = new ArrayList<>();
        List<List<Integer>> groupGraph = new ArrayList<>();

        for (int i = 0; i < n; i++) itemGraph.add(new ArrayList<>());
        for (int i = 0; i < m; i++) groupGraph.add(new ArrayList<>());

        int[] itemIndegree = new int[n];
        int[] groupIndegree = new int[m];

        for (int i = 0; i < n; i++) {
            for (int prev : beforeItems.get(i)) {
                itemGraph.get(prev).add(i);
                itemIndegree[i]++;

                if (group[i] != group[prev]) {
                    groupGraph.get(group[prev]).add(group[i]);
                    groupIndegree[group[i]]++;
                }
            }
        }

        // Step 3: Topological sort groups
        List<Integer> groupOrder = topoSort(groupGraph, groupIndegree, m);
        if (groupOrder.size() == 0) return new int[0];

        // Step 4: Topological sort items
        List<Integer> itemOrder = topoSort(itemGraph, itemIndegree, n);
        if (itemOrder.size() == 0) return new int[0];

        // Step 5: Group items based on sorted order
        Map<Integer, List<Integer>> groupToItems = new HashMap<>();
        for (int item : itemOrder) {
            groupToItems.putIfAbsent(group[item], new ArrayList<>());
            groupToItems.get(group[item]).add(item);
        }

        // Step 6: Build final result
        List<Integer> result = new ArrayList<>();
        for (int grp : groupOrder) {
            if (groupToItems.containsKey(grp)) {
                result.addAll(groupToItems.get(grp));
            }
        }

        return result.stream().mapToInt(i -> i).toArray();
    }

    private List<Integer> topoSort(List<List<Integer>> graph, int[] indegree, int size) {
        Queue<Integer> queue = new LinkedList<>();
        List<Integer> result = new ArrayList<>();

        for (int i = 0; i < size; i++) {
            if (indegree[i] == 0) queue.offer(i);
        }

        while (!queue.isEmpty()) {
            int curr = queue.poll();
            result.add(curr);

            for (int next : graph.get(curr)) {
                if (--indegree[next] == 0) {
                    queue.offer(next);
                }
            }
        }

        return result.size() == size ? result : new ArrayList<>();
    }
}