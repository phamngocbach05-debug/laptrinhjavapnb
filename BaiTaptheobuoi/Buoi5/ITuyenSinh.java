import java.util.ArrayList;

// Interface ITuyenSinh - Dinh nghia cac phep toan co ban tren danh sach thi sinh
public interface ITuyenSinh {
    // Them thi sinh vao he thong
    boolean themThiSinh(ThiSinh ts);

    // Lay toan bo danh sach thi sinh
    ArrayList<ThiSinh> getDanhSach();

    // Tim kiem theo ma so bao danh
    ThiSinh timKiem(String maSoBD);

    // Hien thi tat ca thi sinh
    void hienThi();

    // Loc danh sach: lay cac thi sinh du dieu kien (diem >= nguong)
    ArrayList<ThiSinh> locTheoNguong(double nguongDiem);
}
