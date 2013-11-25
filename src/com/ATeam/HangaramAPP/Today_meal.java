package com.ATeam.HangaramAPP;

import java.io.IOException;
import java.net.URL;
import java.util.Calendar;

import org.holoeverywhere.app.AlertDialog;
import org.holoeverywhere.app.ProgressDialog;
import org.holoeverywhere.widget.Toast;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.text.Spannable;
import android.text.SpannableString;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class Today_meal extends ActionBarActivity {

	Toast mToast;
	ProgressDialog dialog;
	boolean isConnected;
	private ProgressDialog mProgressDialog;
	Context mContext;
	int IsAPhone = 0;
	boolean IsParsing = false;
	DbAdapter myHelper = null;

	/** Called when the activity is first created. */

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setTheme(R.style.TodayMeal);
		setContentView(R.layout.today_meal);
		mContext = this;
		myHelper = new DbAdapter(this);

		myHelper.open("WRITE");
		myHelper.create("bab");
		myHelper.close();
		myHelper.open("READ");
		
		setActionBar();
		setCustomFonts();
		Refresh();

	}

	public void setActionBar() {

		SpannableString s = new SpannableString(
				getString(R.string.Act_Today_meal));
		s.setSpan(new TypefaceSpan(this, "SeoulNamsanB.ttf"), 0, s.length(),
				Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

		final ActionBar ab = getSupportActionBar();
		ab.setDisplayOptions(ActionBar.DISPLAY_SHOW_HOME
				| ActionBar.DISPLAY_SHOW_TITLE);
		ab.setDisplayHomeAsUpEnabled(true);
		ab.setTitle(s);
		ab.setLogo(R.drawable.meal_w);
		ab.setDisplayUseLogoEnabled(true);

	}

	public void setCustomFonts(){
		TextView txt;
		Typeface sn = Typeface.createFromAsset(getAssets(),
				"fonts/SeoulNamsanB.ttf");
		txt = (TextView) findViewById(R.id.strlunch);
		txt.setTypeface(sn);
		txt = (TextView) findViewById(R.id.strdinner);
		txt.setTypeface(sn);
	}
	
	public boolean NetStatCHK() {

		ConnectivityManager manager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo mobile = manager
				.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
		NetworkInfo wifi = manager
				.getNetworkInfo(ConnectivityManager.TYPE_WIFI);

		try {

			if (mobile != null) {
				if (mobile.isConnected() || wifi.isConnected()) {
					return true;
				}
				return false;
			}
		} catch (Exception e) {
		}
		return false;
	}

	public void mod() {
		if (D == 1) {
			if ((M) == 1) {
				M = 12;
				D = 31;
				Y--;
			} else {
				M--;
				if ((M) == 2 || (M) == 4 || (M) == 6 || (M) == 9 || (M) == 11)
					D = 30;
				else
					D = 31;
			}
		} else
			D--;
	}

	public void showToast(String str) {
		if (mToast == null) {
			mToast = Toast.makeText(Today_meal.this, str, Toast.LENGTH_SHORT);
		} else {
			mToast.setText(str);
		}
		mToast.show();

	}

	public void pod() {

		Cursor cursor = myHelper.fetchd("bab");
		cursor.moveToFirst();

		if (cursor.getCount() != 0) {
			do {
				if (cursor.getString(1).equals("recdate")) {

					int raw = Integer.valueOf(cursor.getString(2));
					recdate = raw;
					break;
				}
			} while (cursor.moveToNext());
		}

		if (Integer.valueOf(recdate) <= Y * 10000 + M * 100 + D) {
			showToast("더 이상 급식정보가 존재하지 않습니다. 급식정보를 업데이트 해주세요.");
			// return;
		}
		if ((M) == 2 || (M) == 4 || (M) == 6 || (M) == 9 || (M) == 11) {
			if (D == 30) {
				D = 1;
				if ((M) == 12) {
					M = 1;
					Y++;
				} else
					M++;
			} else
				D++;
		} else {
			if (D == 31) {
				D = 1;
				if ((M) == 12) {
					M = 1;
					Y++;
				}

				else
					M++;
			} else
				D++;
		}
	}

	public void showDate() {
		TextView noticedate = (TextView) findViewById(R.id.dateview);
		noticedate.setText(Y + "년 " + M + "월 " + D + "일 " + YI[getYI(Y, M, D)]
				+ "요일");
		dd = Integer.toString(Y * 10000 + (M) * 100 + D);

	}

	public void Refresh() {

		Y = calendar.get(Calendar.YEAR);
		M = calendar.get(Calendar.MONTH);
		D = calendar.get(Calendar.DAY_OF_MONTH);
		M++;

		Log.i("Net", "일어낫다! " + Y + (M) + D);
		dd = Integer.toString(Y * 10000 + (M) * 100 + D);
		TextView noticedate = (TextView) findViewById(R.id.dateview);
		noticedate.setText(Y + "년 " + M + "월 " + D + "일 " + YI[getYI(Y, M, D)]
				+ "요일");

		update(dd);

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

	public void update(String dd2) {
		Log.i("Net", "update 가 실행되었습니다.");
		Mlunch = (TextView) findViewById(R.id.strlunch);
		Mdinner = (TextView) findViewById(R.id.strdinner);
		//
		Log.i("Net", dd2);

		Mlunch.setText("아직 서버에 해당 날짜 급식정보가 업로드되지 않았습니다.");
		Mdinner.setText("빠른 기한내로 업로드 하도록 하겠습니다.");
		Log.i("Net", dd + "일자 급식정보가 없습니다");

		intdate = (Y * 10000 + M * 100 + D);

		Cursor cursor = myHelper.fetchd("bab");

		if (myHelper.getid("bab", "isbabcheck").equals("nodata") == true) {

			Mlunch.setText("아직 기기에 저장된 급식 정보가 없습니다");
			Mdinner.setText("네트워크에 연결하신 후, 상단의 <급식정보 업데이트> 버튼을 눌러 업데이트를 해주세요");
			return;
		}
		if (myHelper.getid("bab", "recdate") != null) {
			if (myHelper.getid("bab", "recdate").equals("NP") == true) {
				Mlunch.setText("네트워크 오류로 정보 업데이트가 중단되었습니다");
				Mdinner.setText("기기의 네트워크 연결 상황을 다시 확인해주세요");
				return;
			} else {
				// recdate = Integer.valueOf(myHelper.getid("bab", "recdate"));
				// Log.i("Net", "[ERROR] : 정보 만료 " + intdate + "//" + recdate);
				/*
				 * if (intdate > recdate) { Mlunch.setText("정보가 " + recdate +
				 * "자로 만료되었습니다");
				 * Mdinner.setText("네트워크에 연결하신 후, 상단의 <급식정보 업데이트> 버튼을 눌러 업데이트를 해주세요"
				 * ); return; }
				 */}
		} else {
			Mlunch.setText("네트워크 오류로 정보 업데이트가 중단되었습니다");
			Mdinner.setText("기기의 네트워크 연결 상황을 다시 확인해주세요");
		}
		plunch = pdinner = "nodata";
		String strdate = Integer.toString(intdate);

		cursor.moveToFirst();
		plunch = pdinner = "nodata";

		do {
			// Log.i("Net", "* : " + cursor.getString(0) +
			// cursor.getString(1)
			// + cursor.getString(2));
			if (cursor.getString(1).equals(strdate + ".lunch") == true) {

				plunch = cursor.getString(2);
				Log.i("Net", "점심 DB 찾음! : " + plunch);
			} else if (cursor.getString(1).equals(strdate + ".dinner") == true) {
				Log.i("Net", "저녁 DB 찾음! : " + pdinner);
				pdinner = cursor.getString(2);
			}
		} while (cursor.moveToNext());

		if (getYI(Y, M, D) == 0 || getYI(Y, M, D) == 6) {
			Mlunch.setText("휴일 입니다");
			Mdinner.setText("휴일 입니다");
		} else if (plunch.equals("nodata")) { // DB안에 정보가 없을 경우
			Mlunch.setText("급식 정보가 없습니다.");
			Mdinner.setText("급식 정보가 없습니다.");
		} else {
			Mlunch.setText(plunch);
			Mdinner.setText(pdinner);

			if (plunch.equals("NP")) {
				Mlunch.setText("급식이 없습니다.");
			}
			if (pdinner.equals("NP")) {
				Mdinner.setText("급식이 없습니다.");
			}
		}
		Log.i("Net", "급식 표시 완료, " + plunch + pdinner);

	}

	private int Y, D, M;
	private int intdate, recdate;
	private TextView Mlunch, Mdinner;
	private String dd, plunch, pdinner;
	private String[] YI = { "일", "월", "화", "수", "목", "금", "토" };
	Calendar calendar = Calendar.getInstance();

	@Override
	public void onBackPressed() {
		finish();
		overridePendingTransition(R.anim.bottom_to_top_in,
				R.anim.bottom_to_top_out);

	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		myHelper.close();
	}

	public class LoadingParsing extends AsyncTask<Void, String, Void> {

		protected void onCancelled() {
			super.onCancelled();
		}

		@Override
		protected void onPreExecute() {
			Log.i("Net", "AsyncTask 시작 - 다이얼로그 시작");

			mProgressDialog = new ProgressDialog(Today_meal.this);
			mProgressDialog.setTitle("급식 정보 업데이트");
			mProgressDialog.setCancelable(false);
			mProgressDialog
					.setMessage("급식 정보를 서버에서 내려받아 기기 내부에 저장하고 있습니다. \n이 작업이 완료되면 최근 갱신일까지 네트워크 연결없이 급식을 확인할 수 있습니다.");
			mProgressDialog.show();
		}

		@Override
		protected void onPostExecute(Void result) {
			Log.i("Net", "AsyncTask 종료 - 다이얼로그 종료");

			mProgressDialog.dismiss();
			Refresh();

			if ((myHelper.getid("bab", "isbabcheck")).equals("NetError") == true) {
				showToast("현재 나이스 서버 점검중입니다.");
			} else {
				showToast("저장된 급식정보는 학교 사정에 따라 변경될 수 있다는 점을 숙지하여 주시기 바랍니다.");
			}
		}

		@Override
		protected void onProgressUpdate(String... str) {
			Log.i("Net", "AsyncTask 진행중");
			showToast(str[0]);

			super.onProgressUpdate();

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
			BabParse updater = new BabParse(mContext);

			updater.parse(Y, M, true);

			// mProgressDialog.dismiss();
			// TODO Auto-generated method stub
			return null;

		}

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.menu_today_meal, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			// app icon in action bar clicked; go home
			finish();
			overridePendingTransition(R.anim.bottom_to_top_in,
					R.anim.bottom_to_top_out);
			return true;

		case R.id.Help:
			Toast.makeText(
					Today_meal.this,
					"먼저 우측 상단의 <급식정보 업데이트> 버튼을 눌러 데이터를 갱신합니다. \n그 후 필요하시다면 새로고침을 눌러 내용을 표시하게 합니다.",
					Toast.LENGTH_SHORT).show();
			return true;

		case R.id.Act_Parsing_meal:

			if (NetStatCHK() == true) {
				new LoadingParsing().execute();
				Refresh();
			} else {
				AlertDialog.Builder ab = new AlertDialog.Builder(
						Today_meal.this);
				ab.setMessage(getString(R.string.NetStat_error));
				ab.setPositiveButton("확인", null);
				ab.show();
			}
			return true;
		case R.id.PreDay:
			mod();
			showDate();
			Log.i("Net", "-1일 버튼을 누름");
			update(dd);
			return true;
		case R.id.NextDay:
			pod();
			showDate();
			Log.i("Net", "+1일 버튼을 누름");
			update(dd);
			return true;
		case R.id.Today:
			Refresh();
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}
}

// http://kkoseul.tistory.com/30
// http://devsw.tistory.com/60