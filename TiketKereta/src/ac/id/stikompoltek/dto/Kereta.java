package ac.id.stikompoltek.dto;

/**
 * Kelas ini digunakan sebagai model dan tidak lain adalah kelas POJO (Plain Old Java Object) biasa
 * Objeknya akan ditransaksikan disetiap frame sebagai representasi dari database
 * 
 * @author aji
 */
public class Kereta {
    
    private int idKereta;
    private String namaKereta;

    public int getIdKereta() {
        return idKereta;
    }

    public void setIdKereta(int idKereta) {
        this.idKereta = idKereta;
    }

    public String getNamaKereta() {
        return namaKereta;
    }

    public void setNamaKereta(String namaKereta) {
        this.namaKereta = namaKereta;
    }
    
}
