package GTS;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

public class GUI_insertTS extends JFrame {

    private JTextField txtSoBD, txtHoten, txtTongD;
    private JComboBox<String> cboNganhH;
    private JRadioButton rdoNam, rdoNu;
    private ButtonGroup groupGT;
    private JButton btnThem, btnXoa, btnTimKiem;
    private JTable table;
    private DefaultTableModel tableModel;
    private XLTS xlts;

    public GUI_insertTS() {
        xlts = new XLTS();
        initUI();
        loadTable();
    }

    private void initUI() {
        setTitle("Quan ly Tuyen sinh");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(850, 560);
        setLocationRelativeTo(null);
        getContentPane().setBackground(new Color(245, 248, 255));
        setLayout(new BorderLayout(10, 10));

        JPanel pnlInput = new JPanel(new GridBagLayout());
        pnlInput.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(new Color(66, 133, 244), 2),
                "Thong tin Thi sinh", 0, 0,
                new Font("Arial", Font.BOLD, 14), new Color(66, 133, 244)));
        pnlInput.setBackground(Color.WHITE);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(6, 10, 6, 10);
        gbc.anchor = GridBagConstraints.WEST;

        Font lf = new Font("Arial", Font.BOLD, 13);
        Font ff = new Font("Arial", Font.PLAIN, 13);

        gbc.gridx = 0; gbc.gridy = 0;
        pnlInput.add(makeLabel("So bao danh:", lf), gbc);
        gbc.gridx = 1; gbc.gridwidth = 2;
        txtSoBD = makeTextField(ff); pnlInput.add(txtSoBD, gbc);

        gbc.gridx = 0; gbc.gridy = 1; gbc.gridwidth = 1;
        pnlInput.add(makeLabel("Ho ten:", lf), gbc);
        gbc.gridx = 1; gbc.gridwidth = 2;
        txtHoten = makeTextField(ff); pnlInput.add(txtHoten, gbc);

        gbc.gridx = 0; gbc.gridy = 2; gbc.gridwidth = 1;
        pnlInput.add(makeLabel("Gioi tinh:", lf), gbc);
        rdoNam = new JRadioButton("Nam"); rdoNam.setFont(ff); rdoNam.setSelected(true); rdoNam.setBackground(Color.WHITE);
        rdoNu  = new JRadioButton("Nu");  rdoNu.setFont(ff);  rdoNu.setBackground(Color.WHITE);
        groupGT = new ButtonGroup(); groupGT.add(rdoNam); groupGT.add(rdoNu);
        JPanel pnlGT = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        pnlGT.setBackground(Color.WHITE); pnlGT.add(rdoNam); pnlGT.add(rdoNu);
        gbc.gridx = 1; pnlInput.add(pnlGT, gbc);

        gbc.gridx = 0; gbc.gridy = 3; gbc.gridwidth = 1;
        pnlInput.add(makeLabel("Nganh hoc:", lf), gbc);
        gbc.gridx = 1; gbc.gridwidth = 2;
        cboNganhH = new JComboBox<>(new String[]{"Tri tue nhan tao", "Co khi", "Cong trinh thuy"});
        cboNganhH.setFont(ff); cboNganhH.setPreferredSize(new Dimension(200, 28));
        pnlInput.add(cboNganhH, gbc);

        gbc.gridx = 0; gbc.gridy = 4; gbc.gridwidth = 1;
        pnlInput.add(makeLabel("Tong diem:", lf), gbc);
        gbc.gridx = 1; gbc.gridwidth = 2;
        txtTongD = makeTextField(ff); pnlInput.add(txtTongD, gbc);

        JPanel pnlBtn = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 8));
        pnlBtn.setBackground(Color.WHITE);
        btnThem    = makeButton("Them thi sinh moi", new Color(66, 133, 244));
        btnTimKiem = makeButton("Tim kiem",           new Color(52, 168, 83));
        btnXoa     = makeButton("Xoa dong chon",      new Color(234, 67, 53));
        pnlBtn.add(btnThem); pnlBtn.add(btnTimKiem); pnlBtn.add(btnXoa);

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
                BorderFactory.createLineBorder(new Color(66, 133, 244), 1), "Danh sach Thi sinh"));

        JPanel pnlTop = new JPanel(new BorderLayout());
        pnlTop.add(pnlInput, BorderLayout.CENTER);
        pnlTop.add(pnlBtn, BorderLayout.SOUTH);
        pnlTop.setBorder(BorderFactory.createEmptyBorder(8, 8, 0, 8));
        add(pnlTop, BorderLayout.NORTH);
        add(scroll, BorderLayout.CENTER);

        btnThem.addActionListener((ActionEvent e)    -> themThiSinh());
        btnXoa.addActionListener((ActionEvent e)     -> xoaDong());
        btnTimKiem.addActionListener((ActionEvent e) -> timKiem());
        table.getSelectionModel().addListSelectionListener(ev -> {
            if (!ev.getValueIsAdjusting()) fillForm();
        });
    }

    private void loadTable() {
        tableModel.setRowCount(0);
        for (Thisinh ts : xlts.getTS()) {
            tableModel.addRow(new Object[]{
                ts.getSoBD(), ts.getHoten(), ts.getGT(),
                ts.getNganhH(), ts.getTongD(), ts.Hocbong()
            });
        }
    }

    private void themThiSinh() {
        String soBD    = txtSoBD.getText().trim();
        String hoten   = txtHoten.getText().trim();
        String gt      = rdoNam.isSelected() ? "Nam" : "Nu";
        String nganh   = (String) cboNganhH.getSelectedItem();
        String tongDStr = txtTongD.getText().trim();

        if (soBD.isEmpty() || hoten.isEmpty() || tongDStr.isEmpty()) {
            showError("Vui long nhap day du thong tin!", "Thieu thong tin"); return;
        }

        double tongD;
        try {
            tongD = Double.parseDouble(tongDStr);
        } catch (NumberFormatException e) {
            showError("Tong diem phai la so! (Vi du: 27.5)", "Dinh dang sai");
            txtTongD.requestFocus(); return;
        }

        if (tongD < 0 || tongD > 30) {
            showError("Tong diem phai tu 0.0 den 30.0!", "Diem khong hop le");
            txtTongD.requestFocus(); return;
        }

        boolean ok = xlts.insertTS(new Thisinh(soBD, hoten, gt, nganh, tongD));
        if (ok) {
            loadTable(); clearForm();
            showSuccess("Them thi sinh [" + soBD + "] thanh cong!");
        } else {
            showError("So bao danh '" + soBD + "' da ton tai!", "Trung so bao danh");
            txtSoBD.requestFocus(); txtSoBD.selectAll();
        }
    }

    private void xoaDong() {
        int row = table.getSelectedRow();
        if (row < 0) { showError("Vui long chon dong can xoa!", "Chua chon dong"); return; }
        int c = JOptionPane.showConfirmDialog(this, "Ban co chac muon xoa?", "Xac nhan", JOptionPane.YES_NO_OPTION);
        if (c == JOptionPane.YES_OPTION) { xlts.xoaThiSinh(row); loadTable(); clearForm(); }
    }

    private void timKiem() {
        String soBD = txtSoBD.getText().trim();
        if (soBD.isEmpty()) { showError("Nhap So Bao Danh de tim kiem!", "Thieu thong tin"); return; }
        Thisinh ts = xlts.timKiem(soBD);
        if (ts != null) {
            ArrayList<Thisinh> ds = xlts.getTS();
            for (int i = 0; i < ds.size(); i++) {
                if (ds.get(i).getSoBD().equalsIgnoreCase(soBD)) {
                    table.setRowSelectionInterval(i, i);
                    table.scrollRectToVisible(table.getCellRect(i, 0, true));
                    break;
                }
            }
            showSuccess("Tim thay: " + ts.getHoten() + " | Diem: " + ts.getTongD());
        } else {
            showError("Khong tim thay thi sinh co SBD: " + soBD, "Khong tim thay");
        }
    }

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
        rdoNam.setSelected(true); cboNganhH.setSelectedIndex(0);
    }

    private void showError(String msg, String title) {
        JOptionPane.showMessageDialog(this, msg, title, JOptionPane.ERROR_MESSAGE);
    }
    private void showSuccess(String msg) {
        JOptionPane.showMessageDialog(this, msg, "Thanh cong", JOptionPane.INFORMATION_MESSAGE);
    }
    private JLabel makeLabel(String text, Font f) { JLabel l = new JLabel(text); l.setFont(f); return l; }
    private JTextField makeTextField(Font f) { JTextField tf = new JTextField(20); tf.setFont(f); return tf; }
    private JButton makeButton(String text, Color bg) {
        JButton btn = new JButton(text);
        btn.setFont(new Font("Arial", Font.BOLD, 13));
        btn.setBackground(bg); btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
        btn.setBorder(BorderFactory.createEmptyBorder(6, 16, 6, 16));
        btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        return btn;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new GUI_insertTS().setVisible(true));
    }
}
