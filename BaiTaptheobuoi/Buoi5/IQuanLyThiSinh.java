import java.util.ArrayList;

// Giao dien dinh nghia cac phep toan quan ly thi sinh
public interface IQuanLyThiSinh {
    void them(ThiSinh ts);
    void hienThi();
    ThiSinh timKiem(String maSo);
    ArrayList<ThiSinh> getDanhSach();
}
