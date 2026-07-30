import java.util.ArrayList;
import java.util.Scanner;

// Lop chinh: implements IQuanLy, co ham main
public class QuanLyThiSinh implements IQuanLy {

    private ArrayList<ThiSinh> danhSach = new ArrayList<>();

    @Override
    public void them(ThiSinh ts) {
        danhSach.add(ts);
        System.out.println("✔ Da them: " + ts);
    }

    @Override
    public void hienThi() {
        if (danhSach.isEmpty()) { System.out.println("  (Danh sach trong)"); return; }
        System.out.println("-------------------------------------------------------------------");
        for (ThiSinh ts : danhSach) ts.hienThiThongTin();
        System.out.println("-------------------------------------------------------------------");
        System.out.println("  Tong: " + danhSach.size() + " thi sinh");
    }

    @Override
    public ThiSinh timKiem(String maSo) {
        for (ThiSinh ts : danhSach)
            if (ts.getMaSo().equalsIgnoreCase(maSo)) return ts;
        return null;
    }

    @Override
    public ArrayList<ThiSinh> getDanhSach() { return danhSach; }

    // Sap xep theo tong diem giam dan
    public void sapXep() {
        danhSach.sort((a, b) -> Double.compare(b.getTongDiem(), a.getTongDiem()));
        System.out.println("✔ Da sap xep theo diem giam dan!");
    }

    // ================================================================
    // MAIN
    // ================================================================
    public static void main(String[] args) {
        QuanLyThiSinh ql = new QuanLyThiSinh();
        Scanner sc = new Scanner(System.in);
        int choice = -1;

        System.out.println("╔════════════════════════════════════════╗");
        System.out.println("║       QUAN LY THI SINH                 ║");
        System.out.println("╚════════════════════════════════════════╝");

        while (choice != 0) {
            System.out.println("\n====== MENU ======");
            System.out.println("1. Them SV Cong nghe  (Toan + Ly-Hoa)");
            System.out.println("2. Them SV Kinh te    (Toan + Van + Anh)");
            System.out.println("3. Hien thi danh sach");
            System.out.println("4. Tim kiem theo ma so");
            System.out.println("5. Sap xep theo diem giam dan");
            System.out.println("0. Thoat");
            System.out.print("Chon: ");

            try { choice = Integer.parseInt(sc.nextLine().trim()); }
            catch (NumberFormatException e) { System.out.println("Nhap so!"); continue; }

            switch (choice) {
                case 1 -> {
                    System.out.println("\n-- THEM SV CONG NGHE --");
                    System.out.print("Ma so     : "); String ms = sc.nextLine().trim();
                    System.out.print("Ho ten    : "); String ht = sc.nextLine().trim();
                    System.out.print("Diem Toan : "); double toan  = Double.parseDouble(sc.nextLine().trim());
                    System.out.print("Diem LyHoa: "); double lyHoa = Double.parseDouble(sc.nextLine().trim());
                    ql.them(new SinhVienCongNghe(ms, ht, toan, lyHoa));
                }
                case 2 -> {
                    System.out.println("\n-- THEM SV KINH TE --");
                    System.out.print("Ma so     : "); String ms = sc.nextLine().trim();
                    System.out.print("Ho ten    : "); String ht = sc.nextLine().trim();
                    System.out.print("Diem Toan : "); double toan = Double.parseDouble(sc.nextLine().trim());
                    System.out.print("Diem Van  : "); double van  = Double.parseDouble(sc.nextLine().trim());
                    System.out.print("Diem Anh  : "); double anh  = Double.parseDouble(sc.nextLine().trim());
                    ql.them(new SinhVienKinhTe(ms, ht, toan, van, anh));
                }
                case 3 -> { System.out.println(); ql.hienThi(); }
                case 4 -> {
                    System.out.print("\nNhap ma so: "); ThiSinh ts = ql.timKiem(sc.nextLine().trim());
                    if (ts != null) { System.out.println("✔ Tim thay:"); ts.hienThiThongTin(); }
                    else System.out.println("✘ Khong tim thay!");
                }
                case 5 -> ql.sapXep();
                case 0 -> System.out.println("Tam biet!");
                default -> System.out.println("Lua chon khong hop le!");
            }
        }
        sc.close();
    }
}
