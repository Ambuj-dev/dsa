package patterns.island;

import java.util.HashSet;
import java.util.Set;

public class DistinctIslands {

    public static int findDistinctIslandsDFS(int[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;
        boolean[][] visited = new boolean[rows][cols];
        Set<String> islandsSet = new HashSet<>();

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (matrix[i][j] == 1 && !visited[i][j]) { // only if the cell is a land and not visited
                    StringBuilder islandTraversal = new StringBuilder();
                    traverseIslandDFS(matrix, visited, i, j, islandTraversal, "O"); // origin
                    islandsSet.add(islandTraversal.toString());
                }
            }
        }
        return islandsSet.size();
    }

    private static void traverseIslandDFS(int[][] matrix, boolean[][] visited, int x, int y,
                                          StringBuilder islandTraversal, String direction) {
        if (x < 0 || x >= matrix.length || y < 0 || y >= matrix[0].length)
            return; // return if it is not a valid cell
        if (matrix[x][y] == 0 || visited[x][y])
            return; // return if it is a water cell or is visited

        visited[x][y] = true; // mark the cell visited

        islandTraversal.append(direction);
        // recursively visit all neighboring cells (horizontally & vertically)
        traverseIslandDFS(matrix, visited, x + 1, y, islandTraversal, "D"); // down
        traverseIslandDFS(matrix, visited, x - 1, y, islandTraversal, "U"); // up
        traverseIslandDFS(matrix, visited, x, y + 1, islandTraversal, "R"); // right
        traverseIslandDFS(matrix, visited, x, y - 1, islandTraversal, "L"); // left

        islandTraversal.append("B"); // back
    }

    public static void main(String[] args) {
        System.out.println(DistinctIslands.findDistinctIslandsDFS(
                new int[][] {
                        { 1, 1, 0, 1, 1 },
                        { 1, 1, 0, 1, 1 },
                        { 0, 0, 0, 0, 0 },
                        { 0, 1, 1, 0, 1 },
                        { 0, 1, 1, 0, 1 }
                }));

        System.out.println(DistinctIslands.findDistinctIslandsDFS(
                new int[][] {
                        { 1, 1, 0, 1 },
                        { 0, 1, 1, 0 },
                        { 0, 0, 0, 0 },
                        { 1, 1, 0, 0 },
                        { 0, 1, 1, 0 }
                }));
    }
}
