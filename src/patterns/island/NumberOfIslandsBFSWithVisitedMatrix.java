package patterns.island;

import java.util.LinkedList;
import java.util.Queue;

public class NumberOfIslandsBFSWithVisitedMatrix {
    public static int countIslands(int[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;
        boolean[][] visited = new boolean[rows][cols];
        int totalIsland = 0;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                // if the cell has not been visited before and is a land
                if (!visited[i][j] && matrix[i][j] == 1) {
                    // we have found an island
                    totalIsland++;
                    visitIslandBFS(matrix, visited, i, j);
                }
            }
        }
        return totalIsland;
    }

    private static void visitIslandBFS(int[][] matrix, boolean[][] visited, int x, int y) {
        Queue<int[]> neighbors = new LinkedList<>();
        neighbors.add(new int[] { x, y });
        while (!neighbors.isEmpty()) {
            int row = neighbors.peek()[0];
            int col = neighbors.peek()[1];
            neighbors.remove();

            if (row < 0 || col < 0 || row >= matrix.length || col >= matrix[0].length)
                continue; // continue, if it is not a valid cell
            if (matrix[row][col] == 0 || visited[row][col])
                continue; // continue if the cell is water or visited

            visited[row][col] = true; // mark the visited array

            // insert all neighboring cells to the queue for BFS
            neighbors.add(new int[] { row + 1, col }); // lower cell
            neighbors.add(new int[] { row - 1, col }); // upper cell
            neighbors.add(new int[] { row, col + 1 }); // right cell
            neighbors.add(new int[] { row, col - 1 }); // left cell
        }
    }

    public static void main(String[] args) {
        System.out.println(NumberOfIslandsBFSWithVisitedMatrix.countIslands(
                new int[][] {
                        { 0, 1, 1, 1, 0 },
                        { 0, 0, 0, 1, 1 },
                        { 0, 1, 1, 1, 0 },
                        { 0, 1, 1, 0, 0 },
                        { 0, 0, 0, 0, 0 }
                }));

        System.out.println(NumberOfIslandsBFSWithVisitedMatrix.countIslands(
                new int[][] {
                        { 1, 1, 1, 0, 0 },
                        { 0, 1, 0, 0, 1 },
                        { 0, 0, 1, 1, 0 },
                        { 0, 0, 1, 0, 0 },
                        { 0, 0, 1, 0, 0 }
                }));
    }
}
