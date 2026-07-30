// Lop con 2: Thi sinh nganh Kinh te - co them mon thi chuyen
public class ThiSinhKinhTe extends ThiSinh {
    private double diemToan;
    private double diemVan;
    private double diemAnhVan;

    public ThiSinhKinhTe() {}

    public ThiSinhKinhTe(String maSo, String hoTen,
                         double diemToan, double diemVan, double diemAnhVan) {
        super(maSo, hoTen, diemToan + diemVan + diemAnhVan); // tong diem
        this.diemToan   = diemToan;
        this.diemVan    = diemVan;
        this.diemAnhVan = diemAnhVan;
    }

    public double getDiemToan()   { return diemToan; }
    public double getDiemVan()    { return diemVan; }
    public double getDiemAnhVan() { return diemAnhVan; }

    @Override
    public void hienThiThongTin() {
        System.out.printf("Ma so: %-8s | Ho ten: %-20s | Toan: %.1f | Van: %.1f | Anh: %.1f | Tong: %.1f  [Kinh te]%n",
                maSo, hoTen, diemToan, diemVan, diemAnhVan, diem);
    }
}
