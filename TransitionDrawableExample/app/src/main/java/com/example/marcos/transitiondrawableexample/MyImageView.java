package com.example.marcos.transitiondrawableexample;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;
import android.util.Log;

/**
 * Created by marcos on 4/17/18.
 */

public class MyImageView extends AppCompatImageView {

    private MyFilterDrawable myFilterDrawable=null;
    public MyImageView(Context context) {
        super(context);
    }

    public MyImageView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MyImageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setMyFilterDrawable(MyFilterDrawable myFilterDrawable){
        this.myFilterDrawable=myFilterDrawable;
        invalidate();
    }
    public void setFilterOpacity(int opacity){
        this.myFilterDrawable.setOpacity(opacity);
        invalidate();
    }

    private Bitmap overlay(Bitmap bmp1, Bitmap bmp2) {
        Bitmap bmOverlay = Bitmap.createBitmap(bmp1.getWidth(), bmp1.getHeight(), bmp1.getConfig());
        Canvas canvas = new Canvas(bmOverlay);
        canvas.drawBitmap(bmp1, new Matrix(), null);
        canvas.drawBitmap(bmp2, new Matrix(), null);

        return bmOverlay;
    }

    @Override
    protected void onDraw(Canvas canvas){

        if(myFilterDrawable!=null){
            Bitmap overlayBitmap=myFilterDrawable.getBitmap();
            Bitmap bitmap=Bitmap.createBitmap(getDrawable().getIntrinsicWidth(),getDrawable().getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
            Canvas regularCanvas=new Canvas(bitmap);
            getDrawable().draw(regularCanvas);
            Bitmap combinedBitmap=Bitmap.createBitmap(bitmap.getWidth(),bitmap.getHeight(), Bitmap.Config.ARGB_8888);
            int opacity=myFilterDrawable.getOpacity();

            for(int i=0;i<bitmap.getHeight();i++){
                for(int j=0;j<bitmap.getWidth();j++){
                    int bitmapPixel=bitmap.getPixel(i,j);
                    int overlayPixel=overlayBitmap.getPixel(i,j);
                    //Log.i("infor","bitmap pixel red: "+Color.red(bitmapPixel));
                    //Log.i("infor","bitmap pixel red: "+Color.red(bitmapPixel));
                    int color=(overlayPixel*opacity)/100+(bitmapPixel*(100-opacity))/100;

                    combinedBitmap.setPixel(i,j,color);

                }
            }
            canvas.drawBitmap(combinedBitmap,0,0,null);

        }else{
            getDrawable().draw(canvas);
        }




    }
}
