package patterns.island;

public class Cycle {

    public static boolean hasCycle(char[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;
        boolean[][] visited = new boolean[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (!visited[i][j]) // only if the cell is not visited
                    if (containsCycleDFS(matrix, visited, matrix[i][j], i, j, -1, -1))
                        return true;
            }
        }
        return false;
    }

    private static boolean containsCycleDFS(char[][] matrix, boolean[][] visited,
                                            char startChar, int x, int y, int prevX, int prevY) {
        if (x < 0 || x >= matrix.length || y < 0 || y >= matrix[0].length)
            return false; // not a valid cell
        if (matrix[x][y] != startChar)
            return false; // different character which means a different island

        if (visited[x][y])
            return true; // found a cycle, as we are visiting an already visited valid cell

        visited[x][y] = true; // mark the cell visited

        // recursively visit all neighboring cells (horizontally & vertically)
        if (x + 1 != prevX && containsCycleDFS(matrix, visited, startChar, x + 1, y, x, y)) // down
            return true;
        if (x - 1 != prevX && containsCycleDFS(matrix, visited, startChar, x - 1, y, x, y)) // up
            return true;
        if (y + 1 != prevY && containsCycleDFS(matrix, visited, startChar, x, y + 1, x, y)) // right
            return true;
        if (y - 1 != prevY && containsCycleDFS(matrix, visited, startChar, x, y - 1, x, y)) // left
            return true;

        return false;
    }

    public static void main(String[] args) {
        System.out.println(Cycle.hasCycle(
                new char[][] {
                        { 'a', 'a', 'a', 'a' },
                        { 'b', 'a', 'c', 'a' },
                        { 'b', 'a', 'c', 'a' },
                        { 'b', 'a', 'a', 'a' }
                }));

        System.out.println(Cycle.hasCycle(
                new char[][] {
                        { 'a', 'a', 'a', 'a' },
                        { 'a', 'b', 'b', 'a' },
                        { 'a', 'b', 'a', 'a' },
                        { 'a', 'a', 'a', 'c' }
                }));

        System.out.println(Cycle.hasCycle(
                new char[][] {
                        { 'a', 'b', 'e', 'b' },
                        { 'b', 'b', 'b', 'b' },
                        { 'b', 'c', 'c', 'd' },
                        { 'c', 'c', 'd', 'd' }
                }));

    }
}
