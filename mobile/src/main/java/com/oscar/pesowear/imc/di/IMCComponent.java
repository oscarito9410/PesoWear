package com.oscar.pesowear.imc.di;

import com.oscar.pesowear.imc.view.FragmentIMC;
import com.oscar.pesowear.utils.di.SharedPreferencesModule;

import dagger.Component;

/**
 * Created by oemy9 on 07/01/2018.
 */
@Component(modules = {
        IMCModule.class, SharedPreferencesModule.class
})
public interface IMCComponent {
  void inject(FragmentIMC ft);
}
