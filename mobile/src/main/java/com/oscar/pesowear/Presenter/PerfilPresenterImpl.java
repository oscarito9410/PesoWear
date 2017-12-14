package com.oscar.pesowear.Presenter;

import com.oscar.maincore.MVP.Presenter.BasePresenterImpl;

/**
 * Created by oemy9 on 14/12/2017.
 */

public class PerfilPresenterImpl extends BasePresenterImpl implements  PerfilPresenter {

    private boolean isHideActual=true, isHideObjetivo=true, isHideEstatura;
    private double pesoActual;
    private double pesoObjetivo;
    private String unidadMedida="KG";
    private int altura;

    @Override
    public boolean getIsHiddenActual() {
        return isHideActual;
    }

    @Override
    public boolean getIsHiddenObjetivo() {
        return isHideObjetivo;
    }

    @Override
    public boolean getIsHiddenEstatura() {
        return isHideEstatura;
    }

    @Override
    public double getPesoActual() {
        return pesoActual;
    }

    @Override
    public double getPesoObjetivo() {
        return pesoObjetivo;
    }

    @Override
    public int getAltura() {
        return altura;
    }

    @Override
    public void setHideEstatura(boolean hideEstatura) {
        this.isHideEstatura=hideEstatura;
    }

    @Override
    public void setHideActual(boolean hideActual) {
            this.isHideActual=hideActual;
    }

    @Override
    public void setHideObjetivo(boolean hideObjetivo) {
        this.isHideObjetivo = hideObjetivo;
    }

    @Override
    public void setPesoActual(double pesoActual) {
        this.pesoActual=pesoActual;
    }

    @Override
    public void setPesoObjetivo(double pesoObjetivo) {
        this.pesoObjetivo=pesoObjetivo;
    }

    @Override
    public void setAltura(int altura) {
        this.altura=altura;
    }

    @Override
    public void setUnidadMedida(String unidad) {
            this.unidadMedida=unidad;
    }

    @Override
    public String getUnidadMedida() {
        return this.unidadMedida;
    }


}
