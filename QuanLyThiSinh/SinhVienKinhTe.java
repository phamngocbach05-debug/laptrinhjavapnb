// Lop con 2: Sinh vien nganh Kinh te (Toan + Van + Anh)
public class SinhVienKinhTe extends ThiSinh {
    private double diemToan;
    private double diemVan;
    private double diemAnh;

    public SinhVienKinhTe() {}

    public SinhVienKinhTe(String maSo, String hoTen,
                          double diemToan, double diemVan, double diemAnh) {
        super(maSo, hoTen, diemToan + diemVan + diemAnh);
        this.diemToan = diemToan;
        this.diemVan  = diemVan;
        this.diemAnh  = diemAnh;
    }

    public double getDiemToan()  { return diemToan; }
    public double getDiemVan()   { return diemVan; }
    public double getDiemAnh()   { return diemAnh; }

    @Override
    public void hienThiThongTin() {
        System.out.printf("Ma so: %-8s | Ho ten: %-20s | Toan: %.1f | Van: %.1f | Anh: %.1f | Tong: %.1f  [Kinh te]%n",
                maSo, hoTen, diemToan, diemVan, diemAnh, tongDiem);
    }
}
