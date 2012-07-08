package com.ex.HangaramAPP;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class Devteam_info extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
	   super.onCreate(savedInstanceState);
       setContentView(R.layout.devteam_info);
	
       
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
		startActivity(new Intent(Devteam_info.this, Main.class));
		finish();
		overridePendingTransition(R.anim.top_to_bottom_in, R.anim.top_to_bottom_out);
	}
	

}