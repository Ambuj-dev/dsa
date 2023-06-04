package graphs;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class KruskalsAlgoMST {

    static int spanningTree(int V,
                            ArrayList<ArrayList<ArrayList<Integer>>> adj) {
        List<Edge> edges = new ArrayList<>();
        // O(N + E)
        for (int i = 0; i < V; i++) {
            for(List<Integer> node : adj.get(i)){
                int adjNode = node.get(0);
                int wt = node.get(1);
                Edge temp = new Edge(i, adjNode, wt);
                edges.add(temp);
            }
        }
        DisjointSet ds = new DisjointSet(V);
        // M log M
        Collections.sort(edges);
        int mstWt = 0;
        // M x 4 x alpha x 2
        for(Edge edge: edges){
            int wt = edge.weight;
            int u = edge.src;
            int v = edge.dest;

            if (ds.findUPar(u) != ds.findUPar(v)) {
                mstWt += wt;
                ds.unionBySize(u, v);
            }
        }
        return mstWt;
    }

    public static void main (String[] args) {
        int V = 5;
        ArrayList<ArrayList<ArrayList<Integer>>> adj = new ArrayList<ArrayList<ArrayList<Integer>>>();
        int[][] edges =  {{0, 1, 2}, {0, 2, 1}, {1, 2, 1}, {2, 3, 2}, {3, 4, 1}, {4, 2, 2}};

        for (int i = 0; i < V; i++) {
            adj.add(new ArrayList<ArrayList<Integer>>());
        }

        for (int i = 0; i < 6; i++) {
            int u = edges[i][0];
            int v = edges[i][1];
            int w = edges[i][2];

            ArrayList<Integer> tmp1 = new ArrayList<Integer>();
            ArrayList<Integer> tmp2 = new ArrayList<Integer>();
            tmp1.add(v);
            tmp1.add(w);

            tmp2.add(u);
            tmp2.add(w);

            adj.get(u).add(tmp1);
            adj.get(v).add(tmp2);
        }

        KruskalsAlgoMST obj = new KruskalsAlgoMST();
        int mstWt = obj.spanningTree(V, adj);
        System.out.println("The sum of all the edge weights: " + mstWt);

    }

}
