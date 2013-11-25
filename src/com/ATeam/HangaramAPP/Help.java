package com.ATeam.HangaramAPP;

import com.viewpagerindicator.CirclePageIndicator;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.BitmapDrawable;
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
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

public class Help extends ActionBarActivity {


	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setTheme(R.style.Default);
		setContentView(R.layout.viewpager_help);
		
		ViewPager mPager = (ViewPager)findViewById(R.id.help_pager);
		mPager.setAdapter(new HelpPagerAdapter(getApplicationContext()));
		
		// Bind the circle indicator to the adapter
				CirclePageIndicator CPIndicator = (CirclePageIndicator) findViewById(R.id.CPIndicator);
				CPIndicator.setViewPager(mPager);
				CPIndicator.setSnap(true);
		
		
		
	       final ActionBar actionBar = getSupportActionBar();
	       actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_HOME | ActionBar.DISPLAY_SHOW_TITLE);
	       actionBar.setDisplayHomeAsUpEnabled(true);
	       actionBar.setTitle(getString(R.string.Act_Help));
	       actionBar.setLogo(R.drawable.ic_action_help);
	       actionBar.setDisplayUseLogoEnabled (true);
           
           
           CPIndicator.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
			public void onPageScrollStateChanged(int state) {
				// TODO Auto-generated method stub
				
			}

			public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
				// TODO Auto-generated method stub
				
			}

			public void onPageSelected(int position) {
				// TODO Auto-generated method stub
				if (position == 6){
					Button passbutton = (Button)findViewById(R.id.passbutton);
					passbutton.setVisibility(View.VISIBLE);
				}
			}
           });
           
           
           
		
		Button passbutton = (Button)findViewById(R.id.passbutton);
		passbutton.setOnClickListener(new Button.OnClickListener() {

			public void onClick(View v) {
	    		 startActivity(new Intent (Help.this, Main.class));
	    		 finish();
	    		 overridePendingTransition(R.anim.top_to_bottom_in, R.anim.top_to_bottom_out);
			}

		});
		
	}
		
		private class HelpPagerAdapter extends PagerAdapter{
			private LayoutInflater mInflater;
			
			public HelpPagerAdapter(Context con){
				super();
				mInflater = LayoutInflater.from(con);
				
			}

			@Override
			public int getCount() {
				// TODO Auto-generated method stub
				return 7;
			}
			
			@Override
			public Object instantiateItem(View pager, int position) {
				View v = null;
				if (position == 0){
					v = mInflater.inflate(R.layout.help, null);
					
					ImageView img = (ImageView) v.findViewById(R.id.help_img);
					img.setVisibility(View.GONE);
					
					TextView text = (TextView) v.findViewById(R.id.help_text);
					text.setText("계속하시려면 화면을 왼쪽으로 스크롤하세요.");
					text.setTextSize(25);
					
				}
				else if (position == 1) {
					v = mInflater.inflate(R.layout.help, null);
					ImageView img = (ImageView) v.findViewById(R.id.help_img);
					img.setVisibility(View.VISIBLE);
					
				}
				else if (position == 2) {
					v = mInflater.inflate(R.layout.help, null);
					
					ImageView img = (ImageView) v.findViewById(R.id.help_img);
					BitmapDrawable bimg = (BitmapDrawable) getResources().getDrawable(R.drawable.help_main_notice);
					img.setImageDrawable(bimg);
					
					TextView text = (TextView) v.findViewById(R.id.help_text);
					text.setText(R.string.help_main_notice);
					
				}
				
				else if (position == 3) {
					v = mInflater.inflate(R.layout.help, null);
					
					ImageView img = (ImageView) v.findViewById(R.id.help_img);
					BitmapDrawable bimg = (BitmapDrawable) getResources().getDrawable(R.drawable.help_main_preference);
					img.setImageDrawable(bimg);
					
					TextView text = (TextView) v.findViewById(R.id.help_text);
					text.setText(R.string.help_main_preference);
					
				}
				
				else if (position == 4) {
					v = mInflater.inflate(R.layout.help, null);
					
					ImageView img = (ImageView) v.findViewById(R.id.help_img);
					BitmapDrawable bimg = (BitmapDrawable) getResources().getDrawable(R.drawable.help_preference_help);
					img.setImageDrawable(bimg);
					
					TextView text = (TextView) v.findViewById(R.id.help_text);
					text.setText(R.string.help_preference_help);
					
				}
				
				else if (position == 5) {
					v = mInflater.inflate(R.layout.help, null);
					
					ImageView img = (ImageView) v.findViewById(R.id.help_img);
					BitmapDrawable bimg = (BitmapDrawable) getResources().getDrawable(R.drawable.help_parsing_dbupdate);
					img.setImageDrawable(bimg);
					
					TextView text = (TextView) v.findViewById(R.id.help_text);
					text.setText(R.string.help_parsing_dbupdate);
					
				}
				
				else if (position == 6) {
					v = mInflater.inflate(R.layout.help, null);
					
					ImageView img = (ImageView) v.findViewById(R.id.help_img);
					img.setVisibility(View.GONE);
					
					TextView text = (TextView) v.findViewById(R.id.help_text);
					text.setText(R.string.help_ect);
					
				}
				
				
				((ViewPager) pager).addView(v, 0);

				return v;
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
		
		
		
		
		
	
	@Override
	public void onBackPressed() {
		finish();
		startActivity(new Intent(this, Main.class));
		overridePendingTransition(R.anim.top_to_bottom_in, R.anim.top_to_bottom_out);

		
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    switch (item.getItemId()) {
	        case android.R.id.home:
	        	finish();
	    		startActivity(new Intent(this, Main.class));
	            overridePendingTransition(R.anim.top_to_bottom_in, R.anim.top_to_bottom_out);
	            return true;
	        default:
	            return super.onOptionsItemSelected(item);
	    }
	}
	
}
