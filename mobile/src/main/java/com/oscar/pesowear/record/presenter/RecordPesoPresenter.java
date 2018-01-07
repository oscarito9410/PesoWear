package com.oscar.pesowear.record.presenter;

import com.oscar.maincore.MVP.Presenter.BasePresenter;

import java.util.Calendar;

/**
 * Created by oemy9 on 11/12/2017.
 */

public interface RecordPesoPresenter extends BasePresenter {
    void obtenerPesoInicial();
    void agregarPeso(Double peso,String nota);
    void setFecha(Calendar selectedFecha);
    void setFecha(int year,int month,int  dayOfMonth);
}
