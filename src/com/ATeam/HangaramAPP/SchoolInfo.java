package com.ATeam.HangaramAPP;

import org.holoeverywhere.widget.Toast;

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

import com.viewpagerindicator.CirclePageIndicator;


public class SchoolInfo extends ActionBarActivity{
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setTheme(R.style.Default);
		setContentView(R.layout.viewpager_main);
		Toast ht = Toast.makeText(this, R.string.Now_constructing, Toast.LENGTH_SHORT);
		ht.show();
		
		final ActionBar ab = getSupportActionBar();
		ab.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
		ab.setDisplayOptions(ActionBar.DISPLAY_SHOW_HOME
				| ActionBar.DISPLAY_SHOW_TITLE);
		ab.setTitle(getString(R.string.Act_Main_SchoolInfo));
		ab.setDisplayHomeAsUpEnabled(true);
		ab.setLogo(R.drawable.school_info_w);
		ab.setDisplayUseLogoEnabled (true);

		ViewPager mPager = (ViewPager) findViewById(R.id.pager);
		mPager.setAdapter(new SchoolInfoPagerAdapter(
				getApplicationContext()));
		// Bind the circle indicator to the adapter
		CirclePageIndicator CPIndicator = (CirclePageIndicator) findViewById(R.id.CPIndicator);
		CPIndicator.setViewPager(mPager);
		CPIndicator.setSnap(true);
		
		
		
		
		
	}
	
	private class SchoolInfoPagerAdapter extends PagerAdapter {

		private LayoutInflater mInflater;

		public SchoolInfoPagerAdapter(Context con) {
			super();
			mInflater = LayoutInflater.from(con);

		}
		
		@Override
		public Object instantiateItem(View pager, int position) {
			View v = null;
			if (position == 0) {
				v = mInflater.inflate(R.layout.school_info, null);
				v.findViewById(R.id.BTeachers).setOnClickListener(
						mBTN_toTeachers_info);
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
	
	private OnClickListener mBTN_toTeachers_info = new OnClickListener() {
		public void onClick(View v) {
			startActivity(new Intent(SchoolInfo.this, Teachers_list.class));
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
