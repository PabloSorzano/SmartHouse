package ipn.com.mx.smarthome.bd;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import ipn.com.mx.smarthome.dao.CasaDao;
import ipn.com.mx.smarthome.dao.CuartoDao;
import ipn.com.mx.smarthome.dao.DispositivoDao;
import ipn.com.mx.smarthome.domain.Casa;
import ipn.com.mx.smarthome.domain.Cuarto;
import ipn.com.mx.smarthome.domain.Dispositivo;


@Database(entities = {Casa.class,Cuarto.class, Dispositivo.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract CasaDao poCasaDao();
    public abstract CuartoDao poCuartoDao();
    public abstract DispositivoDao poDispositivoDao();
}


