package com.oscar.pesowear.home.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.Menu;
import android.view.MenuItem;

import com.oscar.pesowear.R;
import com.oscar.pesowear.record.view.RecordActivity;
import com.oscar.pesowear.base.BaseWeareableActivity;
import com.oscar.pesowear.imc.view.FragmentIMC;
import com.oscar.pesowear.weightQuery.view.FragmentWeightQuery;

public class HomeActivity extends BaseWeareableActivity implements BottomNavigationView.OnNavigationItemSelectedListener, HomeView {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);
        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(this);
        navigateIMC();
        setToolBar(getString(R.string.title_home),false);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        super.onNavigationItemSelected(item);
        switch (item.getItemId()){
            case R.id.navigation_home:
                navigateIMC();
                break;
            case R.id.navigation_wight_list:
                navigateWeighList();
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
                startActivity(new Intent(this, RecordActivity.class));
                break;
        }
        return false;
    }

    public void navegarFragmento(Fragment fragment, String  Tag, boolean backStack, boolean animation){
        FragmentTransaction ft=getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.container,fragment);
        if(backStack){
            ft.addToBackStack(Tag);
        }
        ft.commitAllowingStateLoss();
    }

    @Override
    public void navigateIMC() {
        navegarFragmento(new FragmentIMC(), FragmentIMC.TAG,false,false);
    }

    @Override
    public void navigateWeighList() {
        navegarFragmento(new FragmentWeightQuery(), FragmentWeightQuery.TAG,false,false);
    }
}
