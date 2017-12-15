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
            Perfil p = update? obtenerPerfil(): new Perfil();
            p.setNombre(nombre);
            p.setObjetivo(objetivo.getValue());
            p.setPesoInicio(pesoInicio);
            p.setPesoMeta(pesoMeta);
            p.setEstatura(estatura);
            p.setUnidadMedida(unidadMedida);
            if(update){
               p.update();
               return true;
            }
           else {
                p.insert();
                return false;
            }

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
