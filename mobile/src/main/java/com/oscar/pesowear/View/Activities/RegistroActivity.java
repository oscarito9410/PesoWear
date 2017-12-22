package com.oscar.pesowear.View.Activities;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.DatePicker;
import android.widget.Toast;

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

public class RegistroActivity extends BaseWeareableActivity implements
        RegistroPesoPresenterImpl.RegistroView,
        DatePickerDialog.OnDateSetListener,
        InputBox.inputBoxCallBack {

    private InputBox inputBox;
    private RegistroPesoPresenter presenter;
    private Calendar c=Calendar.getInstance();


    @Nullable @BindView(R.id.bubbleCalendar)
    BubbleLayout bubbleCalendar;
    @Nullable @BindView(R.id.bubbleNotas)
    BubbleLayout bubbleNotas;
    @Nullable @BindView(R.id.pesoPicker)
    PesoPicker pesoPicker;
    private DatePickerDialog datePickerDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        setPresenter();
        setToolBar("Registro de peso",true);
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
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_registro,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);
        switch (item.getItemId()){
            case R.id.action_save:
                presenter.agregarPeso(pesoPicker.getPeso(),bubbleNotas.isTextChanged()?bubbleNotas.getText(): null);
                break;
        }
        return true;
    }

    @Override
    public void setPresenter() {
        this.presenter=new RegistroPesoPresenterImpl();
        this.presenter.register(this);
        this.presenter.setFecha(c);
        this.presenter.obtenerPesoInicial();
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
    public void onSuccessRegistro(int mensaje) {
        Toast.makeText(this, getString(mensaje), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setErrorFecha(@StringRes  int error) {
    }

    @Override
    public void setFechaValue(String value) {
            bubbleCalendar.setText(value);
    }

    @Override
    public void pesoInicial(double peso) {
            pesoPicker.setPeso(peso);
    }
}
