package com.oscar.pesowear.Presenter;
import com.github.mikephil.charting.data.Entry;
import com.oscar.maincore.MVP.Presenter.BasePresenterImpl;
import com.oscar.maincore.MVP.View.BaseView;
import com.oscar.pesowear.Model.Perfil;
import com.oscar.pesowear.Model.Registro;
import com.oscar.pesowear.Interactor.DataBaseInteractor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by oemy9 on 12/12/2017.
 */

public class ConsultaPresenterImpl extends BasePresenterImpl implements ConsultaPresenter {

    private ConsultaView view;
    private DataBaseInteractor interactor;

    @Override
    public void register(BaseView view) {
        super.register(view);
        this.view=(ConsultaView)view;
        this.interactor=new DataBaseInteractor();
    }

    @Override
    public void obtenerRegistros() {
        List<Entry>listEntries=new ArrayList<>();
        List<Registro>listRegistros=interactor.obtenerListRegistros();
        Collections.reverse(listRegistros);
        for(int j=0;j<listRegistros.size();j++){
            listEntries.add(new Entry(j, (float)listRegistros.get(j).getPeso()));
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
