package com.example.marcos.solayerdquestion;

import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.view.ViewTreeObserver;
import android.widget.ImageView;

/**
 * Created by marcos on 12/21/17.
 */
public class MyImageViewPatch {
    private ImageView imageView;
    public MyImageViewPatch(final ImageView imageView){
        this.imageView=imageView;
        imageView.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
            @Override
            public boolean onPreDraw() {
                MyLayerDrawable layerDrawable=(MyLayerDrawable)imageView.getDrawable();
                int numberOfLayers=layerDrawable.getNumberOfLayers();
                Drawable drawable;
                Rect bounds;


                for(int i=0;i<numberOfLayers;i++){//for each layer
                    drawable=layerDrawable.getDrawable(i);
                    bounds=drawable.getBounds();
                    bounds.top=layerDrawable.getLayer(i).getTopInset();
                    bounds.left=layerDrawable.getLayer(i).getLeftInset();
                    if(drawable.getIntrinsicHeight()<bounds.height()){
                        bounds.bottom=drawable.getIntrinsicHeight()+layerDrawable.getLayer(i).getTopInset();

                    }
                    if(drawable.getIntrinsicWidth()<bounds.width()){
                        bounds.right=drawable.getIntrinsicWidth()+layerDrawable.getLayer(i).getLeftInset();
                    }
                }
                return true;
            }
        });
    }
    public void setImageMyLayerDrawable(MyLayerDrawable layerDrawable){
        imageView.setImageDrawable(layerDrawable);

    }
}
