package com.oscar.maincore.Utils;

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
}
