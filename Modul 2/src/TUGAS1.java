import java.util.ArrayList;
import java.util.Scanner;

class Kontak {
    private String nama;
    private String nomorTelepon;

    public Kontak(String nama, String nomorTelepon) {
        this.nama = nama;
        this.nomorTelepon = nomorTelepon;
    }

    public String getNama() {
        return nama;
    }

    public String getNomorTelepon() {
        return nomorTelepon;
    }
}

class ManajerKontak {
    private ArrayList<Kontak> kontak;

    public ManajerKontak() {
        kontak = new ArrayList<>();
    }

    public void tambahKontak(String nama, String nomorTelepon) {
        kontak.add(new Kontak(nama, nomorTelepon));
        System.out.println("Kontak berhasil ditambahkan!\n");
    }

    public void tampilkanKontak() {
        if (kontak.isEmpty()) {
            System.out.println("Daftar Kontak kosong.");
            return;
        }
        System.out.println("Daftar Kontak:");
        int indeks = 1;
        for (Kontak kontak : kontak) {
            System.out.println(indeks + ". " + kontak.getNama() + " - " + kontak.getNomorTelepon());
            indeks++;
        }
    }

    public void cariKontak(String nama) {
        boolean ditemukan = false;
        for (Kontak kontak : kontak) {
            if (kontak.getNama().equalsIgnoreCase(nama)) {
                ditemukan = true;
                System.out.println("Kontak ditemukan!");
                System.out.println("Nama: " + kontak.getNama());
                System.out.println("Nomor Telepon: " + kontak.getNomorTelepon());
                break;
            }
        }
        if (!ditemukan) {
            System.out.println("Kontak tidak ditemukan.");
        }
    }
}

public class TUGAS1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ManajerKontak manajerKontak = new ManajerKontak();

        System.out.println("Selamat datang di Manajemen Kontak!");
        int pilihan;
        do {
            System.out.println("1. Tambah Kontak\n2. Tampilkan Kontak\n3. Cari Kontak\n4. Keluar");
            System.out.print("Pilih menu (1/2/3/4): ");
            pilihan = scanner.nextInt();
            scanner.nextLine();

            switch (pilihan) {
                case 1:
                    System.out.print("Masukkan nama kontak: ");
                    String nama = scanner.nextLine();
                    System.out.print("Masukkan nomor telepon: ");
                    String nomorTelepon = scanner.nextLine();
                    manajerKontak.tambahKontak(nama, nomorTelepon);
                    break;
                case 2:
                    manajerKontak.tampilkanKontak();
                    break;
                case 3:
                    System.out.print("Masukkan nama kontak yang ingin dicari: ");
                    String namaCari = scanner.nextLine();
                    manajerKontak.cariKontak(namaCari);
                    break;
                case 4:
                    System.out.println("Terima kasih telah menggunakan Manajemen Kontak.");
                    break;
                default:
                    System.out.println("Pilihan tidak valid. Silakan pilih 1, 2, 3, atau 4.");
            }
        } while (pilihan != 4);

        scanner.close();
    }
}
