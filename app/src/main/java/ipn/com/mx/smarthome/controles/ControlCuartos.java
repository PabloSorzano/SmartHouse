package ipn.com.mx.smarthome.controles;

import android.arch.persistence.room.Room;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.AsyncTask;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.List;

import ipn.com.mx.smarthome.R;
import ipn.com.mx.smarthome.adaptadores.AdaptadorCuartos;
import ipn.com.mx.smarthome.agregar.AgregarCuarto;
import ipn.com.mx.smarthome.bd.AppDatabase;
import ipn.com.mx.smarthome.domain.Cuarto;

public class ControlCuartos extends Fragment {

    private RecyclerView poRecyclerView;
    private List<Cuarto> listaCuartos;
    private AppDatabase db;
    private int idCasa;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_control_cuartos, container, false);
        getActivity().setTitle("Cuartos");

        idCasa = this.getArguments().getInt("idCasa");
        Log.d("idCasa: " , idCasa+"");

        poRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerCuartos);
        poRecyclerView.setHasFixedSize(true);
        poRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        //Creando o obteniendo la base
        db = Room.databaseBuilder(getActivity(), AppDatabase.class, "smartHome").build();


        setHasOptionsMenu(true);

        loadSwipe();
        return view;
    }

    @Override
    public void onResume(){
        super.onResume();
        mostrarCuartos();
    }

    private void mostrarCuartos()
    {
        Log.d("Main", "consultarCuartos");
        new AsyncTask<Void, Void, Integer>() {
            @Override
            protected Integer doInBackground(Void... params) {

                //Consultado las casas registradas
                listaCuartos= db.poCuartoDao().consultarCuarto(idCasa);
                return 1;
            }

            @Override
            protected void onPostExecute(Integer agentsCount) {
                mostrarCuartosListView();
            }
        }.execute();
    }

    private void mostrarCuartosListView()
    {
        //Validando si hay casas o no
        if(listaCuartos==null || listaCuartos.size()==0)
            Toast.makeText(getActivity(), "No hay cuartos registradas para: " + idCasa, Toast.LENGTH_SHORT).show();
        else
        {
            //Si hay casas llenar la lista(interfaz)
            AdaptadorCuartos adaptadorCuartos = new AdaptadorCuartos(listaCuartos, getActivity());
            poRecyclerView.setAdapter(adaptadorCuartos);
        }
    }


    public void loadSwipe()
    {
        ItemTouchHelper.SimpleCallback simpleCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {

            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return true;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                final int position = viewHolder.getAdapterPosition();
                final Cuarto cuarto = listaCuartos.get(position);

                new AsyncTask<Void, Void, Integer>() {
                    @Override
                    protected Integer doInBackground(Void... params) {
                        //Lo borra de la base de datos
                        db.poCuartoDao().borrarCuarto(cuarto);
                        return 1;
                    }

                    @Override
                    protected void onPostExecute(Integer agentsCount) {

                        //Lo borra de la lista (visual)
                        listaCuartos.remove(position);
                        //Actualizar la lista
                        poRecyclerView.getAdapter().notifyDataSetChanged();
                    }
                }.execute();
            }

            @Override
            public void onChildDraw(Canvas c, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
                Paint color = new Paint();
                Bitmap icon;
                if(actionState == ItemTouchHelper.ACTION_STATE_SWIPE)
                {
                    View itemView = viewHolder.itemView;
                    float height = (float) itemView.getBottom() - (float) itemView.getTop();
                    float width = height / 3;

                    //Si se hace swipe a la derecha
                    if(dX<0){
                        color.setColor(Color.RED);
                        RectF background = new RectF((float) itemView.getRight() + dX, (float) itemView.getTop(),(float) itemView.getRight(), (float) itemView.getBottom());
                        c.drawRect(background, color);
                    }

                }
                super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
            }
        };

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleCallback);
        itemTouchHelper.attachToRecyclerView(poRecyclerView);
    }

    /*Barra de Menú para agregar*/
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_add, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);
        switch (item.getItemId())
        {
            case R.id.menuAgregar:
                Intent loIntent = new Intent(getActivity(), AgregarCuarto.class);
                loIntent.putExtra("idCasa", idCasa);
                startActivity(loIntent);
                break;

        }
        return true;
    }


}
