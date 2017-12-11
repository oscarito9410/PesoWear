package com.oscar.pesowear;
import android.util.Log;
import android.widget.Toast;
import com.google.android.gms.wearable.MessageEvent;
import com.google.android.gms.wearable.WearableListenerService;
import com.oscar.maincore.Data.RegistroCore;
import com.oscar.maincore.Utils.Constants;
import com.oscar.maincore.Utils.SessionManager;
import com.oscar.maincore.Utils.Utilerias;
import java.util.List;

/**
 * Created by oemy9 on 09/12/2017.
 */

public class DataLayerListenerService extends WearableListenerService {
    private static final String TAG = "DataLayerListenerServic";
    @Override
    public void onMessageReceived(MessageEvent messageEvent) {
        if (messageEvent.getPath().equals(Constants.PATH_WEAR)) {
            final String message = new String(messageEvent.getData());
            SessionManager manager=new SessionManager(this);
            manager.add(Constants.JSON_BD,message);

            List<RegistroCore> listRegistros=Utilerias.stringToArray(manager.getString(Constants.JSON_BD),RegistroCore[].class);

            for (RegistroCore r: listRegistros){
                Log.d(TAG, "onMessageReceived: "+r.getFecha()
                );
            }


            Toast.makeText(this, "Json guardado correctamente", Toast.LENGTH_SHORT).show();
        } else {
            super.onMessageReceived(messageEvent);
        }
    }

}
