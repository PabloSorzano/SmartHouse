package ipn.com.mx.smarthome.data;

public class Cuarto_DispositivosEntity {
    int idCuarto_Disp,
    idCuarto,
    idTipo_Disp;

    public Cuarto_DispositivosEntity(int idCuarto_Disp, int idCuarto,
                                     int idTipo_Disp){
        this.idCuarto_Disp  = idCuarto_Disp;
        this.idCuarto = idCuarto;
        this.idTipo_Disp = idTipo_Disp;
    }

    public int getIdCuarto_Disp() {
        return idCuarto_Disp;
    }

    public int getIdCuarto() {
        return idCuarto;
    }

    public int getIdTipo_Disp() {
        return idTipo_Disp;
    }
}
