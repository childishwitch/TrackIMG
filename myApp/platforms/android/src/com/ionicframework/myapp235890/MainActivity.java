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

    private static int TAKE_PICTURE = 1;
    private Uri imageUri;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.camera);

        //自訂照片名稱 開始
        Date curDateTime = new Date();
        SimpleDateFormat sdFormat = new SimpleDateFormat("yyyy-MM-dd HH.mm.ss");
        String fileName = "checking_" + sdFormat.format(curDateTime) + ".jpg";
        //自訂照面名稱 結束

        //自訂檔案位置 開始
        String root = Environment.getExternalStorageDirectory().toString();
        File mydir = new File(root + "/checking/image/");
        mydir.mkdirs();
        //自訂檔案位置 結束

        //啟動內建相機 並回傳圖片uri
        Intent intent_camera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        File photo = new File(mydir, fileName);
        intent_camera.putExtra(MediaStore.EXTRA_OUTPUT,Uri.fromFile(photo));
        imageUri = Uri.fromFile(photo);
        startActivityForResult(intent_camera, TAKE_PICTURE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        //super.onActivityResult(requestCode, resultCode, data);

        //拍照後顯示圖片
        if(resultCode == RESULT_OK)
        {
            Uri selectedImage = imageUri;
            getContentResolver().notifyChange(selectedImage,null);
            ImageView iv = (ImageView)findViewById(R.id.imgcaptured);
            ContentResolver cr = getContentResolver();
            Bitmap bitmap;
            try{
                bitmap = android.provider.MediaStore.Images.Media.getBitmap(cr,selectedImage);
                iv.setImageBitmap(bitmap);
                Toast.makeText(this, selectedImage.toString(),Toast.LENGTH_LONG).show();
            } catch (Exception e) {
                Toast.makeText(this, "Failed to load", Toast.LENGTH_SHORT).show();
                Log.e("Camera", e.toString());
            }
        }
    }
/*
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }*/
}
