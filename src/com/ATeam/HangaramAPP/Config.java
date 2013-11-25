package com.ATeam.HangaramAPP;

import org.holoeverywhere.preference.PreferenceActivity;
import org.holoeverywhere.widget.Toast;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceManager;
import android.preference.PreferenceScreen;
import android.support.v7.app.ActionBar;
import android.util.Log;
import android.view.MenuItem;

public class Config extends PreferenceActivity {

	DbAdapter myHelper;
	Toast mToast;
	
	 @Override
	    public void onCreate(Bundle savedInstanceState){
		 setTheme(R.style.Default);
	        super.onCreate(savedInstanceState);
	        
	        addPreferencesFromResource(R.xml.config);
	        
	        ActionBar ab = getSupportActionBar();
			ab.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
			ab.setDisplayOptions(ActionBar.DISPLAY_SHOW_HOME
					| ActionBar.DISPLAY_SHOW_TITLE);
			ab.setTitle(getString(R.string.Act_Config));
			ab.setDisplayHomeAsUpEnabled(true);
			ab.setLogo(R.drawable.ic_action_settings);
			ab.setDisplayUseLogoEnabled (true);
	        
			myHelper = new DbAdapter(this);
	    }
	 
	 public boolean onPreferenceTreeClick(PreferenceScreen preferenceScreen, Preference preference)
	    {
	        // 어플리케이션 이름
	        if(preference.getKey().equals("config_reset_pref"))
	        {
	        	Log.i("Net", "설정 초기화 버튼 누름");
				
				SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(this);
				Editor editor = settings.edit();
				editor.clear();
				editor.commit();
				
				onContentChanged();
				startActivity(new Intent(Config.this, Config.class));
				overridePendingTransition(R.anim.zoom_out_still,
						R.anim.zoom_out_enter);
				finish();
	        }
	        
	        if(preference.getKey().equals("delete_meal_pref"))
	        {
	   	     // 노티스 보여주기
	   			myHelper.open("WRITE");
	   			myHelper.create("bab");
	   			myHelper.close();
	   			myHelper.open("WRITE");
	   			myHelper.deletetable("bab");
	   			myHelper.close();
	   			myHelper.open("WRITE");

	   			myHelper.create("bab");
	   			myHelper.close();

	   			showToast("이때까지 저장된 급식정보가 모두 삭제되었습니다.\n다음 급식업데이트는 시간이 다소 많이 소요될 수 있습니다.");
	        	
	        }

	        
	        
	        
	        
	        return false;
	    }
	 
	 public void showToast(String str) {
			mToast = Main.mToast;
			
			if (mToast == null) {
				mToast = Toast.makeText(Config.this, str,
						Toast.LENGTH_SHORT);
			} else {
				mToast.setText(str);
			}
			mToast.show();

		}
	 
	 
		@Override
		public void onBackPressed() {
			finish();
			overridePendingTransition(R.anim.top_to_bottom_in, R.anim.top_to_bottom_out);
		}
		
		@Override
		public boolean onOptionsItemSelected(MenuItem item) {
		    switch (item.getItemId()) {
		        case android.R.id.home:
		        	finish();
		            overridePendingTransition(R.anim.top_to_bottom_in, R.anim.top_to_bottom_out);
		            return true;
		        default:
		            return super.onOptionsItemSelected(item);
		    }
		}
}
