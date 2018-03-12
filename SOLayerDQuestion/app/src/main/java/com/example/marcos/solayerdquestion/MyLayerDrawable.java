package com.example.marcos.solayerdquestion;

import android.content.Context;
import android.content.res.XmlResourceParser;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.support.v4.content.ContextCompat;
import android.util.TypedValue;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by marcos on 12/31/17.
 */
public class MyLayerDrawable extends LayerDrawable{

    private int resource;
    private Context context;
    private List<Layer> layers;

    public static class Builder{
        public static MyLayerDrawable build(Context context,int resource){


            LayerDrawable ld=(LayerDrawable)ContextCompat.getDrawable(context, resource);
            List<Drawable> drawableList=new ArrayList<>();
            Drawable[] drawables={};
            List<Layer> layers=new ArrayList<>();
            for(int i=0;i<ld.getNumberOfLayers();i++){
                int topInset=parseMyLayerInsetTop(i,context,resource);
                int leftInset=parseMyLayerInsetLeft(i,context,resource);
                Layer layer=new MyLayerDrawable.Layer(ld.getDrawable(i),topInset,leftInset);
                layers.add(layer);
                drawableList.add(ld.getDrawable(i));
            }
            drawables=drawableList.toArray(drawables);


            MyLayerDrawable myLayerDrawable=new MyLayerDrawable(drawables);

            myLayerDrawable.setResource(resource);
            myLayerDrawable.setContext(context);
            myLayerDrawable.setLayers(layers);

            return myLayerDrawable;
        }
        public static MyLayerDrawable build(List<Layer> layers){
            List<Drawable> drawableList=new ArrayList<>();
            for(int i=0;i<drawableList.size();i++){
                drawableList.add(layers.get(i).drawable);
            }
            MyLayerDrawable myLayerDrawable=new MyLayerDrawable((Drawable[])drawableList.toArray());
            myLayerDrawable.setLayers(layers);
            return myLayerDrawable;
        }
        private static int parseMyLayerInsetTop(int index,Context context,int resource){
            XmlResourceParser xmlResourceParser=context.getResources().getXml(resource);


            try {
                xmlResourceParser.next();
            } catch (XmlPullParserException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            int n=-1;
            while(n<index){
                try {
                    xmlResourceParser.next();
                    if(xmlResourceParser.getEventType()== XmlPullParser.START_TAG&&xmlResourceParser.getName().equals("item")){

                            n++;


                    }
                } catch (XmlPullParserException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }


            }

            //String dip=xmlResourceParser.getAttributeValue(0);
            String dip="0.0dip";

            for(int i=0;i<xmlResourceParser.getAttributeCount();i++){
                if(xmlResourceParser.getAttributeName(i).equals("top")){
                    dip=xmlResourceParser.getAttributeValue(i);
                    break;
                }
            }

            int dipInt=Integer.parseInt(dip.substring(0,dip.length()-5));

            float px = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dipInt, context.getResources().getDisplayMetrics());
            int pxInt=(int)px;


            xmlResourceParser.close();
            return pxInt;

        }
        private static int parseMyLayerInsetLeft(int index,Context context,int resource){
            XmlResourceParser xmlResourceParser=context.getResources().getXml(resource);

            try {
                xmlResourceParser.next();
            } catch (XmlPullParserException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            int n=-1;
            while(n<index){
                try {
                    xmlResourceParser.next();
                    if(xmlResourceParser.getEventType()== XmlPullParser.START_TAG&&xmlResourceParser.getName().equals("item")){

                        n++;


                    }
                } catch (XmlPullParserException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }


            }
;
            //String dip=xmlResourceParser.getAttributeValue(0);
            String dip="0.0dip";

            for(int i=0;i<xmlResourceParser.getAttributeCount();i++){
                if(xmlResourceParser.getAttributeName(i).equals("left")){
                    dip=xmlResourceParser.getAttributeValue(i);
                    break;
                }
            }

            int dipInt=Integer.parseInt(dip.substring(0,dip.length()-5));

            float px = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dipInt, context.getResources().getDisplayMetrics());
            int pxInt=(int)px;


            xmlResourceParser.close();
            return pxInt;

        }
    }
    public static class Layer{
        private Drawable drawable;
        private int topInset;
        private int leftInset;
        public Layer(Drawable drawable,int topInset,int leftInset){
            this.leftInset=leftInset;
            this.drawable=drawable;
            this.topInset=topInset;
        }
        public Drawable getDrawable(){
            return drawable;
        }
        public int getTopInset(){
            return topInset;
        }
        public int getLeftInset(){return leftInset;}
    }
    public MyLayerDrawable(Drawable[] drawables){
        super(drawables);
    }


    public void setResource(int resource){
        this.resource=resource;
    }

    public void setContext(Context context){
        this.context=context;
    }

    public void setLayers(List<Layer> layers){
        this.layers=layers;
    }

    public Layer getLayer(int index){
        return layers.get(index);
    }



}
