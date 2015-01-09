package ac.id.stikompoltek.service;

import ac.id.stikompoltek.TiketKereta;
import ac.id.stikompoltek.dto.Transaksi;
import ac.id.stikompoltek.utils.Database;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Kelas ini bertanggung jawab untuk melakukan transaksi ke database
 * Menginputkan object DTO / model kedalam database dan sebaliknya
 * Membaca data dari database dan disimpan kedalam objek DTO / model
 * 
 * @author aji
 */
public class TransaksiService implements Database<Transaksi> {
    
    private Connection koneksi;
    
    private static final String insertSql = "INSERT INTO transaksi (id_jurusan, jml_tiket) VALUES(?, ?)";
    private static final String updateSql = "UPDATE transaksi SET id_jurusan=?, jml_tiket=? WHERE id_transaksi=?";
    private static final String deleteSql = "DELETE FROM transaksi WHERE id_transaksi=?";
    private static final String getByIdSql = "SELECT * FROM transaksi WHERE id_transaksi=?";
    private static final String getAllSql = "SELECT * FROM transaksi";
    
    private PreparedStatement insertStatement;
    private PreparedStatement updateStatement;
    private PreparedStatement deleteStatement;
    private PreparedStatement getByIdStatement;
    private PreparedStatement getAllStatement;
    
    public TransaksiService() {
        this.koneksi = TiketKereta.getConnection();
        try {
            this.insertStatement = this.koneksi.prepareStatement(insertSql, PreparedStatement.RETURN_GENERATED_KEYS);
            this.updateStatement = this.koneksi.prepareStatement(updateSql);
            this.deleteStatement = this.koneksi.prepareStatement(deleteSql);
            this.getAllStatement = this.koneksi.prepareStatement(getAllSql);
            this.getByIdStatement = this.koneksi.prepareStatement(getByIdSql);
        } catch (SQLException ex) {
            Logger.getLogger(TransaksiService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public Transaksi save(Transaksi transaksi) {
        try {
            // Jika id transaksi 0, berarti insert, jika tidak maka update
            if(transaksi.getIdTransaksi() == 0) {
                this.insertStatement.setInt(1, transaksi.getJurusan().getIdJurusan());
                this.insertStatement.setInt(2, transaksi.getJmlTiket());
                transaksi.setIdTransaksi(this.insertStatement.executeUpdate());
                return transaksi;
            } else {
                this.updateStatement.setInt(1, transaksi.getJurusan().getIdJurusan());
                this.updateStatement.setInt(2, transaksi.getJmlTiket());
                this.updateStatement.setInt(3, transaksi.getIdTransaksi());
                this.updateStatement.executeUpdate();
                return transaksi;
            }
        } catch (SQLException ex) {
            Logger.getLogger(TransaksiService.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    @Override
    public Transaksi delete(Transaksi transaksi) {
        try {
            this.deleteStatement.setInt(1, transaksi.getIdTransaksi());
            this.deleteStatement.executeUpdate();
            return transaksi;
        } catch (SQLException ex) {
            Logger.getLogger(TransaksiService.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    @Override
    public Transaksi getById(int idTransaksi) {
        try {
            this.getByIdStatement.setInt(1, idTransaksi);
            ResultSet rs = this.getByIdStatement.executeQuery();
            rs.first();
            Transaksi transaksi = new Transaksi();
            transaksi.setIdTransaksi(rs.getInt("id_transaksi"));
            JurusanService jurusanService = new JurusanService(); // Mengambil jurusan yang terpilih
            transaksi.setJurusan(jurusanService.getById(rs.getInt("id_jurusan")));
            transaksi.setJmlTiket(rs.getInt("jml_tiket"));
            return transaksi;
        } catch (SQLException ex) {
            Logger.getLogger(TransaksiService.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    @Override
    public List<Transaksi> getAll() {
        try {
            List<Transaksi> transaksis = new ArrayList<Transaksi>();
            ResultSet rs = this.getAllStatement.executeQuery();
            JurusanService jurusan = new JurusanService();
            System.out.println(rs.getRow());
            while(rs.next()) {
                Transaksi transaksi = new Transaksi();
                transaksi.setIdTransaksi(rs.getInt("id_transaksi"));
                transaksi.setJurusan(jurusan.getById(rs.getInt("id_jurusan")));
                transaksi.setJmlTiket(rs.getInt("jml_tiket"));
                transaksis.add(transaksi);
            }
            return transaksis;
        } catch (SQLException ex) {
            Logger.getLogger(TransaksiService.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
}
