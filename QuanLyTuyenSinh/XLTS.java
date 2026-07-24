package GTS;

import java.util.ArrayList;

public class XLTS implements IThisinh {
    // Danh sach luu tru thi sinh
    private ArrayList<Thisinh> danhSach;

    // Constructor: khoi tao voi du lieu mau san
    public XLTS() {
        danhSach = new ArrayList<>();

        // Them san 3 thi sinh mau (bo qua exception vi du lieu mau luon hop le)
        try {
            insertTS(new Thisinh("TS001", "Nguyen Van An",  "Nam", "Tri tue nhan tao", 28.5));
            insertTS(new Thisinh("TS002", "Tran Thi Bich",  "Nu",  "Co khi",           29.0));
            insertTS(new Thisinh("TS003", "Le Van Cuong",   "Nam", "Cong trinh thuy",  27.0));
        } catch (TuyenSinhException e) {
            System.err.println("[XLTS] Loi khoi tao du lieu mau: " + e.getMessage());
        }
    }

    @Override
    public ArrayList<Thisinh> getTS() {
        return danhSach;
    }

    // Them thi sinh - nem exception neu trung SoBD hoac diem khong hop le
    @Override
    public boolean insertTS(Thisinh ts)
            throws SoBDDaTonTaiException, DiemKhongHopLeException {

        if (ts == null) return false;

        // Kiem tra diem hop le
        if (ts.getTongD() < 0 || ts.getTongD() > 30) {
            throw new DiemKhongHopLeException(ts.getTongD());
        }

        // Kiem tra trung so bao danh
        for (Thisinh t : danhSach) {
            if (t.getSoBD().equalsIgnoreCase(ts.getSoBD())) {
                throw new SoBDDaTonTaiException(ts.getSoBD());
            }
        }

        danhSach.add(ts);
        return true;
    }

    // Tim kiem - nem exception neu khong tim thay
    @Override
    public Thisinh timKiem(String soBD) throws ThiSinhKhongTonTaiException {
        for (Thisinh ts : danhSach) {
            if (ts.getSoBD().equalsIgnoreCase(soBD)) {
                return ts;
            }
        }
        throw new ThiSinhKhongTonTaiException(soBD);
    }

    // Xoa thi sinh theo chi so hang - tra ve false neu khong hop le
    public boolean xoaThiSinh(int index) {
        if (index < 0 || index >= danhSach.size()) return false;
        danhSach.remove(index);
        return true;
    }
}
