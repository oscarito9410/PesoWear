package com.oscar.pesowear.weightQuery.presenter;

import com.github.mikephil.charting.data.Entry;
import com.oscar.maincore.MVP.Presenter.BasePresenterImpl;
import com.oscar.maincore.MVP.View.BaseView;
import com.oscar.pesowear.record.model.Record;
import com.oscar.pesowear.base.DataBaseInteractor;
import com.oscar.pesowear.weightQuery.view.WeightQueryView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by oemy9 on 12/12/2017.
 */

public class WeightQueryPresenterImpl extends BasePresenterImpl implements WeightQueryPresenter {
    private static final String TAG = "WeightQueryPresenterImpl";
    private WeightQueryView view;
    private List <Entry> listEntries = new ArrayList <>();
    private DataBaseInteractor interactor;

    @Override
    public void register (BaseView view) {
        super.register(view);
        this.view = (WeightQueryView) view;
        this.listEntries = new ArrayList <>();
        this.interactor = new DataBaseInteractor();
    }

    @Override
    public void obtenerRegistros () {
        listEntries.clear();
        List <Record> listRecords = interactor.obtenerListRegistros();
        Collections.reverse(listRecords);
        for (int j = 0; j < listRecords.size(); j++) {
            listEntries.add(new Entry(j, (float) listRecords.get(j).getPeso()));
        }
        if (view != null) {
            view.setListRegistros(interactor.obtenerListRegistros());
            view.setListEntriesChart(listEntries);
            view.setPerfil(interactor.obtenerPerfil());
        }
    }

    @Override
    public void eliminarRegistros (ArrayList <Record> listRecords) {
        Observable.fromIterable(listRecords).filter(registro -> registro.isChecked()).toList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(registros -> {
                    if (registros != null) {
                        if (interactor.eliminarRegistros((ArrayList) registros)) {
                            Observable.timer(500, TimeUnit.MICROSECONDS)
                                    .subscribeOn(Schedulers.io())
                                    .observeOn(AndroidSchedulers.mainThread())
                                    .subscribe(aLong -> obtenerRegistros());
                        }
                    }
                });
    }


}
