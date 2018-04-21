package com.example.marcos.transitiondrawableexample;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;

/**
 * Created by marcos on 4/18/18.
 */

public class MyFilterDrawable {
    private Drawable drawable;
    private Bitmap bitmap;
    private int opacity;
    public MyFilterDrawable(Drawable drawable, int opacity){
        this.drawable=drawable;
        this.opacity=opacity;
        bitmap=Bitmap.createBitmap(drawable.getIntrinsicWidth(),drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas=new Canvas(bitmap);
        drawable.draw(canvas);
    }
    public int getOpacity(){
        return opacity;
    }
    public void setOpacity(int opacity){
        this.opacity=opacity;
    }
    public Drawable getDrawable(){
        return drawable;
    }
    public Bitmap getBitmap(){
        return bitmap;
    }

}
