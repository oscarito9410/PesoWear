package com.oscar.pesowear.record.view;

import android.support.annotation.StringRes;

import com.oscar.maincore.MVP.View.BaseView;

/**
 * Created by oemy9 on 06/01/2018.
 */

public interface RecordView extends BaseView {

    void onSuccessRegistro (@StringRes int mensaje);

    void setErrorFecha (@StringRes int error);

    void setFechaValue (String value);

    void pesoInicial (double peso);
}
