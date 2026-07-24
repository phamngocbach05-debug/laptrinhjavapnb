# Sơ đồ lớp - Buổi 3: Quản lý Tuyển sinh

> Sinh viên: **Phạm Ngọc Bách** | Lớp: 65KTPM | MSSV: 2351170576

---

## Sơ đồ lớp (UML)

```
                    ┌─────────────────────────────────┐
                    │         «interface»              │
                    │           IThisinh              │
                    ├─────────────────────────────────┤
                    │ + getTS() : ArrayList<Thisinh>  │
                    │ + insertTS(ts : Thisinh)         │
                    │     throws SoBDDaTonTaiEx        │
                    │            DiemKhongHopLeEx      │
                    │ + timKiem(soBD : String)         │
                    │     throws ThiSinhKhongTonTaiEx  │
                    └─────────────────────────────────┘
                                    ▲
                                    │ «implements»
          ┌─────────────────────────────────────────┐
          │                  XLTS                   │
          ├─────────────────────────────────────────┤
          │ - danhSach : ArrayList<Thisinh>         │
          ├─────────────────────────────────────────┤
          │ + XLTS()                                │
          │ + getTS()        : ArrayList<Thisinh>   │
          │ + insertTS(ts)   : boolean              │
          │ + timKiem(soBD)  : Thisinh             │
          │ + xoaThiSinh(i)  : boolean             │
          └─────────────────────────────────────────┘
                 │ uses                    ▲ uses
                 ▼                        │
  ┌─────────────────────────┐    ┌─────────────────────────────────────────────┐
  │        Thisinh          │    │               GUI_insertTS                  │
  ├─────────────────────────┤    │            extends JFrame                   │
  │ - SoBD    : String      │    ├─────────────────────────────────────────────┤
  │ - Hoten   : String      │    │ - txtSoBD, txtHoten, txtTongD : JTextField  │
  │ - GT      : String      │    │ - cboNganhH   : JComboBox<String>           │
  │ - NganhH  : String      │    │ - rdoNam, rdoNu : JRadioButton             │
  │ - TongD   : double      │    │ - groupGT     : ButtonGroup                 │
  ├─────────────────────────┤    │ - btnThem, btnXoa, btnTimKiem : JButton     │
  │ + Thisinh()             │    │ - table       : JTable                      │
  │ + Thisinh(soBD,hoten,   │    │ - tableModel  : DefaultTableModel           │
  │           gt,nganh,diem)│    │ - xlts        : XLTS                        │
  │ + getSoBD()  : String   │    ├─────────────────────────────────────────────┤
  │ + getHoten() : String   │    │ + GUI_insertTS()                            │
  │ + getGT()    : String   │    │ - initUI()       : void                     │
  │ + getNganhH(): String   │    │ - loadTable()    : void                     │
  │ + getTongD() : double   │    │ - themThiSinh()  : void                     │
  │ + setters...            │    │ - xoaDong()      : void                     │
  │ + Hocbong()  : String   │    │ - timKiem()      : void                     │
  │ + toString() : String   │    │ - fillForm()     : void                     │
  └─────────────────────────┘    │ - clearForm()    : void                     │
                                 │ - showError(msg) : void                     │
                                 │ - showSuccess(msg): void                    │
                                 │ + main(args)     : void                     │
                                 └─────────────────────────────────────────────┘


  ┌──────────────────────────────┐        ┌──────────────────────────────┐
  │          NganhHoc            │        │          DonDangKy           │
  ├──────────────────────────────┤        ├──────────────────────────────┤
  │ - maNganh   : String         │        │ - maDon     : String         │
  │ - tenNganh  : String         │        │ - thisinh   : Thisinh        │
  │ - chiTieu   : int            │◄─uses──│ - nganhHoc  : NganhHoc       │
  │ - diemChuan : double         │        │ - ngayNop   : String         │
  ├──────────────────────────────┤        │ - trangThai : String         │
  │ + NganhHoc()                 │        ├──────────────────────────────┤
  │ + NganhHoc(maNganh,          │        │ + DonDangKy()                │
  │     tenNganh,chiTieu,diem)   │        │ + DonDangKy(maDon,           │
  │ + getMaNganh()  : String     │        │     thisinh,nganh,ngayNop)   │
  │ + getTenNganh() : String     │        │ + getters / setters          │
  │ + getChiTieu()  : int        │        │ + xetDuyet()  : void         │
  │ + getDiemChuan(): double     │        │ + toString()  : String       │
  │ + setters...                 │        └──────────────────────────────┘
  │ + conChiTieu(n) : boolean    │                    │ uses
  └──────────────────────────────┘                    ▼
                                             (Thisinh - xem trên)
```

---

## Quan hệ giữa các lớp

| Quan hệ | Từ | Đến | Kiểu |
|---------|----|-----|------|
| `«implements»` | `XLTS` | `IThisinh` | Realization |
| `uses` | `XLTS` | `Thisinh` | Dependency |
| `uses` | `GUI_insertTS` | `XLTS` | Association |
| `uses` | `DonDangKy` | `Thisinh` | Association |
| `uses` | `DonDangKy` | `NganhHoc` | Association |
| `throws` | `XLTS.insertTS()` | `SoBDDaTonTaiException` | Dependency |
| `throws` | `XLTS.insertTS()` | `DiemKhongHopLeException` | Dependency |
| `throws` | `XLTS.timKiem()` | `ThiSinhKhongTonTaiException` | Dependency |
