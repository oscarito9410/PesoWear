package com.oscar.maincore.Utils;

/**
 * Created by oemy9 on 12/12/2017.
 */

public enum ENUM_OBJETIVO {
    PERDIDA_PESO(1),
    GANAR_PESO(2);

    private int value;

    ENUM_OBJETIVO(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static  ENUM_OBJETIVO fromValue(int value){
        for (ENUM_OBJETIVO item: ENUM_OBJETIVO.values()){
            if(item.getValue()==value){
                return item;
            }
        }
        return null;
    }
}
