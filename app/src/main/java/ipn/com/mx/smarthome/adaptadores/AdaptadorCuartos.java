package ipn.com.mx.smarthome.adaptadores;
import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import ipn.com.mx.smarthome.R;
import ipn.com.mx.smarthome.controles.ControlCuartos;
import ipn.com.mx.smarthome.controles.ControlDispositivos;
import ipn.com.mx.smarthome.domain.Casa;
import ipn.com.mx.smarthome.domain.Cuarto;

public class AdaptadorCuartos extends RecyclerView.Adapter<AdaptadorCuartos.ViewHolder> {

    private List<Cuarto> listCuartos;
    private Context context;

    public AdaptadorCuartos(List<Cuarto> listCuartos, Context context) {
        this.listCuartos = listCuartos;
        this.context = context;
    }

    @Override
    public AdaptadorCuartos.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.itemlistview, parent, false);
        return  new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(AdaptadorCuartos.ViewHolder holder, int position) {
        final int pos = position;
        final Cuarto cuarto = listCuartos.get(position);

        holder.imagen.setImageDrawable(context.getResources().getDrawable(cuarto.getImagen()));
        holder.titulo.setText(cuarto.getNombre());
        holder.descripcion.setText(cuarto.getDescripcion());

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager manager = ((FragmentActivity)context).getSupportFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();

                Bundle bundle = new Bundle();
                bundle.putInt("idCuarto", cuarto.getIdCuarto());

                ControlDispositivos controlDispositivos = new ControlDispositivos();
                controlDispositivos.setArguments(bundle);
                transaction.replace(R.id.frameLayout, controlDispositivos);
                transaction.commit();
            }
        });

    }

    @Override
    public int getItemCount() {
        return listCuartos.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private ImageView imagen;
        private TextView titulo;
        private TextView descripcion;
        private CardView cardView;

        public ViewHolder(View itemView) {
            super(itemView);
            imagen = (ImageView) itemView.findViewById(R.id.imageView);
            titulo = (TextView) itemView.findViewById(R.id.textTitulo);
            descripcion = (TextView) itemView.findViewById(R.id.textDescripcion);
            cardView = (CardView) itemView.findViewById(R.id.cardView);

        }
    }
}