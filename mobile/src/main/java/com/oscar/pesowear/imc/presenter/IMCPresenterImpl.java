package com.oscar.pesowear.imc.presenter;

import com.oscar.maincore.MVP.Presenter.BasePresenterImpl;
import com.oscar.maincore.MVP.View.BaseView;
import com.oscar.maincore.Utils.Constants;
import com.oscar.maincore.Utils.EstatusDescription;
import com.oscar.maincore.Utils.FormulasUtils;
import com.oscar.pesowear.ApplicationBase;
import com.oscar.pesowear.imc.model.IMCResult;
import com.oscar.pesowear.profile.model.Profile;
import com.oscar.pesowear.record.model.Record;
import com.oscar.pesowear.base.DataBaseInteractor;

import java.text.SimpleDateFormat;

/**
 * Created by oemy9 on 19/12/2017.
 */

public class IMCPresenterImpl  extends BasePresenterImpl implements  IMCPresenter{

    private IMCView view;
    private DataBaseInteractor interactor;
    private SimpleDateFormat dt=new SimpleDateFormat(Constants.DATE_FORMAT_INICIO);


    @Override
    public void register(BaseView view) {
        super.register(view);
        this.view=(IMCView)view;
        this.interactor=new DataBaseInteractor();
    }

    @Override
    public void obtenerIMC() {
       Profile p= interactor.obtenerPerfil();
       Record r=interactor.obtenerUltimoRegistro();
       if(p!=null) {
               double IMC = FormulasUtils.getImc(r!=null?r.getPeso(): p.getPesoInicio(), p.getEstatura());
               EstatusDescription description=FormulasUtils.getDescripcionByIMC(ApplicationBase.getIntance().getApplicationContext(),IMC);
               IMCResult result = new IMCResult(p.getPesoInicio(), r!=null?r.getPeso(): p.getPesoObjetivo(), IMC);
               result.setPesoObjetivo(p.getPesoObjetivo());
               result.setEstatusDescription(description);
               result.setUnidadMedida(p.getUnidadMedida());
               result.setFechaIncio(dt.format(p.getFechaInicio()));
               view.onIMCResult(result);
       }
    }

    public  interface  IMCView extends BaseView{
            void onIMCResult(IMCResult result);
    }
}
