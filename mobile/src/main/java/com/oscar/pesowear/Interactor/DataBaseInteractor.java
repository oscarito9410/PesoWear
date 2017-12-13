package com.oscar.pesowear.Interactor;
import com.oscar.maincore.Utils.ENUM_OBJETIVO;
import com.oscar.pesowear.Data.Perfil;
import com.oscar.pesowear.Data.Registro;
import com.oscar.pesowear.Data.Registro_Table;
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

    public void agregarPerfil(String nombre, ENUM_OBJETIVO objetivo, double pesoInicio, double pesoMeta){
        Perfil p=new Perfil();
        p.setNombre(nombre);
        p.setObjetivo(objetivo.getValue());
        p.setPesoInicio(pesoInicio);
        p.setPesoMeta(pesoMeta);
        p.save();
    }

    public List<Registro>obtenerListRegistros(){
      return   SQLite.select().from(Registro.class).orderBy(Registro_Table.fecha,false).queryList();
    }

    public Registro obtenerLastRegistro(){
        return  SQLite.select().from(Registro.class).orderBy(Registro_Table.fecha,false).querySingle();
    }

    public Perfil obtenerPerfil(){
        return SQLite.select().from(Perfil.class).querySingle();
    }
}
