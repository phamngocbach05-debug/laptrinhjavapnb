// Lop NganhHoc - Quan ly thong tin nganh hoc va chi tieu tuyen sinh
public class NganhHoc {
    private String maNganh;
    private String tenNganh;
    private int    chiTieu;
    private double diemChuan;
    private String toHop;       // To hop mon xet tuyen (vd: A00, A01, D01)

    public NganhHoc() {}

    public NganhHoc(String maNganh, String tenNganh,
                    int chiTieu, double diemChuan, String toHop) {
        this.maNganh  = maNganh;
        this.tenNganh = tenNganh;
        this.chiTieu  = chiTieu;
        this.diemChuan = diemChuan;
        this.toHop    = toHop;
    }

    public String getMaNganh()   { return maNganh; }
    public String getTenNganh()  { return tenNganh; }
    public int    getChiTieu()   { return chiTieu; }
    public double getDiemChuan() { return diemChuan; }
    public String getToHop()     { return toHop; }

    public void setMaNganh(String maNganh)      { this.maNganh = maNganh; }
    public void setTenNganh(String tenNganh)    { this.tenNganh = tenNganh; }
    public void setChiTieu(int chiTieu)         { this.chiTieu = chiTieu; }
    public void setDiemChuan(double diemChuan)  { this.diemChuan = diemChuan; }
    public void setToHop(String toHop)          { this.toHop = toHop; }

    @Override
    public String toString() {
        return String.format("%-8s %-25s To hop: %-5s Chi tieu: %3d  Diem chuan: %.1f",
                maNganh, tenNganh, toHop, chiTieu, diemChuan);
    }

    public static void main(String[] args) {
        NganhHoc[] ds = {
            new NganhHoc("TTNT", "Tri tue nhan tao",   100, 28.5, "A00"),
            new NganhHoc("CK",   "Co khi",              120, 26.0, "A01"),
            new NganhHoc("CTT",  "Cong trinh thuy",      80, 25.5, "A00"),
            new NganhHoc("CNTT", "Cong nghe thong tin", 150, 27.5, "A01"),
        };

        System.out.println("===== DANH SACH NGANH HOC =====");
        for (NganhHoc n : ds) System.out.println(n);
    }
}
