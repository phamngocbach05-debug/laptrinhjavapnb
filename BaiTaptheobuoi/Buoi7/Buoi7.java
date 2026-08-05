import java.io.*;
import java.util.*;

// Buoi 7: Thao tac voi File - Nhap lieu + Ghi/Doc file
public class Buoi7 {

    static final String TEN_FILE = "danhsach.txt";
    static ArrayList<String[]> danhSach = new ArrayList<>();
    static Scanner sc = new Scanner(System.in);

    // Kiem tra diem hop le (0 - 10)
    static double nhapDiem(String tenMon) {
        while (true) {
            System.out.print(tenMon);
            try {
                double d = Double.parseDouble(sc.nextLine().trim());
                if (d < 0 || d > 10) {
                    System.out.println("   >> LOI: Diem " + tenMon.trim() + "  Nhap lai.");
                } else {
                    return d;
                }
            } catch (NumberFormatException e) {
                System.out.println("   >> LOI: Phai nhap so! Nhap lai.");
            }
        }
    }

    // ============================================================
    // THEM thi sinh (nhap tu ban phim)
    // ============================================================
    static void themThiSinh(String loai) {
        System.out.print("Ma so  : "); String ms = sc.nextLine().trim();
        System.out.print("Ho ten : "); String ht = sc.nextLine().trim();

        if (loai.equals("CN")) {
            double toan = nhapDiem("Diem Toan  : ");
            double ly   = nhapDiem("Diem Ly    : ");
            double hoa  = nhapDiem("Diem Hoa   : ");
            double tong = toan + ly + hoa;
            danhSach.add(new String[]{ms, ht, "CongNghe",
                    String.format("%.1f+%.1f+%.1f", toan, ly, hoa),
                    String.format("%.1f", tong)});
        } else {
            double toan = nhapDiem("Diem Toan  : ");
            double van  = nhapDiem("Diem Van   : ");
            double anh  = nhapDiem("Diem Anh   : ");
            double tong = toan + van + anh;
            danhSach.add(new String[]{ms, ht, "KinhTe",
                    String.format("%.1f+%.1f+%.1f", toan, van, anh),
                    String.format("%.1f", tong)});
        }
        System.out.println(">> Da them: " + ht);
    }


    // ============================================================
    // HIEN THI danh sach
    // ============================================================
    static void hienThi() {
        if (danhSach.isEmpty()) { System.out.println("  (Danh sach trong)"); return; }
        System.out.println("  +---------+--------------------+----------+-----------------+-------+");
        System.out.println("  | Ma so   | Ho ten             | Nganh    | Cac mon diem    | Tong  |");
        System.out.println("  +---------+--------------------+----------+-----------------+-------+");
        for (String[] sv : danhSach)
            System.out.printf("  | %-7s | %-18s | %-8s | %-15s | %-5s |%n",
                    sv[0], sv[1], sv[2], sv[3], sv[4]);
        System.out.println("  +---------+--------------------+----------+-----------------+-------+");
        System.out.println("  Tong: " + danhSach.size() + " thi sinh");
    }

    // ============================================================
    // GHI danh sach vao file
    // ============================================================
    static void ghiFile() {
        try (PrintWriter pw = new PrintWriter(new FileWriter(TEN_FILE))) {
            for (String[] sv : danhSach)
                pw.println(sv[0] + "|" + sv[1] + "|" + sv[2] + "|" + sv[3] + "|" + sv[4]);
            System.out.println(">> Da luu " + danhSach.size() + " thi sinh vao: " + TEN_FILE);
        } catch (IOException e) {
            System.out.println(">> LOI ghi file: " + e.getMessage());
        }
    }

    // ============================================================
    // DOC danh sach tu file
    // ============================================================
    static void docFile() {
        danhSach.clear();
        try (BufferedReader br = new BufferedReader(new FileReader(TEN_FILE))) {
            String dong;
            while ((dong = br.readLine()) != null) {
                String[] parts = dong.split("\\|");
                if (parts.length == 5) danhSach.add(parts);
            }
            System.out.println(">> Da tai " + danhSach.size() + " thi sinh tu: " + TEN_FILE);
            hienThi();
        } catch (FileNotFoundException e) {
            System.out.println(">> LOI: File '" + TEN_FILE + "' chua ton tai!");
        } catch (IOException e) {
            System.out.println(">> LOI doc file: " + e.getMessage());
        }
    }

    // ============================================================
    // MAIN
    // ============================================================
    public static void main(String[] args) {
        int choice = -1;
        System.out.println("==========================================");
        System.out.println("   BUOI 7: THAO TAC VOI FILE");
        System.out.println("==========================================");

        while (choice != 0) {
            System.out.println("\n--- MENU ---");
            System.out.println("1. Nhap TS Cong nghe ");
            System.out.println("2. Nhap TS Kinh te   ");
            System.out.println("3. Hien thi danh sach");
            System.out.println("4. Luu vao file  --> " + TEN_FILE);
            System.out.println("5. Tai tu file   <-- " + TEN_FILE);
            System.out.println("0. Thoat");
            System.out.print("Chon: ");

            try { choice = Integer.parseInt(sc.nextLine().trim()); }
            catch (NumberFormatException e) { System.out.println("Nhap so!"); continue; }

            switch (choice) {
                case 1 -> { System.out.println("\n-- NHAP TS Cong nghe --"); themThiSinh("CN"); }
                case 2 -> { System.out.println("\n-- NHAP TS Kinh te --");   themThiSinh("KT"); }
                case 3 -> hienThi();
                case 4 -> ghiFile();
                case 5 -> docFile();
                case 0 -> System.out.println("Tam biet!");
                default -> System.out.println("Lua chon khong hop le!");
            }
        }
        sc.close();
    }
}
