package ipn.com.mx.smarthome.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import ipn.com.mx.smarthome.domain.Casa;

@Dao
public interface CasaDao {
    @Query("SELECT * FROM Casa")
    List<Casa> consultarCasas();

    @Insert
    void insertarCasa(Casa casa);

    @Update
    void actualizarCasa(Casa casa);

    @Delete
    void borrarCasa(Casa casa);
}
