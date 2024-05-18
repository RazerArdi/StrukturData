class Node {
    int data;
    Node left;
    Node right;
    public Node(int data) {
        this.data = data;
    }
}

public class BinaryTree {
    public Node root;

    public BinaryTree() {
        root = null;
    }

    // Fungsi untuk menambahkan node secara manual ke tree
    public void addRoot(int data) {
        root = new Node(data);
    }

    public void inOrder(Node node) {
        if (node != null) {
            inOrder(node.left);
            System.out.println(node.data + " ");
            inOrder(node.right);
        }
    }

    public void preOrder(Node node) {
        if (node != null) {
            System.out.println(node.data + " ");
            preOrder(node.left);
            preOrder(node.right);
        }
    }

    public void postOrder(Node node) {
        if (node != null) {
            postOrder(node.left);
            postOrder(node.right);
            System.out.println(node.data + " ");
        }
    }

    public static void main(String[] args) {
        BinaryTree tree = new BinaryTree();
        // Menentukan struktur tree secara manual
        tree.addRoot(20); // Root
        tree.root.left = new Node(2); // Menambahkan node ke kiri root
        tree.root.right = new Node(25); // Menambahkan node ke kanan root
        tree.root.left.left = new Node(37); // Menambahkan node ke kiri dari node kiri root
        tree.root.left.right = new Node(12); // Menambahkan node ke kiri dari node kiri root
        tree.root.right.right = new Node(16); // Menambahkan node ke kanan dari node kanan root

        System.out.println("\nPre Order: ");
        tree.preOrder(tree.root);
        System.out.println("\nIn Order: ");
        tree.inOrder(tree.root);
        System.out.println("\nPost Order: ");
        tree.postOrder(tree.root);
    }
}
