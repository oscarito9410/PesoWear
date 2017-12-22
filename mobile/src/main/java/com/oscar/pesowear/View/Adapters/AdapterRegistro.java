package com.oscar.pesowear.View.Adapters;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.oscar.pesowear.Model.Perfil;
import com.oscar.pesowear.Model.Registro;
import com.oscar.pesowear.R;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by oemy9 on 12/12/2017.
 */

public class AdapterRegistro  extends  RecyclerView.Adapter<AdapterRegistro.ViewHolder>{

    private List<Registro>listRegistros;
    private LayoutInflater layoutInflater;

    private SimpleDateFormat dayFormat=new SimpleDateFormat("dd MMM",new Locale("es"));
    private SimpleDateFormat hourFormat=new SimpleDateFormat("hh:mm aa");
    private Perfil perfil;


    public AdapterRegistro(Context ctx,List<Registro> listRegistros) {
        this.listRegistros = listRegistros;
        this.layoutInflater=LayoutInflater.from(ctx);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(layoutInflater.inflate(R.layout.item_registro,parent,false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bindInfo(this.perfil, position);
    }

    @Override
    public int getItemCount() {
        return listRegistros.size();
        
    }

    public void setPerfil(Perfil perfil) {
        this.perfil = perfil;
    }

    public class  ViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.tvDia)
        TextView tvDia;
        @BindView(R.id.tvHora)
        TextView tvHora;
        @BindView(R.id.tvPeso)
        TextView tvPeso;
        @BindView(R.id.tvComparacion)
        TextView tvComparacion;
        @BindView(R.id.tvIMC)
        TextView tvImc;
        @BindView(R.id.imgArrow)
        ImageView imgArrow;
        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
        public void bindInfo(Perfil p, int position) {
            Registro r = listRegistros.get(position);
            tvPeso.setText(String.valueOf(r.getPeso()) + p.getUnidadMedida());
            tvDia.setText(dayFormat.format(r.getFecha()).replace(".", "").toUpperCase());
            tvHora.setText(hourFormat.format(r.getFecha()));
            try {
                double comparacion = listRegistros.get(position).getPeso() - listRegistros.get( position!=0? position - 1: position + 1).getPeso();
                listRegistros.get(position).getPeso();
                imgArrow.setImageResource(comparacion > 0 ? R.drawable.ic_flecha_arriba : R.drawable.ic_flecha_abajo);

            }
            catch (Exception ex){
                ex.printStackTrace();
                imgArrow.setImageResource(R.drawable.ic_arrow_up);

            }

            /*
            double comparacion = position != 0 && position != listRegistros.size() ? listRegistros.get(position).getPeso() - listRegistros.get(position - 1).getPeso() : listRegistros.get(position).getPeso();
            double imc = FormulasUtils.getImc(r.getPeso(), p.getEstatura());
            tvImc.setText(String.format("%1.2f", imc).concat(" IMC"));
            tvComparacion.setText(comparacion > 0 ? "+" + String.format("%1.2f", comparacion) :   String.format("%1.2f", comparacion));¨*/

        }

    }
}
