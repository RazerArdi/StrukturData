import java.util.Scanner;

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
            System.out.println("6. Keluar");
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
                    System.exit(0);
                default:
                    System.out.println("Pilihan tidak valid.");
            }
        }
    }
}