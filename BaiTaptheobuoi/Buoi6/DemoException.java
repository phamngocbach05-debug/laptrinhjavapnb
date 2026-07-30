import java.util.ArrayList;
import java.util.Scanner;

// ============================================================
// CAC LOP EXCEPTION (dinh nghia trong cung 1 file)
// ============================================================

class SinhVienException extends Exception {
    public SinhVienException(String message) { super(message); }
}

class MaSoTonTaiException extends SinhVienException {
    private String maSo;
    public MaSoTonTaiException(String maSo) {
        super("Ma so '" + maSo + "' da ton tai!");
        this.maSo = maSo;
    }
    public String getMaSo() { return maSo; }
}

class DiemKhongHopLeException extends SinhVienException {
    private double diem;
    public DiemKhongHopLeException(double diem) {
        super("Diem " + diem + " khong hop le! Phai tu 0.0 den 10.0");
        this.diem = diem;
    }
    public double getDiem() { return diem; }
}

class SinhVienKhongTonTaiException extends SinhVienException {
    private String maSo;
    public SinhVienKhongTonTaiException(String maSo) {
        super("Khong tim thay sinh vien co ma so: '" + maSo + "'");
        this.maSo = maSo;
    }
    public String getMaSo() { return maSo; }
}

// ============================================================
// MAIN CLASS - CHAY CHINH
// ============================================================
public class DemoException {

    static ArrayList<String[]> danhSach = new ArrayList<>();
    static Scanner sc = new Scanner(System.in);

    static void kiemTraDiem(double d) throws DiemKhongHopLeException {
        if (d < 0 || d > 10) throw new DiemKhongHopLeException(d);
    }

    static void them(String maSo, String hoTen, String nganh, double[] diems)
            throws MaSoTonTaiException, DiemKhongHopLeException {
        for (double d : diems) kiemTraDiem(d);
        for (String[] sv : danhSach)
            if (sv[0].equalsIgnoreCase(maSo)) throw new MaSoTonTaiException(maSo);
        double tong = 0;
        for (double d : diems) tong += d;
        danhSach.add(new String[]{maSo, hoTen, nganh, String.format("%.2f", tong)});
    }

    static String[] timKiem(String maSo) throws SinhVienKhongTonTaiException {
        for (String[] sv : danhSach)
            if (sv[0].equalsIgnoreCase(maSo)) return sv;
        throw new SinhVienKhongTonTaiException(maSo);
    }

    static void hienThi() {
        if (danhSach.isEmpty()) { System.out.println("  (Danh sach trong)"); return; }
        System.out.println("  +---------+--------------------+----------+------+");
        System.out.println("  | Ma so   | Ho ten             | Nganh    | Tong |");
        System.out.println("  +---------+--------------------+----------+------+");
        for (String[] sv : danhSach)
            System.out.printf("  | %-7s | %-18s | %-8s | %-4s |%n",
                    sv[0], sv[1], sv[2], sv[3]);
        System.out.println("  +---------+--------------------+----------+------+");
    }

    public static void main(String[] args) {
        int choice = -1;
        System.out.println("==========================================");
        System.out.println("   QUAN LY SINH VIEN - BUOI 6");
        System.out.println("   Minh hoa Exception Handling");
        System.out.println("==========================================");

        while (choice != 0) {
            System.out.println("\n--- MENU ---");
            System.out.println("1. Them SV Cong nghe (Toan + LyHoa, moi mon 0-10)");
            System.out.println("2. Them SV Kinh te   (Toan + Van + Anh, moi mon 0-10)");
            System.out.println("3. Hien thi danh sach");
            System.out.println("4. Tim kiem theo ma so");
            System.out.println("0. Thoat");
            System.out.print("Chon: ");

            try { choice = Integer.parseInt(sc.nextLine().trim()); }
            catch (NumberFormatException e) { System.out.println("Nhap so!"); continue; }

            switch (choice) {
                case 1 -> {
                    try {
                        System.out.print("Ma so    : "); String ms = sc.nextLine().trim();
                        System.out.print("Ho ten   : "); String ht = sc.nextLine().trim();
                        System.out.print("Diem Toan: "); double t  = Double.parseDouble(sc.nextLine().trim());
                        System.out.print("Diem LyHo: "); double lh = Double.parseDouble(sc.nextLine().trim());
                        them(ms, ht, "CongNghe", new double[]{t, lh});
                        System.out.println(">> Them thanh cong!");
                    } catch (DiemKhongHopLeException e) {
                        System.out.println(">> LOI: " + e.getMessage() + " | Diem sai: " + e.getDiem());
                    } catch (MaSoTonTaiException e) {
                        System.out.println(">> LOI: " + e.getMessage() + " | Ma so: " + e.getMaSo());
                    } catch (NumberFormatException e) {
                        System.out.println(">> LOI: Diem phai la so (vi du: 8.5)");
                    }
                }
                case 2 -> {
                    try {
                        System.out.print("Ma so    : "); String ms = sc.nextLine().trim();
                        System.out.print("Ho ten   : "); String ht = sc.nextLine().trim();
                        System.out.print("Diem Toan: "); double t  = Double.parseDouble(sc.nextLine().trim());
                        System.out.print("Diem Van : "); double v  = Double.parseDouble(sc.nextLine().trim());
                        System.out.print("Diem Anh : "); double a  = Double.parseDouble(sc.nextLine().trim());
                        them(ms, ht, "KinhTe", new double[]{t, v, a});
                        System.out.println(">> Them thanh cong!");
                    } catch (DiemKhongHopLeException e) {
                        System.out.println(">> LOI: " + e.getMessage() + " | Diem sai: " + e.getDiem());
                    } catch (MaSoTonTaiException e) {
                        System.out.println(">> LOI: " + e.getMessage() + " | Ma so: " + e.getMaSo());
                    } catch (NumberFormatException e) {
                        System.out.println(">> LOI: Diem phai la so (vi du: 7.5)");
                    }
                }
                case 3 -> hienThi();
                case 4 -> {
                    System.out.print("Nhap ma so: ");
                    try {
                        String[] sv = timKiem(sc.nextLine().trim());
                        System.out.printf(">> Tim thay: [%s] %s | %s | Tong: %s%n",
                                sv[0], sv[1], sv[2], sv[3]);
                    } catch (SinhVienKhongTonTaiException e) {
                        System.out.println(">> LOI: " + e.getMessage() + " | Ma so: " + e.getMaSo());
                    }
                }
                case 0 -> System.out.println("Tam biet!");
                default -> System.out.println("Lua chon khong hop le!");
            }
        }
        sc.close();
    }
}
