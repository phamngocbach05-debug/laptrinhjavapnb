package GTS;

import java.util.ArrayList;

public interface IThisinh {
    // Lay danh sach tat ca thi sinh
    ArrayList<Thisinh> getTS();

    // Them mot thi sinh moi vao danh sach
    boolean insertTS(Thisinh ts);

    // Tim kiem thi sinh theo so bao danh
    Thisinh timKiem(String soBD);
}
