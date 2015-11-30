package com.hendr.imagesticker;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
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
import uk.co.senab.photoview.PhotoViewAttacher;


import com.squareup.picasso.Picasso;


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
         Picasso.with(this).load("http://2.bp.blogspot.com/-EMHgM-hI58k/Vi5GtyudrLI/AAAAAAAAMXA/0ZfJUgZGO6M/s1600/2015-10-26%2B11.22.57.jpg").into(imageView);

        // Picasso.with(this).load("http://www.dhresource.com/albu_280449507_00/1.0x0.jpg").into(imageView);


       // addSticker();


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
        float valueImage[] = new float[10];
        float valueDestination[] = new float[10];
        float valueSticker[] = new float[10];

        Bitmap sourceImage = ((BitmapDrawable)imageView.getDrawable()).getBitmap();
        Bitmap bitmapOverlay = Bitmap.createBitmap(sourceImage.getWidth(), sourceImage.getHeight(), Bitmap.Config.ARGB_8888);
      //  Bitmap stickerBitmap = stickerView.getBitmap();

        Canvas canvas = new Canvas(bitmapOverlay);

        Matrix stickerSourceMatrix = stickerView.getmMatrix();
        Matrix overlayMatrix = new Matrix();
        Matrix imageMatrix = imageView.getImageMatrix();
        Matrix stickerDestMatrix = new Matrix();

        Rect stickerRect = new Rect();
        stickerView.getDrawingRect(stickerRect);
        stickerSourceMatrix.getValues(valueSticker);

        //get image display scaling
        float scaleX = ((float)sourceImage.getWidth() / (float)stickerRect.width());
        float scaleY = ((float) sourceImage.getHeight() / (float)stickerRect.height());
        float scale;
        if(scaleX>scaleY)
            scale = scaleX;
        else
            scale = scaleY;

        imageMatrix.getValues(valueImage);
        stickerDestMatrix.getValues(valueDestination);

        // calculate real scale
        float scalex = valueSticker[Matrix.MSCALE_X];
        float skewy = valueSticker[Matrix.MSKEW_Y];
        float rScale = (float) Math.sqrt(scalex * scalex + skewy * skewy);
//     get rotation degree
        float rAngle = stickerView.totalDegree;
        //Rotate First
        stickerDestMatrix.postRotate(rAngle);
        //Scale the image
        stickerDestMatrix.postScale(rScale / valueImage[0], rScale / valueImage[4]);
        //get values after scale
        stickerDestMatrix.getValues(valueDestination);
        //translate to location
        stickerDestMatrix.postTranslate(valueSticker[2] * scale, (valueSticker[5] - valueImage[5]) * scale);

        canvas.drawBitmap(sourceImage, overlayMatrix, null);
        canvas.drawBitmap(sticker, stickerDestMatrix, null);

        //Bitmap bmp = Bitmap.createScaledBitmap(sticker,)
       // MediaStore.Images.Media.insertImage(getContentResolver(), bmp, "test", "test");
     //   MediaStore.Images.Media.insertImage(getContentResolver(), bmOverlay, "test", "test");
       // stickerView.removeBorder();

        //set new combined image to bitmap
        imageView.setImageBitmap(bitmapOverlay);

        //remove sticker from view and dealloc
        ((ViewGroup)imageView.getParent()).removeView(stickerView);
        stickerView.destroyDrawingCache();
        stickerView = null;
        sticker = null;
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
