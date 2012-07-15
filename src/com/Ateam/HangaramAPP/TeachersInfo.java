package com.Ateam.HangaramAPP;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;

public class TeachersInfo extends Activity{
	
	
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.teachers_info);
		
		 /*
		        ListView listview = new ListView(this);
		        ArrayAdapter<String> aa = new ArrayAdapter<String> (this,
		                                    android.R.layout.simple_list_item_1,
		                                    items);
		         
		        listview.setAdapter(aa);
		        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
		            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		                if(position == 0) {
		                	startActivity(new Intent(Intent.ACTION_VIEW,
		        					Uri.parse("http://www.facebook.com/groups/hangaram15th/")));
		                    
		                }
		                else if(position == 1){
		                	startActivity(new Intent(Intent.ACTION_VIEW,
		        					Uri.parse("http://www.facebook.com/groups/222927487789059/")));
		                    
		                }
		            }          
		        });
		         
		        linearLayoutView.setOrientation(LinearLayout.VERTICAL);
		        linearLayoutView.addView(listview);
		        return linearLayoutView;
		    }
		*/
		
		
	}

}
