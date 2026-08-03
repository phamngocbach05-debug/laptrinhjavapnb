import java.util.ArrayList;


public interface IQuanLy {
    void them(ThiSinh ts);          
    boolean sua(String maSo, ThiSinh tsMoi); 
    boolean xoa(String maSo);       
    ThiSinh timKiem(String maSo);   
    void hienThi();                  
}
