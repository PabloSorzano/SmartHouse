package ipn.com.mx.smarthome.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import ipn.com.mx.smarthome.domain.Cuarto;
import ipn.com.mx.smarthome.domain.Dispositivo;

@Dao
public interface DispositivoDao {
    @Query("SELECT * FROM Dispositivo WHERE idCuarto = :idCuarto")
    List<Dispositivo> consultarDispositivos(int idCuarto);

    @Insert
    void insertarDispositivo(Dispositivo dispositivo);

    @Update
    void actualizarDispositivo(Dispositivo dispositivo);

    @Delete
    void borrarDispositivo(Dispositivo dispositivo);
}
