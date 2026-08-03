// Lop con 1: Thi sinh nganh Cong nghe (Toan + Ly + Hoa)
public class ThiSinhCongNghe extends ThiSinh {
    private double diemToan;
    private double diemLy;
    private double diemHoa;

    public ThiSinhCongNghe() {}

    public ThiSinhCongNghe(String maSo, String hoTen,
                           double diemToan, double diemLy, double diemHoa) {
        super(maSo, hoTen, diemToan + diemLy + diemHoa);
        this.diemToan = diemToan;
        this.diemLy   = diemLy;
        this.diemHoa  = diemHoa;
    }

    public double getDiemToan() { return diemToan; }
    public double getDiemLy()   { return diemLy; }
    public double getDiemHoa()  { return diemHoa; }

    @Override
    public void hienThiThongTin() {
        System.out.printf("Ma so: %-8s | Ho ten: %-20s | Toan: %.1f | Ly: %.1f | Hoa: %.1f | Tong: %.1f  [Cong nghe]%n",
                maSo, hoTen, diemToan, diemLy, diemHoa, tongDiem);
    }
}
