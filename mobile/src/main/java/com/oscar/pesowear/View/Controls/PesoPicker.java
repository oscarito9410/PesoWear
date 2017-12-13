package com.oscar.pesowear.View.Controls;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import com.oscar.pesowear.R;
import com.shawnlin.numberpicker.NumberPicker;

/**
 * Created by oemy9 on 11/12/2017.
 */

public class PesoPicker extends LinearLayout {

    private NumberPicker numberPrim, numberSec;

    public PesoPicker(Context context) {
        super(context);
        inflateLayouts(context,null);
    }

    public PesoPicker(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        inflateLayouts(context,attrs);
    }

    public PesoPicker(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        inflateLayouts(context,attrs);
    }

    public void  inflateLayouts(Context context, AttributeSet attrs) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        layoutInflater.inflate(R.layout.peso_picker, this);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        numberPrim=(NumberPicker)findViewById(R.id.numberPrim);
        numberSec=(NumberPicker)findViewById(R.id.numberSec);
    }

    public Double getPeso(){
        return Double.parseDouble(getFormatNumber(numberPrim.getValue(),numberSec.getValue()));
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
