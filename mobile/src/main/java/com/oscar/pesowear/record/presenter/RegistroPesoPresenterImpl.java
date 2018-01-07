package com.oscar.pesowear.record.presenter;

import com.oscar.maincore.MVP.Presenter.BasePresenterImpl;
import com.oscar.maincore.MVP.View.BaseView;
import com.oscar.pesowear.base.DataBaseInteractor;
import com.oscar.pesowear.R;
import com.oscar.pesowear.record.view.RecordView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import static com.oscar.maincore.Utils.Constants.DATE_FORMAT_PICKER;

/**
 * Created by oemy9 on 11/12/2017.
 */

public class RegistroPesoPresenterImpl extends BasePresenterImpl implements RecordPesoPresenter {

    private final SimpleDateFormat dateFormat;
    private Calendar selectedFecha;
    private String fechaString;
    private DataBaseInteractor interactor;
    private RecordView view;


    public RegistroPesoPresenterImpl () {
        dateFormat = new SimpleDateFormat(DATE_FORMAT_PICKER, new Locale("es"));
    }

    @Override
    public void register (BaseView view) {
        super.register(view);
        this.view = (RecordView) view;
        interactor = new DataBaseInteractor();
    }

    @Override
    public void obtenerPesoInicial () {
        if (interactor.obtenerLastRegistro() != null) {
            view.pesoInicial(interactor.obtenerLastRegistro().getPeso());
        } else if (interactor.obtenerPerfil() != null) {
            view.pesoInicial(interactor.obtenerPerfil().getPesoInicio());
        }
    }

    @Override
    public void agregarPeso (Double peso, String nota) {
        if (selectedFecha != null) {
            interactor.agregarRegistroPeso(peso, selectedFecha, nota);
            view.onSuccessRegistro(R.string.registro_guardado);
        } else {
            view.setErrorFecha(R.string.error_prueba);
        }
    }

    @Override
    public void setFecha (Calendar selectedFecha) {
        if (selectedFecha != null) {
            this.selectedFecha = selectedFecha;
            this.fechaString = dateFormat.format(selectedFecha.getTime());
            view.setFechaValue(this.fechaString);
        }
    }

    @Override
    public void setFecha (int year, int month, int dayOfMonth) {
        this.selectedFecha = Calendar.getInstance();
        this.selectedFecha.set(year, month, dayOfMonth);
        this.fechaString = dateFormat.format(selectedFecha.getTime());
        this.view.setFechaValue(this.fechaString);
    }

}
