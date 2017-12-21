package com.oscar.maincore.Utils;

/**
 * Created by oemy9 on 20/12/2017.
 */

public class EstatusDescription {
    private String description;
    private int color;

    public EstatusDescription(String description, int color) {
        this.description = description;
        this.color = color;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }
}
