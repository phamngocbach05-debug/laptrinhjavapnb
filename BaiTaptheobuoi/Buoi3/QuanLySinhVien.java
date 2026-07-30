import java.util.ArrayList;
import java.util.Scanner;

// Lop quan ly chinh: implements IQuanLy
public class QuanLySinhVien implements IQuanLy {

    private ArrayList<ThiSinh> danhSach = new ArrayList<>();

    @Override
    public void them(ThiSinh ts) {
        danhSach.add(ts);
        System.out.println("✔ Da them: " + ts);
    }

    @Override
    public void hienThi() {
        if (danhSach.isEmpty()) { System.out.println("  (Danh sach trong)"); return; }
        System.out.println("------------------------------------------------------------------");
        for (ThiSinh ts : danhSach)
            ts.hienThiThongTin();   // Goi dong - chay dung phuong thuc cua lop con
        System.out.println("------------------------------------------------------------------");
        System.out.println("  Tong: " + danhSach.size() + " sinh vien");
    }

    @Override
    public ThiSinh timKiem(String maSo) {
        for (ThiSinh ts : danhSach)
            if (ts.getMaSo().equalsIgnoreCase(maSo)) return ts;
        return null;
    }

    @Override
    public ArrayList<ThiSinh> getDanhSach() { return danhSach; }

    // ================================================================
    // MAIN - Nhap lieu tu ban phim
    // ================================================================
    public static void main(String[] args) {
        QuanLySinhVien ql = new QuanLySinhVien();
        Scanner sc = new Scanner(System.in);
        int choice = -1;

        System.out.println("╔═══════════════════════════════════════╗");
        System.out.println("║     QUAN LY SINH VIEN - BUOI 3        ║");
        System.out.println("╚═══════════════════════════════════════╝");

        while (choice != 0) {
            System.out.println("\n====== MENU ======");
            System.out.println("1. Them SV nganh Cong nghe  (Toan + Ly-Hoa)");
            System.out.println("2. Them SV nganh Kinh te    (Toan + Van + Anh)");
            System.out.println("3. Hien thi danh sach");
            System.out.println("4. Tim kiem theo ma so");
            System.out.println("0. Thoat");
            System.out.print("Chon: ");

            try { choice = Integer.parseInt(sc.nextLine().trim()); }
            catch (NumberFormatException e) { System.out.println("Nhap so tu 0-4!"); continue; }

            switch (choice) {
                case 1 -> {
                    System.out.println("\n-- THEM SINH VIEN CONG NGHE --");
                    System.out.print("Ma so     : "); String ms = sc.nextLine().trim();
                    System.out.print("Ho ten    : "); String ht = sc.nextLine().trim();
                    System.out.print("Diem Toan : "); double toan  = Double.parseDouble(sc.nextLine().trim());
                    System.out.print("Diem LyHoa: "); double lyHoa = Double.parseDouble(sc.nextLine().trim());
                    ql.them(new SinhVienCongNghe(ms, ht, toan, lyHoa));
                }
                case 2 -> {
                    System.out.println("\n-- THEM SINH VIEN KINH TE --");
                    System.out.print("Ma so     : "); String ms = sc.nextLine().trim();
                    System.out.print("Ho ten    : "); String ht = sc.nextLine().trim();
                    System.out.print("Diem Toan : "); double toan = Double.parseDouble(sc.nextLine().trim());
                    System.out.print("Diem Van  : "); double van  = Double.parseDouble(sc.nextLine().trim());
                    System.out.print("Diem Anh  : "); double anh  = Double.parseDouble(sc.nextLine().trim());
                    ql.them(new SinhVienKinhTe(ms, ht, toan, van, anh));
                }
                case 3 -> { System.out.println("\n-- DANH SACH --"); ql.hienThi(); }
                case 4 -> {
                    System.out.print("\nNhap ma so: "); String ms = sc.nextLine().trim();
                    ThiSinh ts = ql.timKiem(ms);
                    if (ts != null) { System.out.println("✔ Tim thay:"); ts.hienThiThongTin(); }
                    else System.out.println("✘ Khong tim thay: " + ms);
                }
                case 0 -> System.out.println("Tam biet!");
                default -> System.out.println("Lua chon khong hop le!");
            }
        }
        sc.close();
    }
}
