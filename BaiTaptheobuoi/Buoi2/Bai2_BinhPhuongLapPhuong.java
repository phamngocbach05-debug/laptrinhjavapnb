import java.util.Scanner;

public class Bai2_BinhPhuongLapPhuong {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhap mot so nguyen: ");
        double n = sc.nextDouble();

        double binhPhuong = n * n;
        double lapPhuong  = n * n * n;

        System.out.println("So vua nhap     : " + n);
        System.out.println("Binh phuong     : " + n + "^2 = " + binhPhuong);
        System.out.println("Lap phuong      : " + n + "^3 = " + lapPhuong);
        sc.close();
    }
}
