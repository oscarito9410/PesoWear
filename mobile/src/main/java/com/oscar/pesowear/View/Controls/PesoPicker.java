package com.oscar.pesowear.View.Controls;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.oscar.pesowear.R;
import com.shawnlin.numberpicker.NumberPicker;

/**
 * Created by oemy9 on 11/12/2017.
 */

public class PesoPicker extends BasePicker implements NumberPicker.OnValueChangeListener {

    private NumberPicker numberPrim, numberSec;
    private TextView tvUnidad;

    public PesoPicker(Context context) {
        super(context);
    }

    public PesoPicker(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public PesoPicker(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    public void  inflateLayouts(Context context, AttributeSet attrs) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        layoutInflater.inflate(R.layout.peso_picker, this);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        numberPrim=(NumberPicker)findViewById(R.id.numberPrim);
        numberSec=(NumberPicker)findViewById(R.id.numberSec);
        numberSec.setOnValueChangedListener(this);
        numberPrim.setOnValueChangedListener(this);
        tvUnidad=(TextView)findViewById(R.id.tvUnidad);
    }

    public Double getPeso(){
        return Double.parseDouble(getFormatNumber(numberPrim.getValue(),numberSec.getValue()));
    }

    @Override
    public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
       double value=Double.valueOf(getFormatNumber(numberPrim.getValue(),numberSec.getValue()));
       if(pickerChangedListener!=null){

           if(!TextUtils.isEmpty(getUnidad())) {
               tvUnidad.setText(getUnidad());
           }
           pickerChangedListener.onValueChanged(value);
       }
    }

    @Override
    public void setUnidad(String unidad) {
        super.setUnidad(unidad);
        if(tvUnidad!=null){
            tvUnidad.setText(unidad);
        }
    }

    public void setPeso(double peso){
        String[] pesoString=String.valueOf(peso).split("\\.");
        if(pesoString.length>0){
            numberPrim.setValue(Integer.valueOf(pesoString[0]));
            numberSec.setValue(Integer.valueOf(pesoString[1]));
        }
    }

    public String getFormatNumber(int numeroPrimero,int numeroSecundario){
        return String.format("%s.%s",numeroPrimero, numeroSecundario);
    }
}
