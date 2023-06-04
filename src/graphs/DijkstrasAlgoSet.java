package graphs;

import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.TreeSet;

public class DijkstrasAlgoSet {

    public static void main(String[] args) {
        int V = 6, E=6,S=0;

        ArrayList<GraphNode> inter1 = new ArrayList<>(){
            {
                add(new GraphNode(1,4));
                add(new GraphNode(2,4));
            }
        };
        ArrayList<GraphNode> inter2 = new ArrayList<>(){
            {
                add(new GraphNode(2,2));
                add(new GraphNode(0,4));
            }
        };
        ArrayList<GraphNode> inter3 = new ArrayList<>(){
            {
                add(new GraphNode(1,2));
                add(new GraphNode(0,4));
                add(new GraphNode(3,3));
                add(new GraphNode(5,6));
                add(new GraphNode(4,1));
            }
        };

        ArrayList<GraphNode> inter4 = new ArrayList<>(){
            {
                add(new GraphNode(2,3));
                add(new GraphNode(5,2));

            }
        };
        ArrayList<GraphNode> inter5 = new ArrayList<>(){
            {
                add(new GraphNode(2,1));
                add(new GraphNode(5,3));

            }
        };
        ArrayList<GraphNode> inter6 = new ArrayList<>(){
            {
                add(new GraphNode(3,2));
                add(new GraphNode(2,6));
                add(new GraphNode(4,3));

            }
        };
        ArrayList<ArrayList<GraphNode>> adj= new ArrayList<>(){
            {
                add(inter1); // for 1st node
                add(inter2); // for 2nd node
                add(inter3); // for 3rd node
                add(inter4); // for 4th node
                add(inter5); // for 5th node
                add(inter6); // for 6th node
            }
        };
        //add final values of adj here.
        DijkstrasAlgoSet obj = new DijkstrasAlgoSet();
        int[] res= obj.dijkstraSet(V,adj,S);

        for(int i=0;i<V;i++){
            System.out.print(res[i]+" ");
        }
        System.out.println();

    }



    static int[] dijkstraSet(int V, ArrayList<ArrayList<GraphNode>> adj, int S)
    {
        // Create a priority queue for storing the nodes as a pair {dist, node
        // where dist is the distance from source to the node.
        TreeSet<GraphNode> set =
                new TreeSet<>((x, y) -> x.distance != y.distance ? x.distance - y.distance : x.node - y.node);

        int []dist = new int[V];

        // Initialising distTo list with a large number to
        // indicate the nodes are unvisited initially.
        // This list contains distance from source to the nodes.
        for(int i = 0;i<V;i++) dist[i] = (int)(1e9);

        // Source initialised with dist=0.
        dist[S] = 0;
        set.add(new GraphNode(S, 0));

        // Now, pop the minimum distance node first from the min-heap
        // and traverse for all its adjacent nodes.
        while(set.size() != 0) {
            GraphNode graphNode = set.pollFirst();
            int dis = graphNode.distance;
            int node = graphNode.node;

            // Check for all adjacent nodes of the popped out
            // element whether the prev dist is larger than current or not.
            for(GraphNode graphNodes : adj.get(node)){
                int edgeWeight = graphNodes.distance;
                int adjNode =graphNodes.node;

                // If current distance is smaller,
                // push it into the queue.
                if(dis + edgeWeight < dist[adjNode]) {
                    if(dist[adjNode] != 1e9) {
                        set.remove(new GraphNode(adjNode, dist[adjNode]));
                    }

                    dist[adjNode] = dis + edgeWeight;
                    set.add(new GraphNode(adjNode, dist[adjNode]));
                }
            }

        }
        // Return the list containing shortest distances
        // from source to all the nodes.
        return dist;
    }
}
