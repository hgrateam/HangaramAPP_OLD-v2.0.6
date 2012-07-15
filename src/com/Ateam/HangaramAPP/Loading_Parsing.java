package com.Ateam.HangaramAPP;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class Loading_Parsing extends Activity {

	ProgressDialog dialog;
	private Handler mHandler;
    private ProgressDialog mProgressDialog;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		
		mHandler = new Handler();
		 
        runOnUiThread(new Runnable()
        {
            public void run()
            {
				int proIncr = 100;
            	mProgressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                mProgressDialog.setMax(100);
                mProgressDialog.incrementProgressBy(proIncr);
                mProgressDialog.incrementSecondaryProgressBy(5);
                mProgressDialog.setCancelable(false);
                mProgressDialog.setMax(100);
                mProgressDialog = ProgressDialog.show(Loading_Parsing.this,"", 
                        "잠시만 기다려 주세요.",true);
                
                mHandler.postDelayed( new Runnable()
                {
                    public void run()
                    {
                        try
                        {
                            if (mProgressDialog!=null&&mProgressDialog.isShowing()){
                                mProgressDialog.dismiss();
                            }
                        }
                        catch ( Exception e )
                        {
                            e.printStackTrace();
                        }
                    }
                }, 30000);
            }
        } );
        Parsing();
		
		
	}
	public void Parsing() {
		startActivity(new Intent(Loading_Parsing.this, Parsing.class));
		finish();
	}
}
