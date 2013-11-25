package com.ATeam.HangaramAPP;


import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class Devteam_info extends ActionBarActivity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
	   super.onCreate(savedInstanceState);
	   
	   setTheme(R.style.Default);
       setContentView(R.layout.devteam_info);
       
       final ActionBar actionBar = getSupportActionBar();
       actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_HOME | ActionBar.DISPLAY_SHOW_TITLE);
       actionBar.setDisplayHomeAsUpEnabled(true);
       actionBar.setTitle(getString(R.string.Act_Devteam_info));
       actionBar.setLogo(R.drawable.ic_action_about);
       actionBar.setDisplayUseLogoEnabled (true);
	
       
       ImageButton img_btn;
	    img_btn = (ImageButton) findViewById(R.id.Ateam_logo);
	    img_btn.setOnClickListener(new Button.OnClickListener(){
	    	public void onClick(View v){
	    		startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://hgra.tistory.com/")));
	    	}
	    	
	    	
	    });
	
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
	            // app icon in action bar clicked; go home
	            finish();
	            overridePendingTransition(R.anim.top_to_bottom_in, R.anim.top_to_bottom_out);
	            return true;
	        default:
	            return super.onOptionsItemSelected(item);
	    }
	}

}