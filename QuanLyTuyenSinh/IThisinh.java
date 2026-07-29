package GTS;

import java.util.ArrayList;

public interface IThisinh {
   
    ArrayList<Thisinh> getTS();

    
    boolean insertTS(Thisinh ts) throws SoBDDaTonTaiException, DiemKhongHopLeException;

    
    Thisinh timKiem(String soBD) throws ThiSinhKhongTonTaiException;
}
