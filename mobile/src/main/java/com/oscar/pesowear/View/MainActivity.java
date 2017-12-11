package com.oscar.pesowear.View;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.wearable.MessageApi;
import com.google.android.gms.wearable.Node;
import com.google.android.gms.wearable.NodeApi;
import com.google.android.gms.wearable.Wearable;
import com.oscar.maincore.Utils.Constants;
import com.oscar.maincore.Data.RegistroCore;
import com.oscar.maincore.Utils.Utilerias;
import com.oscar.pesowear.Data.Registro;
import com.oscar.pesowear.R;
import com.raizlabs.android.dbflow.sql.language.SQLite;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainActivity extends BaseWeareableActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




/*        for(int j=0;j<100;j++) {
            Registro registro = new Registro();
            registro.setPeso(100);
            registro.setFecha(new Date());
            registro.setNotas("Prueba");
            registro.save();

        }

        List<Registro> registros = SQLite.select().from(Registro.class).queryList();

        for (Registro r: registros){
            Log.d(TAG, "onCreate:"+r.getNotas());
        }*/

    }


}
