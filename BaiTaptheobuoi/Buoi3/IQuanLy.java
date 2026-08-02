import java.util.ArrayList;

// Giao dien quan ly thi sinh (CRUD)
public interface IQuanLy {
    void them(ThiSinh ts);          // Them moi
    boolean sua(String maSo, ThiSinh tsMoi); // Sua theo ma so
    boolean xoa(String maSo);       // Xoa theo ma so
    ThiSinh timKiem(String maSo);   // Tim kiem
    void hienThi();                  // Hien thi danh sach
}
