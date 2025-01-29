import java.util.Scanner;

public class StreamCipher{

    private static String encrypt(String key, String ip) {
        StringBuilder op = new StringBuilder();
        for (int i = 0; i < 8; i++) {
            if (ip.charAt(i) == key.charAt(i)) {
                op.append("0");
            } else {
                op.append("1");
            }
        }

        return op.toString();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String key = "10100101";

        System.out.print("Input Stream: ");
        String ip_str = sc.nextLine();

        String op_str = encrypt(key, ip_str);
    }
}
