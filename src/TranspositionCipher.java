import java.util.Scanner;

public class TranspositionCipher {
    private static String preprocessPT(String plaintext) {
        return plaintext.toUpperCase().replaceAll("[^A-Z]", "");
    }

    private static String encrypt(String plaintext, String key) {
        int cols = key.length();
        int rows = (int) Math.ceil((double) plaintext.length() / cols);
        char[][] matrix = new char[rows][cols];

        int idx = 0;
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (idx < plaintext.length()) {
                    matrix[r][c] = plaintext.charAt(idx++);
                } else {
                    matrix[r][c] = ' ';
                }
            }
        }

        StringBuilder ciphertext = new StringBuilder();
        for (int c = 0; c < cols; c++) {
            int col = Character.getNumericValue(key.charAt(c)) - 1;
            for (int r = 0; r < rows; r++) {
                ciphertext.append(matrix[r][col]);
            }
        }

        return ciphertext.toString();
    }

    private static String decrypt(String ciphertext, String key) {
        int cols = key.length();
        int rows = (int) Math.ceil((double) ciphertext.length() / cols);
        char[][] matrix = new char[rows][cols];

        int idx = 0;
        // Place characters in the matrix column-wise as per the key's order
        for (int c = 0; c < cols; c++) {
            int col = Character.getNumericValue(key.charAt(c)) - 1;
            for (int r = 0; r < rows; r++) {
                matrix[r][col] = ciphertext.charAt(idx++);
            }
        }

        StringBuilder decryptedText = new StringBuilder();
        // Read the matrix row by row to get the decrypted plaintext
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                decryptedText.append(matrix[r][c]);
            }
        }

        return decryptedText.toString().trim(); // Trim to remove extra spaces added during encryption
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter the plaintext: ");
        String plaintext = preprocessPT(sc.nextLine());

        System.out.print("Enter the key: ");  // e.g., 3124
        String key = sc.nextLine();

        String ciphertext = encrypt(plaintext, key);
        System.out.println("Cipher Text: " + ciphertext);

        String decryptedtext = decrypt(ciphertext, key);
        System.out.println("Decrypted Text: " + decryptedtext);
    }
}
