package com.ATeam.HangaramAPP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

import org.holoeverywhere.widget.Toast;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;

public class BabParse {

	Toast mToast;

	int max;

	private Context mContext;

	public BabParse(Context mContext) {
		this.mContext = mContext;
		// TODO Auto-generated constructor stub
	}

	public void parse(int Y, int M, boolean flag) {
		max = -1;
		try {
			if (M == 1) {
				parse_work(Y - 1, 12, true);
				parse_work(Y, M, true);
				parse_work(Y, 2, true);
			} else if (M == 12) {
				parse_work(Y, 11, true);
				parse_work(Y, M, true);
				parse_work(Y + 1, 1, true);
			} else {
				parse_work(Y, M - 1, true);
				parse_work(Y, M, true);
				parse_work(Y, M + 1, true);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		DbAdapter myHelper = new DbAdapter(mContext);
		myHelper.open("WRITE");
		Log.i("Net", "쓰기 준비중");
		Cursor cursor = myHelper.fetchd("bab");
		Log.i("Net", "bab 정보 들고옴");
		cursor.moveToFirst();
		Log.i("Net", "커서초기화");
		int i = 1;
		boolean flag2 = false;
		if (cursor.getCount() != 0) {
			
			if (max == -1) {
				do{
					i = Integer.valueOf(cursor.getString(0));
					if(cursor.getString(1).equals("isbabcheck")){
						myHelper.update("bab", i, "isbabcheck", "NetError");
						break;
					}
				}while(cursor.moveToNext());
				myHelper.close();
				return;
			}
			do {
				i = Integer.valueOf(cursor.getString(0));
				if (cursor.getString(1).equals("recdate") == true) {
					Log.i("Net", i + "번째 정보 클리어, recdate");

					myHelper.update("bab", i, "recdate", Integer.toString(max));
				} else if (cursor.getString(1).equals("isbabcheck") == true) {
					Log.i("Net", i + "번째 정보 클리어, isbabcheck");

					myHelper.update("bab", i, "isbabcheck", "true");
					flag2 = true;
				}

			} while (cursor.moveToNext());
		}
		if (flag2 == false) {
			// 처. 처음이라능
			myHelper.insertd("bab", "recdate", Integer.toString(max));
			myHelper.insertd("bab", "isbabcheck", "true");
		}
		myHelper.close();
	}

	public void parse_work(int Y, int M, boolean flag) throws IOException {
		Log.i("Net", "parse_work, " + Y + M);
		int D;
		int recdate;
		DbAdapter myHelper = new DbAdapter(mContext);

		BufferedReader in = null;
		String userdate;
		String tempdate = "-1";
		if (M <= 9) {
			userdate = Y + "." + "0" + M;
		} else
			userdate = Y + "." + M;

		URL gcal = new URL(
				"http://hes.sen.go.kr/sts_sci_md00_001.do?schYm="
						+ userdate
						+ "&schulCode=B100000549&insttNm=%ED%95%9C%EA%B0%80%EB%9E%8C%EA%B3%A0%EB%93%B1%ED%95%99%EA%B5%90&schulCrseScCode=4&schulKndScCode=04");

		String ptext = null;

		myHelper.open("READ");
		if (myHelper.getid("bab", "isbabcheck").equals("nodata") == true) {
			Log.i("Net", "처음 시작!, DB 테이블을 생성합니다.");
			recdate = 0;
		} else {
			// recdate == 이전의 최신 업데이트 날짜
			if (myHelper.getid("bab", "recdate") != null) {
				if ((myHelper.getid("bab", "recdate").equals("null") == true)) {
					recdate = 0;
				} else {
					recdate = Integer.valueOf(myHelper.getid("bab", "recdate"));
					Log.i("Net", "bab 테이블 최근 갱신 날짜 : (int) " + recdate);
				}
			} else {
				recdate = 0;
			}
		}
		myHelper.close();

		myHelper.open("WRITE");
		
		try {
			in = new BufferedReader(new InputStreamReader(gcal.openStream(),
					"UTF-8"));
			while (true) {

				ptext = in.readLine();
				if (ptext == null)
					break;

				String date, lunch, dinner;
				Pattern p = Pattern.compile("<td>([0-9]{1,2}) *");
				Pattern p2 = Pattern.compile("중식");

				try {
					ptext = ptext.replaceAll("<br />", " ");
					ptext = ptext.replaceAll("</td>", "");

					Matcher m = p.matcher(ptext);
					Matcher m2 = p2.matcher(ptext);
					if (m.find() && m2.find()) {
						date = m.group();
						date = date.replaceAll("<td>", "");
						date = date.replaceAll(" ", "");
						ptext = ptext.replaceAll("<td>", "");
						lunch = dinner = "NP";
						D = Integer.valueOf(date);
						Log.i("Net", Y + "" + M + "" + D + "ptext :" + ptext);
						// plunch, pdinner 추출
						if (ptext.contains("[중식]") && ptext.contains("[석식]")) {

							lunch = ptext.substring(ptext.indexOf(']') + 1,
									ptext.lastIndexOf('['));
							dinner = ptext.substring(
									ptext.lastIndexOf(']') + 1, ptext.length());
						} else if (ptext.contains("[중식]")) {
							lunch = ptext.substring(ptext.indexOf(']') + 1,
									ptext.length());
						} else if (ptext.contains("[석식]")) {
							dinner = ptext.substring(
									ptext.lastIndexOf(']') + 1, ptext.length());
						}
						tempdate = Integer.toString(Y * 10000 + (M) * 100 + D);

						System.out.println(date + "day | " + lunch + "|"
								+ dinner);
						// DB 삽입
						if (Integer.valueOf(tempdate) > recdate) {
							Log.i("Net","|||| tempdate = "+tempdate+">> recdate = "+recdate);
							myHelper.insertd("bab", tempdate + ".lunch", lunch);
							myHelper.insertd("bab", tempdate + ".dinner",
									dinner);
							Log.i("Net", "##" + tempdate + " DB에 급식 정보를 저장중 : "
									+ lunch + dinner + "::::::::::::"
									+ tempdate + "/" + recdate);

						}

					}
				} catch (PatternSyntaxException e) {
				}

			}

		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
		myHelper.close();

		if (max < Integer.valueOf(tempdate)) {
			max = Integer.valueOf(tempdate);
		}

	}
}