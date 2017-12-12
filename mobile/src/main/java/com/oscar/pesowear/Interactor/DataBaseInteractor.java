package com.oscar.pesowear.Interactor;
import com.oscar.pesowear.Data.Registro;
import java.util.Calendar;

/**
 * Created by oemy9 on 11/12/2017.
 */

public class DataBaseInteractor {
    public void agregarRegistroPeso(Double peso, Calendar fecha, String notas){
        Registro r=new Registro(peso,fecha.getTime(),notas);
        r.save();
    }
}
