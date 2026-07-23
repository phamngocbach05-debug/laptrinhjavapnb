import java.util.ArrayList;

// Lop DonDangKy - Quan ly don dang ky tuyen sinh cua mot thi sinh
public class DonDangKy {
    private String maDon;
    private Thisinh thisinh;
    private NganhHoc nganhHoc;
    private String ngayNop;      // Ngay nop don
    private String trangThai;    // "Cho duyet" / "Duoc chap nhan" / "Tu choi"

    // Constructor mac dinh
    public DonDangKy() {
    }

    // Constructor day du
    public DonDangKy(String maDon, Thisinh thisinh,
                     NganhHoc nganhHoc, String ngayNop) {
        this.maDon    = maDon;
        this.thisinh  = thisinh;
        this.nganhHoc = nganhHoc;
        this.ngayNop  = ngayNop;
        this.trangThai = "Cho duyet";
    }

    // Getters
    public String    getMaDon()     { return maDon; }
    public Thisinh   getThisinh()   { return thisinh; }
    public NganhHoc  getNganhHoc()  { return nganhHoc; }
    public String    getNgayNop()   { return ngayNop; }
    public String    getTrangThai() { return trangThai; }

    // Setters
    public void setMaDon(String maDon)         { this.maDon = maDon; }
    public void setThisinh(Thisinh thisinh)    { this.thisinh = thisinh; }
    public void setNganhHoc(NganhHoc nganhHoc) { this.nganhHoc = nganhHoc; }
    public void setNgayNop(String ngayNop)     { this.ngayNop = ngayNop; }
    public void setTrangThai(String trangThai) { this.trangThai = trangThai; }

    // Xu ly don: chap nhan neu tong diem >= diem chuan
    public void xetDuyet() {
        if (thisinh.getTongDiem() >= nganhHoc.getDiemChuan()) {
            this.trangThai = "Duoc chap nhan";
        } else {
            this.trangThai = "Tu choi (Khong du diem chuan)";
        }
    }

    @Override
    public String toString() {
        return String.format("Don [%s] | %s dang ky nganh %s | Tong diem: %.1f | Trang thai: %s",
                maDon, thisinh.getHoTen(), nganhHoc.getTenNganh(),
                thisinh.getTongDiem(), trangThai);
    }

    public static void main(String[] args) {
        // Tao cac nganh hoc
        NganhHoc ttnt = new NganhHoc("TTNT", "Tri tue nhan tao", 100, 28.5);
        NganhHoc ck   = new NganhHoc("CK",   "Co khi",            120, 26.0);

        // Tao cac thi sinh
        Thisinh ts1 = new Thisinh("TS001", "Pham Ngoc Bach", "Nam", "TTNT", 29.5);
        Thisinh ts2 = new Thisinh("TS002", "Nguyen Thi A",   "Nu",  "CK",   25.0);
        Thisinh ts3 = new Thisinh("TS003", "Le Van B",       "Nam", "TTNT", 27.0);

        // Tao cac don dang ky
        ArrayList<DonDangKy> danhSachDon = new ArrayList<>();
        danhSachDon.add(new DonDangKy("DDK001", ts1, ttnt, "2026-07-01"));
        danhSachDon.add(new DonDangKy("DDK002", ts2, ck,   "2026-07-02"));
        danhSachDon.add(new DonDangKy("DDK003", ts3, ttnt, "2026-07-03"));

        // Xet duyet tat ca don
        System.out.println("===== KET QUA XET TUYEN =====");
        for (DonDangKy don : danhSachDon) {
            don.xetDuyet();
            System.out.println(don);
        }
    }
}
