import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("--- Instant Text Cipher Tool ---");
        try {
            System.out.print("Enter text to process: ");
            String input = scanner.nextLine();

            System.out.print("Enter mode (E for Encrypt / D for Decrypt): ");
            String modeStr = scanner.nextLine().toUpperCase();
            boolean isEncrypt = modeStr.equals("E");
            System.out.print("Enter the shift key (integer): ");
            int key = Integer.parseInt(scanner.nextLine());
            String result = processText(input, key, isEncrypt);
            System.out.println("\n------------------------------");
            System.out.println("Result: " + result);
            System.out.println("------------------------------");

        } catch (NumberFormatException e) {
            System.err.println("Error: The key must be a valid integer.");
        } finally {
            scanner.close();
        }
    }
    private static String processText(String text, int key, boolean isEncrypt) {
        StringBuilder sb = new StringBuilder();
        final int effectiveKey = isEncrypt ? key : -key;
        for (char c : text.toCharArray()) {
            sb.append(applyCipher(c, effectiveKey));
        }
        return sb.toString();
    }
    private static char applyCipher(char c, int key) {
        if (Character.isLetter(c)) {
            char base = Character.isLowerCase(c) ? 'a' : 'A';
            int alphabetPosition = c - base;
            int newPosition = (alphabetPosition + (key % 26) + 26) % 26;

            return (char) (base + newPosition);
        }
        return c;
    }
}