/*
1. Method visitURL(), program harus dapat mengunjungi URL baru yang dimasukkan pengguna dan
menyimpannya dalam riwayat navigasi.
2. Method back(), program harus dapat mengembalikan pengguna ke URL sebelumnya dalam riwayat
navigasi.
3. Method forward(), program harus dapat mengarahkan pengguna ke URL berikutnya dalam riwayat
navigasi.
4. Method getCurrentURL(), program harus menampilkan URL yang sedang diakses pengguna.

    Buat Stack secara manual tidak diperkenankan menggunakan library
 */

public class TUGAS1 {
    private Node current;
    private Node head;
    private Node tail;

    private class Node {
        String url;
        Node prev;
        Node next;

        Node(String url) {
            this.url = url;
        }
    }

    public TUGAS1() {
        this.head = new Node(null);
        this.tail = new Node(null);
        head.next = tail;
        tail.prev = head;
        this.current = head;
    }

    public void visitURL(String url) {
        Node newNode = new Node(url);
        newNode.prev = current;
        newNode.next = tail;
        current.next = newNode;
        tail.prev = newNode;
        current = newNode;
    }

    public void back() {
        if (current != head && current.prev != head) {
            current = current.prev;
        }
    }

    public void forward() {
        if (current != tail && current.next != tail) {
            current = current.next;
        } else if (current.next == tail && head.next != tail) {
            current = head.next;
        }
    }

    public String getCurrentURL() {
        return current.url;
    }

    public static void main(String[] args) {
        TUGAS1 browserHistory = new TUGAS1();
        browserHistory.visitURL("https://www.nasa.gov/");
        browserHistory.visitURL("https://www.google.com/");
        browserHistory.visitURL("https://www.nasa.gov/smallspacecraft/technology-demonstrations/");

        System.out.println("Current URL: " + browserHistory.getCurrentURL());
        browserHistory.forward();
        System.out.println("Current URL: " + browserHistory.getCurrentURL());
        browserHistory.back();
        System.out.println("Current URL: " + browserHistory.getCurrentURL());
        browserHistory.forward();
        System.out.println("Current URL: " + browserHistory.getCurrentURL());
        browserHistory.forward();
        System.out.println("Current URL: " + browserHistory.getCurrentURL());
        browserHistory.forward();
        System.out.println("Current URL: " + browserHistory.getCurrentURL());
        browserHistory.forward();
        System.out.println("Current URL: " + browserHistory.getCurrentURL());
        browserHistory.back();
        System.out.println("Current URL: " + browserHistory.getCurrentURL());

    }
}
