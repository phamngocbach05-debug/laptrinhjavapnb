package GTS;


public class SoBDDaTonTaiException extends TuyenSinhException {
    private String soBD;

    public SoBDDaTonTaiException(String soBD) {
        super("So bao danh '" + soBD + "' da ton tai trong he thong!");
        this.soBD = soBD;
    }

    public String getSoBD() {
        return soBD;
    }
}
