// Lop NganhHoc - tuong duong Fruit.java cua Duc Hiep
// Quan ly thong tin nganh hoc trong ky tuyen sinh
public class NganhHoc {
    private String maNganh;
    private String tenNganh;
    private int chiTieu;         // So luong chi tieu tuyen sinh
    private double diemChuan;    // Diem chuan nam truoc

    // Constructor mac dinh
    public NganhHoc() {
    }

    // Constructor day du
    public NganhHoc(String maNganh, String tenNganh,
                    int chiTieu, double diemChuan) {
        this.maNganh   = maNganh;
        this.tenNganh  = tenNganh;
        this.chiTieu   = chiTieu;
        this.diemChuan = diemChuan;
    }

    // Getters
    public String getMaNganh()    { return maNganh; }
    public String getTenNganh()   { return tenNganh; }
    public int    getChiTieu()    { return chiTieu; }
    public double getDiemChuan()  { return diemChuan; }

    // Setters
    public void setMaNganh(String maNganh)      { this.maNganh = maNganh; }
    public void setTenNganh(String tenNganh)    { this.tenNganh = tenNganh; }
    public void setChiTieu(int chiTieu)         { this.chiTieu = chiTieu; }
    public void setDiemChuan(double diemChuan)  { this.diemChuan = diemChuan; }

    // Kiem tra con chi tieu
    public boolean conChiTieu(int soLuongDaDangKy) {
        return soLuongDaDangKy < chiTieu;
    }

    @Override
    public String toString() {
        return String.format("%-8s %-25s Chi tieu: %d  Diem chuan: %.1f",
                maNganh, tenNganh, chiTieu, diemChuan);
    }

    public static void main(String[] args) {
        NganhHoc[] danhSach = {
            new NganhHoc("TTNT", "Tri tue nhan tao",   100, 28.5),
            new NganhHoc("CK",   "Co khi",             120, 26.0),
            new NganhHoc("CTT",  "Cong trinh thuy",     80, 25.5),
        };

        System.out.println("===== DANH SACH NGANH HOC =====");
        System.out.printf("%-8s %-25s %-12s %s%n",
                "Ma Nganh", "Ten Nganh", "Chi Tieu", "Diem Chuan");
        System.out.println("-".repeat(60));
        for (NganhHoc n : danhSach) {
            System.out.println(n);
        }
    }
}
