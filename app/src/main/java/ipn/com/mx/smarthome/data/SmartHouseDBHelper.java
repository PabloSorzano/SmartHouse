package ipn.com.mx.smarthome.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SmartHouseDBHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "SMARTHOUSE";
    ContentValues values;

    // se guarda en /data/data/<paquete>/databases/<nombre-de-la-bd>.db
    public SmartHouseDBHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //comandos sql
        db.execSQL("CREATE TABLE " + SmartConstract.UsrEntry.TABLE_NAME + " ("
                + SmartConstract.UsrEntry.ID_USUARIO + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL UNIQUE,"
                + SmartConstract.UsrEntry.NAME_USUARIO + " TEXT NOT NULL,"
                + SmartConstract.UsrEntry.APELLIDO_PATERNO + " TEXT NOT NULL,"
                + SmartConstract.UsrEntry.APELLIDO_MATERNO + " TEXT NOT NULL,"
                + SmartConstract.UsrEntry.TELEFONO_MOVIL + " TEXT NOT NULL,"
                + SmartConstract.UsrEntry.EMAIL + " TEXT NOT NULL UNIQUE,"
                + SmartConstract.UsrEntry.CONTRASEÑA + " TEXT NOT NULL)");

        db.execSQL("CREATE TABLE " + SmartConstract.CasaEntry.TABLE_NAME + " ("
                + SmartConstract.CasaEntry.ID_CASA + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL UNIQUE,"
                + SmartConstract.CasaEntry.ID_USUARIO + " INTEGER NOT NULL,"
                + SmartConstract.CasaEntry.COORDENADAS + " TEXT NOT NULL,"
                + SmartConstract.CasaEntry.ESTADO + " TEXT NOT NULL,"
                + SmartConstract.CasaEntry.MUNICIPIO + " TEXT NOT NULL,"
                + SmartConstract.CasaEntry.CODIGO_POSTAL + " TEXT NOT NULL,"
                + SmartConstract.CasaEntry.COLONIA + " TEXT NOT NULL,"
                + SmartConstract.CasaEntry.CALLE + " TEXT NOT NULL,"
                + SmartConstract.CasaEntry.NUMERO_INTERIOR + " TEXT NOT NULL,"
                + "FOREIGN KEY (" + SmartConstract.CasaEntry.ID_USUARIO + ") REFERENCES USUARIO("+ SmartConstract.UsrEntry.ID_USUARIO +") ON DELETE CASCADE ON UPDATE CASCADE)");

        db.execSQL("CREATE TABLE " + SmartConstract.CuartoEntry.TABLE_NAME + " ("
                + SmartConstract.CuartoEntry.ID_CUARTO + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL UNIQUE," //raro
                + SmartConstract.CuartoEntry.ID_CASA + " INTEGER NOT NULL,"
                + SmartConstract.CuartoEntry.NOMBRE_CUARTO + " TEXT NOT NULL,"
                + SmartConstract.CuartoEntry.NUMERO_PISO + " TEXT NOT NULL,"
                + SmartConstract.CuartoEntry.OBSERVACION + " TEXT NOT NULL,"
                + "FOREIGN KEY (" + SmartConstract.CuartoEntry.ID_CASA + ") REFERENCES CASA("+ SmartConstract.CasaEntry.ID_CASA +") ON DELETE CASCADE ON UPDATE CASCADE)");

        db.execSQL("CREATE TABLE " + SmartConstract.CatDispEntry.TABLE_NAME + " ("
                + SmartConstract.CatDispEntry.ID_TIPO_DISP + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL UNIQUE,"
                + SmartConstract.CatDispEntry.NOMBRE_DISP + " TEXT NOT NULL,"
                + SmartConstract.CatDispEntry.DESCRIPCION + " TEXT NOT NULL)");

        db.execSQL("CREATE TABLE " + SmartConstract.CuartoDispEntry.TABLE_NAME + " ("
                + SmartConstract.CuartoDispEntry.ID_CUARTO_DISP + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL UNIQUE," //raro
                + SmartConstract.CuartoDispEntry.ID_CUARTO + " INTEGER NOT NULL,"
                + SmartConstract.CuartoDispEntry.ID_TIPO_DISP + " INTEGER NOT NULL,"
                + "FOREIGN KEY (" + SmartConstract.CuartoDispEntry.ID_CUARTO + ") REFERENCES CUARTO("+ SmartConstract.CuartoEntry.ID_CUARTO +")  ON DELETE CASCADE ON UPDATE CASCADE,"
                + "FOREIGN KEY (" + SmartConstract.CuartoDispEntry.ID_TIPO_DISP + ") REFERENCES CATALOGO_DISPOSITIVOS("+ SmartConstract.CatDispEntry.ID_TIPO_DISP +") ON DELETE CASCADE ON UPDATE CASCADE)");

        db.execSQL("CREATE TABLE " + SmartConstract.UsoDispEntry.TABLE_NAME + " ("
                + SmartConstract.UsoDispEntry.ID_USO_DISP + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL UNIQUE,"
                + SmartConstract.UsoDispEntry.ID_CUARTO_DISP + " INTEGER NOT NULL,"
                + SmartConstract.UsoDispEntry.STATUS + " BOOLEAN NOT NULL,"
                + SmartConstract.UsoDispEntry.INICIO_USO + " TIME NOT NULL,"
                + SmartConstract.UsoDispEntry.FIN_USO + " TIME NOT NULL,"
                + "FOREIGN KEY (" + SmartConstract.UsoDispEntry.ID_CUARTO_DISP + ") REFERENCES CUARTO_DISPOSITIVOS("+ SmartConstract.CuartoDispEntry.ID_CUARTO_DISP +") ON DELETE CASCADE ON UPDATE CASCADE)");

        String mensaje  = "";
        values = new ContentValues();
        values.put(SmartConstract.CatDispEntry.ID_TIPO_DISP, 1);
        values.put(SmartConstract.CatDispEntry.NOMBRE_DISP, "Foco");
        values.put(SmartConstract.CatDispEntry.DESCRIPCION, "Fuente de luz con switch");
        try{
            //Se intenta meter el arreglo de datos a la base de datos
            db.insertOrThrow(SmartConstract.CatDispEntry.TABLE_NAME,null, values);
            mensaje = "1 guardado con exito";
        }catch (SQLException e){
            //Si no se puede mandara el sistema mensaje de error
            mensaje = "Error, " + e.getMessage();
        }
        System.out.println(mensaje);

        values = new ContentValues();
        values.put(SmartConstract.CatDispEntry.ID_TIPO_DISP, 2);
        values.put(SmartConstract.CatDispEntry.NOMBRE_DISP, "Puerta");
        values.put(SmartConstract.CatDispEntry.DESCRIPCION, "Lugar de acceso");
        try{
            //Se intenta meter el arreglo de datos a la base de datos
            db.insertOrThrow(SmartConstract.CatDispEntry.TABLE_NAME,null, values);
            mensaje = "2 guardado con exito";
        }catch (SQLException e) {
            //Si no se puede mandara el sistema mensaje de error
            mensaje = "Error, " + e.getMessage();
        }
        System.out.println(mensaje);

        values = new ContentValues();
        values.put(SmartConstract.CatDispEntry.ID_TIPO_DISP, 3);
        values.put(SmartConstract.CatDispEntry.NOMBRE_DISP, "Camara");
        values.put(SmartConstract.CatDispEntry.DESCRIPCION, "Objeto de vigilancia");
        try{
            //Se intenta meter el arreglo de datos a la base de datos
            db.insertOrThrow(SmartConstract.CatDispEntry.TABLE_NAME,null, values);
            mensaje = "3 guardado con exito";
        }catch (SQLException e){
            //Si no se puede mandara el sistema mensaje de error
            mensaje = "Error, " + e.getMessage();
        }
        System.out.println(mensaje);

        values = new ContentValues();
        values.put(SmartConstract.CatDispEntry.ID_TIPO_DISP, 4);
        values.put(SmartConstract.CatDispEntry.NOMBRE_DISP, "Clima");
        values.put(SmartConstract.CatDispEntry.DESCRIPCION, "Objeto capaz de aclimatizar");
        try{
            //Se intenta meter el arreglo de datos a la base de datos
            db.insertOrThrow(SmartConstract.CatDispEntry.TABLE_NAME,null, values);
            mensaje = "4 guardado con exito";
        }catch (SQLException e){
            //Si no se puede mandara el sistema mensaje de error
            mensaje = "Error, " + e.getMessage();
        }
        System.out.println(mensaje);



        values = new ContentValues();
        values.put(SmartConstract.UsrEntry.ID_USUARIO, 1);
        values.put(SmartConstract.UsrEntry.NAME_USUARIO, "Pablo");
        values.put(SmartConstract.UsrEntry.APELLIDO_PATERNO, "Sorzano");
        values.put(SmartConstract.UsrEntry.APELLIDO_MATERNO, "Torres");
        values.put(SmartConstract.UsrEntry.TELEFONO_MOVIL, "5522582795");
        values.put(SmartConstract.UsrEntry.EMAIL, "peibol.sorz@gmail.com");
        values.put(SmartConstract.UsrEntry.CONTRASEÑA, "123");
        try{
            //Se intenta meter el arreglo de datos a la base de datos
            db.insertOrThrow(SmartConstract.UsrEntry.TABLE_NAME,null,values);
            mensaje = "Usuario guardado con exito";
        }catch (SQLException e){
            //Si no se puede mandara el sistema mensaje de error
            mensaje = "Error, " + e.getMessage();
        }
        System.out.println(mensaje);

        values = new ContentValues();
        values.put(SmartConstract.CasaEntry.ID_CASA, 1);
        values.put(SmartConstract.CasaEntry.ID_USUARIO, 1);
        values.put(SmartConstract.CasaEntry.COORDENADAS, "(lat: -15.0, long: 14.6)");
        values.put(SmartConstract.CasaEntry.ESTADO, "Mexico");
        values.put(SmartConstract.CasaEntry.MUNICIPIO, "Atizapan de Zaragoza");
        values.put(SmartConstract.CasaEntry.CODIGO_POSTAL, "52966");
        values.put(SmartConstract.CasaEntry.COLONIA, "Mexico Nuevo");
        values.put(SmartConstract.CasaEntry.CALLE, "Control Democratico");
        values.put(SmartConstract.CasaEntry.NUMERO_INTERIOR, "13 A");
        try{
            //Se intenta meter el arreglo de datos a la base de datos
            db.insertOrThrow(SmartConstract.CasaEntry.TABLE_NAME,null,values);
            mensaje = "Casa guardada con exito";
        }catch (SQLException e){
            //Si no se puede mandara el sistema mensaje de error
            mensaje = "Error, " + e.getMessage();
        }
        System.out.println(mensaje);

        values = new ContentValues();
        values.put(SmartConstract.CuartoEntry.ID_CUARTO, 1);
        values.put(SmartConstract.CuartoEntry.ID_CASA, 1);
        values.put(SmartConstract.CuartoEntry.NOMBRE_CUARTO, "Sala");
        values.put(SmartConstract.CuartoEntry.NUMERO_PISO, "1");
        values.put(SmartConstract.CuartoEntry.OBSERVACION, "");
        try{
            //Se intenta meter el arreglo de datos a la base de datos
            db.insertOrThrow(SmartConstract.CuartoEntry.TABLE_NAME,null,values);
            mensaje = "Cuarto guardado con exito";
        }catch (SQLException e){
            //Si no se puede mandara el sistema mensaje de error
            mensaje = "Error, " + e.getMessage();
        }
        System.out.println(mensaje);

        values = new ContentValues();
        values.put(SmartConstract.CuartoEntry.ID_CUARTO, 2);
        values.put(SmartConstract.CuartoEntry.ID_CASA, 1);
        values.put(SmartConstract.CuartoEntry.NOMBRE_CUARTO, "Comedor");
        values.put(SmartConstract.CuartoEntry.NUMERO_PISO, "2");
        values.put(SmartConstract.CuartoEntry.OBSERVACION, "");
        try{
            //Se intenta meter el arreglo de datos a la base de datos
            db.insertOrThrow(SmartConstract.CuartoEntry.TABLE_NAME,null,values);
            mensaje = "Cuarto guardado con exito";
        }catch (SQLException e){
            //Si no se puede mandara el sistema mensaje de error
            mensaje = "Error, " + e.getMessage();
        }
        System.out.println(mensaje);

        values = new ContentValues();
        values.put(SmartConstract.CuartoDispEntry.ID_CUARTO_DISP, 1);
        values.put(SmartConstract.CuartoDispEntry.ID_CUARTO, 1);
        values.put(SmartConstract.CuartoDispEntry.ID_TIPO_DISP, 1);
        try{
            //Se intenta meter el arreglo de datos a la base de datos
            db.insertOrThrow(SmartConstract.CuartoDispEntry.TABLE_NAME,null,values);
            mensaje = "Dispositivo guardado con exito";
        }catch (SQLException e){
            //Si no se puede mandara el sistema mensaje de error
            mensaje = "Error, " + e.getMessage();
        }
        System.out.println(mensaje);

        values = new ContentValues();
        values.put(SmartConstract.CuartoDispEntry.ID_CUARTO_DISP, 2);
        values.put(SmartConstract.CuartoDispEntry.ID_CUARTO, 1);
        values.put(SmartConstract.CuartoDispEntry.ID_TIPO_DISP, 2);
        try{
            //Se intenta meter el arreglo de datos a la base de datos
            db.insertOrThrow(SmartConstract.CuartoDispEntry.TABLE_NAME,null,values);
            mensaje = "Dispositivo guardado con exito";
        }catch (SQLException e){
            //Si no se puede mandara el sistema mensaje de error
            mensaje = "Error, " + e.getMessage();
        }
        System.out.println(mensaje);

        values = new ContentValues();
        values.put(SmartConstract.CuartoDispEntry.ID_CUARTO_DISP, 3);
        values.put(SmartConstract.CuartoDispEntry.ID_CUARTO, 1);
        values.put(SmartConstract.CuartoDispEntry.ID_TIPO_DISP, 3);
        try{
            //Se intenta meter el arreglo de datos a la base de datos
            db.insertOrThrow(SmartConstract.CuartoDispEntry.TABLE_NAME,null,values);
            mensaje = "Dispositivo guardado con exito";
        }catch (SQLException e){
            //Si no se puede mandara el sistema mensaje de error
            mensaje = "Error, " + e.getMessage();
        }
        System.out.println(mensaje);

        values = new ContentValues();
        values.put(SmartConstract.CuartoDispEntry.ID_CUARTO_DISP, 4);
        values.put(SmartConstract.CuartoDispEntry.ID_CUARTO, 1);
        values.put(SmartConstract.CuartoDispEntry.ID_TIPO_DISP, 4);
        try{
            //Se intenta meter el arreglo de datos a la base de datos
            db.insertOrThrow(SmartConstract.CuartoDispEntry.TABLE_NAME,null,values);
            mensaje = "Dispositivo guardado con exito";
        }catch (SQLException e){
            //Si no se puede mandara el sistema mensaje de error
            mensaje = "Error, " + e.getMessage();
        }
        System.out.println(mensaje);

        //usuario 2
        //
        //
        //

        values = new ContentValues();
        values.put(SmartConstract.UsrEntry.ID_USUARIO, 2);
        values.put(SmartConstract.UsrEntry.NAME_USUARIO, "Yared");
        values.put(SmartConstract.UsrEntry.APELLIDO_PATERNO, "Morena");
        values.put(SmartConstract.UsrEntry.APELLIDO_MATERNO, "Mia");
        values.put(SmartConstract.UsrEntry.TELEFONO_MOVIL, "1234512345");
        values.put(SmartConstract.UsrEntry.EMAIL, "yared@gmail.com");
        values.put(SmartConstract.UsrEntry.CONTRASEÑA, "pequeña");
        try{
            //Se intenta meter el arreglo de datos a la base de datos
            db.insertOrThrow(SmartConstract.UsrEntry.TABLE_NAME,null,values);
            mensaje = "Usuario guardado con exito";
        }catch (SQLException e){
            //Si no se puede mandara el sistema mensaje de error
            mensaje = "Error, " + e.getMessage();
        }
        System.out.println(mensaje);

        values = new ContentValues();
        values.put(SmartConstract.CasaEntry.ID_CASA, 2);
        values.put(SmartConstract.CasaEntry.ID_USUARIO, 2);
        values.put(SmartConstract.CasaEntry.COORDENADAS, "(lat: -56.0, long: 78.6)");
        values.put(SmartConstract.CasaEntry.ESTADO, "Mexico");
        values.put(SmartConstract.CasaEntry.MUNICIPIO, "Naucalpan");
        values.put(SmartConstract.CasaEntry.CODIGO_POSTAL, "12345");
        values.put(SmartConstract.CasaEntry.COLONIA, "La chingada");
        values.put(SmartConstract.CasaEntry.CALLE, "Muerte");
        values.put(SmartConstract.CasaEntry.NUMERO_INTERIOR, "24");
        try{
            //Se intenta meter el arreglo de datos a la base de datos
            db.insertOrThrow(SmartConstract.CasaEntry.TABLE_NAME,null,values);
            mensaje = "Casa guardada con exito";
        }catch (SQLException e){
            //Si no se puede mandara el sistema mensaje de error
            mensaje = "Error, " + e.getMessage();
        }
        System.out.println(mensaje);

        values = new ContentValues();
        values.put(SmartConstract.CuartoEntry.ID_CUARTO, 3);
        values.put(SmartConstract.CuartoEntry.ID_CASA, 2);
        values.put(SmartConstract.CuartoEntry.NOMBRE_CUARTO, "Sala");
        values.put(SmartConstract.CuartoEntry.NUMERO_PISO, "1");
        values.put(SmartConstract.CuartoEntry.OBSERVACION, "");
        try{
            //Se intenta meter el arreglo de datos a la base de datos
            db.insertOrThrow(SmartConstract.CuartoEntry.TABLE_NAME,null,values);
            mensaje = "Cuarto guardado con exito";
        }catch (SQLException e){
            //Si no se puede mandara el sistema mensaje de error
            mensaje = "Error, " + e.getMessage();
        }
        System.out.println(mensaje);

        values = new ContentValues();
        values.put(SmartConstract.CuartoEntry.ID_CUARTO, 4);
        values.put(SmartConstract.CuartoEntry.ID_CASA, 2);
        values.put(SmartConstract.CuartoEntry.NOMBRE_CUARTO, "Comedor");
        values.put(SmartConstract.CuartoEntry.NUMERO_PISO, "1");
        values.put(SmartConstract.CuartoEntry.OBSERVACION, "");
        try{
            //Se intenta meter el arreglo de datos a la base de datos
            db.insertOrThrow(SmartConstract.CuartoEntry.TABLE_NAME,null,values);
            mensaje = "Cuarto guardado con exito";
        }catch (SQLException e){
            //Si no se puede mandara el sistema mensaje de error
            mensaje = "Error, " + e.getMessage();
        }
        System.out.println(mensaje);

        values = new ContentValues();
        values.put(SmartConstract.CuartoDispEntry.ID_CUARTO_DISP, 5);
        values.put(SmartConstract.CuartoDispEntry.ID_CUARTO, 3);
        values.put(SmartConstract.CuartoDispEntry.ID_TIPO_DISP, 1);
        try{
            //Se intenta meter el arreglo de datos a la base de datos
            db.insertOrThrow(SmartConstract.CuartoDispEntry.TABLE_NAME,null,values);
            mensaje = "Dispositivo guardado con exito";
        }catch (SQLException e){
            //Si no se puede mandara el sistema mensaje de error
            mensaje = "Error, " + e.getMessage();
        }
        System.out.println(mensaje);

        values = new ContentValues();
        values.put(SmartConstract.CuartoDispEntry.ID_CUARTO_DISP, 6);
        values.put(SmartConstract.CuartoDispEntry.ID_CUARTO, 3);
        values.put(SmartConstract.CuartoDispEntry.ID_TIPO_DISP, 2);
        try{
            //Se intenta meter el arreglo de datos a la base de datos
            db.insertOrThrow(SmartConstract.CuartoDispEntry.TABLE_NAME,null,values);
            mensaje = "Dispositivo guardado con exito";
        }catch (SQLException e){
            //Si no se puede mandara el sistema mensaje de error
            mensaje = "Error, " + e.getMessage();
        }
        System.out.println(mensaje);

        values = new ContentValues();
        values.put(SmartConstract.CuartoDispEntry.ID_CUARTO_DISP, 7);
        values.put(SmartConstract.CuartoDispEntry.ID_CUARTO, 3);
        values.put(SmartConstract.CuartoDispEntry.ID_TIPO_DISP, 3);
        try{
            //Se intenta meter el arreglo de datos a la base de datos
            db.insertOrThrow(SmartConstract.CuartoDispEntry.TABLE_NAME,null,values);
            mensaje = "Dispositivo guardado con exito";
        }catch (SQLException e){
            //Si no se puede mandara el sistema mensaje de error
            mensaje = "Error, " + e.getMessage();
        }
        System.out.println(mensaje);

        values = new ContentValues();
        values.put(SmartConstract.CuartoDispEntry.ID_CUARTO_DISP, 8);
        values.put(SmartConstract.CuartoDispEntry.ID_CUARTO, 3);
        values.put(SmartConstract.CuartoDispEntry.ID_TIPO_DISP, 4);
        try{
            //Se intenta meter el arreglo de datos a la base de datos
            db.insertOrThrow(SmartConstract.CuartoDispEntry.TABLE_NAME,null,values);
            mensaje = "Dispositivo guardado con exito";
        }catch (SQLException e){
            //Si no se puede mandara el sistema mensaje de error
            mensaje = "Error, " + e.getMessage();
        }
        System.out.println(mensaje);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //sin operaciones
    }
}
