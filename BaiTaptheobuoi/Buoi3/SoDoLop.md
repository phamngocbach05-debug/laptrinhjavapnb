# Sơ đồ lớp - Buổi 3: Quản lý Tuyển sinh

## Mô tả hệ thống

Hệ thống quản lý tuyển sinh xoay quanh 3 đối tượng cốt lõi:

| Lớp | Vai trò |
|-----|---------|
| `Thisinh` | Thông tin thí sinh dự tuyển |
| `NganhHoc` | Thông tin ngành học & chỉ tiêu |
| `DonDangKy` | Đơn đăng ký xét tuyển |

---

## Sơ đồ lớp (UML)

```
┌─────────────────────────────┐
│          Thisinh            │
├─────────────────────────────┤
│ - maSoBD     : String       │
│ - hoTen      : String       │
│ - gioiTinh   : String       │
│ - nganhDangKy: String       │
│ - tongDiem   : double       │
├─────────────────────────────┤
│ + Thisinh()                 │
│ + Thisinh(...)              │
│ + getMaSoBD()  : String     │
│ + getHoTen()   : String     │
│ + getGioiTinh(): String     │
│ + getTongDiem(): double     │
│ + setMaSoBD(v) : void       │
│ + setHoTen(v)  : void       │
│ + hocBong()    : String     │
│ + toString()   : String     │
└─────────────────────────────┘
           ▲
           │ uses
┌─────────────────────────────┐
│         DonDangKy           │◄──────── NganhHoc
├─────────────────────────────┤          ├─ maNganh   : String
│ - maDon    : String         │          ├─ tenNganh  : String
│ - thisinh  : Thisinh        │          ├─ chiTieu   : int
│ - nganhHoc : NganhHoc       │          ├─ diemChuan : double
│ - ngayNop  : String         │          ├─ getters / setters
│ - trangThai: String         │          └─ conChiTieu(n): boolean
├─────────────────────────────┤
│ + DonDangKy()               │
│ + DonDangKy(...)            │
│ + getMaDon()  : String      │
│ + getThisinh(): Thisinh     │
│ + xetDuyet()  : void        │
│ + toString()  : String      │
└─────────────────────────────┘
```

---

## Quan hệ giữa các lớp

- **DonDangKy** *uses* **Thisinh** — mỗi đơn thuộc về một thí sinh
- **DonDangKy** *uses* **NganhHoc** — mỗi đơn đăng ký vào một ngành học
- **DonDangKy.xetDuyet()** — so sánh `Thisinh.tongDiem` với `NganhHoc.diemChuan`

---

> Sinh viên: **Phạm Ngọc Bách** | Lớp: 65KTPM | MSSV: 2351170576
