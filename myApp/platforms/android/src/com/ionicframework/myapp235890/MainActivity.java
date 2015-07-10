package com.ionicframework.myapp235890;

import android.os.Bundle;
import org.apache.cordova.*;

import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class MainActivity extends CordovaActivity{

	private static boolean waiting = false;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.camera);
		
		if(!waiting){
			//call the camera
			Intent intent_camera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
			startActivityForResult(intent_camera, 0);
			waiting = true;
		}
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
		waiting = false;
        if(resultCode == RESULT_OK)
        {
            ImageView iv = (ImageView)findViewById(R.id.imgcaptured);
			Bundle extras = data.getExtras();
			//transfer data into a bitmap format
			Bitmap bitmap = (Bitmap)extras.get("data");
			//show the image in the imageview
			iv.setImageBitmap(bitmap);
        }
		else
		{	//cancel
		}
		super.onActivityResult(requestCode, resultCode, data);
    }
}