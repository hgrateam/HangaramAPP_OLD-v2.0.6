package com.ATeam.HangaramAPP;

import java.io.IOException;
import java.text.Collator;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;

import org.holoeverywhere.app.Activity;
import org.holoeverywhere.app.AlertDialog;
import org.holoeverywhere.app.ProgressDialog;
import org.holoeverywhere.widget.ListView;
import org.holoeverywhere.widget.Toast;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBar;
import android.text.Spannable;
import android.text.SpannableString;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.TextView;


public class Dday extends Activity {

	ProgressDialog mProgressDialog;
	Toast mToast;
	Context mContext;
	String value_events;
	DbAdapter myHelper;
	ListView list;
	TodoAdapter m_adapter;
	int mYear, mMonth, mDay;
	static final int DATE_DIALOG_ID = 999;
	
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		myHelper = new DbAdapter(this);

		myHelper.open("WRITE");
		myHelper.create("sche");
		myHelper.create("sche_private");
		myHelper.close();

		setTheme(R.style.Schedule);
		setContentView(R.layout.dday);

		mContext = this;

		list = (ListView) findViewById(R.id.ScheList);
		list.setOnItemClickListener(m_list);

		setActionBar();

		setDraw();

		/*
		 * Work p1 = new Work("Doomsday","On Earth",2012,12,21); Work p2 = new
		 * Work("Doomsday2","On Earth",2012,12,25); todo.add(p1); todo.add(p2);
		 */

		// TODO Auto-generated method stub
	}
	
	public void setActionBar(){
		

		 SpannableString s = new SpannableString(getString(R.string.Act_Dday));
		    s.setSpan(new TypefaceSpan(this, "SeoulNamsanB.ttf"), 0, s.length(),
		            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
		
		ActionBar ab = getSupportActionBar();
		ab.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
		ab.setDisplayOptions(ActionBar.DISPLAY_SHOW_HOME
				| ActionBar.DISPLAY_SHOW_TITLE);
		ab.setTitle(s);
		ab.setDisplayHomeAsUpEnabled(true);
		ab.setLogo(R.drawable.ic_action_event);
		ab.setDisplayUseLogoEnabled(true);
		
	}

	public void setDraw() {
		ArrayList<Work> todo = new ArrayList<Work>();

		m_adapter = new TodoAdapter(this, R.layout.dday_layrow, todo);

		TextView tv = (TextView) findViewById(R.id.ScheHelpText);
		DbAdapter myHelper = new DbAdapter(this);
		String dates, events;
		int Y, M, D;
		int dY, dM, dD;
		Calendar calendar = Calendar.getInstance();
		dY = calendar.get(Calendar.YEAR);
		dM = calendar.get(Calendar.MONTH);
		dD = calendar.get(Calendar.DAY_OF_MONTH);
		dM++;
		int ddate = dY * 10000 + dM * 100 + dD;
		int i;
		myHelper.open("READ");
		Cursor cursor = myHelper.fetchd("sche");
		cursor.moveToFirst();
		i = 1;
		if (cursor.getCount() != 0) {

			do {
				dates = cursor.getString(2);
				events = cursor.getString(1);
				Y = Integer.valueOf(dates) / 10000;
				int k = Integer.valueOf(dates) % 10000;
				M = k / 100;
				D = Integer.valueOf(dates) % 100;
				if (Integer.valueOf(dates) < ddate) {
					continue;
				}
				Work p1 = new Work(events, dates, Y, M, D, false);
				Log.i("Net", "+ : " + events + dates + "<common> " + i);
				todo.add(p1);
				i++;

			} while (cursor.moveToNext());
			Collections.sort(todo, myComparator);

			// setListAdapter(m_adapter);
			tv.setVisibility(View.GONE);
			list.setAdapter(m_adapter);
		} else {
			list.setAdapter(m_adapter);
		}
		cursor = myHelper.fetchd("sche_private");
		cursor.moveToFirst();
		i = 1;
		if (cursor.getCount() != 0) {

			do {
				dates = cursor.getString(2);
				events = cursor.getString(1);
				Y = Integer.valueOf(dates) / 10000;
				int k = Integer.valueOf(dates) % 10000;
				M = k / 100;
				D = Integer.valueOf(dates) % 100;
				Log.i("Net", "+ : " + events + dates + "<private> " + i);
				Work p1 = new Work(events, dates, Y, M, D, true);
				todo.add(p1);
				i++;
			} while (cursor.moveToNext());
			Collections.sort(todo, myComparator);

			// setListAdapter(m_adapter);
			tv.setVisibility(View.GONE);
			list.setAdapter(m_adapter);
		} else {
			list.setAdapter(m_adapter);
		}

		myHelper.close();
	}

	public class LoadingParsing extends AsyncTask<Void, String, Void> {

		protected void onCancelled() {
			super.onCancelled();
		}

		@Override
		protected void onPreExecute() {
			Log.i("Net", "AsyncTask 시작 - 다이얼로그 시작");

			mProgressDialog = new ProgressDialog(Dday.this);
			mProgressDialog.setTitle("학사일정 정보 받아오는중");
			mProgressDialog.setMessage("학사일정 정보를 서버에서 내려받아 기기 내부에 저장하고 있습니다.");
			mProgressDialog.setCancelable(false);
			mProgressDialog.show();

		}

		@Override
		protected void onPostExecute(Void result) {
			Log.i("Net", "AsyncTask 종료 - 다이얼로그 종료");

			myHelper.open("READ");
			Cursor cursor = myHelper.fetchd("sche");

			if (cursor != null && cursor.getCount() == 0)
				showToast("학사일정이 홈페이지에 아직 업로드되지 않았습니다.");
			else if (cursor != null && cursor.getCount() != 0)
				cursor.moveToFirst();
			else
				showToast("저장된 학사일정은 학교 사정에 따라 변경될 수 있다는 점을 숙지하여 주시기 바랍니다.");
			myHelper.close();

			mProgressDialog.dismiss();
			setDraw();
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
			ScheParse updater = new ScheParse(mContext);

			try {
				updater.parse(Y, M, true);
			} catch (IOException e) {
			}

			// mProgressDialog.dismiss();
			// TODO Auto-generated method stub
			return null;
		}

	}

	public class TodoAdapter extends ArrayAdapter<Work> {

		private ArrayList<Work> items;

		public TodoAdapter(Context context, int textViewResourceId,
				ArrayList<Work> items) {
			super(context, textViewResourceId, items);
			this.items = items;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			View v = convertView;
			if (v == null) {
				LayoutInflater vi = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
				v = vi.inflate(R.layout.dday_layrow, null);
			}
			Work p = items.get(position);
			if (p != null) {

				TextView tt = (TextView) v.findViewById(R.id.dday_works);
				TextView bt = (TextView) v.findViewById(R.id.dday_dates);
				if (tt != null) {
					tt.setText(p.get_works());
				}
				if (bt != null) {
					bt.setText("일시 : " + p.getY() + "년 " + p.getM() + "월 "
							+ p.getD() + "일");
				}
				TextView wt = (TextView) v.findViewById(R.id.dday_type);
				boolean isPrivate = p.isPrivate();
				if (isPrivate == false) {
					wt.setText("[공식]");
					wt.setTextColor(Color.parseColor("#ff1493"));
				} else {
					wt.setText("[개인]");
					wt.setTextColor(Color.parseColor("#00ff00"));
				}
			}
			return v;
		}
	}

	private final static Comparator<Work> myComparator = new Comparator<Work>() {

		private final Collator collator = Collator.getInstance();

		public int compare(Work arg0, Work arg1) {
			// TODO Auto-generated method stub
			return collator.compare(arg0.getDates(), arg1.getDates());
		}
	};

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

	public void showToast(String str) {
		if (mToast == null) {
			mToast = Toast.makeText(Dday.this, str, Toast.LENGTH_SHORT);
		} else {
			mToast.setText(str);
		}
		mToast.show();

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
				} else {
					return false;
				}
			}
		} catch (Exception e) {
		}
		return false;

	}

	public static boolean isStringDouble(String s) {
		try {
			Double.parseDouble(s);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}

	/*
	 * @Override protected void onListItemClick(ListView l, View v, int
	 * position, long id) {
	 * 
	 * super.onListItemClick(l, v, position, id); final Work data = (Work)
	 * this.getListAdapter().getItem(position);
	 * 
	 * AlertDialog.Builder adb = new AlertDialog.Builder(mContext);
	 * adb.setMessage("일정을 삭제하시겠습니까?"); adb.setPositiveButton("확인", new
	 * DialogInterface.OnClickListener(){
	 * 
	 * public void onClick(DialogInterface arg0, int arg1) { // TODO
	 * Auto-generated method stub
	 * 
	 * 
	 * myHelper.open("WRITE"); Cursor cursor = myHelper.fetchd("sche_private");
	 * cursor.moveToFirst(); boolean flag=false; if (cursor.getCount() != 0) {
	 * 
	 * do { if (cursor.getString(1).equals(data.get_works()) &&
	 * cursor.getString(2).equals(data.getDates())) {
	 * 
	 * int i = Integer.parseInt(cursor.getString(0)); Log.d("HangaramAPP",
	 * "i는 "+i);
	 * 
	 * myHelper.deleted("sche_private", i); //
	 * myHelper.update("sche_private",i,"오라오라오라오라","오라오라오라오라"); showToast("일정 "
	 * + data.get_works() + " ("+cursor.getString(2)+")을 삭제하였습니다. "); flag=true;
	 * break; } } while (cursor.moveToNext()); } if(flag==true){ SetDraw(); }
	 * else{ showToast("일정 " + data.get_works() + "는 공식 일정 이므로 삭제할 수 없습니다.");
	 * 
	 * } myHelper.close();
	 * 
	 * }}); adb.setNegativeButton("취소", null); adb.show();
	 * 
	 * 
	 * 
	 * }
	 */
	OnItemClickListener m_list = new OnItemClickListener() {
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			final Work data = (Work) m_adapter.getItem(position);

			AlertDialog.Builder adb = new AlertDialog.Builder(mContext);
			adb.setTitle("일정 삭제");
			adb.setMessage("일정을 삭제하시겠습니까?");
			adb.setPositiveButton("확인", new DialogInterface.OnClickListener() {

				public void onClick(DialogInterface arg0, int arg1) {
					// TODO Auto-generated method stub

					myHelper.open("WRITE");
					Cursor cursor = myHelper.fetchd("sche_private");
					cursor.moveToFirst();
					boolean flag = false;
					if (cursor.getCount() != 0) {

						do {
							if (cursor.getString(1).equals(data.get_works())
									&& cursor.getString(2).equals(
											data.getDates())) {

								int i = Integer.parseInt(cursor.getString(0));
								Log.d("HangaramAPP", "i는 " + i);

								myHelper.deleted("sche_private", i);
								// myHelper.update("sche_private",i,"오라오라오라오라","오라오라오라오라");
								showToast("일정 " + data.get_works() + " ("
										+ cursor.getString(2) + ")을 삭제하였습니다. ");
								flag = true;
								break;
							}
						} while (cursor.moveToNext());
					}
					if (flag == true) {
						setDraw();
					} else {
						showToast("일정 " + data.get_works()
								+ "는 공식 일정 이므로 삭제할 수 없습니다.");

					}
					myHelper.close();

				}
			});
			adb.setNegativeButton("취소", null);
			adb.show();

		}
	};

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.menu_dday, menu);
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

		case R.id.add_dday:

			AlertDialog.Builder alert_events = new AlertDialog.Builder(mContext);

			alert_events.setTitle("일정 추가");
			alert_events.setMessage("계획 이름을 적어주세요. \nex) 수학수행 ");

			// Set an EditText view to get user input
			final EditText _events = new EditText(mContext);

			alert_events.setView(_events);
			alert_events.setPositiveButton("입력",
					new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog,
								int whichButton) {

							Calendar c = Calendar.getInstance();
							mYear = c.get(Calendar.YEAR);
							mMonth = c.get(Calendar.MONTH);
							mDay = c.get(Calendar.DAY_OF_MONTH);

							value_events = _events.getText().toString();
							showDatePickerDialog();
							setDraw();
							// Do something with value!
						}
					});

			alert_events.setNegativeButton("취소", null);
			alert_events.show();

			return true;

		case R.id.parse_sche:
			if (NetStatCHK() == true) {
				DbAdapter myHelper = new DbAdapter(mContext);
				myHelper.open("WRITE");
				myHelper.deletetable("sche");
				myHelper.create("sche");
				myHelper.close();
				new LoadingParsing().execute();
			} else {
				String str = getString(R.string.NetStat_error);
				showToast(str);
			}
			return true;

		default:
			return super.onOptionsItemSelected(item);
		}
	}

	@Override
	public void onBackPressed() {
		finish();
		overridePendingTransition(R.anim.bottom_to_top_in,
				R.anim.bottom_to_top_out);

	}

	/*
	 * public class DatePickerFragment extends DatePickerDialog implements
	 * OnDateSetListener {
	 * 
	 * int dY, dM, dD; int mYear, mMonth, mDay;
	 * 
	 * @Override public void onCreate(Bundle savedInstanceState) {
	 * super.onCreate(savedInstanceState);
	 * 
	 * Calendar calendar = Calendar.getInstance(); dY =
	 * calendar.get(Calendar.YEAR); dM = calendar.get(Calendar.MONTH); dD =
	 * calendar.get(Calendar.DAY_OF_MONTH); dM++; initialize(this, dY, dM, dD);
	 * }
	 * 
	 * public void onDateSet(DatePickerDialog view, int year, int monthOfYear,
	 * int dayOfMonth) { mYear = year; mMonth = monthOfYear++; mDay =
	 * dayOfMonth;
	 * 
	 * Log.d("HangaramAPP", "monthOfYear는 " + monthOfYear);
	 * 
	 * int value_YEAR = mYear * 10000; int value_MONTH = mMonth * 100; int
	 * value_DAY = mDay; int value_DATE = value_YEAR + value_MONTH + value_DAY;
	 * 
	 * value_dates = String.valueOf(value_DATE);
	 * 
	 * } }
	 */
	/*
	public static class DatePickerFragment extends DialogFragment implements
			DatePickerDialog.OnDateSetListener {

		@Override
		public Dialog onCreateDialog(Bundle savedInstanceState) {
			// Use the current date as the default date in the picker
			final Calendar c = Calendar.getInstance();
			int year = c.get(Calendar.YEAR);
			int month = c.get(Calendar.MONTH);
			int day = c.get(Calendar.DAY_OF_MONTH);

			// Create a new instance of DatePickerDialog and return it
			return new DatePickerDialog(getActivity(), this, year, month, day);
		}

		public void onDateSet(DatePicker view, int year, int month, int day) {
			// Do something with the date chosen by the user
			
		}

	}
	*/
	
	public void showDatePickerDialog(){
		FragmentManager fm = getSupportFragmentManager();
        
		  DatePickerFragment date = new DatePickerFragment();
		Bundle currentDate = new Bundle();
		 final Calendar calendar = Calendar.getInstance(); 
		 currentDate.putInt("year", calendar.get(Calendar.YEAR));
		 currentDate.putInt("month", calendar.get(Calendar.MONTH));
		 currentDate.putInt("day", calendar.get(Calendar.DAY_OF_MONTH));
		 currentDate.putString("value_events", value_events);
		 date.setArguments(currentDate);
		 date.show(fm, "Date Picker");
		 setDraw();
	}
		
}
