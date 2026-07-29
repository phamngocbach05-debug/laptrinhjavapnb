// Demo su dung Exception trong he thong Quan ly Tuyen sinh
public class DemoException {

    // Danh sach gia lap
    static String[] dsSoBD = {"TS001", "TS002", "TS003"};

    // ============================================================
    // Ham gia lap: Them thi sinh - nem exception khi co loi
    // ============================================================
    static void themThiSinh(String soBD, double diem)
            throws SoBDDaTonTaiException, DiemKhongHopLeException {

        // Kiem tra diem hop le
        if (diem < 0 || diem > 30) {
            throw new DiemKhongHopLeException(diem);
        }

        // Kiem tra trung so bao danh
        for (String id : dsSoBD) {
            if (id.equalsIgnoreCase(soBD)) {
                throw new SoBDDaTonTaiException(soBD);
            }
        }

        System.out.println("✔ Them thanh cong: " + soBD + " | Diem: " + diem);
    }

    // ============================================================
    // Ham gia lap: Tim kiem - nem exception neu khong thay
    // ============================================================
    static String timKiem(String soBD) throws ThiSinhKhongTonTaiException {
        for (String id : dsSoBD) {
            if (id.equalsIgnoreCase(soBD)) return id;
        }
        throw new ThiSinhKhongTonTaiException(soBD);
    }

    // ============================================================
    // MAIN - Chay demo
    // ============================================================
    public static void main(String[] args) {
        System.out.println("========================================");
        System.out.println("  DEMO EXCEPTION - Quan ly Tuyen sinh  ");
        System.out.println("========================================\n");

        // ---- Demo 1: Them hop le ----
        System.out.println("--- TH1: Them thi sinh hop le ---");
        try {
            themThiSinh("TS999", 27.5);
        } catch (TuyenSinhException e) {
            System.out.println("✘ Loi: " + e.getMessage());
        }

        // ---- Demo 2: Diem khong hop le ----
        System.out.println("\n--- TH2: Diem vuot ngoai 0-30 ---");
        try {
            themThiSinh("TS100", 35.0);
        } catch (DiemKhongHopLeException e) {
            System.out.println("✘ DiemKhongHopLeException: " + e.getMessage());
            System.out.println("  Diem bi loi: " + e.getDiem());
        } catch (SoBDDaTonTaiException e) {
            System.out.println("✘ SoBDDaTonTaiException: " + e.getMessage());
        }

        // ---- Demo 3: Trung so bao danh ----
        System.out.println("\n--- TH3: So bao danh da ton tai ---");
        try {
            themThiSinh("TS001", 25.0);
        } catch (DiemKhongHopLeException e) {
            System.out.println("✘ DiemKhongHopLeException: " + e.getMessage());
        } catch (SoBDDaTonTaiException e) {
            System.out.println("✘ SoBDDaTonTaiException: " + e.getMessage());
            System.out.println("  SoBD bi trung: " + e.getSoBD());
        }

        // ---- Demo 4: Tim kiem thanh cong ----
        System.out.println("\n--- TH4: Tim kiem hop le ---");
        try {
            String ket = timKiem("TS002");
            System.out.println("✔ Tim thay thi sinh: " + ket);
        } catch (ThiSinhKhongTonTaiException e) {
            System.out.println("✘ ThiSinhKhongTonTaiException: " + e.getMessage());
        }

        // ---- Demo 5: Tim kiem khong thay ----
        System.out.println("\n--- TH5: Tim thi sinh khong ton tai ---");
        try {
            timKiem("TS999");
        } catch (ThiSinhKhongTonTaiException e) {
            System.out.println("✘ ThiSinhKhongTonTaiException: " + e.getMessage());
            System.out.println("  SoBD khong tim thay: " + e.getSoBD());
        }

        // ---- Demo 6: Bat lop cha (bat tat ca) ----
        System.out.println("\n--- TH6: Dung lop cha de bat moi loi ---");
        try {
            themThiSinh("TS001", -5.0);  // 2 loi: diem < 0 va trung SoBD
        } catch (TuyenSinhException e) {
            // TuyenSinhException bat duoc ca 2 loai con
            System.out.println("✘ TuyenSinhException (bat cha): " + e.getMessage());
            System.out.println("  Loai cu the: " + e.getClass().getSimpleName());
        }

        System.out.println("\n========== KET THUC DEMO ==========");
    }
}
