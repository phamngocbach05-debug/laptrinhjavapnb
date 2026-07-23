import java.util.Scanner;

// Lop Thisinh - Quan ly thong tin thi sinh du tuyen
public class Thisinh {
    private String maSoBD;
    private String hoTen;
    private String gioiTinh;
    private String nganhDangKy;
    private double tongDiem;

    // Constructor mac dinh
    public Thisinh() {
    }

    // Constructor day du
    public Thisinh(String maSoBD, String hoTen, String gioiTinh,
                   String nganhDangKy, double tongDiem) {
        this.maSoBD      = maSoBD;
        this.hoTen       = hoTen;
        this.gioiTinh    = gioiTinh;
        this.nganhDangKy = nganhDangKy;
        this.tongDiem    = tongDiem;
    }

    // Getters
    public String getMaSoBD()      { return maSoBD; }
    public String getHoTen()       { return hoTen; }
    public String getGioiTinh()    { return gioiTinh; }
    public String getNganhDangKy() { return nganhDangKy; }
    public double getTongDiem()    { return tongDiem; }

    // Setters
    public void setMaSoBD(String maSoBD)           { this.maSoBD = maSoBD; }
    public void setHoTen(String hoTen)             { this.hoTen = hoTen; }
    public void setGioiTinh(String gioiTinh)       { this.gioiTinh = gioiTinh; }
    public void setNganhDangKy(String nganhDangKy) { this.nganhDangKy = nganhDangKy; }
    public void setTongDiem(double tongDiem)        { this.tongDiem = tongDiem; }

    // Phuong thuc tinh hoc bong
    // Tong diem >= 29 -> duoc hoc bong "HB", nguoc lai de trong
    public String hocBong() {
        return tongDiem >= 29 ? "Co hoc bong" : "Khong co hoc bong";
    }

    @Override
    public String toString() {
        return String.format("%-10s %-20s %-6s %-20s %.1f  %s",
                maSoBD, hoTen, gioiTinh, nganhDangKy, tongDiem, hocBong());
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhap Ma So Bao Danh: ");
        String maSoBD = sc.nextLine();
        System.out.print("Nhap Ho Ten       : ");
        String hoTen = sc.nextLine();
        System.out.print("Nhap Gioi Tinh    : ");
        String gioiTinh = sc.nextLine();
        System.out.print("Nhap Nganh Dang Ky: ");
        String nganh = sc.nextLine();
        System.out.print("Nhap Tong Diem    : ");
        double tongDiem = sc.nextDouble();

        Thisinh ts = new Thisinh(maSoBD, hoTen, gioiTinh, nganh, tongDiem);

        System.out.println("\n===== THONG TIN THI SINH =====");
        System.out.println("Ma SBD     : " + ts.getMaSoBD());
        System.out.println("Ho Ten     : " + ts.getHoTen());
        System.out.println("Gioi Tinh  : " + ts.getGioiTinh());
        System.out.println("Nganh DK   : " + ts.getNganhDangKy());
        System.out.println("Tong Diem  : " + ts.getTongDiem());
        System.out.println("Hoc Bong   : " + ts.hocBong());

        sc.close();
    }
}
