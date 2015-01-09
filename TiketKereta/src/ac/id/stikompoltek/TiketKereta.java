package ac.id.stikompoltek;

import ac.id.stikompoltek.utils.DBMySql;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 * Kelas utama dari aplikasi
 * Bertanggung jawab untuk meng-inisialisasi setiap objek
 * termasuk inisialisasi koneksi ke database
 * 
 * @author aji
 */
public class TiketKereta {
    
    private DBMySql dbMySql;
    private static Connection connection;
    private MainFrame mainFrame;
    
    /**
     * Konstruktor
     */
    public TiketKereta() {
        
        // Cek koneksi terlebih dahulu
        cekKoneksi();
        connection = this.dbMySql.getConnection();
        
        // Setting tema tampilan
        setLookAndFeel();
        
        this.mainFrame = new MainFrame();
        this.mainFrame.setVisible(true);
        
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        new TiketKereta();
    }
    
    public static Connection getConnection() {
        return connection;
    }
    
    /**
     * Setting look and feel
     */
    private void setLookAndFeel() {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TiketKereta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
    }
    
    /**
     * Inisialisasi database
     * 
     * @return boolean : true jika berhasil konek ke database dan false jika gagal
     */
    private void cekKoneksi() {
        String host;
        String username;
        String password;
        
        // Ambil pengaturan dari dbconf.properties
        Properties properties = new Properties();
        try {
            // Mengambil data settingan database
            properties.load(new FileInputStream("src/resources/dbconf.properties"));
            
            host = properties.getProperty("database.host");
            username = properties.getProperty("database.username");
            password = properties.getProperty("database.password");

            // Set koneksi ke dbMySql
            this.dbMySql = new DBMySql(host, username, password);
            // Apakah koneksi gagal?
            if(this.dbMySql.getConnection() == null) {
                JOptionPane.showConfirmDialog(null, "Gagal tersambung ke database, \nsilahkan periksa kembali file configurasinya di "
                        + "src/resources/dbconf.properties", "Gagal tersambung ke database", JOptionPane.OK_OPTION, JOptionPane.ERROR_MESSAGE);
                System.exit(0);
            }
            
        } catch (IOException ex) {
            JOptionPane.showConfirmDialog(null, "Maaf, gagal membuka file src/resources/dbconf.properties!!!", "Gagal membuka file configurasi database", JOptionPane.OK_OPTION, JOptionPane.ERROR_MESSAGE);
            Logger.getLogger(TiketKereta.class.getName()).log(Level.SEVERE, null, ex);
            System.exit(0);
        }
    }
    
}
