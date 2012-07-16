package com.ex.HangaramAPP;
	
import java.net.URL;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import android.app.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;



//public void onFinish();
//public void onTick(long millisUntilFinished);

public class Intro extends Activity {

	/** Called when the activity is first created. */

	
	@Override
	public void onCreate(Bundle savedInstanceState) {
	   super.onCreate(savedInstanceState);
       setContentView(R.layout.intro);
       Log.i("Net","Intro ¶ä¤»");

       
       
       Handler handler = new Handler() {
   	   public void handleMessage(Message message){
    		   super.handleMessage(message);
    		   startActivity(new Intent (Intro.this, Main.class));
    		   finish();
    	   }
    	   
       };
       handler.sendEmptyMessageDelayed(0, 1200);
	}
}
       