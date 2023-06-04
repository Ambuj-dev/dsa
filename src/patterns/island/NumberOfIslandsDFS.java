package patterns.island;

public class NumberOfIslandsDFS {
    public static int countIslands(int[][] matrix) {

        int rows = matrix.length;
        int cols = matrix[0].length;
        int totalIslands = 0;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (matrix[i][j] == 1) { // only if the cell is a land
                    // we have found an island
                    totalIslands++;
                    visitIslandDFS(matrix, i, j);
                }
            }
        }
        return totalIslands;
    }

    private static void visitIslandDFS(int[][] matrix, int x, int y) {
        if (x < 0 || x >= matrix.length || y < 0 || y >= matrix[0].length)
            return; // return, if it is not a valid cell
        if (matrix[x][y] == 0)
            return; // return, if it is a water cell

        matrix[x][y] = 0; // mark the cell visited by making it a water cell

        // recursively visit all neighboring cells (horizontally & vertically)
        visitIslandDFS(matrix, x + 1, y); // lower cell
        visitIslandDFS(matrix, x - 1, y); // upper cell
        visitIslandDFS(matrix, x, y + 1); // right cell
        visitIslandDFS(matrix, x, y - 1); // left cell
    }

    public static void main(String[] args) {
        System.out.println(NumberOfIslandsDFS.countIslands(
                new int[][] {
                        { 0, 1, 1, 1, 0 },
                        { 0, 0, 0, 1, 1 },
                        { 0, 1, 1, 1, 0 },
                        { 0, 1, 1, 0, 0 },
                        { 0, 0, 0, 0, 0 }
                }));

        System.out.println(NumberOfIslandsDFS.countIslands(
                new int[][] {
                        { 1, 1, 1, 0, 0 },
                        { 0, 1, 0, 0, 1 },
                        { 0, 0, 1, 1, 0 },
                        { 0, 0, 1, 0, 0 },
                        { 0, 0, 1, 0, 0 }
                }));
    }
}
