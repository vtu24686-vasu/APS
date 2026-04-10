import java.util.*;

class Solution {
    public int maxAreaOfIsland(int[][] grid) {
        
        int maxArea = 0;
        int rows = grid.length;
        int cols = grid[0].length;

        int[][] directions = {{1,0},{-1,0},{0,1},{0,-1}};
        Queue<int[]> queue = new LinkedList<>();

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                
                if (grid[i][j] == 1) {
                    int area = 0;

                    queue.offer(new int[]{i, j});
                    grid[i][j] = 0;

                    while (!queue.isEmpty()) {
                        int[] curr = queue.poll();
                        area++;

                        for (int[] d : directions) {
                            int x = curr[0] + d[0];
                            int y = curr[1] + d[1];

                            if (x >= 0 && y >= 0 && x < rows && y < cols && grid[x][y] == 1) {
                                grid[x][y] = 0;
                                queue.offer(new int[]{x, y});
                            }
                        }
                    }

                    maxArea = Math.max(maxArea, area);
                }
            }
        }

        return maxArea;
    }
}