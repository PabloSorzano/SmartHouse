package ipn.com.mx.smarthome.common;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.Gravity;
import android.widget.Toast;

/**
 * Created by Cristina on 07/05/2018.
 */

public class Utilidades {

    private Context context;

    public Utilidades(Context context){
        this.context = context;
    }

    //Creado para mostrar los dialogos en diferentes partes del codigo con diferente información
    public void showMessage(String tsTitle, String tsMessage, String tsButton)
    {
        final AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(tsTitle).setMessage(tsMessage).setPositiveButton(tsButton, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
            }
        });
        builder.setCancelable(false);
        builder.show();
    }

    //Para mostrar un toast en medio de la pantalla
    public void showToastCentrado(String msj)
    {
        Toast a = Toast.makeText(context, msj , Toast.LENGTH_SHORT);
        a.show();
    }

}
