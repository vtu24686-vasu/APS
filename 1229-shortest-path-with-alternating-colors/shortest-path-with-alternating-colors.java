import java.util.*;

class Solution {
    public int[] shortestAlternatingPaths(int n, int[][] redEdges, int[][] blueEdges) {
        // Graph representation
        List<int[]>[] graph = new ArrayList[n];
        for (int i = 0; i < n; i++) graph[i] = new ArrayList<>();

        // color: 0 = red, 1 = blue
        for (int[] e : redEdges) {
            graph[e[0]].add(new int[]{e[1], 0});
        }
        for (int[] e : blueEdges) {
            graph[e[0]].add(new int[]{e[1], 1});
        }

        // Result array
        int[] result = new int[n];
        Arrays.fill(result, -1);

        // visited[node][color]
        boolean[][] visited = new boolean[n][2];

        // BFS queue: {node, lastColor}
        Queue<int[]> queue = new LinkedList<>();
        
        // Start with both colors
        queue.offer(new int[]{0, 0});
        queue.offer(new int[]{0, 1});
        visited[0][0] = true;
        visited[0][1] = true;

        int steps = 0;

        while (!queue.isEmpty()) {
            int size = queue.size();
            
            for (int i = 0; i < size; i++) {
                int[] curr = queue.poll();
                int node = curr[0];
                int color = curr[1];

                // Set result if not visited before
                if (result[node] == -1) {
                    result[node] = steps;
                }

                // Explore neighbors with alternate color
                for (int[] next : graph[node]) {
                    int nextNode = next[0];
                    int nextColor = next[1];

                    if (nextColor != color && !visited[nextNode][nextColor]) {
                        visited[nextNode][nextColor] = true;
                        queue.offer(new int[]{nextNode, nextColor});
                    }
                }
            }
            steps++;
        }

        return result;
    }
}