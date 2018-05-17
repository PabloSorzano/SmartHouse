package ipn.com.mx.smarthome.agregar;

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
import ipn.com.mx.smarthome.domain.Cuarto;
import ipn.com.mx.smarthome.domain.Dispositivo;

public class AgregarDispositivo extends AppCompatActivity implements Validator.ValidationListener{


    @NotEmpty(messageResId =  R.string.msgNombre )
    private EditText nombre;

    @NotEmpty(messageResId =  R.string.msgDescripcion )
    private EditText descripcion;

    private Spinner spinner;
    private Button botonAgregar;

    private Validator poValidator;
    private Utilidades poUtilidades;

    private int idCuarto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_dispositivo);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        idCuarto = getIntent().getIntExtra("idCuarto",0);

        poValidator = new Validator(this);
        poValidator.setValidationListener(this);

        poUtilidades = new Utilidades(this);

        nombre = (EditText) findViewById(R.id.txtNombreDispositivo);
        descripcion = (EditText) findViewById(R.id.txtDescDispositivo);
        spinner = (Spinner) findViewById(R.id.spinnerDispositivos);

        botonAgregar = (Button) findViewById(R.id.btnAgregarDispositivos);
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
        agregarDispositivoAsync();
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


    private void agregarDispositivoAsync()
    {
        new AsyncTask<Void, Void, Integer>() {
            @Override
            protected Integer doInBackground(Void... params) {
                agregarDispositivo();
                return 1;
            }

            @Override
            protected void onPostExecute(Integer agentsCount) {
                finish();
            }
        }.execute();
    }

    private void agregarDispositivo() {
        Dispositivo dispositivo = new Dispositivo();
        dispositivo.setIdCuarto(idCuarto);
        dispositivo.setNombre(nombre.getText().toString());
        dispositivo.setDescripcion(descripcion.getText().toString());
        dispositivo.setEstado(0);

        String tipo = String.valueOf(spinner.getSelectedItem());
        if (tipo.equals("Luces")) {
            dispositivo.setImagen(R.drawable.ic_luces);
            dispositivo.setTipo(1);
        } else if (tipo.equals("Television")) {
            dispositivo.setImagen(R.drawable.ic_sala);
            dispositivo.setTipo(2);
        } else if (tipo.equals("Calefaccion")){
            dispositivo.setImagen(R.drawable.ic_calefaccion);
            dispositivo.setTipo(3);
    }



        //Agregarlo a la bd
        AppDatabase db = Room.databaseBuilder(this, AppDatabase.class, "smartHome").build();
        db.poDispositivoDao().insertarDispositivo(dispositivo);
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