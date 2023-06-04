package graphs;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class DetectCycleInAUndirectedGraphDFS {
    private boolean dfs(int node, int parent, int vis[], ArrayList<ArrayList<Integer>>
            adj) {
        vis[node] = 1;
        // go to all adjacent nodes
        for(int adjacentNode: adj.get(node)) {
            if(vis[adjacentNode]==0) {
                if(dfs(adjacentNode, node, vis, adj))
                    return true;
            }
            // if adjacent node is visited and is not its own parent node
            else if(adjacentNode != parent) return true;
        }
        return false;
    }


    // function to detect cycle in an undirected graph
    public boolean isCycle(int V, ArrayList<ArrayList<Integer>> adj) {
        int vis[] = new int[V];

        for (int i = 0; i < V; i++)
            if (vis[i] == 0)
                if (dfs( i,-1, vis, adj))
                    return true;

        return false;
    }

    public static void main(String[] args) {
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            adj.add(new ArrayList<>());
        }
        adj.get(1).add(2);
        adj.get(2).add(1);
        adj.get(2).add(3);
        adj.get(3).add(2);

        DetectCycleInAUndirectedGraphDFS obj = new DetectCycleInAUndirectedGraphDFS();
        System.out.println(obj.isCycle(4, adj));

        ArrayList<ArrayList<Integer>> adj1 = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            adj1.add(new ArrayList<>());
        }
        adj1.get(1).add(2);
        adj1.get(1).add(3);
        adj1.get(2).add(1);
        adj1.get(2).add(5);
        adj1.get(3).add(1);
        adj1.get(3).add(4);
        adj1.get(3).add(6);
        adj1.get(4).add(3);
        adj1.get(5).add(2);
        adj1.get(5).add(7);
        adj1.get(6).add(3);
        adj1.get(6).add(7);
        adj1.get(7).add(5);
        adj1.get(7).add(6);
        System.out.println(obj.isCycle(8, adj1));

    }
}


