package com.oscar.pesowear.View.Fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.anastr.speedviewlib.SpeedView;
import com.numetriclabz.numandroidcharts.ChartData;
import com.numetriclabz.numandroidcharts.GaugeChart;
import com.oscar.pesowear.Data.IMCResult;
import com.oscar.pesowear.Presenter.IMCPresenter;
import com.oscar.pesowear.Presenter.IMCPresenterImpl;
import com.oscar.pesowear.R;
import com.oscar.pesowear.View.Controls.GuageChartM;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by oemy9 on 19/12/2017.
 */

public class FragmentIMC extends FragmentBase implements IMCPresenterImpl.IMCView {
    private View rootView;
    private IMCPresenter presenter;

    @BindView(R.id.speedView)
    SpeedView speedView;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView=inflater.inflate(R.layout.fragment_imc,container,false);
        setPresenter();
        return rootView;
    }


    @Override
    public void onIMCResult(IMCResult result) {
        speedView.setSpeedAt(Float.parseFloat(result.getImc().toString()));
    }

    @Override
    public void initView() {
        ButterKnife.bind(this,rootView);
    }

    @Override
    public void setPresenter() {
        presenter=new IMCPresenterImpl();
        presenter.register(this);
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
