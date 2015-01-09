package ac.id.stikompoltek.utils;

import java.util.List;

/**
 * Interface ini ditujukan untuk membuat perintah simpan ke database menjadi lebih mudah dibaca
 * Interface ini berusaha memenuhi konsep 'coding to interface' dalam java
 * Dan interface ini HARUS di implementasikan oleh kelas-kelas service
 * 
 * @author aji
 * @param <T>
 */
public interface Database<T> {
    
    public T save(T domain);
    public T delete(T domain);
    public T getById(int idDomain);
    public List<T> getAll();
    
}
