package com.example.marcos.transitiondrawableexample;

import android.animation.ValueAnimator;
import android.graphics.Color;
import android.graphics.LightingColorFilter;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.TransitionDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.bumptech.glide.GenericTransitionOptions;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ImageButton imageButton=(ImageButton)findViewById(R.id.imageButton);
        final ImageView imageView=(ImageView)findViewById(R.id.imageView);
        MyImageView myImageView=(MyImageView)findViewById(R.id.my_image_view);

        //Glide.with(getApplicationContext()).load(R.drawable.fondo_uno).apply(new RequestOptions().override(100,100)).apply(new RequestOptions().placeholder(R.drawable.fondo_dos)).transition(GenericTransitionOptions.with(R.anim.fade_in)).into(imageView);
        //Glide.with(this).load(R.drawable.fondo_uno).animate(R.anim.fade_in).into(imageView);
        Drawable filteredDrawable=getDrawable(R.drawable.fondo_uno);
        //filteredDrawable.setColorFilter(Color.argb(125,0,0,255), PorterDuff.Mode.OVERLAY);
        //filteredDrawable.setColorFilter(new LightingColorFilter(Color.rgb(255,255,255),Color.rgb(0,0,0)));
        imageView.setImageDrawable(filteredDrawable);

        myImageView.setImageDrawable(getDrawable(R.drawable.fondo_dos));
        myImageView.setMyFilterDrawable(new MyFilterDrawable(getDrawable(R.drawable.fondo_uno),0));


        ValueAnimator valueAnimator=ValueAnimator.ofInt(0,255);
        valueAnimator.setDuration(10000);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            private int lastValue=-1;
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                if(lastValue!=(int)valueAnimator.getAnimatedValue()){
                    lastValue=(int)valueAnimator.getAnimatedValue();
                    Drawable drawable=getDrawable(R.drawable.fondo_uno);
                    drawable.setColorFilter(new LightingColorFilter(Color.rgb(255-lastValue,255-lastValue,255-lastValue),Color.rgb(0,0,lastValue)));
                    imageView.setImageDrawable(drawable);
                    Log.i("infor","animation value: "+valueAnimator.getAnimatedValue());
                }

            }
        });
        valueAnimator.start();
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
}
