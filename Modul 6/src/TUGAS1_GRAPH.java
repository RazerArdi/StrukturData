import java.util.*;

public class TUGAS1_GRAPH {
    private Map<Character, Node> nodes;

    class Node {
        char value;
        List<Node> neighbors;

        public Node(char value) {
            this.value = value;
            this.neighbors = new ArrayList<>();
        }

        public void addNeighbor(Node neighbor) {
            neighbors.add(neighbor);
        }

        public List<Node> getNeighbors() {
            return neighbors;
        }

        @Override
        public String toString() {
            return Character.toString(value);
        }
    }

    public TUGAS1_GRAPH() {
        nodes = new HashMap<>();
        initializeGraph();
    }

    private void initializeGraph() {
        for (char c = 'A'; c <= 'L'; c++) {
            nodes.put(c, new Node(c));
        }

        nodes.get('A').addNeighbor(nodes.get('B'));
        nodes.get('A').addNeighbor(nodes.get('C'));
        nodes.get('B').addNeighbor(nodes.get('D'));
        nodes.get('C').addNeighbor(nodes.get('E'));
        nodes.get('D').addNeighbor(nodes.get('F'));
        nodes.get('E').addNeighbor(nodes.get('F'));
        nodes.get('F').addNeighbor(nodes.get('G'));
        nodes.get('G').addNeighbor(nodes.get('H'));
        nodes.get('H').addNeighbor(nodes.get('I'));
        nodes.get('I').addNeighbor(nodes.get('J'));
        nodes.get('J').addNeighbor(nodes.get('K'));
        nodes.get('K').addNeighbor(nodes.get('L'));
        nodes.get('L').addNeighbor(nodes.get('A'));
        nodes.get('F').addNeighbor(nodes.get('I'));
    }

    public void visualizeGraph() {
        for (char c = 'A'; c <= 'L'; c++) {
            Node node = nodes.get(c);
            StringBuilder sb = new StringBuilder(node + " -> ");
            for (Node neighbor : node.getNeighbors()) {
                sb.append(neighbor).append(" -> ");
            }
            sb.append("\n");
            System.out.print(sb.toString());
        }
    }

    public void printNotation() {
        System.out.println("Notasi (G = V, E)");
        System.out.println("V (Vertices) = " + nodes.keySet());
        System.out.println("E (Edges) = " + getEdges());
    }

    private List<String> getEdges() {
        List<String> edges = new ArrayList<>();
        for (Node node : nodes.values()) {
            for (Node neighbor : node.getNeighbors()) {
                edges.add("(" + node + ", " + neighbor + ")");
            }
        }
        return edges;
    }

    public void printAdjacencyMatrix() {
        int n = nodes.size();
        int[][] adjacencyMatrix = new int[n][n];
        List<Character> nodeList = new ArrayList<>(nodes.keySet());
        Collections.sort(nodeList);

        Map<Character, Integer> indexMap = new HashMap<>();
        for (int i = 0; i < nodeList.size(); i++) {
            indexMap.put(nodeList.get(i), i);
        }

        for (char nodeValue : nodeList) {
            Node node = nodes.get(nodeValue);
            int rowIndex = indexMap.get(nodeValue);
            for (Node neighbor : node.getNeighbors()) {
                int colIndex = indexMap.get(neighbor.value);
                adjacencyMatrix[rowIndex][colIndex] = 1;
            }
        }

        System.out.println("Adjacency Matrix:");
        System.out.print("  ");
        for (char nodeValue : nodeList) {
            System.out.print(nodeValue + " ");
        }
        System.out.println();
        for (int i = 0; i < n; i++) {
            System.out.print(nodeList.get(i) + " ");
            for (int j = 0; j < n; j++) {
                System.out.print(adjacencyMatrix[i][j] + " ");
            }
            System.out.println();
        }
    }

    public void printDegreeInfo() {
        Map<Character, Integer> inDegree = new HashMap<>();
        Map<Character, Integer> outDegree = new HashMap<>();

        for (char c = 'A'; c <= 'L'; c++) {
            inDegree.put(c, 0);
            outDegree.put(c, nodes.get(c).getNeighbors().size());
        }


        for (Node node : nodes.values()) {
            for (Node neighbor : node.getNeighbors()) {
                inDegree.put(neighbor.value, inDegree.get(neighbor.value) + 1);
            }
        }

        int totalEdges = 0;

        System.out.println("Degree Information:");
        for (char c = 'A'; c <= 'L'; c++) {
            int inDeg = inDegree.get(c);
            int outDeg = outDegree.get(c);
            System.out.println("Node " + c + ": In-Degree = " + inDeg + ", Out-Degree = " + outDeg);
            totalEdges += outDeg;
        }

        int totalDegree = totalEdges;

        System.out.println("Total Number of Degrees: " + totalDegree);
    }

    public static void main(String[] args) {
        TUGAS1_GRAPH graph = new TUGAS1_GRAPH();
        System.out.println("Visualisasi:");
        graph.visualizeGraph();
        System.out.println();
        graph.printNotation();
        System.out.println();
        graph.printAdjacencyMatrix();
        System.out.println();
        graph.printDegreeInfo();
    }
}
