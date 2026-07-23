// Lop ThiSinhNgoaiTinh - tuong duong HoaQuaXuatKhau.java cua Duc Hiep
// Ke thua ThiSinh, bo sung thuoc tinh dac thu thi sinh ngoai tinh
public class ThiSinhNgoaiTinh extends ThiSinh {
    private String tinhNguon;     // Tinh que quan
    private String khuVuc;        // KV1 / KV2 / KV2-NT / KV3
    private double phiNhapHoc;    // Phi nhap hoc (ngoai tinh co the cao hon)

    public ThiSinhNgoaiTinh() {}

    public ThiSinhNgoaiTinh(String maSoBD, String hoTen, String gioiTinh,
                             double tongDiem, String nganhDK,
                             String tinhNguon, String khuVuc, double phiNhapHoc) {
        super(maSoBD, hoTen, gioiTinh, tongDiem, nganhDK);
        this.tinhNguon   = tinhNguon;
        this.khuVuc      = khuVuc;
        this.phiNhapHoc  = phiNhapHoc;
    }

    public String getTinhNguon()  { return tinhNguon; }
    public String getKhuVuc()     { return khuVuc; }
    public double getPhiNhapHoc() { return phiNhapHoc; }

    public void setTinhNguon(String tinhNguon)    { this.tinhNguon = tinhNguon; }
    public void setKhuVuc(String khuVuc)          { this.khuVuc = khuVuc; }
    public void setPhiNhapHoc(double phiNhapHoc)  { this.phiNhapHoc = phiNhapHoc; }

    // Diem cong them theo khu vuc: KV1=+1.5, KV2=+1.0, KV2-NT=+0.5, KV3=0
    public double diemCongKhuVuc() {
        switch (khuVuc) {
            case "KV1":    return 1.5;
            case "KV2":    return 1.0;
            case "KV2-NT": return 0.5;
            default:       return 0.0;
        }
    }

    public double diemSauCong() {
        return tongDiem + diemCongKhuVuc();
    }

    @Override
    public String loaiThiSinh() {
        return "Thi sinh ngoai tinh (" + tinhNguon + " - " + khuVuc + ")";
    }

    @Override
    public String toString() {
        return super.toString() + String.format(
                " | Diem cong KV: +%.1f | Diem sau cong: %.1f | Phi: %.0f VND",
                diemCongKhuVuc(), diemSauCong(), phiNhapHoc);
    }

    public static void main(String[] args) {
        ThiSinhNgoaiTinh ts = new ThiSinhNgoaiTinh(
                "TS201", "Nguyen Van An", "Nam", 27.0,
                "Co khi", "Nghe An", "KV1", 15000000);

        System.out.println("===== THI SINH NGOAI TINH =====");
        System.out.println(ts);
        System.out.println("Diem cong khu vuc: " + ts.diemCongKhuVuc());
        System.out.println("Diem sau cong    : " + ts.diemSauCong());
    }
}
