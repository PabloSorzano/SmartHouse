package ipn.com.mx.smarthome.data;

public class CasaEntity {
    int idCasa,
            idUsr;
    String xCoorde,
            xEstado,
            xMuni,
            xCodigoP,//codigoPostal 5 caracteres
            xCol,
            xCalle,
            xNumInt;//numeroInterior 10 caracteres
    public CasaEntity(int idCasa, int idUsr,
                      String xCoorde,
                      String xEstado,
                      String xMuni,
                      String xCodigoP,
                      String xCol,
                      String xCalle,
                      String xNumInt){
        this.idCasa = idCasa;
        this.idUsr = idUsr;
        this.xCoorde = xCoorde;
        this.xEstado =  xEstado;
        this.xMuni  = xMuni;
        this.xCodigoP = xCodigoP;
        this.xCol = xCol;
        this.xCalle = xCalle;
        this.xNumInt = xNumInt;
    }

    public int getIdCasa() {
        return idCasa;
    }

    public int getIdUsr() {
        return idUsr;
    }

    public String getxCoorde() {
        return xCoorde;
    }

    public String getxEstado() {
        return xEstado;
    }

    public String getxMuni() {
        return xMuni;
    }

    public String getxCodigoP() {
        return xCodigoP;
    }

    public String getxCol() {
        return xCol;
    }

    public String getxCalle() {
        return xCalle;
    }

    public String getxNumInt() {
        return xNumInt;
    }
}
