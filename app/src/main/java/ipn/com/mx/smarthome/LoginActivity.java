package ipn.com.mx.smarthome;

import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.*;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;

import java.util.List;

import ipn.com.mx.smarthome.agregar.AgregarUsuario;
import ipn.com.mx.smarthome.common.*;
import ipn.com.mx.smarthome.data.*;

public class LoginActivity extends AppCompatActivity implements Validator.ValidationListener{
    SmartHouseDBHelper jj ;
    SQLiteDatabase sqLiteDatabase ;
    Cursor cursor;

    boolean usr, contra, conD=true;
    @NotEmpty(messageResId =  R.string.msgUsuario )
    EditText txtUsuario;

    @NotEmpty(messageResId =  R.string.msgContrasenia )
    EditText txtContrasenia;

    private Validator poValidator;
    private Button btnIniciar;
    private CheckBox chkShowContrasenia;

    private ProgressDialog poProgress;

    private String psUsuario;
    private String psContrasenia;
    private Utilidades poUtilidades;
    private validacionesJT poValidaciones;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        jj = new SmartHouseDBHelper(getApplicationContext());
        sqLiteDatabase = jj.getWritableDatabase();

        btnIniciar = (Button) findViewById(R.id.btnIniciar);

        txtUsuario = (EditText)  findViewById(R.id.txtUsuarioLogin);
        txtContrasenia = (EditText)  findViewById(R.id.txtContraseniaLogin);
        chkShowContrasenia = (CheckBox) findViewById(R.id.chkShowContrasenia);

        poValidator = new Validator(this);
        poValidator.setValidationListener(this);

        poUtilidades = new Utilidades(this);

        poValidaciones =  new validacionesJT();

        //1. Iniciar sesión
        btnIniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                poValidator.validate();
            }
        });

        //2.Mostrar/ocultar contraseña
        chkShowContrasenia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (chkShowContrasenia.isChecked()){
                    txtContrasenia.setTransformationMethod(null);
                }else{
                    txtContrasenia.setTransformationMethod(new PasswordTransformationMethod());
                }
            }
        });
    }

    @Override
    //Se llama si los datos requeridos se ingresaron correctamente
    public void onValidationSucceeded() {

        psUsuario =  txtUsuario.getText().toString().trim();
        psContrasenia = txtContrasenia.getText().toString().trim();

        usr = poValidaciones.sinEspecial(psUsuario);
        contra = poValidaciones.sinEspecial(psUsuario);

        if(!usr){
            poUtilidades.showToastCentrado("Nombre de usuario incorrecto");
            txtUsuario.setText("");
        }else if(!contra){
            poUtilidades.showToastCentrado("Contraseña incorrecta");
            txtContrasenia.setText("");
        }else if(conD){
            iniciarSesion();
        }


    }

    //Se llama si algun dato requerido se ingresó incorrectamente
    @Override
    public void onValidationFailed(List<ValidationError> errors) {
        for (ValidationError error : errors) {
            View view = error.getView();
            String message = error.getCollatedErrorMessage(this);
            if (view instanceof TextView)
                ((TextView) view).setError(message);
            else
                poUtilidades.showToastCentrado(message);
        }
    }

    private void iniciarSesion()
    {
        consult();
        if(psUsuario.equals(nombre)&&psContrasenia.equals(pass)){
            Intent loIntent = new Intent(this, Principal.class);
            loIntent.putExtra("usuario", psUsuario);
            loIntent.putExtra("contraseña", psContrasenia);
            this.finish();
            startActivity(loIntent);
        }else{
            Toast.makeText(getApplicationContext(), "Usuario y/o contraseña incorrectos", Toast.LENGTH_SHORT).show();
        }
        jj.close();
    }

    String table, columns[], selection, selectionArgs[], groupBy, having, orderBy, limit, msj;
    String coorde, estado, muni, codP, col, call, numInt;
    String nombre, email, pass, aPat, aMat, telefono;
    String nomCuarto, numPiso, obser;
    int idCasa, idUsr, idCuarto, idCuartoDisp, idTipoDisp;
    public boolean consult(){
        table = SmartConstract.UsrEntry.TABLE_NAME;
        columns = new String[]{SmartConstract.UsrEntry.ID_USUARIO,
                SmartConstract.UsrEntry.NAME_USUARIO,
                SmartConstract.UsrEntry.APELLIDO_PATERNO,
                SmartConstract.UsrEntry.APELLIDO_MATERNO,
                SmartConstract.UsrEntry.TELEFONO_MOVIL,
                SmartConstract.UsrEntry.EMAIL,
                SmartConstract.UsrEntry.CONTRASEÑA};
        selection = SmartConstract.UsrEntry.NAME_USUARIO+" = ?";
        selectionArgs = new String[]{psUsuario};
        groupBy = null;
        having = null;
        orderBy = null;
        limit = null;
        msj="";
        //table: the name of the table you want to query
        //columns: the column names that you want returned. Don't return data that you don't need.
        //selection: the row data that you want returned from the columns (This is the WHERE clause.)
        //selectionArgs: This is substituted for the ? in the selection String above.
        //groupBy and having: This groups duplicate data in a column with data having certain conditions. Any unneeded parameters can be set to null.
        //        orderBy: sort the data
        //limit: limit the number of results to return

        cursor = sqLiteDatabase.query(table, columns, selection, selectionArgs, groupBy, having, orderBy, limit);
        //Nos aseguramos de que existe al menos un registro
        if (cursor.moveToFirst()) {
            //Recorremos el cursor hasta que no haya más registros
            do {
                idUsr = Integer.parseInt(cursor.getString(0));
                nombre = cursor.getString(1);
                aPat = cursor.getString(2);
                aMat = cursor.getString(3);
                telefono = cursor.getString(4);
                email = cursor.getString(5);
                pass = cursor.getString(6);
                msj = "-------------USUARIO-------------\n"+
                        "ID_Usuario:\n  "+idUsr+"\n" +
                        "Nombre:\n  "+nombre+" "+aPat+" "+aMat+"\n" +
                        "Teléfono Móvil:\n  "+telefono+"\n" +
                        "E-mail:\n  "+email+"\n" +
                        "Contraseña:\n  "+pass+"\n";
                //Toast.makeText(getApplicationContext(), "Existes", Toast.LENGTH_SHORT).show();
            } while(cursor.moveToNext());

            table = SmartConstract.CasaEntry.TABLE_NAME;
            columns = new String[]{SmartConstract.CasaEntry.ID_CASA,
                    SmartConstract.CasaEntry.ID_USUARIO,
                    SmartConstract.CasaEntry.COORDENADAS,
                    SmartConstract.CasaEntry.ESTADO,
                    SmartConstract.CasaEntry.MUNICIPIO,
                    SmartConstract.CasaEntry.CODIGO_POSTAL,
                    SmartConstract.CasaEntry.COLONIA,
                    SmartConstract.CasaEntry.CALLE,
                    SmartConstract.CasaEntry.NUMERO_INTERIOR};
            selection = SmartConstract.CasaEntry.ID_USUARIO+"=?";
            selectionArgs = new String[]{String.valueOf(idUsr)};
            groupBy = null;
            having = null;
            orderBy = null;
            limit = null;
            msj="";
            //table: the name of the table you want to query
            //columns: the column names that you want returned. Don't return data that you don't need.
            //selection: the row data that you want returned from the columns (This is the WHERE clause.)
            //selectionArgs: This is substituted for the ? in the selection String above.
            //groupBy and having: This groups duplicate data in a column with data having certain conditions. Any unneeded parameters can be set to null.
            //        orderBy: sort the data
            //limit: limit the number of results to return

            cursor = sqLiteDatabase.query(table, columns, selection, selectionArgs, groupBy, having, orderBy, limit);
            //Nos aseguramos de que existe al menos un registro
            if (cursor.moveToFirst()) {
                //Recorremos el cursor hasta que no haya más registros
                do {
                    idCasa = Integer.parseInt(cursor.getString(0));
                    coorde = cursor.getString(2);
                    estado = cursor.getString(3);
                    muni = cursor.getString(4);
                    codP = cursor.getString(5);
                    col = cursor.getString(6);
                    call = cursor.getString(7);
                    numInt = cursor.getString(8);
                    msj = "-------------CASA-------------\n"+
                            "ID_Casa:\n  "+idCasa+"\n" +
                            "ID_Usuario:\n  "+cursor.getString(1)+"\n" +
                            "Coordenadas:\n  "+coorde+"\n" +
                            "Estado:\n  "+estado+"\n" +
                            "Municipio:\n  "+muni+"\n" +
                            "Codigo Postal:\n  "+codP+"\n" +
                            "Colonia:\n  "+col+"\n" +
                            "Calle:\n  "+call+"\n" +
                            "Numero Interior:\n  "+numInt+ "\n";

                } while(cursor.moveToNext());
            }else{
            }
            //cuarto
            table = SmartConstract.CuartoEntry.TABLE_NAME;
            columns = new String[]{SmartConstract.CuartoEntry.ID_CUARTO,
                    SmartConstract.CuartoEntry.ID_CASA,
                    SmartConstract.CuartoEntry.NOMBRE_CUARTO,
                    SmartConstract.CuartoEntry.NUMERO_PISO,
                    SmartConstract.CuartoEntry.OBSERVACION};
            selection = SmartConstract.CuartoEntry.ID_CASA+"=?";
            selectionArgs = new String[]{String.valueOf(idCasa)};
            groupBy = null;
            having = null;
            orderBy = null;
            limit = null;
            msj="";
            //table: the name of the table you want to query
            //columns: the column names that you want returned. Don't return data that you don't need.
            //selection: the row data that you want returned from the columns (This is the WHERE clause.)
            //selectionArgs: This is substituted for the ? in the selection String above.
            //groupBy and having: This groups duplicate data in a column with data having certain conditions. Any unneeded parameters can be set to null.
            //        orderBy: sort the data
            //limit: limit the number of results to return

            cursor = sqLiteDatabase.query(table, columns, selection, selectionArgs, groupBy, having, orderBy, limit);
            //Nos aseguramos de que existe al menos un registro
            if (cursor.moveToFirst()) {
                //Recorremos el cursor hasta que no haya más registros
                do {
                    idCuarto = Integer.parseInt(cursor.getString(0));
                    nomCuarto = cursor.getString(2);
                    numPiso = cursor.getString(3);
                    obser = cursor.getString(4);
                    msj = "-------------CUARTO-------------\n"+
                            "ID_Cuarto:\n  "+idCuarto+"\n" +
                            "ID_Casa:\n  "+cursor.getString(1)+"\n" +
                            "Nombre de Cuarto:\n  "+nomCuarto+"\n" +
                            "Numero de Piso:\n  "+numPiso+"\n" ;

                    //dispositivos
                    table = SmartConstract.CuartoDispEntry.TABLE_NAME;
                    columns = new String[]{SmartConstract.CuartoDispEntry.ID_CUARTO_DISP,
                            SmartConstract.CuartoDispEntry.ID_CUARTO,
                            SmartConstract.CuartoDispEntry.ID_TIPO_DISP};
                    selection = SmartConstract.CuartoDispEntry.ID_CUARTO+"=?";
                    selectionArgs = new String[]{String.valueOf(idCuarto)};
                    groupBy = null;
                    having = null;
                    orderBy = null;
                    limit = null;
                    msj="";
                    //table: the name of the table you want to query
                    //columns: the column names that you want returned. Don't return data that you don't need.
                    //selection: the row data that you want returned from the columns (This is the WHERE clause.)
                    //selectionArgs: This is substituted for the ? in the selection String above.
                    //groupBy and having: This groups duplicate data in a column with data having certain conditions. Any unneeded parameters can be set to null.
                    //        orderBy: sort the data
                    //limit: limit the number of results to return
                    Cursor cursor1;
                    cursor1 = sqLiteDatabase.query(table, columns, selection, selectionArgs, groupBy, having, orderBy, limit);
                    //Nos aseguramos de que existe al menos un registro
                    if (cursor1.moveToFirst()) {
                        //Recorremos el cursor hasta que no haya más registros
                        do {
                            idCuartoDisp = Integer.parseInt(cursor1.getString(0));
                            idTipoDisp = Integer.parseInt(cursor1.getString(2));
                            msj = "---------CUARTO/DISP---------\n"+
                                    "ID_CuartoDisp:\n  "+idCuartoDisp+"\n" +
                                    "ID_Cuarto:\n  "+cursor1.getString(1)+"\n" +
                                    "ID_TipoDisp:\n  "+idTipoDisp+"\n" ;

                        } while(cursor1.moveToNext());
                    }else{
                    }


                } while(cursor.moveToNext());
            }else{
            }
            return true;
        }else{
            Toast.makeText(getApplicationContext(), "No existen registros de usuarios", Toast.LENGTH_SHORT).show();
            return false;
        }




    }
    //validación de inicio
    //void signUpAction(View view) {
    //  String email = editTextEmail.getText().toString();
    //  String phone = editTextPhone.getText().toString();
    //  String license = editTextLicence.getText().toString();

    //  AgentDao agentDao = MyApp.DatabaseSetup.getDatabase().agentDao();
        //1: Check if agent already exists
    //  int agentsCount = agentDao.agentsCount(email, phone, license);
    //  if (agentsCount > 0) {
            //2: If it already exists then prompt user
    //      Toast.makeText(this, "Agent already exists!", Toast.LENGTH_LONG).show();
    //  }
    //  else {
    //      Toast.makeText(this, "Agent does not exist! Hurray :)", Toast.LENGTH_LONG).show();
    //      onBackPressed();
    //  }
    //}
}
