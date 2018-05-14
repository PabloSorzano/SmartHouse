package ipn.com.mx.smarthome.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import ipn.com.mx.smarthome.domain.Cuarto;

@Dao
public interface CuartoDao {
    @Query("SELECT * FROM Cuarto WHERE idCasa = :idCasa")
    List<Cuarto> consultarCuarto(int idCasa);

    @Insert
    void insertarCuarto(Cuarto cuarto);

    @Update
    void actualizarCuarto(Cuarto cuarto);

    @Delete
    void borrarCuarto(Cuarto cuarto);
}
