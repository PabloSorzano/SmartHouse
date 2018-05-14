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
import ipn.com.mx.smarthome.controles.ControlCalefaccion;
import ipn.com.mx.smarthome.controles.ControlCuartos;
import ipn.com.mx.smarthome.controles.ControlDispositivos;
import ipn.com.mx.smarthome.controles.ControlLuces;
import ipn.com.mx.smarthome.controles.ControlTelevision;
import ipn.com.mx.smarthome.domain.Casa;
import ipn.com.mx.smarthome.domain.Cuarto;
import ipn.com.mx.smarthome.domain.Dispositivo;

public class AdaptadorDispositivos extends RecyclerView.Adapter<AdaptadorDispositivos.ViewHolder> {

    private List<Dispositivo> listDispositivos;
    private Context context;

    public AdaptadorDispositivos(List<Dispositivo> listDispositivos, Context context) {
        this.listDispositivos = listDispositivos;
        this.context = context;
    }

    @Override
    public AdaptadorDispositivos.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.itemlistview, parent, false);
        return  new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(AdaptadorDispositivos.ViewHolder holder, int position) {
        final int pos = position;
        final Dispositivo dispositivo = listDispositivos.get(position);

        holder.imagen.setImageDrawable(context.getResources().getDrawable(dispositivo.getImagen()));
        holder.titulo.setText(dispositivo.getNombre());
        holder.descripcion.setText(dispositivo.getDescripcion());

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                FragmentManager manager = ((FragmentActivity)context).getSupportFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();

                Bundle bundle = new Bundle();
                bundle.putInt("idDispositivo", dispositivo.getIdDispositivo());

                if(dispositivo.getTipo()==1) {

                    ControlLuces controlLuces = new ControlLuces();
                    controlLuces.setArguments(bundle);
                    transaction.replace(R.id.frameLayout,controlLuces );
                    transaction.commit();
                }
                else  if(dispositivo.getTipo()==2) {

                    ControlTelevision controlTelevision = new ControlTelevision();
                    controlTelevision.setArguments(bundle);
                    transaction.replace(R.id.frameLayout, controlTelevision);
                    transaction.commit();
                }

                else  if(dispositivo.getTipo()==3) {

                    ControlCalefaccion controlCalefaccion = new ControlCalefaccion();
                    controlCalefaccion.setArguments(bundle);
                    transaction.replace(R.id.frameLayout, controlCalefaccion);
                    transaction.commit();
                }


            }
        });

    }

    @Override
    public int getItemCount() {
        return listDispositivos.size();
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