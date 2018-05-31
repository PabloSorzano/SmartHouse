package ipn.com.mx.smarthome.data;

public class CuartoEntity {
    int idCuarto,
            idCasa;
    String nombreCuarto,
            numeroPiso,
            observacion;

    public CuartoEntity(int idCuarto, int idCasa,
                        String nombreCuarto,
                        String numeroPiso,
                        String observacion){
        this.idCuarto = idCuarto;
        this.idCasa = idCasa;
        this.nombreCuarto = nombreCuarto;
        this.numeroPiso = numeroPiso;
        this.observacion = observacion;
    }

    public int getIdCuarto() {
        return idCuarto;
    }

    public int getIdCasa() {
        return idCasa;
    }

    public String getNombreCuarto() {
        return nombreCuarto;
    }

    public String getNumeroPiso() {
        return numeroPiso;
    }

    public String getObservacion() {
        return observacion;
    }
}
