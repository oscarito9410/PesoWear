package com.oscar.pesowear.Interactor;
import com.oscar.pesowear.Data.Registro;
import com.raizlabs.android.dbflow.sql.language.SQLite;

import java.util.Calendar;
import java.util.List;

/**
 * Created by oemy9 on 11/12/2017.
 */

public class DataBaseInteractor {
    public void agregarRegistroPeso(Double peso, Calendar fecha, String notas){
        Registro r=new Registro(peso,fecha.getTime(),notas);
        r.save();
    }

    public List<Registro>obtenerListRegistros(){
      return   SQLite.select().from(Registro.class).queryList();
    }
}
