package ipn.com.mx.smarthome;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;

import java.util.List;

import ipn.com.mx.smarthome.common.Utilidades;

public class LoginActivity extends AppCompatActivity implements Validator.ValidationListener{

    @NotEmpty(messageResId =  R.string.msgUsuario )
    TextView txtUsuario;

    @NotEmpty(messageResId =  R.string.msgContrasenia )
    TextView txtContrasenia;

    private Validator poValidator;
    private Button btnIniciar;
    private CheckBox chkShowContrasenia;

    private ProgressDialog poProgress;

    private String psUsuario;
    private String psContrasenia;
    private Utilidades poUtilidades;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btnIniciar = (Button) findViewById(R.id.btnIniciar);

        txtUsuario = (TextView)  findViewById(R.id.txtUsuarioLogin);
        txtContrasenia = (TextView)  findViewById(R.id.txtContraseniaLogin);
        chkShowContrasenia = (CheckBox) findViewById(R.id.chkShowContrasenia);


        poValidator = new Validator(this);
        poValidator.setValidationListener(this);

        poUtilidades = new Utilidades(this);

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

        psUsuario =  txtUsuario.getText().toString();
        psContrasenia = txtContrasenia.getText().toString();
        iniciarSesion();
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
        Intent loIntent = new Intent(this, Principal.class);
        loIntent.putExtra("usuario", psUsuario);
        this.finish();
        startActivity(loIntent);
    }
}
