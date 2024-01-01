package graphs;

import java.util.*;

public class DijkstrasAlgoPQ {

    public static void main(String[] args) {
        int V = 3, E=3,S=2;

        ArrayList<GraphNode> inter1 = new ArrayList<>(){
            {
                add(new GraphNode(1,1));
                add(new GraphNode(2,6));
            }
        };
        ArrayList<GraphNode> inter2 = new ArrayList<>(){
            {
                add(new GraphNode(2,3));
                add(new GraphNode(0,1));
            }
        };
        ArrayList<GraphNode> inter3 = new ArrayList<>(){
            {
                add(new GraphNode(1,3));
                add(new GraphNode(0,6));
            }
        };
        ArrayList<ArrayList<GraphNode>> adj= new ArrayList<>(){
            {
                add(inter1); // for 1st node
                add(inter2); // for 2nd node
                add(inter3); // for 3rd node
            }
        };
        //add final values of adj here.
        DijkstrasAlgoPQ obj = new DijkstrasAlgoPQ();
        int[] res= obj.dijkstra(V,adj,S);

        for(int i=0;i<V;i++){
            System.out.print(res[i]+" ");
        }
        System.out.println();

    }


//User function Template for Java

    //Function to find the shortest distance of all the vertices
    //from the source vertex S.
    /*
    Time Complexity: O( E log(V) ), Where E = Number of edges and V = Number of Nodes.
    Space Complexity: O( |E| + |V| ), Where E = Number of edges and V = Number of Nodes.
     */
    static int[] dijkstra(int V, ArrayList<ArrayList<GraphNode>> adj, int S)
    {
        // Create a priority queue for storing the nodes as a pair {dist, node
        // where dist is the distance from source to the node.
        PriorityQueue<GraphNode> pq =
                new PriorityQueue<>((x, y) -> x.distance - y.distance);

        int []dist = new int[V];

        // Initialising distTo list with a large number to
        // indicate the nodes are unvisited initially.
        // This list contains distance from source to the nodes.
        for(int i = 0;i<V;i++) dist[i] = (int)(1e9);

        // Source initialised with dist=0.
        dist[S] = 0;
        pq.add(new GraphNode(S, 0));

        // Now, pop the minimum distance node first from the min-heap
        // and traverse for all its adjacent nodes.
        while(pq.size() != 0) {
            GraphNode graphNode = pq.poll();
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
                    dist[adjNode] = dis + edgeWeight;
                    pq.add(new GraphNode(adjNode, dist[adjNode]));
                }
            }

        }
        // Return the list containing shortest distances
        // from source to all the nodes.
        return dist;
    }
}
