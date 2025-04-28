import java.util.Scanner;

public class RailFenceCipher{

    // function to preprocess the raw plaintext in case there were some illegal characters
    private static String preprocessPT(String plaintext) {
        return plaintext.toUpperCase().replaceAll("[^A-Z]", "");
    }

    private static String encrypt(String plaintext, int key) {
        StringBuilder ciphertext = new StringBuilder();

        char[][] matrix = new char[key][plaintext.length()];

        // initializing matrix
        int row = 0;
        boolean reverse = true;
        for (int i = 0; i < plaintext.length(); i++) {
            matrix[row][i] = plaintext.charAt(i);
            if(row == 0 || row == key-1) {
                reverse = !reverse;
            }
            row = reverse ? row-1 : row+1;
        }

        // extracting ciphertext
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if(matrix[i][j] != '\u0000') {
                    ciphertext.append(matrix[i][j]);
                }
            }
        }

        return ciphertext.toString();
    }

    private static String decrypt(String ciphertext, int key) {
        StringBuilder plaintext = new StringBuilder();

        char[][] matrix = new char[key][ciphertext.length()];

        // marking plaintext locations in matrix with '*'
        int row = 0;
        boolean reverse = true;
        for (int i = 0; i < ciphertext.length(); i++) {
            matrix[row][i] = '*';
            if(row == 0 || row == key-1) {
                reverse = !reverse;
            }
            row = reverse ? row-1 : row+1;
        }

        // replacing markings ('*') with ciphertext letters
        int letterIdx = 0;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if(matrix[i][j] == '*' && letterIdx < ciphertext.length()) {
                    matrix[i][j] = ciphertext.charAt(letterIdx++);
                }
            }
        }

        // extracting plaintext from matrix
        row = 0;
        reverse = true;
        for (int i = 0; i < ciphertext.length(); i++) {
            plaintext.append(matrix[row][i]);
            if(row == 0 || row == key-1) {
                reverse = !reverse;
            }
            row = reverse ? row-1 : row+1;
        }

        return plaintext.toString();
    }

    // driver code
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Key input and preprocessing
        System.out.print("Enter the key: ");
        int key = sc.nextInt();
        sc.nextLine();

        // Plaintext input and preprocessing
        System.out.print("\nEnter plaintext: ");
        String plaintext = sc.nextLine();
        plaintext = preprocessPT(plaintext);

        // Ciphertext calculation and printing
        String ciphertext = encrypt(plaintext, key);
        System.out.println("Ciphertext: " + ciphertext);

        // Decrypted plaintext calculation and printing
        System.out.println("Decrypting...");
        String decryptedtext = decrypt(ciphertext, key);
        System.out.println("Decrypted text: " + decryptedtext);
    }
}
