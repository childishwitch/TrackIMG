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
/**connect to server*/
import java.net.Socket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.io.DataOutputStream;
import java.io.ByteArrayOutputStream;
import java.lang.Exception;
import android.os.StrictMode;
import java.nio.ByteBuffer;
/**create a server*/
import java.net.ServerSocket;
import java.io.InputStream;
/**http client*/
import java.io.BufferedReader;
import java.io.InputStreamReader;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
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
 public String myJSONString = "";
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
			if (android.os.Build.VERSION.SDK_INT > 9) {
				StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
				StrictMode.setThreadPolicy(policy);
			}
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
   Socket socket;
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
	System.out.println("saved");
	//InetAddress serverAdd = InetAddress.getByName("10.6.56.151");
	//InetAddress serverAdd = InetAddress.getByName("163.21.235.61");
	InetAddress serverAdd = InetAddress.getByName("59.115.218.159");
	System.out.println("InetAddress OK");
	SocketAddress sc_add = new InetSocketAddress(serverAdd,5000);
	System.out.println("socketaddress OK");
	socket = new Socket();
	//InetAddress androidAdd = InetAddress.getByName("192.168.2.7");
	//socket = new Socket("36.227.138.35",5000,androidAdd,5000);
	System.out.println("new socket");
	socket.connect(sc_add,2000);
	System.out.println("set time: "+socket.getPort()+" "+socket.getLocalSocketAddress()+" "+socket.getRemoteSocketAddress());
	System.out.println(socket.toString());
	/*
	PrintWriter out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
	out.print("Hi, Jay!\n");
	out.flush();
	*/
	DataOutputStream out = new DataOutputStream(socket.getOutputStream());
    out.flush();
	System.out.println("out create");
	ByteArrayOutputStream baos = new ByteArrayOutputStream();
	System.out.println("baos create");
	bmp.compress(Bitmap.CompressFormat.JPEG, 100, baos);
	System.out.println("bmp compress");
	byte[] bytes = baos.toByteArray();
	System.out.println("to array");
	byte[] newByte = ByteBuffer.allocate(4).putInt(bytes.length).array();
	System.out.println("file size: "+bytes.length+"\n bytes length: "+newByte.length);
	out.write(newByte);
	out.write(bytes);
	out.flush();
	System.out.println("flush out");
	//out.close(); <- server will close this stream
	System.out.println("close out");
	//socket.connect(sc_add,2000);
	
	InputStream inputStream = socket.getInputStream();
	System.out.println("get input");
	byte buffer[] = new byte[1024 * 4];
	int temp = 0;
	// 從InputStream當中讀取客戶端所發送的數據
	
	while ((temp = inputStream.read(buffer)) != -1) {
		System.out.println(new String(buffer, 0, temp));
		myJSONString += new String(buffer, 0, temp);
	}
	
	System.out.println("write over");
	
	socket.close();
	System.out.println("close socket");
	//serversocket
	//Thread t = new thread();
	//t.start();
	
	//http
	//String url = "http://163.21.235.61:5000";
	/*
        // check if you are connected or not
        if(isConnected()){
            tvIsConnected.setBackgroundColor(0xFF00CC00);
            tvIsConnected.setText("You are conncted");
        }
        else{
            tvIsConnected.setText("You are NOT conncted");
        }*/
 
    // call AsynTask to perform network operation on separate thread
    //new HttpAsyncTask().execute(url);

   } catch (FileNotFoundException e) {
    e.printStackTrace();
	System.out.println(e.toString());
    //Toast.makeText(MainActivity.this, "filenotfind", Toast.LENGTH_LONG).show();
   } catch (UnknownHostException e){
	e.printStackTrace();
	System.out.println(e.toString());
   } catch (SocketException e) {
	e.printStackTrace();
	System.out.println(e.toString());
   } catch (IOException e) {
    e.printStackTrace();
	System.out.println(e.toString());
	//Toast.makeText(MainActivity.this, e.toString(), Toast.LENGTH_LONG).show();
   } catch (Exception e){
	e.printStackTrace();
	System.out.println("some else: "+e.toString());
   }
   		myJSONString = "{\"items\": [{\"value\": 4.567401619860497, \"text\": \"\u82f1\u96c4\"}, {\"value\": 3.9425907538571296, \"text\": \"\u904a\u6232\"}, {\"value\": 3.3218091146784494, \"text\": \"\u5b98\u65b9\"}, {\"value\": 2.4552017601000378, \"text\": \"\u653b\u7565\"}, {\"value\": 2.0474363460659375, \"text\": \"\u66f4\u65b0\"}, {\"value\": 1.9091345934520476, \"text\": \"\u806f\u76df\"}, {\"value\": 1.836866560249032, \"text\": \"\u4e2d\u5fc3\"}, {\"value\": 1.5869972927321079, \"text\": \"\u65b0\u805e\"}, {\"value\": 1.4278583209634514, \"text\": \"\u5be6\u6cc1\"}, {\"value\": 1.3085430613891669, \"text\": \"\u96fb\u7af6\"}]}";
	//ServerReceviedByTcp();
   	System.out.println("FINISHED");
	onUrl = true;
	loadUrl(launchUrl);
   //camera.startPreview();
   //需要手動重新startPreview，否則停在拍下的瞬間
  }
  /*http
    public String GET(String url){
        InputStream inputStream = null;
        String result = "";
        try {
 
            // create HttpClient
            HttpClient httpclient = new DefaultHttpClient();
			System.out.println("create default http client");
 
            // make GET request to the given URL
            HttpResponse httpResponse = httpclient.execute(new HttpGet(url));
			System.out.println("make http response");
 
            // receive response as inputStream
            inputStream = httpResponse.getEntity().getContent();
			System.out.println("get inputStream");
 
            // convert inputstream to string
            if(inputStream != null)
                result = convertInputStreamToString(inputStream);
            else
                result = "Did not work!";
			System.out.println("Get over");
 
        } catch (Exception e) {
            System.out.println("InputStream: "+e.getLocalizedMessage());
        }
 
        return result;
    }
 
    private String convertInputStreamToString(InputStream inputStream) throws IOException{
        BufferedReader bufferedReader = new BufferedReader( new InputStreamReader(inputStream));
        String line = "";
        String result = "";
        while((line = bufferedReader.readLine()) != null)
            result += line;
 
        inputStream.close();
        return result;
 
    }*/
 /*
    public boolean isConnected(){
        ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Activity.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
            if (networkInfo != null && networkInfo.isConnected()) 
                return true;
            else
                return false;   
    }*/
	/*http
    class HttpAsyncTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... urls) {
			System.out.println("doInBackground");
            return GET(urls[0]);
        }
        // onPostExecute displays the results of the AsyncTask.
        @Override
        protected void onPostExecute(String result) {
            System.out.println("Received!");
            System.out.println(result);
       }
    }*/
	/*serversocket
  class thread extends Thread{
  public void run(){
	ServerReceviedByTcp();
  }
  public void ServerReceviedByTcp() {
		// 聲明一個ServerSocket對象
		ServerSocket serverSocket = null;
		boolean run = true;
		while(run){
		try {
			// 創建一個ServerSocket對象，並讓這個Socket在1989端口監聽
			serverSocket = new ServerSocket(5000);
			System.out.println("server socket created");
			// waiting for socket connection
			Socket socket = serverSocket.accept();
			System.out.println("Accepted connection from "+socket.getInetAddress().getHostAddress());
			InputStream inputStream = socket.getInputStream();
			byte buffer[] = new byte[1024 * 4];
			int temp = 0;
			// 從InputStream當中讀取客戶端所發送的數據
			while ((temp = inputStream.read(buffer)) != -1) {
				System.out.println(new String(buffer, 0, temp));
			}
			run = false;
			serverSocket.close();
		} catch (IOException e) {
			System.out.println("IOExcpetion");
			e.printStackTrace();
		}
		}
	}
	}*/
  
    };
 
 //自動對焦監聽式
 AutoFocusCallback afcb= new AutoFocusCallback(){
  
  public void onAutoFocus(boolean success, Camera camera) {
    
   if(success){
    //對焦成功才拍照
    camera.takePicture(null, null, jpeg);
	System.out.println("take picture");
	Toast.makeText(MainActivity.this, "takePicture",Toast.LENGTH_LONG).show();
   }
  }
  
  
 };
}