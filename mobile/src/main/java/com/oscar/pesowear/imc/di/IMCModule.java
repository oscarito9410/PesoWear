package com.oscar.pesowear.imc.di;

import com.oscar.pesowear.imc.presenter.IMCPresenter;
import com.oscar.pesowear.imc.presenter.IMCPresenterImpl;
import com.oscar.pesowear.imc.view.IMCView;

import javax.inject.Inject;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by oemy9 on 07/01/2018.
 */
@Module
public class IMCModule {

    public IMCView view;

    @Inject
    public IMCModule (IMCView view) {
        this.view = view;
    }

    @Provides
    public IMCView providesIMCView(){
        return view;
    }
    @Provides
    public IMCPresenter providesIMCPresenter(){
       IMCPresenterImpl imcPresenter=new  IMCPresenterImpl();
       imcPresenter.register(view);
       return  imcPresenter;
    }
}
