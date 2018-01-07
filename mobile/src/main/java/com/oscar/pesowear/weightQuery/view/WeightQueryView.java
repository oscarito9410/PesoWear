package com.oscar.pesowear.weightQuery.view;

import com.github.mikephil.charting.data.Entry;
import com.oscar.maincore.MVP.View.BaseView;
import com.oscar.pesowear.profile.model.Profile;
import com.oscar.pesowear.record.model.Record;

import java.util.List;

/**
 * Created by oemy9 on 06/01/2018.
 */

public interface WeightQueryView extends BaseView {

    void setListRegistros (List <Record> listRecords);

    void setListEntriesChart (List <Entry> listEntries);

    void setPerfil (Profile perfil);
}
