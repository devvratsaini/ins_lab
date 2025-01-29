import java.util.LinkedHashSet;
import java.util.Scanner;

public class PlayFairCipher {

    private static char[][] createMatrix(String key) {
        key = key.toUpperCase();
        LinkedHashSet<Character> set = new LinkedHashSet<>();

        // Adding key to the set
        for (char c : key.toCharArray()) {
            if (c != 'J') {
                set.add(c);
            }
        }

        // Add rest of the alphabet to the set
        for (char ch = 'A'; ch <= 'Z'; ch++) {
            if (!set.contains(ch) && ch != 'J') {
                set.add(ch);
            }
        }

        // Create 5x5 matrix from set
        char[][] matrix = new char[5][5];
        int index = 0;
        for (char ch : set) {
            matrix[index / 5][index % 5] = ch;
            index++;
        }

        return matrix;
    }

    private static void printMatrix(char[][] matrix) {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }

    private static String preprocessPT(String plaintext) {
        plaintext = plaintext.toUpperCase();
        plaintext = plaintext.replaceAll("[^A-Z]", "");
        plaintext = plaintext.replace('J', 'I');

        StringBuilder processed = new StringBuilder();
        for (int i = 0; i < plaintext.length(); i++) {
            char current = plaintext.charAt(i);
            processed.append(current);

            if (i + 1 < plaintext.length() && plaintext.charAt(i + 1) == current) {
                processed.append('X');
            }
        }

        if (processed.length() % 2 != 0) {
            processed.append('X');
        }

        return processed.toString();
    }

    private static int[] findPosition(char[][] matrix, char ch) {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (matrix[i][j] == ch) {
                    return new int[]{i, j};
                }
            }
        }
        throw new IllegalArgumentException("Character '" + ch + "' not found in matrix.");
    }

    private static String encrypt(String plaintext, char[][] matrix) {
        StringBuilder ciphertext = new StringBuilder();

        for (int i = 0; i < plaintext.length(); i += 2) {
            char fc = plaintext.charAt(i);
            char sc = plaintext.charAt(i + 1);

            int[] fc_pos = findPosition(matrix, fc);
            int[] sc_pos = findPosition(matrix, sc);

            if (fc_pos[0] == sc_pos[0]) { // Same row
                ciphertext.append(matrix[fc_pos[0]][(fc_pos[1] + 1) % 5]);
                ciphertext.append(matrix[sc_pos[0]][(sc_pos[1] + 1) % 5]);
            } else if (fc_pos[1] == sc_pos[1]) { // Same column
                ciphertext.append(matrix[(fc_pos[0] + 1) % 5][fc_pos[1]]);
                ciphertext.append(matrix[(sc_pos[0] + 1) % 5][sc_pos[1]]);
            } else { // Rectangle swap
                ciphertext.append(matrix[fc_pos[0]][sc_pos[1]]);
                ciphertext.append(matrix[sc_pos[0]][fc_pos[1]]);
            }
        }

        return ciphertext.toString();
    }

    private static String decrypt(String ciphertext, char[][] matrix) {
        StringBuilder plaintext = new StringBuilder();

        for (int i = 0; i < ciphertext.length(); i += 2) {
            char fc = ciphertext.charAt(i);
            char sc = ciphertext.charAt(i + 1);

            int[] fc_pos = findPosition(matrix, fc);
            int[] sc_pos = findPosition(matrix, sc);

            if (fc_pos[0] == sc_pos[0]) { // Same row
                plaintext.append(matrix[fc_pos[0]][(fc_pos[1] + 4) % 5]);
                plaintext.append(matrix[sc_pos[0]][(sc_pos[1] + 4) % 5]);
            } else if (fc_pos[1] == sc_pos[1]) { // Same column
                plaintext.append(matrix[(fc_pos[0] + 4) % 5][fc_pos[1]]);
                plaintext.append(matrix[(sc_pos[0] + 4) % 5][sc_pos[1]]);
            } else { // Rectangle swap
                plaintext.append(matrix[fc_pos[0]][sc_pos[1]]);
                plaintext.append(matrix[sc_pos[0]][fc_pos[1]]);
            }
        }

        // Remove added 'X' characters used for padding or duplicate letter separation
        for (int i = 0; i < plaintext.length() - 1; i++) {
            if (plaintext.charAt(i) == 'X' && (i == 0 || plaintext.charAt(i - 1) == plaintext.charAt(i + 1))) {
                plaintext.deleteCharAt(i);
                i--; // Adjust index after removal
            }
        }

        return plaintext.toString();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter the key: ");
        String key = sc.nextLine();

        char[][] matrix = createMatrix(key);

        System.out.println("Matrix:");
        printMatrix(matrix);

        System.out.print("\nEnter plaintext: ");
        String plaintext = sc.nextLine();
        plaintext = preprocessPT(plaintext);

        String ciphertext = encrypt(plaintext, matrix);

        System.out.println("Ciphertext: " + ciphertext);

        System.out.println("Decrypting...");

        String decryptedtext = decrypt(ciphertext, matrix);

        System.out.println("Decrypted text: " + decryptedtext);
    }
}
