package com.ATeam.HangaramAPP;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;

public class Teachers_list extends ActionBarActivity {

	public static Activity Teachers_list;
	
	
	public static Teachers_list instance;
	public int POS = 0;
	public int Click_dat =0;
	public String DATA;
	public ArrayList<String> data1, data2, data3, data4, data5, data6, data7, data8,
			data9, data10;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		

		setTheme(R.style.Default);
		setContentView(R.layout.teachers_list);
		
		final ActionBar ab = getSupportActionBar();
    	ab.setDisplayOptions(ActionBar.DISPLAY_SHOW_HOME | ActionBar.DISPLAY_SHOW_TITLE);
		ab.setDisplayHomeAsUpEnabled(true);
    	ab.setTitle(getString(R.string.Act_Teachers_list));
    	ab.setLogo(R.drawable.teachers_w);
		ab.setDisplayUseLogoEnabled (true);
		
    	Teachers_list = Teachers_list.this;
		
		instance = this;

		// #1
		// List 내용
		data1 = new ArrayList<String>();
		data1.add("이옥식 선생님");
		data1.add("백성호 선생님");
		data1.add("이준희 선생님");
		data1.add("지영복 선생님");

		// #
		ArrayAdapter<String> adpater1 = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, data1);

		ListView list1 = (ListView) findViewById(R.id.tab1_listView);
		list1.setAdapter(adpater1);

		list1.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				
				Click_dat = 1;
				// 클릭된 아이템의 포지션을 이용해 어댑터뷰에서 아이템을 꺼내온다.
				   final String data_T = (String) parent.getItemAtPosition(position);
				   POS = position;
				   DATA = data_T;
				startActivity(new Intent(Teachers_list.this,
						Teachers_info.class));
				overridePendingTransition(R.anim.top_to_bottom_in, R.anim.top_to_bottom_out);
			}
		});

		// #2
		// List 내용
		data2 = new ArrayList<String>();
		data2.add("이용수 선생님");
		data2.add("신원용 선생님");
		data2.add("최승태 선생님");
		data2.add("정문종 선생님");
		data2.add("김현순 선생님");
		data2.add("이순이 선생님");
		data2.add("박광훈 선생님");
		data2.add("박영준 선생님");

		// #
		ArrayAdapter<String> adpater2 = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, data2);

		ListView list2 = (ListView) findViewById(R.id.tab2_listView);
		list2.setAdapter(adpater2);

		list2.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				
				Click_dat = 2;
				
				// 클릭된 아이템의 포지션을 이용해 어댑터뷰에서 아이템을 꺼내온다.
			   final String data_T = (String) parent.getItemAtPosition(position);
			   POS = position;
			   DATA = data_T;
				startActivity(new Intent(Teachers_list.this,
						Teachers_info.class));
				overridePendingTransition(R.anim.top_to_bottom_in, R.anim.top_to_bottom_out);
			}
		});

		// #3
		// List 내용
		data3 = new ArrayList<String>();
		data3.add("김성진 선생님");
		data3.add("진소영 선생님");
		data3.add("김정영 선생님");
		data3.add("이정진 선생님");
		data3.add("최윤선 선생님");
		data3.add("이숙재 선생님");
		data3.add("최지은 선생님");
		data3.add("강영현 선생님");
		data3.add("김명옥 선생님");
		data3.add("정성희 선생님");

		// #
		ArrayAdapter<String> adpater3 = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, data3);

		ListView list3 = (ListView) findViewById(R.id.tab3_listView);
		list3.setAdapter(adpater3);

		list3.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				
				Click_dat = 3;
				// 클릭된 아이템의 포지션을 이용해 어댑터뷰에서 아이템을 꺼내온다.
			   final String data_T = (String) parent.getItemAtPosition(position);
			   POS = position;
			   DATA = data_T;
			// 클릭된 아이템의 포지션을 확인해 본다.
			   Log.i("AndroidTutorial07Activity", "---------------------------------------------------------");
			   Log.i("AndroidTutorial07Activity", "onItemClick()");
			   Log.i("AndroidTutorial07Activity", "position is " + position);
			   Log.i("AndroidTutorial07Activity", "data3_POS is " + data_T);
			   Log.i("AndroidTutorial07Activity", "POS is " + POS);
			 
				startActivity(new Intent(Teachers_list.this,
						Teachers_info.class));
				overridePendingTransition(R.anim.top_to_bottom_in, R.anim.top_to_bottom_out);
			}
		});
		

		// #4
		// List 내용
		data4 = new ArrayList<String>();
		data4.add("이정자 선생님");
		data4.add("주상돈 선생님");
		data4.add("구자현 선생님");
		data4.add("김혜원 선생님");

		// #
		ArrayAdapter<String> adpater4 = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, data4);

		ListView list4 = (ListView) findViewById(R.id.tab4_listView);
		list4.setAdapter(adpater4);

		list4.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				
				Click_dat = 4;
				// 클릭된 아이템의 포지션을 이용해 어댑터뷰에서 아이템을 꺼내온다.
				   final String data_T = (String) parent.getItemAtPosition(position);
				   POS = position;
				   DATA = data_T;
				
				startActivity(new Intent(Teachers_list.this,
						Teachers_info.class));
				overridePendingTransition(R.anim.top_to_bottom_in, R.anim.top_to_bottom_out);
			}
		});
		
		
		// #5
		// List 내용
		data5 = new ArrayList<String>();
		data5.add("서문혜영 선생님");
		data5.add("박철진 선생님");
		data5.add("김예리 선생님");
		data5.add("안인선 선생님");

		// #
		ArrayAdapter<String> adpater5 = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, data5);

		ListView list5 = (ListView) findViewById(R.id.tab5_listView);
		list5.setAdapter(adpater5);

		list5.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				
				Click_dat = 5;
				// 클릭된 아이템의 포지션을 이용해 어댑터뷰에서 아이템을 꺼내온다.
				   final String data_T = (String) parent.getItemAtPosition(position);
				   POS = position;
				   DATA = data_T;
				
				startActivity(new Intent(Teachers_list.this,
						Teachers_info.class));
				overridePendingTransition(R.anim.top_to_bottom_in, R.anim.top_to_bottom_out);
			}
		});
		
		
		// #6
		// List 내용
		data6 = new ArrayList<String>();
		data6.add("강철 선생님");
		data6.add("김연주 선생님");
		data6.add("이현주 선생님");
		data6.add("정성훈 선생님");
		data6.add("정태영 선생님");
		data6.add("나승철 선생님");
		data6.add("최정희 선생님");

		// #
		ArrayAdapter<String> adpater6 = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, data6);

		ListView list6 = (ListView) findViewById(R.id.tab6_listView);
		list6.setAdapter(adpater6);

		list6.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				
				Click_dat = 6;
				// 클릭된 아이템의 포지션을 이용해 어댑터뷰에서 아이템을 꺼내온다.
				   final String data_T = (String) parent.getItemAtPosition(position);
				   POS = position;
				   DATA = data_T;
				
				startActivity(new Intent(Teachers_list.this,
						Teachers_info.class));
				overridePendingTransition(R.anim.top_to_bottom_in, R.anim.top_to_bottom_out);
			}
		});
		
		
		// #7
		// List 내용
		data7 = new ArrayList<String>();
		data7.add("김정오 선생님");
		data7.add("오지민 선생님");
		data7.add("박성조 선생님");
		data7.add("정지영 선생님");
		data7.add("유승완 선생님");
		data7.add("김민지 선생님");

		// #
		ArrayAdapter<String> adpater7 = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, data7);

		ListView list7 = (ListView) findViewById(R.id.tab7_listView);
		list7.setAdapter(adpater7);

		list7.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				
				Click_dat = 7;
				// 클릭된 아이템의 포지션을 이용해 어댑터뷰에서 아이템을 꺼내온다.
				   final String data_T = (String) parent.getItemAtPosition(position);
				   POS = position;
				   DATA = data_T;
				
				startActivity(new Intent(Teachers_list.this,
						Teachers_info.class));
				overridePendingTransition(R.anim.top_to_bottom_in, R.anim.top_to_bottom_out);
			}
		});
		
		
		// #8
		// List 내용
		data8 = new ArrayList<String>();
		data8.add("서현주 선생님");
		data8.add("김경진 선생님");
		data8.add("이준상 선생님");
		data8.add("허은지 선생님");
		data8.add("박고운 선생님");
		data8.add("글라라 고 선생님");
		data8.add("Philip Marnell 선생님");
		data8.add("조민나 선생님");

		// #
		ArrayAdapter<String> adpater8 = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, data8);

		ListView list8 = (ListView) findViewById(R.id.tab8_listView);
		list8.setAdapter(adpater8);

		list8.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				
				Click_dat = 8;
				// 클릭된 아이템의 포지션을 이용해 어댑터뷰에서 아이템을 꺼내온다.
				   final String data_T = (String) parent.getItemAtPosition(position);
				   POS = position;
				   DATA = data_T;
				
				startActivity(new Intent(Teachers_list.this,
						Teachers_info.class));
				overridePendingTransition(R.anim.top_to_bottom_in, R.anim.top_to_bottom_out);
			}
		});							
		
		
		// #9
		// List 내용
		data9 = new ArrayList<String>();
		data9.add("최병재 선생님");
		data9.add("이용주 선생님");
		data9.add("이예승 선생님");
		data9.add("박영호 선생님");
		data9.add("곽미선 선생님");
		data9.add("김병수 선생님");
		data9.add("기희진 선생님");
		data9.add("권영주 선생님");
		data9.add("박음정 선생님");

		// #
		ArrayAdapter<String> adpater9 = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, data9);

		ListView list9 = (ListView) findViewById(R.id.tab9_listView);
		list9.setAdapter(adpater9);

		list9.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				
				Click_dat = 9;
				// 클릭된 아이템의 포지션을 이용해 어댑터뷰에서 아이템을 꺼내온다.
				   final String data_T = (String) parent.getItemAtPosition(position);
				   POS = position;
				   DATA = data_T;
				
				startActivity(new Intent(Teachers_list.this,
						Teachers_info.class));
				overridePendingTransition(R.anim.top_to_bottom_in, R.anim.top_to_bottom_out);
			}
		});							
				
		
		// #10
		// List 내용
		data10 = new ArrayList<String>();
		data10.add("유영만 선생님");
		data10.add("윤치경 선생님");
		data10.add("나옥주 선생님");
		data10.add("윤창현 선생님");
		data10.add("이윤희 선생님");
		data10.add("문인수 선생님");
		data10.add("전혜리 선생님");
		data10.add("송은우 선생님");
		data10.add("허현숙 선생님");

		// #
		ArrayAdapter<String> adpater10 = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, data10);

		ListView list10 = (ListView) findViewById(R.id.tab10_listView);
		list10.setAdapter(adpater10);

		list10.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				
				Click_dat = 10;
				// 클릭된 아이템의 포지션을 이용해 어댑터뷰에서 아이템을 꺼내온다.
				   final String data_T = (String) parent.getItemAtPosition(position);
				   POS = position;
				   DATA = data_T;
				
				startActivity(new Intent(Teachers_list.this,
						Teachers_info.class));
				overridePendingTransition(R.anim.top_to_bottom_in, R.anim.top_to_bottom_out);
			}
		});							
					
		
//
		// 여기서부터 건들지 마시오.

		TabHost tabhost = (TabHost) findViewById(R.id.tab_host);
		tabhost.setup();

		// 대표 선생님_tab1
		TabSpec tbspc1 = tabhost.newTabSpec("Tab1");
		tbspc1.setIndicator("     대표     ");
		tbspc1.setContent(R.id.tab1);
		tabhost.addTab(tbspc1);

		// 교무업무지원 선생님_tab2
		TabSpec tbspc2 = tabhost.newTabSpec("Tab2");
		tbspc2.setIndicator(" 교무업무지원 ");
		tbspc2.setContent(R.id.tab2);
		tabhost.addTab(tbspc2);

		// AA 선생님_tab3
		TabSpec tbspc3 = tabhost.newTabSpec("Tab3");
		tbspc3.setIndicator(" Academic Adviser ");
		tbspc3.setContent(R.id.tab3);
		tabhost.addTab(tbspc3);

		// 국어 선생님_tab4
		TabSpec tbspc4 = tabhost.newTabSpec("Tab4");
		tbspc4.setIndicator("    국어과    ");
		tbspc4.setContent(R.id.tab4);
		tabhost.addTab(tbspc4);

		// 사회과 선생님_tab5
		TabSpec tbspc5 = tabhost.newTabSpec("Tab5");
		tbspc5.setIndicator("    사회과    ");
		tbspc5.setContent(R.id.tab5);
		tabhost.addTab(tbspc5);

		// 수학과 선생님_tab6
		TabSpec tbspc6 = tabhost.newTabSpec("Tab6");
		tbspc6.setIndicator("    수학과    ");
		tbspc6.setContent(R.id.tab6);
		tabhost.addTab(tbspc6);

		// 과학과 선생님_tab7
		TabSpec tbspc7 = tabhost.newTabSpec("Tab7");
		tbspc7.setIndicator("    과학과    ");
		tbspc7.setContent(R.id.tab7);
		tabhost.addTab(tbspc7);

		// 영어과/중국어 선생님_tab8
		TabSpec tbspc8 = tabhost.newTabSpec("Tab8");
		tbspc8.setIndicator(" 영어과/중국어 ");
		tbspc8.setContent(R.id.tab8);
		tabhost.addTab(tbspc8);

		// 실기교과/사서/보건실 선생님_tab9
		TabSpec tbspc9 = tabhost.newTabSpec("Tab9");
		tbspc9.setIndicator(" 실기교과/사서/보건실 ");
		tbspc9.setContent(R.id.tab9);
		tabhost.addTab(tbspc9);

		// 행정실 선생님_tab10
		TabSpec tbspc10 = tabhost.newTabSpec("Tab10");
		tbspc10.setIndicator("    행정실    ");
		tbspc10.setContent(R.id.tab10);
		tabhost.addTab(tbspc10);

	}
	@Override
	public void onBackPressed() {
		finish();
		overridePendingTransition(R.anim.bottom_to_top_in, R.anim.bottom_to_top_out);
		
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    switch (item.getItemId()) {
	        case android.R.id.home:
	            // app icon in action bar clicked; go home
	        	/*
	            Intent intent = new Intent(this, Main.class);
	            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
	            startActivity(intent);
	            */
	        	finish();
	            overridePendingTransition(R.anim.bottom_to_top_in, R.anim.bottom_to_top_out);
	            return true;
	        default:
	            return super.onOptionsItemSelected(item);
	    }
	}
	
}
