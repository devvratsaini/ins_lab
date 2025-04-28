import java.util.Scanner;

public class HillCipher {

    private static String preprocess(String text) {
        return text.toUpperCase().replaceAll("[^A-Z]", "");
    }

    private static void getKeyMatrix(String key, int[][] keyMatrix) {
        if (key.length() != 9) {
            throw new IllegalArgumentException("Key must be 9 characters long.");
        }
        int k = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                keyMatrix[i][j] = key.charAt(k) - 'A';
                k++;
            }
        }
    }

    public static String encrypt(String plaintext, String key) {
        int[][] keyMatrix = new int[3][3];
        getKeyMatrix(key, keyMatrix);

        int remainder = plaintext.length() % 3;
        if (remainder != 0) {
            int padLength = 3 - remainder;
            for (int i = 0; i < padLength; i++) {
                plaintext += "X";
            }
        }

        StringBuilder ciphertext = new StringBuilder();
        for (int block = 0; block < plaintext.length(); block += 3) {
            int[][] ptVector = new int[3][1];
            for (int i = 0; i < 3; i++) {
                ptVector[i][0] = plaintext.charAt(block + i) - 'A';
            }
            int[][] cipherVector = new int[3][1];
            for (int i = 0; i < 3; i++) {
                cipherVector[i][0] = 0;
                for (int j = 0; j < 3; j++) {
                    cipherVector[i][0] += keyMatrix[i][j] * ptVector[j][0];
                }
                cipherVector[i][0] %= 26;
            }
            for (int i = 0; i < 3; i++) {
                ciphertext.append((char)(cipherVector[i][0] + 'A'));
            }
        }
        return ciphertext.toString();
    }

    private static int modInverse(int a, int m) {
        a = a % m;
        for (int x = 1; x < m; x++) {
            if ((a * x) % m == 1)
                return x;
        }
        return -1;
    }

    public static String decrypt(String ciphertext, String key) {
        int[][] keyMatrix = new int[3][3];
        getKeyMatrix(key, keyMatrix);

        int det = keyMatrix[0][0] * (keyMatrix[1][1] * keyMatrix[2][2] - keyMatrix[1][2] * keyMatrix[2][1])
                - keyMatrix[0][1] * (keyMatrix[1][0] * keyMatrix[2][2] - keyMatrix[1][2] * keyMatrix[2][0])
                + keyMatrix[0][2] * (keyMatrix[1][0] * keyMatrix[2][1] - keyMatrix[1][1] * keyMatrix[2][0]);
        det %= 26;
        if (det < 0)
            det += 26;

        int detInv = modInverse(det, 26);
        if (detInv == -1) {
            System.out.println("Error: Key matrix is not invertible modulo 26.");
            return "";
        }

        int[][] cof = new int[3][3];
        cof[0][0] =  keyMatrix[1][1] * keyMatrix[2][2] - keyMatrix[1][2] * keyMatrix[2][1];
        cof[0][1] = - (keyMatrix[1][0] * keyMatrix[2][2] - keyMatrix[1][2] * keyMatrix[2][0]);
        cof[0][2] =  keyMatrix[1][0] * keyMatrix[2][1] - keyMatrix[1][1] * keyMatrix[2][0];

        cof[1][0] = - (keyMatrix[0][1] * keyMatrix[2][2] - keyMatrix[0][2] * keyMatrix[2][1]);
        cof[1][1] =  keyMatrix[0][0] * keyMatrix[2][2] - keyMatrix[0][2] * keyMatrix[2][0];
        cof[1][2] = - (keyMatrix[0][0] * keyMatrix[2][1] - keyMatrix[0][1] * keyMatrix[2][0]);

        cof[2][0] =  keyMatrix[0][1] * keyMatrix[1][2] - keyMatrix[0][2] * keyMatrix[1][1];
        cof[2][1] = - (keyMatrix[0][0] * keyMatrix[1][2] - keyMatrix[0][2] * keyMatrix[1][0]);
        cof[2][2] =  keyMatrix[0][0] * keyMatrix[1][1] - keyMatrix[0][1] * keyMatrix[1][0];

        int[][] adj = new int[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                adj[i][j] = cof[j][i];
                adj[i][j] %= 26;
                if (adj[i][j] < 0)
                    adj[i][j] += 26;
            }
        }

        int[][] invKeyMatrix = new int[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                invKeyMatrix[i][j] = (adj[i][j] * detInv) % 26;
                if (invKeyMatrix[i][j] < 0)
                    invKeyMatrix[i][j] += 26;
            }
        }

        StringBuilder plaintext = new StringBuilder();
        for (int block = 0; block < ciphertext.length(); block += 3) {
            int[][] ctVector = new int[3][1];
            for (int i = 0; i < 3; i++) {
                ctVector[i][0] = ciphertext.charAt(block + i) - 'A';
            }
            int[][] ptVector = new int[3][1];
            for (int i = 0; i < 3; i++) {
                ptVector[i][0] = 0;
                for (int j = 0; j < 3; j++) {
                    ptVector[i][0] += invKeyMatrix[i][j] * ctVector[j][0];
                }
                ptVector[i][0] %= 26;
                if (ptVector[i][0] < 0)
                    ptVector[i][0] += 26;
            }
            for (int i = 0; i < 3; i++) {
                plaintext.append((char)(ptVector[i][0] + 'A'));
            }
        }
        return plaintext.toString();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Hill Cipher Implementation");

        System.out.print("Enter a 9-letter key: ");
        String key = preprocess(sc.nextLine());
        if (key.length() != 9) {
            System.out.println("Error: Key must be exactly 9 alphabetic characters after preprocessing.");
            return;
        }

        System.out.print("Enter plaintext: ");
        String plaintext = preprocess(sc.nextLine());

        String ciphertext = encrypt(plaintext, key);
        System.out.println("Encrypted ciphertext: " + ciphertext);

        String decryptedText = decrypt(ciphertext, key);
        System.out.println("Decrypted plaintext: " + decryptedText);
    }
}
