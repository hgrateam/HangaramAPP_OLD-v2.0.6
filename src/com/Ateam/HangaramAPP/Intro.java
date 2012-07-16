package com.Ateam.HangaramAPP;
	
import android.app.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;



//public void onFinish();`
//public void onTick(long millisUntilFinished);

public class Intro extends Activity {

	/** Called when the activity is first created. */

	Handler handler;
	
	
	public void onCreate(Bundle savedInstanceState) {
	   super.onCreate(savedInstanceState);
       setContentView(R.layout.intro);
       Log.i("Net","Intro ¶ä¤»");
       handler = new Handler();
       handler.postDelayed(irun, 4000);
	}
	
	Runnable irun = new Runnable(){
	 	   public void run(){
	 		   Intent i = new Intent (Intro.this, Main.class);
	 		   startActivity(i);
	 		   finish();
	 		   
	 	   }
	    };
	@Override
	public void onBackPressed(){
		super.onBackPressed();
		handler.removeCallbacks(irun);
		
	}
}
       