package ipn.com.mx.smarthome;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;

import java.util.List;

import ipn.com.mx.smarthome.common.*;

public class LoginActivity extends AppCompatActivity implements Validator.ValidationListener{
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

        btnIniciar = (Button) findViewById(R.id.btnIniciar);

        txtUsuario = (EditText)  findViewById(R.id.txtUsuarioLogin);
        txtContrasenia = (EditText)  findViewById(R.id.txtContraseniaLogin);
        chkShowContrasenia = (CheckBox) findViewById(R.id.chkShowContrasenia);
        txtUsuario.setText("Usuario");

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
            poUtilidades.showToastCentrado(getApplicationContext(),"Nombre de usuario incorrecto");
            txtUsuario.setText("");
        }else if(!contra){
            poUtilidades.showToastCentrado(getApplicationContext(),"Contraseña incorrecta");
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
                poUtilidades.showToastCentrado(getApplicationContext(), message);
        }
    }

    private void iniciarSesion()
    {
        Intent loIntent = new Intent(this, Principal.class);
        loIntent.putExtra("usuario", psUsuario);
        this.finish();
        startActivity(loIntent);
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
