import java.util.*;

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

        void printVisualTree() {
            List<List<String>> lines = new ArrayList<>();
            List<Node> level = new ArrayList<>();
            List<Node> next = new ArrayList<>();
            level.add(root);
            int nn = 1;

            int widest = 0;

            while (nn != 0) {
                List<String> line = new ArrayList<>();

                nn = 0;

                for (Node n : level) {
                    if (n == null) {
                        line.add(null);

                        next.add(null);
                        next.add(null);
                    } else {
                        String aa = n.platform.platformID + " " + n.platform.platformName;
                        line.add(aa);
                        if (aa.length() > widest) widest = aa.length();

                        next.add(n.left);
                        next.add(n.right);

                        if (n.left != null) nn++;
                        if (n.right != null) nn++;
                    }
                }

                if (widest % 2 == 1) widest++;

                lines.add(line);

                List<Node> tmp = level;
                level = next;
                next = tmp;
                next.clear();
            }

            int perpiece = lines.get(lines.size() - 1).size() * (widest + 4);
            for (int i = 0; i < lines.size(); i++) {
                List<String> line = lines.get(i);
                int hpw = (int) Math.floor(perpiece / 2f) - 1;

                if (i > 0) {
                    for (int j = 0; j < line.size(); j++) {

                        char c = ' ';
                        if (j % 2 == 1) {
                            if (line.get(j - 1) != null) {
                                c = (line.get(j) != null) ? '┴' : '┘';
                            } else {
                                if (j < line.size() && line.get(j) != null) c = '└';
                            }
                        }
                        System.out.print(c);

                        if (line.get(j) == null) {
                            for (int k = 0; k < perpiece - 1; k++) {
                                System.out.print(" ");
                            }
                        } else {

                            for (int k = 0; k < hpw; k++) {
                                System.out.print(j % 2 == 0 ? " " : "─");
                            }
                            System.out.print(j % 2 == 0 ? "┌" : "┐");
                            for (int k = 0; k < hpw; k++) {
                                System.out.print(j % 2 == 0 ? "─" : " ");
                            }
                        }
                    }
                    System.out.println();
                }

                for (int j = 0; j < line.size(); j++) {

                    String f = line.get(j);
                    if (f == null) f = "";
                    int gap1 = (int) Math.ceil(perpiece / 2f - f.length() / 2f);
                    int gap2 = (int) Math.floor(perpiece / 2f - f.length() / 2f);

                    for (int k = 0; k < gap1; k++) {
                        System.out.print(" ");
                    }
                    System.out.print(f);
                    for (int k = 0; k < gap2; k++) {
                        System.out.print(" ");
                    }
                }
                System.out.println();

                perpiece /= 2;
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
            scanner.nextLine();

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
                    inventory.printVisualTree();
                    break;
                case 7:
                    System.exit(0);
                default:
                    System.out.println("Pilihan tidak valid.");
            }
        }
    }
}
