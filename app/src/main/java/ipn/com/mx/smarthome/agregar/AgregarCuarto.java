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

public class AgregarCuarto extends AppCompatActivity implements Validator.ValidationListener{

    @NotEmpty(messageResId =  R.string.msgNombre )
    private EditText nombre;

    @NotEmpty(messageResId =  R.string.msgDescripcion )
    private EditText descripcion;

    private Spinner spinner;
    private Button botonAgregar;

    private Validator poValidator;
    private Utilidades poUtilidades;

    private int idCasa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_cuarto);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        idCasa = getIntent().getIntExtra("idCasa",0);

        poValidator = new Validator(this);
        poValidator.setValidationListener(this);

        poUtilidades = new Utilidades(this);

        nombre = (EditText) findViewById(R.id.txtNombreCuarto);
        descripcion = (EditText) findViewById(R.id.txtDescCuarto);
        spinner = (Spinner) findViewById(R.id.spinnerCuarto);

        botonAgregar = (Button) findViewById(R.id.btnAgregarCuarto);
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
        agregarCuartoAsync();
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


    private void agregarCuartoAsync()
    {
        new AsyncTask<Void, Void, Integer>() {
            @Override
            protected Integer doInBackground(Void... params) {
                agregarCuarto();
                return 1;
            }

            @Override
            protected void onPostExecute(Integer agentsCount) {
                finish();
            }
        }.execute();
    }

    private void agregarCuarto()
    {
        Cuarto cuarto = new Cuarto();
        cuarto.setNombre(nombre.getText().toString().trim());
        cuarto.setDescripcion(descripcion.getText().toString().trim());
        cuarto.setIdCasa(idCasa);

        String tipo = String.valueOf(spinner.getSelectedItem());
        if(tipo.equals("Dormitorio"))
            cuarto.setImagen(R.drawable.ic_cuarto);
        else  if(tipo.equals("Sala"))
            cuarto.setImagen(R.drawable.ic_sala);
        else  if(tipo.equals("Cocina"))
            cuarto.setImagen(R.drawable.ic_cocina);
        else  if(tipo.equals("Garage"))
            cuarto.setImagen(R.drawable.ic_garage);
        else  if(tipo.equals("Baño"))
            cuarto.setImagen(R.drawable.ic_bano);

        //Agregarlo a la bd
        AppDatabase db = Room.databaseBuilder(this, AppDatabase.class, "smartHome").build();
        db.poCuartoDao().insertarCuarto(cuarto);
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

