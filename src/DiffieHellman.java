import java.util.Scanner;

public class DiffieHellman {

    private static long powerMod(long base, long exponent, long modulus) {
        long result = 1;
        base = base % modulus;

        while (exponent > 0) {
            if ((exponent & 1) == 1) { // If exponent is odd
                result = (result * base) % modulus;
            }
            exponent = exponent >> 1; // Divide exponent by 2
            base = (base * base) % modulus;
        }
        return result;
    }

    private static long generatePublicKey(long base, long privateKey, long prime) {
        return powerMod(base, privateKey, prime);
    }

    private static long generateSharedSecret(long otherPublicKey, long privateKey, long prime) {
        return powerMod(otherPublicKey, privateKey, prime);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter prime number (p): ");
        long p = sc.nextLong();

        System.out.print("Enter primitive root modulo p (g): ");
        long g = sc.nextLong();

        System.out.print("Enter your private key (a): ");
        long a = sc.nextLong();

        System.out.print("Enter partner's private key (b): ");
        long b = sc.nextLong();

        long A = generatePublicKey(g, a, p); // Your public key
        long B = generatePublicKey(g, b, p); // Partner's public key

        System.out.println("Your public key (A): " + A);
        System.out.println("Partner's public key (B): " + B);

        long sharedSecretA = generateSharedSecret(B, a, p);
        long sharedSecretB = generateSharedSecret(A, b, p);

        System.out.println("Shared secret computed by you: " + sharedSecretA);
        System.out.println("Shared secret computed by partner: " + sharedSecretB);
    }
}
