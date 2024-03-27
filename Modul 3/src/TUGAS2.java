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
        int nomorPemesanan;
        Node next;

        Node(String namaPemesan, int jumlahTiket, int nomorPemesanan) {
            this.namaPemesan = namaPemesan;
            this.jumlahTiket = jumlahTiket;
            this.nomorPemesanan = nomorPemesanan;
            this.next = null;
        }
    }

    public TUGAS2() {
        head = null;
        tail = null;
        jumlah = 0;
    }

    public void tambahAntrian(String namaPemesan, int jumlahTiket) {
        int nomorPemesanan = ++jumlah;
        Node nodeBaru = new Node(namaPemesan, jumlahTiket, nomorPemesanan);
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
        while (saatIni != null) {
            System.out.println("Nomor Pemesanan: " + saatIni.nomorPemesanan + ", Nama Pemesan: " + saatIni.namaPemesan + ", Jumlah Tiket: " + saatIni.jumlahTiket);
            saatIni = saatIni.next;
        }
    }

    public void hapusAntrian() {
        if (head != null) {
            System.out.println("Menghapus Nomor Pemesanan: " + head.nomorPemesanan);
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

        System.out.println("Selamat datang di Sistem Antrian Tiket KAI");
        String masukan;
        do {
            System.out.println("Masukkan 'tambah' untuk menambah pesanan tiket baru, 'tampilkan' untuk menampilkan antrian, 'hapus' untuk menghapus pesanan dari antrian, atau 'keluar' untuk keluar.");
            masukan = scanner.nextLine();

            switch (masukan) {
                case "tambah":
                    System.out.print("Masukkan nama pemesan tiket: ");
                    String namaPemesan = scanner.nextLine();
                    System.out.print("Masukkan jumlah tiket: ");
                    int jumlahTiket = Integer.parseInt(scanner.nextLine());
                    antrian.tambahAntrian(namaPemesan, jumlahTiket);
                    break;
                case "tampilkan":
                    antrian.tampilkanAntrian();
                    break;
                case "hapus":
                    antrian.hapusAntrian();
                    break;
                case "keluar":
                    System.out.println("Keluar dari Sistem Antrian Tiket KAI.");
                    break;
                default:
                    System.out.println("Masukan tidak valid. Silakan coba lagi.");
                    break;
            }
        } while (!masukan.equals("keluar"));

        scanner.close();
    }
}

