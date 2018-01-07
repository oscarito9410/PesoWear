package com.oscar.pesowear.Utils.utils;
import android.content.Context;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.TextView;
import com.oscar.pesowear.R;
import com.shawnlin.numberpicker.NumberPicker;

/**
 * Created by oemy9 on 14/12/2017.
 */

public class AlturaPicker extends BasePicker implements NumberPicker.OnValueChangeListener {

    private NumberPicker numberPrim;
    private TextView tvUnidad;

    public AlturaPicker(Context context) {
        super(context);
    }

    public AlturaPicker(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public AlturaPicker(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public AlturaPicker(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    public void  inflateLayouts(Context context, AttributeSet attrs) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        layoutInflater.inflate(R.layout.altura_picker, this);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        numberPrim=(NumberPicker)findViewById(R.id.numberPrim);
        numberPrim.setOnValueChangedListener(this);
        tvUnidad=(TextView)findViewById(R.id.tvUnidad);
        if(!TextUtils.isEmpty(getUnidad())) {
            tvUnidad.setText(getUnidad());
        }
    }

    public void setAltura(int estatura){
        numberPrim.setValue(estatura);
    }

    @Override
    public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
        if(pickerChangedListener!=null){
            pickerChangedListener.onValueChanged((double)newVal);
        }
    }
}
