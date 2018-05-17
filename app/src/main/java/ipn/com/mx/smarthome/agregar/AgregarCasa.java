package ipn.com.mx.smarthome.agregar;

import android.annotation.SuppressLint;
import android.arch.persistence.room.Room;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;

import java.util.List;

import ipn.com.mx.smarthome.R;
import ipn.com.mx.smarthome.bd.AppDatabase;
import ipn.com.mx.smarthome.common.Utilidades;
import ipn.com.mx.smarthome.domain.Casa;

public class AgregarCasa extends AppCompatActivity implements Validator.ValidationListener{

    @NotEmpty(messageResId =  R.string.msgNombre )
    private EditText nombre;

    @NotEmpty(messageResId =  R.string.msgDescripcion )
    private EditText descripcion;

    private Spinner spinner;
    private Button botonAgregar;

    private Validator poValidator;
    private Utilidades poUtilidades;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_casa);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        poValidator = new Validator(this);
        poValidator.setValidationListener(this);

        poUtilidades = new Utilidades(this);

        nombre = (EditText) findViewById(R.id.txtNombreCasa);
        descripcion = (EditText) findViewById(R.id.txtDescCasa);
        spinner = (Spinner) findViewById(R.id.spinnerCasa);

        botonAgregar = (Button) findViewById(R.id.btnAgregarCasa);
        botonAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                poValidator.validate();
            }
        });
    }

    @Override
    //Se llama si los datos requeridos se ingresaron correctamente
    public void onValidationSucceeded() {
        agregarCasaAsync();
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


    @SuppressLint("StaticFieldLeak")
    private void agregarCasaAsync()
    {
        new AsyncTask<Void, Void, Integer>() {
            @Override
            protected Integer doInBackground(Void... params) {
                agregarCasa();
                return 1;
            }

            @Override
            protected void onPostExecute(Integer agentsCount) {
                finish();
            }
        }.execute();
    }

    private void agregarCasa()
    {
        Casa casa = new Casa();
        casa.setNombre(nombre.getText().toString().trim());
        casa.setDescripcion(descripcion.getText().toString().trim());

        String tipo = String.valueOf(spinner.getSelectedItem());
        if(tipo.equals("Casa"))
            casa.setImagen(R.drawable.casa);
        else  if(tipo.equals("Casa grande"))
            casa.setImagen(R.drawable.casagrande);
        else  if(tipo.equals("Departamento"))
            casa.setImagen(R.drawable.edificio);

        //Agregarlo a la bd
        AppDatabase db = Room.databaseBuilder(this, AppDatabase.class, "smartHome").build();
        db.poCasaDao().insertarCasa(casa);
    }

    //Flecha de regresar
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);
        switch (item.getItemId())
        {
            case android.R.id.home:
                finish();
                break;
        }
        return true;
    }
}
