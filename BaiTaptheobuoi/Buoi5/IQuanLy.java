import java.util.ArrayList;

// Giao dien 
public interface IQuanLy {
    void them(ThiSinh ts);
    void hienThi();
    ThiSinh timKiem(String maSo);
    ArrayList<ThiSinh> getDanhSach();
}
