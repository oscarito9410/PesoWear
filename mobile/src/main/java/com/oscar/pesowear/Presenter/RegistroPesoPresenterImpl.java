package com.oscar.pesowear.Presenter;
import android.support.annotation.StringRes;
import com.oscar.maincore.MVP.Presenter.BasePresenterImpl;
import com.oscar.maincore.MVP.View.BaseView;
import com.oscar.pesowear.Interactor.DataBaseInteractor;
import com.oscar.pesowear.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import static com.oscar.maincore.Utils.Constants.DATE_FORMAT_PICKER;

/**
 * Created by oemy9 on 11/12/2017.
 */

public class RegistroPesoPresenterImpl extends BasePresenterImpl implements RegistroPesoPresenter {

    private final SimpleDateFormat dateFormat;
    private Calendar selectedFecha;
    private String  fechaString;
    private DataBaseInteractor interactor;
    private RegistroView view;



    public RegistroPesoPresenterImpl() {
        dateFormat=new SimpleDateFormat(DATE_FORMAT_PICKER, new Locale("es"));
    }

    @Override
    public void register(BaseView view) {
        super.register(view);
        this.view=(RegistroView)view;
        interactor=new DataBaseInteractor();
    }

    @Override
    public void agregarPeso(Double peso, String nota) {
        if (selectedFecha != null) {
            interactor.agregarRegistroPeso(peso,selectedFecha,nota);
        }
        else {
                view.setErrorFecha(R.string.error_prueba);
        }
    }

    @Override
    public void setFecha(Calendar selectedFecha) {
        if(selectedFecha!=null) {
            this.selectedFecha = selectedFecha;
            this.fechaString = dateFormat.format(selectedFecha.getTime());
            view.setFechaValue(this.fechaString);
        }
    }

    @Override
    public void setFecha(int year, int month, int dayOfMonth) {
       this.selectedFecha=Calendar.getInstance();
       this.selectedFecha.set(year,month,dayOfMonth);
       this.fechaString = dateFormat.format(selectedFecha.getTime());
       this.view.setFechaValue(this.fechaString);
    }

    public interface RegistroView extends BaseView
    {
        void setErrorFecha(@StringRes int error);
        void setFechaValue(String value);
    }
}
