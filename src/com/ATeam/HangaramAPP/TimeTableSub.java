package com.ATeam.HangaramAPP;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

public class TimeTableSub {
	TextView dow, Period1, Period2, Period3, Period4, Period5, Period6;
	DbAdapter_TimeTable myTTHelper;
	Context mContext;

	public TimeTableSub(Context con) {
		this.mContext = con;
	}

	public void createDBTable() {

		myTTHelper = new DbAdapter_TimeTable(mContext);

		myTTHelper.open("WRITE");
		myTTHelper.create("TimeTable");
		myTTHelper.close();

	}

	public void getDayWeek(View v, int day) {

		myTTHelper = new DbAdapter_TimeTable(mContext);

		Period1 = (TextView) v.findViewById(R.id.Period1);
		Period2 = (TextView) v.findViewById(R.id.Period2);
		Period3 = (TextView) v.findViewById(R.id.Period3);
		Period4 = (TextView) v.findViewById(R.id.Period4);
		Period5 = (TextView) v.findViewById(R.id.Period5);
		Period6 = (TextView) v.findViewById(R.id.Period6);
		dow = (TextView) v.findViewById(R.id.DayOfWeek);

		/*
		 * DbAdapter myHelper = null; myHelper = new DbAdapter(this);
		 * myHelper.open("READ"); if(!myHelper.isTableExists("TimeTable")){
		 * myHelper.close(); myHelper.open("WRITE");
		 * myHelper.create("TimeTable"); myHelper.close(); }
		 * 
		 * myHelper.open("READ"); myHelper.close();]
		 */

		if (day == 1) {
			TextView dow = (TextView) v.findViewById(R.id.DayOfWeek);
			dow.setText("일요일");

			myTTHelper.open("READ");
			String p1 = myTTHelper.getid("TimeTable", "Sunday", 1);
			String p2 = myTTHelper.getid("TimeTable", "Sunday", 2);
			String p3 = myTTHelper.getid("TimeTable", "Sunday", 3);
			String p4 = myTTHelper.getid("TimeTable", "Sunday", 4);
			String p5 = myTTHelper.getid("TimeTable", "Sunday", 5);
			String p6 = myTTHelper.getid("TimeTable", "Sunday", 6);
			myTTHelper.close();

			Period1.setText(p1);
			Period2.setText(p2);
			Period3.setText(p3);
			Period4.setText(p4);
			Period5.setText(p5);
			Period6.setText(p6);
		} else if (day == 2) {
			dow.setText("월요일");

			myTTHelper.open("READ");
			String p1 = myTTHelper.getid("TimeTable", "Monday", 1);
			String p2 = myTTHelper.getid("TimeTable", "Monday", 2);
			String p3 = myTTHelper.getid("TimeTable", "Monday", 3);
			String p4 = myTTHelper.getid("TimeTable", "Monday", 4);
			String p5 = myTTHelper.getid("TimeTable", "Monday", 5);
			String p6 = myTTHelper.getid("TimeTable", "Monday", 6);
			myTTHelper.close();

			Period1.setText(p1);
			Period2.setText(p2);
			Period3.setText(p3);
			Period4.setText(p4);
			Period5.setText(p5);
			Period6.setText(p6);

		} else if (day == 3) {
			dow.setText("화요일");

			myTTHelper.open("READ");
			String p1 = myTTHelper.getid("TimeTable", "Tuesday", 1);
			String p2 = myTTHelper.getid("TimeTable", "Tuesday", 2);
			String p3 = myTTHelper.getid("TimeTable", "Tuesday", 3);
			String p4 = myTTHelper.getid("TimeTable", "Tuesday", 4);
			String p5 = myTTHelper.getid("TimeTable", "Tuesday", 5);
			String p6 = myTTHelper.getid("TimeTable", "Tuesday", 6);
			myTTHelper.close();

			Period1.setText(p1);
			Period2.setText(p2);
			Period3.setText(p3);
			Period4.setText(p4);
			Period5.setText(p5);
			Period6.setText(p6);

		} else if (day == 4) {
			dow.setText("수요일");

			myTTHelper.open("READ");
			String p1 = myTTHelper.getid("TimeTable", "Wednesday", 1);
			String p2 = myTTHelper.getid("TimeTable", "Wednesday", 2);
			String p3 = myTTHelper.getid("TimeTable", "Wednesday", 3);
			String p4 = myTTHelper.getid("TimeTable", "Wednesday", 4);
			String p5 = myTTHelper.getid("TimeTable", "Wednesday", 5);
			String p6 = myTTHelper.getid("TimeTable", "Wednesday", 6);
			myTTHelper.close();

			Period1.setText(p1);
			Period2.setText(p2);
			Period3.setText(p3);
			Period4.setText(p4);
			Period5.setText(p5);
			Period6.setText(p6);

		} else if (day == 5) {
			dow.setText("목요일");

			myTTHelper.open("READ");
			String p1 = myTTHelper.getid("TimeTable", "Thursday", 1);
			String p2 = myTTHelper.getid("TimeTable", "Thursday", 2);
			String p3 = myTTHelper.getid("TimeTable", "Thursday", 3);
			String p4 = myTTHelper.getid("TimeTable", "Thursday", 4);
			String p5 = myTTHelper.getid("TimeTable", "Thursday", 5);
			String p6 = myTTHelper.getid("TimeTable", "Thursday", 6);
			myTTHelper.close();

			Period1.setText(p1);
			Period2.setText(p2);
			Period3.setText(p3);
			Period4.setText(p4);
			Period5.setText(p5);
			Period6.setText(p6);

		} else if (day == 6) {
			TextView tv1 = (TextView) v.findViewById(R.id.DayOfWeek);
			tv1.setText("금요일");

			myTTHelper.open("READ");
			String p1 = myTTHelper.getid("TimeTable", "Friday", 1);
			String p2 = myTTHelper.getid("TimeTable", "Friday", 2);
			String p3 = myTTHelper.getid("TimeTable", "Friday", 3);
			String p4 = myTTHelper.getid("TimeTable", "Friday", 4);
			String p5 = myTTHelper.getid("TimeTable", "Friday", 5);
			String p6 = myTTHelper.getid("TimeTable", "Friday", 6);
			myTTHelper.close();

			Period1.setText(p1);
			Period2.setText(p2);
			Period3.setText(p3);
			Period4.setText(p4);
			Period5.setText(p5);
			Period6.setText(p6);

		} else if (day == 7) {
			TextView tv1 = (TextView) v.findViewById(R.id.DayOfWeek);
			tv1.setText("토요일");

			myTTHelper.open("READ");
			String p1 = myTTHelper.getid("TimeTable", "Saturday", 1);
			String p2 = myTTHelper.getid("TimeTable", "Saturday", 2);
			String p3 = myTTHelper.getid("TimeTable", "Saturday", 3);
			String p4 = myTTHelper.getid("TimeTable", "Saturday", 4);
			String p5 = myTTHelper.getid("TimeTable", "Saturday", 5);
			String p6 = myTTHelper.getid("TimeTable", "Saturday", 6);
			myTTHelper.close();

			Period1.setText(p1);
			Period2.setText(p2);
			Period3.setText(p3);
			Period4.setText(p4);
			Period5.setText(p5);
			Period6.setText(p6);

		}

	}

}
