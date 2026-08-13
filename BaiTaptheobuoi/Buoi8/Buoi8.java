import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;
import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 * Buoi 8 - CHUONG TRINH QUAN LY THI SINH (GUI Swing Sieu Gọn)
 * - Bo hoàn toàn khung Thống kê.
 * - Thay nút Thống kê thành nút 'Hoàn thành'.
 * - Nhập xong bấm 'Hoàn thành' (hoặc Enter) là hiển thị thí sinh lên Bảng danh sách.
 * Sinh vien: Pham Ngoc Bach - MSSV: 2351170576
 */
public class Buoi8 extends JFrame {

    // ===== MODEL DU LIEU =====
    private ArrayList<String[]> danhSachTS = new ArrayList<>();

    // ===== KHU VUC NHAP THONG TIN THI SINH =====
    private JTextField txtMaSo = new JTextField(22);
    private JTextField txtHoTen = new JTextField(22);
    private JComboBox<String> cbLoaiTS = new JComboBox<>(new String[]{"Thí sinh Công nghệ", "Thí sinh Kinh tế"});
    
    private JLabel lblMon1 = new JLabel("Điểm Toán:");
    private JLabel lblMon2 = new JLabel("Điểm Lý:");
    private JLabel lblMon3 = new JLabel("Điểm Hóa:");
    
    private JTextField txtDiem1 = new JTextField(22);
    private JTextField txtDiem2 = new JTextField(22);
    private JTextField txtDiem3 = new JTextField(22);

    // ===== CÁC NÚT THAO TÁC =====
    private JButton btnHoanThanh = new JButton("Hoàn thành");
    private JButton btnTiep = new JButton("Tiếp");

    // ===== BẢNG HIỂN THỊ DANH SÁCH =====
    private DefaultTableModel tableModel;
    private JTable table;

    private DecimalFormat df = new DecimalFormat("#,##0.00");

    public Buoi8() {
        // 1. Tiêu đề Form đúng yêu cầu
        setTitle("Quản Lý Thí Sinh (Phạm Ngọc Bách – 2351170576)");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        JPanel pMain = new JPanel(new BorderLayout(10, 10));
        pMain.setBorder(new EmptyBorder(12, 15, 15, 15));
        pMain.setBackground(new Color(242, 244, 248));

        // 2. HEADER BANNER
        JLabel lblHeader = new JLabel("CHƯƠNG TRÌNH QUẢN LÝ THÍ SINH", SwingConstants.CENTER);
        lblHeader.setFont(new Font("Segoe UI", Font.BOLD, 18));
        lblHeader.setOpaque(true);
        lblHeader.setBackground(new Color(165, 214, 167)); // Màu xanh lá gống mẫu
        lblHeader.setForeground(new Color(27, 94, 32));
        lblHeader.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(76, 175, 80), 2),
                new EmptyBorder(8, 0, 8, 0)
        ));

        // 3. GROUPBOX THÔNG TIN THÍ SINH
        JPanel pThongTin = createGroupPanel("Thông tin Thí sinh:");
        pThongTin.setLayout(new GridBagLayout());
        GridBagConstraints g = new GridBagConstraints();
        g.insets = new Insets(6, 10, 6, 10);
        g.anchor = GridBagConstraints.WEST;

        addLabel(pThongTin, g, 0, "Mã Số Thí Sinh:");
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

        // 4. HÀNG NÚT THAO TÁC (Chỉ gồm Hoàn thành & Tiếp)
        JPanel pButtons = new JPanel(new FlowLayout(FlowLayout.CENTER, 25, 10));
        pButtons.setOpaque(false);
        styleButton(btnHoanThanh, new Color(27, 94, 32));
        styleButton(btnTiep, new Color(21, 101, 192));

        pButtons.add(btnHoanThanh);
        pButtons.add(btnTiep);

        // 5. BẢNG HIỂN THỊ DANH SÁCH THÍ SINH
        String[] cols = {"STT", "Mã Số", "Họ và Tên", "Loại Thí Sinh", "Điểm 1", "Điểm 2", "Điểm 3", "Tổng Điểm"};
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

        // Custom Header Renderer (Fix lỗi chữ trắng trên Windows)
        JTableHeader header = table.getTableHeader();
        header.setPreferredSize(new Dimension(0, 32));
        header.setReorderingAllowed(false);
        header.setDefaultRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value,
                                                           boolean isSelected, boolean hasFocus, int row, int column) {
                JLabel label = new JLabel(value.toString(), SwingConstants.CENTER);
                label.setFont(new Font("Segoe UI", Font.BOLD, 13));
                label.setOpaque(true);
                label.setBackground(new Color(27, 94, 32));
                label.setForeground(Color.WHITE);
                label.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 1, new Color(200, 200, 200)));
                return label;
            }
        });

        // Kích thước độ rộng từng cột chuẩn
        int[] columnWidths = {50, 95, 190, 150, 80, 80, 80, 100};
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
        spTable.setPreferredSize(new Dimension(840, 220));
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
        pTopContent.add(Box.createVerticalStrut(8));
        pTopContent.add(pButtons);

        pMain.add(lblHeader, BorderLayout.NORTH);
        pMain.add(pTopContent, BorderLayout.CENTER);
        pMain.add(spTable, BorderLayout.SOUTH);

        add(pMain);

        // Phím Enter gán mặc định cho nút "Hoàn thành"
        getRootPane().setDefaultButton(btnHoanThanh);

        // ===== GÁN SỰ KIỆN NÚT =====
        btnHoanThanh.addActionListener(e -> suKienHoanThanh());
        btnTiep.addActionListener(e -> suKienTiep());

        pack();
        setLocationRelativeTo(null);
        setResizable(false);
    }

    // =====================================================================
    // XỬ LÝ SỰ KIỆN THAO TÁC
    // =====================================================================

    // Khi bấm nút "Hoàn thành" (hoặc phím Enter): Kiểm tra và thêm thí sinh vào bảng
    private void suKienHoanThanh() {
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

    // Nút "Tiếp" (Xóa trắng ô nhập liệu để nhập thí sinh tiếp theo)
    private void suKienTiep() {
        txtMaSo.setText("");
        txtHoTen.setText("");
        txtDiem1.setText("");
        txtDiem2.setText("");
        txtDiem3.setText("");
        cbLoaiTS.setSelectedIndex(0);
        txtMaSo.requestFocus();
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
        tf.setHorizontalAlignment(JTextField.RIGHT);
    }

    private void styleButton(JButton b, Color bg) {
        b.setFont(new Font("Segoe UI", Font.BOLD, 13));
        b.setBackground(bg);
        b.setForeground(Color.WHITE);
        b.setPreferredSize(new Dimension(125, 34));
        b.setFocusPainted(false);
        b.setBorderPainted(false);
        b.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ignored) {}
        SwingUtilities.invokeLater(() -> new Buoi8().setVisible(true));
    }
}
