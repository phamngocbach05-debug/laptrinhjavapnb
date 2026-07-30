// Lop con 1: Thi sinh nganh Cong nghe - co them mon thi chuyen
public class ThiSinhCongNghe extends ThiSinh {
    private double diemToan;
    private double diemLyHoa;   // Trung binh Ly + Hoa

    public ThiSinhCongNghe() {}

    public ThiSinhCongNghe(String maSo, String hoTen,
                           double diemToan, double diemLyHoa) {
        super(maSo, hoTen, diemToan + diemLyHoa); // tong diem = Toan + LyHoa
        this.diemToan   = diemToan;
        this.diemLyHoa  = diemLyHoa;
    }

    public double getDiemToan()   { return diemToan; }
    public double getDiemLyHoa()  { return diemLyHoa; }

    @Override
    public void hienThiThongTin() {
        System.out.printf("Ma so: %-8s | Ho ten: %-20s | Toan: %.1f | Ly-Hoa: %.1f | Tong: %.1f  [Cong nghe]%n",
                maSo, hoTen, diemToan, diemLyHoa, diem);
    }
}
