package com.ATeam.HangaramAPP;


import org.holoeverywhere.widget.datetimepicker.date.DatePickerDialog;

import android.os.Bundle;
import android.util.Log;

public class DatePickerFragment extends DatePickerDialog implements DatePickerDialog.OnDateSetListener{
	 OnDateSetListener ondateSet;
	  	int year, month, day;
		int mYear, mMonth, mDay;
		String value_dates, value_events;
		
	public DatePickerFragment() {
		// TODO Auto-generated constructor stub
	}
	 
	 @Override
	 public void onCreate(Bundle savedInstanceState) {
		 super.onCreate(savedInstanceState);
		 Bundle bundle = getArguments(); 
		 year = bundle.getInt("year");
		  month = bundle.getInt("month");
		  day = bundle.getInt("day");
		  value_events = bundle.getString("value_events");
		  Log.i("DatePickerFragment", "year = "+year+ ",month = "+month+ ",day = "+day+ ",value_events = "+value_events );
	        initialize(this, year, month, day);
	 }
	 
	@Override
	public void onDateSet(DatePickerDialog dialog, int year, int monthOfYear,
			int dayOfMonth) {
		// TODO Auto-generated method stub
		mYear = year; 
    	mMonth = monthOfYear; 
    	mDay = dayOfMonth;
    	mMonth++;
    	
    	Log.d("HangaramAPP", "monthOfYear´Â " + monthOfYear);
    			  
    	int value_YEAR = mYear * 10000; 
    	int value_MONTH = mMonth * 100; 
    	int value_DAY = mDay; 
    	int value_DATE = value_YEAR + value_MONTH + value_DAY;
    			  
    	value_dates = String.valueOf(value_DATE);
    	
    	DbAdapter myHelper = new DbAdapter(getSupportActivity());
		myHelper.open("WRITE");
		myHelper.insertd("sche_private", value_events, value_dates);
		myHelper.close();
	}
}
