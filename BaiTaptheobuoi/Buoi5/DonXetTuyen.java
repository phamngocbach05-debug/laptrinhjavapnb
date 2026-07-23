import java.util.ArrayList;

// Lop DonXetTuyen - Quan ly don xet tuyen va ket qua tuyen sinh
// Implements ITuyenSinh de quan ly toan bo danh sach
public class DonXetTuyen implements ITuyenSinh {

    private String      maDon;
    private ThiSinh     thiSinh;
    private NganhHocTS  nganhHoc;
    private String      ngayNop;
    private String      trangThai;   // "Cho duyet" / "Trung tuyen" / "Truot"

    // Danh sach toan he thong (static - dung chung)
    private static ArrayList<ThiSinh> danhSachChung = new ArrayList<>();

    // ---- Constructors ----
    public DonXetTuyen() {}

    public DonXetTuyen(String maDon, ThiSinh thiSinh,
                       NganhHocTS nganhHoc, String ngayNop) {
        this.maDon     = maDon;
        this.thiSinh   = thiSinh;
        this.nganhHoc  = nganhHoc;
        this.ngayNop   = ngayNop;
        this.trangThai = "Cho duyet";
    }

    // ---- Getters / Setters ----
    public String     getMaDon()    { return maDon; }
    public ThiSinh    getThiSinh()  { return thiSinh; }
    public NganhHocTS getNganhHoc() { return nganhHoc; }
    public String     getNgayNop()  { return ngayNop; }
    public String     getTrangThai(){ return trangThai; }

    public void setMaDon(String maDon)            { this.maDon = maDon; }
    public void setThiSinh(ThiSinh thiSinh)       { this.thiSinh = thiSinh; }
    public void setNganhHoc(NganhHocTS nganhHoc)  { this.nganhHoc = nganhHoc; }
    public void setNgayNop(String ngayNop)        { this.ngayNop = ngayNop; }

    // ---- Logic xet tuyen ----
    public void xetTuyen() {
        double diem = thiSinh.getTongDiem();
        // Cong diem khu vuc neu la thi sinh ngoai tinh
        if (thiSinh instanceof ThiSinhNgoaiTinh) {
            diem = ((ThiSinhNgoaiTinh) thiSinh).diemSauCong();
        }
        // Cong diem uu tien neu la thi sinh trong tinh co uu tien
        if (thiSinh instanceof ThiSinhTrongTinh) {
            diem = ((ThiSinhTrongTinh) thiSinh).diemSauUuTien();
        }
        this.trangThai = (diem >= nganhHoc.getDiemChuan()) ? "TRUNG TUYEN" : "TRUOT";
    }

    @Override
    public String toString() {
        return String.format("Don [%s] | %s -> Nganh %s | Ngay nop: %s | %s",
                maDon, thiSinh.getHoTen(), nganhHoc.getTenNganh(), ngayNop, trangThai);
    }

    // ---- Implement ITuyenSinh ----
    @Override
    public boolean themThiSinh(ThiSinh ts) {
        if (ts == null) return false;
        for (ThiSinh t : danhSachChung) {
            if (t.getMaSoBD().equalsIgnoreCase(ts.getMaSoBD())) return false;
        }
        danhSachChung.add(ts);
        return true;
    }

    @Override
    public ArrayList<ThiSinh> getDanhSach() { return danhSachChung; }

    @Override
    public ThiSinh timKiem(String maSoBD) {
        for (ThiSinh ts : danhSachChung) {
            if (ts.getMaSoBD().equalsIgnoreCase(maSoBD)) return ts;
        }
        return null;
    }

    @Override
    public void hienThi() {
        System.out.println("===== DANH SACH THI SINH =====");
        for (ThiSinh ts : danhSachChung) System.out.println(ts);
    }

    @Override
    public ArrayList<ThiSinh> locTheoNguong(double nguongDiem) {
        ArrayList<ThiSinh> ketQua = new ArrayList<>();
        for (ThiSinh ts : danhSachChung) {
            if (ts.getTongDiem() >= nguongDiem) ketQua.add(ts);
        }
        return ketQua;
    }

    // ---- Main demo ----
    public static void main(String[] args) {
        NganhHocTS ttnt = new NganhHocTS("TTNT", "Tri tue nhan tao", 100, 28.5, "A00");
        NganhHocTS ck   = new NganhHocTS("CK",   "Co khi",           120, 26.0, "A01");

        ThiSinhTrongTinh ts1 = new ThiSinhTrongTinh(
                "TS001", "Pham Ngoc Bach", "Nam", 28.5, "TTNT", "Ha Noi", true);
        ThiSinhNgoaiTinh ts2 = new ThiSinhNgoaiTinh(
                "TS002", "Nguyen Thi A", "Nu", 27.0, "CK", "Nghe An", "KV1", 15000000);
        ThiSinh ts3 = new ThiSinh(
                "TS003", "Le Van B", "Nam", 25.0, "CK");

        ArrayList<DonXetTuyen> dsDon = new ArrayList<>();
        dsDon.add(new DonXetTuyen("DDK001", ts1, ttnt, "2026-07-01"));
        dsDon.add(new DonXetTuyen("DDK002", ts2, ck,   "2026-07-02"));
        dsDon.add(new DonXetTuyen("DDK003", ts3, ck,   "2026-07-03"));

        System.out.println("===== KET QUA XET TUYEN =====");
        for (DonXetTuyen don : dsDon) {
            don.xetTuyen();
            System.out.println(don);
        }

        System.out.println("\n===== THI SINH CO DIEM >= 27 =====");
        DonXetTuyen ql = new DonXetTuyen();
        ql.themThiSinh(ts1); ql.themThiSinh(ts2); ql.themThiSinh(ts3);
        for (ThiSinh ts : ql.locTheoNguong(27.0)) System.out.println(ts);
    }
}
