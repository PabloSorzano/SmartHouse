package ipn.com.mx.smarthome.bd;

import android.arch.persistence.room.Database;
import android.arch.persistence.*;
import android.arch.persistence.room.*;

import ipn.com.mx.smarthome.dao.CasaDao;
import ipn.com.mx.smarthome.dao.*;
import ipn.com.mx.smarthome.dao.DispositivoDao;
import ipn.com.mx.smarthome.dao.UsuarioDao;
import ipn.com.mx.smarthome.domain.Casa;
import ipn.com.mx.smarthome.domain.Cuarto;
import ipn.com.mx.smarthome.domain.*;


@Database(entities = {Casa.class,Cuarto.class, Dispositivo.class, Usuario.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract CasaDao poCasaDao();
    public abstract CuartoDao poCuartoDao();
    public abstract DispositivoDao poDispositivoDao();
    public abstract UsuarioDao poUsuarioDao();

}


