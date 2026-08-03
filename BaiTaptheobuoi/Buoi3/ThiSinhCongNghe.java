// Lop con 1: Sinh vien nganh Cong nghe
// Thi 2 mon: Toan + Ly-Hoa
public class ThiSinhCongNghe extends ThiSinh {
    private double diemToan;
    private double diemLyHoa;

    public ThiSinhCongNghe() {}

    public ThiSinhCongNghe(String maSo, String hoTen,
                            double diemToan, double diemLyHoa) {
        super(maSo, hoTen, diemToan + diemLyHoa);
        this.diemToan  = diemToan;
        this.diemLyHoa = diemLyHoa;
    }

    public double getDiemToan()   { return diemToan; }
    public double getDiemLyHoa()  { return diemLyHoa; }

    @Override
    public void hienThiThongTin() {
        System.out.printf("Ma so: %-8s | Ho ten: %-20s | Toan: %.1f | LyHoa: %.1f | Tong: %.1f  [Cong nghe]%n",
                maSo, hoTen, diemToan, diemLyHoa, tongDiem);
    }
}
