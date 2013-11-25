package com.ATeam.HangaramAPP;

import com.viewpagerindicator.CirclePageIndicator;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.FrameLayout;

public class Main_Tools extends ActionBarActivity{

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);


		setTheme(R.style.Default);
		setContentView(R.layout.viewpager_main);
		
		final ActionBar ab = getSupportActionBar();
		ab.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
		ab.setDisplayOptions(ActionBar.DISPLAY_SHOW_HOME
				| ActionBar.DISPLAY_SHOW_TITLE);
		ab.setTitle(getString(R.string.Act_Main_Tools));
		ab.setDisplayHomeAsUpEnabled(true);
		ab.setLogo(R.drawable.four_dots);
		ab.setDisplayUseLogoEnabled (true);

		ViewPager mPager = (ViewPager) findViewById(R.id.pager);
		mPager.setAdapter(new main_ToolsPagerAdapter(
				getApplicationContext()));
		// Bind the circle indicator to the adapter
		CirclePageIndicator CPIndicator = (CirclePageIndicator) findViewById(R.id.CPIndicator);
		CPIndicator.setViewPager(mPager);
		CPIndicator.setSnap(true);
		
		
		
		
		
	}
	
	private class main_ToolsPagerAdapter extends PagerAdapter {

		private LayoutInflater mInflater;

		public main_ToolsPagerAdapter(Context con) {
			super();
			mInflater = LayoutInflater.from(con);

		}
		
		@Override
		public Object instantiateItem(View pager, int position) {
			View v = null;
			if (position == 0) {
				v = mInflater.inflate(R.layout.menu_main_tools, null);
				v.findViewById(R.id.BCalc).setOnClickListener(
						mBTN_toCalc);
			}
			((ViewPager) pager).addView(v, 0);

			return v;
		}

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return 1;
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
	
	private OnClickListener mBTN_toCalc = new OnClickListener() {
		public void onClick(View v) {
			startActivity(new Intent(Main_Tools.this, Calc.class));
			overridePendingTransition(R.anim.top_to_bottom_in,
					R.anim.top_to_bottom_out);
		}
	};
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			// app icon in action bar clicked; go home
			finish();
			overridePendingTransition(R.anim.bottom_to_top_in,
					R.anim.bottom_to_top_out);
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
}
