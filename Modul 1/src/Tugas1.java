import java.util.Scanner;

enum JenisBarang {
    SANDANG,
    PANGAN,
    PAPAN
}

class Barang<T, U> {
    private T nama;
    private U harga;
    private JenisBarang jenis;

    public Barang(T nama, U harga, JenisBarang jenis) {
        this.nama = nama;
        this.harga = harga;
        this.jenis = jenis;
    }

    public T getNama() {
        return nama;
    }

    public U getHarga() {
        return harga;
    }

    public JenisBarang getJenis() {
        return jenis;
    }
}

public class Tugas1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Masukkan Nama: ");
        String nama = scanner.nextLine();

        System.out.println("Masukkan Harga");
        Double harga = scanner.nextDouble();

        System.out.println("Pilih jenis barang");
        for (JenisBarang jenis : JenisBarang.values()) {
            System.out.println(jenis.ordinal() + " . " + jenis);
        }

        System.out.println("Masukkan pilihan: ");
        int pilihan = scanner.nextInt();

        JenisBarang[] JB = JenisBarang.values();

        Barang<String, Double> barang = new Barang<>(nama, harga, JB[pilihan]);
        System.out.println("\nInformasi Barang:");
        System.out.println("Nama: " + barang.getNama());
        System.out.println("Harga: " + barang.getHarga());
        System.out.println("Jenis: " + barang.getJenis());
    }
}