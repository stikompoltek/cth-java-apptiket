package ac.id.stikompoltek;

/**
 *
 * @author aji
 */
public class MainFrame extends javax.swing.JFrame {
    
    private TransaksiFrame transaksiFrame;
    private KeretaFrame keretaFrame;
    private JurusanFrame jurusanFrame;

    /**
     * Creates new form MainFrame
     */
    public MainFrame() {
        initComponents();
        
        // Inisialisasi komponen
        this.transaksiFrame = new TransaksiFrame();
        this.keretaFrame = new KeretaFrame();
        this.jurusanFrame = new JurusanFrame();
        
        this.desktopPane.add(this.transaksiFrame);
        this.desktopPane.add(this.keretaFrame);
        this.desktopPane.add(this.jurusanFrame);
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        desktopPane = new javax.swing.JDesktopPane();
        jToolBar1 = new javax.swing.JToolBar();
        btnToolTransaksi = new javax.swing.JButton();
        btnTambahKeretaTool = new javax.swing.JButton();
        btnTambahJurusanTool = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Aplikasi Tiket");
        setPreferredSize(new java.awt.Dimension(800, 600));

        jToolBar1.setFloatable(false);
        jToolBar1.setRollover(true);

        btnToolTransaksi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/cash.png"))); // NOI18N
        btnToolTransaksi.setText("Transaksi");
        btnToolTransaksi.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnToolTransaksi.setFocusable(false);
        btnToolTransaksi.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnToolTransaksi.setMargin(new java.awt.Insets(0, 10, 0, 10));
        btnToolTransaksi.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnToolTransaksi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnToolTransaksiActionPerformed(evt);
            }
        });
        jToolBar1.add(btnToolTransaksi);

        btnTambahKeretaTool.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Universal.png"))); // NOI18N
        btnTambahKeretaTool.setText("Kereta");
        btnTambahKeretaTool.setFocusable(false);
        btnTambahKeretaTool.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnTambahKeretaTool.setMargin(new java.awt.Insets(0, 10, 0, 10));
        btnTambahKeretaTool.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnTambahKeretaTool.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTambahKeretaToolActionPerformed(evt);
            }
        });
        jToolBar1.add(btnTambahKeretaTool);

        btnTambahJurusanTool.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/new archive.png"))); // NOI18N
        btnTambahJurusanTool.setText("Jurusan");
        btnTambahJurusanTool.setFocusable(false);
        btnTambahJurusanTool.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnTambahJurusanTool.setMargin(new java.awt.Insets(0, 10, 0, 10));
        btnTambahJurusanTool.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnTambahJurusanTool.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTambahJurusanToolActionPerformed(evt);
            }
        });
        jToolBar1.add(btnTambahJurusanTool);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(desktopPane, javax.swing.GroupLayout.Alignment.TRAILING)
            .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, 846, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(desktopPane, javax.swing.GroupLayout.DEFAULT_SIZE, 455, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnToolTransaksiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnToolTransaksiActionPerformed
        this.transaksiFrame.setVisible(true);
        this.transaksiFrame.resetView();
    }//GEN-LAST:event_btnToolTransaksiActionPerformed

    private void btnTambahKeretaToolActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTambahKeretaToolActionPerformed
        this.keretaFrame.setVisible(true);
        this.keretaFrame.resetView();
    }//GEN-LAST:event_btnTambahKeretaToolActionPerformed

    private void btnTambahJurusanToolActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTambahJurusanToolActionPerformed
        this.jurusanFrame.setVisible(true);
        this.jurusanFrame.resetView();
    }//GEN-LAST:event_btnTambahJurusanToolActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnTambahJurusanTool;
    private javax.swing.JButton btnTambahKeretaTool;
    private javax.swing.JButton btnToolTransaksi;
    private javax.swing.JDesktopPane desktopPane;
    private javax.swing.JToolBar jToolBar1;
    // End of variables declaration//GEN-END:variables
}
