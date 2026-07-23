# CSE284 - Lập trình Java | Summer 2026

## Thông tin sinh viên

- **Họ và tên:** Phạm Ngọc Bách
- **Lớp:** 65KTPM
- **Mã sinh viên:** 2351170576
- **GitHub:** [phamngocbach05-debug](https://github.com/phamngocbach05-debug)

---

## Về Repository này

Repository này là nơi lưu trữ mã nguồn cho môn học **CSE284 - Lập trình Java**, bao gồm hai phần chính:

### 1. 📚 Bài tập các buổi (`BaiTaptheobuoi/`)
Chứa các bài tập thực hành lập trình Java được giao theo từng buổi học
(ví dụ: bảng cửu chương, vẽ mô hình hóa lớp, danh bạ điện thoại, ...).

### 2. 🎓 Bài tập lớn (BTL) - Quản lý Tuyển sinh (`QuanLyTuyenSinh/`)
Một dự án nhỏ với chủ đề **quản lý tuyển sinh đại học**.
Hệ thống được thiết kế xoay quanh các đối tượng cốt lõi:

- **`Thisinh`** (Thí sinh): Quản lý thông tin thí sinh dự tuyển (SoBD, Họ tên, Giới tính, Ngành học, Tổng điểm).
- **`IThisinh`** (Interface): Định nghĩa các phương thức chuẩn cho hệ thống.
- **`XLTS`** (Xử lý Tuyển sinh): Lớp xử lý dữ liệu, tìm kiếm và thêm thí sinh.
- **`GUI_insertTS`** (Giao diện): Giao diện Swing cho phép xem, thêm, xóa và tìm kiếm thí sinh. Tự động tính học bổng khi Tổng điểm ≥ 29.

---

## Cấu trúc thư mục

```
laptrinhjavapnb/
├── BaiTaptheobuoi/        # Bài tập thực hành theo từng buổi
│   ├── Buoi1/
│   ├── Buoi2/
│   └── ...
├── QuanLyTuyenSinh/       # Bài tập lớn - Quản lý Tuyển sinh
│   ├── IThisinh.java
│   ├── Thisinh.java
│   ├── XLTS.java
│   └── GUI_insertTS.java
└── README.md
```

---

## Hướng dẫn chạy BTL Quản lý Tuyển sinh

```bash
# Biên dịch
javac -d out QuanLyTuyenSinh/*.java

# Chạy
java -cp out GTS.GUI_insertTS
```
