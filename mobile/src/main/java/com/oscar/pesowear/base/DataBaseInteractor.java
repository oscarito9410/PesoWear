package com.oscar.pesowear.base;
import com.oscar.maincore.Utils.ENUM_OBJETIVO;
import com.oscar.pesowear.profile.model.Profile;
import com.oscar.pesowear.record.model.Record;
import com.oscar.pesowear.Model.Registro_Table;
import com.raizlabs.android.dbflow.sql.language.SQLite;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import io.reactivex.Observable;

/**
 * Created by oemy9 on 11/12/2017.
 */

public class DataBaseInteractor {
    public void agregarRegistroPeso(Double peso, Calendar fecha, String notas){
        Record r=new Record(peso,fecha.getTime(),notas);
        r.save();
    }

    /**
     * Ingresa un nuevo perfil  a la bd
     * @param nombre  Nombre de usuario
     * @param objetivo su objetivo ganar o perder peso
     * @param pesoInicio peso de inicio
     * @param pesoMeta peso meta
     * @param estatura estatura actual
     * @param unidadMedida unidad de medidass
     * @returns si el valor es true quiere decir que se actualizo, si es false se agrego
     */
    public boolean agregarActualizarPerfil(String nombre, ENUM_OBJETIVO objetivo, double pesoInicio, double pesoMeta, int estatura, String unidadMedida){
            boolean update=obtenerPerfil()!=null;
            Profile p = update? obtenerPerfil(): new Profile();
            p.setNombre(nombre);
            p.setObjetivo(objetivo.getValue());
            p.setPesoInicio(pesoInicio);
            p.setPesoObjetivo(pesoMeta);
            p.setEstatura(estatura);
            p.setUnidadMedida(unidadMedida);
            if(update){
               p.update();
               return true;
            }
           else {
                p.setFechaInicio(new Date());
                p.insert();
                return false;
            }

    }

    public  boolean eliminarRegistros(ArrayList<Record> listRecords){
        try {
            if (listRecords != null) {
                Observable.fromIterable(listRecords).subscribe(
                        registro -> registro.delete());
            }
            return true;
        }catch (Exception ex){
            return  false;
        }
    }
    public List<Record>obtenerListRegistros(){
      return   SQLite.select().from(Record.class).orderBy(Registro_Table.fecha,false).queryList();
    }

    public Record obtenerLastRegistro(){
        return  SQLite.select().from(Record.class).orderBy(Registro_Table.fecha,false).querySingle();
    }

    public Profile obtenerPerfil(){
        return SQLite.select().from(Profile.class).querySingle();
    }

    public Record obtenerUltimoRegistro(){
        return   SQLite.select().from(Record.class).orderBy(Registro_Table.fecha,false).querySingle();

    }
}

