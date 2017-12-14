package com.oscar.pesowear.View.Controls;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.LinearLayout;

/**
 * Created by oemy9 on 14/12/2017.
 */

public  abstract  class BasePicker extends LinearLayout {

    private String unidad;

    public String getUnidad() {
        return unidad;
    }

    public void setUnidad(String unidad) {
        this.unidad = unidad;
    }

    public BasePicker(Context context) {
        super(context);
        inflateLayouts(context,null);
    }

    public BasePicker(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        inflateLayouts(context,attrs);
    }

    public BasePicker(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        inflateLayouts(context,attrs);
    }

    public BasePicker(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        inflateLayouts(context,attrs);
    }

    public interface PickerChangedListener {
        void onValueChanged(double value);
    }

    protected abstract void  inflateLayouts(Context context, AttributeSet attrs);

    protected PickerChangedListener pickerChangedListener;


    public void setListener(PickerChangedListener listener) {
        this.pickerChangedListener = listener;
    }

    public void clearListener(){
        if(this.pickerChangedListener!=null){
            this.pickerChangedListener=null;
        }
    }
}
