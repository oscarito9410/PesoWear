package com.oscar.pesowear.View.Fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.oscar.pesowear.Model.Perfil;
import com.oscar.pesowear.Model.Registro;
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
    private int countFired=0;

    @BindView(R.id.rlRegistro)
    RecyclerView rlRegistro;

    @BindView(R.id.lineChart)
    LineChart lineChart;


    private AdapterRegistro adpt;
    private LineDataSet dataSet;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView=inflater.inflate(R.layout.fragment_lista, container,false);
        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        setPresenter();
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
            if(dataSet==null) {
                dataSet = new LineDataSet(listEntries, "Peso");
            }
            else{
                dataSet.clear();
                for(Entry e: listEntries){
                    dataSet.addEntry(e);
                    dataSet.notifyDataSetChanged();
                }
            }
            dataSet.setDrawFilled(true);
            dataSet.setColor(ContextCompat.getColor(getContext(),R.color.colorPrimaryDark));
            dataSet.setFillColor(ContextCompat.getColor(getContext(),R.color.colorPrimary));
            dataSet.setCircleColor(ContextCompat.getColor(getContext(),R.color.colorPrimaryDark));
            LineData lineData = new LineData(dataSet);
            lineChart.setData(lineData);
            if(countFired==0) {
                lineChart.animateX(500);
                countFired++;
            }
            lineChart.notifyDataSetChanged();
            lineChart.invalidate();
        }
    }

    @Override
    public void setPerfil(Perfil perfil) {
        adpt.setPerfil(perfil);
    }
}
