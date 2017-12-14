package com.oscar.pesowear.View.Activities;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.aakira.expandablelayout.ExpandableLayout;
import com.oscar.maincore.MVP.View.BaseView;
import com.oscar.pesowear.Presenter.PerfilPresenter;
import com.oscar.pesowear.Presenter.PerfilPresenterImpl;
import com.oscar.pesowear.R;
import com.oscar.pesowear.View.Controls.AlturaPicker;
import com.oscar.pesowear.View.Controls.BasePicker;
import com.oscar.pesowear.View.Controls.ExpandableListenerCustom;
import com.oscar.pesowear.View.Controls.PesoPicker;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PerfilActivity extends AppCompatActivity implements BaseView {
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
    }

    @Override
    public void setPresenter() {
            presenter=new PerfilPresenterImpl();
            presenter.register(this);
    }

    @Override
    public void setListeners() {
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
                            tvEstatura.setText(getString(R.string.estatura_actual, String.valueOf(value), presenter.getUnidadMedida()));
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


}
