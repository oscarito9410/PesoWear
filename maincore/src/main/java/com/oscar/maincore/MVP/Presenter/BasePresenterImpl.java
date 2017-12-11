package com.oscar.maincore.MVP.Presenter;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import com.oscar.maincore.MVP.View.BaseView;


/**
 * Created by oemy9 on 16/09/2017.
 */

public class BasePresenterImpl implements BasePresenter {

    private BaseView view;
    @Override
    public <T extends BaseView> BaseView getView() {
        return view;
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
    }

    @Override
    public void register(BaseView view) {
        this.view=view;
        this.view.initView();
    }

    @Override
    public void unregister(BaseView view) {
    }

    @Override
    public void setArguments(Intent intent) {

    }

}
