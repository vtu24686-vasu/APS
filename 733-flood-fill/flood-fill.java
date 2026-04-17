import java.util.*;

class Solution {
    public int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
        int originalColor = image[sr][sc];
        if (originalColor == newColor) return image;

        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{sr, sc});
        image[sr][sc] = newColor;

        int[][] dirs = {{1,0},{-1,0},{0,1},{0,-1}};

        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            for (int[] d : dirs) {
                int r = curr[0] + d[0];
                int c = curr[1] + d[1];

                if (r >= 0 && c >= 0 && r < image.length && c < image[0].length 
                    && image[r][c] == originalColor) {
                    
                    image[r][c] = newColor;
                    queue.offer(new int[]{r, c});
                }
            }
        }

        return image;
    }
}