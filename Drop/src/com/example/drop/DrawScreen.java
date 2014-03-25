package com.example.drop;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnFocusChangeListener;
import android.view.ViewTreeObserver;
import android.widget.ImageView;

public class DrawScreen extends Activity {
    
	byte [] data;
	 
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_draw_screen);
        
        Intent i = getIntent();
        File pictureFile = (File)i.getSerializableExtra("image");
        data  = new byte[(int) pictureFile.length()];
        
        try 
        {
            //convert file into array of bytes
        	FileInputStream fileInputStream = new FileInputStream(pictureFile);
	    	fileInputStream.read(data);
	    	fileInputStream.close();
        }
        catch(Exception e)
        {
        	e.printStackTrace();
        }
        final DrawingWidget background = (DrawingWidget)findViewById(R.id.background_picture);
        
        background.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {

            public void onGlobalLayout() {
                // Ensure you call it only once :
            	background.getViewTreeObserver().removeGlobalOnLayoutListener(this);

            	//Get image taken
		        BitmapFactory.Options options = new BitmapFactory.Options();
		        options.inMutable = true;
		        Bitmap b = BitmapFactory.decodeByteArray(data, 0, data.length, options);
		        
		        //Rotate and scale image
		        Log.i("DRAW", background.getHeight() +" x "+ background.getWidth());
		        b = Bitmap.createScaledBitmap(b, background.getHeight(), background.getWidth(), true);
		        Matrix matrix = new Matrix();
		        matrix.postRotate(90);
		        b = Bitmap.createBitmap(b , 0, 0, b.getWidth(), b.getHeight(), matrix, true);
		        
		        background.setBitmap(b);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_draw_screen, menu);
        return true;
    }
}
