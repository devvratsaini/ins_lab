import java.util.Scanner;

public class CaesarCipher{
    private static String encrypt(String plaintext, int key) {
        StringBuilder ciphertext = new StringBuilder();

        for (int i = 0; i < plaintext.length(); i++) {
            char ch = plaintext.charAt(i);

            // Handle uppercase letters
            if (ch >= 'A' && ch <= 'Z') {
                ch = (char) ('A' + (ch - 'A' + key) % 26);
            }
            // Handle lowercase letters
            else if (ch >= 'a' && ch <= 'z') {
                ch = (char) ('a' + (ch - 'a' + key) % 26);
            }
            // Non-alphabetic characters remain unchanged
            ciphertext.append(ch);
        }

        return ciphertext.toString();
    }

    private static String decrypt(String ciphertext, int key) {
        StringBuilder plaintext = new StringBuilder();

        for (int i = 0; i < ciphertext.length(); i++) {
            char ch = ciphertext.charAt(i);

            // Handle uppercase letters
            if (ch >= 'A' && ch <= 'Z') {
                ch = (char) ('A' + (ch - 'A' - key + 26) % 26);
            }
            // Handle lowercase letters
            else if (ch >= 'a' && ch <= 'z') {
                ch = (char) ('a' + (ch - 'a' - key + 26) % 26);
            }
            // Non-alphabetic characters remain unchanged
            plaintext.append(ch);
        }
        return plaintext.toString();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter shift key: ");
        int key = sc.nextInt();
        sc.nextLine();

        System.out.print("Enter plaintext: ");
        String plaintext = sc.nextLine();

        String ciphertext = encrypt(plaintext, key);

        System.out.println("Ciphertext: " + ciphertext);
        System.out.println("Decrypting again...");

        String decryptedtext = decrypt(ciphertext, key);

        System.out.println("Decrypted text: " + decryptedtext);
    }
}
