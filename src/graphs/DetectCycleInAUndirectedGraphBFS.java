package graphs;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class DetectCycleInAUndirectedGraphBFS {
    static boolean checkForCycle(ArrayList<ArrayList<Integer>> adj, int s,
                                 boolean vis[]) {
        Queue<Node> q = new LinkedList<>(); //BFS
        q.add(new Node(s, -1));
        vis[s] = true;

        // until the queue is empty
        while (!q.isEmpty()) {
            // source node and its parent node
            Node pair = q.remove();
            int node = pair.first;
            int par = pair.second;


            // go to all the adjacent nodes
            for (Integer it : adj.get(node)) {
                if (vis[it] == false) {
                    q.add(new Node(it, node));
                    vis[it] = true;
                }

                // if adjacent node is visited and is not its own parent node
                else if (par != it) return true;
            }
        }

        return false;
    }

    // function to detect cycle in an undirected graph
    public boolean isCycle(int V, ArrayList<ArrayList<Integer>> adj) {
        boolean vis[] = new boolean[V];

        for (int i = 0; i < V; i++)
            if (vis[i] == false)
                if (checkForCycle(adj, i, vis))
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

        DetectCycleInAUndirectedGraphBFS obj = new DetectCycleInAUndirectedGraphBFS();
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

class Node {
    int first;
    int second;

    public Node(int first, int second) {
        this.first = first;
        this.second = second;
    }
}

