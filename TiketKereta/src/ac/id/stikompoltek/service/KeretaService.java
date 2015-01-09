package ac.id.stikompoltek.service;

import ac.id.stikompoltek.TiketKereta;
import ac.id.stikompoltek.dto.Kereta;
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
public class KeretaService implements Database<Kereta> {
    
    private Connection koneksi;
    
    private static final String insertSql = "INSERT INTO kererta (nama_kereta) VALUES(?)";
    private static final String updateSql = "UPDATE kererta SET nama_kereta=? WHERE id_kererta=?";
    private static final String deleteSql = "DELETE FROM kererta WHERE id_kererta=?";
    private static final String getByIdSql = "SELECT * FROM kererta WHERE id_kererta=?";
    private static final String getAllSql = "SELECT * FROM kererta";
    
    private PreparedStatement insertStatement;
    private PreparedStatement updateStatement;
    private PreparedStatement deleteStatement;
    private PreparedStatement getByIdStatement;
    private PreparedStatement getAllStatement;
    
    public KeretaService() {
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
    public Kereta save(Kereta kereta) {
        try {
            if(kereta.getIdKereta() == 0) {
                this.insertStatement.setString(1, kereta.getNamaKereta());
                kereta.setIdKereta(this.insertStatement.executeUpdate());
            } else {
                this.updateStatement.setString(1, kereta.getNamaKereta());
                this.updateStatement.setInt(2, kereta.getIdKereta());
                this.updateStatement.executeUpdate();
            }
            return kereta;
        } catch (SQLException ex) {
            Logger.getLogger(KeretaService.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    @Override
    public Kereta delete(Kereta kereta) {
        try {
            this.deleteStatement.setInt(1, kereta.getIdKereta());
            this.deleteStatement.executeUpdate();
            return kereta;
        } catch (SQLException ex) {
            Logger.getLogger(KeretaService.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    @Override
    public Kereta getById(int idkereta) {
        Kereta kereta = new Kereta();
        try {
            this.getByIdStatement.setInt(1, idkereta);
            ResultSet rs = this.getByIdStatement.executeQuery();
            rs.first();
            kereta.setIdKereta(rs.getInt("id_kererta"));
            kereta.setNamaKereta(rs.getString("nama_kereta"));
            return kereta;
        } catch (SQLException ex) {
            Logger.getLogger(KeretaService.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    @Override
    public List<Kereta> getAll() {
        try {
            List<Kereta> keretas = new ArrayList<Kereta>();
            ResultSet rs = this.getAllStatement.executeQuery();
            while(rs.next()) {
                Kereta kereta = new Kereta();
                kereta.setIdKereta(rs.getInt("id_kererta"));
                kereta.setNamaKereta(rs.getString("nama_kereta"));
                keretas.add(kereta);
            }
            return keretas;
        } catch (SQLException ex) {
            Logger.getLogger(KeretaService.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
}
