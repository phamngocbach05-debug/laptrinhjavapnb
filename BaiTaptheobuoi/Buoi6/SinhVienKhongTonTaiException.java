// Nem ra khi khong tim thay sinh vien theo ma so
public class SinhVienKhongTonTaiException extends SinhVienException {
    private String maSo;

    public SinhVienKhongTonTaiException(String maSo) {
        super("Khong tim thay sinh vien co ma so: '" + maSo + "'");
        this.maSo = maSo;
    }

    public String getMaSo() { return maSo; }
}
