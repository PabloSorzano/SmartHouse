package ipn.com.mx.smarthome;


import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import ipn.com.mx.smarthome.*;
import ipn.com.mx.smarthome.controles.ControlTelevision;
import ipn.com.mx.smarthome.controles.ControlCasa;
import ipn.com.mx.smarthome.controles.ControlCuartos;
import ipn.com.mx.smarthome.controles.ControlLuces;
import ipn.com.mx.smarthome.controles.*;

public class Principal extends AppCompatActivity {

    private DrawerLayout poDrawerLayout;
    private ActionBarDrawerToggle poBarToggle;

    private FragmentManager poFragmentManager;

    private Bienvenida poBienvenida;
    private ControlCasa poControlCasa;
    private ControlCuartos poControlCuartos;
    private ControlLuces poControlLuces;
    private ControlCalefaccion poControlCalefaccion;
    private ControlTelevision poControlTelevision;
    private ControlSeguridad poControlCamaras;
    private Configuracion poConfiguracion;

    private NavigationView poNavigationView;

    private TextView poTxtUsuario;
    private String psUsuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        //Obtener datos de ese usuario y mostrarlos en interfaz
        psUsuario = getIntent().getStringExtra("usuario");
        Log.d("Usuario: " , psUsuario);


        poFragmentManager = getSupportFragmentManager();
        poBienvenida = new Bienvenida();
        poControlCasa= new ControlCasa();
        poControlCuartos = new ControlCuartos();
        poControlLuces = new ControlLuces();
        poControlCalefaccion = new ControlCalefaccion();
        poControlTelevision = new ControlTelevision();
        poControlCamaras = new ControlSeguridad();
        poConfiguracion = new Configuracion();

        /*Mostrar u ocultar menu*/
        poDrawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        poBarToggle = new ActionBarDrawerToggle(this,poDrawerLayout,R.string.txtAbrir,R.string.txtCerrar);
        poDrawerLayout.addDrawerListener(poBarToggle);
        poBarToggle.syncState();

        /*Agregar boton para el menu*/
        poNavigationView = (NavigationView) findViewById(R.id.navigationView);
        setupDrawerContent(poNavigationView);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        View header = poNavigationView.getHeaderView(0);
        poTxtUsuario = (TextView) header.findViewById(R.id.txtUser);
        poTxtUsuario.setText(psUsuario);

        //Establecer el fragment inicial de bienvenida
        poFragmentManager.beginTransaction().replace(R.id.frameLayout, poBienvenida).commit();
        setTitle(R.string.txtBienvenido);
    }

    //Movimiento del botón menu bar
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(poBarToggle.onOptionsItemSelected(item))
        {
            return  true;
        }
        return super.onOptionsItemSelected(item);
    }

    //Identificar clic en el menú
    private void selectItemDrawer(MenuItem menuItem)
    {
        Fragment loFragment = null;

        switch (menuItem.getItemId())
        {
            case R.id.menuBienvenida:
                loFragment = poBienvenida;
                break;

            case R.id.menuCasas:
                loFragment = poControlCasa;
                break;

            case R.id.menuLuces:
                loFragment = poControlLuces;
                break;

            case R.id.menuCalefaccion:
                loFragment = poControlCalefaccion;
                break;

            case R.id.menuSeguridad:
                loFragment = poControlTelevision;
                break;

            case R.id.menuCamaras:
                loFragment = poControlCamaras;
                break;

            case R.id.menuConfiguracion:
                loFragment = poConfiguracion;
                break;

            case R.id.menuSalir:
                salir();
                break;
        }

        if(loFragment != null)
        {
            poFragmentManager.beginTransaction().replace(R.id.frameLayout, loFragment).commit();
            menuItem.setChecked(true);
            setTitle(menuItem.getTitle());
        }
        poDrawerLayout.closeDrawers();

    }

    private void setupDrawerContent(NavigationView navigationView)
    {
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                selectItemDrawer(item);
                return false;
            }
        });
    }

    private void salir()
    {
        Intent loIntent = new Intent(this, LoginActivity.class);
        this.finish();
        startActivity(loIntent);
    }

}
