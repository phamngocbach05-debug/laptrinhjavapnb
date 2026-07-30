import java.io.*;
import java.util.*;

// Buoi 7: Thao tac voi tep tin (File I/O)
// - Luu danh sach sinh vien vao file text
// - Doc danh sach sinh vien tu file text
public class Buoi7 {

    static final String TEN_FILE = "danhsach.txt";
    static ArrayList<String[]> danhSach = new ArrayList<>();
    static Scanner sc = new Scanner(System.in);

    // ============================================================
    // GHI danh sach vao file text
    // ============================================================
    static void ghiFile() {
        try (PrintWriter pw = new PrintWriter(new FileWriter(TEN_FILE))) {
            for (String[] sv : danhSach) {
                // Moi dong: maSo|hoTen|nganh|tongDiem
                pw.println(sv[0] + "|" + sv[1] + "|" + sv[2] + "|" + sv[3]);
            }
            System.out.println(">> Da luu " + danhSach.size() + " sinh vien vao file: " + TEN_FILE);
        } catch (IOException e) {
            System.out.println(">> LOI ghi file: " + e.getMessage());
        }
    }

    // ============================================================
    // DOC danh sach tu file text
    // ============================================================
    static void docFile() {
        danhSach.clear();
        try (BufferedReader br = new BufferedReader(new FileReader(TEN_FILE))) {
            String dong;
            while ((dong = br.readLine()) != null) {
                String[] parts = dong.split("\\|");
                if (parts.length == 4) danhSach.add(parts);
            }
            System.out.println(">> Da tai " + danhSach.size() + " sinh vien tu file: " + TEN_FILE);
        } catch (FileNotFoundException e) {
            System.out.println(">> LOI: File '" + TEN_FILE + "' khong ton tai!");
        } catch (IOException e) {
            System.out.println(">> LOI doc file: " + e.getMessage());
        }
    }

    // ============================================================
    // HIEN THI danh sach
    // ============================================================
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
            System.out.println("1. Them sinh vien");
            System.out.println("2. Hien thi danh sach");
            System.out.println("3. Luu vao file  (" + TEN_FILE + ")");
            System.out.println("4. Tai tu file   (" + TEN_FILE + ")");
            System.out.println("0. Thoat");
            System.out.print("Chon: ");

            try { choice = Integer.parseInt(sc.nextLine().trim()); }
            catch (NumberFormatException e) { System.out.println("Nhap so!"); continue; }

            switch (choice) {
                case 1 -> {
                    System.out.print("Ma so  : "); String ms = sc.nextLine().trim();
                    System.out.print("Ho ten : "); String ht = sc.nextLine().trim();
                    System.out.print("Nganh  : "); String ng = sc.nextLine().trim();
                    System.out.print("Tong D : "); String td = sc.nextLine().trim();
                    danhSach.add(new String[]{ms, ht, ng, td});
                    System.out.println(">> Da them: " + ht);
                }
                case 2 -> hienThi();
                case 3 -> ghiFile();
                case 4 -> { docFile(); hienThi(); }
                case 0 -> System.out.println("Tam biet!");
                default -> System.out.println("Lua chon khong hop le!");
            }
        }
        sc.close();
    }
}
