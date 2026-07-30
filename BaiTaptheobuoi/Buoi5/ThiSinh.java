// Lop cha: ThiSinh - chua thong tin co ban cua mot thi sinh
public class ThiSinh {
    protected String maSo;
    protected String hoTen;
    protected double diem;

    public ThiSinh() {}

    public ThiSinh(String maSo, String hoTen, double diem) {
        this.maSo  = maSo;
        this.hoTen = hoTen;
        this.diem  = diem;
    }

    // Getters
    public String getMaSo()  { return maSo; }
    public String getHoTen() { return hoTen; }
    public double getDiem()  { return diem; }

    // Setters
    public void setMaSo(String maSo)   { this.maSo = maSo; }
    public void setHoTen(String hoTen) { this.hoTen = hoTen; }
    public void setDiem(double diem)   { this.diem = diem; }

    // Phuong thuc in thong tin - lop con co the ghi de
    public void hienThiThongTin() {
        System.out.printf("Ma so : %-8s | Ho ten: %-20s | Diem: %.1f%n",
                maSo, hoTen, diem);
    }

    @Override
    public String toString() {
        return String.format("[%s] %s - Diem: %.1f", maSo, hoTen, diem);
    }
}
