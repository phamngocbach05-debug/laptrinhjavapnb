import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;



public class Buoi8 extends JFrame {
    

    // ===== DU LIEU =====
    private ArrayList<String[]> danhSach = new ArrayList<>();

    // ===== KHUA VUC CONG NGHE =====
    private JTextField cnMaSo   = new JTextField(10);
    private JTextField cnHoTen  = new JTextField(14);
    private JTextField cnToan   = new JTextField(5);
    private JTextField cnLy     = new JTextField(5);
    private JTextField cnHoa    = new JTextField(5);
    private JRadioButton cnDangKy = new JRadioButton("Da dang ky");
    private JComboBox<String> cnNganh = new JComboBox<>(new String[]{
            "CNTT", "Co khi", "Dien tu", "Xay dung"});

    // ===== KHU VUC KINH TE =====
    private JTextField ktMaSo   = new JTextField(10);
    private JTextField ktHoTen  = new JTextField(14);
    private JTextField ktToan   = new JTextField(5);
    private JTextField ktVan    = new JTextField(5);
    private JTextField ktAnh    = new JTextField(5);
    private JRadioButton ktDangKy = new JRadioButton("Da dang ky");
    private JComboBox<String> ktNganh = new JComboBox<>(new String[]{
            "Ke toan", "Tai chinh", "Quan tri", "Marketing"});

    // ===== BANG DU LIEU =====
    private DefaultTableModel tableModel;
    private JTable table;

    // =====================================================================
    public Buoi8() {
        setTitle("Buoi 8 - Quan ly Thi sinh (Swing GUI)");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout(8, 8));
        getContentPane().setBackground(new Color(240, 244, 250));

        // --- Tieu de ---
        JLabel lblTitle = new JLabel("QUAN LY THI SINH", SwingConstants.CENTER);
        lblTitle.setFont(new Font("Arial", Font.BOLD, 20));
        lblTitle.setForeground(new Color(13, 71, 161));
        lblTitle.setBorder(new EmptyBorder(12, 0, 4, 0));
        add(lblTitle, BorderLayout.NORTH);

        // --- Panel nhap lieu (2 khu vuc) ---
        JPanel pInput = new JPanel(new GridLayout(1, 2, 12, 0));
        pInput.setOpaque(false);
        pInput.setBorder(new EmptyBorder(0, 10, 0, 10));
        pInput.add(buildCNPanel());
        pInput.add(buildKTPanel());

        // --- Panel buttons ---
        JButton btnThemCN  = new JButton("Them CN");
        JButton btnThemKT  = new JButton("Them KT");
        JButton btnHienThi = new JButton("Hien thi");
        JButton btnXoa     = new JButton("Xoa dong");
        JButton btnXoaHet  = new JButton("Xoa het");
        JButton btnThoat   = new JButton("Thoat");

        styleBtn(btnThemCN,  new Color(27, 94, 32));
        styleBtn(btnThemKT,  new Color(1, 87, 155));
        styleBtn(btnHienThi, new Color(74, 20, 140));
        styleBtn(btnXoa,     new Color(183, 28, 28));
        styleBtn(btnXoaHet,  new Color(130, 0, 0));
        styleBtn(btnThoat,   new Color(62, 62, 62));

        btnThemCN.addActionListener(e -> themCongNghe());
        btnThemKT.addActionListener(e -> themKinhTe());
        btnHienThi.addActionListener(e -> hienThi());
        btnXoa.addActionListener(e -> xoaDong());
        btnXoaHet.addActionListener(e -> { danhSach.clear(); tableModel.setRowCount(0); });
        btnThoat.addActionListener(e -> System.exit(0));

        JPanel pBtn = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 8));
        pBtn.setOpaque(false);
        for (JButton b : new JButton[]{btnThemCN, btnThemKT, btnHienThi, btnXoa, btnXoaHet, btnThoat})
            pBtn.add(b);

        // --- Bang ket qua ---
        String[] cols = {"STT", "Ma so", "Ho ten", "Nganh", "Toan", "Mon2", "Mon3", "Tong diem", "Dang ky"};
        tableModel = new DefaultTableModel(cols, 0) {
            public boolean isCellEditable(int r, int c) { return false; }
        };
        table = new JTable(tableModel) {
            // Zebra striping (mau xen ke hang)
            @Override
            public Component prepareRenderer(TableCellRenderer renderer, int row, int col) {
                Component c = super.prepareRenderer(renderer, row, col);
                if (!isRowSelected(row)) {
                    c.setBackground(row % 2 == 0
                            ? Color.WHITE
                            : new Color(235, 243, 255));
                }
                return c;
            }
        };
        table.setRowHeight(28);
        table.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        table.setShowVerticalLines(true);
        table.setShowHorizontalLines(true);
        table.setGridColor(new Color(180, 200, 230));
        table.setIntercellSpacing(new Dimension(8, 4));
        table.setSelectionBackground(new Color(144, 202, 249));
        table.setSelectionForeground(Color.BLACK);
        table.setFillsViewportHeight(true);

        // Header dep
        JTableHeader header = table.getTableHeader();
        header.setFont(new Font("Segoe UI", Font.BOLD, 13));
        header.setBackground(new Color(13, 71, 161));
        header.setForeground(Color.WHITE);
        header.setPreferredSize(new Dimension(0, 32));
        header.setReorderingAllowed(false);

        // Do rong cot
        int[] colWidths = {40, 80, 160, 100, 65, 65, 65, 85, 70};
        for (int i = 0; i < colWidths.length; i++) {
            table.getColumnModel().getColumn(i).setPreferredWidth(colWidths[i]);
        }
        // Can giua cac cot so
        DefaultTableCellRenderer centerRender = new DefaultTableCellRenderer();
        centerRender.setHorizontalAlignment(SwingConstants.CENTER);
        for (int i : new int[]{0, 4, 5, 6, 7, 8})
            table.getColumnModel().getColumn(i).setCellRenderer(centerRender);

        JScrollPane sp = new JScrollPane(table);
        sp.setBorder(BorderFactory.createCompoundBorder(
                new EmptyBorder(6, 0, 0, 0),
                BorderFactory.createTitledBorder(
                        BorderFactory.createLineBorder(new Color(13, 71, 161), 2),
                        "  Danh sach Thi sinh  ",
                        TitledBorder.LEFT, TitledBorder.TOP,
                        new Font("Segoe UI", Font.BOLD, 13),
                        new Color(13, 71, 161))));
        sp.getViewport().setBackground(Color.WHITE);
        sp.setPreferredSize(new Dimension(0, 240));

        // --- CENTER panel ---
        JPanel pCenter = new JPanel(new BorderLayout(5, 5));
        pCenter.setOpaque(false);
        pCenter.setBorder(new EmptyBorder(0, 10, 0, 10));
        pCenter.add(pInput, BorderLayout.NORTH);
        pCenter.add(pBtn,   BorderLayout.CENTER);
        pCenter.add(sp,     BorderLayout.SOUTH);

        add(pCenter, BorderLayout.CENTER);

        setSize(860, 600);
        setLocationRelativeTo(null);
        setResizable(false);
    }

    // =====================================================================
    // PANEL THI SINH CONG NGHE
    // =====================================================================
    private JPanel buildCNPanel() {
        JPanel p = new JPanel(new GridBagLayout());
        p.setBackground(new Color(232, 245, 233));
        p.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(new Color(27, 94, 32), 2),
                "Khu vuc: Thi sinh Cong nghe",
                TitledBorder.CENTER, TitledBorder.TOP,
                new Font("Arial", Font.BOLD, 13), new Color(27, 94, 32)));

        GridBagConstraints g = gbc();
        int r = 0;
        addRow(p, g, r++, "Ma so:",         cnMaSo);
        addRow(p, g, r++, "Ho ten:",         cnHoTen);
        addRow(p, g, r++, "Diem Toan:",      cnToan);
        addRow(p, g, r++, "Diem Ly:",        cnLy);
        addRow(p, g, r++, "Diem Hoa:",       cnHoa);

        // RadioButton
        g.gridx = 0; g.gridy = r;
        p.add(new JLabel("Dang ky:"), g);
        g.gridx = 1; cnDangKy.setOpaque(false);
        p.add(cnDangKy, g); r++;

        // ComboBox
        addComboRow(p, g, r, "Nganh CN:", cnNganh);
        return p;
    }

    // =====================================================================
    // PANEL THI SINH KINH TE
    // =====================================================================
    private JPanel buildKTPanel() {
        JPanel p = new JPanel(new GridBagLayout());
        p.setBackground(new Color(227, 242, 253));
        p.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(new Color(1, 87, 155), 2),
                "Khu vuc: Thi sinh Kinh te",
                TitledBorder.CENTER, TitledBorder.TOP,
                new Font("Arial", Font.BOLD, 13), new Color(1, 87, 155)));

        GridBagConstraints g = gbc();
        int r = 0;
        addRow(p, g, r++, "Ma so:",    ktMaSo);
        addRow(p, g, r++, "Ho ten:",   ktHoTen);
        addRow(p, g, r++, "Diem Toan:",ktToan);
        addRow(p, g, r++, "Diem Van:", ktVan);
        addRow(p, g, r++, "Diem Anh:", ktAnh);

        g.gridx = 0; g.gridy = r;
        p.add(new JLabel("Dang ky:"), g);
        g.gridx = 1; ktDangKy.setOpaque(false);
        p.add(ktDangKy, g); r++;

        addComboRow(p, g, r, "Nganh KT:", ktNganh);
        return p;
    }

    // =====================================================================
    // THEM / HIEN THI / XOA
    // =====================================================================
    private void themCongNghe() {
        try {
            String ms = cnMaSo.getText().trim();
            String ht = cnHoTen.getText().trim();
            if (ms.isEmpty() || ht.isEmpty()) throw new Exception("Vui long nhap ma so va ho ten!");
            double toan = parseDiem(cnToan.getText(), "Toan");
            double ly   = parseDiem(cnLy.getText(),   "Ly");
            double hoa  = parseDiem(cnHoa.getText(),  "Hoa");
            double tong = toan + ly + hoa;
            String nganh = (String) cnNganh.getSelectedItem();
            String dk = cnDangKy.isSelected() ? "Co" : "Chua";
            int stt = danhSach.size() + 1;
            danhSach.add(new String[]{String.valueOf(stt), ms, ht, nganh,
                    df(toan), df(ly), df(hoa), df(tong), dk});
            tableModel.addRow(new Object[]{stt, ms, ht, nganh,
                    df(toan), df(ly), df(hoa), df(tong), dk});
            xoaNhapCN();
            JOptionPane.showMessageDialog(this, "Da them: " + ht + " [CongNghe]", "Thanh cong", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Loi nhap lieu", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void themKinhTe() {
        try {
            String ms = ktMaSo.getText().trim();
            String ht = ktHoTen.getText().trim();
            if (ms.isEmpty() || ht.isEmpty()) throw new Exception("Vui long nhap ma so va ho ten!");
            double toan = parseDiem(ktToan.getText(), "Toan");
            double van  = parseDiem(ktVan.getText(),  "Van");
            double anh  = parseDiem(ktAnh.getText(),  "Anh");
            double tong = toan + van + anh;
            String nganh = (String) ktNganh.getSelectedItem();
            String dk = ktDangKy.isSelected() ? "Co" : "Chua";
            int stt = danhSach.size() + 1;
            danhSach.add(new String[]{String.valueOf(stt), ms, ht, nganh,
                    df(toan), df(van), df(anh), df(tong), dk});
            tableModel.addRow(new Object[]{stt, ms, ht, nganh,
                    df(toan), df(van), df(anh), df(tong), dk});
            xoaNhapKT();
            JOptionPane.showMessageDialog(this, "Da them: " + ht + " [KinhTe]", "Thanh cong", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Loi nhap lieu", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void hienThi() {
        tableModel.setRowCount(0);
        for (String[] row : danhSach)
            tableModel.addRow(row);
        JOptionPane.showMessageDialog(this,
                "Tong so thi sinh: " + danhSach.size(), "Hien thi", JOptionPane.INFORMATION_MESSAGE);
    }

    private void xoaDong() {
        int row = table.getSelectedRow();
        if (row < 0) { JOptionPane.showMessageDialog(this, "Chon dong can xoa!", "Canh bao", JOptionPane.WARNING_MESSAGE); return; }
        danhSach.remove(row);
        tableModel.removeRow(row);
    }

    private void xoaNhapCN() {
        cnMaSo.setText(""); cnHoTen.setText("");
        cnToan.setText(""); cnLy.setText(""); cnHoa.setText("");
        cnDangKy.setSelected(false); cnNganh.setSelectedIndex(0);
    }

    private void xoaNhapKT() {
        ktMaSo.setText(""); ktHoTen.setText("");
        ktToan.setText(""); ktVan.setText(""); ktAnh.setText("");
        ktDangKy.setSelected(false); ktNganh.setSelectedIndex(0);
    }

    // =====================================================================
    // HELPERS
    // =====================================================================
    private double parseDiem(String s, String mon) throws Exception {
        try {
            double d = Double.parseDouble(s.trim());
            if (d < 0 || d > 10) throw new Exception("Diem " + mon + " phai tu 0 den 10!");
            return d;
        } catch (NumberFormatException e) {
            throw new Exception("Diem " + mon + " phai la so!");
        }
    }

    private String df(double v) { return String.format("%.1f", v); }

    private GridBagConstraints gbc() {
        GridBagConstraints g = new GridBagConstraints();
        g.insets = new Insets(5, 8, 5, 8);
        g.anchor = GridBagConstraints.WEST;
        return g;
    }

    private void addRow(JPanel p, GridBagConstraints g, int row, String lbl, JTextField tf) {
        g.gridx = 0; g.gridy = row; p.add(new JLabel(lbl), g);
        g.gridx = 1; p.add(tf, g);
    }

    private void addComboRow(JPanel p, GridBagConstraints g, int row, String lbl, JComboBox<String> cb) {
        g.gridx = 0; g.gridy = row; p.add(new JLabel(lbl), g);
        g.gridx = 1; p.add(cb, g);
    }

    private void styleBtn(JButton b, Color bg) {
        b.setBackground(bg);
        b.setForeground(Color.WHITE);
        b.setFont(new Font("Arial", Font.BOLD, 12));
        b.setFocusPainted(false);
        b.setBorderPainted(false);
        b.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        b.setPreferredSize(new Dimension(100, 32));
    }

    // =====================================================================
    // MAIN
    // =====================================================================
    public static void main(String[] args) {
        try { UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName()); }
        catch (Exception ignored) {}
        SwingUtilities.invokeLater(() -> new Buoi8().setVisible(true));
    }
}
