package com.oscar.maincore.MVP.Presenter;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.oscar.maincore.MVP.View.BaseView;


/**
 * Created by oscar on 19/09/17.
 */

public interface BasePresenter {
    <T extends BaseView> BaseView getView();
    void onCreate(@Nullable Bundle savedInstanceState);
    void register(BaseView view);
    void unregister(BaseView view);
    void setArguments(Intent intent);
}
