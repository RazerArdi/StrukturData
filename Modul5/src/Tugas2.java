import java.util.*;

public class Tugas2 {
    static class Book {
        String isbn;
        String title;

        public Book(String isbn, String title) {
            this.isbn = isbn;
            this.title = title;
        }
    }

    static class Node {
        Book book;
        Node left, right;

        public Node(Book book) {
            this.book = book;
            left = right = null;
        }
    }

    static class BookInventory {
        Node root;

        void addBook(String isbn, String title) {
            root = addRec(root, new Book(isbn, title));
        }

        private Node addRec(Node root, Book book) {
            if (root == null) {
                root = new Node(book);
                return root;
            }
            if (book.isbn.compareTo(root.book.isbn) < 0) {
                root.left = addRec(root.left, book);
            } else if (book.isbn.compareTo(root.book.isbn) > 0) {
                root.right = addRec(root.right, book);
            }
            return root;
        }

        void searchBook(String isbn) {
            Node result = searchRec(root, isbn);
            if (result != null) {
                System.out.println("Buku ditemukan: ISBN " + result.book.isbn + ", Judul " + result.book.title);
            } else {
                System.out.println("Buku dengan ISBN " + isbn + " tidak ditemukan.");
            }
        }

        private Node searchRec(Node root, String isbn) {
            if (root == null || root.book.isbn.equals(isbn)) {
                return root;
            }
            if (root.book.isbn.compareTo(isbn) > 0) {
                return searchRec(root.left, isbn);
            }
            return searchRec(root.right, isbn);
        }

        void printInOrder() {
            System.out.println("Inventaris Buku (terurut berdasarkan ISBN InOrder):");
            inOrderRec(root);
            System.out.println();
        }

        private void inOrderRec(Node root) {
            if (root != null) {
                inOrderRec(root.left);
                System.out.println(root.book.isbn + " " + root.book.title);
                inOrderRec(root.right);
            }
        }

        void printPreOrder() {
            System.out.println("Inventaris Buku (PreOrder):");
            preOrderRec(root);
            System.out.println();
        }

        private void preOrderRec(Node root) {
            if (root != null) {
                System.out.println(root.book.isbn + " " + root.book.title);
                preOrderRec(root.left);
                preOrderRec(root.right);
            }
        }

        void printPostOrder() {
            System.out.println("Inventaris Buku (PostOrder):");
            postOrderRec(root);
            System.out.println();
        }

        private void postOrderRec(Node root) {
            if (root != null) {
                postOrderRec(root.left);
                postOrderRec(root.right);
                System.out.println(root.book.isbn + " " + root.book.title);
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
                        String aa = n.book.isbn + " " + n.book.title;
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
        BookInventory inventory = new BookInventory();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Menu:");
            System.out.println("1. Tambah Buku");
            System.out.println("2. Cari Buku");
            System.out.println("3. Tampilkan Buku (InOrder)");
            System.out.println("4. Tampilkan Buku (PreOrder)");
            System.out.println("5. Tampilkan Buku (PostOrder)");
            System.out.println("6. Tampilkan Visualisasi Pohon Biner");
            System.out.println("7. Keluar");
            System.out.print("Pilih opsi: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Masukkan ISBN: ");
                    String isbn = scanner.nextLine();
                    System.out.print("Masukkan Judul: ");
                    String title = scanner.nextLine();
                    inventory.addBook(isbn, title);
                    break;
                case 2:
                    System.out.print("Masukkan ISBN: ");
                    String searchIsbn = scanner.nextLine();
                    inventory.searchBook(searchIsbn);
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
