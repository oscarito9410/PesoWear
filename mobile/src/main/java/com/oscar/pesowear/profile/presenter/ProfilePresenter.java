package com.oscar.pesowear.profile.presenter;

import com.oscar.maincore.MVP.Presenter.BasePresenter;

/**
 * Created by oemy9 on 14/12/2017.
 */

public interface ProfilePresenter extends BasePresenter {
    boolean getIsHiddenActual ();

    boolean getIsHiddenObjetivo ();

    boolean getIsHiddenEstatura ();

    double getPesoActual ();

    double getPesoObjetivo ();

    int getAltura ();

    void setHideEstatura (boolean hideEstatura);

    void setHideActual (boolean hideActual);

    void setHideObjetivo (boolean hideObjetivo);

    void setPesoActual (double pesoActual);

    void setPesoObjetivo (double pesoObjetivo);

    void setAltura (int altura);

    void setUnidadMedida (String unidad);

    String getUnidadMedida ();

    String getUnidadMedidaAltura ();

    void guardarPerfil ();

    void obtenerPerfil ();
}
