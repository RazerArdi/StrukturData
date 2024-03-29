/*
Buat Queue secara manual tidak diperkenankan menggunakan library

1. Program harus meminta input pengguna untuk jumlah tiket yang ingin dipesan dan setiap tiket harus
mencakup informasi seperti nama pemesan dan jumlah tiket.
2. Setiap pemesanan harus dimasukkan ke dalam Queue.
3. Program harus dapat menambahkan, menampilkan, dan menghapus dari Queue.
4. Setiap pemesanan harus diberi nomor unik untuk identifikasi.

 */
import java.util.Scanner;

public class TUGAS2 {
    private Node head;
    private Node tail;
    private int jumlah;

    private class Node {
        String namaPemesan;
        int jumlahTiket;
        int ID_Pesanan;
        Node next;

        Node(String namaPemesan, int jumlahTiket, int ID_Pesanan) {
            this.namaPemesan = namaPemesan;
            this.jumlahTiket = jumlahTiket;
            this.ID_Pesanan = ID_Pesanan;
            this.next = null;
        }
    }

    public TUGAS2() {
        head = null;
        tail = null;
        jumlah = 0;
    }

    public void tambahAntrian(String namaPemesan, int jumlahTiket) {
        int ID_Pesanan = ++jumlah;
        Node nodeBaru = new Node(namaPemesan, jumlahTiket, ID_Pesanan);
        if (tail != null) {
            tail.next = nodeBaru;
        }
        tail = nodeBaru;
        if (head == null) {
            head = nodeBaru;
        }
    }

    public void tampilkanAntrian() {
        Node saatIni = head;
        if (saatIni == null) {
            System.out.println("Antrian kosong.");
            return;
        }
        while (saatIni != null) {
            System.out.println("ID Pesanan: " + saatIni.ID_Pesanan + ", Nama Pemesan: " + saatIni.namaPemesan + ", Jumlah Tiket: " + saatIni.jumlahTiket);
            saatIni = saatIni.next;
        }
    }


    public void hapusAntrian() {
        if (head != null) {
            System.out.println("Menghapus Nomor Pemesanan: " + head.ID_Pesanan);
            head = head.next;
            if (head == null) {
                tail = null;
            }
        } else {
            System.out.println("Antrian kosong.");
        }
    }


    public static void main(String[] args) {
        TUGAS2 antrian = new TUGAS2();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Author: Bayu Ardiyansyah" +
                "Sistem tiket KAI menggunakan Struktur data Queue");
        String masukan;
        do {
            System.out.println("\n1 == tambah, untuk menambah pesanan tiket baru, \n2 == tampilkan, untuk menampilkan antrian " +
                    "\n3 == hapus, untuk menghapus pesanan dari antrian,\n4 == keluar, untuk Out.");
            System.out.println("\nMasukkan pilihan: ");
            masukan = scanner.nextLine();

            switch (masukan) {
                case "1":
                    System.out.print("Masukkan nama pemesan tiket: ");
                    String namaPemesan = scanner.nextLine();
                    System.out.print("Masukkan jumlah tiket: ");
                    int jumlahTiket = Integer.parseInt(scanner.nextLine());
                    antrian.tambahAntrian(namaPemesan, jumlahTiket);
                    break;
                case "2":
                    antrian.tampilkanAntrian();
                    break;
                case "3":
                    antrian.hapusAntrian();
                    break;
                case "4":
                    System.out.println("Keluar dari Sistem Antrian Tiket KAI.");
                    break;
                default:
                    System.out.println("Masukan tidak valid. Silakan coba lagi.");
                    break;
            }
        } while (!masukan.equals("4"));

        scanner.close();
    }
}
