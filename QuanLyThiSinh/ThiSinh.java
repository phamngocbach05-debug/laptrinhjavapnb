// Lop cha: ThiSinh
public class ThiSinh {
    protected String maSo;
    protected String hoTen;
    protected double tongDiem;

    public ThiSinh() {}

    public ThiSinh(String maSo, String hoTen, double tongDiem) {
        this.maSo     = maSo;
        this.hoTen    = hoTen;
        this.tongDiem = tongDiem;
    }

    public String getMaSo()       { return maSo; }
    public String getHoTen()      { return hoTen; }
    public double getTongDiem()   { return tongDiem; }
    public void setMaSo(String v)    { this.maSo = v; }
    public void setHoTen(String v)   { this.hoTen = v; }
    public void setTongDiem(double v){ this.tongDiem = v; }

    public void hienThiThongTin() {
        System.out.printf("Ma so: %-8s | Ho ten: %-20s | Tong diem: %.2f%n",
                maSo, hoTen, tongDiem);
    }

    @Override
    public String toString() {
        return String.format("[%s] %s - %.2f diem", maSo, hoTen, tongDiem);
    }
}
