// Lop ThiSinhTrongTinh - tuong duong HoaQuaDiaPhuong.java cua Duc Hiep
// Ke thua ThiSinh, bo sung thuoc tinh dac thu thi sinh trong tinh
public class ThiSinhTrongTinh extends ThiSinh {
    private String tinh;          // Ten tinh/thanh pho
    private boolean uuTien;       // Co duoc uu tien khu vuc khong

    public ThiSinhTrongTinh() {}

    public ThiSinhTrongTinh(String maSoBD, String hoTen, String gioiTinh,
                             double tongDiem, String nganhDK,
                             String tinh, boolean uuTien) {
        super(maSoBD, hoTen, gioiTinh, tongDiem, nganhDK);
        this.tinh    = tinh;
        this.uuTien  = uuTien;
    }

    public String  getTinh()    { return tinh; }
    public boolean isUuTien()   { return uuTien; }
    public void    setTinh(String tinh)       { this.tinh = tinh; }
    public void    setUuTien(boolean uuTien)  { this.uuTien = uuTien; }

    // Diem sau khi cong uu tien khu vuc (0.5 diem neu duoc uu tien)
    public double diemSauUuTien() {
        return uuTien ? tongDiem + 0.5 : tongDiem;
    }

    @Override
    public String loaiThiSinh() {
        return "Thi sinh trong tinh (" + tinh + ")" + (uuTien ? " [Uu tien]" : "");
    }

    @Override
    public String toString() {
        return super.toString() + String.format(" | Diem sau uu tien: %.1f", diemSauUuTien());
    }

    public static void main(String[] args) {
        ThiSinhTrongTinh ts = new ThiSinhTrongTinh(
                "TS101", "Pham Ngoc Bach", "Nam", 28.5,
                "Tri tue nhan tao", "Ha Noi", true);

        System.out.println("===== THI SINH TRONG TINH =====");
        System.out.println(ts);
        System.out.println("Diem sau uu tien: " + ts.diemSauUuTien());
    }
}
