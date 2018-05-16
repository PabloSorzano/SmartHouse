package ipn.com.mx.smarthome.dao;

import android.arch.persistence.room.*;

import java.util.List;

import ipn.com.mx.smarthome.domain.*;

@Dao
public interface UsuarioDao {
    @Query("SELECT * FROM Usuario WHERE idUsuario = :idUsuario")
    List<Usuario> consultarUsuario(int idUsuario);

    @Insert
    void insertarUsuario(Usuario usuario);

    @Update
    void actualizarUsuario(Usuario usuario);

    @Delete
    void borrarUsuario(Usuario usuario);
}
