import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Scanner;

public class RSA{
    static int gcd(int a, int b) {
        if (a == 0) {
            return b;
        } else {
            return gcd(b % a, a);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter value of p: ");
        int p = sc.nextInt();

        System.out.print("Enter value of q: ");
        int q = sc.nextInt();

        int n = p * q;
        int totient = (p - 1) * (q - 1);
        System.out.println("Value of n: " + n);
        System.out.println("Value of totient: " + totient);

        int e;
        for (e = 2; e < totient; e++) {
            if (gcd(e, totient) == 1) {
                break;
            }
        }
        System.out.println("Value of e: " + e);

        int d = 1;
        while ((d * e) % totient != 1) {
            d++;
        }
        System.out.println("Value of d: " + d);

        int[] publicKey = {e, n};
        int[] privateKey = {d, n};
        System.out.println("Public Key: " + publicKey[0] + " " + publicKey[1]);
        System.out.println("Private Key:" + privateKey[0] + " " + privateKey[1]);

        System.out.print("Enter plaintext number: ");
        int m = sc.nextInt();

        double c = (Math.pow(m, e)) % n;
        System.out.println("Ciphertext: " + c);

        BigInteger N = BigInteger.valueOf(n);
        BigInteger C = BigDecimal.valueOf(c).toBigInteger();
        BigInteger c_decrypt = (C.pow(d)).mod(N);
        System.out.println("Decrypted message is : " + c_decrypt);
    }
}
