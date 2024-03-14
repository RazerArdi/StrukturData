public class DasarLinkedList {
    Node first; // Node pertama di dalam linked list
    Node last; // Node terakhir di dalam linked list

    // Method untuk menyisipkan data baru ke dalam linked list sesuai urutan ascending
    public void insertSort(int data) {
        Node newNode = new Node(data); // Membuat node baru dengan data yang diberikan
        if (first == null || first.data >= newNode.data) { // Jika linked list kosong atau data baru lebih kecil dari atau sama dengan data di first
            newNode.next = first; // Menyisipkan node baru di depan first
            first = newNode; // Mengubah first menjadi node baru
            if (last == null) { // Jika linked list sebelumnya kosong, node baru juga menjadi last
                last = newNode;
            }
        } else {
            Node current = first; // Pointer untuk melacak posisi saat ini dalam linked list
            // Melakukan iterasi sampai menemukan posisi yang tepat untuk menyisipkan data baru
            while (current.next != null && current.next.data < newNode.data) {
                current = current.next; // Bergerak ke node berikutnya
            }
            // Menyisipkan node baru di antara node saat ini dan node berikutnya
            newNode.next = current.next;
            current.next = newNode;
            if (newNode.next == null) { // Jika node baru adalah yang terakhir, perbarui last
                last = newNode;
            }
        }
    }

    // Method untuk menampilkan isi linked list
    public void display() {
        Node current = first; // Pointer untuk melacak node saat ini
        while (current != null) { // Melakukan iterasi selama masih ada node yang tersedia
            System.out.print(current.data + " "); // Menampilkan data node saat ini
            current = current.next; // Bergerak ke node berikutnya
        }
        System.out.println(); // Mencetak baris baru setelah menampilkan semua data
        // Menampilkan informasi tentang node pertama dan terakhir
        if (first != null && last != null) {
            System.out.println("First: " + first.data);
            System.out.println("Last: " + last.data);
        }
    }

    public static void main(String[] args) {
        DasarLinkedList list = new DasarLinkedList(); // Membuat objek linked list
        // Menyisipkan beberapa data ke dalam linked list dengan menggunakan method insertSort
        list.insertSort(10);
        list.insertSort(30);
        list.insertSort(20);
        list.insertSort(40);

        list.display(); // Menampilkan isi linked list setelah data-data disisipkan
    }
}

class Node {
    int data; // Data yang disimpan dalam node
    Node next; // Referensi ke node berikutnya dalam linked list

    public Node(int data) {
        this.data = data; // Menginisialisasi data pada node
    }
}
