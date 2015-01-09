package ac.id.stikompoltek.utils;

import java.sql.Connection;

/**
 * Kelas ini bertanggung jawab untuk menghandle berbagai jenis koneksi database
 * Seperti MySql, Postgress dan lain sebagainya
 * 
 * @author aji
 */
public abstract class DBManager {
    
    private Connection connection;
    
    /**
     * Konstruktor
     */
    public DBManager() {
        this.connection = null;
    }
    
    /**
     * Mengambil koneksi sekarang
     * 
     * @return Connection object yang sekarang aktif, null jika memang koneksinya belum di set atauh gagal koneksi
     */
    public Connection getConnection() {
        return this.connection;
    }
    
    /**
     * Set koneksi untuk transaksi database
     * 
     * @param connection 
     */
    public void setConnection(Connection connection) {
        this.connection = connection;
    }
    
}
