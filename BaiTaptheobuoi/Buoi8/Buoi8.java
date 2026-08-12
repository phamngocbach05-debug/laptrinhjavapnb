import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.text.DecimalFormat;

/**
 * Buoi 8 - CHUONG TRINH TINH TIEN BAN SACH
 * Sinh vien: Pham Ngoc Bach - MSSV: 2351170576
 */
public class Buoi8 extends JFrame {

    // ===== KHU VUC HOA DON =====
    private JTextField txtTenKH = new JTextField(20);
    private JTextField txtSoLuong = new JTextField(20);
    private JCheckBox chkLaSV = new JCheckBox();
    private JTextField txtThanhTien = new JTextField(20);

    // ===== BUTTONS =====
    private JButton btnTinhTT = new JButton("Tính TT");
    private JButton btnTiep = new JButton("Tiếp");
    private JButton btnThongKe = new JButton("Thống Kê");
    private JButton btnKetThuc = new JButton("Kết Thúc");

    // ===== KHU VUC THONG KE =====
    private JTextField txtTongKH = new JTextField(20);
    private JTextField txtTongKHSV = new JTextField(20);
    private JTextField txtTongDoanhThu = new JTextField(20);

    // ===== BIEN THEO DOI THONG KE =====
    private int tongKHCount = 0;
    private int tongKHSVCount = 0;
    private double tongDoanhThuVal = 0.0;
    private static final double DON_GIA = 20000.0;
    private DecimalFormat df = new DecimalFormat("#,##0 VNĐ");

    public Buoi8() {
        // 1. Tieu de form
        setTitle("Quản Lý Sách (Phạm Ngọc Bách – 2351170576)");
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        // Event dong cua so bang xac nhan
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                suKienKetThuc();
            }
        });

        // Background & Padding
        JPanel pMain = new JPanel(new BorderLayout(10, 10));
        pMain.setBorder(new EmptyBorder(10, 15, 15, 15));
        pMain.setBackground(new Color(238, 238, 238));

        // 2. BANNER TIEU DE
        JLabel lblHeader = new JLabel("CHƯƠNG TRÌNH TÍNH TIỀN BÁN SÁCH", SwingConstants.CENTER);
        lblHeader.setFont(new Font("Arial", Font.BOLD, 18));
        lblHeader.setOpaque(true);
        lblHeader.setBackground(new Color(165, 214, 167)); // Mau xanh la nhat giong mau
        lblHeader.setForeground(new Color(27, 94, 32));
        lblHeader.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(76, 175, 80), 2),
                new EmptyBorder(8, 0, 8, 0)
        ));

        // 3. GROUPBOX HOA DON
        JPanel pHoaDon = createGroupPanel("Hóa Đơn:");
        pHoaDon.setLayout(new GridBagLayout());
        GridBagConstraints g = new GridBagConstraints();
        g.insets = new Insets(6, 8, 6, 8);
        g.anchor = GridBagConstraints.WEST;

        // Label + Controls Hoa Don
        addLabel(pHoaDon, g, 0, "Tên Khách Hàng:");
        g.gridx = 1; txtTenKH.setFont(new Font("Segoe UI", Font.PLAIN, 14)); pHoaDon.add(txtTenKH, g);

        addLabel(pHoaDon, g, 1, "Số lượng Sách:");
        g.gridx = 1; 
        txtSoLuong.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        txtSoLuong.setHorizontalAlignment(JTextField.RIGHT); // Canh le phai
        pHoaDon.add(txtSoLuong, g);

        addLabel(pHoaDon, g, 2, "Khách Hàng là SV:");
        g.gridx = 1; chkLaSV.setOpaque(false); pHoaDon.add(chkLaSV, g);

        addLabel(pHoaDon, g, 3, "Thành Tiền:");
        g.gridx = 1; 
        txtThanhTien.setFont(new Font("Segoe UI", Font.BOLD, 14));
        txtThanhTien.setHorizontalAlignment(JTextField.RIGHT); // Canh le phai
        txtThanhTien.setEditable(false);
        txtThanhTien.setBackground(new Color(245, 245, 245));
        pHoaDon.add(txtThanhTien, g);

        // 4. HANG NUT BUTTONS
        JPanel pButtons = new JPanel(new FlowLayout(FlowLayout.CENTER, 12, 10));
        pButtons.setOpaque(false);
        
        styleButton(btnTinhTT);
        styleButton(btnTiep);
        styleButton(btnThongKe);
        styleButton(btnKetThuc);

        pButtons.add(btnTinhTT);
        pButtons.add(btnTiep);
        pButtons.add(btnThongKe);
        pButtons.add(btnKetThuc);

        // 5. GROUPBOX THONG KE
        JPanel pThongKe = createGroupPanel("Thống kê:");
        pThongKe.setLayout(new GridBagLayout());
        GridBagConstraints g2 = new GridBagConstraints();
        g2.insets = new Insets(6, 8, 6, 8);
        g2.anchor = GridBagConstraints.WEST;

        addLabel(pThongKe, g2, 0, "Tổng số KH:");
        g2.gridx = 1; configStatField(txtTongKH); pThongKe.add(txtTongKH, g2);

        addLabel(pThongKe, g2, 1, "Tổng số KH là SV:");
        g2.gridx = 1; configStatField(txtTongKHSV); pThongKe.add(txtTongKHSV, g2);

        addLabel(pThongKe, g2, 2, "Tổng doanh thu:");
        g2.gridx = 1; configStatField(txtTongDoanhThu); pThongKe.add(txtTongDoanhThu, g2);

        // Center Panel chua (Hoa Don + Buttons + Thong Ke)
        JPanel pContent = new JPanel();
        pContent.setLayout(new BoxLayout(pContent, BoxLayout.Y_AXIS));
        pContent.setOpaque(false);
        pContent.add(pHoaDon);
        pContent.add(Box.createVerticalStrut(8));
        pContent.add(pButtons);
        pContent.add(Box.createVerticalStrut(8));
        pContent.add(pThongKe);

        pMain.add(lblHeader, BorderLayout.NORTH);
        pMain.add(pContent, BorderLayout.CENTER);

        add(pMain);

        // gán phím Enter cho nút Tính TT
        getRootPane().setDefaultButton(btnTinhTT);

        // ===== GAN SU KIEN =====
        btnTinhTT.addActionListener(e -> suKienTinhTT());
        btnTiep.addActionListener(e -> suKienTiep());
        btnThongKe.addActionListener(e -> suKienThongKe());
        btnKetThuc.addActionListener(e -> suKienKetThuc());

        pack();
        // 6. Vi tri xuat hien ban dau cua form la giua man hinh
        setLocationRelativeTo(null);
        setResizable(false);
    }

    // =====================================================================
    // XU LY SU KIEN
    // =====================================================================

    // 1. Nut Tinh TT
    private void suKienTinhTT() {
        String tenKH = txtTenKH.getText().trim();
        if (tenKH.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Tên khách hàng không được phép rỗng!", "Lỗi nhập liệu", JOptionPane.ERROR_MESSAGE);
            txtTenKH.requestFocus();
            return;
        }

        int soLuong = 0;
        try {
            soLuong = Integer.parseInt(txtSoLuong.getText().trim());
            if (soLuong <= 0) {
                JOptionPane.showMessageDialog(this, "Số lượng sách phải là số nguyên dương!", "Lỗi nhập liệu", JOptionPane.ERROR_MESSAGE);
                txtSoLuong.requestFocus();
                return;
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Số lượng sách phải là số nguyên dương hợp lệ!", "Lỗi nhập liệu", JOptionPane.ERROR_MESSAGE);
            txtSoLuong.requestFocus();
            return;
        }

        // Tinh thanh tien: Don gia 20000, SV duoc giam 5%
        double thanhTien = soLuong * DON_GIA;
        boolean laSV = chkLaSV.isSelected();
        if (laSV) {
            thanhTien *= 0.95; // Giam 5%
        }

        // Xuat ket qua len label Thanh tien
        txtThanhTien.setText(df.format(thanhTien));

        // Cap nhat du lieu thong ke
        tongKHCount++;
        if (laSV) tongKHSVCount++;
        tongDoanhThuVal += thanhTien;
    }

    // 2. Nut Tiep
    private void suKienTiep() {
        txtTenKH.setText("");
        txtSoLuong.setText("");
        chkLaSV.setSelected(false);
        txtThanhTien.setText("");
        txtTenKH.requestFocus(); // Dat focus cho Textbox Ten Khach Hang
    }

    // 3. Nut Thong Ke
    private void suKienThongKe() {
        txtTongKH.setText(String.valueOf(tongKHCount));
        txtTongKHSV.setText(String.valueOf(tongKHSVCount));
        txtTongDoanhThu.setText(df.format(tongDoanhThuVal));
    }

    // 4. Nut Ket Thuc
    private void suKienKetThuc() {
        int ret = JOptionPane.showConfirmDialog(this,
                "Bạn có thật sự muốn đóng ứng dụng không?",
                "Xác nhận thoát",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE);
        if (ret == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }

    // =====================================================================
    // UI HELPERS
    // =====================================================================
    private JPanel createGroupPanel(String title) {
        JPanel p = new JPanel();
        p.setBackground(new Color(245, 245, 245));
        p.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(new Color(150, 150, 150), 1),
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

    private void configStatField(JTextField tf) {
        tf.setFont(new Font("Segoe UI", Font.BOLD, 13));
        tf.setHorizontalAlignment(JTextField.RIGHT); // Canh le phai cho so lieu
        tf.setEditable(false);
        tf.setBackground(new Color(240, 240, 240));
    }

    private void styleButton(JButton b) {
        b.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        b.setPreferredSize(new Dimension(95, 30));
        b.setFocusPainted(false);
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ignored) {}
        SwingUtilities.invokeLater(() -> new Buoi8().setVisible(true));
    }
}
