

// Nem ra khi tong diem nhap vao khong hop le (ngoai khoang 0 - 30)
public class DiemKhongHopLeException extends TuyenSinhException {
    private double diem;

    public DiemKhongHopLeException(double diem) {
        super("Tong diem " + diem + " khong hop le! Diem phai trong khoang 0.0 - 30.0");
        this.diem = diem;
    }

    public double getDiem() {
        return diem;
    }
}
