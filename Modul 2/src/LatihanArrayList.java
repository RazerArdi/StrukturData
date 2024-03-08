import java.util.ArrayList;

public class LatihanArrayList {

    public static void main(String[] args) {
        ArrayList<String> daftarNama = new ArrayList<>();

        daftarNama.add("Iqbal");
        daftarNama.add("Izza");
        daftarNama.add("Fathony");
        daftarNama.add("Azizki");

        System.out.println("Daftar Nama:");
        for (String nama : daftarNama) {
            System.out.println(nama);
        }

        System.out.println("Elemen pada indeks 2: " + daftarNama.get(2));

        daftarNama.set(1, "Paijo");

        System.out.println("Daftar Nama setelah perubahan:");
        for (String nama : daftarNama) {
            System.out.println(nama);
        }

        daftarNama.remove(0);

        System.out.println("Daftar Nama setelah penghapusan:");
        for (String nama : daftarNama) {
            System.out.println(nama);
        }

        System.out.println("Jumlah elemen dalam ArrayList: " + daftarNama.size());

        System.out.println("Apakah ArrayList kosong? " + daftarNama.isEmpty());
    }
}
