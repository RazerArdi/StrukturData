import java.util.Stack;

public class Latihan1 {
    // StackLibrary
    public static void main(String[] args) {
        Stack s = new Stack();

        System.out.println(s.empty());

        s.push("Bebek");
        s.push("Angsa");
        s.push("Kuda");
        s.push("Buaya");
        s.push("Tikus");

        System.out.println(s.empty());

        System.out.println("Peek: "+ s.peek());
        System.out.println("Animals: " + s);

        s.pop();
        s.pop();

        System.out.println("Animals: " + s);
        System.out.println("Position of Kuda: " + s.search("Kuda"));
    }
}