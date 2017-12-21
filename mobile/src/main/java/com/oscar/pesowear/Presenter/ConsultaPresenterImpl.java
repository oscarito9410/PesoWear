package com.oscar.pesowear.Presenter;
import com.github.mikephil.charting.data.Entry;
import com.oscar.maincore.MVP.Presenter.BasePresenterImpl;
import com.oscar.maincore.MVP.View.BaseView;
import com.oscar.maincore.Utils.ENUM_OBJETIVO;
import com.oscar.pesowear.Data.Perfil;
import com.oscar.pesowear.Data.Registro;
import com.oscar.pesowear.Interactor.DataBaseInteractor;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by oemy9 on 12/12/2017.
 */

public class ConsultaPresenterImpl extends BasePresenterImpl implements ConsultaPresenter {

    private ConsultaView view;
    private List<Entry>listEntries;
    private DataBaseInteractor interactor;

    @Override
    public void register(BaseView view) {
        super.register(view);
        this.view=(ConsultaView)view;
        this.listEntries=new ArrayList<>();
        this.interactor=new DataBaseInteractor();
    }

    @Override
    public void obtenerRegistros() {
        int c=0;
        for (Registro r: interactor.obtenerListRegistros()){
            listEntries.add(new Entry(c, (float)r.getPeso()));
            c++;
        }

        if(view!=null){
            view.setListRegistros(interactor.obtenerListRegistros());
            view.setListEntriesChart(listEntries);
            view.setPerfil(interactor.obtenerPerfil());
        }
    }

    public interface ConsultaView extends BaseView{
        void setListRegistros(List<Registro>listRegistros);
        void setListEntriesChart(List<Entry>listEntries);
        void setPerfil(Perfil perfil);
    }
}
