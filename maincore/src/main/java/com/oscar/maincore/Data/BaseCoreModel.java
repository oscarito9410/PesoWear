package com.oscar.maincore.Data;

import com.google.gson.Gson;

/**
 * Created by oemy9 on 09/12/2017.
 */

public class BaseCoreModel {
    public   String toJson(){
        return new Gson().toJson(this);
    }
}
