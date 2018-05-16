package ipn.com.mx.smarthome.agregar;

import android.arch.persistence.room.Room;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.*;
import android.widget.*;

import com.mobsandgeeks.saripaar.*;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;

import java.util.List;

import ipn.com.mx.smarthome.*;
import ipn.com.mx.smarthome.bd.*;
import ipn.com.mx.smarthome.common.*;
import ipn.com.mx.smarthome.domain.*;

public class AgregarUsuario extends AppCompatActivity implements Validator.ValidationListener {
    boolean nama, pata, mata, celu, mai, pass;
    String xnama, xpata, xmata, xcelu, xmail, xpass;

    @NotEmpty(messageResId =  R.string.msgNombre3 )
    private EditText nombre;

    @NotEmpty(messageResId =  R.string.msgApellidoP )
    private EditText apellidoP;

    @NotEmpty(messageResId =  R.string.msgApellidoM )
    private EditText apellidoM;

    @NotEmpty(messageResId =  R.string.msgCelular )
    private EditText cel;

    @NotEmpty(messageResId =  R.string.msgCorreoE )
    private EditText corr;

    @NotEmpty(messageResId =  R.string.msgContraseña )
    private EditText contra;

    private Button botonAgregar;

    private Validator poValidator;
    private Utilidades poUtilidades;
    private validacionesJT poValidaciones;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_usuario);

        poValidator = new Validator(this);
        poValidator.setValidationListener(this);

        poUtilidades = new Utilidades(this);

        poValidaciones = new validacionesJT();

        nombre = (EditText)findViewById(R.id.txtNombre);
        apellidoP = (EditText)findViewById(R.id.txtApellidoP);
        apellidoM = (EditText)findViewById(R.id.txtApellidoM);
        cel = (EditText)findViewById(R.id.txtCelular);
        corr = (EditText)findViewById(R.id.txtCorreo);
        contra = (EditText)findViewById(R.id.txtContraseña);


        botonAgregar = (Button) findViewById(R.id.btnAgregarUsuario);
        botonAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                poValidator.validate();
            }
        });
    }

    @Override
    public void onValidationSucceeded() {
        agregarUsuario();
    }

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



    private void agregarUsuario()
    {
        Usuario usuario = new Usuario();
        xnama = nombre.getText().toString().trim();
        xpata = apellidoP.getText().toString().trim();
        xmata = apellidoM.getText().toString().trim();
        xcelu = cel.getText().toString().trim();
        xmail = corr.getText().toString().trim();
        xpass = contra.getText().toString().trim();

        nama = poValidaciones.soloLetras(xnama);
        pata = poValidaciones.soloLetras(xpata);
        mata = poValidaciones.soloLetras(xmata);
        celu = poValidaciones.soloNumeros(xcelu);
        mai = poValidaciones.isValidEmail(xmail);
        pass = poValidaciones.sinEspecial(xpass);

        if (nama == false) {
            poUtilidades.showToastCentrado("Nombre incorrecto");
            nombre.setText("");
        } else if (pata == false) {
            poUtilidades.showToastCentrado("Apellido Paterno incorrecto");
            apellidoP.setText("");
        } else if (mata == false) {
            poUtilidades.showToastCentrado("Apellido Materno incorrecto");
            apellidoM.setText("");
        } else if (xcelu.length() != 10 || celu == false) {
            poUtilidades.showToastCentrado("Celular incorrecto");
            cel.setText("");
        } else if (mai == false) {
            poUtilidades.showToastCentrado("Correo incorrecto");
            corr.setText("");
        } else if (pass == false) {
            poUtilidades.showToastCentrado("Contraseña incorrecta");
            contra.setText("");
        } else if(nama && pata && mata && mai && pass && celu){
                usuario.setNombre(xnama);
                usuario.setApellidoPaterno(xpata);
                usuario.setApellidoMaterno(xmata);
                usuario.setCelular(xcelu);
                usuario.setCorreoElectronico(xmail);
                usuario.setContraseña(xpass);
                poUtilidades.showToastCentrado("Todo bien");
                //Agregarlo a la bd
                AppDatabase db = Room.databaseBuilder(this, AppDatabase.class, "smartHome").build();
                db.poUsuarioDao().insertarUsuario(usuario);

                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                intent.putExtra("nombre", xnama);
                finish();
                startActivity(intent);
            }else{

            }
        }
}



