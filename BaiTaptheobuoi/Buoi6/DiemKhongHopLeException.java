// Nem ra khi diem nhap vao ngoai khoang 0-10
public class DiemKhongHopLeException extends SinhVienException {
    private double diem;
    public DiemKhongHopLeException(double diem) {
        super("Diem " + diem + " khong hop le! Diem moi mon phai tu 0.0 den 10.0");
        this.diem = diem;
    }
    public double getDiem() { return diem; }
}
