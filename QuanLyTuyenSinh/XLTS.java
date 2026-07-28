package GTS;

import java.util.ArrayList;

public class XLTS implements IThisinh {
    private ArrayList<Thisinh> danhSach;

    public XLTS() {
        danhSach = new ArrayList<>();
        danhSach.add(new Thisinh("TS001", "Nguyen Van An",  "Nam", "Tri tue nhan tao", 28.5));
        danhSach.add(new Thisinh("TS002", "Tran Thi Bich",  "Nu",  "Co khi",           29.0));
        danhSach.add(new Thisinh("TS003", "Le Van Cuong",   "Nam", "Cong trinh thuy",  27.0));
    }

    @Override
    public ArrayList<Thisinh> getTS() {
        return danhSach;
    }

    @Override
    public boolean insertTS(Thisinh ts) {
        if (ts == null) return false;
        // Kiem tra diem hop le
        if (ts.getTongD() < 0 || ts.getTongD() > 30) return false;
        // Kiem tra trung so bao danh
        for (Thisinh t : danhSach) {
            if (t.getSoBD().equalsIgnoreCase(ts.getSoBD())) return false;
        }
        danhSach.add(ts);
        return true;
    }

    @Override
    public Thisinh timKiem(String soBD) {
        for (Thisinh ts : danhSach) {
            if (ts.getSoBD().equalsIgnoreCase(soBD)) return ts;
        }
        return null;
    }

    public boolean xoaThiSinh(int index) {
        if (index < 0 || index >= danhSach.size()) return false;
        danhSach.remove(index);
        return true;
    }
}
