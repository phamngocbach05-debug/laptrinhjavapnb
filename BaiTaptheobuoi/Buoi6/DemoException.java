import java.util.Scanner;

public class DemoException {

    // 1 lop exception duy nhat
    static class DiemKhongHopLeException extends Exception {
        public DiemKhongHopLeException(double diem) {
            super("Diem " + diem + " khong hop le! Phai tu 0 den 10");
        }
    }

    // Ham kiem tra diem - nem exception neu sai
    static void kiemTraDiem(double diem) throws DiemKhongHopLeException {
        if (diem < 0 || diem > 10) {
            throw new DiemKhongHopLeException(diem);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("=== BUOI 6: EXCEPTION HANDLING ===");
        System.out.print("Nhap diem cua ban: ");

        try {
            double diem = Double.parseDouble(sc.nextLine().trim());
            kiemTraDiem(diem);
            System.out.println(">> Diem hop le: " + diem);

        } catch (DiemKhongHopLeException e) {
            System.out.println(">> LOI: " + e.getMessage());

        } catch (NumberFormatException e) {
            System.out.println(">> LOI: Vui long nhap so!");

        } finally {
            System.out.println(">> Chuong trinh ket thuc.");
        }

        sc.close();
    }
}
