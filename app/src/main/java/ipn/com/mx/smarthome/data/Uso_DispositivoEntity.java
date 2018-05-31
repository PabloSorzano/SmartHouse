package ipn.com.mx.smarthome.data;

import java.util.UUID;

public class Uso_DispositivoEntity {
    int idUso_Disp,
    idCuarto_Disp;
    String
    status,
    inicio_uso,
    fin_uso;

    public Uso_DispositivoEntity(int idCuarto_Disp,
                                 String status,
                                 String inicio_uso,
                                 String fin_uso){
        this.idUso_Disp = Integer.parseInt(UUID.randomUUID().toString());
        this.idCuarto_Disp = idCuarto_Disp;
        this.status = status;
        this.inicio_uso = inicio_uso;
        this.fin_uso = fin_uso;
    }

    public int getIdUso_Disp() {
        return idUso_Disp;
    }

    public int getIdCuarto_Disp() {
        return idCuarto_Disp;
    }

    public String getStatus() {
        return status;
    }

    public String getInicio_uso() {
        return inicio_uso;
    }

    public String getFin_uso() {
        return fin_uso;
    }
}
