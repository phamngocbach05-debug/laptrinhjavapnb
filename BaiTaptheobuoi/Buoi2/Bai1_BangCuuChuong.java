import java.util.Scanner;

public class Bai1_BangCuuChuong {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhap so can in bang cuu chuong (2-9): ");
        int n = sc.nextInt();

        if (n < 2 || n > 9) {
            System.out.println("So khong hop le! Vui long nhap tu 2 den 9.");
        } else {
            System.out.println("===== BANG CUU CHUONG " + n + " =====");
            for (int i = 1; i <= 10; i++) {
                System.out.println(n + " x " + i + " = " + (n * i));
            }
        }
        sc.close();
    }
}
