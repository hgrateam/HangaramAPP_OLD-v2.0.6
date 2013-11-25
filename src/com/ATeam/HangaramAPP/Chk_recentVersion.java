package com.ATeam.HangaramAPP;

import java.net.URL;

import org.holoeverywhere.app.AlertDialog;
import org.holoeverywhere.widget.Toast;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import com.ATeam.HangaramAPP.Today_meal.LoadingParsing;



import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.text.Html;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

public class Chk_recentVersion {
	Toast mToast;
	//boolean isConnected;
	public static String ptitle;
	//int IsAPhone = 0;
	private Context mContext;
	static String pmessage = null;
	
	// BLUR 효과
	
	public Chk_recentVersion(Context context){
		
		this.mContext = context;
	}
	
	
	public Chk_recentVersion ChkNotice(boolean isConnected, int IsAPhone) {
		Log.i("Net","ChkNotice()가 실행됐당께?");
		//new Handler();

		DbAdapter myHelper;
		myHelper = new DbAdapter(mContext);

		myHelper.open("WRITE");
		myHelper.create("version_check");
		myHelper.close();

		String nowversion =  mContext.getString(R.string.$app_ver);
		myHelper.open("READ");
		String finalversion = myHelper.getid("version_check", "versionid");
		myHelper.close();
		// 처음으로 실행되었다.

		if (finalversion.equals("nodata") == true) {

			myHelper.open("WRITE");
			myHelper.update("version_check", 1, "versionid", nowversion);
			myHelper.close();
		} else {
			if (finalversion.equals(nowversion) != true) {
				showToast("기존 버전 " + finalversion + "에서 " + nowversion
						+ " 으로 변경 되었습니다.");
			}
			myHelper.open("WRITE");
			Log.i("Net", "쓰기 준비중");
			Cursor cursor = myHelper.fetchd("version_check");
			Log.i("Net", "version_check 정보 들고옴");
			cursor.moveToFirst();
			Log.i("Net", "커서초기화");
			int i = 1;
			boolean flag = false;
			do {
				if (cursor.getString(1).equals(finalversion) == true) {
					myHelper.update("version_check", i, "versionid", nowversion);
					break;
				}
				i++;
			} while (cursor.moveToNext());
			myHelper.close();
		}
		if (isConnected == true || IsAPhone == 0) {
		
		//new noticeGetter().execute();
			checknotice();
			Try(mContext);
		
		} else {
			ptitle = mContext.getString(R.string.NetStat_error);
			
		}
		return this;
		
		
		
	}

	public void showToast(String str) {
		if (mToast == null) {
			mToast = Toast.makeText(mContext, str,
					Toast.LENGTH_SHORT);
		} else {
			mToast.setText(str);
		}
		mToast.show();

	}
	

	
	public void checknotice() {
		
			try {
				URL url = new URL("http://bluepeal.raonnet.com/inf.xml");
				Log.i("Net", "파싱 시작");
				XmlPullParserFactory parserCreator = XmlPullParserFactory
						.newInstance();
				XmlPullParser parser = parserCreator.newPullParser();

				parser.setInput(url.openStream(), null);

				int parserEvent = parser.getEventType();
				while (parserEvent != XmlPullParser.END_DOCUMENT) {
					if (parserEvent == XmlPullParser.START_TAG) {
						if (parser.getName().equals("notice")) {
							ptitle = parser.getAttributeValue(null, "title");

							Log.d("Net", "AsyncTask 내부 - checknotice()실행");
							

						}
					} else if (parser.getEventType() == XmlPullParser.TEXT) {
					}
					parserEvent = parser.next();
				}
			} catch (Exception e) {
				// 안됨ㅋ
			}
		
		// 공지사항을 파싱해 온다
		Log.i("Net", "공지사항 : " + ptitle);
	}
	
	

	public void Try(Context con) {
		Log.i("Net","Try()가 실행됐당께?");
		try {
			URL url = new URL("http://bluepeal.raonnet.com/inf.xml");
			Log.i("Net", "파싱 시작");
			XmlPullParserFactory parserCreator = XmlPullParserFactory
					.newInstance();
			XmlPullParser parser = parserCreator.newPullParser();

			parser.setInput(url.openStream(), null);
			Log.i("Net","Try(try())가 실행됐당께?");
			int parserEvent = parser.getEventType();
			while (parserEvent != XmlPullParser.END_DOCUMENT) {

				if (parserEvent == XmlPullParser.START_TAG) {

					if (parser.getName().equals("inf")) {

						pmessage = parser.getAttributeValue(null,
								"message_new");
						
						pmessage = pmessage.replace("{","<");
						pmessage = pmessage.replace("}",">");
						pmessage = pmessage.replace("|",con.getResources().getString(R.string.mal));
						
						Log.i("Net", "pmessage는" +pmessage);
						
						/*
						
						AlertDialog.Builder ab=new AlertDialog.Builder(Chk_recentVersion.this);
					 	ab.setMessage(Html.fromHtml(pmessage));
					 		ab.setPositiveButton("확인", null);
							ab.setTitle("공지사항"); 
							ab.setIcon(R.drawable.ic_launcher);
					 		ab.show();
					*/
					}
				} else if (parser.getEventType() == XmlPullParser.TEXT) {
					// Toast toast = Toast.makeText(this, "서버가 응답하지 않습니다",
					// Toast.LENGTH_SHORT);
					// toast.show();
					// Log.i("Net", "서버가삐짐ㅋ");
				}

				parserEvent = parser.next();
			}

		} catch (Exception e) {
			Log.i("Net", "공지사항 받으려면 네트워크에 연결하시오 ㅋ");
		}
		
		
		// 안됨ㅋ
	}
}
