package com.ionicframework.myapp235890;

import android.os.Bundle;
import org.apache.cordova.*;
import android.app.Activity;
import android.widget.ImageView;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import android.hardware.Camera;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.graphics.PixelFormat;
import android.hardware.Camera.PictureCallback;
import android.hardware.Camera.AutoFocusCallback;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
 /**try*/
import android.widget.Toast;
import android.webkit.JavascriptInterface;
 import android.content.Intent;
 
public class MainActivity extends CordovaActivity implements SurfaceHolder.Callback{
 SurfaceHolder surfaceHolder;
 SurfaceView surfaceView1;
 Button scan;
 ImageView imageView;
 Camera camera;
 static boolean onUrl = false;
 
  
    @Override
    public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if(onUrl)
			loadUrl(launchUrl);
		else{
			setContentView(R.layout.activity_main);
			scan=(Button)findViewById(R.id.scan1);
			imageView=(ImageView)findViewById(R.id.imgcaptured);
			surfaceView1=(SurfaceView)findViewById(R.id.camera_inside);
        }
    }
	
	 @Override
    public void onResume(){
		super.onResume();
		if(!onUrl){
			camera=Camera.open();
			
			//在AndroidManifest.xml中設定或是用下面的setRequestedOrientation(0)設定也可以
			//0代表橫向、1代表縱向
			//setRequestedOrientation(0);
			//設為横向顯示。因為攝影頭會自動翻轉90度，所以如果不横向顯示，看到的畫面就是翻轉的。
			
			surfaceHolder=surfaceView1.getHolder();
			surfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
			surfaceHolder.setSizeFromLayout();
			surfaceHolder.addCallback(this);
			scan.setOnClickListener(new OnClickListener(){
	  
			   public void onClick(View v) {
				 
				//自動對焦
				camera.autoFocus(afcb);
				scan.setEnabled(false);
				//imageView.setImageResource(R.drawable.building);
				
				//error occured before loadUrl
				//surfaceDestroyed(surfaceHolder);
				//onUrl = true;
				//loadUrl(launchUrl);
				/**have some try*/
				//appView.addJavascriptInterface(new IJavascriptHandler(), "cpjs");
				//appView.loadUrl("javascript:androidResponse();void(0)");
			   }});
		}
    }
	
	public boolean restartCamera(){
		onUrl = false;
		//camera=Camera.open();
		scan.setEnabled(true);
		/*
		if(this.appView != null){
			loadUrl("about:blank");
			//this.keepRunning = false;
			//appView.handleStop();
			appView = null;
		}
		*/
		
		Intent intent = getIntent();
		//loadUrl("about:blank");
		finish();
		startActivity(intent);
		return true;
		
	}
	/*
    PictureCallback jpeg =new PictureCallback(){
  
  public void onPictureTaken(byte[] data, Camera camera) {
    
   Bitmap bmp=BitmapFactory.decodeByteArray(data, 0, data.length);
   //byte數组轉換成Bitmap
   imageView.setImageBitmap(bmp);
   //拍下圖片顯示在下面的ImageView裡
        Date curDateTime = new Date();
        SimpleDateFormat sdFormat = new SimpleDateFormat("yyyy-MM-dd HH.mm.ss");
        String fileName = "checking_" + sdFormat.format(curDateTime) + ".jpg";
   FileOutputStream fop;
   try {
    fop=new FileOutputStream("/sdcard/"+fileName);
    //實例化FileOutputStream，參數是生成路徑
    bmp.compress(Bitmap.CompressFormat.JPEG, 100, fop);
    //壓缩bitmap寫進outputStream 參數：輸出格式  輸出質量  目標OutputStream
    //格式可以為jpg,png,jpg不能存儲透明
    fop.close();
    System.out.println("拍照成功");
    //關閉流
   } catch (FileNotFoundException e) {
     
    e.printStackTrace();
    System.out.println("FileNotFoundException");
  
   } catch (IOException e) {
     
    e.printStackTrace();
    System.out.println("IOException");
   }
   camera.startPreview();
   //需要手動重新startPreview，否則停在拍下的瞬間
  }
  
    };*/
 

	@Override  
    public void surfaceChanged(SurfaceHolder holder, int format, int width,  int height) {   
	  try {
	   Camera.Parameters parameters=camera.getParameters();
	   parameters.setPictureFormat(PixelFormat.JPEG);
	   //parameters.setPreviewSize(320, 220);
	   camera.setParameters(parameters);
	   //set parameter
	   camera.setPreviewDisplay(holder);
	   //change the camera to portscape
	   camera.setDisplayOrientation(90);
	   camera.startPreview();
	  } catch (IOException e) {
		
	   e.printStackTrace();
	  }
    }  
   @Override
 public void surfaceCreated(SurfaceHolder holder) {
  /*
  camera=Camera.open();
  try {
  
   Camera.Parameters parameters=camera.getParameters();
   parameters.setPictureFormat(PixelFormat.JPEG);
   parameters.setPreviewSize(320, 220);
   camera.setParameters(parameters);
   //設置參數
   camera.setPreviewDisplay(holder);
   //鏡頭的方向和手機相差90度，所以要轉向
   //camera.setDisplayOrientation(90);
   //攝影頭畫面顯示在Surface上
   camera.startPreview();
  } catch (IOException e) {
    
   e.printStackTrace();
  }*/
 }
  @Override 
 public void surfaceDestroyed(SurfaceHolder holder) {
   
  System.out.println("surfaceDestroyed");
  //if(camera != null){
	  camera.stopPreview();
	  //關閉預覽
	  camera.release();
  //}
  // 
 }

    PictureCallback jpeg =new PictureCallback(){
  
  public void onPictureTaken(byte[] data, Camera camera) {
    
   Bitmap bmp=BitmapFactory.decodeByteArray(data, 0, data.length);
   //byte數组轉換成Bitmap
   imageView.setImageBitmap(bmp);
   //拍下圖片顯示在下面的ImageView裡
   /*
        Date curDateTime = new Date();
        SimpleDateFormat sdFormat = new SimpleDateFormat("yyyy-MM-dd HH.mm.ss");
        String fileName = "checking_" + sdFormat.format(curDateTime) + ".jpg";
   */
   FileOutputStream fop;
   try {
	//????how to display in the sony image previewer
	fop=new FileOutputStream("/sdcard/dd.jpg");
    //fop=new FileOutputStream("/sdcard/"+fileName);
    //實例化FileOutputStream，參數是生成路徑
    bmp.compress(Bitmap.CompressFormat.JPEG, 100, fop);
    //壓缩bitmap寫進outputStream 參數：輸出格式  輸出質量  目標OutputStream
    //格式可以為jpg,png,jpg不能存儲透明
    fop.close();
     Toast.makeText(MainActivity.this, "saved",Toast.LENGTH_LONG).show();
	//關閉流
   } catch (FileNotFoundException e) {
    e.printStackTrace();
    Toast.makeText(MainActivity.this, "filenotfind", Toast.LENGTH_LONG).show();
   } catch (IOException e) {
     
    e.printStackTrace();
    System.out.println("IOException");
   }
   	onUrl = true;
	loadUrl(launchUrl);
   //camera.startPreview();
   //需要手動重新startPreview，否則停在拍下的瞬間
  }
  
    };
 
 //自動對焦監聽式
 AutoFocusCallback afcb= new AutoFocusCallback(){
  
  public void onAutoFocus(boolean success, Camera camera) {
    
   if(success){
    //對焦成功才拍照
    camera.takePicture(null, null, jpeg);
	Toast.makeText(MainActivity.this, "takePicture",Toast.LENGTH_LONG).show();
   }
  }
  
  
 };
}