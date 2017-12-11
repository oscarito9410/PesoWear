package com.oscar.maincore.MVP.View;
import android.support.annotation.StringRes;

/**
 * Created by oemy9 on 08/09/2017.
 */

public interface BaseViewServer extends BaseView {
    void showProgress();
    void hideProgress();
    void showAlertError(@StringRes int mensaje);
    void showAlertError(String mensaje);
}
