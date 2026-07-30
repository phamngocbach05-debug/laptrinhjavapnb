// Nem ra khi khong tim thay thi sinh theo ma so
public class SinhVienKhongTonTaiException extends SinhVienException {
    private String maSo;
    public SinhVienKhongTonTaiException(String maSo) {
        super("Khong tim thay thi sinh co ma so: '" + maSo + "'");
        this.maSo = maSo;
    }
    public String getMaSo() { return maSo; }
}
