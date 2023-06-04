package graphs;

import java.util.LinkedList;
import java.util.Queue;

public class DistanceOfNearestCellHaving1 {
    public int[][] nearest(int[][] grid)
    {
        int n = grid.length;
        int m = grid[0].length;
        // visited and distance matrix
        int vis[][] = new int[n][m];
        int dist[][] = new int[n][m];
        // <coordinates, steps>
        Queue<Triple> q = new LinkedList<>();
        // traverse the matrix
        for(int i = 0;i<n;i++) {
            for(int j = 0;j<m;j++) {
                // start BFS if cell contains 1
                if(grid[i][j] == 1) {
                    q.add(new Triple(i, j, 0));
                    vis[i][j] = 1;
                }
            }
        }

        int delrow[] = {-1, 0, +1, 0};
        int delcol[] = {0, +1, 0, -1};

        // n x m x 4
        // traverse till queue becomes empty
        while(!q.isEmpty()) {
            Triple triple = q.poll();
            int row = triple.first;
            int col = triple.second;
            int steps = triple.third;
            dist[row][col] = steps;
            // for all 4 neighbours
            for(int i = 0;i<4;i++) {
                int nrow = row + delrow[i];
                int ncol = col + delcol[i];
                // check for valid unvisited cell
                if(nrow >= 0 && nrow < n && ncol >= 0 && ncol < m
                        && vis[nrow][ncol] == 0)  {
                    vis[nrow][ncol] = 1;
                    q.add(new Triple(nrow, ncol, steps+1));
                }
            }
        }

        // return distance matrix
        return dist;
    }
    public static void main(String[] args)
    {
        int[][] grid = {
                {0,1,1,0},
                {1,1,0,0},
                {0,0,1,1}
        };

        DistanceOfNearestCellHaving1 obj = new DistanceOfNearestCellHaving1();
        int[][] ans = obj.nearest(grid);
        for(int i = 0; i < ans.length; i++){
            for(int j = 0; j < ans[i].length; j++){
                System.out.print(ans[i][j] + " ");
            }
            System.out.println();
        }
    }
}

