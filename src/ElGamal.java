import java.util.Scanner;
import java.util.Random;

public class ElGamal {

    private static int modPow(int base, int exp, int mod) {
        int result = 1;
        base = base % mod;

        while (exp > 0) {
            if ((exp & 1) == 1) { // If exp is odd
                result = (result * base) % mod;
            }
            exp = exp >> 1; // exp = exp / 2
            base = (base * base) % mod;
        }
        return result;
    }

    private static int encryptMessage(int message, int p, int g, int y, int k) {
        int c1 = modPow(g, k, p);
        int c2 = (message * modPow(y, k, p)) % p;

        System.out.println("\nGenerated values during encryption:");
        System.out.println("Random ephemeral key (k): " + k);
        System.out.println("Cipher component c1: " + c1);
        System.out.println("Cipher component c2: " + c2);

        return (c1 << 16) | c2; // Packing c1 and c2 into one int for simplicity
    }

    private static int decryptMessage(int cipher, int p, int x) {
        int c1 = (cipher >> 16) & 0xFFFF;
        int c2 = cipher & 0xFFFF;

        int s = modPow(c1, x, p);
        int s_inv = modPow(s, p - 2, p); // Using Fermat's Little Theorem for modular inverse
        int message = (c2 * s_inv) % p;

        System.out.println("\nGenerated values during decryption:");
        System.out.println("Cipher component c1: " + c1);
        System.out.println("Cipher component c2: " + c2);
        System.out.println("Shared secret s = c1^x mod p: " + s);
        System.out.println("Modular inverse of s: " + s_inv);

        return message;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Random rand = new Random();

        System.out.print("Enter a prime number (p): ");
        int p = sc.nextInt();

        System.out.print("Enter primitive root modulo p (g): ");
        int g = sc.nextInt();

        System.out.print("Enter your private key (x): ");
        int x = sc.nextInt();

        int y = modPow(g, x, p); // Public key y = g^x mod p

        System.out.println("Generated public key (y): " + y);

        System.out.print("\nEnter the message (as integer) to encrypt: ");
        int message = sc.nextInt();

        int k = rand.nextInt(p - 2) + 1; // Random ephemeral key 1 <= k <= p-2
        int cipher = encryptMessage(message, p, g, y, k);

        System.out.println("\nCiphertext (packed as integer): " + cipher);

        int decryptedMessage = decryptMessage(cipher, p, x);

        System.out.println("\nDecrypted Message: " + decryptedMessage);
    }
}
