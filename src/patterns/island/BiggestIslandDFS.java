package patterns.island;

public class BiggestIslandDFS {
    public static int maxAreaOfIsland(int[][] matrix) {

        int rows = matrix.length;
        int cols = matrix[0].length;
        int biggestIslandArea = 0;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (matrix[i][j] == 1) { // only if the cell is a land
                    // we have found an island
                    biggestIslandArea = Math.max(biggestIslandArea, visitIslandDFS(matrix, i, j));
                }
            }
        }
        return biggestIslandArea;
    }

    private static int visitIslandDFS(int[][] matrix, int x, int y) {
        if (x < 0 || x >= matrix.length || y < 0 || y >= matrix[0].length)
            return 0; // return, if it is not a valid cell
        if (matrix[x][y] == 0)
            return 0; // return, if it is a water cell

        matrix[x][y] = 0; // mark the cell visited by making it a water cell

        int area = 1; // counting the current cell
        // recursively visit all neighboring cells (horizontally & vertically)
        area += visitIslandDFS(matrix, x + 1, y); // lower cell
        area += visitIslandDFS(matrix, x - 1, y); // upper cell
        area += visitIslandDFS(matrix, x, y + 1); // right cell
        area += visitIslandDFS(matrix, x, y - 1); // left cell

        return area;
    }

    public static void main(String[] args) {
        System.out.println(BiggestIslandDFS.maxAreaOfIsland(
                new int[][] {
                        { 1, 1, 1, 0, 0 },
                        { 0, 1, 0, 0, 1 },
                        { 0, 0, 1, 1, 0 },
                        { 0, 1, 1, 0, 0 },
                        { 0, 0, 1, 0, 0 }
                }));
    }
}
