package ac.id.stikompoltek.service;

import ac.id.stikompoltek.TiketKereta;
import ac.id.stikompoltek.dto.Jurusan;
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
public class JurusanService implements Database<Jurusan> {
    
    private Connection koneksi;
    
    private static final String insertSql = "INSERT INTO jurusan (id_kota_asal, id_kota_tujuan, harga_tiket, id_kereta) VALUES(?, ?, ?, ?)";
    private static final String updateSql = "UPDATE jurusan SET id_kota_asal=?, id_kota_tujuan=?, harga_tiket=?, id_kereta=? WHERE id_jurusan=?";
    private static final String deleteSql = "DELETE FROM jurusan WHERE id_jurusan=?";
    private static final String getByIdSql = "SELECT * FROM jurusan WHERE id_jurusan=?";
    private static final String getAllSql = "SELECT * FROM jurusan";
    
    private PreparedStatement insertStatement;
    private PreparedStatement updateStatement;
    private PreparedStatement deleteStatement;
    private PreparedStatement getByIdStatement;
    private PreparedStatement getAllStatement;
    
    public JurusanService() {
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
    public Jurusan save(Jurusan jurusan) {
        try {
            // Jika id jurusan = 0, maka insert, jika tidak maka update
            if(jurusan.getIdJurusan() == 0) {

                this.insertStatement.setInt(1, jurusan.getKotaAsal().getIdKota());
                this.insertStatement.setInt(2, jurusan.getKotaTujuan().getIdKota());
                this.insertStatement.setInt(3, jurusan.getHargaTiket());
                this.insertStatement.setInt(4, jurusan.getKereta().getIdKereta());
                jurusan.setIdJurusan(this.insertStatement.executeUpdate());
                return jurusan;

            } else {
                
                this.updateStatement.setInt(1, jurusan.getKotaAsal().getIdKota());
                this.updateStatement.setInt(2, jurusan.getKotaTujuan().getIdKota());
                this.updateStatement.setInt(3, jurusan.getHargaTiket());
                this.updateStatement.setInt(4, jurusan.getKereta().getIdKereta());
                this.updateStatement.setInt(5, jurusan.getIdJurusan());
                this.updateStatement.executeUpdate();
                return jurusan;

            }
        } catch (SQLException ex) {
            Logger.getLogger(JurusanService.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    @Override
    public Jurusan delete(Jurusan jurusan) {
        try{
            this.deleteStatement.setInt(1, jurusan.getIdJurusan());
            this.deleteStatement.executeUpdate();
            return jurusan;
        } catch(SQLException e) {
            Logger.getLogger(JurusanService.class.getName()).log(Level.SEVERE, null, e);
            return null;
        }
    }

    @Override
    public Jurusan getById(int idJurusan) {
        try {
            this.getByIdStatement.setInt(1, idJurusan);
            ResultSet rs = this.getByIdStatement.executeQuery();
            rs.first();
            Jurusan jurusan = new Jurusan();
            jurusan.setIdJurusan(rs.getInt("id_jurusan"));
            KotaService kota = new KotaService();
            jurusan.setKotaAsal(kota.getById(rs.getInt("id_kota_asal")));
            jurusan.setKotaTujuan(kota.getById(rs.getInt("id_kota_tujuan")));
            KeretaService kereta = new KeretaService();
            jurusan.setKereta(kereta.getById(rs.getInt("id_kereta")));
            jurusan.setHargaTiket(rs.getInt("harga_tiket"));
            return jurusan;
            
        } catch (SQLException e) {
            Logger.getLogger(JurusanService.class.getName()).log(Level.SEVERE, null, e);
            return null;
        }
    }

    @Override
    public List<Jurusan> getAll() {
        List<Jurusan> jurusans = new ArrayList<Jurusan>();
        try {
            ResultSet rs = this.getAllStatement.executeQuery();
            while(rs.next()) {
                Jurusan jurusan = new Jurusan();
                jurusan.setIdJurusan(rs.getInt("id_jurusan"));
                KotaService kota = new KotaService();
                jurusan.setKotaAsal(kota.getById(rs.getInt("id_kota_asal")));
                jurusan.setKotaTujuan(kota.getById(rs.getInt("id_kota_tujuan")));
                KeretaService kereta = new KeretaService();
                jurusan.setKereta(kereta.getById(rs.getInt("id_kereta")));
                jurusan.setHargaTiket(rs.getInt("harga_tiket"));
                jurusans.add(jurusan);
            }
            return jurusans;
        } catch (SQLException ex) {
            Logger.getLogger(JurusanService.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
}
