/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ac.id.stikompoltek;

import ac.id.stikompoltek.dto.Jurusan;
import ac.id.stikompoltek.dto.Kereta;
import ac.id.stikompoltek.dto.Kota;
import ac.id.stikompoltek.service.JurusanService;
import ac.id.stikompoltek.service.KeretaService;
import ac.id.stikompoltek.service.KotaService;
import ac.id.stikompoltek.utils.Database;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author aji
 */
public class JurusanFrame extends javax.swing.JInternalFrame {
    
    private Database database;

    /**
     * Creates new form JurusanFrame
     */
    public JurusanFrame() {
        initComponents();
        
        resetView();
        
    }
    
    public void resetView(){
        fillComboBox();
        fillTableJurusan();
        this.txtHargaTiketJurusan.setText("0");
        this.lblIdJurusan.setVisible(false);
        this.lblIdJurusan.setText("0");
        this.setTitle("Jurusan | MODE : INSERT");
    }
    
    private void fillComboBox() {
        this.cbKotaAsalJurusan.removeAllItems();
        this.cbKotaTujuanJurusan.removeAllItems();
        this.cbKeretaJurusan.removeAllItems();
        
        this.database = new KotaService();
        List<Kota> listKota = this.database.getAll();
        for(Kota kota : listKota) {
            this.cbKotaAsalJurusan.addItem(kota.getNamaKota());
            this.cbKotaTujuanJurusan.addItem(kota.getNamaKota());
        }
        
        this.database = new KeretaService();
        List<Kereta> listKereta = this.database.getAll();
        for(Kereta kereta : listKereta) {
            this.cbKeretaJurusan.addItem(kereta.getNamaKereta());
        }
    }
    
    
    private void fillTableJurusan() {
        this.database = new JurusanService();
        List<Jurusan> jurusans = this.database.getAll();
        this.tblJurusan.setModel(new TableJurusanModel(jurusans));
    }
    
    public class TableJurusanModel extends AbstractTableModel {
        
        private final String[] columnName = {"Id Jurusan", "Kota Asal", "Kota Tujuan", "Harga Tiket", "Kereta"};
        private List<Jurusan> jurusans;
        
        TableJurusanModel(List<Jurusan> jurusans) {
            this.jurusans = jurusans;
        }

        @Override
        public int getRowCount() {
            return this.jurusans.size();
        }

        @Override
        public int getColumnCount() {
            return this.columnName.length;
        }
        
        @Override
        public String getColumnName(int index) {
            return this.columnName[index];
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            Jurusan jurusan = this.jurusans.get(rowIndex);
            switch(columnIndex) {
                case 0:
                    return jurusan.getIdJurusan();
                case 1:
                    return jurusan.getKotaAsal().getNamaKota();
                case 2:
                    return jurusan.getKotaTujuan().getNamaKota();
                case 3:
                    return jurusan.getHargaTiket();
                case 4:
                    return jurusan.getKereta().getNamaKereta();
                default:
                    return "";
            }
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
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        cbKotaAsalJurusan = new javax.swing.JComboBox();
        cbKotaTujuanJurusan = new javax.swing.JComboBox();
        txtHargaTiketJurusan = new javax.swing.JTextField();
        cbKeretaJurusan = new javax.swing.JComboBox();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblJurusan = new javax.swing.JTable();
        btnSimpanJurusan = new javax.swing.JButton();
        btnResetJurusan = new javax.swing.JButton();
        btnDeleteJurusan = new javax.swing.JButton();
        lblIdJurusan = new javax.swing.JLabel();

        setClosable(true);
        setDefaultCloseOperation(javax.swing.WindowConstants.HIDE_ON_CLOSE);
        setTitle("Jurusan");
        setToolTipText("");

        jLabel1.setText("Kota Asal");

        jLabel2.setText("Kota Tujuan");

        jLabel3.setText("Harga Tiket");

        jLabel4.setText("Kereta");

        cbKotaAsalJurusan.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        cbKotaTujuanJurusan.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        cbKeretaJurusan.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        tblJurusan.setModel(new javax.swing.table.DefaultTableModel(
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
        tblJurusan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblJurusanMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblJurusan);

        btnSimpanJurusan.setText("Simpan");
        btnSimpanJurusan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSimpanJurusanActionPerformed(evt);
            }
        });

        btnResetJurusan.setText("Reset");
        btnResetJurusan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetJurusanActionPerformed(evt);
            }
        });

        btnDeleteJurusan.setText("Delete");
        btnDeleteJurusan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteJurusanActionPerformed(evt);
            }
        });

        lblIdJurusan.setText("idJurusan");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1)
                                    .addComponent(jLabel3))
                                .addGap(31, 31, 31)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(cbKotaAsalJurusan, 0, 159, Short.MAX_VALUE)
                                    .addComponent(txtHargaTiketJurusan))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel4))
                                .addGap(26, 26, 26)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(cbKotaTujuanJurusan, 0, 153, Short.MAX_VALUE)
                                    .addComponent(cbKeretaJurusan, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(lblIdJurusan)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnSimpanJurusan, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnResetJurusan, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnDeleteJurusan, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(71, 71, 71))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(cbKotaAsalJurusan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbKotaTujuanJurusan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtHargaTiketJurusan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(cbKeretaJurusan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblIdJurusan)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnDeleteJurusan, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 64, Short.MAX_VALUE)
                            .addComponent(btnSimpanJurusan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnResetJurusan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(13, 13, 13)))
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnResetJurusanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetJurusanActionPerformed
        resetView();
    }//GEN-LAST:event_btnResetJurusanActionPerformed

    private void btnDeleteJurusanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteJurusanActionPerformed
        if(!this.lblIdJurusan.getText().equals("0")) {
            this.database = new JurusanService();
            Jurusan jurusan = (Jurusan)this.database.getById(Integer.parseInt(this.lblIdJurusan.getText()));
            this.database.delete(jurusan);
            resetView();
        } else {
            JOptionPane.showMessageDialog(this, "Silahkan pilih data terlebih dauhulu!", "Tidak ada data terpilih", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnDeleteJurusanActionPerformed

    private void btnSimpanJurusanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSimpanJurusanActionPerformed
        // Mengambil data kota yang dipilih dari database
        Kota kotaAsal = new Kota();
        Kota kotaTujuan = new Kota();
        this.database = new KotaService();
        List<Kota> listKota = this.database.getAll();
        for(Kota kota : listKota) {
            if(kota.getNamaKota().equals(this.cbKotaAsalJurusan.getSelectedItem().toString())) {
                kotaAsal = kota;
            }
            if(kota.getNamaKota().equals(this.cbKotaTujuanJurusan.getSelectedItem().toString())) {
                kotaTujuan = kota;
            }
        }
        
        // Memilih data kereta yang dipilih, dari database
        Kereta keretaSelected = new Kereta();
        this.database = new KeretaService();
        List<Kereta> listKereta = this.database.getAll();
        for(Kereta kereta : listKereta) {
            if(kereta.getNamaKereta().equals(this.cbKeretaJurusan.getSelectedItem().toString())) {
                keretaSelected = kereta;
            }
        }
        
        Jurusan jurusan = new Jurusan();
        jurusan.setIdJurusan(Integer.parseInt(this.lblIdJurusan.getText()));
        jurusan.setHargaTiket(Integer.parseInt(this.txtHargaTiketJurusan.getText()));
        jurusan.setKereta(keretaSelected);
        jurusan.setKotaAsal(kotaAsal);
        jurusan.setKotaTujuan(kotaTujuan);
        
        this.database = new JurusanService();
        this.database.save(jurusan);
        resetView();
        
    }//GEN-LAST:event_btnSimpanJurusanActionPerformed

    private void tblJurusanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblJurusanMouseClicked
        this.database = new JurusanService();
        Jurusan jurusan = (Jurusan) this.database.getById((int)this.tblJurusan.getValueAt(this.tblJurusan.getSelectedRow(), 0));
        this.cbKeretaJurusan.setSelectedItem(jurusan.getKereta().getNamaKereta());
        this.cbKotaAsalJurusan.setSelectedItem(jurusan.getKotaAsal().getNamaKota());
        this.cbKotaTujuanJurusan.setSelectedItem(jurusan.getKotaTujuan().getNamaKota());
        this.lblIdJurusan.setText(String.valueOf(jurusan.getIdJurusan()));
        this.txtHargaTiketJurusan.setText(String.valueOf(jurusan.getHargaTiket()));
        this.setTitle("Jurusan | MODE : EDIT / DELETE");
    }//GEN-LAST:event_tblJurusanMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDeleteJurusan;
    private javax.swing.JButton btnResetJurusan;
    private javax.swing.JButton btnSimpanJurusan;
    private javax.swing.JComboBox cbKeretaJurusan;
    private javax.swing.JComboBox cbKotaAsalJurusan;
    private javax.swing.JComboBox cbKotaTujuanJurusan;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblIdJurusan;
    private javax.swing.JTable tblJurusan;
    private javax.swing.JTextField txtHargaTiketJurusan;
    // End of variables declaration//GEN-END:variables
}