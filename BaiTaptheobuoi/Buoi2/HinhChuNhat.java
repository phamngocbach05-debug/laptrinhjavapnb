// Buoi 2 - Bai tap: Mo hinh hoa lop - Lop HinhChuNhat
// Sinh vien: Pham Ngoc Bach - 2351170576
public class HinhChuNhat {
    private double chieuDai;
    private double chieuRong;

    public HinhChuNhat(double chieuDai, double chieuRong) {
        this.chieuDai = chieuDai;
        this.chieuRong = chieuRong;
    }

    public double tinhDienTich() {
        return chieuDai * chieuRong;
    }

    public double tinhChuVi() {
        return 2 * (chieuDai + chieuRong);
    }

    public void hienThi() {
        System.out.println("Hinh chu nhat: " + chieuDai + " x " + chieuRong);
        System.out.println("Dien tich: " + tinhDienTich());
        System.out.println("Chu vi: " + tinhChuVi());
    }

    public static void main(String[] args) {
        HinhChuNhat h = new HinhChuNhat(5.0, 3.0);
        h.hienThi();
    }
}
