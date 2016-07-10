package com.example.tk.arrayadapterpractice;

import android.graphics.drawable.Drawable;

/**
 * Created by TK on 2016-07-09.
 */
public class TARAValueObject {
    public String memberName;
    public Drawable memberImage;
    public int count;

    public TARAValueObject(String memberName, Drawable memberImage) {
        this.memberName = memberName;
        this.memberImage = memberImage;
    }
}
