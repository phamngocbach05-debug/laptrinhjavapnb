import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;
import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 * Buoi 8 - CHUONG TRINH QUAN LY THI SINH (GUI Swing)
 * - Doi nut "Tinh TT" thanh "Tao bang"
 * - Bang "Danh sach thi sinh da nhap" hien thi Cuc ky ro rang tat ca cac cot (STT, Ma so, Ho ten,...)
 * Sinh vien: Pham Ngoc Bach - MSSV: 2351170576
 */
public class Buoi8 extends JFrame {

    // ===== MODEL DU LIEU =====
    private ArrayList<String[]> danhSachTS = new ArrayList<>();

    // ===== KHU VUC NHAP THONG TIN THI SINH =====
    private JTextField txtMaSo = new JTextField(20);
    private JTextField txtHoTen = new JTextField(20);
    private JComboBox<String> cbLoaiTS = new JComboBox<>(new String[]{"Thí sinh Công nghệ", "Thí sinh Kinh tế"});
    
    private JLabel lblMon1 = new JLabel("Điểm Toán:");
    private JLabel lblMon2 = new JLabel("Điểm Lý:");
    private JLabel lblMon3 = new JLabel("Điểm Hóa:");
    
    private JTextField txtDiem1 = new JTextField(20);
    private JTextField txtDiem2 = new JTextField(20);
    private JTextField txtDiem3 = new JTextField(20);

    // ===== CÁC NÚT THAO TÁC =====
    private JButton btnTaoBang = new JButton("Tạo bảng");
    private JButton btnTiep = new JButton("Tiếp");
    private JButton btnThongKe = new JButton("Thống Kê");
    private JButton btnKetThuc = new JButton("Kết Thúc");

    // ===== KHU VUC THONG KE =====
    private JTextField txtTongSoTS = new JTextField(20);
    private JTextField txtTongTSCN = new JTextField(20);
    private JTextField txtTongTSKT = new JTextField(20);
    private JTextField txtDiemTrungBinh = new JTextField(20);

    // ===== BẢNG HIỂN THỊ DANH SÁCH =====
    private DefaultTableModel tableModel;
    private JTable table;

    private DecimalFormat df = new DecimalFormat("#,##0.00");

    public Buoi8() {
        // 1. Tiêu đề Form đúng yêu cầu
        setTitle("Quản Lý Thí Sinh (Phạm Ngọc Bách – 2351170576)");
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        // Sự kiện đóng cửa sổ với xác nhận
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                suKienKetThuc();
            }
        });

        JPanel pMain = new JPanel(new BorderLayout(10, 10));
        pMain.setBorder(new EmptyBorder(10, 15, 15, 15));
        pMain.setBackground(new Color(242, 244, 248));

        // 2. HEADER BANNER
        JLabel lblHeader = new JLabel("CHƯƠNG TRÌNH QUẢN LÝ THÍ SINH", SwingConstants.CENTER);
        lblHeader.setFont(new Font("Segoe UI", Font.BOLD, 18));
        lblHeader.setOpaque(true);
        lblHeader.setBackground(new Color(165, 214, 167)); // Màu xanh lá giống mẫu
        lblHeader.setForeground(new Color(27, 94, 32));
        lblHeader.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(76, 175, 80), 2),
                new EmptyBorder(8, 0, 8, 0)
        ));

        // 3. GROUPBOX THÔNG TIN THÍ SINH
        JPanel pThongTin = createGroupPanel("Thông tin Thí sinh:");
        pThongTin.setLayout(new GridBagLayout());
        GridBagConstraints g = new GridBagConstraints();
        g.insets = new Insets(5, 8, 5, 8);
        g.anchor = GridBagConstraints.WEST;

        addLabel(pThongTin, g, 0, "Mã Số:");
        g.gridx = 1; txtMaSo.setFont(new Font("Segoe UI", Font.PLAIN, 13)); pThongTin.add(txtMaSo, g);

        addLabel(pThongTin, g, 1, "Họ và Tên:");
        g.gridx = 1; txtHoTen.setFont(new Font("Segoe UI", Font.PLAIN, 13)); pThongTin.add(txtHoTen, g);

        addLabel(pThongTin, g, 2, "Loại Thí Sinh:");
        g.gridx = 1; cbLoaiTS.setFont(new Font("Segoe UI", Font.PLAIN, 13)); pThongTin.add(cbLoaiTS, g);

        // Môn điểm
        g.gridx = 0; g.gridy = 3; pThongTin.add(lblMon1, g);
        g.gridx = 1; configNumberInput(txtDiem1); pThongTin.add(txtDiem1, g);

        g.gridx = 0; g.gridy = 4; pThongTin.add(lblMon2, g);
        g.gridx = 1; configNumberInput(txtDiem2); pThongTin.add(txtDiem2, g);

        g.gridx = 0; g.gridy = 5; pThongTin.add(lblMon3, g);
        g.gridx = 1; configNumberInput(txtDiem3); pThongTin.add(txtDiem3, g);

        // Đổi tên môn học linh hoạt khi chuyển đổi loại thí sinh
        cbLoaiTS.addActionListener(e -> {
            if (cbLoaiTS.getSelectedIndex() == 0) { // Công nghệ
                lblMon1.setText("Điểm Toán:");
                lblMon2.setText("Điểm Lý:");
                lblMon3.setText("Điểm Hóa:");
            } else { // Kinh tế
                lblMon1.setText("Điểm Toán:");
                lblMon2.setText("Điểm Văn:");
                lblMon3.setText("Điểm Anh:");
            }
        });

        // 4. HÀNG NÚT THAO TÁC (Tạo bảng, Tiếp, Thống Kê, Kết Thúc)
        JPanel pButtons = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 8));
        pButtons.setOpaque(false);
        styleButton(btnTaoBang);
        styleButton(btnTiep);
        styleButton(btnThongKe);
        styleButton(btnKetThuc);

        pButtons.add(btnTaoBang);
        pButtons.add(btnTiep);
        pButtons.add(btnThongKe);
        pButtons.add(btnKetThuc);

        // 5. GROUPBOX THỐNG KÊ
        JPanel pThongKe = createGroupPanel("Thống kê:");
        pThongKe.setLayout(new GridBagLayout());
        GridBagConstraints g2 = new GridBagConstraints();
        g2.insets = new Insets(5, 8, 5, 8);
        g2.anchor = GridBagConstraints.WEST;

        addLabel(pThongKe, g2, 0, "Tổng số KH (Thí sinh):");
        g2.gridx = 1; configReadOnlyNumber(txtTongSoTS); pThongKe.add(txtTongSoTS, g2);

        addLabel(pThongKe, g2, 1, "Số TS Công nghệ:");
        g2.gridx = 1; configReadOnlyNumber(txtTongTSCN); pThongKe.add(txtTongTSCN, g2);

        addLabel(pThongKe, g2, 2, "Số TS Kinh tế:");
        g2.gridx = 1; configReadOnlyNumber(txtTongTSKT); pThongKe.add(txtTongTSKT, g2);

        addLabel(pThongKe, g2, 3, "Điểm TB Chung:");
        g2.gridx = 1; configReadOnlyNumber(txtDiemTrungBinh); pThongKe.add(txtDiemTrungBinh, g2);

        // 6. BẢNG HIỂN THỊ DANH SÁCH THÍ SINH (CẤU HÌNH CỰC KỲ RÕ RÀNG KHÔNG ẨN CỘT)
        String[] cols = {"STT", "Mã Số", "Họ và Tên", "Loại Thí Sinh", "Đ.Toán", "Đ.Lý/Văn", "Đ.Hóa/Anh", "Tổng Điểm"};
        tableModel = new DefaultTableModel(cols, 0) {
            @Override
            public boolean isCellEditable(int r, int c) { return false; }
        };
        
        table = new JTable(tableModel) {
            @Override
            public Component prepareRenderer(TableCellRenderer renderer, int row, int col) {
                Component c = super.prepareRenderer(renderer, row, col);
                if (!isRowSelected(row)) {
                    c.setBackground(row % 2 == 0 ? Color.WHITE : new Color(238, 244, 255));
                }
                return c;
            }
        };
        
        table.setRowHeight(28);
        table.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        
        JTableHeader header = table.getTableHeader();
        header.setFont(new Font("Segoe UI", Font.BOLD, 13));
        header.setBackground(new Color(27, 94, 32));
        header.setForeground(Color.WHITE);
        header.setPreferredSize(new Dimension(0, 32));
        header.setReorderingAllowed(false);

        // Đặt kích thước độ rộng chuẩn cho từng cột
        int[] columnWidths = {50, 100, 200, 150, 80, 80, 80, 100};
        for (int i = 0; i < columnWidths.length; i++) {
            TableColumn col = table.getColumnModel().getColumn(i);
            col.setPreferredWidth(columnWidths[i]);
        }

        // Căn giữa STT và các cột điểm số
        DefaultTableCellRenderer centerRender = new DefaultTableCellRenderer();
        centerRender.setHorizontalAlignment(SwingConstants.CENTER);
        for (int i : new int[]{0, 1, 4, 5, 6, 7}) {
            table.getColumnModel().getColumn(i).setCellRenderer(centerRender);
        }

        table.setFillsViewportHeight(true);

        JScrollPane spTable = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        spTable.setPreferredSize(new Dimension(840, 180));
        spTable.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(new Color(27, 94, 32), 1),
                "Danh sách Thí sinh đã nhập",
                TitledBorder.LEFT, TitledBorder.TOP,
                new Font("Segoe UI", Font.BOLD, 13), new Color(27, 94, 32)
        ));

        // Gom các phần lại
        JPanel pTopContent = new JPanel();
        pTopContent.setLayout(new BoxLayout(pTopContent, BoxLayout.Y_AXIS));
        pTopContent.setOpaque(false);
        pTopContent.add(pThongTin);
        pTopContent.add(Box.createVerticalStrut(6));
        pTopContent.add(pButtons);
        pTopContent.add(Box.createVerticalStrut(6));
        pTopContent.add(pThongKe);

        pMain.add(lblHeader, BorderLayout.NORTH);
        pMain.add(pTopContent, BorderLayout.CENTER);
        pMain.add(spTable, BorderLayout.SOUTH);

        add(pMain);

        // Phím Enter nhận sự kiện nút "Tạo bảng"
        getRootPane().setDefaultButton(btnTaoBang);

        // ===== GÁN SỰ KIỆN NÚT =====
        btnTaoBang.addActionListener(e -> suKienTaoBang());
        btnTiep.addActionListener(e -> suKienTiep());
        btnThongKe.addActionListener(e -> suKienThongKe());
        btnKetThuc.addActionListener(e -> suKienKetThuc());

        pack();
        // Vị trí ban đầu là giữa màn hình
        setLocationRelativeTo(null);
        setResizable(false);
    }

    // =====================================================================
    // XỬ LÝ SỰ KIỆN THAO TÁC
    // =====================================================================

    // 1. Nút "Tạo bảng" (Thêm dữ liệu thí sinh vào bảng)
    private void suKienTaoBang() {
        String ms = txtMaSo.getText().trim();
        String ht = txtHoTen.getText().trim();

        if (ms.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Mã số thí sinh không được phép rỗng!", "Lỗi nhập liệu", JOptionPane.ERROR_MESSAGE);
            txtMaSo.requestFocus();
            return;
        }

        if (ht.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Họ tên thí sinh không được phép rỗng!", "Lỗi nhập liệu", JOptionPane.ERROR_MESSAGE);
            txtHoTen.requestFocus();
            return;
        }

        double d1 = docDiem(txtDiem1, lblMon1.getText());
        if (d1 < 0) return;

        double d2 = docDiem(txtDiem2, lblMon2.getText());
        if (d2 < 0) return;

        double d3 = docDiem(txtDiem3, lblMon3.getText());
        if (d3 < 0) return;

        double tongDiem = d1 + d2 + d3;
        String loaiTS = (String) cbLoaiTS.getSelectedItem();
        int stt = danhSachTS.size() + 1;

        String[] row = new String[]{
                String.valueOf(stt), ms, ht, loaiTS,
                df.format(d1), df.format(d2), df.format(d3),
                df.format(tongDiem)
        };

        danhSachTS.add(row);
        tableModel.addRow(row);
    }

    // 2. Nút "Tiếp" (Xóa nội dung nhập liệu và đặt focus vào ô Mã số)
    private void suKienTiep() {
        txtMaSo.setText("");
        txtHoTen.setText("");
        txtDiem1.setText("");
        txtDiem2.setText("");
        txtDiem3.setText("");
        cbLoaiTS.setSelectedIndex(0);
        txtMaSo.requestFocus(); // Đặt focus cho Textbox Mã số
    }

    // 3. Nút "Thống Kê" (Tính và hiển thị kết quả trên các ô trong groupbox Thống kê)
    private void suKienThongKe() {
        int tongSo = danhSachTS.size();
        int cnCount = 0;
        int ktCount = 0;
        double tongDiemChung = 0.0;

        for (String[] r : danhSachTS) {
            if (r[3].contains("Công nghệ")) cnCount++;
            else ktCount++;

            try {
                tongDiemChung += Double.parseDouble(r[7].replace(",", "."));
            } catch (Exception ignored) {}
        }

        txtTongSoTS.setText(String.valueOf(tongSo));
        txtTongTSCN.setText(String.valueOf(cnCount));
        txtTongTSKT.setText(String.valueOf(ktCount));

        if (tongSo > 0) {
            txtDiemTrungBinh.setText(df.format(tongDiemChung / tongSo));
        } else {
            txtDiemTrungBinh.setText("0.00");
        }
    }

    // 4. Nút "Kết Thúc" (Phát sinh messageBox hỏi người dùng có thật sự muốn đóng ứng dụng hay không)
    private void suKienKetThuc() {
        int ret = JOptionPane.showConfirmDialog(this,
                "Bạn có thật sự muốn đóng ứng dụng hay không?",
                "Xác nhận thoát",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE);
        if (ret == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }

    // =====================================================================
    // UTILS & STYLING
    // =====================================================================
    private double docDiem(JTextField tf, String tenMon) {
        try {
            double d = Double.parseDouble(tf.getText().trim());
            if (d < 0 || d > 10) {
                JOptionPane.showMessageDialog(this, tenMon.replace(":", "") + " phải từ 0 đến 10!", "Lỗi nhập điểm", JOptionPane.ERROR_MESSAGE);
                tf.requestFocus();
                return -1;
            }
            return d;
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, tenMon.replace(":", "") + " phải là số hợp lệ!", "Lỗi nhập điểm", JOptionPane.ERROR_MESSAGE);
            tf.requestFocus();
            return -1;
        }
    }

    private JPanel createGroupPanel(String title) {
        JPanel p = new JPanel();
        p.setBackground(new Color(245, 245, 245));
        p.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(new Color(160, 160, 160), 1),
                title, TitledBorder.LEFT, TitledBorder.TOP,
                new Font("Segoe UI", Font.BOLD, 13), Color.BLACK
        ));
        return p;
    }

    private void addLabel(JPanel p, GridBagConstraints g, int row, String text) {
        g.gridx = 0; g.gridy = row;
        JLabel lbl = new JLabel(text);
        lbl.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        p.add(lbl, g);
    }

    private void configNumberInput(JTextField tf) {
        tf.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        tf.setHorizontalAlignment(JTextField.RIGHT); // Canh lề phải số liệu
    }

    private void configReadOnlyNumber(JTextField tf) {
        tf.setFont(new Font("Segoe UI", Font.BOLD, 13));
        tf.setHorizontalAlignment(JTextField.RIGHT); // Canh lề phải số liệu
        tf.setEditable(false);
        tf.setBackground(new Color(240, 240, 240));
    }

    private void styleButton(JButton b) {
        b.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        b.setPreferredSize(new Dimension(100, 32));
        b.setFocusPainted(false);
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ignored) {}
        SwingUtilities.invokeLater(() -> new Buoi8().setVisible(true));
    }
}
