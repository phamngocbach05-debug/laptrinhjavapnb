// Nem ra khi diem nhap vao ngoai khoang cho phep (0 - 10)
public class DiemKhongHopLeException extends SinhVienException {
    private double diem;

    public DiemKhongHopLeException(double diem) {
        super("Diem " + diem + " khong hop le! Diem phai trong khoang 0.0 - 10.0");
        this.diem = diem;
    }

    public double getDiem() { return diem; }
}
