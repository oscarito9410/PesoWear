package com.oscar.maincore.Utils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.oscar.maincore.Data.BaseCoreModel;
import com.oscar.maincore.Data.RegistroCore;

import java.util.Arrays;
import java.util.List;

/**
 * Created by oemy9 on 09/12/2017.
 */

public class Utilerias {
    public static final String getJson(Object objt){
        return new Gson().toJson(objt);
    }
    public static final Object fromJson(String json, Class<? extends BaseCoreModel>obj){
        return new Gson().fromJson(json,obj);
    }

    public static <T> List<T> jsonToList(String json, Class<T> clazz) {
        if (null == json) {
            return null;
        }
        Gson gson = new Gson();
        return gson.fromJson(json, new TypeToken<T>(){}.getType());
    }

    public static <T> List<T> stringToArray(String s, Class<T[]> clazz) {
        T[] arr = new Gson().fromJson(s, clazz);
        return Arrays.asList(arr);
    }


}
