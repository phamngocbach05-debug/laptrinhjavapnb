import java.util.Scanner;

public class Bai4_Fibonacci {
    // Tinh so Fibonacci thu n (de quy)
    public static long fibDeQuy(int n) {
        if (n <= 0) return 0;
        if (n == 1) return 1;
        return fibDeQuy(n - 1) + fibDeQuy(n - 2);
    }

    // In day Fibonacci den n phan tu (vong lap)
    public static void inDayFibonacci(int n) {
        long a = 0, b = 1;
        System.out.print("Day Fibonacci " + n + " phan tu: ");
        for (int i = 0; i < n; i++) {
            System.out.print(a);
            if (i < n - 1) System.out.print(", ");
            long temp = a + b;
            a = b;
            b = temp;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhap n: ");
        int n = sc.nextInt();

        if (n <= 0) {
            System.out.println("n phai lon hon 0!");
        } else {
            inDayFibonacci(n);
            System.out.println("Fibonacci thu " + n + " = " + fibDeQuy(n));
        }
        sc.close();
    }
}
