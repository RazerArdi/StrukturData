import java.util.Scanner;

class Kontak1 {
    protected String nama;
    protected String nomorTelepon;
    Kontak1 next;

    public Kontak1(String nama, String nomorTelepon) {
        this.nama = nama;
        this.nomorTelepon = nomorTelepon;
        this.next = null;
    }
}

class ManajerKontak1 {
    public Kontak1 head;

    public ManajerKontak1() {
        this.head = null;
    }

    public void tambahKontak1(String nama, String nomorTelepon) {
        Kontak1 newContact = new Kontak1(nama, nomorTelepon);
        if (head == null) {
            head = newContact;
        } else {
            Kontak1 current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newContact;
        }
        System.out.println("Kontak berhasil ditambahkan!");
    }

    public void tampilkanKontak() {
        if (head == null) {
            System.out.println("Daftar Kontak kosong.");
            return;
        }
        System.out.println("Daftar Kontak:");
        int index = 1;
        Kontak1 current = head;
        while (current != null) {
            System.out.println(index + ". " + current.nama + "\n-" + current.nomorTelepon + "\t");
            System.out.println("------------- \n");
            current = current.next;
            index++;
        }
    }

    public void cariKontak(String nama) {
        if (head == null) {
            System.out.println("Kontak tidak ditemukan.");
            return;
        }
        Kontak1 current = head;
        boolean ditemukan = false;
        while (current != null) {
            if (current.nama.equalsIgnoreCase(nama)) {
                ditemukan = true;
                System.out.println("Kontak ditemukan!");
                System.out.println("Nama: " + current.nama);
                System.out.println("Nomor Telepon: " + current.nomorTelepon);
                break;
            }
            current = current.next;
        }
        if (!ditemukan) {
            System.out.println("Kontak tidak ditemukan.");
        }
    }
}

public class TUGAS2 {
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
