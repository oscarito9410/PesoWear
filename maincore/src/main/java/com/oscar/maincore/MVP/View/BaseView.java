package com.oscar.maincore.MVP.View;
import android.app.Activity;

public interface BaseView {
    void initView();
    void setPresenter();
    void setListeners();
    Activity getActivityInstance();
}
