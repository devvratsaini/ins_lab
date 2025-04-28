import java.util.Scanner;

public class ChineseRemainderTheorem {

    private static int modularInverse(int a, int m) {
        int m0 = m;
        int y = 0, x = 1;

        if (m == 1)
            return 0;

        while (a > 1) {
            int q = a / m;
            int t = m;
            m = a % m;
            a = t;
            t = y;
            y = x - q * y;
            x = t;
        }

        if (x < 0)
            x += m0;

        return x;
    }

    private static int findMinX(int[] num, int[] rem, int k) {
        int prod = 1;
        for (int i = 0; i < k; i++) {
            prod *= num[i];
        }

        System.out.println("\nTotal product of divisors (prod) = " + prod);

        int result = 0;

        for (int i = 0; i < k; i++) {
            int pp = prod / num[i];
            int inv = modularInverse(pp, num[i]);
            int term = rem[i] * inv * pp;

            System.out.println("\n--- Step " + (i + 1) + " ---");
            System.out.println("Divisor (n[" + (i + 1) + "]) = " + num[i]);
            System.out.println("Remainder (r[" + (i + 1) + "]) = " + rem[i]);
            System.out.println("Partial product (pp) = prod / n[" + (i + 1) + "] = " + prod + " / " + num[i] + " = " + pp);
            System.out.println("Modular inverse of " + pp + " mod " + num[i] + " = " + inv);
            System.out.println("Term contribution = r[" + (i + 1) + "] * inverse * pp = " + rem[i] + " * " + inv + " * " + pp + " = " + term);

            result += term;
        }

        int finalResult = result % prod;
        System.out.println("\nFinal result before modulo: " + result);
        System.out.println("Final result after modulo prod: " + finalResult);

        return finalResult;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of equations: ");
        int k = sc.nextInt();

        int[] num = new int[k];
        int[] rem = new int[k];

        System.out.println("Enter divisors (n) and remainders (r) for each equation:");
        for (int i = 0; i < k; i++) {
            System.out.print("Divisor n[" + (i + 1) + "]: ");
            num[i] = sc.nextInt();
            System.out.print("Remainder r[" + (i + 1) + "]: ");
            rem[i] = sc.nextInt();
        }

        int solution = findMinX(num, rem, k);

        System.out.println("\nThe solution x is: " + solution);
    }
}
