package com.oscar.pesowear.Presenter;

import com.oscar.maincore.MVP.Presenter.BasePresenterImpl;
import com.oscar.maincore.MVP.View.BaseView;
import com.oscar.maincore.Utils.FormulasUtils;
import com.oscar.pesowear.Data.IMCResult;
import com.oscar.pesowear.Data.Perfil;
import com.oscar.pesowear.Data.Registro;
import com.oscar.pesowear.Interactor.DataBaseInteractor;

/**
 * Created by oemy9 on 19/12/2017.
 */

public class IMCPresenterImpl  extends BasePresenterImpl implements  IMCPresenter{

    public IMCView view;
    public DataBaseInteractor interactor;


    @Override
    public void register(BaseView view) {
        super.register(view);
        this.view=(IMCView)view;
        this.interactor=new DataBaseInteractor();
    }

    @Override
    public void obtenerIMC() {
       Perfil p= interactor.obtenerPerfil();
       Registro r=interactor.obtenerUltimoRegistro();
       double IMC=FormulasUtils.getImc(r.getPeso(),p.getEstatura());
       IMCResult result=new IMCResult(p.getPesoInicio(),r.getPeso(),IMC,"Gordo muy gordo");
       view.onIMCResult(result);
    }

    public  interface  IMCView extends BaseView{
            void onIMCResult(IMCResult result);
    }
}
