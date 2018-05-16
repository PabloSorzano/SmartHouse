package ipn.com.mx.smarthome.domain;

import android.arch.persistence.room.*;

@Entity
public class Usuario {
    @PrimaryKey (autoGenerate = true)
    private int idUsuario;

    @ColumnInfo (name = "nombre")
    private String nombre;

    @ColumnInfo (name = "apellidoPaterno")
    private String apellidoPaterno;

    @ColumnInfo (name = "apellidoMaterno")
    private String apellidoMaterno;

    @ColumnInfo (name = "celular")
    private String celular;

    @ColumnInfo (name = "correoElectronico")
    private String correoElectronico;

    @ColumnInfo (name = "contraseña")
    private String contraseña;

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }

    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }
}
