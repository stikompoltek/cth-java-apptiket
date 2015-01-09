package ac.id.stikompoltek.dto;

/**
 * Kelas ini digunakan sebagai model dan tidak lain adalah kelas POJO (Plain Old Java Object) biasa
 * Objeknya akan ditransaksikan disetiap frame sebagai representasi dari database
 * 
 * @author aji
 */
public class Jurusan {
    
    private int idJurusan;
    private Kota kotaAsal;
    private Kota kotaTujuan;
    private int hargaTiket;
    private Kereta kereta;

    public int getIdJurusan() {
        return idJurusan;
    }

    public void setIdJurusan(int idJurusan) {
        this.idJurusan = idJurusan;
    }

    public Kota getKotaAsal() {
        return kotaAsal;
    }

    public void setKotaAsal(Kota kotaAsal) {
        this.kotaAsal = kotaAsal;
    }

    public Kota getKotaTujuan() {
        return kotaTujuan;
    }

    public void setKotaTujuan(Kota kotaTujuan) {
        this.kotaTujuan = kotaTujuan;
    }

    public int getHargaTiket() {
        return hargaTiket;
    }

    public void setHargaTiket(int hargaTiket) {
        this.hargaTiket = hargaTiket;
    }
    
    public Kereta getKereta() {
        return kereta;
    }

    public void setKereta(Kereta kereta) {
        this.kereta = kereta;
    }
    
}
