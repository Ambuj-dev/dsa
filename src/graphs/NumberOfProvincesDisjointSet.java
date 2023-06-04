package graphs;

import java.util.ArrayList;
import java.util.List;

public class NumberOfProvincesDisjointSet {
    static  int numProvinces(List<List<Integer>> adj, int v){
        DisjointSet ds = new DisjointSet(v);
        for(int i = 0; i< v; i++){
            for(int j = 0; j< v; j++){
                if(adj.get(i).get(j) == 1){
                    ds.unionBySize(i, j);
                }
            }
        }
        int cnt = 0;
        for(int i = 0; i< v; i++){
            if(ds.parent.get(i)  == i) cnt++;
        }
        return cnt;
    }

    public static void main(String[] args)
    {

        // adjacency matrix
        List<List<Integer>> adj = new ArrayList<>();

        adj.add(new ArrayList<>());
        adj.get(0).add(0, 1);
        adj.get(0).add(1, 0);
        adj.get(0).add(2, 1);
        adj.add(new ArrayList<>());
        adj.get(1).add(0, 0);
        adj.get(1).add(1, 1);
        adj.get(1).add(2, 0);
        adj.add(new ArrayList<>());
        adj.get(2).add(0, 1);
        adj.get(2).add(1, 0);
        adj.get(2).add(2, 1);

        NumberOfProvincesDisjointSet ob = new NumberOfProvincesDisjointSet();
        System.out.println(ob.numProvinces(adj,3));
    }
}
