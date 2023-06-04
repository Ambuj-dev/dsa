package graphs;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class MostStonesRemovedWithSameRowColumn {
    int maxRemove(int[][] stones, int n) {
        int maxRow = 0;
        int maxCol = 0;
        for (int i = 0; i < n; i++) {
            maxRow = Math.max(maxRow, stones[i][0]);
            maxCol = Math.max(maxCol, stones[i][1]);
        }
        DisjointSet ds = new DisjointSet(maxRow + maxCol+ 1);
        HashMap<Integer, Integer> stoneNodes = new HashMap<>();
        for (int i = 0; i < n; i++) {
            int nodeRow = stones[i][0];
            int nodeCol = stones[i][1] + maxRow + 1;
            ds.unionBySize(nodeRow, nodeCol);
            stoneNodes.put(nodeRow, 1);
            stoneNodes.put(nodeCol, 1);
        }

        int cnt = 0;
        for (Map.Entry<Integer, Integer> it : stoneNodes.entrySet()) {
            if (ds.findUPar(it.getKey()) == it.getKey()) {
                cnt++;
            }
        }
        return n - cnt;
    }
    int maxRemove1(int[][] stones, int n) {
        int maxRow = 0;
        int maxCol = 0;
        for (int i = 0; i < n; i++) {
            maxRow = Math.max(maxRow, stones[i][0]);
            maxCol = Math.max(maxCol, stones[i][1]);
        }
        DisjointSet ds = new DisjointSet(maxRow + maxCol+ 1);
        HashSet<Integer> stoneNodes = new HashSet<>();
        for (int i = 0; i < n; i++) {
            int nodeRow = stones[i][0];
            int nodeCol = stones[i][1] + maxRow + 1;
            ds.unionBySize(nodeRow, nodeCol);
            stoneNodes.add(nodeRow);
            stoneNodes.add(nodeCol);
        }

        int cnt = 0;
        for(Integer i : stoneNodes){
            if(ds.findUPar(i) == i){
                cnt++;
            }
        }
        return n - cnt;
    }


    public static void main (String[] args) {
        int n = 6;
        int[][] stones = {
                {0, 0}, {0, 2},
                {1, 3}, {3, 1},
                {3, 2}, {4, 3}
        };

        MostStonesRemovedWithSameRowColumn obj = new MostStonesRemovedWithSameRowColumn();
        int ans = obj.maxRemove(stones, n);
        System.out.println("The maximum number of stones we can remove is: " + ans);
        ans = obj.maxRemove1(stones, n);
        System.out.println("The maximum number of stones we can remove is: " + ans);

    }

}
