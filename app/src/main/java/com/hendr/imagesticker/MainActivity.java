package com.hendr.imagesticker;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import me.isming.sticker.library.StickerView;
import ooo.oxo.library.widget.TouchImageView;
import uk.co.senab.photoview.PhotoView;
import uk.co.senab.photoview.PhotoViewAttacher;


import com.hendr.imagesticker.*;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.io.IOException;
import java.util.concurrent.Semaphore;

import static android.graphics.Bitmap.Config.ARGB_8888;


public class MainActivity extends AppCompatActivity {

    private PhotoViewAttacher mAttacher;
    private ImageView mImageView;
    private TouchImageView imageView;
    private StickerView stickerView ;
    private Bitmap sticker;
    private Paint mPaint;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
      //  Fresco.initialize(this);
        setContentView(R.layout.activity_main);

        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setFilterBitmap(true);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(4.0f);
        mPaint.setColor(Color.WHITE);

        imageView = (TouchImageView)findViewById(R.id.imageView);
     //   Picasso.with(this).load("http://dummyimage.com/3240x1533/FFF/000.jpg&text=asu").into(imageView);
         //Picasso.with(this).load("http://dummyimage.com/2160x1533/FFF/000.jpg&text=asu").into(imageView);
       // Picasso.with(this).load("http://dummyimage.com/1080x1533/FFF/000.jpg&text=asu").into(imageView);
       // Picasso.with(this).load("http://dummyimage.com/2160x3066/FFF/000.jpg&text=asu").into(imageView);
        //
        // Picasso.with(this).load("http://2.bp.blogspot.com/-EMHgM-hI58k/Vi5GtyudrLI/AAAAAAAAMXA/0ZfJUgZGO6M/s1600/2015-10-26%2B11.22.57.jpg").into(imageView);

         Picasso.with(this).load("http://www.dhresource.com/albu_280449507_00/1.0x0.jpg").into(imageView);


        addSticker();


      /*  mImageView = (ImageView) findViewById(R.id.iv_photo);
        mAttacher = new PhotoViewAttacher(mImageView);
        mAttacher.
        Picasso.with(this).load("http://www.dhresource.com/albu_280449507_00/1.0x0.jpg").into(mImageView);

        mAttacher.update();
        */
    //    PhotoView mImageView = (PhotoView) findViewById(R.id.iv_photo);


       // SimpleDraweeView draweeView = (SimpleDraweeView)findViewById(R.id.my_image_view);
     //   ZoomableDraweeView draweeView = (ZoomableDraweeView) findViewById(R.id.zoomable);
       // draweeView.set
       // draweeView.setImageURI(Uri.parse("http://www.dhresource.com/albu_280449507_00/1.0x0.jpg"));
/*
        Uri data = Uri.parse("http://www.dhresource.com/albu_280449507_00/1.0x0.jpg");
        DraweeController ctrl = Fresco.newDraweeControllerBuilder().setUri(
                data).setTapToRetryEnabled(true).build();
        GenericDraweeHierarchy hierarchy = new GenericDraweeHierarchyBuilder(getResources())
                .setActualImageScaleType(ScalingUtils.ScaleType.FIT_CENTER)
                .setProgressBarImage(new ProgressBarDrawable())
                .build();

        draweeView.setController(ctrl);
        draweeView.setHierarchy(hierarchy);

*/
      /*  RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT);
        params.addRule(RelativeLayout.ALIGN_BOTTOM, R.id.fitEnd);
        params.addRule(RelativeLayout.ALIGN_TOP, R.id.fitStart);*/
        //ImageView mImgView1 = new ImageView(this);
    }

    private void doneAddSticker()
    {
        Bitmap img = ((BitmapDrawable)imageView.getDrawable()).getBitmap();
        Bitmap bmOverlay = Bitmap.createBitmap(img.getWidth(), img.getHeight(), Bitmap.Config.ARGB_8888);
        Bitmap bmp = stickerView.getBitmap();
        Matrix stickerMatrix = stickerView.getmMatrix();
        Canvas canvas = new Canvas(bmOverlay);
        Matrix myMatrix = new Matrix();

        Rect smallImgRect = new Rect();

        Rect imgRect = new Rect();
        imageView.getDrawingRect(imgRect);
        imgRect.set(0, 0, img.getWidth(), img.getHeight());
        Rect stickerRect = new Rect();
        stickerView.getDrawingRect(stickerRect);

        float value[] = new float[9];
        float mPoints[] = new float[100];
        stickerMatrix.getValues(value);

        stickerMatrix.mapPoints(mPoints);

        Log.i("stiker1", stickerMatrix.toString());
        float scaleX = ((float)imgRect.width() / (float)stickerRect.width());
        float scaleY = ((float)imgRect.height() / (float)stickerRect.height());
        float transX,deltaX;
        float transY,deltaY;


        float scale;
        if(scaleX>scaleY)
        {
            scale = scaleX;
        }
        else
        {
            scale = scaleY;
        }

        Matrix testMat = imageView.getImageMatrix();
        Log.i("testMat", testMat.toString());

      /*  deltaY = (float)stickerRect.height() - ((float)stickerRect.height()*( value[stickerMatrix.MTRANS_Y]/(float)stickerRect.height()));
        // deltaY =  ((float)stickerRect.height()* scaleY/scale) * -1f;
       // deltaY = ((float)stickerRect.height() - value[stickerMatrix.MTRANS_Y]) * scaleY;
        transY = (scaleY*value[stickerMatrix.MTRANS_Y]) - deltaY+ (((float)sticker.getHeight()/2)*(scale-1));
       // transY = 0f;
       // deltaY = -766.5f;
        transX = ((scaleX*value[stickerMatrix.MTRANS_X]) - value[stickerMatrix.MTRANS_X]) +  (((float)sticker.getWidth()/2)*(scale-1));
       // transY = ((scaleY*value[stickerMatrix.MTRANS_Y]) - (value[stickerMatrix.MTRANS_Y] - deltaY)) - deltaY+ (((float)sticker.getHeight()/2)*(scale-1));
        //transY = -383.25f;
     //   transY = ((scaleY*value[stickerMatrix.MTRANS_Y])*(scaleY/scale)) - (value[stickerMatrix.MTRANS_Y]) + (((float)sticker.getHeight()/2)*(scale-1));
       // transY = 0+ (((float)sticker.getHeight()/2)*(scale-1));
        //height sticker = 144
       // transY = ((float)stickerRect.height() - ((float)imgRect.height()))/2;
       // transY = transY * (-1);
       // transY = transY - (298.5f + ((float)sticker.getHeight()/2));
      //  transY = 468f + ((float)sticker.getHeight()/2);
       // transY = transY * -1f;
//        Log.i("heightSticker",Float.toString(sticker.getHeight()));
        Log.i("dY",Float.toString(deltaY));
        Log.i("scY", Float.toString(scaleY));
        Log.i("scX", Float.toString(scaleX));
        Log.i("Y",Float.toString(transY));
        Log.i("X",Float.toString(transX));
        stickerMatrix.postTranslate(transX, transY);
       // stickerMatrix.preTranslate(transX,transY);
       //stickerMatrix.setTranslate(transX,transY);
        stickerMatrix.getValues(value);
        Log.i("stikerTR", stickerMatrix.toString());

        Log.i("scale", Float.toString(scale));
       // value[2] = value[2] + ((float)sticker.getWidth()/2);
       // value[5] = value[5] + ((float)sticker.getHeight()/2);
       // float scale = value[stickerMatrix.MSCALE_Y] *(imgRect.height() / stickerRect.height());
      //  stickerMatrix.postScale(scaleX,scaleY);
      //  stickerMatrix.postTranslate(540f,766.5f);
       // stickerMatrix.pos
        float centerPointStickerX = value[2]+(((float)sticker.getWidth()*value[0])/2);
        float centerPointStickerY = value[5]+(((float)sticker.getHeight()*value[4])/2);

        stickerMatrix.postScale(scale, scale, centerPointStickerX,centerPointStickerY);
        stickerMatrix.getValues(value);
       // Matrix newSticker = new Matrix();
//        newSticker.postRotate()
        */
       // stickerMatrix.postConcat(testMat);
        //testMat.postConcat(stickerMatrix);
       // testMat.postConcat()
        float val1[] = new float[10];
        float val2[] = new float[10];
        testMat.getValues(val1);

        Matrix mat = new Matrix();


        float rAngle = Math.round(Math.atan2(value[Matrix.MSKEW_X], value[Matrix.MSKEW_X]) * (180 / Math.PI));



        mat.postScale(value[0] / val1[0], value[4] / val1[4]);
      //  stickerMatrix.postScale(value[0] / val1[0], value[4] / val1[4]);
        mat.getValues(val2);
        Log.i("stiker2", mat.toString());
        mat.postTranslate(value[2]*val2[0],(value[5] - val1[5]) * val2[4]);
        stickerMatrix.getValues(value);
        Log.i("stiker2", mat.toString());
    //    mat.postRotate(rAngle,(float)sticker.getWidth(),(float)sticker.getHeight());
      //  stickerMatrix.postScale(value[0]/val1[0],value[4]/val1[4],value[2],value[5]/2);
      //  stickerMatrix.postScale()
        canvas.drawBitmap(img, myMatrix, null);
        canvas.drawBitmap(sticker, mat, null);
        Log.i("StickerRect", stickerRect.toString());
        Log.i("imgRect",imgRect.toString());
        Log.i("stiker2",stickerMatrix.toString());
        //Bitmap bmp = Bitmap.createScaledBitmap(sticker,)
       // MediaStore.Images.Media.insertImage(getContentResolver(), bmp, "test", "test");
     //   MediaStore.Images.Media.insertImage(getContentResolver(), bmOverlay, "test", "test");
       // stickerView.removeBorder();
        imageView.setImageBitmap(bmOverlay);
      //  ((ViewGroup)imageView.getParent()).removeView(stickerView);
     //   canvas.drawBitmap(bmp2, 0, 0, null);

    }

    private void addSticker()
    {

        stickerView = new StickerView(this);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT);
        params.addRule(RelativeLayout.ALIGN_BOTTOM, R.id.image);
        params.addRule(RelativeLayout.ALIGN_TOP, R.id.image);
        ((ViewGroup)imageView.getParent()).addView(stickerView, params);
        sticker = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
        stickerView.setWaterMark(sticker);

      /*  final Semaphore semaphore = new Semaphore(0);
        Picasso.with(this).load("http://2.bp.blogspot.com/-fSGUtRfRi3A/UE96fk5Zy1I/AAAAAAAAFmM/iL-cDeoZszw/s1600/new+fill+layer.png").priority(Picasso.Priority.HIGH).into(new Target() {
            @Override
            public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                sticker = bitmap;
            }

            @Override
            public void onBitmapFailed(Drawable errorDrawable) {
            }

            @Override
            public void onPrepareLoad(Drawable placeHolderDrawable) {

            }
        });

        try {
            semaphore.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if(sticker != null) {
            stickerView = new StickerView(this);
            RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT);
            params.addRule(RelativeLayout.ALIGN_BOTTOM, R.id.image);
            params.addRule(RelativeLayout.ALIGN_TOP, R.id.image);
            ((ViewGroup) imageView.getParent()).addView(stickerView, params);
            stickerView.setWaterMark(sticker);
        }*/
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    //    Fresco.shutDown();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:
                // User chose the "Settings" item, show the app settings UI...
                return true;

            case R.id.action_done:
                // User chose the "Favorite" action, mark the current item
                // as a favorite...
                doneAddSticker();
                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }


}
