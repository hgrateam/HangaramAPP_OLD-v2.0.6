package com.ATeam.HangaramAPP;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import android.app.PendingIntent;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import com.ATeam.HangaramAPP.Today_meal.LoadingParsing;

import android.app.ProgressDialog;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.RemoteViews;

public class BabWidgetProvider extends AppWidgetProvider {

	int Y, M, D, H, mwidgetid;
	Context mcontext;
	String showbabtxt;
	RemoteViews mviews;
	AppWidgetManager mwidgetmanager;
	boolean isConnected, isError;

	/*
	 * @Override public void onReceive(Context context, Intent intent) { String
	 * action = intent.getAction(); if
	 * (AppWidgetManager.ACTION_APPWIDGET_UPDATE.equals(action)) { Bundle extras
	 * = intent.getExtras(); if (extras != null) { int[] appWidgetIds = extras
	 * .getIntArray(AppWidgetManager.EXTRA_APPWIDGET_IDS); if (appWidgetIds !=
	 * null && appWidgetIds.length > 0) { this.onUpdate(context,
	 * AppWidgetManager.getInstance(context), appWidgetIds); } } } else if
	 * (AppWidgetManager.ACTION_APPWIDGET_DELETED.equals(action)) { Bundle
	 * extras = intent.getExtras(); if (extras != null &&
	 * extras.containsKey(AppWidgetManager.EXTRA_APPWIDGET_ID)) { final int
	 * appWidgetId = extras .getInt(AppWidgetManager.EXTRA_APPWIDGET_ID);
	 * this.onDeleted(context, new int[] { appWidgetId }); } } else if
	 * (AppWidgetManager.ACTION_APPWIDGET_ENABLED.equals(action)) {
	 * this.onEnabled(context); } else if
	 * (AppWidgetManager.ACTION_APPWIDGET_DISABLED.equals(action)) {
	 * this.onDisabled(context); } }
	 */
	
	@Override
	public void onUpdate(Context context, AppWidgetManager appWidgetManager,
			int[] appWidgetIds) {

		appWidgetIds = appWidgetManager.getAppWidgetIds(new ComponentName(
				context, getClass()));
		super.onUpdate(context, appWidgetManager, appWidgetIds);

		final int N = appWidgetIds.length;

		for (int i = 0; i < N; i++) {
			int appWidgetId = appWidgetIds[i];
			updateAppWidget(context, appWidgetManager, appWidgetId);
		}

	}

	@Override
	public void onEnabled(Context context) {
		PackageManager pm = context.getPackageManager();
		pm.setComponentEnabledSetting(new ComponentName(
				"com.ATeam.HangaramAPP", "com.ATeam.HangaramAPP.BabWidgetProvider"),
				PackageManager.COMPONENT_ENABLED_STATE_ENABLED,
				PackageManager.DONT_KILL_APP);

	}

	public void updateAppWidget(Context context,
			AppWidgetManager appWidgetManager, int appWidgetId) {

		long now = System.currentTimeMillis();
		Date date = new Date(now);
		SimpleDateFormat sdfNow = new SimpleDateFormat("HH");
		H = Integer.valueOf(sdfNow.format(date));

		isError = false;
		mcontext = context;
		RemoteViews views = new RemoteViews(context.getPackageName(),
				R.layout.bablayout);
		publishshowbab();

		if (isError == true)
			views.setTextViewText(R.id.showbab, showbabtxt);
		else {
			if (H >= 0 && H <= 13) {
				views.setTextViewText(R.id.showbab, "<" + M + "/" + D
						+ "/ " + "점심>\n" + showbabtxt);
			} else {
				views.setTextViewText(R.id.showbab, "<" + M + "/" + D
						+ "/ " + "저녁>\n" + showbabtxt);
			}
		}
		Intent intent = new Intent();
		intent.addCategory(Intent.CATEGORY_BROWSABLE);
		intent.setComponent(new ComponentName("com.ATeam.HangaramAPP",
				"com.ATeam.HangaramAPP.Today_meal"));

		PendingIntent pendingintent = PendingIntent.getActivity(context, 0,
				intent, 0);
		views.setOnClickPendingIntent(R.id.showbab, pendingintent);

		appWidgetManager.updateAppWidget(appWidgetId, views);

	}

	public class LoadingParsing extends AsyncTask<Void, Void, Void> {

		protected void onCancelled() {
			super.onCancelled();
		}

		@Override
		protected void onPreExecute() {
			Log.i("Net", "[위젯]AsyncTask 시작 - 다이얼로그 시작");

		}

		@Override
		protected void onPostExecute(Void result) {
			Log.i("Net", "[위젯]AsyncTask 종료 - 다이얼로그 종료");

		}

		@Override
		protected Void doInBackground(Void... arg0) {
			Log.i("Net", "AsyncTask 백그라운드에서 작업중");
			Calendar calendar = Calendar.getInstance();
			int Y, M, D, recdate;
			Y = calendar.get(Calendar.YEAR);
			M = calendar.get(Calendar.MONTH);
			M++;
			D = calendar.get(Calendar.DAY_OF_MONTH);
			BabParse updater = new BabParse(mcontext);

			updater.parse(Y, M, true);

			// mProgressDialog.dismiss();
			// TODO Auto-generated method stub
			return null;
/*			Calendar calendar = Calendar.getInstance();
			int Y, M, D, recdate;
			Y = calendar.get(Calendar.YEAR);
			M = calendar.get(Calendar.MONTH);
			M++;
			D = calendar.get(Calendar.DAY_OF_MONTH);
			String todate = null;
			todate = Integer.toString(Y * 10000 + (M) * 100 + D);

			DbAdapter myHelper = null;
			myHelper = new DbAdapter(mcontext);

			String pdate, plunch, pdinner;
			pdate = "NP";

			myHelper.open("READ");
			Cursor cursor = myHelper.fetchd("bab");
			int cnt = cursor.getCount();
			if (myHelper.getid("bab", "isbabcheck").equals("nodata") == true) {
				recdate = 0;
			} else {
				recdate = Integer.valueOf(myHelper.getid("bab", "recdate"));
			}
			myHelper.close();
			myHelper.open("WRITE");
			try {
				// Log.i("Net", "bab 테이블 최근 갱신 날짜 : " + recdate);
				URL url = new URL("http://bluepeal.raonnet.com/sch.xml");

				XmlPullParserFactory parserCreator = XmlPullParserFactory
						.newInstance();
				XmlPullParser parser = parserCreator.newPullParser();

				parser.setInput(url.openStream(), null);

				int parserEvent = parser.getEventType();
				while (parserEvent != XmlPullParser.END_DOCUMENT) {
					if (parserEvent == XmlPullParser.START_TAG) {
						if (parser.getName().equals("inf")) {
							pdate = parser.getAttributeValue(null, "date");
							plunch = parser.getAttributeValue(null, "lunch");
							pdinner = parser.getAttributeValue(null, "dinner");
							// row name = pdate+.lunch or pdata.dinner
							if (plunch.equals("NULL") != true) {
								if (Integer.valueOf(pdate) > Integer
										.valueOf(recdate)) {
									myHelper.insertd("bab", pdate + ".lunch",
											plunch);
									myHelper.insertd("bab", pdate + ".dinner",
											pdinner);
								}
							}
						}

					} else if (parser.getEventType() == XmlPullParser.TEXT) {

					}
					parserEvent = parser.next();
				}
			} catch (Exception e) {
				// 오류
			}

			myHelper.close();
			myHelper.open("WRITE");
			Log.i("Net", "쓰기 준비중");
			cursor = myHelper.fetchd("bab");
			Log.i("Net", "bab 정보 들고옴");
			cursor.moveToFirst();
			Log.i("Net", "커서초기화");
			int i = 1;
			boolean flag = false;
			do {
				if (cursor.getString(1).equals("recdate") == true) {
					Log.i("Net", i + "번째 정보 클리어, recdate");

					myHelper.update("bab", i, "recdate", pdate);
				} else if (cursor.getString(1).equals("isbabcheck") == true) {
					Log.i("Net", i + "번째 정보 클리어, isbabcheck");
					myHelper.update("bab", i, "isbabcheck", "true");
					flag = true;
				}

				i++;
			} while (cursor.moveToNext());

			if (flag == false) {
				myHelper.insertd("bab", "recdate", pdate);
				myHelper.insertd("bab", "isbabcheck", "true");
			}

			myHelper.close();

			// mProgressDialog.dismiss();
			// TODO Auto-generated method stub
			return null;
			*/
		}

	}

	public void publishshowbab() {

		NetStatCHK();
		Calendar calendar = Calendar.getInstance();

		Y = calendar.get(Calendar.YEAR);
		M = calendar.get(Calendar.MONTH);
		D = calendar.get(Calendar.DAY_OF_MONTH);
		M++;
		String strdate = Integer.toString(Y * 10000 + (M) * 100 + D);
		;
		DbAdapter myHelper = null;
		myHelper = new DbAdapter(mcontext);

		myHelper.open("READ");

		Cursor cursor = myHelper.fetchd("bab");
		int cnt = cursor.getCount();
		boolean Isfinddata = false;
		Log.i("Net", "위젯] " + cnt + "개의 DB 가 존재한다.");
		cursor.moveToFirst();
		for (int i = 0; i < cnt; i++) {
			Log.i("Net", "위젯] " + cursor.getString(1));
			if (H >= 0 && H <= 13) {
				if (cursor.getString(1).equals(strdate + ".lunch")) {
					showbabtxt = cursor.getString(2);
					Isfinddata = true;
					break;
				}
			} else {
				if (cursor.getString(1).equals(strdate + ".dinner")) {
					showbabtxt = cursor.getString(2);
					Isfinddata = true;
					break;
				}

			}
			cursor.moveToNext();
		}
		myHelper.close();

		if (Isfinddata == false) {

			if (getYI(Y, M, D) == 0 || getYI(Y, M, D) == 6) {
			showbabtxt = "오늘은 휴일입니다.";
			return;
			}
			if (isConnected == false) {
			isError = true;
			showbabtxt = "네트워크가 연결되어 있지 않습니다. 30분뒤에 재시도 됩니다.";
			return;
			}

			isError = true;
			showbabtxt = "급식 정보가 존재하지 않습니다. 급식 업데이트를 시작합니다. 잠시만 기다려주세요.";
			new LoadingParsing().execute();
			return;
			}

	}

	public int getYI(int Y, int M, int D) {

		int a, b, c, d;
		if (M < 3) {
			a = ((Y - 1) / 100);
			b = (Y - 1) % (a * 100);
			c = 12 + M;
			d = D;
		} else {
			a = ((Y) / 100);
			b = (Y) % (a * 100);
			c = M;
			d = D;
		}
		int e = (((21 * a) / 4) + (5 * b / 4) + (26 * (c + 1) / 10) + d - 1) % 7;
		return e;

	}

	public void NetStatCHK() {

		int IsAPhone = 0;
		
		ConnectivityManager manager = (ConnectivityManager) mcontext
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo mobile = manager
				.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
		NetworkInfo wifi = manager
				.getNetworkInfo(ConnectivityManager.TYPE_WIFI);

		Log.i("Net", "isConnected는 " + isConnected);

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

	}

}

/*
 * . OnUpdate() : 위젯이 사용자가 지정한 시간이 되어 자동으로 업데이트 되는 경우에 호출되거나 위젯을 업데이트 해야하는 경우가
 * 발생할 때 호출됩니다. 2. onDelete() : 위젯이 바탕화면에서 삭제될 때 호출됩니다. 3. onEnabled() :
 * HOST(바탕화면)에 위젯이 처음 추가될 경우 호출됩니다. 데이터베이스를 열거나 다른 추가 작업을 해줘야 하는 경우 이곳에 선언합니다.
 * 4. onDisabled() : 바탕화면에서 동일한 위젯이 여러개 설치되어 있다고 하면, 그 중 제일 마지막 위젯이 삭제된 후 호출됩니다.
 * 5. onReceive() : 바탕화면에 있는 모든 위젯의 이벤트를 수신합니다.
 */