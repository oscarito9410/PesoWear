package com.oscar.pesowear.View;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.annotation.StringRes;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.DatePicker;

import com.oscar.pesowear.Presenter.RegistroPesoPresenter;
import com.oscar.pesowear.Presenter.RegistroPesoPresenterImpl;
import com.oscar.pesowear.R;
import com.oscar.pesowear.View.Base.BaseWeareableActivity;
import com.oscar.pesowear.View.Controls.BubbleLayout;
import com.oscar.pesowear.View.Controls.InputBox;
import com.oscar.pesowear.View.Controls.PesoPicker;

import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseWeareableActivity implements
        RegistroPesoPresenterImpl.RegistroView,
        DatePickerDialog.OnDateSetListener,
        InputBox.inputBoxCallBack {

    private InputBox inputBox;
    private RegistroPesoPresenter presenter;
    private Calendar c=Calendar.getInstance();


    @BindView(R.id.bubbleCalendar)
    BubbleLayout bubbleCalendar;
    @BindView(R.id.bubbleNotas)
    BubbleLayout bubbleNotas;
    @BindView(R.id.pesoPicker)
    PesoPicker pesoPicker;
    @BindView(R.id.btnRegistrar)
    Button btnRegistrar;
    private DatePickerDialog datePickerDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setPresenter();
    }

    @Override
    public void initView(){
        ButterKnife.bind(this);
        inputBox=new InputBox(this);
        inputBox.setTitle("Ingresar nota");
        inputBox.setErrorText("Por favor ingresa una nota");
        datePickerDialog=new DatePickerDialog(this,this, c.get(Calendar.YEAR),c.get(Calendar.MONTH),c.get(Calendar.DAY_OF_MONTH));
    }

    @Override
    public void setPresenter() {
        this.presenter=new RegistroPesoPresenterImpl();
        this.presenter.register(this);
        this.presenter.setFecha(c);
    }

    @Override
    public void setListeners() {
        inputBox.setCallBack(this);
    }

    @Override
    public Activity getActivityInstance() {
        return this;
    }

    @OnClick(R.id.bubbleCalendar)
    public void initCalendar(){
        if(!datePickerDialog.isShowing()) {
            datePickerDialog.show();
        }
    }


    @OnClick(R.id.bubbleNotas)
    public void initNotas(){
        inputBox.showAsync();
    }
    @OnClick(R.id.btnRegistrar)
    public void initRegistro(){
        presenter.agregarPeso(pesoPicker.getPeso(),bubbleNotas.getText());
    }


    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth){
        presenter.setFecha(year,month,dayOfMonth);
    }

    @Override
    public void OnResult(String result) {
        if(!TextUtils.isEmpty(result)){
            bubbleNotas.setText(result);
            inputBox.setTextDefault(result);
        }
    }

    @Override
    public void OnCancel() {

    }

    @Override
    public void setErrorFecha(@StringRes  int error) {

    }

    @Override
    public void setFechaValue(String value) {
            bubbleCalendar.setText(value);
    }
}
