package com.hendr.imagesticker;

import android.content.Intent;
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

import it.sephiroth.android.library.imagezoom.graphics.FastBitmapDrawable;
import me.isming.sticker.library.StickerView;
import ooo.oxo.library.widget.TouchImageView;
import pl.aprilapps.easyphotopicker.EasyImage;
import pl.aprilapps.easyphotopicker.DefaultCallback;


import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

import java.io.File;


public class MainActivity extends AppCompatActivity  {


    private ImageView mImageView;
    private TouchImageView imageView;
    private StickerView stickerView ;
    private Bitmap sticker;
    private Paint mPaint;
    private Menu mMenu;
    private boolean displayPhoto;
    private boolean displaySticker;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        EasyImage.handleActivityResult(requestCode, resultCode, data, this, new DefaultCallback() {
            @Override
            public void onImagePickerError(Exception e, EasyImage.ImageSource source) {
                //Some error handling
                displayPhoto = false;
            }

            @Override
            public void onImagePicked(File imageFile, EasyImage.ImageSource source) {
                //Handle the image
                onPhotoReturned(imageFile);
            }
        });
    }

    private void onPhotoReturned(File imageFile)
    {
        if (imageFile == null) {
            return;
        }
        if(imageFile.canRead())
        {
            displayPhoto = true;


            Picasso.with(this).load(imageFile).transform(new BitmapTransform(2000, 2000)).memoryPolicy(MemoryPolicy.NO_CACHE).into(imageView);
 //           Picasso.with(this).load(imageFile).into(imageView);
            setMenuDisplay();
        }
    }

    private void loadBarcodeScanner()
    {
        Intent myIntent = new Intent(MainActivity.this, ScannerActivity.class);
        //myIntent.putExtra("key", value); //Optional parameters
        startActivity(myIntent);
    }

    private void setMenuDisplay()
    {
        /*
        * 0 = Done
        * 1 = Camera
        * 2 = Sticker
        * 3 = Barcode
        * */

        MenuItem itemSticker = mMenu.getItem(2);
        itemSticker.setVisible(displayPhoto&&!displaySticker);

        MenuItem itemDone = mMenu.getItem(0);
        itemDone.setVisible(displaySticker);

        MenuItem itemCamera = mMenu.getItem(1);
        itemCamera.setVisible(!displaySticker);
       // item.setVisible();
    }



    private void loadPhoto()
    {
        EasyImage.openChooser(this, "Add Photo");
    }

    private void setPaint()
    {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setFilterBitmap(true);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(4.0f);
        mPaint.setColor(Color.WHITE);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        displayPhoto = false;
        displaySticker = false;
      //  Fresco.initialize(this);
        setContentView(R.layout.activity_main);
        setPaint();

        imageView = (TouchImageView)findViewById(R.id.imageView);
        //imageView.get
       // imageView.scale
     //   Picasso.with(this).load("http://dummyimage.com/3240x1533/FFF/000.jpg&text=asu").into(imageView);
         //Picasso.with(this).load("http://dummyimage.com/2160x1533/FFF/000.jpg&text=asu").into(imageView);
       // Picasso.with(this).load("http://dummyimage.com/1080x1533/FFF/000.jpg&text=asu").into(imageView);
       // Picasso.with(this).load("http://dummyimage.com/2160x3066/FFF/000.jpg&text=asu").into(imageView);
        //


       // EasyImage.openCamera(this);
//         Picasso.with(this).load("http://2.bp.blogspot.com/-EMHgM-hI58k/Vi5GtyudrLI/AAAAAAAAMXA/0ZfJUgZGO6M/s1600/2015-10-26%2B11.22.57.jpg").into(imageView);

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
       // setMenuDisplay();
    }

    private void doneAddSticker()
    {
        float valueImage[] = new float[10];
        float valueDestination[] = new float[10];
        float valueSticker[] = new float[10];


        Bitmap sourceImage ;
        if(imageView.getDrawable().getClass() == FastBitmapDrawable.class)
        {
            // if loaded from setBitmap
            sourceImage =((FastBitmapDrawable)imageView.getDrawable()).getBitmap();;
        }
        else
        {
            // if image loaded from somewhere else
            sourceImage =((BitmapDrawable)imageView.getDrawable()).getBitmap();
        }

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
       // Picasso.with(this).
      //  imageView.bitmap
        //remove sticker from view and dealloc
                ((ViewGroup) imageView.getParent()).removeView(stickerView);
        stickerView.destroyDrawingCache();
        stickerView = null;
        sticker = null;

        //remove the done button
        displaySticker = false;
        setMenuDisplay();
     //   canvas.drawBitmap(bmp2, 0, 0, null);

    }

    private void addSticker()
    {
        imageView.resetMatrix();
        stickerView = new StickerView(this);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT);
        params.addRule(RelativeLayout.ALIGN_BOTTOM, R.id.image);
        params.addRule(RelativeLayout.ALIGN_TOP, R.id.image);
        ((ViewGroup)imageView.getParent()).addView(stickerView, params);
        sticker = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
        stickerView.setWaterMark(sticker);
        displaySticker = true;
        setMenuDisplay();
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
        mMenu = menu;
        setMenuDisplay();
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:
                // User chose the "Settings" item, show the app settings UI...
                return true;

            case R.id.action_camera:
                loadPhoto();
                return true;
            case R.id.action_sticker:
                addSticker();
                return true;
            case R.id.action_barcode:
                loadBarcodeScanner();
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
