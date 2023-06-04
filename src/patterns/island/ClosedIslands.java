package patterns.island;

public class ClosedIslands {

    public static int countClosedIslands(int[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;
        int countClosedIslands = 0;
        boolean[][] visited = new boolean[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (matrix[i][j] == 1 && !visited[i][j]) { // only if the cell is a land and not visited
                    if (isClosedIslandDFS(matrix, visited, i, j))
                        countClosedIslands++;
                }
            }
        }
        return countClosedIslands;
    }

    private static boolean isClosedIslandDFS(int[][] matrix, boolean[][] visited, int x, int y) {
        if (x < 0 || x >= matrix.length || y < 0 || y >= matrix[0].length)
            return false; // returning false since the island is touching an edge
        if (matrix[x][y] == 0 || visited[x][y])
            return true; // returning true as the island is surrounded by water

        visited[x][y] = true; // mark the cell visited

        Boolean isClosed = true;
        // recursively visit all neighboring cells (horizontally & vertically)
        isClosed &= isClosedIslandDFS(matrix, visited, x + 1, y); // lower cell
        isClosed &= isClosedIslandDFS(matrix, visited, x - 1, y); // upper cell
        isClosed &= isClosedIslandDFS(matrix, visited, x, y + 1); // right cell
        isClosed &= isClosedIslandDFS(matrix, visited, x, y - 1); // left cell

        return isClosed;
    }

    public static void main(String[] args) {
        System.out.println(ClosedIslands.countClosedIslands(
                new int[][] {
                        { 1, 1, 0, 0, 0 },
                        { 0, 1, 0, 0, 0 },
                        { 0, 0, 1, 1, 0 },
                        { 0, 1, 1, 0, 0 },
                        { 0, 0, 0, 0, 0 }
                }));

        System.out.println(ClosedIslands.countClosedIslands(
                new int[][] {
                        { 0, 0, 0, 0 },
                        { 0, 1, 0, 0 },
                        { 0, 1, 0, 0 },
                        { 0, 0, 1, 0 },
                        { 0, 0, 0, 0 }
                }));

        System.out.println(ClosedIslands.countClosedIslands(
                new int[][] {
                        { 0, 0, 0, 0 },
                        { 1, 1, 0, 0 },
                        { 0, 1, 0, 0 },
                        { 0, 0, 1, 0 },
                        { 0, 0, 0, 0 }
                }));
    }
}
