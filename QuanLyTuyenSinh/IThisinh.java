package GTS;

import java.util.ArrayList;

public interface IThisinh {
    // Lay danh sach tat ca thi sinh
    ArrayList<Thisinh> getTS();

    // Them mot thi sinh moi - nem exception neu trung SoBD hoac diem sai
    boolean insertTS(Thisinh ts) throws SoBDDaTonTaiException, DiemKhongHopLeException;

    // Tim kiem thi sinh theo so bao danh - nem exception neu khong tim thay
    Thisinh timKiem(String soBD) throws ThiSinhKhongTonTaiException;
}
