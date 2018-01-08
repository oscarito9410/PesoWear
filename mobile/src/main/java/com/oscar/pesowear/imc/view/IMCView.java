package com.oscar.pesowear.imc.view;

import com.oscar.maincore.MVP.View.BaseView;
import com.oscar.pesowear.imc.model.IMCResult;

/**
 * Created by oemy9 on 07/01/2018.
 */

public interface IMCView extends BaseView {
    void onIMCResult(IMCResult result);
}
