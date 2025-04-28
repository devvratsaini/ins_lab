import java.util.LinkedList;
import java.util.Scanner;

public class BasicHashing {
    private static final int SIZE = 10;
    private static LinkedList<Integer>[] chainingTable = new LinkedList[SIZE];
    private static Integer[] linearProbingTable = new Integer[SIZE];

    private static int hash(int key) {
        return key % SIZE;
    }

    // Chaining Insertion
    private static void insertChaining(int key) {
        int index = hash(key);
        if (chainingTable[index] == null) {
            chainingTable[index] = new LinkedList<>();
        }
        chainingTable[index].add(key);
    }

    // Linear Probing Insertion
    private static void insertLinearProbing(int key) {
        int index = hash(key);
        int start = index;
        while (linearProbingTable[index] != null) {
            index = (index + 1) % SIZE;
            if (index == start) {
                System.out.println("Hash table full. Cannot insert key: " + key);
                return;
            }
        }
        linearProbingTable[index] = key;
    }

    private static void displayChaining() {
        System.out.println("Hash Table with Chaining:");
        for (int i = 0; i < SIZE; i++) {
            System.out.print(i + ": ");
            if (chainingTable[i] != null) {
                for (int key : chainingTable[i]) {
                    System.out.print(key + " -> ");
                }
            }
            System.out.println("null");
        }
    }

    private static void displayLinearProbing() {
        System.out.println("Hash Table with Linear Probing:");
        for (int i = 0; i < SIZE; i++) {
            System.out.println(i + ": " + (linearProbingTable[i] != null ? linearProbingTable[i] : "null"));
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of elements to insert: ");
        int n = sc.nextInt();

        System.out.println("Enter elements:");
        for (int i = 0; i < n; i++) {
            int key = sc.nextInt();
            insertChaining(key);
            insertLinearProbing(key);
        }

        displayChaining();
        displayLinearProbing();
    }
}
