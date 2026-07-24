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

        // ========== PANEL NHAP LIEU ==========
        JPanel pnlInput = new JPanel(new GridBagLayout());
        pnlInput.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(new Color(66, 133, 244), 2),
                "Thong tin Thi sinh", 0, 0,
                new Font("Arial", Font.BOLD, 14), new Color(66, 133, 244)));
        pnlInput.setBackground(Color.WHITE);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(6, 10, 6, 10);
        gbc.anchor = GridBagConstraints.WEST;

        Font labelFont = new Font("Arial", Font.BOLD, 13);
        Font fieldFont = new Font("Arial", Font.PLAIN, 13);

        // So bao danh
        gbc.gridx = 0; gbc.gridy = 0;
        pnlInput.add(makeLabel("So bao danh:", labelFont), gbc);
        gbc.gridx = 1; gbc.gridwidth = 2;
        txtSoBD = makeTextField(fieldFont);
        pnlInput.add(txtSoBD, gbc);

        // Ho ten
        gbc.gridx = 0; gbc.gridy = 1; gbc.gridwidth = 1;
        pnlInput.add(makeLabel("Ho ten:", labelFont), gbc);
        gbc.gridx = 1; gbc.gridwidth = 2;
        txtHoten = makeTextField(fieldFont);
        pnlInput.add(txtHoten, gbc);

        // Gioi tinh
        gbc.gridx = 0; gbc.gridy = 2; gbc.gridwidth = 1;
        pnlInput.add(makeLabel("Gioi tinh:", labelFont), gbc);
        rdoNam = new JRadioButton("Nam"); rdoNam.setFont(fieldFont); rdoNam.setSelected(true); rdoNam.setBackground(Color.WHITE);
        rdoNu  = new JRadioButton("Nu");  rdoNu.setFont(fieldFont);  rdoNu.setBackground(Color.WHITE);
        groupGT = new ButtonGroup();
        groupGT.add(rdoNam); groupGT.add(rdoNu);
        JPanel pnlGT = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        pnlGT.setBackground(Color.WHITE);
        pnlGT.add(rdoNam); pnlGT.add(rdoNu);
        gbc.gridx = 1; pnlInput.add(pnlGT, gbc);

        // Nganh hoc
        gbc.gridx = 0; gbc.gridy = 3; gbc.gridwidth = 1;
        pnlInput.add(makeLabel("Nganh hoc:", labelFont), gbc);
        gbc.gridx = 1; gbc.gridwidth = 2;
        cboNganhH = new JComboBox<>(new String[]{
            "Tri tue nhan tao", "Co khi", "Cong trinh thuy"
        });
        cboNganhH.setFont(fieldFont);
        cboNganhH.setPreferredSize(new Dimension(200, 28));
        pnlInput.add(cboNganhH, gbc);

        // Tong diem
        gbc.gridx = 0; gbc.gridy = 4; gbc.gridwidth = 1;
        pnlInput.add(makeLabel("Tong diem:", labelFont), gbc);
        gbc.gridx = 1; gbc.gridwidth = 2;
        txtTongD = makeTextField(fieldFont);
        pnlInput.add(txtTongD, gbc);

        // ========== PANEL BUTTONS ==========
        JPanel pnlBtn = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 8));
        pnlBtn.setBackground(Color.WHITE);
        btnThem    = makeButton("Them thi sinh moi", new Color(66, 133, 244));
        btnTimKiem = makeButton("Tim kiem",           new Color(52, 168, 83));
        btnXoa     = makeButton("Xoa dong chon",      new Color(234, 67, 53));
        pnlBtn.add(btnThem); pnlBtn.add(btnTimKiem); pnlBtn.add(btnXoa);

        // ========== TABLE ==========
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

        // ========== LAYOUT ==========
        JPanel pnlTop = new JPanel(new BorderLayout());
        pnlTop.add(pnlInput, BorderLayout.CENTER);
        pnlTop.add(pnlBtn,   BorderLayout.SOUTH);
        pnlTop.setBorder(BorderFactory.createEmptyBorder(8, 8, 0, 8));
        add(pnlTop, BorderLayout.NORTH);
        add(scroll, BorderLayout.CENTER);

        // ========== EVENTS ==========
        btnThem.addActionListener((ActionEvent e)    -> themThiSinh());
        btnXoa.addActionListener((ActionEvent e)     -> xoaDong());
        btnTimKiem.addActionListener((ActionEvent e) -> timKiem());
        table.getSelectionModel().addListSelectionListener(ev -> {
            if (!ev.getValueIsAdjusting()) fillForm();
        });
    }

    // ---- Load toan bo danh sach vao JTable ----
    private void loadTable() {
        tableModel.setRowCount(0);
        for (Thisinh ts : xlts.getTS()) {
            tableModel.addRow(new Object[]{
                ts.getSoBD(), ts.getHoten(), ts.getGT(),
                ts.getNganhH(), ts.getTongD(), ts.Hocbong()
            });
        }
    }

    // ---- Them thi sinh moi (co xu ly Exception) ----
    private void themThiSinh() {
        String soBD  = txtSoBD.getText().trim();
        String hoten = txtHoten.getText().trim();
        String gt    = rdoNam.isSelected() ? "Nam" : "Nu";
        String nganh = (String) cboNganhH.getSelectedItem();
        String tongDStr = txtTongD.getText().trim();

        // Kiem tra truong trong
        if (soBD.isEmpty() || hoten.isEmpty() || tongDStr.isEmpty()) {
            showError("Vui long nhap day du thong tin!", "Thieu thong tin");
            return;
        }

        // Parse diem - bat NumberFormatException
        double tongD;
        try {
            tongD = Double.parseDouble(tongDStr);
        } catch (NumberFormatException e) {
            showError("Tong diem phai la so! (Vi du: 27.5)", "Dinh dang sai");
            txtTongD.requestFocus();
            return;
        }

        // Them vao he thong - bat TuyenSinhException
        try {
            Thisinh ts = new Thisinh(soBD, hoten, gt, nganh, tongD);
            xlts.insertTS(ts);
            loadTable();
            clearForm();
            showSuccess("Them thi sinh [" + soBD + "] thanh cong!");

        } catch (DiemKhongHopLeException e) {
            // Diem ngoai khoang 0-30
            showError(e.getMessage(), "Diem khong hop le");
            txtTongD.requestFocus();
            txtTongD.selectAll();

        } catch (SoBDDaTonTaiException e) {
            // So bao danh trung
            showError(e.getMessage(), "So bao danh trung");
            txtSoBD.requestFocus();
            txtSoBD.selectAll();

        } catch (Exception e) {
            // Bat tat ca cac loi khac khong luong truoc
            showError("Loi khong xac dinh: " + e.getMessage(), "Loi he thong");
        }
    }

    // ---- Xoa dong duoc chon ----
    private void xoaDong() {
        int row = table.getSelectedRow();
        if (row < 0) {
            showError("Vui long chon dong can xoa!", "Chua chon dong");
            return;
        }
        int confirm = JOptionPane.showConfirmDialog(this,
                "Ban co chac muon xoa thi sinh nay?", "Xac nhan xoa",
                JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
        if (confirm == JOptionPane.YES_OPTION) {
            xlts.xoaThiSinh(row);
            loadTable();
            clearForm();
        }
    }

    // ---- Tim kiem theo So Bao Danh (co xu ly ThiSinhKhongTonTaiException) ----
    private void timKiem() {
        String soBD = txtSoBD.getText().trim();
        if (soBD.isEmpty()) {
            showError("Nhap So Bao Danh de tim kiem!", "Thieu thong tin");
            return;
        }

        try {
            Thisinh ts = xlts.timKiem(soBD);

            // Highlight dong tim duoc
            ArrayList<Thisinh> ds = xlts.getTS();
            for (int i = 0; i < ds.size(); i++) {
                if (ds.get(i).getSoBD().equalsIgnoreCase(soBD)) {
                    table.setRowSelectionInterval(i, i);
                    table.scrollRectToVisible(table.getCellRect(i, 0, true));
                    break;
                }
            }
            showSuccess("Tim thay: " + ts.getHoten() + " | Diem: " + ts.getTongD());

        } catch (ThiSinhKhongTonTaiException e) {
            // Khong tim thay
            showError(e.getMessage(), "Khong tim thay");
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

    // ---- Helper: hien thi dialog loi / thanh cong ----
    private void showError(String msg, String title) {
        JOptionPane.showMessageDialog(this, msg, title, JOptionPane.ERROR_MESSAGE);
    }

    private void showSuccess(String msg) {
        JOptionPane.showMessageDialog(this, msg, "Thanh cong", JOptionPane.INFORMATION_MESSAGE);
    }

    // ---- Utility ----
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
