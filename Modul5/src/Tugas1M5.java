import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Tugas1M5 {
    static class Platform {
        String platformID;
        String platformName;

        public Platform(String platformID, String platformName) {
            this.platformID = platformID;
            this.platformName = platformName;
        }
    }

    static class Node {
        Platform platform;
        Node left, right;

        public Node(Platform platform) {
            this.platform = platform;
            left = right = null;
        }
    }

    static class PlatformInventory {
        Node root;

        void addPlatform(String platformID, String platformName) {
            root = addRec(root, new Platform(platformID, platformName));
        }

        private Node addRec(Node root, Platform platform) {
            if (root == null) {
                root = new Node(platform);
                return root;
            }
            if (platform.platformID.compareTo(root.platform.platformID) < 0) {
                root.left = addRec(root.left, platform);
            } else if (platform.platformID.compareTo(root.platform.platformID) > 0) {
                root.right = addRec(root.right, platform);
            }
            return root;
        }

        void searchPlatform(String platformID) {
            Node result = searchRec(root, platformID);
            if (result != null) {
                System.out.println("Platform ditemukan: ID " + result.platform.platformID + ", Nama " + result.platform.platformName);
            } else {
                System.out.println("Platform dengan ID " + platformID + " tidak ditemukan.");
            }
        }

        private Node searchRec(Node root, String platformID) {
            if (root == null || root.platform.platformID.equals(platformID)) {
                return root;
            }
            if (root.platform.platformID.compareTo(platformID) > 0) {
                return searchRec(root.left, platformID);
            }
            return searchRec(root.right, platformID);
        }

        void printInOrder() {
            System.out.println("Inventaris Platform (terurut berdasarkan ID Platform InOrder):");
            inOrderRec(root);
            System.out.println();
        }

        private void inOrderRec(Node root) {
            if (root != null) {
                inOrderRec(root.left);
                System.out.println(root.platform.platformID + " " + root.platform.platformName);
                inOrderRec(root.right);
            }
        }

        void printPreOrder() {
            System.out.println("Inventaris Platform (PreOrder):");
            preOrderRec(root);
            System.out.println();
        }

        private void preOrderRec(Node root) {
            if (root != null) {
                System.out.println(root.platform.platformID + " " + root.platform.platformName);
                preOrderRec(root.left);
                preOrderRec(root.right);
            }
        }

        void printPostOrder() {
            System.out.println("Inventaris Platform (PostOrder):");
            postOrderRec(root);
            System.out.println();
        }

        private void postOrderRec(Node root) {
            if (root != null) {
                postOrderRec(root.left);
                postOrderRec(root.right);
                System.out.println(root.platform.platformID + " " + root.platform.platformName);
            }
        }

        void printLevelOrder() {
            System.out.println("Visualisasi Pohon Binernya:");
            if (root == null) {
                System.out.println("Pohon biner kosong.");
                return;
            }

            Queue<Node> queue = new LinkedList<>();
            queue.add(root);

            while (!queue.isEmpty()) {
                int levelSize = queue.size();

                for (int i = 0; i < levelSize; i++) {
                    Node current = queue.poll();
                    assert current != null;
                    System.out.print(current.platform.platformID + " " + current.platform.platformName + " ");

                    if (current.left != null) {
                        queue.add(current.left);
                    }
                    if (current.right != null) {
                        queue.add(current.right);
                    }
                }
                System.out.println();
            }
        }
    }

    public static void main(String[] args) {
        PlatformInventory inventory = new PlatformInventory();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Menu:");
            System.out.println("1. Tambah Platform");
            System.out.println("2. Cari Platform");
            System.out.println("3. Tampilkan Platform (InOrder)");
            System.out.println("4. Tampilkan Platform (PreOrder)");
            System.out.println("5. Tampilkan Platform (PostOrder)");
            System.out.println("6. Tampilkan Visualisasi Pohon Biner");
            System.out.println("7. Keluar");
            System.out.print("Pilih opsi: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Masukkan ID Platform: ");
                    String platformID = scanner.nextLine();
                    System.out.print("Masukkan Nama Platform: ");
                    String platformName = scanner.nextLine();
                    inventory.addPlatform(platformID, platformName);
                    break;
                case 2:
                    System.out.print("Masukkan ID Platform: ");
                    String searchPlatformID = scanner.nextLine();
                    inventory.searchPlatform(searchPlatformID);
                    break;
                case 3:
                    inventory.printInOrder();
                    break;
                case 4:
                    inventory.printPreOrder();
                    break;
                case 5:
                    inventory.printPostOrder();
                    break;
                case 6:
                    inventory.printLevelOrder();
                    break;
                case 7:
                    System.exit(0);
                default:
                    System.out.println("Pilihan tidak valid.");
            }
        }
    }
}
