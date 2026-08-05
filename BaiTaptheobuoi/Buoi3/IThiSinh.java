// Giao dien thao tac tren MOT thi sinh
public interface IThiSinh {
    void nhapThongTin();           // Them (nhap) thong tin
    void suaThongTin(String hoTenMoi, double tongDiemMoi); // Sua thong tin
    void xoaThongTin();            // Xoa (reset) thong tin
    void hienThiThongTin();        // Hien thi thong tin
}
