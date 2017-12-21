package com.oscar.pesowear.Presenter;

import android.support.annotation.StringRes;

import com.oscar.maincore.MVP.Presenter.BasePresenterImpl;
import com.oscar.maincore.MVP.View.BaseView;
import com.oscar.maincore.Utils.ENUM_OBJETIVO;
import com.oscar.pesowear.Data.Perfil;
import com.oscar.pesowear.Interactor.DataBaseInteractor;
import com.oscar.pesowear.R;

/**
 * Created by oemy9 on 14/12/2017.
 */

public  class PerfilPresenterImpl extends BasePresenterImpl implements  PerfilPresenter {

    private boolean isHideActual=true, isHideObjetivo=true, isHideEstatura;
    private double pesoActual;
    private double pesoObjetivo;
    private String unidadMedida="KG";
    private DataBaseInteractor interactor;
    private int altura;
    private ViewPerfil view;
    private String unidadMedidaAltura="CM";

    @Override
    public void register(BaseView view) {
        super.register(view);
        if(view!=null){
            this.view=(ViewPerfil) view;
            this.interactor=new DataBaseInteractor();
        }
    }

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

    @Override
    public String getUnidadMedidaAltura() {
        return this.unidadMedidaAltura;
    }

    public void setUnidadMedidaAltura(String unidadMedidaAltura) {
        this.unidadMedidaAltura = unidadMedidaAltura;
    }

    @Override
    public  void guardarPerfil(){
        if(getPesoActual()==0){
            view.onError(R.string.ingresa_peso_actual);
        }else if(getPesoObjetivo()==0){
            view.onError(R.string.ingresa_peso_objetivo);
        }else if(getAltura()==0){
            view.onError(R.string.ingres_tu_estatura);
        }
        else {
            ENUM_OBJETIVO objetivo;
            if (pesoActual >= pesoObjetivo)
                objetivo = ENUM_OBJETIVO.PERDIDA_PESO;
            else
                objetivo = ENUM_OBJETIVO.GANAR_PESO;
           boolean update= interactor.agregarActualizarPerfil("Oscar Emilio", objetivo, getPesoActual(),
                    getPesoObjetivo(), getAltura(), unidadMedida);

            view.onPerfilSaved(update ? R.string.perfil_actualizado : R.string.perfil_guardado);
        }
    }

    @Override
    public void obtenerPerfil() {
        Perfil p=interactor.obtenerPerfil();
        if(p!=null){
                this.setPesoActual(p.getPesoInicio());
                this.setPesoObjetivo(p.getPesoObjetivo());
                this.setAltura(p.getEstatura());
                view.setPerfil(p);
        }
        else{
            this.setPesoActual(75d);
            this.setPesoObjetivo(70d);
            this.setAltura(170);
        }
    }

    public interface  ViewPerfil extends BaseView{
        void onPerfilSaved(@StringRes int mensaje);
        void onError(@StringRes int mensaje);
        void setPerfil(Perfil p);
    }

}
