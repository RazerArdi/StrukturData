import java.util.*;

public class TUGAS2_DFS_BFS {
    private Map<Integer, List<Integer>> graph;

    public TUGAS2_DFS_BFS() {
        this.graph = new HashMap<>();
    }

    public void addEdge(int source, int destination) {
        if (!graph.containsKey(source)) {
            graph.put(source, new ArrayList<>());
        }
        graph.get(source).add(destination);
    }

    public List<Integer> dfs(int start, int end) {
        Set<Integer> visited = new HashSet<>();
        List<Integer> path = new ArrayList<>();
        dfsHelper(start, end, visited, path);
        return path;
    }

    private boolean dfsHelper(int current, int end, Set<Integer> visited, List<Integer> path) {
        visited.add(current);
        path.add(current);

        if (current == end) {
            return true;
        }

        List<Integer> neighbors = graph.getOrDefault(current, new ArrayList<>());
        for (int neighbor : neighbors) {
            if (!visited.contains(neighbor)) {
                if (dfsHelper(neighbor, end, visited, path)) {
                    return true;
                }
            }
        }

        path.remove(path.size() - 1);
        return false;
    }

    public List<Integer> bfs(int start, int end) {
        Queue<Integer> queue = new LinkedList<>();
        Set<Integer> visited = new HashSet<>();
        Map<Integer, Integer> parent = new HashMap<>();
        List<Integer> path = new ArrayList<>();

        queue.offer(start);
        visited.add(start);
        parent.put(start, -1);

        while (!queue.isEmpty()) {
            int current = queue.poll();
            if (current == end) {
                break;
            }

            List<Integer> neighbors = graph.getOrDefault(current, new ArrayList<>());
            for (int neighbor : neighbors) {
                if (!visited.contains(neighbor)) {
                    queue.offer(neighbor);
                    visited.add(neighbor);
                    parent.put(neighbor, current);
                }
            }
        }

        int current = end;
        while (current != -1) {
            path.add(current);
            current = parent.get(current);
        }

        Collections.reverse(path);
        return path;
    }

    public static void main(String[] args) {
        TUGAS2_DFS_BFS graph = new TUGAS2_DFS_BFS();

        graph.addEdge(5, 9);
        graph.addEdge(5, 2);
        graph.addEdge(5, 6);
        graph.addEdge(2, 5);
        graph.addEdge(2, 6);
        graph.addEdge(6, 1);
        graph.addEdge(1, 5);
        graph.addEdge(1, 3);
        graph.addEdge(11, 1);
        graph.addEdge(7, 11);
        graph.addEdge(7, 8);
        graph.addEdge(8, 4);
        graph.addEdge(3, 5);
        graph.addEdge(3, 7);
        graph.addEdge(3, 4);
        graph.addEdge(4, 10);

        int start = 5;
        int end = 10;

        // DFS
        List<Integer> dfsPath = graph.dfs(start, end);
        System.out.println("DFS Path from " + start + " to " + end + ": " + dfsPath);

        // BFS
        List<Integer> bfsPath = graph.bfs(start, end);
        System.out.println("BFS Path from " + start + " to " + end + ": " + bfsPath);
    }
}
