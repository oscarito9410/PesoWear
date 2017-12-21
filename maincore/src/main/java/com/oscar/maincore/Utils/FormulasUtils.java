package com.oscar.maincore.Utils;

import android.content.Context;
import android.support.annotation.StringRes;

import com.oscar.maincore.R;

import java.util.HashMap;

/**
 * Created by oemy9 on 12/12/2017.
 */

public class FormulasUtils {
    public static double getImc(double peso,double estatura) {
        if (estatura > 100) {
            estatura = estatura / 100;
        }
        return peso / Math.pow(estatura, 2);
    }

    public static EstatusDescription getDescripcionByIMC(Context context, double imc){

        EstatusDescription description=null;

        if(imc>=18.5d && imc<=24.9){
            description=new EstatusDescription(context.getString(R.string.estatus_normal),R.color.colorVerdeIndicator);
        }
        else if(imc>=25 && imc<=29.9){
           description=new EstatusDescription(context.getString(R.string.estatus_sobrepeso),R.color.colorNaranjaInidcator);
        }
        else if(imc>=30 && imc<=34.9){
            description=new EstatusDescription(context.getString(R.string.estatus_obesidad_uno),R.color.colorRojoIndicator);
        }
        else if(imc>=35 && imc<=39.9){
            description=new EstatusDescription(context.getString(R.string.estatus_obesidad_dos),R.color.colorRojoIndicator);
        }
        else if(imc>=40){
            description=new EstatusDescription(context.getString(R.string.estatus_obesidad_tres),R.color.colorRojoIndicator);
        }
        return description;
    }
}
