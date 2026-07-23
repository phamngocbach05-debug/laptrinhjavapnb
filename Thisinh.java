package GTS;

public class Thisinh {
    private String SoBD;
    private String Hoten;
    private String GT;      // Gioi tinh: Nam / Nu
    private String NganhH;  // Nganh hoc
    private double TongD;   // Tong diem

    // Constructor mac dinh
    public Thisinh() {
    }

    // Constructor day du
    public Thisinh(String soBD, String hoten, String gT, String nganhH, double tongD) {
        this.SoBD = soBD;
        this.Hoten = hoten;
        this.GT = gT;
        this.NganhH = nganhH;
        this.TongD = tongD;
    }

    // Getters
    public String getSoBD() { return SoBD; }
    public String getHoten() { return Hoten; }
    public String getGT() { return GT; }
    public String getNganhH() { return NganhH; }
    public double getTongD() { return TongD; }

    // Setters
    public void setSoBD(String soBD) { this.SoBD = soBD; }
    public void setHoten(String hoten) { this.Hoten = hoten; }
    public void setGT(String gT) { this.GT = gT; }
    public void setNganhH(String nganhH) { this.NganhH = nganhH; }
    public void setTongD(double tongD) { this.TongD = tongD; }

    // Phuong thuc tinh hoc bong
    // Neu Tong diem >= 29 thi tra ve "HB", nguoc lai de trong
    public String Hocbong() {
        if (this.TongD >= 29) {
            return "HB";
        }
        return "";
    }

    @Override
    public String toString() {
        return "Thisinh{"
                + "SoBD='" + SoBD + '\''
                + ", Hoten='" + Hoten + '\''
                + ", GT='" + GT + '\''
                + ", NganhH='" + NganhH + '\''
                + ", TongD=" + TongD
                + ", Hocbong=" + Hocbong()
                + '}';
    }
}
