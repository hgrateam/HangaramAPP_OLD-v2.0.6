package com.Ateam.HangaramAPP;

import java.net.URL;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

public class Chk_UPDATE extends Activity {
	
	// BLUR 효과
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_BLUR_BEHIND,WindowManager.LayoutParams.FLAG_BLUR_BEHIND);


		new Handler();
		
			
			Toast t = Toast.makeText(this, "잠시만 기다려 주십시오...",
					Toast.LENGTH_SHORT);
			t.show();
			Log.i("Net", "대기타시오ㅋ");
		

		Try();
		// 최신 버전이 있는가 알려줌

	}

	public void Try(){
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
		
				if (parser.getName().equals("inf")) {
					String pver = parser.getAttributeValue(null, "version");
					String pmessage = parser.getAttributeValue(null,
							"message");
					Log.i("Net", "찾음ㅋ");

					if (pver.equals(getString(R.string.app_ver)) == true) {
						Toast toast = Toast.makeText(this, "최신 버전의 상태 입니다",
								Toast.LENGTH_SHORT);
						toast.show();
						Log.i("Net", "최신버전ㅋ");
					} else {
						Toast toast = Toast.makeText(this, pmessage,
								Toast.LENGTH_SHORT);
						toast.show();
						Log.i("Net", "최신버전아님ㅋ");
					}
				}
			} else if(parser.getEventType() == XmlPullParser.TEXT) {
//				Toast toast = Toast.makeText(this, "서버가 응답하지 않습니다",
//						Toast.LENGTH_SHORT);
//				toast.show();
//				Log.i("Net", "서버가삐짐ㅋ");
			}
			
			parserEvent = parser.next();
		}
		
	} catch (Exception e) {
		Toast toast = Toast.makeText(this, "네트워크 상태를 확인하시기 바랍니다.",
				Toast.LENGTH_SHORT);
		toast.show();
		Log.i("Net", "네트워크에 연결하시오 ㅋ");
		}
		
		// 안됨ㅋ
	
	finish();
	}
}
