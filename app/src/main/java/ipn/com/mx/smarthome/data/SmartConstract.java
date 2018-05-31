package ipn.com.mx.smarthome.data;

import android.provider.BaseColumns;

public class SmartConstract {

    public static abstract class UsrEntry implements BaseColumns{
        public static final String TABLE_NAME = "USUARIO";

        public static final String ID_USUARIO = "ID_USUARIO";
        public static final String NAME_USUARIO = "NAME_USUARIO";
        public static final String APELLIDO_PATERNO= "APELLIDO_PATERNO";
        public static final String APELLIDO_MATERNO = "APELLIDO_MATERNO";
        public static final String TELEFONO_MOVIL = "TELEFONO_MOVIL";
        public static final String EMAIL = "EMAIL";
        public static final String CONTRASEÑA = "CONTRASEÑA";

    }

    public static abstract class CasaEntry implements BaseColumns{
        public static final String TABLE_NAME = "CASA";

        public static final String ID_CASA = "ID_CASA";
        public static final String ID_USUARIO = "ID_USUARIO";
        public static final String COORDENADAS = "COORDENADAS";
        public static final String ESTADO = "ESTADO";
        public static final String MUNICIPIO = "MUNICIPIO";
        public static final String CODIGO_POSTAL = "CODIGO_POSTAL";
        public static final String COLONIA = "COLONIA";
        public static final String CALLE = "CALLE";
        public static final String NUMERO_INTERIOR = "NUMERO_INTERIOR";
    }

    public static abstract class CuartoEntry implements BaseColumns{
        public static final String TABLE_NAME = "CUARTO";

        public static final String ID_CUARTO = "ID_CUARTO";
        public static final String ID_CASA = "ID_CASA";
        public static final String NOMBRE_CUARTO = "NOMBRE_CUARTO";
        public static final String NUMERO_PISO = "NUMERO_PISO";
        public static final String OBSERVACION = "OBSERVACION";

    }

    public static abstract class UsoDispEntry implements BaseColumns{
        public static final String TABLE_NAME = "USO_DISPOSITIVOS";

        public static final String ID_USO_DISP = "ID_USO_DISP";
        public static final String ID_CUARTO_DISP = "ID_CUARTO";
        public static final String STATUS = "STATUS";
        public static final String INICIO_USO = "INICIO_USO";
        public static final String FIN_USO = "FIN_USO";

    }

    public static abstract class CuartoDispEntry implements BaseColumns{
        public static final String TABLE_NAME = "CUARTO_DISPOSITIVOS";

        public static final String ID_CUARTO_DISP = "ID_CUARTO_DISP";
        public static final String ID_CUARTO = "ID_CUARTO";
        public static final String ID_TIPO_DISP = "ID_TIPO_DISP";

    }

    public static abstract class CatDispEntry implements BaseColumns{
        public static final String TABLE_NAME = "CATALOGO_DISPOSITIVOS";

        public static final String ID_TIPO_DISP = "ID_TIPO_DISP";
        public static final String NOMBRE_DISP = "NOMBRE_DISP";
        public static final String DESCRIPCION = "DESCRIPCION";

    }
}
