package com.ATeam.HangaramAPP;


import android.content.SharedPreferences;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

public class Teachers_info extends ActionBarActivity {

	
	Teachers_list aActivity = (Teachers_list)Teachers_list.Teachers_list;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		

		setTheme(R.style.Default);
		setContentView(R.layout.teachers_info);
		
		String data = Teachers_list.instance.DATA ;
		
		final ActionBar ab = getSupportActionBar();
    	ab.setDisplayOptions(ActionBar.DISPLAY_SHOW_HOME | ActionBar.DISPLAY_SHOW_TITLE);
		ab.setDisplayHomeAsUpEnabled(true);
    	ab.setTitle(data);
    	ab.setLogo(R.drawable.teachers_w);
		ab.setDisplayUseLogoEnabled (true);
		
		
		
		int posit = Teachers_list.instance.POS ;
		int Click_dat = Teachers_list.instance.Click_dat;
		
		
		

		if(Click_dat == 1){
			
			if (posit == 0){

				ImageView TPic = (ImageView) findViewById(R.id.Teachers_Pic);
				BitmapDrawable img = (BitmapDrawable) getResources().getDrawable(R.drawable.hgrapp);
				TPic.setImageDrawable(img);

				TextView TTxt1 = (TextView) findViewById(R.id.Teachers_NAME);
				TTxt1.setText(data);
				
				TextView TTxt2 = (TextView) findViewById(R.id.Teachers_PART);
				TTxt2.setText("이사장");
				
				TextView TTxt3 = (TextView) findViewById(R.id.Teachers_SUBJ);
				TTxt3.setText("초대교장");
			}
			

			if (posit == 1){

				ImageView TPic = (ImageView) findViewById(R.id.Teachers_Pic);
				BitmapDrawable img = (BitmapDrawable) getResources().getDrawable(R.drawable.hgrapp);
				TPic.setImageDrawable(img);

				TextView TTxt1 = (TextView) findViewById(R.id.Teachers_NAME);
				TTxt1.setText(data);
				
				TextView TTxt2 = (TextView) findViewById(R.id.Teachers_PART);
				TTxt2.setText("교장");
				
				TextView TTxt3 = (TextView) findViewById(R.id.Teachers_SUBJ);
				TTxt3.setText("일반사회");
			}
			
			if (posit == 2){

				ImageView TPic = (ImageView) findViewById(R.id.Teachers_Pic);
				BitmapDrawable img = (BitmapDrawable) getResources().getDrawable(R.drawable.hgrapp);
				TPic.setImageDrawable(img);

				TextView TTxt1 = (TextView) findViewById(R.id.Teachers_NAME);
				TTxt1.setText(data);
				
				TextView TTxt2 = (TextView) findViewById(R.id.Teachers_PART);
				TTxt2.setText("교감");
				
				TextView TTxt3 = (TextView) findViewById(R.id.Teachers_SUBJ);
				TTxt3.setText("국어");
			}
			
			if (posit == 3){

				ImageView TPic = (ImageView) findViewById(R.id.Teachers_Pic);
				BitmapDrawable img = (BitmapDrawable) getResources().getDrawable(R.drawable.hgrapp);
				TPic.setImageDrawable(img);

				TextView TTxt1 = (TextView) findViewById(R.id.Teachers_NAME);
				TTxt1.setText(data);
				
				TextView TTxt2 = (TextView) findViewById(R.id.Teachers_PART);
				TTxt2.setText("행정실장");
				
				TextView TTxt3 = (TextView) findViewById(R.id.Teachers_SUBJ);
				TTxt3.setText("");
			}
			
		}
		else if(Click_dat == 2){
			
			if(posit == 0){
				ImageView TPic = (ImageView) findViewById(R.id.Teachers_Pic);
				BitmapDrawable img = (BitmapDrawable) getResources().getDrawable(R.drawable.hgrapp);
				TPic.setImageDrawable(img);

				TextView TTxt1 = (TextView) findViewById(R.id.Teachers_NAME);
				TTxt1.setText(data);
				
				TextView TTxt2 = (TextView) findViewById(R.id.Teachers_PART);
				TTxt2.setText("학생활동지원부장");
				
				TextView TTxt3 = (TextView) findViewById(R.id.Teachers_SUBJ);
				TTxt3.setText("체육");
			}
			
			else if(posit == 1){
				ImageView TPic = (ImageView) findViewById(R.id.Teachers_Pic);
				BitmapDrawable img = (BitmapDrawable) getResources().getDrawable(R.drawable.hgrapp);
				TPic.setImageDrawable(img);

				TextView TTxt1 = (TextView) findViewById(R.id.Teachers_NAME);
				TTxt1.setText(data);
				
				TextView TTxt2 = (TextView) findViewById(R.id.Teachers_PART);
				TTxt2.setText("연구부장");
				
				TextView TTxt3 = (TextView) findViewById(R.id.Teachers_SUBJ);
				TTxt3.setText("국어");
			}
			
			else if(posit == 2){
				ImageView TPic = (ImageView) findViewById(R.id.Teachers_Pic);
				BitmapDrawable img = (BitmapDrawable) getResources().getDrawable(R.drawable.hgrapp);
				TPic.setImageDrawable(img);

				TextView TTxt1 = (TextView) findViewById(R.id.Teachers_NAME);
				TTxt1.setText(data);
				
				TextView TTxt2 = (TextView) findViewById(R.id.Teachers_PART);
				TTxt2.setText("교육과정 부장");
				
				TextView TTxt3 = (TextView) findViewById(R.id.Teachers_SUBJ);
				TTxt3.setText("일반사회,경제");
			}
			
			else if(posit == 3){
				ImageView TPic = (ImageView) findViewById(R.id.Teachers_Pic);
				BitmapDrawable img = (BitmapDrawable) getResources().getDrawable(R.drawable.hgrapp);
				TPic.setImageDrawable(img);

				TextView TTxt1 = (TextView) findViewById(R.id.Teachers_NAME);
				TTxt1.setText(data);
				
				TextView TTxt2 = (TextView) findViewById(R.id.Teachers_PART);
				TTxt2.setText("정보부장");
				
				TextView TTxt3 = (TextView) findViewById(R.id.Teachers_SUBJ);
				TTxt3.setText("컴퓨터");
			}			
			else if(posit == 4){
				ImageView TPic = (ImageView) findViewById(R.id.Teachers_Pic);
				BitmapDrawable img = (BitmapDrawable) getResources().getDrawable(R.drawable.hgrapp);
				TPic.setImageDrawable(img);

				TextView TTxt1 = (TextView) findViewById(R.id.Teachers_NAME);
				TTxt1.setText(data);
				
				TextView TTxt2 = (TextView) findViewById(R.id.Teachers_PART);
				TTxt2.setText("전문상담사");
				
				TextView TTxt3 = (TextView) findViewById(R.id.Teachers_SUBJ);
				TTxt3.setText("");
			}
			
			
			else if(posit == 5){
				ImageView TPic = (ImageView) findViewById(R.id.Teachers_Pic);
				BitmapDrawable img = (BitmapDrawable) getResources().getDrawable(R.drawable.hgrapp);
				TPic.setImageDrawable(img);

				TextView TTxt1 = (TextView) findViewById(R.id.Teachers_NAME);
				TTxt1.setText(data);
				
				TextView TTxt2 = (TextView) findViewById(R.id.Teachers_PART);
				TTxt2.setText("전문상담사");
				
				TextView TTxt3 = (TextView) findViewById(R.id.Teachers_SUBJ);
				TTxt3.setText("");
			}
			
			
			else if(posit == 6){
				ImageView TPic = (ImageView) findViewById(R.id.Teachers_Pic);
				BitmapDrawable img = (BitmapDrawable) getResources().getDrawable(R.drawable.hgrapp);
				TPic.setImageDrawable(img);

				TextView TTxt1 = (TextView) findViewById(R.id.Teachers_NAME);
				TTxt1.setText(data);
				
				TextView TTxt2 = (TextView) findViewById(R.id.Teachers_PART);
				TTxt2.setText("자율학습지도");
				
				TextView TTxt3 = (TextView) findViewById(R.id.Teachers_SUBJ);
				TTxt3.setText("");
			}
			
			
			else if(posit == 7){
				ImageView TPic = (ImageView) findViewById(R.id.Teachers_Pic);
				BitmapDrawable img = (BitmapDrawable) getResources().getDrawable(R.drawable.hgrapp);
				TPic.setImageDrawable(img);

				TextView TTxt1 = (TextView) findViewById(R.id.Teachers_NAME);
				TTxt1.setText(data);
				
				TextView TTxt2 = (TextView) findViewById(R.id.Teachers_PART);
				TTxt2.setText("학생지도");
				
				TextView TTxt3 = (TextView) findViewById(R.id.Teachers_SUBJ);
				TTxt3.setText("");
			}
		}
		
		else if(Click_dat == 3){		
		
		if (posit == 0){

			ImageView TPic = (ImageView) findViewById(R.id.Teachers_Pic);
			BitmapDrawable img = (BitmapDrawable) getResources().getDrawable(R.drawable.hgrapp);
			TPic.setImageDrawable(img);

			TextView TTxt1 = (TextView) findViewById(R.id.Teachers_NAME);
			TTxt1.setText(data);
			
			TextView TTxt2 = (TextView) findViewById(R.id.Teachers_PART);
			TTxt2.setText("Adviser 팀장");
			
			TextView TTxt3 = (TextView) findViewById(R.id.Teachers_SUBJ);
			TTxt3.setText("과학,물리");
		}
		
		
		else if (posit == 1){
			
			ImageView TPic = (ImageView) findViewById(R.id.Teachers_Pic);
			BitmapDrawable img = (BitmapDrawable) getResources().getDrawable(R.drawable.hgrapp);
			TPic.setImageDrawable(img);
			
			TextView TTxt1 = (TextView) findViewById(R.id.Teachers_NAME);
			TTxt1.setText(data);
			
			TextView TTxt2 = (TextView) findViewById(R.id.Teachers_PART);
			TTxt2.setText("1반 Adviser(2,3)");
			
			TextView TTxt3 = (TextView) findViewById(R.id.Teachers_SUBJ);
			TTxt3.setText("국어");
		}
		else if (posit == 2){

			ImageView TPic = (ImageView) findViewById(R.id.Teachers_Pic);
			BitmapDrawable img = (BitmapDrawable) getResources().getDrawable(R.drawable.hgrapp);
			TPic.setImageDrawable(img);

			TextView TTxt1 = (TextView) findViewById(R.id.Teachers_NAME);
			TTxt1.setText(data);
			
			TextView TTxt2 = (TextView) findViewById(R.id.Teachers_PART);
			TTxt2.setText("2반 Adviser");
			
			TextView TTxt3 = (TextView) findViewById(R.id.Teachers_SUBJ);
			TTxt3.setText("일본어");
			
		}
		else if (posit == 3){

			ImageView TPic = (ImageView) findViewById(R.id.Teachers_Pic);
			BitmapDrawable img = (BitmapDrawable) getResources().getDrawable(R.drawable.hgrapp);
			TPic.setImageDrawable(img);

			TextView TTxt1 = (TextView) findViewById(R.id.Teachers_NAME);
			TTxt1.setText(data);
			
			TextView TTxt2 = (TextView) findViewById(R.id.Teachers_PART);
			TTxt2.setText("3반 Adviser");
			
			TextView TTxt3 = (TextView) findViewById(R.id.Teachers_SUBJ);
			TTxt3.setText("생물");
			
		}
		else if (posit == 4){

			ImageView TPic = (ImageView) findViewById(R.id.Teachers_Pic);
			BitmapDrawable img = (BitmapDrawable) getResources().getDrawable(R.drawable.hgrapp);
			TPic.setImageDrawable(img);
			
			TextView TTxt1 = (TextView) findViewById(R.id.Teachers_NAME);
			TTxt1.setText(data);
			
			TextView TTxt2 = (TextView) findViewById(R.id.Teachers_PART);
			TTxt2.setText("4반 Adviser");
			
			TextView TTxt3 = (TextView) findViewById(R.id.Teachers_SUBJ);
			TTxt3.setText("지리");
		}
		else if (posit == 5){

			ImageView TPic = (ImageView) findViewById(R.id.Teachers_Pic);
			BitmapDrawable img = (BitmapDrawable) getResources().getDrawable(R.drawable.hgrapp);
			TPic.setImageDrawable(img);
			
			TextView TTxt1 = (TextView) findViewById(R.id.Teachers_NAME);
			TTxt1.setText(data);
			
			TextView TTxt2 = (TextView) findViewById(R.id.Teachers_PART);
			TTxt2.setText("5반 Adviser");
			
			TextView TTxt3 = (TextView) findViewById(R.id.Teachers_SUBJ);
			TTxt3.setText("국어");
		}
		else if (posit == 6){

			ImageView TPic = (ImageView) findViewById(R.id.Teachers_Pic);
			BitmapDrawable img = (BitmapDrawable) getResources().getDrawable(R.drawable.hgrapp);
			TPic.setImageDrawable(img);
			
			TextView TTxt1 = (TextView) findViewById(R.id.Teachers_NAME);
			TTxt1.setText(data);
			
			TextView TTxt2 = (TextView) findViewById(R.id.Teachers_PART);
			TTxt2.setText("6반 Adviser");
			
			TextView TTxt3 = (TextView) findViewById(R.id.Teachers_SUBJ);
			TTxt3.setText("체육");
			
		}
		else if (posit == 7){

			ImageView TPic = (ImageView) findViewById(R.id.Teachers_Pic);
			BitmapDrawable img = (BitmapDrawable) getResources().getDrawable(R.drawable.hgrapp);
			TPic.setImageDrawable(img);
			
			TextView TTxt1 = (TextView) findViewById(R.id.Teachers_NAME);
			TTxt1.setText(data);
			
			TextView TTxt2 = (TextView) findViewById(R.id.Teachers_PART);
			TTxt2.setText("7반 Adviser");
			
			TextView TTxt3 = (TextView) findViewById(R.id.Teachers_SUBJ);
			TTxt3.setText("수학");
		}
		else if (posit == 8){

			ImageView TPic = (ImageView) findViewById(R.id.Teachers_Pic);
			BitmapDrawable img = (BitmapDrawable) getResources().getDrawable(R.drawable.hgrapp);
			TPic.setImageDrawable(img);

			TextView TTxt1 = (TextView) findViewById(R.id.Teachers_NAME);
			TTxt1.setText(data);
			
			TextView TTxt2 = (TextView) findViewById(R.id.Teachers_PART);
			TTxt2.setText("9반 Adviser(2,3) \n1반 Adviser(1)");
			
			TextView TTxt3 = (TextView) findViewById(R.id.Teachers_SUBJ);
			TTxt3.setText("한문");
		}
		else if (posit == 9){

			ImageView TPic = (ImageView) findViewById(R.id.Teachers_Pic);
			BitmapDrawable img = (BitmapDrawable) getResources().getDrawable(R.drawable.hgrapp);
			TPic.setImageDrawable(img);

			TextView TTxt1 = (TextView) findViewById(R.id.Teachers_NAME);
			TTxt1.setText(data);
			
			TextView TTxt2 = (TextView) findViewById(R.id.Teachers_PART);
			TTxt2.setText("10반 Adviser(2,3) \n8반 Adviser(1)");
			
			TextView TTxt3 = (TextView) findViewById(R.id.Teachers_SUBJ);
			TTxt3.setText("역사");
		}
		
		}
		else if(Click_dat == 4){
			if (posit == 0){
				
				ImageView TPic = (ImageView) findViewById(R.id.Teachers_Pic);
				BitmapDrawable img = (BitmapDrawable) getResources().getDrawable(R.drawable.hgrapp);
				TPic.setImageDrawable(img);

				TextView TTxt1 = (TextView) findViewById(R.id.Teachers_NAME);
				TTxt1.setText(data);
				
				TextView TTxt2 = (TextView) findViewById(R.id.Teachers_PART);
				TTxt2.setText("국어");
				
				TextView TTxt3 = (TextView) findViewById(R.id.Teachers_SUBJ);
				TTxt3.setText("");
			}
			
			else if (posit == 1){

				ImageView TPic = (ImageView) findViewById(R.id.Teachers_Pic);
				BitmapDrawable img = (BitmapDrawable) getResources().getDrawable(R.drawable.hgrapp);
				TPic.setImageDrawable(img);

				TextView TTxt1 = (TextView) findViewById(R.id.Teachers_NAME);
				TTxt1.setText(data);
				
				TextView TTxt2 = (TextView) findViewById(R.id.Teachers_PART);
				TTxt2.setText("국어");
				
				TextView TTxt3 = (TextView) findViewById(R.id.Teachers_SUBJ);
				TTxt3.setText("");
			}
			
			else if (posit == 2){

				ImageView TPic = (ImageView) findViewById(R.id.Teachers_Pic);
				BitmapDrawable img = (BitmapDrawable) getResources().getDrawable(R.drawable.hgrapp);
				TPic.setImageDrawable(img);

				TextView TTxt1 = (TextView) findViewById(R.id.Teachers_NAME);
				TTxt1.setText(data);
				
				TextView TTxt2 = (TextView) findViewById(R.id.Teachers_PART);
				TTxt2.setText("국어");
				
				TextView TTxt3 = (TextView) findViewById(R.id.Teachers_SUBJ);
				TTxt3.setText("");
			}
			
			else if (posit == 3){

				ImageView TPic = (ImageView) findViewById(R.id.Teachers_Pic);
				BitmapDrawable img = (BitmapDrawable) getResources().getDrawable(R.drawable.hgrapp);
				TPic.setImageDrawable(img);

				TextView TTxt1 = (TextView) findViewById(R.id.Teachers_NAME);
				TTxt1.setText(data);
				
				TextView TTxt2 = (TextView) findViewById(R.id.Teachers_PART);
				TTxt2.setText("국어");
				
				TextView TTxt3 = (TextView) findViewById(R.id.Teachers_SUBJ);
				TTxt3.setText("");
			}
			
		}
		
		else if(Click_dat == 5){
			
			if (posit == 0){

				ImageView TPic = (ImageView) findViewById(R.id.Teachers_Pic);
				BitmapDrawable img = (BitmapDrawable) getResources().getDrawable(R.drawable.hgrapp);
				TPic.setImageDrawable(img);

				TextView TTxt1 = (TextView) findViewById(R.id.Teachers_NAME);
				TTxt1.setText(data);
				
				TextView TTxt2 = (TextView) findViewById(R.id.Teachers_PART);
				TTxt2.setText("역사");
				
				TextView TTxt3 = (TextView) findViewById(R.id.Teachers_SUBJ);
				TTxt3.setText("");
			}
			
			else if (posit == 1){

				ImageView TPic = (ImageView) findViewById(R.id.Teachers_Pic);
				BitmapDrawable img = (BitmapDrawable) getResources().getDrawable(R.drawable.hgrapp);
				TPic.setImageDrawable(img);

				TextView TTxt1 = (TextView) findViewById(R.id.Teachers_NAME);
				TTxt1.setText(data);
				
				TextView TTxt2 = (TextView) findViewById(R.id.Teachers_PART);
				TTxt2.setText("지리");
				
				TextView TTxt3 = (TextView) findViewById(R.id.Teachers_SUBJ);
				TTxt3.setText("");
			}
			
			else if (posit == 2){

				ImageView TPic = (ImageView) findViewById(R.id.Teachers_Pic);
				BitmapDrawable img = (BitmapDrawable) getResources().getDrawable(R.drawable.hgrapp);
				TPic.setImageDrawable(img);

				TextView TTxt1 = (TextView) findViewById(R.id.Teachers_NAME);
				TTxt1.setText(data);
				
				TextView TTxt2 = (TextView) findViewById(R.id.Teachers_PART);
				TTxt2.setText("사회");
				
				TextView TTxt3 = (TextView) findViewById(R.id.Teachers_SUBJ);
				TTxt3.setText("");
			}
			
			else if (posit == 3){

				ImageView TPic = (ImageView) findViewById(R.id.Teachers_Pic);
				BitmapDrawable img = (BitmapDrawable) getResources().getDrawable(R.drawable.hgrapp);
				TPic.setImageDrawable(img);

				TextView TTxt1 = (TextView) findViewById(R.id.Teachers_NAME);
				TTxt1.setText(data);
				
				TextView TTxt2 = (TextView) findViewById(R.id.Teachers_PART);
				TTxt2.setText("윤리");
				
				TextView TTxt3 = (TextView) findViewById(R.id.Teachers_SUBJ);
				TTxt3.setText("");
			}
			
			
		}
		else if (Click_dat == 6){
			
			if (posit == 0){

				ImageView TPic = (ImageView) findViewById(R.id.Teachers_Pic);
				BitmapDrawable img = (BitmapDrawable) getResources().getDrawable(R.drawable.hgrapp);
				TPic.setImageDrawable(img);

				TextView TTxt1 = (TextView) findViewById(R.id.Teachers_NAME);
				TTxt1.setText(data);
				
				TextView TTxt2 = (TextView) findViewById(R.id.Teachers_PART);
				TTxt2.setText("수학");
				
				TextView TTxt3 = (TextView) findViewById(R.id.Teachers_SUBJ);
				TTxt3.setText("");
			}
			else if (posit == 1){

				ImageView TPic = (ImageView) findViewById(R.id.Teachers_Pic);
				BitmapDrawable img = (BitmapDrawable) getResources().getDrawable(R.drawable.hgrapp);
				TPic.setImageDrawable(img);

				TextView TTxt1 = (TextView) findViewById(R.id.Teachers_NAME);
				TTxt1.setText(data);
				
				TextView TTxt2 = (TextView) findViewById(R.id.Teachers_PART);
				TTxt2.setText("수학");
				
				TextView TTxt3 = (TextView) findViewById(R.id.Teachers_SUBJ);
				TTxt3.setText("");
			}
			else if (posit == 2){

				ImageView TPic = (ImageView) findViewById(R.id.Teachers_Pic);
				BitmapDrawable img = (BitmapDrawable) getResources().getDrawable(R.drawable.hgrapp);
				TPic.setImageDrawable(img);

				TextView TTxt1 = (TextView) findViewById(R.id.Teachers_NAME);
				TTxt1.setText(data);
				
				TextView TTxt2 = (TextView) findViewById(R.id.Teachers_PART);
				TTxt2.setText("수학");
				
				TextView TTxt3 = (TextView) findViewById(R.id.Teachers_SUBJ);
				TTxt3.setText("");
			}
			else if (posit == 3){

				ImageView TPic = (ImageView) findViewById(R.id.Teachers_Pic);
				BitmapDrawable img = (BitmapDrawable) getResources().getDrawable(R.drawable.hgrapp);
				TPic.setImageDrawable(img);

				TextView TTxt1 = (TextView) findViewById(R.id.Teachers_NAME);
				TTxt1.setText(data);
				
				TextView TTxt2 = (TextView) findViewById(R.id.Teachers_PART);
				TTxt2.setText("수학");
				
				TextView TTxt3 = (TextView) findViewById(R.id.Teachers_SUBJ);
				TTxt3.setText("");
			}
			else if (posit == 4){

				ImageView TPic = (ImageView) findViewById(R.id.Teachers_Pic);
				BitmapDrawable img = (BitmapDrawable) getResources().getDrawable(R.drawable.hgrapp);
				TPic.setImageDrawable(img);

				TextView TTxt1 = (TextView) findViewById(R.id.Teachers_NAME);
				TTxt1.setText(data);
				
				TextView TTxt2 = (TextView) findViewById(R.id.Teachers_PART);
				TTxt2.setText("수학");
				
				TextView TTxt3 = (TextView) findViewById(R.id.Teachers_SUBJ);
				TTxt3.setText("");
			}
			else if (posit == 5){

				ImageView TPic = (ImageView) findViewById(R.id.Teachers_Pic);
				BitmapDrawable img = (BitmapDrawable) getResources().getDrawable(R.drawable.hgrapp);
				TPic.setImageDrawable(img);

				TextView TTxt1 = (TextView) findViewById(R.id.Teachers_NAME);
				TTxt1.setText(data);
				
				TextView TTxt2 = (TextView) findViewById(R.id.Teachers_PART);
				TTxt2.setText("수학");
				
				TextView TTxt3 = (TextView) findViewById(R.id.Teachers_SUBJ);
				TTxt3.setText("");
			}
			else if (posit == 6){

				ImageView TPic = (ImageView) findViewById(R.id.Teachers_Pic);
				BitmapDrawable img = (BitmapDrawable) getResources().getDrawable(R.drawable.hgrapp);
				TPic.setImageDrawable(img);

				TextView TTxt1 = (TextView) findViewById(R.id.Teachers_NAME);
				TTxt1.setText(data);
				
				TextView TTxt2 = (TextView) findViewById(R.id.Teachers_PART);
				TTxt2.setText("수학");
				
				TextView TTxt3 = (TextView) findViewById(R.id.Teachers_SUBJ);
				TTxt3.setText("");
			}
			
			
		}
		else if(Click_dat == 7){
			
			if (posit == 0){

				ImageView TPic = (ImageView) findViewById(R.id.Teachers_Pic);
				BitmapDrawable img = (BitmapDrawable) getResources().getDrawable(R.drawable.hgrapp);
				TPic.setImageDrawable(img);

				TextView TTxt1 = (TextView) findViewById(R.id.Teachers_NAME);
				TTxt1.setText(data);
				
				TextView TTxt2 = (TextView) findViewById(R.id.Teachers_PART);
				TTxt2.setText("화학");
				
				TextView TTxt3 = (TextView) findViewById(R.id.Teachers_SUBJ);
				TTxt3.setText("");
			}
			else if (posit == 1){

				ImageView TPic = (ImageView) findViewById(R.id.Teachers_Pic);
				BitmapDrawable img = (BitmapDrawable) getResources().getDrawable(R.drawable.hgrapp);
				TPic.setImageDrawable(img);

				TextView TTxt1 = (TextView) findViewById(R.id.Teachers_NAME);
				TTxt1.setText(data);
				
				TextView TTxt2 = (TextView) findViewById(R.id.Teachers_PART);
				TTxt2.setText("생물");
				
				TextView TTxt3 = (TextView) findViewById(R.id.Teachers_SUBJ);
				TTxt3.setText("");
			}
			else if (posit == 2){

				ImageView TPic = (ImageView) findViewById(R.id.Teachers_Pic);
				BitmapDrawable img = (BitmapDrawable) getResources().getDrawable(R.drawable.hgrapp);
				TPic.setImageDrawable(img);

				TextView TTxt1 = (TextView) findViewById(R.id.Teachers_NAME);
				TTxt1.setText(data);
				
				TextView TTxt2 = (TextView) findViewById(R.id.Teachers_PART);
				TTxt2.setText("물리");
				
				TextView TTxt3 = (TextView) findViewById(R.id.Teachers_SUBJ);
				TTxt3.setText("");
			}
			else if (posit == 3){

				ImageView TPic = (ImageView) findViewById(R.id.Teachers_Pic);
				BitmapDrawable img = (BitmapDrawable) getResources().getDrawable(R.drawable.hgrapp);
				TPic.setImageDrawable(img);

				TextView TTxt1 = (TextView) findViewById(R.id.Teachers_NAME);
				TTxt1.setText(data);
				
				TextView TTxt2 = (TextView) findViewById(R.id.Teachers_PART);
				TTxt2.setText("화학");
				
				TextView TTxt3 = (TextView) findViewById(R.id.Teachers_SUBJ);
				TTxt3.setText("");
			}
			else if (posit == 4){

				ImageView TPic = (ImageView) findViewById(R.id.Teachers_Pic);
				BitmapDrawable img = (BitmapDrawable) getResources().getDrawable(R.drawable.hgrapp);
				TPic.setImageDrawable(img);

				TextView TTxt1 = (TextView) findViewById(R.id.Teachers_NAME);
				TTxt1.setText(data);
				
				TextView TTxt2 = (TextView) findViewById(R.id.Teachers_PART);
				TTxt2.setText("물리");
				
				TextView TTxt3 = (TextView) findViewById(R.id.Teachers_SUBJ);
				TTxt3.setText("");
			}
			else if (posit == 5){

				ImageView TPic = (ImageView) findViewById(R.id.Teachers_Pic);
				BitmapDrawable img = (BitmapDrawable) getResources().getDrawable(R.drawable.hgrapp);
				TPic.setImageDrawable(img);

				TextView TTxt1 = (TextView) findViewById(R.id.Teachers_NAME);
				TTxt1.setText(data);
				
				TextView TTxt2 = (TextView) findViewById(R.id.Teachers_PART);
				TTxt2.setText("지구과학");
				
				TextView TTxt3 = (TextView) findViewById(R.id.Teachers_SUBJ);
				TTxt3.setText("");
			}
			
		}
		else if(Click_dat == 8){
			
			if (posit == 0){

				ImageView TPic = (ImageView) findViewById(R.id.Teachers_Pic);
				BitmapDrawable img = (BitmapDrawable) getResources().getDrawable(R.drawable.hgrapp);
				TPic.setImageDrawable(img);

				TextView TTxt1 = (TextView) findViewById(R.id.Teachers_NAME);
				TTxt1.setText(data);
				
				TextView TTxt2 = (TextView) findViewById(R.id.Teachers_PART);
				TTxt2.setText("영어");
				
				TextView TTxt3 = (TextView) findViewById(R.id.Teachers_SUBJ);
				TTxt3.setText("");
			}
			else if (posit == 1){

				ImageView TPic = (ImageView) findViewById(R.id.Teachers_Pic);
				BitmapDrawable img = (BitmapDrawable) getResources().getDrawable(R.drawable.hgrapp);
				TPic.setImageDrawable(img);

				TextView TTxt1 = (TextView) findViewById(R.id.Teachers_NAME);
				TTxt1.setText(data);
				
				TextView TTxt2 = (TextView) findViewById(R.id.Teachers_PART);
				TTxt2.setText("영어");
				
				TextView TTxt3 = (TextView) findViewById(R.id.Teachers_SUBJ);
				TTxt3.setText("");
			}
			else if (posit == 2){

				ImageView TPic = (ImageView) findViewById(R.id.Teachers_Pic);
				BitmapDrawable img = (BitmapDrawable) getResources().getDrawable(R.drawable.hgrapp);
				TPic.setImageDrawable(img);

				TextView TTxt1 = (TextView) findViewById(R.id.Teachers_NAME);
				TTxt1.setText(data);
				
				TextView TTxt2 = (TextView) findViewById(R.id.Teachers_PART);
				TTxt2.setText("영어");
				
				TextView TTxt3 = (TextView) findViewById(R.id.Teachers_SUBJ);
				TTxt3.setText("");
			}
			else if (posit == 3){

				ImageView TPic = (ImageView) findViewById(R.id.Teachers_Pic);
				BitmapDrawable img = (BitmapDrawable) getResources().getDrawable(R.drawable.hgrapp);
				TPic.setImageDrawable(img);

				TextView TTxt1 = (TextView) findViewById(R.id.Teachers_NAME);
				TTxt1.setText(data);
				
				TextView TTxt2 = (TextView) findViewById(R.id.Teachers_PART);
				TTxt2.setText("영어");
				
				TextView TTxt3 = (TextView) findViewById(R.id.Teachers_SUBJ);
				TTxt3.setText("");
			}
			else if (posit == 4){

				ImageView TPic = (ImageView) findViewById(R.id.Teachers_Pic);
				BitmapDrawable img = (BitmapDrawable) getResources().getDrawable(R.drawable.hgrapp);
				TPic.setImageDrawable(img);

				TextView TTxt1 = (TextView) findViewById(R.id.Teachers_NAME);
				TTxt1.setText(data);
				
				TextView TTxt2 = (TextView) findViewById(R.id.Teachers_PART);
				TTxt2.setText("영어");
				
				TextView TTxt3 = (TextView) findViewById(R.id.Teachers_SUBJ);
				TTxt3.setText("");
			}
			else if (posit == 5){

				ImageView TPic = (ImageView) findViewById(R.id.Teachers_Pic);
				BitmapDrawable img = (BitmapDrawable) getResources().getDrawable(R.drawable.hgrapp);
				TPic.setImageDrawable(img);

				TextView TTxt1 = (TextView) findViewById(R.id.Teachers_NAME);
				TTxt1.setText(data);
				
				TextView TTxt2 = (TextView) findViewById(R.id.Teachers_PART);
				TTxt2.setText("영어");
				
				TextView TTxt3 = (TextView) findViewById(R.id.Teachers_SUBJ);
				TTxt3.setText("");
			}
			else if (posit == 6){

				ImageView TPic = (ImageView) findViewById(R.id.Teachers_Pic);
				BitmapDrawable img = (BitmapDrawable) getResources().getDrawable(R.drawable.hgrapp);
				TPic.setImageDrawable(img);

				TextView TTxt1 = (TextView) findViewById(R.id.Teachers_NAME);
				TTxt1.setText(data);
				
				TextView TTxt2 = (TextView) findViewById(R.id.Teachers_PART);
				TTxt2.setText("영어");
				
				TextView TTxt3 = (TextView) findViewById(R.id.Teachers_SUBJ);
				TTxt3.setText("");
			}
			else if (posit == 7){

				ImageView TPic = (ImageView) findViewById(R.id.Teachers_Pic);
				BitmapDrawable img = (BitmapDrawable) getResources().getDrawable(R.drawable.hgrapp);
				TPic.setImageDrawable(img);

				TextView TTxt1 = (TextView) findViewById(R.id.Teachers_NAME);
				TTxt1.setText(data);
				
				TextView TTxt2 = (TextView) findViewById(R.id.Teachers_PART);
				TTxt2.setText("중국어");
				
				TextView TTxt3 = (TextView) findViewById(R.id.Teachers_SUBJ);
				TTxt3.setText("");
			}
			
		}
		
		else if(Click_dat == 9){
			
			if (posit == 0){

				ImageView TPic = (ImageView) findViewById(R.id.Teachers_Pic);
				BitmapDrawable img = (BitmapDrawable) getResources().getDrawable(R.drawable.hgrapp);
				TPic.setImageDrawable(img);

				TextView TTxt1 = (TextView) findViewById(R.id.Teachers_NAME);
				TTxt1.setText(data);
				
				TextView TTxt2 = (TextView) findViewById(R.id.Teachers_PART);
				TTxt2.setText("미술");
				
				TextView TTxt3 = (TextView) findViewById(R.id.Teachers_SUBJ);
				TTxt3.setText("");
			}
			else if (posit == 1){

				ImageView TPic = (ImageView) findViewById(R.id.Teachers_Pic);
				BitmapDrawable img = (BitmapDrawable) getResources().getDrawable(R.drawable.hgrapp);
				TPic.setImageDrawable(img);

				TextView TTxt1 = (TextView) findViewById(R.id.Teachers_NAME);
				TTxt1.setText(data);
				
				TextView TTxt2 = (TextView) findViewById(R.id.Teachers_PART);
				TTxt2.setText("음악");
				
				TextView TTxt3 = (TextView) findViewById(R.id.Teachers_SUBJ);
				TTxt3.setText("");
			}
			else if (posit == 2){

				ImageView TPic = (ImageView) findViewById(R.id.Teachers_Pic);
				BitmapDrawable img = (BitmapDrawable) getResources().getDrawable(R.drawable.hgrapp);
				TPic.setImageDrawable(img);

				TextView TTxt1 = (TextView) findViewById(R.id.Teachers_NAME);
				TTxt1.setText(data);
				
				TextView TTxt2 = (TextView) findViewById(R.id.Teachers_PART);
				TTxt2.setText("한국조리");
				
				TextView TTxt3 = (TextView) findViewById(R.id.Teachers_SUBJ);
				TTxt3.setText("");
			}
			else if (posit == 3){

				ImageView TPic = (ImageView) findViewById(R.id.Teachers_Pic);
				BitmapDrawable img = (BitmapDrawable) getResources().getDrawable(R.drawable.hgrapp);
				TPic.setImageDrawable(img);

				TextView TTxt1 = (TextView) findViewById(R.id.Teachers_NAME);
				TTxt1.setText(data);
				
				TextView TTxt2 = (TextView) findViewById(R.id.Teachers_PART);
				TTxt2.setText("체육");
				
				TextView TTxt3 = (TextView) findViewById(R.id.Teachers_SUBJ);
				TTxt3.setText("");
			}
			else if (posit == 4){

				ImageView TPic = (ImageView) findViewById(R.id.Teachers_Pic);
				BitmapDrawable img = (BitmapDrawable) getResources().getDrawable(R.drawable.hgrapp);
				TPic.setImageDrawable(img);

				TextView TTxt1 = (TextView) findViewById(R.id.Teachers_NAME);
				TTxt1.setText(data);
				
				TextView TTxt2 = (TextView) findViewById(R.id.Teachers_PART);
				TTxt2.setText("사서");
				
				TextView TTxt3 = (TextView) findViewById(R.id.Teachers_SUBJ);
				TTxt3.setText("");
			}
			else if (posit == 5){

				ImageView TPic = (ImageView) findViewById(R.id.Teachers_Pic);
				BitmapDrawable img = (BitmapDrawable) getResources().getDrawable(R.drawable.hgrapp);
				TPic.setImageDrawable(img);

				TextView TTxt1 = (TextView) findViewById(R.id.Teachers_NAME);
				TTxt1.setText(data);
				
				TextView TTxt2 = (TextView) findViewById(R.id.Teachers_PART);
				TTxt2.setText("영화");
				
				TextView TTxt3 = (TextView) findViewById(R.id.Teachers_SUBJ);
				TTxt3.setText("");
			}
			else if (posit == 6){

				ImageView TPic = (ImageView) findViewById(R.id.Teachers_Pic);
				BitmapDrawable img = (BitmapDrawable) getResources().getDrawable(R.drawable.hgrapp);
				TPic.setImageDrawable(img);

				TextView TTxt1 = (TextView) findViewById(R.id.Teachers_NAME);
				TTxt1.setText(data);
				
				TextView TTxt2 = (TextView) findViewById(R.id.Teachers_PART);
				TTxt2.setText("연극");
				
				TextView TTxt3 = (TextView) findViewById(R.id.Teachers_SUBJ);
				TTxt3.setText("");
			}
			else if (posit == 7){

				ImageView TPic = (ImageView) findViewById(R.id.Teachers_Pic);
				BitmapDrawable img = (BitmapDrawable) getResources().getDrawable(R.drawable.hgrapp);
				TPic.setImageDrawable(img);

				TextView TTxt1 = (TextView) findViewById(R.id.Teachers_NAME);
				TTxt1.setText(data);
				
				TextView TTxt2 = (TextView) findViewById(R.id.Teachers_PART);
				TTxt2.setText("보건교사");
				
				TextView TTxt3 = (TextView) findViewById(R.id.Teachers_SUBJ);
				TTxt3.setText("");
			}
			else if (posit == 8){

				ImageView TPic = (ImageView) findViewById(R.id.Teachers_Pic);
				BitmapDrawable img = (BitmapDrawable) getResources().getDrawable(R.drawable.hgrapp);
				TPic.setImageDrawable(img);

				TextView TTxt1 = (TextView) findViewById(R.id.Teachers_NAME);
				TTxt1.setText(data);
				
				TextView TTxt2 = (TextView) findViewById(R.id.Teachers_PART);
				TTxt2.setText("보건교사");
				
				TextView TTxt3 = (TextView) findViewById(R.id.Teachers_SUBJ);
				TTxt3.setText("");
			}
			
			
		}
		
		else if(Click_dat == 10){
			
			if (posit == 0){

				ImageView TPic = (ImageView) findViewById(R.id.Teachers_Pic);
				BitmapDrawable img = (BitmapDrawable) getResources().getDrawable(R.drawable.hgrapp);
				TPic.setImageDrawable(img);

				TextView TTxt1 = (TextView) findViewById(R.id.Teachers_NAME);
				TTxt1.setText(data);
				
				TextView TTxt2 = (TextView) findViewById(R.id.Teachers_PART);
				TTxt2.setText("행정실");
				
				TextView TTxt3 = (TextView) findViewById(R.id.Teachers_SUBJ);
				TTxt3.setText("");
			}
			else if (posit == 1){

				ImageView TPic = (ImageView) findViewById(R.id.Teachers_Pic);
				BitmapDrawable img = (BitmapDrawable) getResources().getDrawable(R.drawable.hgrapp);
				TPic.setImageDrawable(img);

				TextView TTxt1 = (TextView) findViewById(R.id.Teachers_NAME);
				TTxt1.setText(data);
				
				TextView TTxt2 = (TextView) findViewById(R.id.Teachers_PART);
				TTxt2.setText("행정실");
				
				TextView TTxt3 = (TextView) findViewById(R.id.Teachers_SUBJ);
				TTxt3.setText("");
			}
			else if (posit == 2){

				ImageView TPic = (ImageView) findViewById(R.id.Teachers_Pic);
				BitmapDrawable img = (BitmapDrawable) getResources().getDrawable(R.drawable.hgrapp);
				TPic.setImageDrawable(img);

				TextView TTxt1 = (TextView) findViewById(R.id.Teachers_NAME);
				TTxt1.setText(data);
				
				TextView TTxt2 = (TextView) findViewById(R.id.Teachers_PART);
				TTxt2.setText("행정실");
				
				TextView TTxt3 = (TextView) findViewById(R.id.Teachers_SUBJ);
				TTxt3.setText("");
			}
			else if (posit == 3){

				ImageView TPic = (ImageView) findViewById(R.id.Teachers_Pic);
				BitmapDrawable img = (BitmapDrawable) getResources().getDrawable(R.drawable.hgrapp);
				TPic.setImageDrawable(img);

				TextView TTxt1 = (TextView) findViewById(R.id.Teachers_NAME);
				TTxt1.setText(data);
				
				TextView TTxt2 = (TextView) findViewById(R.id.Teachers_PART);
				TTxt2.setText("행정실");
				
				TextView TTxt3 = (TextView) findViewById(R.id.Teachers_SUBJ);
				TTxt3.setText("");
			}
			else if (posit == 4){

				ImageView TPic = (ImageView) findViewById(R.id.Teachers_Pic);
				BitmapDrawable img = (BitmapDrawable) getResources().getDrawable(R.drawable.hgrapp);
				TPic.setImageDrawable(img);

				TextView TTxt1 = (TextView) findViewById(R.id.Teachers_NAME);
				TTxt1.setText(data);
				
				TextView TTxt2 = (TextView) findViewById(R.id.Teachers_PART);
				TTxt2.setText("행정실");
				
				TextView TTxt3 = (TextView) findViewById(R.id.Teachers_SUBJ);
				TTxt3.setText("");
			}
			else if (posit == 5){

				ImageView TPic = (ImageView) findViewById(R.id.Teachers_Pic);
				BitmapDrawable img = (BitmapDrawable) getResources().getDrawable(R.drawable.hgrapp);
				TPic.setImageDrawable(img);

				TextView TTxt1 = (TextView) findViewById(R.id.Teachers_NAME);
				TTxt1.setText(data);
				
				TextView TTxt2 = (TextView) findViewById(R.id.Teachers_PART);
				TTxt2.setText("행정실");
				
				TextView TTxt3 = (TextView) findViewById(R.id.Teachers_SUBJ);
				TTxt3.setText("");
			}
			else if (posit == 6){

				ImageView TPic = (ImageView) findViewById(R.id.Teachers_Pic);
				BitmapDrawable img = (BitmapDrawable) getResources().getDrawable(R.drawable.hgrapp);
				TPic.setImageDrawable(img);

				TextView TTxt1 = (TextView) findViewById(R.id.Teachers_NAME);
				TTxt1.setText(data);
				
				TextView TTxt2 = (TextView) findViewById(R.id.Teachers_PART);
				TTxt2.setText("행정실");
				
				TextView TTxt3 = (TextView) findViewById(R.id.Teachers_SUBJ);
				TTxt3.setText("");
			}
			else if (posit == 7){

				ImageView TPic = (ImageView) findViewById(R.id.Teachers_Pic);
				BitmapDrawable img = (BitmapDrawable) getResources().getDrawable(R.drawable.hgrapp);
				TPic.setImageDrawable(img);

				TextView TTxt1 = (TextView) findViewById(R.id.Teachers_NAME);
				TTxt1.setText(data);
				
				TextView TTxt2 = (TextView) findViewById(R.id.Teachers_PART);
				TTxt2.setText("행정실");
				
				TextView TTxt3 = (TextView) findViewById(R.id.Teachers_SUBJ);
				TTxt3.setText("");
			}
			else if (posit == 8){

				ImageView TPic = (ImageView) findViewById(R.id.Teachers_Pic);
				BitmapDrawable img = (BitmapDrawable) getResources().getDrawable(R.drawable.hgrapp);
				TPic.setImageDrawable(img);

				TextView TTxt1 = (TextView) findViewById(R.id.Teachers_NAME);
				TTxt1.setText(data);
				
				TextView TTxt2 = (TextView) findViewById(R.id.Teachers_PART);
				TTxt2.setText("행정실");
				
				TextView TTxt3 = (TextView) findViewById(R.id.Teachers_SUBJ);
				TTxt3.setText("");
			}
			
		}
		else{
			finish();
		}
		
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
	        	aActivity.finish();
	            finish();
	            overridePendingTransition(R.anim.bottom_to_top_in, R.anim.bottom_to_top_out);
	            return true;
	        default:
	            return super.onOptionsItemSelected(item);
	    }
	}
	
}
