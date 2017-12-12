package com.oscar.pesowear.View.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;

/**
 * Created by oemy9 on 12/12/2017.
 */

public class FragmentBase extends Fragment {
    public static final String TAG = "FragmentBase";

    public static FragmentBase getInstance(Bundle arguments){
        FragmentBase ft=new FragmentBase();
        if(arguments!=null){
            ft.setArguments(arguments);
        }
        return ft;
    }

}
