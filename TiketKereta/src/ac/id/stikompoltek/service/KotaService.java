package ac.id.stikompoltek.service;

import ac.id.stikompoltek.TiketKereta;
import ac.id.stikompoltek.dto.Kota;
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
 *
 * @author aji
 */
public class KotaService implements Database<Kota> {
    
    private Connection koneksi;
    
    private static final String insertSql = "INSERT INTO kota (nama_kota) VALUES(?)";
    private static final String updateSql = "UPDATE kota SET nama_kota=? WHERE id_kota=?";
    private static final String deleteSql = "DELETE FROM kota WHERE id_kota=?";
    private static final String getByIdSql = "SELECT * FROM kota WHERE id_kota=?";
    private static final String getAllSql = "SELECT * FROM kota";
    private static final String getByNameSql = "SELECT * FROM kota WHERE nama_kota=?";
    
    private PreparedStatement insertStatement;
    private PreparedStatement updateStatement;
    private PreparedStatement deleteStatement;
    private PreparedStatement getByIdStatement;
    private PreparedStatement getAllStatement;
    private PreparedStatement getByNameStatement;
    
    public KotaService() {
        this.koneksi = TiketKereta.getConnection();
        try {
            this.insertStatement = this.koneksi.prepareStatement(insertSql, PreparedStatement.RETURN_GENERATED_KEYS);
            this.updateStatement = this.koneksi.prepareStatement(updateSql);
            this.deleteStatement = this.koneksi.prepareStatement(deleteSql);
            this.getAllStatement = this.koneksi.prepareStatement(getAllSql);
            this.getByIdStatement = this.koneksi.prepareStatement(getByIdSql);
            this.getByNameStatement = this.koneksi.prepareStatement(getByNameSql);
        } catch (SQLException ex) {
            Logger.getLogger(TransaksiService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public Kota save(Kota kota) {
        try {
            if(kota.getIdKota() == 0) {
                this.insertStatement.setString(1, kota.getNamaKota());
                kota.setIdKota(this.insertStatement.executeUpdate());
            } else {
                this.updateStatement.setString(1, kota.getNamaKota());
                this.updateStatement.setInt(2, kota.getIdKota());
            }
            return kota;
        } catch (SQLException ex) {
            Logger.getLogger(KotaService.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    @Override
    public Kota delete(Kota kota) {
        try {
            this.deleteStatement.setInt(1, kota.getIdKota());
            this.deleteStatement.executeUpdate();
            return kota;
        } catch (SQLException ex) {
            Logger.getLogger(KotaService.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    @Override
    public Kota getById(int idKota) {
        try {
            this.getByIdStatement.setInt(1, idKota);
            ResultSet rs = this.getByIdStatement.executeQuery();
            rs.first();
            Kota kota = new Kota();
            kota.setIdKota(rs.getInt("id_kota"));
            kota.setNamaKota(rs.getString("nama_kota"));
            return kota;
        } catch (SQLException ex) {
            Logger.getLogger(KotaService.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    @Override
    public List<Kota> getAll() {
        try {
            List<Kota> kotas = new ArrayList<Kota>();
            ResultSet rs = this.getAllStatement.executeQuery();
            while(rs.next()) {
                Kota kota = new Kota();
                kota.setIdKota(rs.getInt("id_kota"));
                kota.setNamaKota(rs.getString("nama_kota"));
                kotas.add(kota);
            }
            return kotas;
        } catch (SQLException ex) {
            Logger.getLogger(KotaService.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }   
    }
    
    /**
     * Mengambil data kota berdasarkan nama
     * @param name
     * @return 
     */
    public Kota getByName(String name) {
        try {
            this.getByNameStatement.setString(1, name);
            ResultSet rs = this.getByNameStatement.executeQuery();
            if(rs.first()) {
                Kota kota = new Kota();
                kota.setIdKota(rs.getInt("id_kota"));
                kota.setNamaKota(rs.getString("nama_kota"));
                return kota;
            } else {
                return null;
            }
        } catch (SQLException ex) {
            Logger.getLogger(KotaService.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
}
