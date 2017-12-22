package com.oscar.pesowear.View.Base;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.wearable.MessageApi;
import com.google.android.gms.wearable.Node;
import com.google.android.gms.wearable.NodeApi;
import com.google.android.gms.wearable.Wearable;
import com.oscar.pesowear.R;
import com.oscar.pesowear.View.Activities.PerfilActivity;

/**
 * Created by oemy9 on 09/12/2017.
 */

public class BaseWeareableActivity extends AppCompatActivity implements  GoogleApiClient.ConnectionCallbacks,  GoogleApiClient.OnConnectionFailedListener, NavigationView.OnNavigationItemSelectedListener {
    private static final String TAG = "BaseWeareableActivity";
    private GoogleApiClient googleClient;
    private Toolbar toolbar;
    private DrawerLayout drawer;
    private String currentTitle;
    private ActionBarDrawerToggle toggle;

    protected Toolbar setToolbar(@StringRes int resource, boolean setDefaultHome){
      return setToolBar(getString(resource),setDefaultHome);
    }

    protected   Toolbar setToolBar(String texto, boolean setDefaultHome){
        this.toolbar = (Toolbar) findViewById(R.id.toolbar);
        this.currentTitle=texto;
        if(toolbar!=null) {
            setSupportActionBar(toolbar);
            if (getSupportActionBar() != null && setDefaultHome) {
                getSupportActionBar().setDisplayHomeAsUpEnabled(true);
                getSupportActionBar().setDisplayShowHomeEnabled(true);
            }
        }
        return this.toolbar;
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        this.drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer != null) {
            Toolbar toolbar = setToolBar(this.currentTitle, true);
            toggle = new ActionBarDrawerToggle(this, drawer, toolbar,R.string.abierto,R.string.cerrado);
            drawer.setDrawerListener(toggle);
            toggle.syncState();
            NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
            navigationView.setNavigationItemSelectedListener(this);
            View headerView = navigationView.getHeaderView(0);
        }
    }

    public  void showDialogOk(Context context, String title, String message, DialogInterface.OnClickListener okListener) {
        new AlertDialog.Builder(context)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton(context.getString(R.string.aceptar), okListener)
                .setNegativeButton(context.getString(R.string.cancelar), okListener)
                .create()
                .show();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.nav_configuracion:
                startActivity(new Intent(this, PerfilActivity.class));
                break;
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case android.R.id.home:
                finish();
                break;

        }
        return super.onOptionsItemSelected(item);
    }



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        googleClient = new GoogleApiClient.Builder(this)
                .addApi(Wearable.API)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .build();
    }

    @Override
    protected void onStart() {
        super.onStart();
        googleClient.connect();
    }
    @Override
    public void onStop(){
        if (null != googleClient && googleClient.isConnected()) {
            googleClient.disconnect();
        }
        super.onStop();
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        /*List<Registro> registros = SQLite.select().from(Registro.class).queryList();
        List<RegistroCore>registroCores=new ArrayList<>();
        for (Registro r: registros){
            registroCores.add(r.toCoreElement());
        }
        new SendToDataLayerThread(Constants.PATH_WEAR, Utilerias.getJson(registroCores)).start();*/
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
    class SendToDataLayerThread extends Thread {
        String path;
        String message;

        SendToDataLayerThread(String p, String msg) {
            path = p;
            message = msg;
        }

        public void run() {
            NodeApi.GetConnectedNodesResult nodes = Wearable.NodeApi.getConnectedNodes(googleClient).await();
            for (Node node : nodes.getNodes()) {
                MessageApi.SendMessageResult result = Wearable.MessageApi.sendMessage(googleClient, node.getId(), path, message.getBytes()).await();
                if (result.getStatus().isSuccess()) {
                    Log.v("myTag", "Message: {" + message + "} sent to: " + node.getDisplayName());
                } else {
                    Log.v("myTag", "ERROR: failed to send Message");
                }
            }
        }
    }

}
