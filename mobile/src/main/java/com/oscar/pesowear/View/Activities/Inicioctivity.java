package com.oscar.pesowear.View.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.Menu;
import android.view.MenuItem;

import com.oscar.pesowear.R;
import com.oscar.pesowear.View.Base.BaseWeareableActivity;
import com.oscar.pesowear.View.Fragments.FragmentListaRegistro;

public class Inicioctivity extends BaseWeareableActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(this);
        navegarFragmento(new FragmentListaRegistro(),FragmentListaRegistro.TAG,false,false);
        setToolBar("Inicio",false);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.navigation_home:
                navegarFragmento(new FragmentListaRegistro(),FragmentListaRegistro.TAG,false,false);
                break;
        }
        return true;
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_home,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_add:
                startActivity(new Intent(this, RegistroActivity.class));
                break;
            case R.id.action_profile:
                startActivity(new Intent(this,PerfilActivity.class));
                break;
        }
        return true;
    }

    public void navegarFragmento(Fragment fragment, String  Tag, boolean backStack, boolean animation){
        FragmentTransaction ft=getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.container,fragment);
        if(backStack){
            ft.addToBackStack(Tag);
        }
        ft.commit();
    }

}
