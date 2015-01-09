package ac.id.stikompoltek;

import ac.id.stikompoltek.dto.Jurusan;
import ac.id.stikompoltek.dto.Kereta;
import ac.id.stikompoltek.dto.Kota;
import ac.id.stikompoltek.dto.Transaksi;
import ac.id.stikompoltek.service.JurusanService;
import ac.id.stikompoltek.service.KeretaService;
import ac.id.stikompoltek.service.KotaService;
import ac.id.stikompoltek.service.TransaksiService;
import ac.id.stikompoltek.utils.Database;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author aji
 */
public class TransaksiFrame extends javax.swing.JInternalFrame {
    
    private Database database;

    /**
     * Creates new form Transaksi
     */
    public TransaksiFrame() {
        initComponents();
        
        resetView();
        
    }
    
    /**
     * Reset tampilan
     */
    public void resetView() {
        fillJurusanCombo();
        fillKeretaCombo();
        fillTableData();
        this.txtJmlTiket.setText("1");
        this.lblIdTransaksi.setVisible(false);
        this.lblIdTransaksi.setText("0");
        this.setTitle("Transaksi | MODE : INSERT");
    }
    
    /**
     * Isi data combo box jurusan
     */
    private void fillJurusanCombo() {
        this.cbKotaAsal.removeAllItems();
        this.cbKotaTujuan.removeAllItems();
        this.database = new KotaService();
        List<Kota> kotas = this.database.getAll();
        for(Kota kota : kotas) {
            this.cbKotaAsal.addItem(kota.getNamaKota());
            this.cbKotaTujuan.addItem(kota.getNamaKota());
        }
    }
    
    /**
     * Isi data combo box kereta
     */
    private void fillKeretaCombo() {
        this.cbKereta.removeAllItems();
        this.database = new KeretaService();
        List<Kereta> keretas = this.database.getAll();
        for(Kereta kereta : keretas) {
            this.cbKereta.addItem(kereta.getNamaKereta());
        }
    }
    
    /**
     * Isi data table
     */
    private void fillTableData() {
        this.database = new TransaksiService();
        this.tblTransaksi.setModel(new TableModelTransaksi(this.database.getAll()));
    }
    
    public class TableModelTransaksi extends AbstractTableModel {
        
        private List<Transaksi> transaksis;
        private final String[] columnName = {"Id Transaksi", "Kota Asal", "Kota Tujuan", "Harga Tiket", 
            "Jumlah Tiket", "Harga Total","Kereta"};
        
        public TableModelTransaksi(List<Transaksi> transaksis) {
            this.transaksis = transaksis;
        }

        @Override
        public int getRowCount() {
            return this.transaksis.size();
        }

        @Override
        public int getColumnCount() {
            return this.columnName.length;
        }
        
        @Override
        public String getColumnName(int index) {
            return columnName[index];
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            Transaksi transaksi = transaksis.get(rowIndex);
            switch(columnIndex) {
                case 0:
                    return transaksi.getIdTransaksi();
                case 1:
                    return transaksi.getJurusan().getKotaAsal().getNamaKota();
                case 2:
                    return transaksi.getJurusan().getKotaTujuan().getNamaKota();
                case 3:
                    return transaksi.getJurusan().getHargaTiket();
                case 4:
                    return transaksi.getJmlTiket();
                case 5:
                    return transaksi.getJmlTiket() * transaksi.getJurusan().getHargaTiket();
                case 6:
                    return transaksi.getJurusan().getKereta().getNamaKereta();
                default:
                    return "";
            }
        }
        
    }
    
    // Method untuk menghitung Jml total
    private int hitungTotal() {
        
        KotaService serviceKota = new KotaService();
        Kota kotaAsal = serviceKota.getByName(cbKotaAsal.getSelectedItem().toString());
        Kota kotaTujuan = serviceKota.getByName(cbKotaTujuan.getSelectedItem().toString());
        
        // Check for above criteria on jurusan
        this.database = new JurusanService();
        List<Jurusan> jurusans = this.database.getAll();
        for(Jurusan jurusan : jurusans) {
            if(jurusan.getKotaAsal().getIdKota() == kotaAsal.getIdKota() && 
                    jurusan.getKotaTujuan().getIdKota() == kotaTujuan.getIdKota() &&
                    jurusan.getKereta().getNamaKereta().equals(this.cbKereta.getSelectedItem().toString())) {
                int hargaPertiket = jurusan.getHargaTiket();
                int jmlTiket = Integer.parseInt(txtJmlTiket.getText());
                int hargaTotal = hargaPertiket * jmlTiket;
                return hargaTotal;
            }
        }
        
        return 0;
        
    }
    
    // Method untuk mengecek apakah jurusan yang dipilih ada atau tidak
    private boolean isJurusanExists() {
        if(hitungTotal() != 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        cbKotaAsal = new javax.swing.JComboBox();
        jLabel2 = new javax.swing.JLabel();
        cbKereta = new javax.swing.JComboBox();
        jLabel4 = new javax.swing.JLabel();
        txtJmlTiket = new javax.swing.JTextField();
        btnSimpanTransaksi = new javax.swing.JButton();
        btnResetTransaksi = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblTransaksi = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        lblHarTot = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        cbKotaTujuan = new javax.swing.JComboBox();
        lblIdTransaksi = new javax.swing.JLabel();
        btnDeleteTransaksi = new javax.swing.JButton();

        setClosable(true);
        setDefaultCloseOperation(javax.swing.WindowConstants.HIDE_ON_CLOSE);
        setTitle("Transaksi");

        jLabel1.setText("Dari");

        cbKotaAsal.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbKotaAsal.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbKotaAsalItemStateChanged(evt);
            }
        });
        cbKotaAsal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbKotaAsalActionPerformed(evt);
            }
        });
        cbKotaAsal.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                cbKotaAsalPropertyChange(evt);
            }
        });

        jLabel2.setText("Kereta");

        cbKereta.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbKereta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbKeretaActionPerformed(evt);
            }
        });

        jLabel4.setText("Total");

        txtJmlTiket.setText("1");
        txtJmlTiket.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtJmlTiketKeyReleased(evt);
            }
        });

        btnSimpanTransaksi.setText("Simpan");
        btnSimpanTransaksi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSimpanTransaksiActionPerformed(evt);
            }
        });

        btnResetTransaksi.setText("Reset");
        btnResetTransaksi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetTransaksiActionPerformed(evt);
            }
        });

        tblTransaksi.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tblTransaksi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblTransaksiMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblTransaksi);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Total (Rp.)"));

        lblHarTot.setFont(new java.awt.Font("Ubuntu", 0, 24)); // NOI18N
        lblHarTot.setForeground(new java.awt.Color(255, 0, 0));
        lblHarTot.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblHarTot.setText("000");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblHarTot, javax.swing.GroupLayout.DEFAULT_SIZE, 140, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblHarTot)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel3.setText("Ke");

        cbKotaTujuan.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        lblIdTransaksi.setText("idTransaksi");

        btnDeleteTransaksi.setText("Delete");
        btnDeleteTransaksi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteTransaksiActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 659, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2))
                        .addGap(22, 22, 22)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(cbKotaAsal, 0, 152, Short.MAX_VALUE)
                            .addComponent(cbKereta, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(44, 44, 44)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addGap(34, 34, 34)
                                .addComponent(cbKotaTujuan, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addGap(18, 18, 18)
                                .addComponent(txtJmlTiket, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(lblIdTransaksi)
                        .addGap(48, 48, 48)
                        .addComponent(btnSimpanTransaksi, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnResetTransaksi, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnDeleteTransaksi, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(109, 109, 109)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(cbKotaAsal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3)
                            .addComponent(cbKotaTujuan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cbKereta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2)
                            .addComponent(jLabel4)
                            .addComponent(txtJmlTiket, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnResetTransaksi, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSimpanTransaksi, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblIdTransaksi)
                    .addComponent(btnDeleteTransaksi, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 240, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnResetTransaksiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetTransaksiActionPerformed
        resetView();
    }//GEN-LAST:event_btnResetTransaksiActionPerformed

    private void btnSimpanTransaksiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSimpanTransaksiActionPerformed
       
        if(isJurusanExists()) {
            // Mengambil kota asal dan tujuan yang terpilih
            this.database = new KotaService();
            List<Kota> listKota = this.database.getAll();
            Kota kotaAsal = new Kota();
            Kota kotaTujuan = new Kota();
            for(Kota kota : listKota) {
                if(kota.getNamaKota().equals(this.cbKotaAsal.getSelectedItem().toString())) {
                    kotaAsal = kota;
                }
                if(kota.getNamaKota().equals(this.cbKotaTujuan.getSelectedItem().toString())) {
                    kotaTujuan = kota;
                }
            }

            // Mengambil kereta
            this.database = new KeretaService();
            List<Kereta> listKereta = this.database.getAll();
            Kereta keretaSelected = new Kereta();
            for(Kereta kereta : listKereta) {
                if(kereta.getNamaKereta().equals(this.cbKereta.getSelectedItem().toString())) {
                    keretaSelected = kereta;
                }
            }

            // Setting jurusan
            Jurusan jurusanSelected = new Jurusan();
            this.database = new JurusanService();
            List<Jurusan> listJurusan = this.database.getAll();
            for(Jurusan jurusan : listJurusan) {
                if(jurusan.getKereta().getNamaKereta().equals(keretaSelected.getNamaKereta()) &&
                        jurusan.getKotaAsal().getNamaKota().equals(kotaAsal.getNamaKota()) &&
                        jurusan.getKotaTujuan().getNamaKota().equals(kotaTujuan.getNamaKota())) {
                    jurusanSelected = jurusan;
                }
            }

            Transaksi transaksi = new Transaksi();
            transaksi.setIdTransaksi(Integer.parseInt(this.lblIdTransaksi.getText()));
            transaksi.setJmlTiket(Integer.parseInt(this.txtJmlTiket.getText()));
            transaksi.setJurusan(jurusanSelected);

            // Eksekusi simpan data
            this.database = new TransaksiService();
            this.database.save(transaksi);

            resetView();
        } else {
            JOptionPane.showMessageDialog(this, "Jurusan yang anda pilih tidak ada!\nSilahkan cek halaman Jurusan untuk melihat daftar jurusan yang ada!");
        }
        
    }//GEN-LAST:event_btnSimpanTransaksiActionPerformed

    private void txtJmlTiketKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtJmlTiketKeyReleased
        if(!this.txtJmlTiket.getText().equals("")) {
            if(hitungTotal() != 0) {
                this.lblHarTot.setText(String.valueOf(hitungTotal()));
            } else {
                JOptionPane.showMessageDialog(this, "Jurusan yang anda maksud tidak ada.", "Jurusan tidak terdaftar", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_txtJmlTiketKeyReleased

    private void cbKotaAsalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbKotaAsalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbKotaAsalActionPerformed

    private void cbKeretaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbKeretaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbKeretaActionPerformed

    private void cbKotaAsalItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbKotaAsalItemStateChanged
        
    }//GEN-LAST:event_cbKotaAsalItemStateChanged

    private void cbKotaAsalPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_cbKotaAsalPropertyChange
        
    }//GEN-LAST:event_cbKotaAsalPropertyChange

    private void tblTransaksiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblTransaksiMouseClicked
        this.database = new TransaksiService();
        Transaksi transaksi = (Transaksi) this.database.getById((int)this.tblTransaksi.getValueAt(this.tblTransaksi.getSelectedRow(), 0));
        if(transaksi != null) {
            this.lblIdTransaksi.setText(String.valueOf(transaksi.getIdTransaksi()));
            this.cbKereta.setSelectedItem(transaksi.getJurusan().getKereta().getNamaKereta());
            this.cbKotaAsal.setSelectedItem(transaksi.getJurusan().getKotaAsal().getNamaKota());
            this.cbKotaTujuan.setSelectedItem(transaksi.getJurusan().getKotaTujuan().getNamaKota());
            this.txtJmlTiket.setText(String.valueOf(transaksi.getJmlTiket()));
            this.lblHarTot.setText(String.valueOf(transaksi.getJurusan().getHargaTiket() * transaksi.getJmlTiket()));
            this.setTitle("Transaksi | MODE : EDIT / DELETE");
        }
    }//GEN-LAST:event_tblTransaksiMouseClicked

    private void btnDeleteTransaksiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteTransaksiActionPerformed
        if(!this.lblIdTransaksi.getText().equals("0")) {
            this.database = new TransaksiService();
            Transaksi transaksi = (Transaksi) this.database.getById(Integer.parseInt(this.lblIdTransaksi.getText()));
            this.database.delete(transaksi);
            resetView();
        } else {
            JOptionPane.showMessageDialog(this, "Silahkan pilih data terliebih dahulu!", "Tidak ada data terpilih", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnDeleteTransaksiActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDeleteTransaksi;
    private javax.swing.JButton btnResetTransaksi;
    private javax.swing.JButton btnSimpanTransaksi;
    private javax.swing.JComboBox cbKereta;
    private javax.swing.JComboBox cbKotaAsal;
    private javax.swing.JComboBox cbKotaTujuan;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblHarTot;
    private javax.swing.JLabel lblIdTransaksi;
    private javax.swing.JTable tblTransaksi;
    private javax.swing.JTextField txtJmlTiket;
    // End of variables declaration//GEN-END:variables
}
