package ac.id.stikompoltek.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Kelas ini bertanggung jawab untuk menyediakan koneksi mysql
 * yang akan di-"lemparkan" ke kelas ayahnya
 * 
 * @author aji
 */
public class DBMySql extends DBManager {

    /**
     * Konstruktor
     * @param host
     * @param username
     * @param password
     */
    public DBMySql(String host, String username, String password) {
        Connection connection;
        try {
            // Make a connection and push it to the superclass
            connection = DriverManager.getConnection(host, username, password);
            super.setConnection(connection);
        } catch (SQLException ex) {
            Logger.getLogger(DBMySql.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
