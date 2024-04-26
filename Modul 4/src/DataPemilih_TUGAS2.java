import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class DataPemilih_TUGAS2 {
    private HashMap<String, Integer> candidates;
    private HashMap<String, String> users;
    private HashMap<String, ArrayList<String>> userDetails;
    private String loggedInUserEmail;
    private final String dataFilePath = "DataPemilih.txt";

    public DataPemilih_TUGAS2() {
        this.candidates = new HashMap<>();
        this.users = new HashMap<>();
        this.userDetails = new HashMap<>();
        this.loggedInUserEmail = "";
        loadUserDataFromFile();
    }

    private void loadUserDataFromFile() {
        try {
            File file = new File(dataFilePath);
            if (file.exists()) {
                BufferedReader reader = new BufferedReader(new FileReader(file));
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] data = line.split(",");
                    String email = data[0];
                    String password = data[1];
                    String nama = data[2];
                    String nik = data[3];

                    users.put(email, password);
                    ArrayList<String> userInfo = new ArrayList<>();
                    userInfo.add(nama);
                    userInfo.add(nik);
                    userDetails.put(email, userInfo);
                }
                reader.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void saveUserDataToFile() {
        try {
            FileWriter writer = new FileWriter(dataFilePath);
            for (String email : users.keySet()) {
                String password = users.get(email);
                ArrayList<String> userInfo = userDetails.get(email);
                String nama = userInfo.get(0);
                String nik = userInfo.get(1);

                writer.write(email + "," + password + "," + nama + "," + nik + "\n");
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addCandidate(String name) {
        candidates.put(name, 0);
    }

    public void register() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Masukkan email: ");
        String email = scanner.nextLine();
        System.out.print("Masukkan password: ");
        String password = scanner.nextLine();
        System.out.print("Masukkan nama: ");
        String nama = scanner.nextLine();
        System.out.print("Masukkan NIK: ");
        String nik = scanner.nextLine();

        if ((email.endsWith("@gmail.com") || email.endsWith("@webmail.umm.ac.id") || email.endsWith("@outlook.com"))
                && !users.containsKey(email) && !userDetails.containsValue(nik)) {
            users.put(email, password);
            ArrayList<String> userInfo = new ArrayList<>();
            userInfo.add(nama);
            userInfo.add(nik);
            userDetails.put(email, userInfo);
            System.out.println("Berhasil Mendaftar");
            saveUserDataToFile();
            loginOrRegister();
        } else {
            System.out.println("Gagal Login");
            loginOrRegister();
        }
    }

    public void login() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Masukkan email: ");
        String email = scanner.nextLine();
        System.out.print("Masukkan password: ");
        String password = scanner.nextLine();

        if ((users.containsKey(email) && users.get(email).equals(password))
                && (email.endsWith("@gmail.com") || email.endsWith("@webmail.umm.ac.id") || email.endsWith("@outlook.com"))) {
            System.out.println("Login Berhasil");
            ArrayList<String> userInfo = userDetails.get(email);
            System.out.println("Nama: " + userInfo.get(0));
            System.out.println("NIK: " + userInfo.get(1));
            loggedInUserEmail = email;
            pemilihan();
        } else {
            System.out.println("Gagal Login");
            loginOrRegister();
        }
    }

    public void pemilihan() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Selamat datang di sistem voting:");
        System.out.println("Pilih kandidat yang ingin Anda dukung:");
        for (String candidate : candidates.keySet()) {
            System.out.println("- " + candidate);
        }
        System.out.print("Masukkan nama kandidat (atau ketik 'selesai' atau 'keluar' untuk keluar): ");
        String input = scanner.nextLine();

        if (!input.equalsIgnoreCase("selesai") && !input.equalsIgnoreCase("keluar")) {
            vote(input);
        } else if (input.equalsIgnoreCase("selesai")) {
            displayResults();
        } else if (input.equalsIgnoreCase("keluar")) {
            logout();
        }
    }

    public void vote(String candidateName) {
        if (candidates.containsKey(candidateName)) {
            int currentVotes = candidates.get(candidateName);
            candidates.put(candidateName, currentVotes + 1);
            System.out.println("Terima kasih, suara Anda telah direkam.");
            pemilihan();
        } else {
            System.out.println("Kandidat tidak valid.");
            pemilihan();
        }
    }

    public void logout() {
        loggedInUserEmail = "";
        loginOrRegister();
    }

    public void displayResults() {
        System.out.println("Hasil Voting:");
        for (String candidate : candidates.keySet()) {
            System.out.println("- " + candidate + ": " + candidates.get(candidate) + " suara");
        }
    }

    public void loginOrRegister() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Selamat datang di Sistem Voting Online");
        System.out.println("Pilih menu:");
        System.out.println("1. Login");
        System.out.println("2. Daftar");
        System.out.println("3. Hasil Vote");
        System.out.println("4. Hapus Akun");
        System.out.print("Pilihan Anda: ");
        int choice = scanner.nextInt();

        switch (choice) {
            case 1:
                login();
                break;
            case 2:
                register();
                break;
            case 3:
                displayResults();
                break;
            case 4:
                deleteAccount();
                break;
            default:
                System.out.println("Pilihan tidak valid.");
                loginOrRegister();
        }
    }

    public void deleteAccount() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Masukkan email yang akan dihapus: ");
        String email = scanner.nextLine();

        if (users.containsKey(email)) {
            users.remove(email);
            userDetails.remove(email);
            System.out.println("Akun berhasil dihapus.");
            saveUserDataToFile();
            loginOrRegister();
        } else {
            System.out.println("Email tidak ditemukan.");
            loginOrRegister();
        }
    }

    public static void main(String[] args) {
        DataPemilih_TUGAS2 votingSystem = new DataPemilih_TUGAS2();
        votingSystem.addCandidate("Kandidat A");
        votingSystem.addCandidate("Kandidat B");
        votingSystem.addCandidate("Kandidat C");
        votingSystem.addCandidate("Kandidat D");

        votingSystem.loginOrRegister();
    }
}