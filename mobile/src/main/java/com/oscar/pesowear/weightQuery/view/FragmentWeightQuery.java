package com.oscar.pesowear.weightQuery.view;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.oscar.pesowear.profile.model.Profile;
import com.oscar.pesowear.record.model.Record;
import com.oscar.pesowear.base.FragmentBase;
import com.oscar.pesowear.weightQuery.adapter.AdapterWeghtQuery;
import com.oscar.pesowear.weightQuery.presenter.WeightQueryPresenter;
import com.oscar.pesowear.weightQuery.presenter.WeightQueryPresenterImpl;
import com.oscar.pesowear.R;
import com.oscar.pesowear.base.BaseWeareableActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by oemy9 on 12/12/2017.
 */

public class FragmentWeightQuery extends FragmentBase implements WeightQueryView, AdapterWeghtQuery.RegistroListener {
    private View rootView;
    private WeightQueryPresenter presenter;
    private boolean isDeleting=false;
    @BindView(R.id.rlRegistro)
    RecyclerView rlRegistro;
    @BindView(R.id.lineChart)
    LineChart lineChart;
    private AdapterWeghtQuery adpt;    private LineDataSet dataSet;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView=inflater.inflate(R.layout.fragment_lista, container,false);
        setHasOptionsMenu(true);
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
        presenter=new WeightQueryPresenterImpl();
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
    public void setListRegistros(List<Record> listRecords) {
        adpt = new AdapterWeghtQuery(getContext(), listRecords);
        adpt.setRegistroListener(this);
        rlRegistro.setLayoutManager(new LinearLayoutManager(getContext()));
        rlRegistro.addItemDecoration(new DividerItemDecoration(getContext(),DividerItemDecoration.VERTICAL));
        rlRegistro.setAdapter(adpt);
    }

    @Override
    public void setListEntriesChart(List<Entry> listEntries) {
        if(listEntries!=null && (!listEntries.isEmpty())) {
            dataSet = new LineDataSet(listEntries, getString(R.string.peso));
            dataSet.setDrawFilled(true);
            dataSet.setColor(ContextCompat.getColor(getContext(),R.color.colorPrimaryDark));
            dataSet.setFillColor(ContextCompat.getColor(getContext(),R.color.colorPrimary));
            dataSet.setCircleColor(ContextCompat.getColor(getContext(),R.color.colorPrimaryDark));
            LineData lineData = new LineData(dataSet);
            lineChart.setData(lineData);
            lineChart.animateX(500);
            lineChart.notifyDataSetChanged();
            lineChart.invalidate();
        }
    }

    @Override
    public void setPerfil(Profile perfil) {
        adpt.setPerfil(perfil);
    }

    @Override
    public void onLongClickDeleteListener(boolean isDelete) {
        isDeleting=isDelete;
        getActivity().invalidateOptionsMenu();
    }

    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);
        MenuItem item=menu.findItem(R.id.action_delete);
        item.setVisible(isDeleting);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);
        switch (item.getItemId()){
            case R.id.action_delete:
                BaseWeareableActivity act=(BaseWeareableActivity)getActivity();
                act.showDialogOk(getContext(), "¿Deseas eliminar?", "Los registros seleccionados serán eliminados",
                        (dialogInterface, i) -> {
                            presenter.eliminarRegistros((ArrayList)adpt.getListRecords());
                        });
                break;
        }
        return false;
    }

    @Override
    public void onItemDeleteSelected(int count) {

    }
}
