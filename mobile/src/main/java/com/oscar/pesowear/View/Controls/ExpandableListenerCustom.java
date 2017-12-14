package com.oscar.pesowear.View.Controls;

import android.widget.ImageView;
import com.github.aakira.expandablelayout.ExpandableLayoutListener;
import com.oscar.pesowear.R;
/**
 * Created by oemy9 on 24/09/2017.
 */

public class ExpandableListenerCustom implements ExpandableLayoutListener {


    private ImageView imageArrow;

    public ExpandableListenerCustom(ImageView imageArrow){
        this.imageArrow=imageArrow;
    }

    @Override
    public void onAnimationStart() {

    }

    @Override
    public void onAnimationEnd() {

    }

    @Override
    public void onPreOpen() {
        imageArrow.setImageResource(R.drawable.ic_arrow_up);
    }

    @Override
    public void onPreClose() {
        imageArrow.setImageResource( R.drawable.ic_arrow_down);
    }

    @Override
    public void onOpened() {

    }

    @Override
    public void onClosed() {

    }
}
