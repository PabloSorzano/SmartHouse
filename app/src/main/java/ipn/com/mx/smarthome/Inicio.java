package ipn.com.mx.smarthome;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import ipn.com.mx.smarthome.agregar.AgregarUsuario;

public class Inicio extends AppCompatActivity {
    ImageButton install, usr;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);

        install = (ImageButton)findViewById(R.id.installer);
        usr = (ImageButton)findViewById(R.id.user);
        install.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), AgregarUsuario.class);
                finish();
                startActivity(intent);
            }
        });
        usr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                finish();
                startActivity(intent);
            }
        });
    }
}
