package com.ATeam.HangaramAPP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Calendar;

import org.holoeverywhere.widget.Toast;

import com.ATeam.HangaramAPP.Today_meal.LoadingParsing;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

public class Fixmode  {
	ProgressDialog mProgressDialog;
	Toast mToast;
	Context mContext;
	boolean rebabupdate = false;
	
	public Fixmode(Context con) {
		this.mContext = con;
	}

	public int fix() {
		BufferedReader in = null;
		URL gcal = null;
		try {
			gcal = new URL("http://bluepeal.raonnet.com/command.txt");
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		boolean symflag, tfflag = false;
		String ptext = null;
		symflag = false;
		DbAdapter myHelper = new DbAdapter(mContext);
		String table, name, value;
		
		try {
			in = new BufferedReader(new InputStreamReader(gcal.openStream(),
					"UTF-8"));
			while (true) {

				ptext = in.readLine();
				
				if (ptext == null || ptext.equals("end"))
					break;
				if (isFind("<==>", ptext))
					continue;
				if(ptext.equals(""))
					continue;
				System.out.println(ptext);
				if (isFind("re-babupdate", ptext)){
					// after, not completed
					if (tfflag == true)
					rebabupdate = true;
					
					continue;
				}
				if (isFind("{", ptext)) {
					symflag = true;
					continue;
				}
				if (isFind("}", ptext)) {
					tfflag = false;
					symflag = false;
					continue;
				}
				if (tfflag == false && symflag == true) {
					System.out.println("** No Effect");
					continue;
				}
				
				String[] values = ptext.split(" ");
				
				table = values[1];
				// table
				if (isFind("remove_table", ptext)) {
					table=values[1];
					myHelper.open("WRITE");
					myHelper.deletetable(table);
					myHelper.close();
					continue;
				}
				
				Log.d("SJ","table은 "+table);

				name = values[2];
				// table + name
				if (isFind("remove", ptext)) {
					System.out.println("** table : " + table + "\tname : "
							+ name);
					myHelper.open("WRITE");
					Cursor cursor = myHelper.fetchd(table);
					cursor.moveToFirst();
					int i = Integer.parseInt(cursor.getString(0));
					do {
						if (cursor.getString(1).equals(name) == true) {
							myHelper.deleted(table, i);
//							break;
						}

						i++;
					} while (cursor.moveToNext());
					myHelper.close();
					continue;
				}
				// table + name + <value>
				value = ptext.substring(ptext.indexOf('<') + 1,
						ptext.indexOf('>'));
				if (isFind("edit", ptext) || isFind("add", ptext) || isFind("if",ptext) || isFind("!if",ptext)){
					System.out.println("** table : " + table + "\tname : "
							+ name + "\tvalue : " + value);

					myHelper.open("WRITE");
					// edit
					if (isFind("edit", ptext)) {
						Cursor cursor = myHelper.fetchd(table);
						try{
						cursor.moveToFirst();
						int i = 1;
						do {
							i = Integer.valueOf(cursor.getString(0));
							if (cursor.getString(1).equals(name) == true) {
								myHelper.update(table, i, name, value);
								Log.i("SJ", "myHelper.update("+table+", "+i+", "+name+", "+value+");가 실행됨");
							}

						} while (cursor.moveToNext());
						}catch(Exception e){
							Log.d("SJ", "try{cursor.moveToFirst(); 에서 오류남 ㅠㅠㅠ");
						}
					}
					// add
					else if(isFind("add",ptext)) myHelper.insertd(table, name, value);
					myHelper.close();
					// if
					if (isFind("if", ptext)) {
						myHelper.open("READ");
						if (myHelper.getid(table, name).equals(value))
							tfflag = true;
						else
							tfflag = false;
						myHelper.close();
						Log.i("Net", "TFFLAG = " + tfflag);
					}
					if (isFind("!if", ptext)) {
						myHelper.open("READ");
						if (myHelper.getid(table, name).equals(value))
							tfflag = false;
						else
							tfflag = true;
						myHelper.close();
						Log.i("Net", "TFFLAG = " + tfflag);
					}
					continue;
				}
			}

		} catch (IOException ioe) {
			ioe.printStackTrace();
		}

		if (rebabupdate == true){
			Log.i("Net", "rebabupdate가 true 래요.");
			return 1;
			}
		return 0;
		
		
	}

	public static int getYI(int Y, int M, int D) {

		int a, b, c, d;
		if (M < 3) {
			a = ((Y - 1) / 100);
			b = (Y - 1) % (a * 100);
			c = 12 + M;
			d = D;
		} else {
			a = ((Y) / 100);
			b = (Y) % (a * 100);
			c = M;
			d = D;
		}
		int e = (((21 * a) / 4) + (5 * b / 4) + (26 * (c + 1) / 10) + d - 1) % 7;
		return e;

	}

	public static boolean isFind(String findc, String a) {
		String[] values;
		values = a.split(" ");
		if (values[0].equals(findc))
			return true;
		return false;
	}


	

	// TODO Auto-generated method stub
}