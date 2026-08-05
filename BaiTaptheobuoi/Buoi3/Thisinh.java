
public class ThiSinh implements IThiSinh {
    protected String maSo;
    protected String hoTen;
    protected double tongDiem;

    public ThiSinh() {}

    public ThiSinh(String maSo, String hoTen, double tongDiem) {
        this.maSo     = maSo;
        this.hoTen    = hoTen;
        this.tongDiem = tongDiem;
    }

    public String getMaSo()      { return maSo; }
    public String getHoTen()     { return hoTen; }
    public double getTongDiem()  { return tongDiem; }

    public void setMaSo(String maSo)         { this.maSo = maSo; }
    public void setHoTen(String hoTen)       { this.hoTen = hoTen; }
    public void setTongDiem(double tongDiem) { this.tongDiem = tongDiem; }

   
    @Override
    public void nhapThongTin() {
       
    }

    @Override
    public void suaThongTin(String hoTenMoi, double tongDiemMoi) {
        this.hoTen    = hoTenMoi;
        this.tongDiem = tongDiemMoi;
        System.out.println(">> Da sua thong tin: " + hoTenMoi + " | Tong diem: " + tongDiemMoi);
    }

    @Override
    public void xoaThongTin() {
        this.maSo     = "";
        this.hoTen    = "";
        this.tongDiem = 0;
        System.out.println(">> Da xoa thong tin thi sinh.");
    }

    @Override
    public void hienThiThongTin() {
        System.out.printf("Ma so: %-8s | Ho ten: %-20s | Tong diem: %.2f%n",
                maSo, hoTen, tongDiem);
    }

    @Override
    public String toString() {
        return String.format("[%s] %s - %.2f diem", maSo, hoTen, tongDiem);
    }
}
