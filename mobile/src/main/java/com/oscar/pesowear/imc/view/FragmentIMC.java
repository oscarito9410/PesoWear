package com.oscar.pesowear.imc.view;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.github.anastr.speedviewlib.SpeedView;
import com.oscar.pesowear.imc.di.DaggerIMCComponent;
import com.oscar.pesowear.imc.di.IMCModule;
import com.oscar.pesowear.imc.model.IMCResult;
import com.oscar.pesowear.R;
import com.oscar.pesowear.base.FragmentBase;
import com.oscar.pesowear.imc.presenter.IMCPresenter;
import com.oscar.pesowear.imc.presenter.IMCPresenterImpl;
import com.oscar.pesowear.utils.di.SharedPreferencesModule;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by oemy9 on 19/12/2017.
 */

public class FragmentIMC extends FragmentBase implements IMCView {
    private View rootView;
    @Inject
    IMCPresenter presenter;

    @BindView(R.id.speedView)
    SpeedView speedView;
    @BindView(R.id.tvPesoActual)
    TextView tvPesoActual;
    @BindView(R.id.tvPesoObjetivo)
    TextView tvPesoObjetivo;
    @BindView(R.id.tvEstatusIMC)
    TextView tvEstatusIMC;
    @BindView(R.id.tvFechaPesoActual)
    TextView tvFechaPesoActual;
    @BindView(R.id.tvPesoRestante)
    TextView tvPesoRestante;
    @BindView(R.id.tvUnidad)
    TextView tvUnidad;
    @BindView(R.id.tvUnidadObjetivo)
    TextView tvUnidadObjetivo;



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView=inflater.inflate(R.layout.fragment_imc,container,false);
        DaggerIMCComponent.builder()
                .sharedPreferencesModule(new SharedPreferencesModule(getContext()))
                .iMCModule(new IMCModule(this)).build().inject(this);
        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        setPresenter();
    }

    @Override
    public void onIMCResult(IMCResult result) {
        speedView.setSpeedAt(0);
        speedView.speedTo(Float.parseFloat(result.getImc().toString()),500);
        tvPesoActual.setText(String.valueOf(result.getPesoActual()));
        tvPesoObjetivo.setText(String.valueOf(result.getPesoObjetivo()));
        tvEstatusIMC.setText(result.getEstatusDescription()!=null?result.getEstatusDescription().getDescription():"");
        tvEstatusIMC.setTextColor(result.getEstatusDescription()!=null?  ContextCompat.getColor(getContext(),result.getEstatusDescription().getColor()): Color.BLACK);
        tvFechaPesoActual.setText(result.getFechaIncio());
        tvUnidad.setText(result.getUnidadMedida());
        tvUnidadObjetivo.setText(result.getUnidadMedida());
        tvPesoRestante.setText(getString(R.string.faltan,String.valueOf(result.getPesoRestante()),result.getUnidadMedida()));
    }

    @Override
    public void initView() {
        ButterKnife.bind(this,rootView);
    }

    @Override
    public void setPresenter() {
        presenter.obtenerIMC();
    }

    @Override
    public void setListeners() {
    }

    @Override
    public Activity getActivityInstance() {
        return this.getActivity();
    }
}
