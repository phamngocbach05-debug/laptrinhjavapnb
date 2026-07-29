import java.util.ArrayList;
import java.util.Scanner;

public class DemoException {

    static ArrayList<String[]> danhSach = new ArrayList<>();
    static Scanner sc = new Scanner(System.in);

    // ---- Them thi sinh, nem exception neu co loi ----
    static void themThiSinh(String soBD, String hoTen, double diem)
            throws SoBDDaTonTaiException, DiemKhongHopLeException {

        if (diem < 0 || diem > 30)
            throw new DiemKhongHopLeException(diem);

        for (String[] ts : danhSach)
            if (ts[0].equalsIgnoreCase(soBD))
                throw new SoBDDaTonTaiException(soBD);

        danhSach.add(new String[]{soBD, hoTen, String.valueOf(diem)});
    }

    // ---- Tim kiem, nem exception neu khong thay ----
    static String[] timKiem(String soBD) throws ThiSinhKhongTonTaiException {
        for (String[] ts : danhSach)
            if (ts[0].equalsIgnoreCase(soBD)) return ts;
        throw new ThiSinhKhongTonTaiException(soBD);
    }

    // ---- Hien thi danh sach ----
    static void hienThi() {
        if (danhSach.isEmpty()) {
            System.out.println("  (Danh sach trong)");
            return;
        }
        System.out.println("  +----------+----------------------+-------+");
        System.out.println("  | So BD    | Ho Ten               | Diem  |");
        System.out.println("  +----------+----------------------+-------+");
        for (String[] ts : danhSach)
            System.out.printf("  | %-8s | %-20s | %-5s |\n", ts[0], ts[1], ts[2]);
        System.out.println("  +----------+----------------------+-------+");
    }

    // ---- Menu chinh ----
    public static void main(String[] args) {
        System.out.println("╔══════════════════════════════════════╗");
        System.out.println("║   QUAN LY TUYEN SINH - BUOI 6        ║");
        System.out.println("║   Minh hoa Exception Handling        ║");
        System.out.println("╚══════════════════════════════════════╝");

        int choice = -1;
        while (choice != 0) {
            System.out.println("\n====== MENU ======");
            System.out.println("1. Them thi sinh");
            System.out.println("2. Tim kiem thi sinh");
            System.out.println("3. Hien thi danh sach");
            System.out.println("0. Thoat");
            System.out.print("Chon: ");

            try {
                choice = Integer.parseInt(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("✘ Vui long nhap so tu 0-3!");
                continue;
            }

            switch (choice) {
                case 1 -> menuThem();
                case 2 -> menuTimKiem();
                case 3 -> { System.out.println("\n-- Danh sach thi sinh --"); hienThi(); }
                case 0 -> System.out.println("Tam biet!");
                default -> System.out.println("✘ Lua chon khong hop le!");
            }
        }
        sc.close();
    }

    // ---- Menu them ----
    static void menuThem() {
        System.out.println("\n-- THEM THI SINH --");
        System.out.print("Nhap So Bao Danh : ");
        String soBD = sc.nextLine().trim();

        System.out.print("Nhap Ho Ten      : ");
        String hoTen = sc.nextLine().trim();

        System.out.print("Nhap Tong Diem   : ");
        String diemStr = sc.nextLine().trim();

        double diem;
        try {
            diem = Double.parseDouble(diemStr);
        } catch (NumberFormatException e) {
            System.out.println("✘ Loi: Tong diem phai la so! (Vi du: 27.5)");
            return;
        }

        try {
            themThiSinh(soBD, hoTen, diem);
            System.out.println("✔ Them thanh cong: [" + soBD + "] " + hoTen + " - " + diem);

        } catch (DiemKhongHopLeException e) {
            System.out.println("✘ DiemKhongHopLeException: " + e.getMessage());
            System.out.println("  >> Diem nhap vao: " + e.getDiem());

        } catch (SoBDDaTonTaiException e) {
            System.out.println("✘ SoBDDaTonTaiException: " + e.getMessage());
            System.out.println("  >> So bao danh bi trung: " + e.getSoBD());
        }
    }

    // ---- Menu tim kiem ----
    static void menuTimKiem() {
        System.out.println("\n-- TIM KIEM THI SINH --");
        System.out.print("Nhap So Bao Danh can tim: ");
        String soBD = sc.nextLine().trim();

        try {
            String[] ts = timKiem(soBD);
            System.out.println("✔ Tim thay:");
            System.out.println("  So BD  : " + ts[0]);
            System.out.println("  Ho Ten : " + ts[1]);
            System.out.println("  Diem   : " + ts[2]);

        } catch (ThiSinhKhongTonTaiException e) {
            System.out.println("✘ ThiSinhKhongTonTaiException: " + e.getMessage());
            System.out.println("  >> SoBD khong ton tai: " + e.getSoBD());
        }
    }
}
