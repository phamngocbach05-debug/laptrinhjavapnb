import java.util.ArrayList;
import java.util.Scanner;

// Lop quan ly: implements IQuanLyThiSinh, co ham main de chay
public class QuanLyThiSinh implements IQuanLyThiSinh {

    private ArrayList<ThiSinh> danhSach = new ArrayList<>();

    @Override
    public void them(ThiSinh ts) {
        danhSach.add(ts);
        System.out.println("✔ Da them: " + ts);
    }

    @Override
    public void hienThi() {
        if (danhSach.isEmpty()) {
            System.out.println("  (Danh sach trong)");
            return;
        }
        System.out.println("--------------------------------------------------------------------");
        for (ThiSinh ts : danhSach)
            ts.hienThiThongTin();
        System.out.println("--------------------------------------------------------------------");
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

    // ================================================================
    // MAIN
    // ================================================================
    public static void main(String[] args) {
        QuanLyThiSinh ql = new QuanLyThiSinh();
        Scanner sc = new Scanner(System.in);
        int choice = -1;

        System.out.println("╔══════════════════════════════════════╗");
        System.out.println("║      QUAN LY THI SINH - BUOI 5       ║");
        System.out.println("╚══════════════════════════════════════╝");

        while (choice != 0) {
            System.out.println("\n====== MENU ======");
            System.out.println("1. Them thi sinh nganh Cong nghe  (Toan + Ly-Hoa)");
            System.out.println("2. Them thi sinh nganh Kinh te    (Toan + Van + Anh)");
            System.out.println("3. Hien thi danh sach");
            System.out.println("4. Tim kiem theo ma so");
            System.out.println("0. Thoat");
            System.out.print("Chon: ");

            try {
                choice = Integer.parseInt(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Vui long nhap so!"); continue;
            }

            switch (choice) {
                case 1 -> {
                    System.out.println("\n-- THEM THI SINH CONG NGHE --");
                    System.out.print("Ma so       : "); String ms = sc.nextLine().trim();
                    System.out.print("Ho ten      : "); String ht = sc.nextLine().trim();
                    System.out.print("Diem Toan   : "); double toan   = Double.parseDouble(sc.nextLine().trim());
                    System.out.print("Diem Ly-Hoa : "); double lyHoa  = Double.parseDouble(sc.nextLine().trim());
                    ql.them(new ThiSinhCongNghe(ms, ht, toan, lyHoa));
                }
                case 2 -> {
                    System.out.println("\n-- THEM THI SINH KINH TE --");
                    System.out.print("Ma so       : "); String ms = sc.nextLine().trim();
                    System.out.print("Ho ten      : "); String ht = sc.nextLine().trim();
                    System.out.print("Diem Toan   : "); double toan = Double.parseDouble(sc.nextLine().trim());
                    System.out.print("Diem Van    : "); double van  = Double.parseDouble(sc.nextLine().trim());
                    System.out.print("Diem Anh    : "); double anh  = Double.parseDouble(sc.nextLine().trim());
                    ql.them(new ThiSinhKinhTe(ms, ht, toan, van, anh));
                }
                case 3 -> {
                    System.out.println("\n-- DANH SACH THI SINH --");
                    ql.hienThi();
                }
                case 4 -> {
                    System.out.print("\nNhap ma so can tim: ");
                    ThiSinh ts = ql.timKiem(sc.nextLine().trim());
                    if (ts != null) { System.out.println("✔ Tim thay:"); ts.hienThiThongTin(); }
                    else System.out.println("✘ Khong tim thay!");
                }
                case 0 -> System.out.println("Tam biet!");
                default -> System.out.println("Lua chon khong hop le!");
            }
        }
        sc.close();
    }
}
