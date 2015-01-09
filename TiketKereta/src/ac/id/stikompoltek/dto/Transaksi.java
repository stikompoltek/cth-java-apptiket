package ac.id.stikompoltek.dto;

/**
 *
 * @author aji
 */
public class Transaksi {
    
    private int idTransaksi;
    private Jurusan jurusan;
    private int jmlTiket;

    public int getIdTransaksi() {
        return idTransaksi;
    }

    public void setIdTransaksi(int idTransaksi) {
        this.idTransaksi = idTransaksi;
    }

    public Jurusan getJurusan() {
        return jurusan;
    }

    public void setJurusan(Jurusan jurusan) {
        this.jurusan = jurusan;
    }

    public int getJmlTiket() {
        return jmlTiket;
    }

    public void setJmlTiket(int jmlTiket) {
        this.jmlTiket = jmlTiket;
    }
    
}
