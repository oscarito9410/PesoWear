package com.oscar.pesowear.View.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.oscar.pesowear.Interactor.DataBaseInteractor;
import com.oscar.pesowear.R;
import com.oscar.pesowear.View.Adapters.AdapterRegistro;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by oemy9 on 12/12/2017.
 */

public class FragmentListaRegistro extends FragmentBase {
    private View rootView;

    @BindView(R.id.rlRegistro)
    RecyclerView rlRegistro;

    @Override
    public void onStart() {
        super.onStart();
        Toast.makeText(getContext(), "Ok", Toast.LENGTH_SHORT).show();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView=inflater.inflate(R.layout.fragment_lista, container,false);
        ButterKnife.bind(this,rootView);
        DataBaseInteractor interactor=new DataBaseInteractor();
        AdapterRegistro adpt = new AdapterRegistro(getContext(), interactor.obtenerListRegistros());
        rlRegistro.setLayoutManager(new LinearLayoutManager(getContext()));
        rlRegistro.addItemDecoration(new DividerItemDecoration(getContext(),DividerItemDecoration.VERTICAL));
        rlRegistro.setAdapter(adpt);
        return rootView;
    }




}
