import java.util.ArrayList;


public interface IQuanLy {
    void them(ThiSinh ts);                   
    boolean xoa(String maSo);               
    ThiSinh timKiem(String maSo);           
    void hienThi();                          
    ArrayList<ThiSinh> getDanhSach();       
}
