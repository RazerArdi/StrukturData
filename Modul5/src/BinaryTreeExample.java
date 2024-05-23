import java.util.Scanner;

class Node12 {
    String data;
    Node12 left, right;

    public Node12(String item) {
        data = item;
        left = right = null;
    }
}

class BinaryTree {
    Node12 root;

    // PreOrder Traversal
    void printPreOrder(Node12 node) {
        if (node == null) return;
        System.out.print(node.data + " ");
        printPreOrder(node.left);
        printPreOrder(node.right);
    }

    // InOrder Traversal
    void printInOrder(Node12 node) {
        if (node == null) return;
        printInOrder(node.left);
        System.out.print(node.data + " ");
        printInOrder(node.right);
    }

    // PostOrder Traversal
    void printPostOrder(Node12 node) {
        if (node == null) return;
        printPostOrder(node.left);
        printPostOrder(node.right);
        System.out.print(node.data + " ");
    }

    Node12 insertLevelOrder(String[] arr, Node12 root, int i) {
        if (i < arr.length) {
            Node12 temp = new Node12(arr[i]);
            root = temp;
            root.left = insertLevelOrder(arr, root.left, 2 * i + 1);
            root.right = insertLevelOrder(arr, root.right, 2 * i + 2);
        }
        return root;
    }

    Node12 find(Node12 node, String key) {
        if (node == null || node.data.equals(key)) return node;
        Node12 leftResult = find(node.left, key);
        if (leftResult != null) return leftResult;
        return find(node.right, key);
    }

    Node12 findParent(Node12 node, Node12 child) {
        if (node == null) return null;
        if (node.left == child || node.right == child) return node;
        Node12 leftParent = findParent(node.left, child);
        if (leftParent != null) return leftParent;
        return findParent(node.right, child);
    }

    void findPredSucc(Node12 root, Node12 target) {
        if (root == null) return;

        // Find predecessor
        Node12 pred = null;
        Node12 curr = root;
        while (curr != null && !curr.data.equals(target.data)) {
            if (target.data.compareTo(curr.data) > 0) {
                pred = curr;
                curr = curr.right;
            } else {
                curr = curr.left;
            }
        }

        // Find successor
        Node12 succ = null;
        curr = root;
        while (curr != null && !curr.data.equals(target.data)) {
            if (target.data.compareTo(curr.data) < 0) {
                succ = curr;
                curr = curr.left;
            } else {
                curr = curr.right;
            }
        }

        System.out.println("Predecessor: " + (pred != null ? pred.data : "None"));
        System.out.println("Successor: " + (succ != null ? succ.data : "None"));
    }
}

public class BinaryTreeExample {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Masukkan elemen Binary Tree (dipisahkan dengan spasi):");
        String input = scanner.nextLine();
        String[] elements = input.split(" ");

        BinaryTree tree = new BinaryTree();
        tree.root = tree.insertLevelOrder(elements, tree.root, 0);

        System.out.println("PreOrder Traversal:");
        tree.printPreOrder(tree.root);
        System.out.println();

        System.out.println("InOrder Traversal:");
        tree.printInOrder(tree.root);
        System.out.println();

        System.out.println("PostOrder Traversal:");
        tree.printPostOrder(tree.root);
        System.out.println();

        System.out.println("Masukkan elemen untuk menemukan predecessor dan successor:");
        String key = scanner.nextLine();
        Node12 target = tree.find(tree.root, key);
        if (target != null) {
            tree.findPredSucc(tree.root, target);
        } else {
            System.out.println("Elemen tidak ditemukan di dalam tree.");
        }

        System.out.println("Masukkan elemen untuk menemukan ancestor:");
        String childKey = scanner.nextLine();
        Node12 childNode = tree.find(tree.root, childKey);
        if (childNode != null) {
            Node12 parent = tree.findParent(tree.root, childNode);
            if (parent != null) {
                System.out.println("Ancestor (Parent) dari " + childKey + " adalah: " + parent.data);
            } else {
                System.out.println(childKey + " adalah root dan tidak memiliki ancestor.");
            }
        } else {
            System.out.println("Elemen tidak ditemukan di dalam tree.");
        }
    }
}
