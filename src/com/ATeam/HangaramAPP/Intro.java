package com.ATeam.HangaramAPP;

import java.net.URL;
import java.util.Calendar;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import android.app.Activity;
import android.app.ProgressDialog;

import com.ATeam.HangaramAPP.DbAdapter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

public class Intro extends Activity {
	public boolean flag1 = false;
	public boolean flag2 = false, isConnected;
	int IsAPhone = 0;
	int need = -1;
	Context mContext;
	
	DbAdapter myHelper;
	

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		myHelper = new DbAdapter(Intro.this);
		mContext = this;
		NetStatCHK();
		
		PreferenceManager.setDefaultValues(mContext, R.xml.config, false);
		
		new LoadingProcess().execute();
		Log.i("Net", "need값은"+need);
	}

	public class LoadingProcess extends AsyncTask<Void, Void, Void> {

		protected void onCancelled() {
			super.onCancelled();
		}

		@Override
		protected void onPreExecute() {
			Log.i("Net", "AsyncTask 시작 - 다이얼로그 시작");

			setContentView(R.layout.intro);

			myHelper.open("READ");
			if(!myHelper.isTableExists("config")){
				myHelper.close();
				myHelper.open("WRITE");
				myHelper.create("config");
				myHelper.create("bab");
				myHelper.create("sche");
				myHelper.create("sche_private");
				myHelper.close();
			}
			getMyTheme();

		}

		@Override
		protected void onPostExecute(Void result) {
			Log.i("Net", "AsyncTask 종료 - 다이얼로그 종료");
			
			if (flag2 == true) {
				startActivity(new Intent(Intro.this, Main_build.class));
				overridePendingTransition(R.anim.zoom_out_still,
						R.anim.zoom_out_exit);
				finish();
			} else if (flag2 == false) {
				startActivity(new Intent(Intro.this, Main.class));
				overridePendingTransition(R.anim.zoom_out_still,
						R.anim.zoom_out_exit);
				finish();
			}
			
		}

		@Override
		protected Void doInBackground(Void... arg0) {
			Log.i("Net", "AsyncTask 백그라운드에서 작업중");
			
			Chk_recentVersion myVersionHelper = new Chk_recentVersion(mContext);
			Fixmode fxmod = new Fixmode(mContext);
			/*
			myHelper.open("READ");
			String isnoticeupdate = myHelper.getid("config", "ISNOTICEUPDATE");
			String isfirsthelp = myHelper.getid("config", "ISFIRSTHELP");
			myHelper.close();
			
			if (isnoticeupdate.equals("true") == true) {
				Log.i("Net", "처음 시작시 업데이트를 확인합니다");
				flag1 = true;
				
			}
			if (isfirsthelp.equals("true") == true) {
				Log.i("Net", "처음 시작시 도움말을 표시합니다");
				flag2 = true;
			}
			*/
			
			SharedPreferences preference = PreferenceManager.getDefaultSharedPreferences(mContext);
			flag2 = preference.getBoolean("autostart_help_pref", true);
			
			myVersionHelper.ChkNotice(isConnected, IsAPhone);
			
			if (isConnected == true || IsAPhone == 0) {
				need = fxmod.fix();
			if (need == 1){
			myHelper.open("WRITE");
   			myHelper.create("bab");
   			myHelper.close();
   			myHelper.open("WRITE");
   			myHelper.deletetable("bab");
   			myHelper.close();
   			myHelper.open("WRITE");

   			myHelper.create("bab");
   			myHelper.close();
   			
   			Calendar calendar = Calendar.getInstance();
			int Y, M, D, recdate;
			Y = calendar.get(Calendar.YEAR);
			M = calendar.get(Calendar.MONTH);
			M++;
			D = calendar.get(Calendar.DAY_OF_MONTH);
			BabParse updater = new BabParse(mContext);

			updater.parse(Y, M, true);
			fxmod.fix();
			}
			}
			return null;

		}
	}
	
	@Override
	public void onBackPressed() {

	}

	public void getMyTheme() {
		RelativeLayout lay = ((RelativeLayout) findViewById(R.id.introLay));
		
		SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(this);
		String themeSet = pref.getString("theme_pref", "default");
		
		if (themeSet.equals("default") == true) {
			Log.i("Net", "기본 테마 적용");
			lay.setBackgroundResource(R.drawable.intro_default);
		} else if (themeSet.equals("hotpink") == true) {
			Log.i("Net", "핫핑쿠 테마 적용");
			lay.setBackgroundResource(R.drawable.intro_hotpink);
		} else if (themeSet.equals("green") == true) {
			Log.i("Net", "구린 테마 적용");
			lay.setBackgroundResource(R.drawable.intro_green);
		} else if (themeSet.equals("red") == true) {
			Log.i("Net", "빨간장미 테마 적용");
			lay.setBackgroundResource(R.drawable.intro_red);
		} else if (themeSet.equals("skyblue") == true) {
			Log.i("Net", "하늘색 테마 적용");
			lay.setBackgroundResource(R.drawable.intro_skyblue);
	}
	}
	
	public void NetStatCHK() {

		ConnectivityManager manager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo mobile = manager
				.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
		NetworkInfo wifi = manager
				.getNetworkInfo(ConnectivityManager.TYPE_WIFI);

		try {

			if (mobile != null) {
				if (mobile.isConnected() || wifi.isConnected()) {
					isConnected = true;
				} else {
					isConnected = false;
				}
				IsAPhone = 1;
			}
		} catch (Exception e) {
		}

		Log.i("Net", "isConnected는 " + isConnected);

	}

}