// Buoi 3 - Bai tap: Danh ba Dien thoai
// Sinh vien: Pham Ngoc Bach - 2351170576
import java.util.ArrayList;

public class DanhBaDienThoai {
    private String hoTen;
    private String soDienThoai;
    private String email;

    public DanhBaDienThoai(String hoTen, String soDienThoai, String email) {
        this.hoTen = hoTen;
        this.soDienThoai = soDienThoai;
        this.email = email;
    }

    public String getHoTen() { return hoTen; }
    public String getSoDienThoai() { return soDienThoai; }
    public String getEmail() { return email; }

    @Override
    public String toString() {
        return hoTen + " | " + soDienThoai + " | " + email;
    }

    public static void main(String[] args) {
        ArrayList<DanhBaDienThoai> danhBa = new ArrayList<>();
        danhBa.add(new DanhBaDienThoai("Pham Ngoc Bach", "0901234567", "bach@gmail.com"));
        danhBa.add(new DanhBaDienThoai("Nguyen Van A",   "0912345678", "vana@gmail.com"));
        danhBa.add(new DanhBaDienThoai("Tran Thi B",     "0923456789", "thib@gmail.com"));

        System.out.println("===== DANH BA DIEN THOAI =====");
        for (DanhBaDienThoai d : danhBa) {
            System.out.println(d);
        }
    }
}
