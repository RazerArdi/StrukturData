package SingleLInkedList;

class Node {
    int data;
    Node next;

    public Node(int data) {
        this.data = data;
    }
}

public class DasarLinkedList {
    Node head; // Node pertama di dalam linked list

    public void insertSort(int data) {
        Node newNode = new Node(data); // Membuat node baru dengan data yang diberikan
        if (head == null || head.data >= newNode.data) {
            newNode.next = head;
            head = newNode;
        } else {
            Node current = head;
            while (current.next != null && current.next.data < newNode.data) {
                current = current.next;
            }
            newNode.next = current.next;
            current.next = newNode;
        }
    }

    public void display() {
        Node current = head;
        while (current != null) {
            System.out.print(current.data + " ");
            current = current.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        DasarLinkedList list = new DasarLinkedList();
        list.insertSort(10);
        list.insertSort(30);
        list.insertSort(20);
        list.insertSort(40);

        list.display(); // Output yang diharapkan: 10 20 30 40
    }
}
