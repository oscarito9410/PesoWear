package com.oscar.pesowear.View.Adapters;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.oscar.pesowear.Data.Registro;
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
        holder.bindInfo(listRegistros.get(position));
    }

    @Override
    public int getItemCount() {
        return
                listRegistros.size();
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
        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
        public void bindInfo(Registro r){
            tvPeso.setText(String.valueOf(r.getPeso())+"KG");
            tvDia.setText(dayFormat.format(r.getFecha()).replace(".","").toUpperCase());
            tvHora.setText(hourFormat.format(r.getFecha()));
        }
    }
}
