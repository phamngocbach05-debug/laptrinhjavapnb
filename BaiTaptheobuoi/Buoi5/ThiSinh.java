// Lop cha ThiSinh - tuong duong HoaQua.java cua Duc Hiep
// Day la lop co so chua thong tin chung cua thi sinh
public class ThiSinh {
    protected String maSoBD;
    protected String hoTen;
    protected String gioiTinh;   // Nam / Nu
    protected double tongDiem;
    protected String nganhDK;    // Nganh dang ky

    public ThiSinh() {}

    public ThiSinh(String maSoBD, String hoTen, String gioiTinh,
                   double tongDiem, String nganhDK) {
        this.maSoBD   = maSoBD;
        this.hoTen    = hoTen;
        this.gioiTinh = gioiTinh;
        this.tongDiem = tongDiem;
        this.nganhDK  = nganhDK;
    }

    public String getMaSoBD()   { return maSoBD; }
    public String getHoTen()    { return hoTen; }
    public String getGioiTinh() { return gioiTinh; }
    public double getTongDiem() { return tongDiem; }
    public String getNganhDK()  { return nganhDK; }

    public void setMaSoBD(String maSoBD)     { this.maSoBD = maSoBD; }
    public void setHoTen(String hoTen)       { this.hoTen = hoTen; }
    public void setGioiTinh(String gioiTinh) { this.gioiTinh = gioiTinh; }
    public void setTongDiem(double tongDiem)  { this.tongDiem = tongDiem; }
    public void setNganhDK(String nganhDK)   { this.nganhDK = nganhDK; }

    // Phuong thuc co the override o lop con
    public String loaiThiSinh() {
        return "Thi sinh thuong";
    }

    public String hocBong() {
        return tongDiem >= 29 ? "Co hoc bong" : "Khong co hoc bong";
    }

    @Override
    public String toString() {
        return String.format("[%s] %s | %s | Nganh: %s | Diem: %.1f | %s | %s",
                maSoBD, hoTen, gioiTinh, nganhDK, tongDiem, loaiThiSinh(), hocBong());
    }
}
