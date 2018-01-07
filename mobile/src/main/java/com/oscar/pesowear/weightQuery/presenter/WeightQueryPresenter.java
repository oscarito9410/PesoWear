package com.oscar.pesowear.weightQuery.presenter;
import com.oscar.maincore.MVP.Presenter.BasePresenter;
import com.oscar.pesowear.record.model.Record;

import java.util.ArrayList;

/**
 * Created by oemy9 on 12/12/2017.
 */

public interface WeightQueryPresenter extends BasePresenter {
    void obtenerRegistros();
    void eliminarRegistros(ArrayList<Record> listRecords);
}
