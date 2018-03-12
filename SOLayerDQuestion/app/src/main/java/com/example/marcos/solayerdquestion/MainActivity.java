package com.example.marcos.solayerdquestion;

import android.graphics.drawable.LayerDrawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView iv=(ImageView)findViewById(R.id.imageView);
        MyLayerDrawable myLayerDrawable=MyLayerDrawable.Builder.build(getApplicationContext(),R.drawable.layer_d_example);
        MyImageViewPatch myImageViewPatch=new MyImageViewPatch(iv);
        myImageViewPatch.setImageMyLayerDrawable(myLayerDrawable);

    }
}
