# Sơ đồ lớp - Buổi 3: Quản lý Tuyển sinh

## Mô tả hệ thống

Hệ thống quản lý tuyển sinh xoay quanh các đối tượng cốt lõi:

| Lớp | Vai trò |
|-----|---------|
| `Thisinh` | Thông tin thí sinh dự tuyển |
| `NganhHoc` | Thông tin ngành học & chỉ tiêu |
| `DonDangKy` | Đơn đăng ký xét tuyển |
| `IThisinh` | Interface định nghĩa các phép toán |
| `XLTS` | Xử lý dữ liệu, implements IThisinh |
| `GUI_insertTS` | Giao diện Swing quản lý thí sinh |
| `TuyenSinhException` | Exception cơ sở hệ thống |
| `SoBDDaTonTaiException` | Lỗi trùng số báo danh |
| `DiemKhongHopLeException` | Lỗi điểm ngoài khoảng 0-30 |
| `ThiSinhKhongTonTaiException` | Lỗi không tìm thấy thí sinh |

---

## Sơ đồ lớp (UML)

```
╔══════════════════════════════════════════════════════════════════════════╗
║                  HỆ THỐNG QUẢN LÝ TUYỂN SINH                           ║
╚══════════════════════════════════════════════════════════════════════════╝

 ┌──────────────────────┐          ┌──────────────────────────────┐
 │    «interface»       │          │          Thisinh             │
 │      IThisinh        │          ├──────────────────────────────┤
 ├──────────────────────┤          │ - SoBD      : String         │
 │ + getTS()            │          │ - Hoten     : String         │
 │ + insertTS(ts)       │          │ - GT        : String         │
 │   throws SoBDEx,     │          │ - NganhH    : String         │
 │         DiemEx       │          │ - TongD     : double         │
 │ + timKiem(soBD)      │          ├──────────────────────────────┤
 │   throws TSKhEx      │          │ + Thisinh()                  │
 └──────────────────────┘          │ + Thisinh(soBD,hoten,...)    │
            ▲                      │ + getSoBD()  : String        │
            │ «implements»         │ + getHoten() : String        │
            │                      │ + getGT()    : String        │
 ┌──────────────────────┐          │ + getNganhH(): String        │
 │        XLTS          │─ uses ──►│ + getTongD() : double        │
 ├──────────────────────┤          │ + setters...                 │
 │ - danhSach:          │          │ + Hocbong()  : String        │
 │   ArrayList<Thisinh> │          │ + toString() : String        │
 ├──────────────────────┤          └──────────────────────────────┘
 │ + XLTS()             │
 │ + getTS()            │
 │ + insertTS(ts)       │──── throws ────► DiemKhongHopLeException
 │ + timKiem(soBD)      │──── throws ────► ThiSinhKhongTonTaiException
 │ + xoaThiSinh(index)  │──── throws ────► SoBDDaTonTaiException
 └──────────────────────┘
            ▲
            │ uses
 ┌──────────────────────────────────────────────────────────┐
 │                     GUI_insertTS                         │
 │                  extends JFrame                          │
 ├──────────────────────────────────────────────────────────┤
 │ - txtSoBD, txtHoten, txtTongD  : JTextField              │
 │ - cboNganhH                    : JComboBox<String>       │
 │ - rdoNam, rdoNu                : JRadioButton            │
 │ - groupGT                      : ButtonGroup             │
 │ - btnThem, btnXoa, btnTimKiem  : JButton                 │
 │ - table                        : JTable                  │
 │ - tableModel                   : DefaultTableModel       │
 │ - xlts                         : XLTS                    │
 ├──────────────────────────────────────────────────────────┤
 │ + GUI_insertTS()                                         │
 │ - initUI()         : void                                │
 │ - loadTable()      : void                                │
 │ - themThiSinh()    : void   ← catch DiemEx, SoBDEx      │
 │ - xoaDong()        : void                                │
 │ - timKiem()        : void   ← catch ThiSinhKhongTonEx   │
 │ - fillForm()       : void                                │
 │ - clearForm()      : void                                │
 │ - showError(msg)   : void                                │
 │ - showSuccess(msg) : void                                │
 │ + main(args)       : void                                │
 └──────────────────────────────────────────────────────────┘


═══════════════════ PHÂN CẤP EXCEPTION ═══════════════════

               ┌──────────────────────┐
               │   Exception (Java)   │
               └──────────────────────┘
                          ▲
                          │ extends
               ┌──────────────────────┐
               │  TuyenSinhException  │
               │ (Base Exception)     │
               └──────────────────────┘
                /          |          \
               ▼           ▼           ▼
  ┌─────────────────┐ ┌────────────────────┐ ┌─────────────────────────┐
  │  SoBDDaTonTai   │ │  DiemKhongHopLe    │ │  ThiSinhKhongTonTai     │
  │  Exception      │ │  Exception         │ │  Exception              │
  ├─────────────────┤ ├────────────────────┤ ├─────────────────────────┤
  │ - soBD: String  │ │ - diem: double     │ │ - soBD: String          │
  ├─────────────────┤ ├────────────────────┤ ├─────────────────────────┤
  │ + getSoBD()     │ │ + getDiem()        │ │ + getSoBD()             │
  └─────────────────┘ └────────────────────┘ └─────────────────────────┘
  Ném khi: SoBD      Ném khi: Diem < 0      Ném khi: Khong tim
  bi trung            hoac Diem > 30         thay thi sinh


═══════════════════ NGÀNH HỌC & ĐƠN ĐĂNG KÝ ═══════════════════

 ┌──────────────────────────┐         ┌──────────────────────────┐
 │        NganhHoc          │         │        DonDangKy         │
 ├──────────────────────────┤         ├──────────────────────────┤
 │ - maNganh   : String     │         │ - maDon     : String     │
 │ - tenNganh  : String     │         │ - thisinh   : Thisinh    │
 │ - chiTieu   : int        │◄─ uses─ │ - nganhHoc  : NganhHoc   │
 │ - diemChuan : double     │         │ - ngayNop   : String     │
 ├──────────────────────────┤         │ - trangThai : String     │
 │ + getters / setters      │         ├──────────────────────────┤
 │ + conChiTieu(): boolean  │         │ + DonDangKy()            │
 └──────────────────────────┘         │ + DonDangKy(...)         │
                                      │ + getters / setters      │
                                      │ + xetDuyet() : void      │
                                      │ + toString() : String    │
                                      └──────────────────────────┘
```

---

## Quan hệ giữa các lớp

| Quan hệ | Từ | Đến | Loại |
|---------|----|-----|------|
| implements | `XLTS` | `IThisinh` | Realization |
| uses | `XLTS` | `Thisinh` | Dependency |
| uses | `GUI_insertTS` | `XLTS` | Association |
| uses | `DonDangKy` | `Thisinh` | Association |
| uses | `DonDangKy` | `NganhHoc` | Association |
| extends | `SoBDDaTonTaiException` | `TuyenSinhException` | Inheritance |
| extends | `DiemKhongHopLeException` | `TuyenSinhException` | Inheritance |
| extends | `ThiSinhKhongTonTaiException` | `TuyenSinhException` | Inheritance |
| throws | `XLTS.insertTS()` | `SoBDDaTonTaiException` | Dependency |
| throws | `XLTS.insertTS()` | `DiemKhongHopLeException` | Dependency |
| throws | `XLTS.timKiem()` | `ThiSinhKhongTonTaiException` | Dependency |

---

> Sinh viên: **Phạm Ngọc Bách** | Lớp: 65KTPM | MSSV: 2351170576
