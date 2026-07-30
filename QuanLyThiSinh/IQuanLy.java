import java.util.ArrayList;

// Giao dien quan ly thi sinh
public interface IQuanLy {
    void them(ThiSinh ts);
    void hienThi();
    ThiSinh timKiem(String maSo);
    ArrayList<ThiSinh> getDanhSach();
}
