package ipn.com.mx.smarthome.adaptadores;


import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
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
import ipn.com.mx.smarthome.domain.Casa;

public class AdaptadorCasa extends RecyclerView.Adapter<AdaptadorCasa.ViewHolder> {

    private List<Casa> listCasa;
    private Context context;

    public AdaptadorCasa(List<Casa> listCasa, Context context) {
        this.listCasa = listCasa;
        this.context = context;
    }

    @Override
    public AdaptadorCasa.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.itemlistview, parent, false);
        return  new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(AdaptadorCasa.ViewHolder holder, int position) {
        final int pos = position;
        final Casa casa = listCasa.get(position);

        holder.imagen.setImageDrawable(context.getResources().getDrawable(casa.getImagen()));
        holder.titulo.setText(casa.getNombre());
        holder.descripcion.setText(casa.getDescripcion());

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager manager = ((FragmentActivity)context).getSupportFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();

                Bundle bundle = new Bundle();
                bundle.putInt("idCasa", casa.getIdCasa());

                ControlCuartos controlCuartos = new ControlCuartos();
                controlCuartos.setArguments(bundle);
                transaction.replace(R.id.frameLayout, controlCuartos);
                transaction.commit();
            }
        });

    }

    @Override
    public int getItemCount() {
        return listCasa.size();
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
