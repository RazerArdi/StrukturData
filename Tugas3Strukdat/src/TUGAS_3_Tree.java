import java.util.ArrayList;
import java.util.List;



/*
1. Method untuk mengembalikan jumlah node.
2. Method find1(int data) untuk memeriksa apakah data ada dalam tree.
3. Method find2(int data) untuk menemukan data dan menampilkan level node beserta node-node yang dikunjungi.
 */

class TUGAS_3_Tree {
    class Node {
        int data;
        Node left, right;

        public Node(int item) {
            data = item;
            left = right = null;
        }
    }

    Node root;

    TUGAS_3_Tree() {
        root = null;
    }

    // Method untuk menambahkan node baru
    void insert(int data) {
        root = insertRec(root, data);
    }

    Node insertRec(Node root, int data) {
        if (root == null) {
            root = new Node(data);
            return root;
        }

        if (data < root.data)
            root.left = insertRec(root.left, data);
        else if (data > root.data)
            root.right = insertRec(root.right, data);

        return root;
    }

    // Method untuk mengembalikan jumlah node
    int countNodes() {
        return countNodesRec(root);
    }

    int countNodesRec(Node root) {
        if (root == null) {
            return 0;
        }
        return 1 + countNodesRec(root.left) + countNodesRec(root.right);
    }

    // Method find1(int data) untuk memeriksa apakah data ada dalam tree.
    void find1(int data) {
        if (FindHist(root, data)) {
            System.out.println("data ada");
        } else {
            System.out.println("data tidak ada");
        }
    }

    boolean FindHist(Node root, int data) {
        if (root == null) {
            return false;
        }

        if (root.data == data) {
            return true;
        }

        if (data < root.data) {
            return FindHist(root.left, data);
        } else {
            return FindHist(root.right, data);
        }
    }

    /*
     Method find2(int data) untuk menemukan data dan
     menampilkan level node beserta node-node yang dikunjungi.
     */
    void find2(int data) {
        List<Integer> visitedNodes = new ArrayList<>();
        int level = find(root, data, 1, visitedNodes);

        System.out.println("Node yang dikunjungi: " + visitedNodes);

        if (level != -1) {
            System.out.println("data ditemukan pada level: " + level);
        } else {
            System.out.println("data tidak ada");
        }
    }

    int find(Node root, int data, int level, List<Integer> visitedNodes) {
        if (root == null) {
            return -1;
        }

        visitedNodes.add(root.data);

        if (root.data == data) {
            return level;
        }

        if (data < root.data) {
            return find(root.left, data, level + 1, visitedNodes);
        } else {
            return find(root.right, data, level + 1, visitedNodes);
        }
    }

    // Main method untuk pengujian
    public static void main(String[] args) {
        TUGAS_3_Tree tree = new TUGAS_3_Tree();

        tree.insert(50);
        tree.insert(30);
        tree.insert(20);
        tree.insert(40);
        tree.insert(70);
        tree.insert(60);
        tree.insert(80);

        System.out.println("Jumlah node: " + tree.countNodes());

        tree.find1(40);
        tree.find1(100);

        tree.find2(40);
        tree.find2(100);
    }
}
