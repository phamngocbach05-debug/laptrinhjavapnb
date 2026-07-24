package GTS;

// Nem ra khi khong tim thay thi sinh trong he thong
public class ThiSinhKhongTonTaiException extends TuyenSinhException {
    private String soBD;

    public ThiSinhKhongTonTaiException(String soBD) {
        super("Khong tim thay thi sinh co So Bao Danh: '" + soBD + "'");
        this.soBD = soBD;
    }

    public String getSoBD() {
        return soBD;
    }
}
