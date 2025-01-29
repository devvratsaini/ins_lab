import java.util.Scanner;

public class PolyAlphaCipher{

    private static char[][] createMatrix() {
        char[][] matrix = new char[26][26];

        for (int i = 0; i < 26; i++) {
            for (int j = 0; j < 26; j++) {
                matrix[i][j] = (char) (65 + ((i + j) % 26));
            }
        }

        return matrix;
    }

    private static void printMatrix(char[][] matrix) {
        for (int i = 0; i < 26; i++) {
            for (int j = 0; j < 26; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }

    private static String preprocessPT(String plaintext) {
        return plaintext.toUpperCase().replaceAll("[^A-Z]", "");
    }

    private static String encrypt(String plaintext, String key, char[][] matrix) {
        StringBuilder ciphertext = new StringBuilder();

        int key_idx, pt_idx;
        for (int i = 0; i < plaintext.length(); i++) {
            key_idx = key.charAt(i % (key.length())) - 'A';
            pt_idx = plaintext.charAt(i) - 'A';
            ciphertext.append(matrix[key_idx][pt_idx]);
        }

        return ciphertext.toString();
    }

    private static String decrypt(String ciphertext, String key, char[][] matrix) {
        StringBuilder plaintext = new StringBuilder();

        int key_idx, ct_idx = -1;
        for (int i = 0; i < ciphertext.length(); i++) {
            key_idx = key.charAt(i % (key.length())) - 'A';

            for (int j = 0; j < 26; j++) {
                if (matrix[key_idx][j] == ciphertext.charAt(i)) {
                    ct_idx = j;
                    break;
                }
            }

            plaintext.append((char)('A' + ct_idx));
        }

        return plaintext.toString();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Matrix creation and printing
        char[][] matrix = createMatrix();
        System.out.println("Do you want to print the matrix? (Y/N)");
        char choice = sc.nextLine().charAt(0);
        if (choice == 'Y') {
            System.out.println("Matrix:");
            printMatrix(matrix);
        }

        // Key input and preprocessing
        System.out.print("Enter the key: ");
        String key = sc.nextLine();
        key = key.toUpperCase();

        // Plaintext input and preprocessing
        System.out.print("\nEnter plaintext: ");
        String plaintext = sc.nextLine();
        plaintext = preprocessPT(plaintext);

        // Ciphertext calculation and printing
        String ciphertext = encrypt(plaintext, key, matrix);
        System.out.println("Ciphertext: " + ciphertext);

        // Decrypted plaintext calculation and printing
        System.out.println("Decrypting...");
        String decryptedtext = decrypt(ciphertext, key, matrix);
        System.out.println("Decrypted text: " + decryptedtext);
    }
}
