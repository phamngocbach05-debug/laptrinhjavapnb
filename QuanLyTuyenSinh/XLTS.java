package GTS;

import java.util.ArrayList;

public class XLTS implements IThisinh {
    // Danh sach luu tru thi sinh (khong can database)
    private ArrayList<Thisinh> danhSach;

    // Constructor: khoi tao voi du lieu mau san
    public XLTS() {
        danhSach = new ArrayList<>();

        // Them san 3 thi sinh mau
        danhSach.add(new Thisinh("TS001", "Nguyen Van An",   "Nam", "Tri tue nhan tao", 28.5));
        danhSach.add(new Thisinh("TS002", "Tran Thi Bich",   "Nu",  "Co khi",           29.0));
        danhSach.add(new Thisinh("TS003", "Le Van Cuong",    "Nam", "Cong trinh thuy",  27.0));
    }

    @Override
    public ArrayList<Thisinh> getTS() {
        return danhSach;
    }

    @Override
    public boolean insertTS(Thisinh ts) {
        if (ts == null) return false;

        // Kiem tra trung so bao danh
        for (Thisinh t : danhSach) {
            if (t.getSoBD().equalsIgnoreCase(ts.getSoBD())) {
                return false; // So bao danh da ton tai
            }
        }
        danhSach.add(ts);
        return true;
    }

    @Override
    public Thisinh timKiem(String soBD) {
        for (Thisinh ts : danhSach) {
            if (ts.getSoBD().equalsIgnoreCase(soBD)) {
                return ts;
            }
        }
        return null;
    }
}
