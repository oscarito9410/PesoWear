package com.oscar.pesowear.Utils.utils;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.oscar.pesowear.R;

/**
 * Created by oemy9 on 11/12/2017.
 */

public class BubbleLayout extends RelativeLayout {

    private Drawable icon;
    private String text;
    private TextView tvTitulo;
    private boolean textChanged=false;

    public BubbleLayout(Context context) {
        super(context);
        inflateLayouts(context,null);
    }

    public BubbleLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        inflateLayouts(context,attrs);
    }

    public BubbleLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        inflateLayouts(context,attrs);
    }


    private void  inflateLayouts(Context context, AttributeSet attrs) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        layoutInflater.inflate(R.layout.bubble_layout, this);
        if(attrs!=null) {
            TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.BubbleLayout);
            setIcon(typedArray.getDrawable(R.styleable.BubbleLayout_icono));
            setText(typedArray.getString(R.styleable.BubbleLayout_text));
        }
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        ImageView imgBubble = (ImageView) findViewById(R.id.imgBubble);
        tvTitulo=(TextView)findViewById(R.id.tvBubble);
        if(getIcon()!=null){
            imgBubble.setImageDrawable(getIcon());
        }
        tvTitulo.setText(getText());
    }

    public Drawable getIcon() {
        return icon;
    }

    public void setIcon(Drawable icon) {
        if(icon!=null) {
            this.icon = icon;
        }
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        if(text!=null) {
            this.text = text;
            if(tvTitulo!=null){
                tvTitulo.setText(text);
                textChanged=true;
            }
        }
    }

    public boolean isTextChanged() {
        return textChanged;
    }

    public void setTextChanged(boolean textChanged) {
        this.textChanged = textChanged;
    }
}
