// Nem ra khi ma so thi sinh da ton tai
public class MaSoTonTaiException extends SinhVienException {
    private String maSo;
    public MaSoTonTaiException(String maSo) {
        super("Ma so '" + maSo + "' da ton tai trong he thong!");
        this.maSo = maSo;
    }
    public String getMaSo() { return maSo; }
}
