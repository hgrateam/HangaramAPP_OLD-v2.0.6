package com.ATeam.HangaramAPP;

import java.util.List;

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

import com.viewpagerindicator.CirclePageIndicator;

public class Main_Community extends ActionBarActivity{
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setTheme(R.style.Default);

		setContentView(R.layout.viewpager_main);
		final ActionBar ab = getSupportActionBar();
		ab.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
		ab.setDisplayOptions(ActionBar.DISPLAY_SHOW_HOME
				| ActionBar.DISPLAY_SHOW_TITLE);
		ab.setTitle(getString(R.string.Act_Main_Community));
		ab.setDisplayHomeAsUpEnabled(true);
		ab.setLogo(R.drawable.communication_w);
		ab.setDisplayUseLogoEnabled (true);

		ViewPager mPager = (ViewPager) findViewById(R.id.pager);
		mPager.setAdapter(new main_CommunityPagerAdapter(
				getApplicationContext()));
		// Bind the circle indicator to the adapter
		CirclePageIndicator CPIndicator = (CirclePageIndicator) findViewById(R.id.CPIndicator);
		CPIndicator.setViewPager(mPager);
		CPIndicator.setSnap(true);
		
	}
	
	private class main_CommunityPagerAdapter extends PagerAdapter {

		private LayoutInflater mInflater;

		public main_CommunityPagerAdapter(Context con) {
			super();
			mInflater = LayoutInflater.from(con);

		}
		
		@Override
		public Object instantiateItem(View pager, int position) {
			View v = null;
			if (position == 0) {
				v = mInflater.inflate(R.layout.menu_main_community, null);
				v.findViewById(R.id.BCom16th).setOnClickListener(
						mBTN_Com16th);
				v.findViewById(R.id.BCom15th).setOnClickListener(
						mBTN_Com15th);
				v.findViewById(R.id.BCom17th).setOnClickListener(
						mBTN_Com17th);
				v.findViewById(R.id.BHGRency).setOnClickListener(
						mBTN_toHGRency);
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
	
	private OnClickListener mBTN_Com16th = new OnClickListener() {
		public void onClick(View v) {
			
			String urlFb = "fb://group/222927487789059";
			String urlBrowser = "http://www.facebook.com/groups/222927487789059/";
			launchFacebook(urlFb, urlBrowser);
			overridePendingTransition(R.anim.top_to_bottom_in,
					R.anim.top_to_bottom_out);
		}
	};
	
	private OnClickListener mBTN_Com15th = new OnClickListener() {
		public void onClick(View v) {
			
			String urlFb = "fb://group/hangaram15th";
			String urlBrowser = "http://www.facebook.com/groups/hangaram15th/";
			launchFacebook(urlFb, urlBrowser);
			overridePendingTransition(R.anim.top_to_bottom_in,
					R.anim.top_to_bottom_out);
		}
	};
	
	private OnClickListener mBTN_Com17th = new OnClickListener() {
		public void onClick(View v) {
			
			String urlFb = "fb://group/hangaram17";
			String urlBrowser = "http://www.facebook.com/groups/hangaram17/";
			launchFacebook(urlFb, urlBrowser);
			overridePendingTransition(R.anim.top_to_bottom_in,
					R.anim.top_to_bottom_out);
		}
	};
	
	private OnClickListener mBTN_toHGRency = new OnClickListener() {
		public void onClick(View v) {
			
			String urlFb = "fb://page/147662202017495";
			String urlBrowser = "http://www.facebook.com/hangarampedia/";
			launchFacebook(urlFb, urlBrowser);
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
