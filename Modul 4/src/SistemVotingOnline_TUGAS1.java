import java.util.HashMap;
import java.util.Scanner;

public class SistemVotingOnline_TUGAS1 {
    private HashMap<String, Integer> candidates;

    public SistemVotingOnline_TUGAS1() {
        this.candidates = new HashMap<>();
    }

    public void addCandidate(String name) {
        candidates.put(name, 0);
    }

    public void vote(String candidateName) {
        if (candidates.containsKey(candidateName)) {
            int currentVotes = candidates.get(candidateName);
            candidates.put(candidateName, currentVotes + 1);
            System.out.println("Terima kasih, suara Anda telah direkam.");
        } else {
            System.out.println("Kandidat tidak valid.");
        }
    }

    public void displayResults() {
        System.out.println("Hasil Voting:");
        for (String candidate : candidates.keySet()) {
            System.out.println("- " + candidate + ": " + candidates.get(candidate) + " suara");
        }
    }

    public static void main(String[] args) {
        SistemVotingOnline_TUGAS1 votingSystem = new SistemVotingOnline_TUGAS1();
        votingSystem.addCandidate("Kandidat A");
        votingSystem.addCandidate("Kandidat B");
        votingSystem.addCandidate("Kandidat C");

        Scanner scanner = new Scanner(System.in);
        System.out.println("Selamat datang di Sistem Voting Online");

        String input;
        do {
            System.out.println("Pilih kandidat yang ingin Anda dukung:");
            for (String candidate : votingSystem.candidates.keySet()) {
                System.out.println("- " + candidate);
            }
            System.out.print("Masukkan nama kandidat (atau ketik 'selesai' untuk keluar): ");
            input = scanner.nextLine();

            if (!input.equalsIgnoreCase("selesai")) {
                votingSystem.vote(input);
            }
        } while (!input.equalsIgnoreCase("selesai"));

        votingSystem.displayResults();
    }
}
