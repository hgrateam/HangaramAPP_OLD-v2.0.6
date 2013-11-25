package com.ATeam.HangaramAPP;

import java.util.List;

import com.viewpagerindicator.CirclePageIndicator;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
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

public class Main_SchoolMenu extends ActionBarActivity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);


		setTheme(R.style.Default);
		setContentView(R.layout.viewpager_main);

		final ActionBar ab = getSupportActionBar();
		ab.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
		ab.setDisplayOptions(ActionBar.DISPLAY_SHOW_HOME
				| ActionBar.DISPLAY_SHOW_TITLE);
		ab.setTitle(getString(R.string.Act_Main_SchoolMenu));
		ab.setDisplayHomeAsUpEnabled(true);
		ab.setLogo(R.drawable.school_w);
		ab.setDisplayUseLogoEnabled (true);

		ViewPager mPager = (ViewPager) findViewById(R.id.pager);
		mPager.setAdapter(new main_SchoolMenuPagerAdapter(
				getApplicationContext()));
		// Bind the circle indicator to the adapter
		CirclePageIndicator CPIndicator = (CirclePageIndicator) findViewById(R.id.CPIndicator);
		CPIndicator.setViewPager(mPager);
		CPIndicator.setSnap(true);

	}

	private class main_SchoolMenuPagerAdapter extends PagerAdapter {

		private LayoutInflater mInflater;

		public main_SchoolMenuPagerAdapter(Context con) {
			super();
			mInflater = LayoutInflater.from(con);

		}

		@Override
		public Object instantiateItem(View pager, int position) {
			View v = null;
			if (position == 0) {
				v = mInflater.inflate(R.layout.menu_main_school, null);
				v.findViewById(R.id.BPrincipleWall).setOnClickListener(
						mBTN_toPrincipleWall);
				v.findViewById(R.id.BHGR).setOnClickListener(mBTN_toHGR);
				v.findViewById(R.id.BSchoolInfo).setOnClickListener(
						mBTN_SchoolInfo);
				v.findViewById(R.id.BLib).setOnClickListener(mBTN_toLib);
				v.findViewById(R.id.BAfterSchool).setOnClickListener(mBTN_toAfterSchool);
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
	private OnClickListener mBTN_toPrincipleWall = new OnClickListener() {
		public void onClick(View v) {
			
			String urlFb = "fb://profile/100001584597082";
			String urlBrowser = "https://www.facebook.com/sanjinii";
			launchFacebook(urlFb, urlBrowser);
			overridePendingTransition(R.anim.top_to_bottom_in,
					R.anim.top_to_bottom_out);
		}
	};

	private OnClickListener mBTN_toHGR = new OnClickListener() {
		public void onClick(View v) {
			startActivity(new Intent(Intent.ACTION_VIEW,
					Uri.parse("http://www.hangaram.hs.kr/")));
			overridePendingTransition(R.anim.top_to_bottom_in,
					R.anim.top_to_bottom_out);
		}
	};
	
	private OnClickListener mBTN_SchoolInfo = new OnClickListener() {
		public void onClick(View v) {
			startActivity(new Intent(Main_SchoolMenu.this, SchoolInfo.class));
			overridePendingTransition(R.anim.top_to_bottom_in,
					R.anim.top_to_bottom_out);
		}
	};
	
	private OnClickListener mBTN_toLib = new OnClickListener() {
		public void onClick(View v) {
			startActivity(new Intent(
					Intent.ACTION_VIEW,
					Uri.parse("http://reading.ssem.or.kr/r/dls/school_code_setting.jsp?school_code=9080&school_name=%ED%95%9C%EA%B0%80%EB%9E%8C%EA%B3%A0%EB%93%B1%ED%95%99%EA%B5%90")));
			overridePendingTransition(R.anim.top_to_bottom_in,
					R.anim.top_to_bottom_out);
		}
	};
	private OnClickListener mBTN_toAfterSchool = new OnClickListener() {
		public void onClick(View v) {
			startActivity(new Intent(
					Intent.ACTION_VIEW,
					Uri.parse("http://afterschool.sen.go.kr/student/stuIndex.do?sch_code=2100H135")));
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
	
	public final void launchFacebook(String urlFb, String urlBrowser) {
        //final  = "fb://page/192338590805862";
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(urlFb));

        // If a Facebook app is installed, use it. Otherwise, launch
        // a browser
        final PackageManager packageManager = getPackageManager();
        List<ResolveInfo> list =
            packageManager.queryIntentActivities(intent,
            PackageManager.MATCH_DEFAULT_ONLY);
        if (list.size() == 0) {
            //final  = "https://www.facebook.com/pages/Ziggys-Games/192338590805862";
            intent.setData(Uri.parse(urlBrowser));
        }
        
        startActivity(intent);
    }
}
