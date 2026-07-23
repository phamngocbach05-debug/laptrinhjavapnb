package GTS;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

public class GUI_insertTS extends JFrame {

    // ---- Components ----
    private JTextField txtSoBD, txtHoten, txtTongD;
    private JComboBox<String> cboNganhH;
    private JRadioButton rdoNam, rdoNu;
    private ButtonGroup groupGT;
    private JButton btnThem, btnXoa, btnTimKiem;
    private JTable table;
    private DefaultTableModel tableModel;

    // ---- Data layer ----
    private XLTS xlts;

    // ---- Constructor ----
    public GUI_insertTS() {
        xlts = new XLTS();
        initUI();
        loadTable();
    }

    // ---- Khoi tao giao dien ----
    private void initUI() {
        setTitle("Quan ly Tuyen sinh - DATS");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(850, 560);
        setLocationRelativeTo(null);
        getContentPane().setBackground(new Color(245, 248, 255));
        setLayout(new BorderLayout(10, 10));

        // ========== PANEL NHAP LIEU (TREN) ==========
        JPanel pnlInput = new JPanel(new GridBagLayout());
        pnlInput.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(new Color(66, 133, 244), 2),
                "Thong tin Thi sinh",
                0, 0,
                new Font("Arial", Font.BOLD, 14),
                new Color(66, 133, 244)));
        pnlInput.setBackground(Color.WHITE);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(6, 10, 6, 10);
        gbc.anchor = GridBagConstraints.WEST;

        Font labelFont = new Font("Arial", Font.BOLD, 13);
        Font fieldFont = new Font("Arial", Font.PLAIN, 13);

        // So bao danh
        gbc.gridx = 0; gbc.gridy = 0;
        pnlInput.add(makeLabel("So bao danh:", labelFont), gbc);
        gbc.gridx = 1; gbc.gridy = 0; gbc.gridwidth = 2;
        txtSoBD = makeTextField(fieldFont);
        pnlInput.add(txtSoBD, gbc);

        // Ho ten
        gbc.gridx = 0; gbc.gridy = 1; gbc.gridwidth = 1;
        pnlInput.add(makeLabel("Ho ten:", labelFont), gbc);
        gbc.gridx = 1; gbc.gridy = 1; gbc.gridwidth = 2;
        txtHoten = makeTextField(fieldFont);
        pnlInput.add(txtHoten, gbc);

        // Gioi tinh
        gbc.gridx = 0; gbc.gridy = 2; gbc.gridwidth = 1;
        pnlInput.add(makeLabel("Gioi tinh:", labelFont), gbc);
        gbc.gridx = 1; gbc.gridy = 2;
        rdoNam = new JRadioButton("Nam"); rdoNam.setFont(fieldFont); rdoNam.setSelected(true); rdoNam.setBackground(Color.WHITE);
        rdoNu  = new JRadioButton("Nu");  rdoNu.setFont(fieldFont);  rdoNu.setBackground(Color.WHITE);
        groupGT = new ButtonGroup();
        groupGT.add(rdoNam);
        groupGT.add(rdoNu);
        JPanel pnlGT = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        pnlGT.setBackground(Color.WHITE);
        pnlGT.add(rdoNam); pnlGT.add(rdoNu);
        pnlInput.add(pnlGT, gbc);

        // Nganh hoc
        gbc.gridx = 0; gbc.gridy = 3; gbc.gridwidth = 1;
        pnlInput.add(makeLabel("Nganh hoc:", labelFont), gbc);
        gbc.gridx = 1; gbc.gridy = 3; gbc.gridwidth = 2;
        cboNganhH = new JComboBox<>(new String[]{
            "Tri tue nhan tao", "Co khi", "Cong trinh thuy"
        });
        cboNganhH.setFont(fieldFont);
        cboNganhH.setPreferredSize(new Dimension(200, 28));
        pnlInput.add(cboNganhH, gbc);

        // Tong diem
        gbc.gridx = 0; gbc.gridy = 4; gbc.gridwidth = 1;
        pnlInput.add(makeLabel("Tong diem:", labelFont), gbc);
        gbc.gridx = 1; gbc.gridy = 4; gbc.gridwidth = 2;
        txtTongD = makeTextField(fieldFont);
        pnlInput.add(txtTongD, gbc);

        // ========== PANEL BUTTONS ==========
        JPanel pnlBtn = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 8));
        pnlBtn.setBackground(Color.WHITE);

        btnThem     = makeButton("Them thi sinh moi", new Color(66, 133, 244));
        btnTimKiem  = makeButton("Tim kiem",           new Color(52, 168, 83));
        btnXoa      = makeButton("Xoa dong chon",      new Color(234, 67, 53));

        pnlBtn.add(btnThem);
        pnlBtn.add(btnTimKiem);
        pnlBtn.add(btnXoa);

        // ========== PANEL TABLE (DUOI) ==========
        String[] cols = {"So BD", "Ho ten", "Gioi tinh", "Nganh hoc", "Tong diem", "Hoc bong"};
        tableModel = new DefaultTableModel(cols, 0) {
            @Override public boolean isCellEditable(int r, int c) { return false; }
        };
        table = new JTable(tableModel);
        table.setFont(new Font("Arial", Font.PLAIN, 13));
        table.setRowHeight(28);
        table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 13));
        table.getTableHeader().setBackground(new Color(66, 133, 244));
        table.getTableHeader().setForeground(Color.WHITE);
        table.setSelectionBackground(new Color(197, 218, 255));
        JScrollPane scroll = new JScrollPane(table);
        scroll.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(new Color(66, 133, 244), 1),
                "Danh sach Thi sinh"));

        // ========== LAYOUT TONG HOP ==========
        JPanel pnlTop = new JPanel(new BorderLayout());
        pnlTop.add(pnlInput, BorderLayout.CENTER);
        pnlTop.add(pnlBtn,   BorderLayout.SOUTH);
        pnlTop.setBorder(BorderFactory.createEmptyBorder(8, 8, 0, 8));

        add(pnlTop,  BorderLayout.NORTH);
        add(scroll,  BorderLayout.CENTER);

        // ========== EVENTS ==========
        btnThem.addActionListener((ActionEvent e) -> themThiSinh());
        btnXoa.addActionListener((ActionEvent e) -> xoaDong());
        btnTimKiem.addActionListener((ActionEvent e) -> timKiem());

        // Click vao hang de dien vao form
        table.getSelectionModel().addListSelectionListener(ev -> {
            if (!ev.getValueIsAdjusting()) fillForm();
        });
    }

    // ---- Load toan bo danh sach vao JTable ----
    private void loadTable() {
        tableModel.setRowCount(0);
        ArrayList<Thisinh> ds = xlts.getTS();
        for (Thisinh ts : ds) {
            tableModel.addRow(new Object[]{
                ts.getSoBD(), ts.getHoten(), ts.getGT(),
                ts.getNganhH(), ts.getTongD(), ts.Hocbong()
            });
        }
    }

    // ---- Them thi sinh moi ----
    private void themThiSinh() {
        String soBD  = txtSoBD.getText().trim();
        String hoten = txtHoten.getText().trim();
        String gt    = rdoNam.isSelected() ? "Nam" : "Nu";
        String nganh = (String) cboNganhH.getSelectedItem();
        String tongDStr = txtTongD.getText().trim();

        if (soBD.isEmpty() || hoten.isEmpty() || tongDStr.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui long nhap day du thong tin!", "Loi", JOptionPane.WARNING_MESSAGE);
            return;
        }
        double tongD;
        try {
            tongD = Double.parseDouble(tongDStr);
            if (tongD < 0 || tongD > 30) throw new NumberFormatException();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Tong diem khong hop le (0 - 30)!", "Loi", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Thisinh ts = new Thisinh(soBD, hoten, gt, nganh, tongD);
        boolean ok = xlts.insertTS(ts);
        if (ok) {
            loadTable();
            clearForm();
            JOptionPane.showMessageDialog(this, "Them thi sinh thanh cong!", "Thong bao", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "So bao danh da ton tai!", "Loi", JOptionPane.WARNING_MESSAGE);
        }
    }

    // ---- Xoa dong duoc chon ----
    private void xoaDong() {
        int row = table.getSelectedRow();
        if (row < 0) {
            JOptionPane.showMessageDialog(this, "Vui long chon dong can xoa!", "Thong bao", JOptionPane.WARNING_MESSAGE);
            return;
        }
        int confirm = JOptionPane.showConfirmDialog(this, "Ban co chac muon xoa dong nay?", "Xac nhan", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            xlts.getTS().remove(row);
            loadTable();
            clearForm();
        }
    }

    // ---- Tim kiem theo so bao danh ----
    private void timKiem() {
        String soBD = txtSoBD.getText().trim();
        if (soBD.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Nhap So bao danh de tim kiem!", "Thong bao", JOptionPane.WARNING_MESSAGE);
            return;
        }
        Thisinh ts = xlts.timKiem(soBD);
        if (ts == null) {
            JOptionPane.showMessageDialog(this, "Khong tim thay thi sinh co So BD: " + soBD, "Ket qua", JOptionPane.INFORMATION_MESSAGE);
        } else {
            // Highlight dong tim duoc
            ArrayList<Thisinh> ds = xlts.getTS();
            for (int i = 0; i < ds.size(); i++) {
                if (ds.get(i).getSoBD().equalsIgnoreCase(soBD)) {
                    table.setRowSelectionInterval(i, i);
                    table.scrollRectToVisible(table.getCellRect(i, 0, true));
                    break;
                }
            }
        }
    }

    // ---- Dien thong tin tu dong vao form khi chon hang ----
    private void fillForm() {
        int row = table.getSelectedRow();
        if (row < 0) return;
        txtSoBD.setText(tableModel.getValueAt(row, 0).toString());
        txtHoten.setText(tableModel.getValueAt(row, 1).toString());
        String gt = tableModel.getValueAt(row, 2).toString();
        if ("Nam".equals(gt)) rdoNam.setSelected(true); else rdoNu.setSelected(true);
        cboNganhH.setSelectedItem(tableModel.getValueAt(row, 3).toString());
        txtTongD.setText(tableModel.getValueAt(row, 4).toString());
    }

    private void clearForm() {
        txtSoBD.setText(""); txtHoten.setText(""); txtTongD.setText("");
        rdoNam.setSelected(true);
        cboNganhH.setSelectedIndex(0);
    }

    // ---- Utility helpers ----
    private JLabel makeLabel(String text, Font f) {
        JLabel l = new JLabel(text); l.setFont(f); return l;
    }
    private JTextField makeTextField(Font f) {
        JTextField tf = new JTextField(20); tf.setFont(f); return tf;
    }
    private JButton makeButton(String text, Color bg) {
        JButton btn = new JButton(text);
        btn.setFont(new Font("Arial", Font.BOLD, 13));
        btn.setBackground(bg);
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
        btn.setBorder(BorderFactory.createEmptyBorder(6, 16, 6, 16));
        btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        return btn;
    }

    // ---- Main ----
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new GUI_insertTS().setVisible(true));
    }
}
