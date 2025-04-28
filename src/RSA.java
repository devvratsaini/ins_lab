import java.math.BigInteger;
import java.util.Scanner;

public class RSA {

    // Function to calculate gcd using Euclid's algorithm
    static int gcd(int a, int b) {
        if (a == 0) {
            return b;
        } else {
            return gcd(b % a, a);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Input values for prime numbers p and q
        System.out.print("Enter value of p: ");
        int p = sc.nextInt();

        System.out.print("Enter value of q: ");
        int q = sc.nextInt();

        int n = p * q;
        int totient = (p - 1) * (q - 1); // Euler's totient function (φ(n))
        System.out.println("Value of n: " + n);
        System.out.println("Value of totient: " + totient);

        // Find public key exponent 'e'
        int e;
        for (e = 2; e < totient; e++) {
            if (gcd(e, totient) == 1) {
                break;
            }
        }
        System.out.println("Value of e: " + e);

        // Find private key exponent 'd'
        int d = 1;
        while ((d * e) % totient != 1) {
            d++;
        }
        System.out.println("Value of d: " + d);

        // Public and Private keys
        int[] publicKey = {e, n};
        int[] privateKey = {d, n};
        System.out.println("Public Key: " + publicKey[0] + " " + publicKey[1]);
        System.out.println("Private Key: " + privateKey[0] + " " + privateKey[1]);

        // Encryption
        System.out.print("Enter plaintext number (m): ");
        int m = sc.nextInt();

        // Encrypt the message
        BigInteger M = BigInteger.valueOf(m); // Convert m to BigInteger
        BigInteger N = BigInteger.valueOf(n); // Convert n to BigInteger
        BigInteger C = M.modPow(BigInteger.valueOf(e), N); // C = m^e % n
        System.out.println("Ciphertext: " + C);

        // Decryption
        BigInteger c_decrypt = C.modPow(BigInteger.valueOf(d), N); // c_decrypt = C^d % n
        System.out.println("Decrypted message: " + c_decrypt);

        // Optional: Convert decrypted message back to integer (original m)
        System.out.println("Original plaintext message (m): " + c_decrypt.intValue());
    }
}
