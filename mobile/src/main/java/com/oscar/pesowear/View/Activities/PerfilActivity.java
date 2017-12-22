package com.oscar.pesowear.View.Activities;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.github.aakira.expandablelayout.ExpandableLayout;
import com.oscar.pesowear.Model.Perfil;
import com.oscar.pesowear.Presenter.PerfilPresenter;
import com.oscar.pesowear.Presenter.PerfilPresenterImpl;
import com.oscar.pesowear.R;
import com.oscar.pesowear.View.Base.BaseWeareableActivity;
import com.oscar.pesowear.View.Controls.AlturaPicker;
import com.oscar.pesowear.View.Controls.BasePicker;
import com.oscar.pesowear.View.Controls.ExpandableListenerCustom;
import com.oscar.pesowear.View.Controls.PesoPicker;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PerfilActivity extends BaseWeareableActivity implements PerfilPresenterImpl.ViewPerfil {
    private PerfilPresenter presenter;
    @BindView(R.id.imageArrowUnidad)
    ImageView imageArrowUnidad;
    @BindView(R.id.imageArrowActual)
    ImageView imageArrowActual;
    @BindView(R.id.imageArrowObjetivo)
    ImageView imageArrowObjetivo;
    @BindView(R.id.expandableUnidad)
    ExpandableLayout expandableUnidad;
    @BindView(R.id.expandableActual)
    ExpandableLayout expandableActual;
    @BindView(R.id.expandableObjetivo)
    ExpandableLayout expandableObjetivo;
    @BindView(R.id.expandableEstatura)
    ExpandableLayout expandableEstatura;
    @BindView(R.id.pesoPickerActual)
    PesoPicker pesoPickerActual;
    @BindView(R.id.pesoPickerObjetivo)
    PesoPicker pesoPickerObjetivo;
    @BindView(R.id.alturaPicker)
    AlturaPicker alturaPicker;
    @BindView(R.id.tvPesoActual)
    TextView tvPesoActual;
    @BindView(R.id.tvObjetivo)
    TextView tvObjetivo;
    @BindView(R.id.tvEstatura)
    TextView tvEstatura;
    @BindView(R.id.rdKg)
    RadioButton rdKg;
    @BindView(R.id.rdLibras)
    RadioButton rdLibras;
    @BindView(R.id.rdGroup)
    RadioGroup rdGroup;
    @BindView(R.id.imageArrowEstatura)
    ImageView imageArrowEstatura;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);
        setPresenter();
    }

    public void onClickUnidad(View view) {
        expandableUnidad.toggle();
    }


    public void onClickActual(View view) {
        expandableActual.toggle();
        presenter.setHideActual(expandableActual.isExpanded());
    }

    public void onClickObjetivo(View view) {
        expandableObjetivo.toggle();
        presenter.setHideObjetivo(expandableObjetivo.isExpanded());
    }
    public void onClickEstatura(View view) {
        expandableEstatura.toggle();
        presenter.setHideEstatura(expandableEstatura.isExpanded());
    }

    @Override
    public void initView() {
        ButterKnife.bind(this);
        setToolbar(R.string.perfil,true);
    }

    @Override
    public void setPresenter() {
            presenter=new PerfilPresenterImpl();
            presenter.register(this);
            presenter.obtenerPerfil();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_perfil,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);
        switch (item.getItemId()){
            case R.id.action_save:
                presenter.guardarPerfil();
                break;
        }
        return true;
    }


    @Override
    public void setListeners() {

        rdGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                if(checkedId==rdKg.getId()){
                    presenter.setUnidadMedida("Kg");
                }
                else if(checkedId==rdLibras.getId()){
                    presenter.setUnidadMedida("Lb");
                }
            }
        });

        expandableUnidad.setListener(new ExpandableListenerCustom(this.imageArrowUnidad));
        expandableEstatura.setListener(new ExpandableListenerCustom(this.imageArrowEstatura){
            @Override
            public void onClosed() {
                    alturaPicker.clearListener();
            }
            @Override
            public void onOpened() {
                alturaPicker.setAltura(presenter.getAltura());
                alturaPicker.setListener(new BasePicker.PickerChangedListener() {
                    @Override
                    public void onValueChanged(double value) {
                        if(!presenter.getIsHiddenEstatura()) {
                            presenter.setAltura((int) value);
                            tvEstatura.setText(getString(R.string.estatura_actual, String.valueOf(value), presenter.getUnidadMedidaAltura()));
                        }
                    }
                });
            }
        });

        expandableActual.setListener(new ExpandableListenerCustom(this.imageArrowActual)
        {
            @Override
            public void onClosed() {
                pesoPickerActual.clearListener();
            }

            @Override
            public void onOpened() {
                pesoPickerActual.setPeso(presenter.getPesoActual());
                pesoPickerActual.setListener(new BasePicker.PickerChangedListener() {
                    @Override
                    public void onValueChanged(double value) {
                        if(!presenter.getIsHiddenActual()) {
                            presenter.setPesoActual(value);
                            tvPesoActual.setText(getString(R.string.peso_actual, String.valueOf(value), presenter.getUnidadMedida()));
                        }
                    }
                });

            }
        });
        expandableObjetivo.setListener(new ExpandableListenerCustom(this.imageArrowUnidad){
            @Override
            public void onClosed() {
                pesoPickerObjetivo.clearListener();
            }

            @Override
            public void onOpened() {
                pesoPickerObjetivo.setPeso(presenter.getPesoObjetivo());
                pesoPickerObjetivo.setListener(new BasePicker.PickerChangedListener() {
                    @Override
                    public void onValueChanged(double value) {
                        if(!presenter.getIsHiddenObjetivo()) {
                            presenter.setPesoObjetivo(value);
                            tvObjetivo.setText(getString(R.string.peso_objetivo, String.valueOf(value), presenter.getUnidadMedida()));
                        }
                    }
                });
            }
        });
        pesoPickerActual.setUnidad(presenter.getUnidadMedida());
        pesoPickerObjetivo.setUnidad(presenter.getUnidadMedida());
        alturaPicker.setUnidad(presenter.getUnidadMedida());
    }

    @Override
    public Activity getActivityInstance() {
        return this;
    }

    @Override
    public void onPerfilSaved(int mensaje) {
        Toast.makeText(this, getString(mensaje), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onError(int mensaje) {
        Toast.makeText(this, getString(mensaje), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setPerfil(Perfil p) {
        pesoPickerObjetivo.setPeso(p.getPesoObjetivo());
        pesoPickerObjetivo.setUnidad(p.getUnidadMedida());
        pesoPickerActual.setPeso(p.getPesoInicio());
        pesoPickerActual.setUnidad(p.getUnidadMedida());
        alturaPicker.setAltura(p.getEstatura());
        tvObjetivo.setText(getString(R.string.peso_objetivo, String.valueOf(p.getPesoObjetivo()),p.getUnidadMedida()));
        tvEstatura.setText(getString(R.string.estatura_actual, String.valueOf(p.getEstatura()), presenter.getUnidadMedidaAltura()));
        tvPesoActual.setText(getString(R.string.peso_actual, String.valueOf(p.getPesoInicio()), presenter.getUnidadMedida()));
        if(p.getUnidadMedida().equalsIgnoreCase("kg"))
            rdKg.setChecked(true);
        else
            rdLibras.setChecked(true);
    }
}
