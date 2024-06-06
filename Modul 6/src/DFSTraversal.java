import java.util.*;

public class DFSTraversal {
    private LinkedList<Integer> adj[];
    private boolean visited[];

    // Graph creation
    DFSTraversal(int v) {
        adj = new LinkedList[v];
        visited = new boolean[v];
        for (int i = 0; i < v; ++i)
            adj[i] = new LinkedList();
    }

    // Add edges to the graph
    void addEdge(int src, int dest) {
        adj[src].add(dest); // Add w to v's list.
    }

    // DFS algorithm
    void DFS(int vertex) {
        // Mark the current node as visited and print it
        visited[vertex] = true;
        System.out.print(vertex + " ");

        Iterator<Integer> i = adj[vertex].listIterator();
        while (i.hasNext()) {
            int n = i.next();
            if (!visited[n])
                DFS(n);
        }
    }

    public static void main(String args[]) {
        DFSTraversal g = new DFSTraversal(6); // Changed from 4 to 8

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

        System.out.println("Depth First Traversal for the graph:");

        g.DFS(3);
    }
}
