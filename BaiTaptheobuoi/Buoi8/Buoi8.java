import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Buoi 8 - Chuong 6: Xay dung giao dien voi Swing
 * JFrame chua 2 khu vuc: Hinh chu nhat va Hinh tron
 * Thanh phan: JTextField, JRadioButton (filled), JComboBox (color)
 */
public class Buoi8 extends JFrame {

    // ===== MAU SAC =====
    private static final String[] COLORS = {"Red", "Green", "Blue", "Yellow", "Pink"};
    private static final Color[]  COLOR_MAP = {Color.RED, Color.GREEN, Color.BLUE, Color.YELLOW, Color.PINK};

    // ===== HINH CHU NHAT =====
    private JTextField txtWidth  = new JTextField(8);
    private JTextField txtLength = new JTextField(8);
    private JRadioButton rbFilledRect = new JRadioButton("To day");
    private JComboBox<String> cbColorRect = new JComboBox<>(COLORS);

    // ===== HINH TRON =====
    private JTextField txtRadius = new JTextField(8);
    private JRadioButton rbFilledCircle = new JRadioButton("To day");
    private JComboBox<String> cbColorCircle = new JComboBox<>(COLORS);

    // ===== CANVAS VE HINH =====
    private DrawPanel canvas = new DrawPanel();

    // ===== THONG TIN =====
    private JTextArea taInfo = new JTextArea(4, 30);

    // =====================================================================
    public Buoi8() {
        setTitle("Buoi 8 - Xay dung giao dien Swing - Shape");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));
        getContentPane().setBackground(new Color(245, 245, 250));

        // ---- PANEL NHAP LIEU (tren) ----
        JPanel pInput = new JPanel(new GridLayout(1, 2, 15, 0));
        pInput.setOpaque(false);
        pInput.setBorder(new EmptyBorder(10, 10, 5, 10));
        pInput.add(buildRectPanel());
        pInput.add(buildCirclePanel());

        // ---- PANEL BUTTON ----
        JButton btnVe    = new JButton("Ve hinh");
        JButton btnXoa   = new JButton("Xoa");
        JButton btnThoat = new JButton("Thoat");
        styleButton(btnVe,    new Color(46, 125, 50));
        styleButton(btnXoa,   new Color(198, 40, 40));
        styleButton(btnThoat, new Color(21, 101, 192));

        btnVe.addActionListener(e -> veHinh());
        btnXoa.addActionListener(e -> { canvas.clear(); taInfo.setText(""); });
        btnThoat.addActionListener(e -> System.exit(0));

        JPanel pBtn = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 8));
        pBtn.setOpaque(false);
        pBtn.add(btnVe); pBtn.add(btnXoa); pBtn.add(btnThoat);

        // ---- CANVAS VE ----
        canvas.setPreferredSize(new Dimension(600, 280));
        canvas.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(new Color(100,100,150), 2),
                "Ket qua ve hinh", TitledBorder.CENTER, TitledBorder.TOP,
                new Font("Arial", Font.BOLD, 13)));

        // ---- THONG TIN ----
        taInfo.setEditable(false);
        taInfo.setFont(new Font("Consolas", Font.PLAIN, 13));
        taInfo.setBackground(new Color(250, 250, 240));
        JScrollPane sp = new JScrollPane(taInfo);
        sp.setBorder(BorderFactory.createTitledBorder("Thong tin"));
        sp.setPreferredSize(new Dimension(600, 90));

        // ---- PANEL DUOI (canvas + info) ----
        JPanel pBottom = new JPanel(new BorderLayout(5, 5));
        pBottom.setOpaque(false);
        pBottom.setBorder(new EmptyBorder(0, 10, 10, 10));
        pBottom.add(canvas, BorderLayout.CENTER);
        pBottom.add(sp, BorderLayout.SOUTH);

        add(pInput,  BorderLayout.NORTH);
        add(pBtn,    BorderLayout.CENTER);
        add(pBottom, BorderLayout.SOUTH);

        pack();
        setLocationRelativeTo(null);
        setResizable(false);
    }

    // =====================================================================
    // PANEL HINH CHU NHAT
    // =====================================================================
    private JPanel buildRectPanel() {
        JPanel p = new JPanel(new GridBagLayout());
        p.setBackground(new Color(232, 245, 233));
        p.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(new Color(46, 125, 50), 2),
                "Hinh chu nhat (Rectangle)",
                TitledBorder.CENTER, TitledBorder.TOP,
                new Font("Arial", Font.BOLD, 13), new Color(46, 125, 50)));

        GridBagConstraints g = new GridBagConstraints();
        g.insets = new Insets(6, 8, 6, 8);
        g.anchor = GridBagConstraints.WEST;

        addRow(p, g, 0, "Chieu rong (width):",  txtWidth);
        addRow(p, g, 1, "Chieu dai (length):",  txtLength);

        // RadioButton filled
        g.gridx = 0; g.gridy = 2; p.add(new JLabel("Filled:"), g);
        g.gridx = 1; p.add(rbFilledRect, g);
        rbFilledRect.setOpaque(false);

        addComboRow(p, g, 3, "Mau sac (color):", cbColorRect);
        return p;
    }

    // =====================================================================
    // PANEL HINH TRON
    // =====================================================================
    private JPanel buildCirclePanel() {
        JPanel p = new JPanel(new GridBagLayout());
        p.setBackground(new Color(227, 242, 253));
        p.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(new Color(21, 101, 192), 2),
                "Hinh tron (Circle)",
                TitledBorder.CENTER, TitledBorder.TOP,
                new Font("Arial", Font.BOLD, 13), new Color(21, 101, 192)));

        GridBagConstraints g = new GridBagConstraints();
        g.insets = new Insets(6, 8, 6, 8);
        g.anchor = GridBagConstraints.WEST;

        addRow(p, g, 0, "Ban kinh (radius):", txtRadius);

        g.gridx = 0; g.gridy = 1; p.add(new JLabel("Filled:"), g);
        g.gridx = 1; p.add(rbFilledCircle, g);
        rbFilledCircle.setOpaque(false);

        addComboRow(p, g, 2, "Mau sac (color):", cbColorCircle);
        return p;
    }

    // =====================================================================
    // VE HINH
    // =====================================================================
    private void veHinh() {
        StringBuilder sb = new StringBuilder();
        boolean hasRect   = false;
        boolean hasCircle = false;

        // Doc rect
        try {
            double w = Double.parseDouble(txtWidth.getText().trim());
            double l = Double.parseDouble(txtLength.getText().trim());
            if (w <= 0 || l <= 0) throw new NumberFormatException();
            Color c = COLOR_MAP[cbColorRect.getSelectedIndex()];
            boolean filled = rbFilledRect.isSelected();
            canvas.setRect(w, l, c, filled);
            double dt = 2 * (w + l);
            double s  = w * l;
            sb.append(String.format("[Chu nhat] W=%.1f  L=%.1f  Chu vi=%.1f  Dien tich=%.1f  Mau=%s  Filled=%s%n",
                    w, l, dt, s, COLORS[cbColorRect.getSelectedIndex()], filled));
            hasRect = true;
        } catch (NumberFormatException ex) {
            sb.append("[Chu nhat] Width/Length khong hop le - bo qua\n");
        }

        // Doc circle
        try {
            double r = Double.parseDouble(txtRadius.getText().trim());
            if (r <= 0) throw new NumberFormatException();
            Color c = COLOR_MAP[cbColorCircle.getSelectedIndex()];
            boolean filled = rbFilledCircle.isSelected();
            canvas.setCircle(r, c, filled);
            double cv = 2 * Math.PI * r;
            double s  = Math.PI * r * r;
            sb.append(String.format("[Hinh tron] R=%.1f  Chu vi=%.2f  Dien tich=%.2f  Mau=%s  Filled=%s%n",
                    r, cv, s, COLORS[cbColorCircle.getSelectedIndex()], filled));
            hasCircle = true;
        } catch (NumberFormatException ex) {
            sb.append("[Hinh tron] Radius khong hop le - bo qua\n");
        }

        if (!hasRect) canvas.clearRect();
        if (!hasCircle) canvas.clearCircle();
        canvas.repaint();
        taInfo.setText(sb.toString());
    }

    // =====================================================================
    // HELPERS
    // =====================================================================
    private void addRow(JPanel p, GridBagConstraints g, int row, String label, JTextField tf) {
        g.gridx = 0; g.gridy = row; p.add(new JLabel(label), g);
        g.gridx = 1; p.add(tf, g);
    }

    private void addComboRow(JPanel p, GridBagConstraints g, int row, String label, JComboBox<String> cb) {
        g.gridx = 0; g.gridy = row; p.add(new JLabel(label), g);
        g.gridx = 1; p.add(cb, g);
    }

    private void styleButton(JButton btn, Color bg) {
        btn.setBackground(bg);
        btn.setForeground(Color.WHITE);
        btn.setFont(new Font("Arial", Font.BOLD, 13));
        btn.setFocusPainted(false);
        btn.setBorderPainted(false);
        btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btn.setPreferredSize(new Dimension(110, 34));
    }

    // =====================================================================
    // CANVAS VE HINH
    // =====================================================================
    class DrawPanel extends JPanel {
        private double rW, rL, cR;
        private Color  rC = Color.RED, cC = Color.BLUE;
        private boolean rFilled, cFilled;
        private boolean drawRect, drawCircle;

        DrawPanel() { setBackground(Color.WHITE); }

        void setRect(double w, double l, Color c, boolean filled) {
            rW = w; rL = l; rC = c; rFilled = filled; drawRect = true;
        }
        void setCircle(double r, Color c, boolean filled) {
            cR = r; cC = c; cFilled = filled; drawCircle = true;
        }
        void clearRect()   { drawRect   = false; repaint(); }
        void clearCircle() { drawCircle = false; repaint(); }
        void clear()       { drawRect = false; drawCircle = false; repaint(); }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g;
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            int margin = 20;
            int halfW = (getWidth() / 2) - margin;
            int halfH = getHeight() - 2 * margin;

            // Ve hinh chu nhat o nua trai
            if (drawRect) {
                double scale = Math.min((double) halfW / (rW + 1), (double) halfH / (rL + 1));
                scale = Math.min(scale, 8.0);
                int pw = (int)(rW * scale);
                int ph = (int)(rL * scale);
                int x = margin + (halfW - pw) / 2;
                int y = margin + (halfH - ph) / 2;
                if (rFilled) { g2.setColor(rC); g2.fillRect(x, y, pw, ph); }
                g2.setColor(rC.darker());
                g2.setStroke(new BasicStroke(2));
                g2.drawRect(x, y, pw, ph);
                g2.setColor(Color.DARK_GRAY);
                g2.setFont(new Font("Arial", Font.PLAIN, 11));
                g2.drawString("Chu nhat", x, y - 5);
            }

            // Ve hinh tron o nua phai
            if (drawCircle) {
                int offsetX = getWidth() / 2;
                double scale = Math.min((double) halfW / (cR * 2 + 1), (double) halfH / (cR * 2 + 1));
                scale = Math.min(scale, 8.0);
                int d = (int)(cR * 2 * scale);
                int x = offsetX + margin + (halfW - d) / 2;
                int y = margin + (halfH - d) / 2;
                if (cFilled) { g2.setColor(cC); g2.fillOval(x, y, d, d); }
                g2.setColor(cC.darker());
                g2.setStroke(new BasicStroke(2));
                g2.drawOval(x, y, d, d);
                g2.setColor(Color.DARK_GRAY);
                g2.setFont(new Font("Arial", Font.PLAIN, 11));
                g2.drawString("Hinh tron", x, y - 5);
            }

            // Duong ke giua
            g2.setColor(new Color(200, 200, 200));
            g2.setStroke(new BasicStroke(1, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL,
                    0, new float[]{6}, 0));
            g2.drawLine(getWidth() / 2, margin, getWidth() / 2, getHeight() - margin);
        }
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
