package com.oscar.pesowear.View.Fragments;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.oscar.pesowear.Data.Perfil;
import com.oscar.pesowear.Data.Registro;
import com.oscar.pesowear.Interactor.DataBaseInteractor;
import com.oscar.pesowear.Presenter.ConsultaPresenter;
import com.oscar.pesowear.Presenter.ConsultaPresenterImpl;
import com.oscar.pesowear.R;
import com.oscar.pesowear.View.Adapters.AdapterRegistro;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by oemy9 on 12/12/2017.
 */

public class FragmentListaRegistro extends FragmentBase implements ConsultaPresenterImpl.ConsultaView {
    private View rootView;
    private ConsultaPresenter presenter;

    @BindView(R.id.rlRegistro)
    RecyclerView rlRegistro;

    @BindView(R.id.lineChart)
    LineChart lineChart;

    private boolean hasUpdated=false;

    private AdapterRegistro adpt;

    @Override
    public void onStart() {
        super.onStart();
        Toast.makeText(getContext(), "Ok", Toast.LENGTH_SHORT).show();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView=inflater.inflate(R.layout.fragment_lista, container,false);
        setPresenter();
        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.obtenerRegistros();
    }

    @Override
    public void initView() {
        ButterKnife.bind(this,rootView);
    }

    @Override
    public void setPresenter() {
        presenter=new ConsultaPresenterImpl();
        presenter.register(this);
        presenter.obtenerRegistros();
    }

    @Override
    public void setListeners() {
    }

    @Override
    public Activity getActivityInstance() {
        return getActivity();
    }

    @Override
    public void setListRegistros(List<Registro> listRegistros) {
        adpt = new AdapterRegistro(getContext(), listRegistros);
        rlRegistro.setLayoutManager(new LinearLayoutManager(getContext()));
        rlRegistro.addItemDecoration(new DividerItemDecoration(getContext(),DividerItemDecoration.VERTICAL));
        rlRegistro.setAdapter(adpt);
    }

    @Override
    public void setListEntriesChart(List<Entry> listEntries) {
        if(listEntries.size()>0) {
            LineDataSet dataSet = new LineDataSet(listEntries, "Peso");
            dataSet.setDrawFilled(true);
            LineData lineData = new LineData(dataSet);
            dataSet.notifyDataSetChanged();
            lineChart.setData(lineData);
            lineChart.notifyDataSetChanged();
            lineChart.invalidate();
            hasUpdated=true;
        }
    }

    @Override
    public void setPerfil(Perfil perfil) {
        adpt.setPerfil(perfil);
    }
}
