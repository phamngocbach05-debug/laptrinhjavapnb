import java.util.ArrayList;

// Giao dien quan ly NHIEU thi sinh (danh sach)
public interface IQuanLy {
    void them(ThiSinh ts);                   // Them vao danh sach
    boolean xoa(String maSo);               // Xoa khoi danh sach
    ThiSinh timKiem(String maSo);           // Tim kiem trong danh sach
    void hienThi();                          // Hien thi tat ca
    ArrayList<ThiSinh> getDanhSach();        // Lay danh sach
}
