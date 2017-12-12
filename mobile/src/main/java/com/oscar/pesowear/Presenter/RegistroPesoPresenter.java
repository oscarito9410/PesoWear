package com.oscar.pesowear.Presenter;

import com.oscar.maincore.MVP.Presenter.BasePresenter;

import java.util.Calendar;

/**
 * Created by oemy9 on 11/12/2017.
 */

public interface RegistroPesoPresenter extends BasePresenter {
    void agregarPeso(Double peso,String nota);
    void setFecha(Calendar selectedFecha);
    void setFecha(int year,int month,int  dayOfMonth);
}
