package graphs;

import java.util.ArrayList;
import java.util.HashSet;

public class NumberOfDistinctIslands {
    private void dfs(int row, int col, int[][] vis,
                     int[][] grid, ArrayList<String> vec, int row0, int col0) {
        // mark the cell as visited
        vis[row][col] = 1;

        // coordinates - base coordinates
        vec.add(toString(row - row0, col - col0));
        int n = grid.length;
        int m = grid[0].length;

        // delta row and delta column
        int delrow[] = {-1, 0, +1, 0};
        int delcol[] = {0, -1, 0, +1};

        // traverse all 4 neighbours
        for (int i = 0; i < 4; i++) {
            int nrow = row + delrow[i];
            int ncol = col + delcol[i];
            // check for valid unvisited land neighbour coordinates
            if (nrow >= 0 && nrow < n && ncol >= 0 && ncol < m &&
                    vis[nrow][ncol] == 0 && grid[nrow][ncol] == 1) {
                dfs(nrow, ncol, vis, grid, vec, row0, col0);
            }
        }
    }

    private String toString(int r, int c) {
        return r + " " + c;
    }

    int countDistinctIslands(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        int vis[][] = new int[n][m];
        HashSet<ArrayList<String>> set = new HashSet<>();

        // traverse the grid
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                // if not visited and is a land cell
                if (vis[i][j] == 0 && grid[i][j] == 1) {
                    ArrayList<String> vec = new ArrayList<>();
                    dfs(i, j, vis, grid, vec, i, j);
                    // store it in HashSet
                    set.add(vec);
                }
            }
        }
        return set.size();
    }

    public static void main(String[] args) {
        int grid[][] = {
                {1, 1, 0, 1, 1},
                {1, 0, 0, 0, 0},
                {0, 0, 0, 0, 1},
                {1, 1, 0, 1, 1}};

        NumberOfDistinctIslands ob = new NumberOfDistinctIslands();
        int ans = ob.countDistinctIslands(grid);
        System.out.println(ans);

        int grid1[][] = {
                {1, 1, 0, 1, 1},
                {1, 0, 0, 0, 0},
                {0, 0, 0, 1, 1},
                {1, 1, 0, 1, 0}};

        ans = ob.countDistinctIslands(grid1);
        System.out.println(ans);
    }
}
