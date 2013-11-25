package com.ATeam.HangaramAPP;

import java.text.Collator;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import org.holoeverywhere.app.AlertDialog;
import org.holoeverywhere.widget.AutoCompleteTextView;
import org.holoeverywhere.widget.Toast;

import android.annotation.SuppressLint;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningTaskInfo;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.v4.app.NotificationCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.text.Html;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.view.View.OnTouchListener;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.SlidingDrawer;
import android.widget.TextView;

import com.ATeam.HangaramAPP.Dday.Work;
import com.nineoldandroids.view.ViewHelper;
import com.viewpagerindicator.CirclePageIndicator;

@SuppressLint("NewApi")
public class Main extends ActionBarActivity {

	private SlidingDrawer sd;
	private ImageView im;
	boolean PressBack = false, isActivityAlive = true, isConnected,
			MainLocked = false, MainLockerShow = false;
	Context mContext;
	static Toast mToast;
	int day_changeable, IsAPhone = 0, noticeGetterFlag;
	View pagerview = null, timeTableView;
	TimeTableSub myGetter;
	AutoCompleteTextView edt;
	EditText Mainedt;
	
	AlertDialog.Builder suject_input_dialog, noticeAB;
	DbAdapter_TimeTable myTTHelper;
	DbAdapter myHelper;
	ImageButton timetable_lock;
	TextView DayOfWeek, Period1, Period2, Period3, Period4, Period5, Period6, TML, TMD, ScheDate, ScheEvent, ScheDate_p, ScheEvent_p;
	String dw, p1, p2, p3, p4, p5, p6, noteText;
	Typeface tface;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mContext = this;
		myHelper = new DbAdapter(mContext);
		myGetter = new TimeTableSub(mContext);
		myTTHelper = new DbAdapter_TimeTable(mContext);
		myHelper.open("READ");

		setTheme(R.style.Default);
		setContentView(R.layout.viewpager_main_app);

		tface = Typeface.createFromAsset(getAssets(),
                 "fonts/SeoulNamsanB.ttf");
		ViewPager mPager = (ViewPager) findViewById(R.id.pager);
		CirclePageIndicator CPIndicator = (CirclePageIndicator) findViewById(R.id.CPIndicator);
		tableBuilder();
		mPager.setAdapter(new mainPagerAdapter(getApplicationContext()));

		PressBack = false;
		// Bind the circle indicator to the adapter
		CPIndicator.setViewPager(mPager);
		CPIndicator.setSnap(true);
		CPIndicator.setCurrentItem(1);
		CPIndicator.setOnPageChangeListener(MainPageChangeListener);

		final ActionBar ab = getSupportActionBar();
		ab.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
		ab.setDisplayOptions(ActionBar.DISPLAY_SHOW_HOME
				| ActionBar.DISPLAY_SHOW_TITLE);
		ab.setTitle(getString(R.string.$app_name));
		ab.setHomeButtonEnabled(false);
		ab.setDisplayUseLogoEnabled(true);

		checkBabTableFinalDate();
		NetStatCHK();
		checknotice();
		noticeAlertBuilder();

		SharedPreferences pref = PreferenceManager
				.getDefaultSharedPreferences(this);
		boolean isnoticeupdate = pref.getBoolean("notice_popup_pref", true);

		if (isnoticeupdate == true) {
			new Handler().postDelayed(new Runnable() {
				public void run() {
					AlertDialog noticeDL = noticeAB.show();
					TextView tv = (TextView) noticeDL.findViewById(R.id.message);
					tv.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);
				}
			}, 80);
		}

		ImageButton bpod = (ImageButton) findViewById(R.id.noticebutton);
		bpod.setOnClickListener(new ImageButton.OnClickListener() {
			public void onClick(View v) {
				AlertDialog noticeDL = noticeAB.show();
				TextView tv = (TextView) noticeDL.findViewById(R.id.message);
				tv.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);
			}
		});

		sd = (SlidingDrawer) findViewById(R.id.slidingDrawer1);
		im = (ImageView) findViewById(R.id.up_down);
		sd.setOnDrawerOpenListener(new SlidingDrawer.OnDrawerOpenListener() {

			public void onDrawerOpened() {
				im.setImageResource(R.drawable.navigate_down);
			}
		});
		sd.setOnDrawerCloseListener(new SlidingDrawer.OnDrawerCloseListener() {
			public void onDrawerClosed() {
				im.setImageResource(R.drawable.navigate_up);
			}
		});

		// DB 최근 갱신날짜 지정

	}

	@Override
	public void onResume() {
		super.onResume();

		checkBabTableFinalDate();
		
		try{
		DbAdapter myHelper = new DbAdapter(mContext);
		myHelper.open("READ");
		TodayMealTextPusher();
		myHelper.close();
		
		ScheTextPusher();
		}catch(Exception e){}
		if (Chk_recentVersion.ptitle == null) {
			noticeGetterFlag = 1;
			new noticeGetter().execute();
		} else {
			noticeGetterFlag = 2;
			String ptitle = Chk_recentVersion.ptitle;
			Log.d("Net", "ptitle는" + ptitle);

			String netStatError = getString(R.string.NetStat_error);
			if (ptitle.equals(netStatError) == true
					|| ptitle.equals(null) == true) {

				NetStatCHK();
				Log.d("Net", "isConnected는" + isConnected);

				if (isConnected == true || IsAPhone == 0) {
					new noticeGetter().execute();
				}
			}
		}
	}

	public class noticeGetter extends AsyncTask<Void, String, Void> {

		Chk_recentVersion myChecker = new Chk_recentVersion(mContext);

		@Override
		protected Void doInBackground(Void... arg0) {
			// TODO Auto-generated method stub

			if (noticeGetterFlag == 1) {
				myChecker.ChkNotice(isConnected, IsAPhone);

			} else if (noticeGetterFlag == 2) {
				myChecker.checknotice();
				myChecker.Try(mContext);
			}
			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			Log.i("Net", "AsyncTask 종료 - 다이얼로그 종료");
			try {
				checknotice();
				noticeAB.setMessage(Html.fromHtml(Chk_recentVersion.pmessage));
			} catch (Exception e) {
			}
		}
	}

	public String recentUpdatedBab() {

		Log.i("Net", "recentUpdatedBab이 실행되었소.");

		myHelper.close();
		myHelper.open("WRITE");
		myHelper.create("bab");
		myHelper.create("sche");
		myHelper.create("sche_private");
		myHelper.close();
		
		myHelper.open("READ");
		Cursor cursor = myHelper.fetchd("bab");
		cursor.moveToFirst();
		if (cursor.getCount() != 0) {
			if (myHelper.getid("bab", "isbabcheck").equals("nodata") == true) {
				return "0";
			} else {
				String finalDate = myHelper.getid("bab", "recdate");
				return finalDate;
			}
		} else
			return "0";
	}

	private class mainPagerAdapter extends PagerAdapter {

		private LayoutInflater mInflater;

		public mainPagerAdapter(Context con) {
			super();
			mInflater = LayoutInflater.from(con);

		}

		@SuppressWarnings("deprecation")
		@Override
		public Object instantiateItem(View pager, int position) {

			if (position == 0) {
				pagerview = mInflater.inflate(R.layout.time_table, null);
				timeTableView = pagerview;

				Calendar rightNow = Calendar.getInstance();
				int day = rightNow.get(Calendar.DAY_OF_WEEK);
				day_changeable = day;

				myHelper.close();
				myGetter.createDBTable();
				myGetter.getDayWeek(pagerview, day);
				myHelper.open("READ");
				doTimeTableWork(pagerview);

				pagerview.findViewById(R.id.Period1).setOnClickListener(
						mBTN_TimeTable1);
				pagerview.findViewById(R.id.Period2).setOnClickListener(
						mBTN_TimeTable2);
				pagerview.findViewById(R.id.Period3).setOnClickListener(
						mBTN_TimeTable3);
				pagerview.findViewById(R.id.Period4).setOnClickListener(
						mBTN_TimeTable4);
				pagerview.findViewById(R.id.Period5).setOnClickListener(
						mBTN_TimeTable5);
				pagerview.findViewById(R.id.Period6).setOnClickListener(
						mBTN_TimeTable6);
				pagerview.findViewById(R.id.TimeTablePre).setOnClickListener(
						mBTN_TimeTablePre);
				pagerview.findViewById(R.id.TimeTableNext).setOnClickListener(
						mBTN_TimeTableNext);
				pagerview.findViewById(R.id.TimeTableToday).setOnClickListener(
						mBTN_TimeTableToday);
				
				View table = ((View) pagerview.findViewById(R.id.lTimetable));
				ViewHelper.setAlpha(table, (float) 0.8);

			} else if (position == 1) {
				pagerview = mInflater.inflate(R.layout.main, null);
				
				pagerview.findViewById(R.id.BTodaymeal).setOnClickListener(
						mBTN_todayMeal);
				pagerview.findViewById(R.id.BTodaymeal2).setOnClickListener(
						mBTN_todayMeal);
				pagerview.findViewById(R.id.BSchInfo).setOnClickListener(
						mBTN_SchInfo);
				pagerview.findViewById(R.id.BTools).setOnClickListener(
						mBTN_Tools);
				pagerview.findViewById(R.id.BDday).setOnClickListener(
						mBTN_Dday);
				TML = (TextView)pagerview.findViewById(R.id.TM_preview_L);
				TMD = (TextView)pagerview.findViewById(R.id.TM_preview_D);
				
				ScheDate = (TextView)pagerview.findViewById(R.id.ScheDate);
				ScheEvent = (TextView)pagerview.findViewById(R.id.ScheEvent);
				ScheDate_p = (TextView)pagerview.findViewById(R.id.ScheDate_p);
				ScheEvent_p = (TextView)pagerview.findViewById(R.id.ScheEvent_p);
				
				pagerview.findViewById(R.id.BCommunity).setOnClickListener(mBTN_Community);
				//((TextView) pagerview.findViewById(R.id.TSchInfo)).setTypeface(tface);
				//((TextView) pagerview.findViewById(R.id.TTools)).setTypeface(tface);
				//((TextView) pagerview.findViewById(R.id.TCommunity)).setTypeface(tface);
				((ImageView) pagerview.findViewById(R.id.ICommunity)).setAlpha(70);
				((ImageView) pagerview.findViewById(R.id.ITools)).setAlpha(70);
				((ImageView) pagerview.findViewById(R.id.ISchInfo)).setAlpha(70);
				((ImageView) pagerview.findViewById(R.id.ITodaymeal)).setAlpha(100);
				
				
				TodayMealTextPusherTD td1 = new TodayMealTextPusherTD();
				td1.start();
				
				ScheTextPusherTD td2 = new ScheTextPusherTD();
				td2.start();
				
				
			}

			else if (position == 2) {
				pagerview = mInflater.inflate(R.layout.note, null);

				noteText = myHelper.getid("note", "noteText");
				Log.d("Net", "noteText은 " + noteText);

				ScrollView view = (ScrollView) pagerview
						.findViewById(R.id.Main_NoteSCV);
				view.setOnTouchListener(mBTN_MainNoteSCV);
				Mainedt = (EditText) pagerview.findViewById(R.id.Main_NoteText);
				Mainedt.setText(noteText);
				Mainedt.setOnFocusChangeListener(mBTN_MainNoteText);
				if (MainLocked == false) {
					Mainedt.setFocusable(true);
				} else if (MainLocked == true) {
					Mainedt.setFocusable(false);
				}
			}
			((ViewPager) pager).addView(pagerview, 0);

			return pagerview;
		}

		@Override
		public int getCount() {
			return 3;
		}

		@Override
		public void destroyItem(View pager, int position, Object view) {

			((ViewPager) pager).removeView((View) view);
		}

		@Override
		public boolean isViewFromObject(View view, Object obj) {
			// TODO Auto-generated method stub
			return view == obj;
		}

	}

	private OnClickListener mBTN_SchInfo = new OnClickListener() {
		public void onClick(View v) {
			startActivity(new Intent(Main.this, Main_SchoolMenu.class));
			overridePendingTransition(R.anim.top_to_bottom_in,
					R.anim.top_to_bottom_out);
		}
	};
	private OnClickListener mBTN_Community = new OnClickListener() {
		public void onClick(View v) {
			startActivity(new Intent(Main.this, Main_Community.class));
			overridePendingTransition(R.anim.top_to_bottom_in,
					R.anim.top_to_bottom_out);
		}
	};
	private OnClickListener mBTN_Tools = new OnClickListener() {
		public void onClick(View v) {
			startActivity(new Intent(Main.this, Calc.class));
			overridePendingTransition(R.anim.top_to_bottom_in,
					R.anim.top_to_bottom_out);
		}
	};
	private OnClickListener mBTN_todayMeal = new OnClickListener() {
		public void onClick(View v) {
			startActivity(new Intent(Main.this, Today_meal.class));
			overridePendingTransition(R.anim.top_to_bottom_in,
					R.anim.top_to_bottom_out);
		}
	};
	private OnClickListener mBTN_Dday = new OnClickListener() {
		public void onClick(View v) {
			startActivity(new Intent(Main.this, Dday.class));
			overridePendingTransition(R.anim.top_to_bottom_in,
					R.anim.top_to_bottom_out);
		}
	};

	private OnPageChangeListener MainPageChangeListener = new OnPageChangeListener() {

		public void onPageScrollStateChanged(int arg0) {
			// TODO Auto-generated method stub
		}

		public void onPageScrolled(int arg0, float arg1, int arg2) {
			// TODO Auto-generated method stub

		}

		public void onPageSelected(int position) {

			InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
			// TODO Auto-generated method stub
			if (position == 0) {

				MainLockerShow = true;
				supportInvalidateOptionsMenu();
			}

			if (position == 1) {

				MainLockerShow = false;
				supportInvalidateOptionsMenu();

				Mainedt.clearFocus();

				imm.hideSoftInputFromWindow(Mainedt.getWindowToken(), 0);

				String str = Mainedt.getText().toString();
				myHelper.close();
				myHelper.open("WRITE");
				myHelper.update("note", 1, "noteText", str);
				myHelper.close();
				myHelper.open("READ");
			}

			if (position == 2) {

				MainLockerShow = true;
				supportInvalidateOptionsMenu();
			}

		}

	};

	private OnClickListener mBTN_TimeTablePre = new OnClickListener() {
		public void onClick(View v) {
			myGetter = new TimeTableSub(mContext);

			if (day_changeable == 1) {
				day_changeable = 7;
				myGetter.getDayWeek(timeTableView, day_changeable);
				doTimeTableWork(timeTableView);

				Log.d("SJ", "day_changeable은" + day_changeable);
			} else {
				day_changeable = day_changeable - 1;
				myGetter.getDayWeek(timeTableView, day_changeable);
				doTimeTableWork(timeTableView);

				Log.d("SJ", "day_changeable은" + day_changeable);
			}
		}
	};

	private OnClickListener mBTN_TimeTableNext = new OnClickListener() {
		public void onClick(View v) {
			myGetter = new TimeTableSub(mContext);

			if (day_changeable == 7) {
				day_changeable = 1;
				myGetter.getDayWeek(timeTableView, day_changeable);
				doTimeTableWork(timeTableView);

				Log.d("SJ", "day_changeable == 0 일때는" + day_changeable);
			} else {
				day_changeable = day_changeable + 1;
				myGetter.getDayWeek(timeTableView, day_changeable);
				doTimeTableWork(timeTableView);

				Log.d("SJ", "day_changeable else 는" + day_changeable);
			}
		}
	};

	private OnClickListener mBTN_TimeTableToday = new OnClickListener() {
		public void onClick(View v) {
			myGetter = new TimeTableSub(mContext);

			Calendar rightNow = Calendar.getInstance();
			int day = rightNow.get(Calendar.DAY_OF_WEEK);
			day_changeable = day;

			myGetter.getDayWeek(timeTableView, day_changeable);
			doTimeTableWork(timeTableView);

			Log.d("SJ", "day_changeable == 0 일때는" + day_changeable);
		}
	};

	private OnClickListener mBTN_TimeTable1 = new OnClickListener() {
		public void onClick(View v) {
			// Custom Dialog 생성 예제
			if (MainLocked == false) {
				LayoutInflater inflater = LayoutInflater.from(mContext);
				View dialogview = inflater.inflate(R.layout.dl_subject_input,
						null);
				edt = (AutoCompleteTextView) dialogview
						.findViewById(R.id.setSubject);
				edt.setAdapter(new ArrayAdapter<String>(mContext,
						android.R.layout.simple_dropdown_item_1line,
						(String[]) getResources().getStringArray(
								R.array.timetable_subject_input)));

				final InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
				imm.showSoftInput(edt, InputMethodManager.SHOW_FORCED);

				suject_input_dialog.setView(dialogview);
				suject_input_dialog.setTitle("1교시 과목 설정");
				suject_input_dialog.setPositiveButton("확인",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,
									int whichButton) {

								imm.hideSoftInputFromWindow(
										edt.getWindowToken(), 0);

								myTTHelper.open("WRITE");
								TimeTableGetText();
								String p1 = edt.getText().toString();
								if (dw == "일요일") {
									myTTHelper.update("TimeTable", 1, "Sunday",
											p1, p2, p3, p4, p5, p6);
								} else if (dw == "월요일") {
									myTTHelper.update("TimeTable", 2, "Monday",
											p1, p2, p3, p4, p5, p6);
								} else if (dw == "화요일") {
									myTTHelper.update("TimeTable", 3,
											"Tuesday", p1, p2, p3, p4, p5, p6);
								} else if (dw == "수요일") {
									myTTHelper
											.update("TimeTable", 4,
													"Wednesday", p1, p2, p3,
													p4, p5, p6);
								} else if (dw == "목요일") {
									myTTHelper.update("TimeTable", 5,
											"Thursday", p1, p2, p3, p4, p5, p6);
								} else if (dw == "금요일") {
									myTTHelper.update("TimeTable", 6, "Friday",
											p1, p2, p3, p4, p5, p6);
								} else if (dw == "토요일") {
									myTTHelper.update("TimeTable", 7,
											"Saturday", p1, p2, p3, p4, p5, p6);
								}
								myTTHelper.close();
								Period1.setText(p1);
								Log.i("Net", "p1은 " + p1 + ", p2는 " + p2);

							}
						});

				suject_input_dialog.show();
				Log.i("Net", "dw는 " + dw);
			}
		}
	};
	private OnClickListener mBTN_TimeTable2 = new OnClickListener() {
		public void onClick(final View v) {
			// Custom Dialog 생성 예제
			if (MainLocked == false) {
				LayoutInflater inflater = LayoutInflater.from(mContext);
				View dialogview = inflater.inflate(R.layout.dl_subject_input,
						null);
				edt = (AutoCompleteTextView) dialogview
						.findViewById(R.id.setSubject);
				edt.setAdapter(new ArrayAdapter<String>(mContext,
						android.R.layout.simple_dropdown_item_1line,
						(String[]) getResources().getStringArray(
								R.array.timetable_subject_input)));

				final InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
				imm.showSoftInput(edt, InputMethodManager.SHOW_FORCED);

				suject_input_dialog.setView(dialogview);
				suject_input_dialog.setTitle("2교시 과목 설정");
				suject_input_dialog.setPositiveButton("확인",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,
									int whichButton) {

								imm.hideSoftInputFromWindow(
										edt.getWindowToken(), 0);

								myTTHelper.open("WRITE");
								TimeTableGetText();
								String p2 = edt.getText().toString();
								if (dw == "일요일") {
									myTTHelper.update("TimeTable", 1, "Sunday",
											p1, p2, p3, p4, p5, p6);
								} else if (dw == "월요일") {
									myTTHelper.update("TimeTable", 2, "Monday",
											p1, p2, p3, p4, p5, p6);
								} else if (dw == "화요일") {
									myTTHelper.update("TimeTable", 3,
											"Tuesday", p1, p2, p3, p4, p5, p6);
								} else if (dw == "수요일") {
									myTTHelper
											.update("TimeTable", 4,
													"Wednesday", p1, p2, p3,
													p4, p5, p6);
								} else if (dw == "목요일") {
									myTTHelper.update("TimeTable", 5,
											"Thursday", p1, p2, p3, p4, p5, p6);
								} else if (dw == "금요일") {
									myTTHelper.update("TimeTable", 6, "Friday",
											p1, p2, p3, p4, p5, p6);
								} else if (dw == "토요일") {
									myTTHelper.update("TimeTable", 7,
											"Saturday", p1, p2, p3, p4, p5, p6);
								}
								myTTHelper.close();
								Period2.setText(p2);
								Log.i("Net", "p1은 " + p1 + ", p2는 " + p2);
							}
						});

				suject_input_dialog.show();
			}
		}
	};;
	private OnClickListener mBTN_TimeTable3 = new OnClickListener() {
		public void onClick(View v) {
			// Custom Dialog 생성 예제
			if (MainLocked == false) {
				LayoutInflater inflater = LayoutInflater.from(mContext);
				View dialogview = inflater.inflate(R.layout.dl_subject_input,
						null);
				edt = (AutoCompleteTextView) dialogview
						.findViewById(R.id.setSubject);
				edt.setAdapter(new ArrayAdapter<String>(mContext,
						android.R.layout.simple_dropdown_item_1line,
						(String[]) getResources().getStringArray(
								R.array.timetable_subject_input)));

				final InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
				imm.showSoftInput(edt, InputMethodManager.SHOW_FORCED);

				suject_input_dialog.setView(dialogview);
				suject_input_dialog.setTitle("3교시 과목 설정");
				suject_input_dialog.setPositiveButton("확인",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,
									int whichButton) {

								imm.hideSoftInputFromWindow(
										edt.getWindowToken(), 0);

								myTTHelper.open("WRITE");
								TimeTableGetText();
								String p3 = edt.getText().toString();
								if (dw == "일요일") {
									myTTHelper.update("TimeTable", 1, "Sunday",
											p1, p2, p3, p4, p5, p6);
								} else if (dw == "월요일") {
									myTTHelper.update("TimeTable", 2, "Monday",
											p1, p2, p3, p4, p5, p6);
								} else if (dw == "화요일") {
									myTTHelper.update("TimeTable", 3,
											"Tuesday", p1, p2, p3, p4, p5, p6);
								} else if (dw == "수요일") {
									myTTHelper
											.update("TimeTable", 4,
													"Wednesday", p1, p2, p3,
													p4, p5, p6);
								} else if (dw == "목요일") {
									myTTHelper.update("TimeTable", 5,
											"Thursday", p1, p2, p3, p4, p5, p6);
								} else if (dw == "금요일") {
									myTTHelper.update("TimeTable", 6, "Friday",
											p1, p2, p3, p4, p5, p6);
								} else if (dw == "토요일") {
									myTTHelper.update("TimeTable", 7,
											"Saturday", p1, p2, p3, p4, p5, p6);
								}
								myTTHelper.close();
								Period3.setText(p3);
								Log.i("Net", "p2은 " + p2 + ", p3는 " + p3);
							}
						});
				suject_input_dialog.show();
			}
		}
	};;
	private OnClickListener mBTN_TimeTable4 = new OnClickListener() {
		public void onClick(View v) {
			// Custom Dialog 생성 예제
			if (MainLocked == false) {
				LayoutInflater inflater = LayoutInflater.from(mContext);
				View dialogview = inflater.inflate(R.layout.dl_subject_input,
						null);
				edt = (AutoCompleteTextView) dialogview
						.findViewById(R.id.setSubject);
				edt.setAdapter(new ArrayAdapter<String>(mContext,
						android.R.layout.simple_dropdown_item_1line,
						(String[]) getResources().getStringArray(
								R.array.timetable_subject_input)));

				final InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
				imm.showSoftInput(edt, InputMethodManager.SHOW_FORCED);

				suject_input_dialog.setView(dialogview);
				suject_input_dialog.setTitle("4교시 과목 설정");
				suject_input_dialog.setPositiveButton("확인",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,
									int whichButton) {

								imm.hideSoftInputFromWindow(
										edt.getWindowToken(), 0);

								myTTHelper.open("WRITE");
								TimeTableGetText();
								String p4 = edt.getText().toString();
								if (dw == "일요일") {
									myTTHelper.update("TimeTable", 1, "Sunday",
											p1, p2, p3, p4, p5, p6);
								} else if (dw == "월요일") {
									myTTHelper.update("TimeTable", 2, "Monday",
											p1, p2, p3, p4, p5, p6);
								} else if (dw == "화요일") {
									myTTHelper.update("TimeTable", 3,
											"Tuesday", p1, p2, p3, p4, p5, p6);
								} else if (dw == "수요일") {
									myTTHelper
											.update("TimeTable", 4,
													"Wednesday", p1, p2, p3,
													p4, p5, p6);
								} else if (dw == "목요일") {
									myTTHelper.update("TimeTable", 5,
											"Thursday", p1, p2, p3, p4, p5, p6);
								} else if (dw == "금요일") {
									myTTHelper.update("TimeTable", 6, "Friday",
											p1, p2, p3, p4, p5, p6);
								} else if (dw == "토요일") {
									myTTHelper.update("TimeTable", 7,
											"Saturday", p1, p2, p3, p4, p5, p6);
								}
								myTTHelper.close();
								Period4.setText(p4);
								Log.i("Net", "p4은 " + p4 + ", p5는 " + p5);
							}
						});
				suject_input_dialog.show();
			}
		}
	};
	private OnClickListener mBTN_TimeTable5 = new OnClickListener() {
		public void onClick(View v) {
			// Custom Dialog 생성 예제
			if (MainLocked == false) {
				LayoutInflater inflater = LayoutInflater.from(mContext);
				View dialogview = inflater.inflate(R.layout.dl_subject_input,
						null);
				edt = (AutoCompleteTextView) dialogview
						.findViewById(R.id.setSubject);
				edt.setAdapter(new ArrayAdapter<String>(mContext,
						android.R.layout.simple_dropdown_item_1line,
						(String[]) getResources().getStringArray(
								R.array.timetable_subject_input)));

				final InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
				imm.showSoftInput(edt, InputMethodManager.SHOW_FORCED);

				suject_input_dialog.setView(dialogview);
				suject_input_dialog.setTitle("5교시 과목 설정");
				suject_input_dialog.setPositiveButton("확인",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,
									int whichButton) {

								imm.hideSoftInputFromWindow(
										edt.getWindowToken(), 0);

								myTTHelper.open("WRITE");
								TimeTableGetText();
								String p5 = edt.getText().toString();
								if (dw == "일요일") {
									myTTHelper.update("TimeTable", 1, "Sunday",
											p1, p2, p3, p4, p5, p6);
								} else if (dw == "월요일") {
									myTTHelper.update("TimeTable", 2, "Monday",
											p1, p2, p3, p4, p5, p6);
								} else if (dw == "화요일") {
									myTTHelper.update("TimeTable", 3,
											"Tuesday", p1, p2, p3, p4, p5, p6);
								} else if (dw == "수요일") {
									myTTHelper
											.update("TimeTable", 4,
													"Wednesday", p1, p2, p3,
													p4, p5, p6);
								} else if (dw == "목요일") {
									myTTHelper.update("TimeTable", 5,
											"Thursday", p1, p2, p3, p4, p5, p6);
								} else if (dw == "금요일") {
									myTTHelper.update("TimeTable", 6, "Friday",
											p1, p2, p3, p4, p5, p6);
								} else if (dw == "토요일") {
									myTTHelper.update("TimeTable", 7,
											"Saturday", p1, p2, p3, p4, p5, p6);
								}
								myTTHelper.close();
								Period5.setText(p5);
								Log.i("Net", "p4은 " + p4 + ", p5는 " + p5);
							}
						});

				suject_input_dialog.show();
			}
		}
	};
	private OnClickListener mBTN_TimeTable6 = new OnClickListener() {
		public void onClick(View v) {
			// Custom Dialog 생성 예제
			if (MainLocked == false) {
				LayoutInflater inflater = LayoutInflater.from(mContext);
				View dialogview = inflater.inflate(R.layout.dl_subject_input,
						null);
				edt = (AutoCompleteTextView) dialogview
						.findViewById(R.id.setSubject);
				edt.setAdapter(new ArrayAdapter<String>(mContext,
						android.R.layout.simple_dropdown_item_1line,
						(String[]) getResources().getStringArray(
								R.array.timetable_subject_input)));

				final InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
				imm.showSoftInput(edt, InputMethodManager.SHOW_FORCED);

				suject_input_dialog.setView(dialogview);
				suject_input_dialog.setTitle("6교시 과목 설정");
				suject_input_dialog.setPositiveButton("확인",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,
									int whichButton) {

								imm.hideSoftInputFromWindow(
										edt.getWindowToken(), 0);

								myTTHelper.open("WRITE");
								TimeTableGetText();
								String p6 = edt.getText().toString();
								if (dw == "일요일") {
									myTTHelper.update("TimeTable", 1, "Sunday",
											p1, p2, p3, p4, p5, p6);
								} else if (dw == "월요일") {
									myTTHelper.update("TimeTable", 2, "Monday",
											p1, p2, p3, p4, p5, p6);
								} else if (dw == "화요일") {
									myTTHelper.update("TimeTable", 3,
											"Tuesday", p1, p2, p3, p4, p5, p6);
								} else if (dw == "수요일") {
									myTTHelper
											.update("TimeTable", 4,
													"Wednesday", p1, p2, p3,
													p4, p5, p6);
								} else if (dw == "목요일") {
									myTTHelper.update("TimeTable", 5,
											"Thursday", p1, p2, p3, p4, p5, p6);
								} else if (dw == "금요일") {
									myTTHelper.update("TimeTable", 6, "Friday",
											p1, p2, p3, p4, p5, p6);
								} else if (dw == "토요일") {
									myTTHelper.update("TimeTable", 7,
											"Saturday", p1, p2, p3, p4, p5, p6);
								}
								myTTHelper.close();
								Period6.setText(p6);
								Log.i("Net", "p6은 " + p6);
							}
						});

				suject_input_dialog.show();
			}
		}
	};

	private OnFocusChangeListener mBTN_MainNoteText = new View.OnFocusChangeListener() {

		public void onFocusChange(View v, boolean hasFocus) {
			noteBuilder();
		}
	};
	private OnTouchListener mBTN_MainNoteSCV = new OnTouchListener() {

		public boolean onTouch(View v, MotionEvent event) {
			// TODO Auto-generated method stub

			if (MainLocked == false) {
				noteBuilder();
			}

			return false;
		}

	};

	// 여기서부터 옵션메뉴 관련 내용
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		getMenuInflater().inflate(R.menu.menu_main, menu);

		if (MainLockerShow == true) {
			if (MainLocked == true) {
				menu.getItem(3).setVisible(false);
				menu.getItem(4).setVisible(true);
			} else if (MainLocked == false) {
				menu.getItem(3).setVisible(true);
				menu.getItem(4).setVisible(false);
			}
		} else if (MainLockerShow == false) {
			menu.getItem(3).setVisible(false);
			menu.getItem(4).setVisible(false);
		}

		// createMenu(menu);
		// return super.onCreateOptionsMenu(menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.Act_Devteam_info:
			startActivity(new Intent(this, Devteam_info.class));
			overridePendingTransition(R.anim.bottom_to_top_in,
					R.anim.bottom_to_top_out);
			break;

		case R.id.Act_Help:
			startActivity(new Intent(this, Help.class));
			finish();
			overridePendingTransition(R.anim.bottom_to_top_in,
					R.anim.bottom_to_top_out);
			break;

		case R.id.Act_Chk_Version:

			String str1 = getString(R.string.$app_ver);
			String str2 = getString(R.string.Copyrights);

			AlertDialog.Builder arb = new AlertDialog.Builder(Main.this);
			arb.setMessage("현재 실행중인 한가람앱의 버전은 " + str1 + "입니다." + str2);
			arb.setPositiveButton("확인", null);
			arb.setTitle(R.string.Act_Chk_Version);
			arb.show();

			break;

		case R.id.Act_Config:
			startActivity(new Intent(this, Config.class));
			overridePendingTransition(R.anim.bottom_to_top_in,
					R.anim.bottom_to_top_out);
			break;

		case R.id.Act_MainUnlocked:
			MainLocked = true;
			Mainedt.setFocusable(false);
			InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
			imm.hideSoftInputFromWindow(Mainedt.getWindowToken(), 0);
			myHelper.close();
			myHelper.open("WRITE");
			myHelper.update("timeTableLock", 1, "isTimeTableLocked", "true");
			myHelper.close();
			myHelper.open("READ");
			supportInvalidateOptionsMenu();
			break;

		case R.id.Act_MainLocked:
			MainLocked = false;
			Mainedt.setFocusable(true);
			Mainedt.setFocusableInTouchMode(true);
			myHelper.close();
			myHelper.open("WRITE");
			myHelper.update("timeTableLock", 1, "isTimeTableLocked", "false");
			myHelper.close();
			myHelper.open("READ");
			supportInvalidateOptionsMenu();
			break;

		default:
			return false;
		}
		return true;

	}

	@Override
	public void onBackPressed() {

		if (PressBack == true) {
			isActivityAlive = false;
			finish();
			overridePendingTransition(R.anim.zoom_out_still,
					R.anim.zoom_out_enter);
		} else {
			showToast("다시 누르면 한가람앱을 종료합니다.");
			Handler PressBackCheck = new Handler();
			PressBackCheck.postDelayed(new Runnable() {
				public void run() {
					if (isActivityAlive == true) {
						PressBack = false;
					}
				}
			}, 2000);
			PressBack = true;
		}
	}
	
	@Override
	public void onDestroy(){
		super.onDestroy();
		/*
		ActivityManager activityManager = (ActivityManager) mContext.getSystemService(Context.ACTIVITY_SERVICE);
        List<RunningTaskInfo> info;
        info = activityManager.getRunningTasks(1);
         for (Iterator<RunningTaskInfo> iterator = info.iterator(); iterator.hasNext();)  {
             RunningTaskInfo runningTaskInfo = (RunningTaskInfo) iterator.next();
             if((runningTaskInfo.topActivity.getClassName().equals("com.ATeam.HangaramAPP.Help")) != true) {
         		ByeBye();
             }
         }
         */
		ByeBye();
		String str = Mainedt.getText().toString();
		myHelper.close();
		myHelper.open("WRITE");
		myHelper.update("note", 1, "noteText", str);
		myHelper.close();
	}

	public void showToast(String str) {
		if (mToast == null) {
			mToast = Toast.makeText(Main.this, str, 2000);
		} else {
			mToast.setText(str);
		}
		mToast.show();

	}

	public void checkBabTableFinalDate() {
		String StrRecDate = recentUpdatedBab();
		Log.i("Net", "StrRecDate" + StrRecDate);
		int intRecDate = 0;
		if (StrRecDate != null){
		if (StrRecDate.equals("NP") || StrRecDate.equals("null") || StrRecDate.equals("0")) {
			TextView show_rd = (TextView) findViewById(R.id.show_recentd);
			show_rd.setText("<오늘의 급식>에서 급식 정보를 저장해주세요.");
		} else {
			intRecDate = Integer.valueOf(StrRecDate);
			int yRecDate = intRecDate / 10000;
			int mRecDate = intRecDate / 100 - yRecDate * 100;
			int dRecDate = intRecDate - yRecDate * 10000 - mRecDate * 100;

			TextView show_rd = (TextView) findViewById(R.id.show_recentd);
			show_rd.setText("현재 " + yRecDate + "년 " + mRecDate + "월 "
					+ dRecDate + "일" + "자 급식까지 기기에 저장되어 있습니다.");
		}
	}else {
		TextView show_rd = (TextView) findViewById(R.id.show_recentd);
		show_rd.setText("<오늘의 급식>에서 급식 정보를 저장해주세요.");
	}
	}

	public void checknotice() {

		TextView nv = (TextView) findViewById(R.id.noticeview);
		nv.setText(Chk_recentVersion.ptitle);

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

	private void doTimeTableWork(View v) {

		DayOfWeek = (TextView) v.findViewById(R.id.DayOfWeek);
		Period1 = (TextView) v.findViewById(R.id.Period1);
		Period2 = (TextView) v.findViewById(R.id.Period2);
		Period3 = (TextView) v.findViewById(R.id.Period3);
		Period4 = (TextView) v.findViewById(R.id.Period4);
		Period5 = (TextView) v.findViewById(R.id.Period5);
		Period6 = (TextView) v.findViewById(R.id.Period6);

		suject_input_dialog = new AlertDialog.Builder(mContext);
		suject_input_dialog.setNegativeButton("취소",
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int whichButton) {

						InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
						imm.hideSoftInputFromWindow(edt.getWindowToken(), 0);

						getWindow().clearFlags(
								WindowManager.LayoutParams.FLAG_FULLSCREEN);

					}
				});
		suject_input_dialog.setCancelable(false);

		myHelper.open("READ");
		String tf = myHelper.getid("timeTableLock", "isTimeTableLocked");

		if (tf.equals("false") == true) {
			MainLocked = false;
		} else if (tf.equals("true") == true) {
			MainLocked = true;
		}

		TimeTableGetText();

	}

	public void TimeTableGetText() {

		dw = DayOfWeek.getText().toString();
		p1 = Period1.getText().toString();
		p2 = Period2.getText().toString();
		p3 = Period3.getText().toString();
		p4 = Period4.getText().toString();
		p5 = Period5.getText().toString();
		p6 = Period6.getText().toString();

	}

	public void noticeAlertBuilder() {

		try {
			myHelper.close();
			myHelper.open("WRITE");
			myHelper.deletetable("temp_click_counter");
			myHelper.close();
			myHelper.open("READ");
		} catch (Exception e) {
		}
		String str = Chk_recentVersion.pmessage;
		Log.d("Net", "Chk_recentVersion.pmessage은" + str);

		noticeAB = new AlertDialog.Builder(this)
				.setPositiveButton("확인", null)
				.setTitle("공지사항")
				.setIcon(R.drawable.ic_launcher)
				.setCancelable(false);
		
		if (isConnected == true || IsAPhone == 0) {
			try {
				noticeAB.setMessage(Html.fromHtml(Chk_recentVersion.pmessage));
			} catch (Exception e) {
			}
		} else if (Chk_recentVersion.pmessage == null) {
			noticeAB.setMessage(getString(R.string.NetStat_error));
		}

	}

	public void noteBuilder() {
		InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);

		// 커서 위치 맨 뒤로
		CharSequence text = Mainedt.getText();
		if (text != null && text.length() != 0) {
			int cursorPos = Math.min(text.length(), 2000);
			Mainedt.setSelection(cursorPos, cursorPos);
		}
		Mainedt.requestFocus();
		imm.showSoftInput(Mainedt, InputMethodManager.SHOW_FORCED);
	}
	
	
	class TodayMealTextPusherTD extends Thread{
		public void run(){
			DbAdapter myHelper = new DbAdapter(mContext);
			myHelper.open("READ");
			TodayMealTextPusher();
			myHelper.close();
		}
	}

	
	public void TodayMealTextPusher(){
		
		
		
		Calendar calendar = Calendar.getInstance();
		int Y, D, M;
		int intdate;
		String plunch, pdinner;
		
		Y = calendar.get(Calendar.YEAR);
		M = calendar.get(Calendar.MONTH);
		D = calendar.get(Calendar.DAY_OF_MONTH);
		M++;

		Log.i("Net", "일어낫다! " + Y + (M) + D);

		TML.setText("아직 서버에 해당 날짜 급식정보가 업로드되지 않았습니다.");
		TMD.setText("빠른 기한내로 업로드 하도록 하겠습니다.");
		
		intdate = (Y * 10000 + M * 100 + D);

		Cursor cursor = myHelper.fetchd("bab");

		if (myHelper.getid("bab", "isbabcheck").equals("nodata") == true) {

			TML.setText(getString(R.string.Act_Today_meal));
			TMD.setText("아직 기기에 저장된 급식 정보가 없습니다");
			return;
		}
		if (myHelper.getid("bab", "recdate") != null){
			if (myHelper.getid("bab", "recdate").equals("NP") == true) {
				TML.setText("네트워크 오류로 정보 업데이트가 중단되었습니다");
				TMD.setText("기기의 네트워크 연결 상황을 다시 확인해주세요");
			return;
		} else {
	//		recdate = Integer.valueOf(myHelper.getid("bab", "recdate"));
		//	Log.i("Net", "[ERROR] : 정보 만료 " + intdate + "//" + recdate);
/*			if (intdate > recdate) {
				Mlunch.setText("정보가 " + recdate + "자로 만료되었습니다");
				Mdinner.setText("네트워크에 연결하신 후, 상단의 <급식정보 업데이트> 버튼을 눌러 업데이트를 해주세요");
				return;
			}
	*/		}
		}else {
			TML.setText("네트워크 오류로 정보 업데이트가 중단되었습니다");
			TMD.setText("기기의 네트워크 연결 상황을 다시 확인해주세요");
		}
		plunch = pdinner = "nodata";
		String strdate = Integer.toString(intdate);

		cursor.moveToFirst();
		plunch=pdinner="nodata";
		

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
			TML.setText("점심 : " +"휴일 입니다");
			TMD.setText("저녁 : " +"휴일 입니다");
		} else if (plunch.equals("nodata")) { // DB안에 정보가 없을 경우
			TML.setText("점심 : " +"급식 정보가 없습니다.");
			TMD.setText("저녁 : " +"급식 정보가 없습니다.");
		} else {
			TML.setText("점심 : " +plunch);
			TMD.setText("저녁 : " +pdinner);

			if(plunch.equals("NP")){
				TML.setText("점심 : " +"급식이 없습니다.");				
			}
			if(pdinner.equals("NP")){
				TMD.setText("저녁 : " +"급식이 없습니다.");				
			}
		}
		Log.i("Net", "급식 표시 완료, " + plunch + pdinner);
		
		
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

	public void tableBuilder() {
		
		myHelper.close();
		myHelper.open("WRITE");
		myHelper.create("timeTableLock");
		myHelper.create("note");
		myHelper.close();
		myHelper.open("READ");

	}
	
	void ByeBye(){
		int AppShutdownNoti = 4;
		NotificationManager nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
		
		PendingIntent intent = PendingIntent.getActivity(
                Main.this, 0, 
                new Intent(Main.this, Today_meal.class), 0);
		
		int currentapiVersion = android.os.Build.VERSION.SDK_INT;
		Notification noti;
		if (currentapiVersion >= android.os.Build.VERSION_CODES.JELLY_BEAN){
			noti = new Notification.Builder(mContext)
			.setTicker("한가람앱을 이용해주셔서 감사합니다.\n오늘도 즐거운 하루되세요!")
			.setContentIntent(intent)
			.setSmallIcon(R.drawable.ic_launcher)
			.build();
		} else{
			noti = new NotificationCompat.Builder(mContext)
			.setTicker("한가람앱을 이용해주셔서 감사합니다.\n오늘도 즐거운 하루되세요!")
			.setContentIntent(intent)
			.setSmallIcon(R.drawable.ic_launcher)
			.build();
		}
		nm.notify(AppShutdownNoti, noti);
		nm.cancel(AppShutdownNoti);
		
	}

	class ScheTextPusherTD extends Thread{
		public void run(){
			ScheTextPusher();
		}
	}
	
	public final static Comparator<Work> myComparator = new Comparator<Work>() {

		private final Collator collator = Collator.getInstance();

		public int compare(Work arg0, Work arg1) {
			// TODO Auto-generated method stub
			return collator.compare(arg0.getDates(), arg1.getDates());
		}
	};
	
	void ScheTextPusher(){
		DbAdapter myHelper = new DbAdapter(mContext);
		myHelper.open("READ");
		Calendar calendar = Calendar.getInstance();
		int Y, D, M;
		String dates, events, dates_p, events_p;
		ArrayList<Work> todo = new ArrayList<Work>();
		ArrayList<Work> todo_p = new ArrayList<Work>();
		
		
		Y = calendar.get(Calendar.YEAR);
		M = calendar.get(Calendar.MONTH);
		D = calendar.get(Calendar.DAY_OF_MONTH);
		M++;
		int intdate = (Y * 10000 + M * 100 + D);
		
		ScheDate.setText("아직 일정정보가 저장되어있지 않습니다.");
		ScheDate.setSelected(true);
		ScheEvent.setText("일정을 저장하시려면 누르십시오.");
		ScheEvent.setSelected(true);
		ScheDate_p.setText("일정정보가 없습니다.");
		ScheEvent_p.setText("일정을 추가하십시오.");
		ScheDate_p.setSelected(true);
		ScheEvent_p.setSelected(true);
		
		Cursor cursor = myHelper.fetchd("sche");
		cursor.moveToFirst();
		
		int i = 0;
		if (cursor.getCount() != 0) {

			do {
				dates = cursor.getString(2);
				events = cursor.getString(1);
				Y = Integer.valueOf(dates) / 10000;
				int k = Integer.valueOf(dates) % 10000;
				M = k / 100;
				D = Integer.valueOf(dates) % 100;
				Work p = new Work(events, dates, Y, M, D, false);
				todo.add(p);
			}while (cursor.moveToNext());
				Collections.sort(todo, myComparator);
			
			do {
				
				Work p1 = todo.get(i);
				Log.i("Net", "공식일정 날짜는 "+p1.get_place());
				dates = p1.get_place();
				
				if (Integer.valueOf(dates) >= intdate) {
					Y = Integer.valueOf(dates) / 10000;
					int k = Integer.valueOf(dates) % 10000;
					M = k / 100;
					D = Integer.valueOf(dates) % 100;
					events = p1.get_works();
					
					ScheDate.setText("[ " +Y +"년 "+M +"월 "+D +"일" +" ]");
					ScheEvent.setText(events);
					Log.i("Net", "+ : " + events + dates + "<common> " + i);
					break;
				}
				i++;

			} while (i != todo.size());
		}
		
		cursor = myHelper.fetchd("sche_private");
		cursor.moveToFirst();

		i = 0;
		if (cursor.getCount() != 0) {

			do {
				dates_p = cursor.getString(2);
				events_p = cursor.getString(1);
				Y = Integer.valueOf(dates_p) / 10000;
				int k = Integer.valueOf(dates_p) % 10000;
				M = k / 100;
				D = Integer.valueOf(dates_p) % 100;
				Work p = new Work(events_p, dates_p, Y, M, D, false);
				todo_p.add(p);
			}while (cursor.moveToNext());
				Collections.sort(todo_p, myComparator);
			
			do {
				
				Work p1 = todo_p.get(i);
				Log.i("Net", "개인일정 날짜는 "+p1.get_place());
				dates_p = p1.get_place();
				
				if (Integer.valueOf(dates_p) >= intdate) {
					Y = Integer.valueOf(dates_p) / 10000;
					int k = Integer.valueOf(dates_p) % 10000;
					M = k / 100;
					D = Integer.valueOf(dates_p) % 100;
					events_p = p1.get_works();
					
					ScheDate_p.setText("[ " +Y +"년 "+M +"월 "+D +"일" +" ]");
					ScheEvent_p.setText(events_p);
					Log.i("Net", "+ : " + events_p + dates_p + "<private> " + i);
					break;
				}
				i++;

			} while (i != todo_p.size());
		}
		myHelper.close();
}
	public class Work {
		private int Y, M, D;
		private String works;
		private String place;
		private boolean isPrivate;

		public Work(String _works, String _place, int _Y, int _M, int _D,
				boolean _isPrivate) {
			this.works = _works;
			this.place = _place;
			this.Y = _Y;
			this.M = _M;
			this.D = _D;
			this.isPrivate = _isPrivate;
		}

		public boolean isPrivate() {
			return isPrivate;
		}

		public String getDates() {
			return Y * 10000 + M * 100 + D + "";
		}

		public int getY() {
			return Y;
		}

		public int getM() {
			return M;
		}

		public int getD() {
			return D;
		}

		public String get_works() {
			return works;
		}

		public String get_place() {
			return place;
		}

	}
	
	}