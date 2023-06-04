package patterns.island;

import java.util.LinkedList;
import java.util.Queue;

public class NumberOfIslandsBFS {
    public static int countIslands(int[][] matrix) {

        int rows = matrix.length;
        int cols = matrix[0].length;
        int totalIslands = 0;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (matrix[i][j] == 1) { // only if the cell is a land
                    // we have found an island
                    totalIslands++;
                    visitIslandBFS(matrix, i, j);
                }
            }
        }
        return totalIslands;
    }

    private static void visitIslandBFS(int[][] matrix, int x, int y) {
        Queue<int[]> neighbors = new LinkedList<>();
        neighbors.add(new int[] { x, y });
        while (!neighbors.isEmpty()) {
            int row = neighbors.peek()[0];
            int col = neighbors.peek()[1];
            neighbors.remove();

            if (row < 0 || row >= matrix.length || col < 0 || col >= matrix[0].length)
                continue; // continue, if it is not a valid cell
            if (matrix[row][col] == 0)
                continue; // continue if it is a water cell

            matrix[row][col] = 0; // mark the cell visited by making it a water cell

            // insert all neighboring cells to the queue for BFS
            neighbors.add(new int[] { row + 1, col }); // lower cell
            neighbors.add(new int[] { row - 1, col }); // upper cell
            neighbors.add(new int[] { row, col + 1 }); // right cell
            neighbors.add(new int[] { row, col - 1 }); // left cell
        }
    }

    public static void main(String[] args) {
        System.out.println(NumberOfIslandsBFS.countIslands(
                new int[][] {
                        { 0, 1, 1, 1, 0 },
                        { 0, 0, 0, 1, 1 },
                        { 0, 1, 1, 1, 0 },
                        { 0, 1, 1, 0, 0 },
                        { 0, 0, 0, 0, 0 }
                }));

        System.out.println(NumberOfIslandsBFS.countIslands(
                new int[][] {
                        { 1, 1, 1, 0, 0 },
                        { 0, 1, 0, 0, 1 },
                        { 0, 0, 1, 1, 0 },
                        { 0, 0, 1, 0, 0 },
                        { 0, 0, 1, 0, 0 }
                }));
    }
}
