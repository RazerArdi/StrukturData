import java.util.*;

public class BFSTraversal {
    private int V; // total number of nodes in the graph
    private LinkedList<Integer> adj[]; // adjacency list array

    // Graph creation
    BFSTraversal(int v) {
        V = v;
        adj = new LinkedList[v];
        for (int i=0; i<v; ++i)
            adj[i] = new LinkedList();
    }

    // Add edges to the graph
    void addEdge(int src,int dest) {
        adj[src].add(dest); // Add w to v's list.
    }

    // BFS algorithm
    void BFS(int s) { // s is the source node
        boolean visited[] = new boolean[V]; // Initialize boolean array for holding the data

        // Create a queue for BFS
        LinkedList<Integer> queue = new LinkedList<Integer>();

        // Mark the current node as visited and enqueue it
        visited[s] = true;
        queue.add(s);

        while (queue.size() != 0) {
            // Dequeue a vertex from queue and print it
            s = queue.poll();
            System.out.print(s + " ");

            // Get all adjacent vertices of the dequeued vertex s
            // If a adjacent has not been visited, then mark it visited and enqueue it
            Iterator<Integer> i = adj[s].listIterator();
            while (i.hasNext()) {
                int n = i.next();
                if (!visited[n]) {
                    visited[n] = true;
                    queue.add(n);
                }
            }
        }
    }

    public static void main(String args[]) {
        BFSTraversal g = new BFSTraversal(6);

        g.addEdge(3, 2);
        g.addEdge(2, 3);
        g.addEdge(2, 4);
        g.addEdge(2, 1);
        g.addEdge(1, 2);
        g.addEdge(1, 5);
        g.addEdge(1, 0);
        g.addEdge(4, 5);
        g.addEdge(5, 0);
        g.addEdge(5, 4);
        g.addEdge(5, 1);
        g.addEdge(4, 0);
        g.addEdge(4, 2);
        g.addEdge(1, 0);
        g.addEdge(0, 5);
        g.addEdge(0, 4);
        g.addEdge(0, 1);

        System.out.println("Following is Breadth First Traversal ");
        g.BFS(3);
    }
}
