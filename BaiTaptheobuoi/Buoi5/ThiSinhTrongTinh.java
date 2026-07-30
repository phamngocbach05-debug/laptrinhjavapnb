// Lop con 1: Thi sinh trong tinh - co them diem uu tien
public class ThiSinhTrongTinh extends ThiSinh {
    private double diemUuTien;

    public ThiSinhTrongTinh() {}

    public ThiSinhTrongTinh(String maSo, String hoTen, double diem, double diemUuTien) {
        super(maSo, hoTen, diem);   // Goi constructor lop cha
        this.diemUuTien = diemUuTien;
    }

    public double getDiemUuTien() { return diemUuTien; }
    public void setDiemUuTien(double d) { this.diemUuTien = d; }

    // Tinh tong diem sau khi cong uu tien
    public double getDiemSauUuTien() {
        return diem + diemUuTien;
    }

    // Ghi de phuong thuc cua lop cha
    @Override
    public void hienThiThongTin() {
        System.out.printf("Ma so : %-8s | Ho ten: %-20s | Diem: %.1f | +UuTien: %.1f | Tong: %.1f [Trong tinh]%n",
                maSo, hoTen, diem, diemUuTien, getDiemSauUuTien());
    }
}
