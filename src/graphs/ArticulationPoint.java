package graphs;

import java.util.ArrayList;

public class ArticulationPoint {

    private int timer = 1;
    private void dfs(int node, int parent, int[] vis,
                     int tin[], int low[], int[] mark,
                     ArrayList<ArrayList<Integer>> adj) {
        vis[node] = 1;
        tin[node] = low[node] = timer;
        timer++;
        int child = 0;
        for (Integer it : adj.get(node)) {
            if (it == parent) continue;
            if (vis[it] == 0) {
                //visit the node update low if required and check for bridge.
                dfs(it, node, vis, tin, low, mark, adj);
                //while backtracking update the lowest time from adjacent node if it is smaller
                low[node] = Math.min(low[node], low[it]);
                // node --- it
                //As we are removing node even if it is equals to parent node then it is an articulation paint
                if (low[it] >= tin[node] && parent != -1) {
                    mark[node] = 1;
                }
                child++;
            } else {//As it is already visited by some other route. There is already a path. So can't be a bridge. So only update the lowest time.
                // If we remove the node we can't reach his parent, so it's low should be compared with tin(time of insertion)
                low[node] = Math.min(low[node], tin[it]);
            }
        }

        //If the node has multiple child, and it is the parent node(parent = -1) then it is a articulation point
        if (child > 1 && parent == -1) {
            mark[node] = 1;
        }
    }
    //Function to return Breadth First Traversal of given graph.
    public ArrayList<Integer> articulationPoints(int n,
                                                 ArrayList<ArrayList<Integer>> adj) {
        int[] vis = new int[n];
        int[] tin = new int[n];
        int[] low = new int[n];
        int[] mark = new int[n];
        for (int i = 0; i < n; i++) {
            if (vis[i] == 0) {
                dfs(i, -1, vis, tin, low, mark, adj);
            }
        }
        ArrayList<Integer> ans = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (mark[i] == 1) {
                ans.add(i);
            }
        }
        if (ans.size() == 0) {
            ans.add(-1);
        }
        return ans;
    }

    public static void main (String[] args) {
        int n = 5;
        int[][] edges = {
                {0, 1}, {1, 4},
                {2, 4}, {2, 3}, {3, 4}
        };
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<Integer>());
        }
        for (int i = 0; i < n; i++) {
            int u = edges[i][0], v = edges[i][1];
            adj.get(u).add(v);
            adj.get(v).add(u);
        }

        ArticulationPoint obj = new ArticulationPoint();
        ArrayList<Integer> nodes = obj.articulationPoints(n, adj);

        int size = nodes.size();
        for (int i = 0; i < size; i++) {
            int node = nodes.get(i);
            System.out.print(node + " ");
        }
        System.out.println("");
    }
}
