package com.oscar.pesowear.View.Adapters;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.oscar.pesowear.Model.Perfil;
import com.oscar.pesowear.Model.Registro;
import com.oscar.pesowear.R;
import java.text.SimpleDateFormat;
import java.util.HashMap;
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
    private boolean isDeleting=false;
    private Context ctx;


    public interface RegistroListener{
        void onLongClickDeleteListener(boolean isDelete);
        void onItemDeleteSelected(int count);
    }

    private RegistroListener listener;

    public void setRegistroListener(RegistroListener listener) {
        this.listener = listener;
    }

    public boolean isDeleting() {
        return isDeleting;
    }

    public void setDeleting(boolean deleting) {
        isDeleting = deleting;
    }

    public AdapterRegistro(Context ctx, List<Registro> listRegistros) {
        this.listRegistros = listRegistros;
        this.ctx=ctx;
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
        @BindView(R.id.checkEliminar)
        CheckBox checkEliminar;
        @BindView(R.id.tvDia)
        TextView tvDia;
        @BindView(R.id.tvHora)
        TextView tvHora;
        @BindView(R.id.tvPeso)
        TextView tvPeso;
        @BindView(R.id.tvIMC)
        TextView tvImc;
        @BindView(R.id.tvNotas)
        TextView tvNotas;
        @BindView(R.id.imgArrow)
        ImageView imgArrow;
        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
        public void bindInfo(Perfil p, final int position) {
            Registro r = listRegistros.get(position);
            tvPeso.setText(String.valueOf(r.getPeso()) + p.getUnidadMedida());
            tvDia.setText(dayFormat.format(r.getFecha()).replace(".", "").toUpperCase());
            tvHora.setText(hourFormat.format(r.getFecha()));
            tvNotas.setText(r.getNotas()!=null? ctx.getString(R.string.con_notas):"");
            double comparacion =listRegistros.size()>1? listRegistros.get(position).getPeso() - listRegistros.get( position!=0? position - 1: position + 1).getPeso():0;
            listRegistros.get(position).getPeso();
            imgArrow.setImageResource(comparacion  ==0 ? R.drawable.ic_flecha_abajo_azul: comparacion>0? R.drawable.ic_flecha_arriba : R.drawable.ic_flecha_abajo);
            checkEliminar.setVisibility(isDeleting()? View.VISIBLE: View.GONE);
            checkEliminar.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean checked) {

                }
            });

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    setDeleting(!isDeleting());
                    notifyDataSetChanged();
                    if(listener!=null){
                        listener.onLongClickDeleteListener(isDeleting());
                    }
                    return false;
                }
            });
        }

    }
}
