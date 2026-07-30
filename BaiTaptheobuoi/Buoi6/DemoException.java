import java.util.ArrayList;
import java.util.Scanner;

// Buoi 6: Demo Exception Handling - nhap lieu tu ban phim
public class DemoException {

    static ArrayList<String[]> danhSach = new ArrayList<>();
    static Scanner sc = new Scanner(System.in);

    // Kiem tra diem hop le (moi mon: 0-10)
    static void kiemTraDiem(double diem) throws DiemKhongHopLeException {
        if (diem < 0 || diem > 10)
            throw new DiemKhongHopLeException(diem);
    }

    // Them sinh vien, nem exception neu loi
    static void them(String maSo, String hoTen, String nganh, double[] diems)
            throws MaSoTonTaiException, DiemKhongHopLeException {

        // Kiem tra tung diem
        for (double d : diems) kiemTraDiem(d);

        // Kiem tra trung ma so
        for (String[] sv : danhSach)
            if (sv[0].equalsIgnoreCase(maSo))
                throw new MaSoTonTaiException(maSo);

        double tong = 0;
        for (double d : diems) tong += d;
        danhSach.add(new String[]{maSo, hoTen, nganh, String.format("%.2f", tong)});
    }

    // Tim kiem, nem exception neu khong thay
    static String[] timKiem(String maSo) throws SinhVienKhongTonTaiException {
        for (String[] sv : danhSach)
            if (sv[0].equalsIgnoreCase(maSo)) return sv;
        throw new SinhVienKhongTonTaiException(maSo);
    }

    // Hien thi danh sach
    static void hienThi() {
        if (danhSach.isEmpty()) { System.out.println("  (Danh sach trong)"); return; }
        System.out.println("  +---------+----------------------+----------+-------+");
        System.out.println("  | Ma so   | Ho ten               | Nganh    | Tong  |");
        System.out.println("  +---------+----------------------+----------+-------+");
        for (String[] sv : danhSach)
            System.out.printf("  | %-7s | %-20s | %-8s | %-5s |%n",
                    sv[0], sv[1], sv[2], sv[3]);
        System.out.println("  +---------+----------------------+----------+-------+");
    }

    // ================================================================
    // MAIN
    // ================================================================
    public static void main(String[] args) {
        int choice = -1;
        System.out.println("╔══════════════════════════════════════╗");
        System.out.println("║  QUAN LY SINH VIEN - BUOI 6          ║");
        System.out.println("║  Exception Handling                  ║");
        System.out.println("╚══════════════════════════════════════╝");

        while (choice != 0) {
            System.out.println("\n====== MENU ======");
            System.out.println("1. Them SV Cong nghe  (Toan + Ly-Hoa)");
            System.out.println("2. Them SV Kinh te    (Toan + Van + Anh)");
            System.out.println("3. Hien thi danh sach");
            System.out.println("4. Tim kiem theo ma so");
            System.out.println("0. Thoat");
            System.out.print("Chon: ");

            try { choice = Integer.parseInt(sc.nextLine().trim()); }
            catch (NumberFormatException e) { System.out.println("Vui long nhap so!"); continue; }

            switch (choice) {
                case 1 -> {
                    System.out.println("\n-- THEM SV CONG NGHE (diem moi mon: 0-10) --");
                    try {
                        System.out.print("Ma so     : "); String ms = sc.nextLine().trim();
                        System.out.print("Ho ten    : "); String ht = sc.nextLine().trim();
                        System.out.print("Diem Toan : "); double toan  = Double.parseDouble(sc.nextLine().trim());
                        System.out.print("Diem LyHoa: "); double lyHoa = Double.parseDouble(sc.nextLine().trim());
                        them(ms, ht, "Cong nghe", new double[]{toan, lyHoa});
                        System.out.println("✔ Them thanh cong!");
                    } catch (DiemKhongHopLeException e) {
                        System.out.println("✘ DiemKhongHopLeException: " + e.getMessage());
                        System.out.println("  >> Diem bi sai: " + e.getDiem());
                    } catch (MaSoTonTaiException e) {
                        System.out.println("✘ MaSoTonTaiException: " + e.getMessage());
                        System.out.println("  >> Ma so trung: " + e.getMaSo());
                    } catch (NumberFormatException e) {
                        System.out.println("✘ Diem phai la so! (Vi du: 8.5)");
                    }
                }
                case 2 -> {
                    System.out.println("\n-- THEM SV KINH TE (diem moi mon: 0-10) --");
                    try {
                        System.out.print("Ma so     : "); String ms = sc.nextLine().trim();
                        System.out.print("Ho ten    : "); String ht = sc.nextLine().trim();
                        System.out.print("Diem Toan : "); double toan = Double.parseDouble(sc.nextLine().trim());
                        System.out.print("Diem Van  : "); double van  = Double.parseDouble(sc.nextLine().trim());
                        System.out.print("Diem Anh  : "); double anh  = Double.parseDouble(sc.nextLine().trim());
                        them(ms, ht, "Kinh te", new double[]{toan, van, anh});
                        System.out.println("✔ Them thanh cong!");
                    } catch (DiemKhongHopLeException e) {
                        System.out.println("✘ DiemKhongHopLeException: " + e.getMessage());
                        System.out.println("  >> Diem bi sai: " + e.getDiem());
                    } catch (MaSoTonTaiException e) {
                        System.out.println("✘ MaSoTonTaiException: " + e.getMessage());
                        System.out.println("  >> Ma so trung: " + e.getMaSo());
                    } catch (NumberFormatException e) {
                        System.out.println("✘ Diem phai la so! (Vi du: 7.5)");
                    }
                }
                case 3 -> { System.out.println("\n-- DANH SACH --"); hienThi(); }
                case 4 -> {
                    System.out.print("\nNhap ma so can tim: "); String ms = sc.nextLine().trim();
                    try {
                        String[] sv = timKiem(ms);
                        System.out.println("✔ Tim thay:");
                        System.out.printf("  Ma so: %s | Ho ten: %s | Nganh: %s | Tong: %s%n",
                                sv[0], sv[1], sv[2], sv[3]);
                    } catch (SinhVienKhongTonTaiException e) {
                        System.out.println("✘ SinhVienKhongTonTaiException: " + e.getMessage());
                        System.out.println("  >> Ma so khong ton tai: " + e.getMaSo());
                    }
                }
                case 0 -> System.out.println("Tam biet!");
                default -> System.out.println("Lua chon khong hop le!");
            }
        }
        sc.close();
    }
}
