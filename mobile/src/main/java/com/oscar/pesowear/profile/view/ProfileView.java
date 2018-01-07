package com.oscar.pesowear.profile.view;

import android.support.annotation.StringRes;

import com.oscar.maincore.MVP.View.BaseView;
import com.oscar.pesowear.profile.model.Profile;

/**
 * Created by oemy9 on 06/01/2018.
 */
public interface ProfileView extends BaseView {
    void onPerfilSaved (@StringRes int mensaje);

    void onError (@StringRes int mensaje);

    void setPerfil (Profile p);
}
