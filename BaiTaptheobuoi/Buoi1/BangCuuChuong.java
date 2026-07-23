// Buoi 1 - Bai tap: Bang Cuu Chuong
// Sinh vien: Pham Ngoc Bach - 2351170576
public class BangCuuChuong {
    public static void main(String[] args) {
        System.out.println("===== BANG CUU CHUONG =====");
        for (int i = 2; i <= 9; i++) {
            System.out.println("--- Bang " + i + " ---");
            for (int j = 1; j <= 10; j++) {
                System.out.println(i + " x " + j + " = " + (i * j));
            }
            System.out.println();
        }
    }
}
