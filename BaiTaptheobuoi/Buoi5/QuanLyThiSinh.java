import java.util.ArrayList;
import java.util.Scanner;

// Lop quan ly: implements IQuanLyThiSinh, co ham main de chay
public class QuanLyThiSinh implements IQuanLyThiSinh {

    private ArrayList<ThiSinh> danhSach = new ArrayList<>();

    // ---- Them thi sinh ----
    @Override
    public void them(ThiSinh ts) {
        danhSach.add(ts);
        System.out.println("✔ Da them: " + ts);
    }

    // ---- Hien thi toan bo ----
    @Override
    public void hienThi() {
        if (danhSach.isEmpty()) {
            System.out.println("  (Danh sach trong)");
            return;
        }
        System.out.println("-----------------------------------------------------------");
        for (ThiSinh ts : danhSach) {
            ts.hienThiThongTin();   // Goi dong: lop con nao chay phuong thuc cua lop do
        }
        System.out.println("-----------------------------------------------------------");
        System.out.println("  Tong so thi sinh: " + danhSach.size());
    }

    // ---- Tim kiem theo ma so ----
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
        QuanLyThiSinh ql = new QuanLyThiSinh();
        Scanner sc = new Scanner(System.in);
        int choice = -1;

        System.out.println("╔══════════════════════════════════╗");
        System.out.println("║     QUAN LY THI SINH - BUOI 5    ║");
        System.out.println("╚══════════════════════════════════╝");

        while (choice != 0) {
            System.out.println("\n====== MENU ======");
            System.out.println("1. Them thi sinh trong tinh");
            System.out.println("2. Them thi sinh ngoai tinh");
            System.out.println("3. Hien thi danh sach");
            System.out.println("4. Tim kiem theo ma so");
            System.out.println("0. Thoat");
            System.out.print("Chon: ");

            try {
                choice = Integer.parseInt(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Vui long nhap so!");
                continue;
            }

            switch (choice) {
                case 1 -> {
                    System.out.println("\n-- THEM THI SINH TRONG TINH --");
                    System.out.print("Ma so   : "); String ms = sc.nextLine().trim();
                    System.out.print("Ho ten  : "); String ht = sc.nextLine().trim();
                    System.out.print("Diem    : "); double d  = Double.parseDouble(sc.nextLine().trim());
                    System.out.print("Diem uu tien: "); double ut = Double.parseDouble(sc.nextLine().trim());
                    ql.them(new ThiSinhTrongTinh(ms, ht, d, ut));
                }
                case 2 -> {
                    System.out.println("\n-- THEM THI SINH NGOAI TINH --");
                    System.out.print("Ma so   : "); String ms = sc.nextLine().trim();
                    System.out.print("Ho ten  : "); String ht = sc.nextLine().trim();
                    System.out.print("Diem    : "); double d  = Double.parseDouble(sc.nextLine().trim());
                    System.out.print("Tinh    : "); String ti = sc.nextLine().trim();
                    System.out.print("Phi KTX : "); double phi = Double.parseDouble(sc.nextLine().trim());
                    ql.them(new ThiSinhNgoaiTinh(ms, ht, d, ti, phi));
                }
                case 3 -> {
                    System.out.println("\n-- DANH SACH THI SINH --");
                    ql.hienThi();
                }
                case 4 -> {
                    System.out.print("\nNhap ma so can tim: ");
                    String ms = sc.nextLine().trim();
                    ThiSinh ts = ql.timKiem(ms);
                    if (ts != null) {
                        System.out.println("✔ Tim thay:");
                        ts.hienThiThongTin();
                    } else {
                        System.out.println("✘ Khong tim thay ma so: " + ms);
                    }
                }
                case 0 -> System.out.println("Tam biet!");
                default -> System.out.println("Lua chon khong hop le!");
            }
        }
        sc.close();
    }
}
