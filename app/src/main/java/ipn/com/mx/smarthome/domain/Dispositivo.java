package ipn.com.mx.smarthome.domain;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class Dispositivo {

    @PrimaryKey(autoGenerate = true)
    private int idDispositivo;

    @ColumnInfo(name = "tipo")
    private int tipo;
    ;

    @ColumnInfo(name = "nombre")
    private String nombre;
    ;



    @ColumnInfo(name = "descripcion")
    private String descripcion ;
    ;


    @ColumnInfo(name = "idCuarto")
    private int idCuarto;
    ;

    @ColumnInfo(name = "imagen")
    private int imagen;

    @ColumnInfo(name = "estado")
    private int estado;

    public int getIdDispositivo() {
        return idDispositivo;
    }

    public void setIdDispositivo(int idDispositivo) {
        this.idDispositivo = idDispositivo;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public int getImagen() {
        return imagen;
    }

    public void setImagen(int imagen) {
        this.imagen = imagen;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public int getIdCuarto() {
        return idCuarto;
    }

    public void setIdCuarto(int idCuarto) {
        this.idCuarto = idCuarto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }


}

