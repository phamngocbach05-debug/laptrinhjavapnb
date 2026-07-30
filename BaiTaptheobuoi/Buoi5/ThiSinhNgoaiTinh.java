// Lop con 2: Thi sinh ngoai tinh - co them phi ky tuc xa
public class ThiSinhNgoaiTinh extends ThiSinh {
    private String tinh;
    private double phiKTX;

    public ThiSinhNgoaiTinh() {}

    public ThiSinhNgoaiTinh(String maSo, String hoTen, double diem, String tinh, double phiKTX) {
        super(maSo, hoTen, diem);   // Goi constructor lop cha
        this.tinh   = tinh;
        this.phiKTX = phiKTX;
    }

    public String getTinh()    { return tinh; }
    public double getPhiKTX()  { return phiKTX; }
    public void setTinh(String tinh)      { this.tinh = tinh; }
    public void setPhiKTX(double phiKTX)  { this.phiKTX = phiKTX; }

    // Ghi de phuong thuc cua lop cha
    @Override
    public void hienThiThongTin() {
        System.out.printf("Ma so : %-8s | Ho ten: %-20s | Diem: %.1f | Tinh: %-12s | PhiKTX: %.0f [Ngoai tinh]%n",
                maSo, hoTen, diem, tinh, phiKTX);
    }
}
