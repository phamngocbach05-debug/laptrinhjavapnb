import java.util.ArrayList;
import java.util.Scanner;

// kế thừa
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
        System.out.println("------------------------------------------------------------------");
        for (ThiSinh ts : danhSach)
            ts.hienThiThongTin();
        System.out.println("------------------------------------------------------------------");
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

    // Sắp xếp điểm
    public void sapXepTheoDiem() {
        for (int i = 0; i < danhSach.size() - 1; i++)
            for (int j = 0; j < danhSach.size() - 1 - i; j++)
                if (danhSach.get(j).getTongDiem() < danhSach.get(j + 1).getTongDiem()) {
                    ThiSinh tam = danhSach.get(j);
                    danhSach.set(j, danhSach.get(j + 1));
                    danhSach.set(j + 1, tam);
                }
        System.out.println("✔ Da sap xep theo diem giam dan!");
    }

    // Tinh diem trung binh
    public double diemTrungBinh() {
        if (danhSach.isEmpty()) return 0;
        double tong = 0;
        for (ThiSinh ts : danhSach) tong += ts.getTongDiem();
        return tong / danhSach.size();
    }

    
    public static void main(String[] args) {
        QuanLyThiSinh ql = new QuanLyThiSinh();
        Scanner sc = new Scanner(System.in);
        int choice = -1;

        System.out.println("╔═══════════════════════════════════════╗");
        System.out.println("║     QUAN LY THI SINH - BUOI 5         ║");
        System.out.println("║     Ke thua & Da hinh                 ║");
        System.out.println("╚═══════════════════════════════════════╝");

        while (choice != 0) {
            System.out.println("\n====== MENU ======");
            System.out.println("1. Them TS Cong nghe  (Toan + Ly-Hoa)");
            System.out.println("2. Them TS Kinh te    (Toan + Van + Anh)");
            System.out.println("3. Hien thi danh sach");
            System.out.println("4. Tim kiem theo ma so");
            System.out.println("5. Sap xep theo diem giam dan");
            System.out.println("6. Xem diem trung binh");
            System.out.println("0. Thoat");
            System.out.print("Chon: ");

            try { choice = Integer.parseInt(sc.nextLine().trim()); }
            catch (NumberFormatException e) { System.out.println("Nhap so!"); continue; }

            switch (choice) {
                case 1 -> {
                    System.out.println("\n-- THEM TS Cong nghe --");
                    System.out.print("Ma so     : "); String ms = sc.nextLine().trim();
                    System.out.print("Ho ten    : "); String ht = sc.nextLine().trim();
                    System.out.print("Diem Toan : "); double toan  = Double.parseDouble(sc.nextLine().trim());
                    System.out.print("Diem LyHoa: "); double lyHoa = Double.parseDouble(sc.nextLine().trim());
                    ql.them(new ThiSinhCongNghe(ms, ht, toan, lyHoa));
                }
                case 2 -> {
                    System.out.println("\n-- THEM TS Kinh te --");
                    System.out.print("Ma so     : "); String ms = sc.nextLine().trim();
                    System.out.print("Ho ten    : "); String ht = sc.nextLine().trim();
                    System.out.print("Diem Toan : "); double toan = Double.parseDouble(sc.nextLine().trim());
                    System.out.print("Diem Van  : "); double van  = Double.parseDouble(sc.nextLine().trim());
                    System.out.print("Diem Anh  : "); double anh  = Double.parseDouble(sc.nextLine().trim());
                    ql.them(new ThiSinhKinhTe(ms, ht, toan, van, anh));
                }
                case 3 -> { System.out.println("\n-- DANH SACH --"); ql.hienThi(); }
                case 4 -> {
                    System.out.print("\nNhap ma so: "); String ms = sc.nextLine().trim();
                    ThiSinh ts = ql.timKiem(ms);
                    if (ts != null) { System.out.println("✔ Tim thay:"); ts.hienThiThongTin(); }
                    else System.out.println("✘ Khong tim thay: " + ms);
                }
                case 5 -> ql.sapXepTheoDiem();
                case 6 -> System.out.printf("Diem trung binh: %.2f%n", ql.diemTrungBinh());
                case 0 -> System.out.println("Tam biet!");
                default -> System.out.println("Lua chon khong hop le!");
            }
        }
        sc.close();
    }
}
