package com.ATeam.HangaramAPP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

import android.content.Context;
import android.util.Log;

public class ScheParse {

	private Context mContext;
	sched[] plans;
	int cnt;

	public ScheParse(Context mContext) {
		this.mContext = mContext;
		// TODO Auto-generated constructor stub
	}

	public void parse_part(int Y, String sem) {

		BufferedReader in = null;
		String date = null, event;
		Pattern p, p2, p3;
		int i, j;

		String ptext = null;
		Matcher m, m2, m3;
		boolean flag = false;
		// int cnt=0;

		try {
			URL gcal = new URL(
					"http://hes.sen.go.kr/sts_sci_sf00_001.do?ay="
							+ Y
							+ "&sem="
							+ sem
							+ "&x=17&y=12&schulCode=B100000549&insttNm=%ED%95%9C%EA%B0%80%EB%9E%8C%EA%B3%A0%EB%93%B1%ED%95%99%EA%B5%90&schulCrseScCode=4&schulKndScCode=04");

			in = new BufferedReader(new InputStreamReader(gcal.openStream(),
					"UTF-8"));
			while (true) {

				ptext = in.readLine();
				if (ptext == null)
					break;
				p = Pattern.compile(";eventDate=[0-9]{8}");
				p2 = Pattern.compile("[0-9]{8}");
				p3 = Pattern.compile("</span></a><br />");

				try {

					m = p.matcher(ptext);
					m2 = p2.matcher(ptext);
					m3 = p3.matcher(ptext);
					if (m.find() && m2.find()) {
						date = m2.group(0);

					} else if (m3.find()) {
						event = ptext;
						event = event.replaceAll("</span></a><br />", "");
						event = event.replaceAll(">", "");
						event = event.replaceAll("\t", "");
						flag = true;
						for (i = 0; i < cnt; i++) {
							if (plans[i].dates.equals(date)) {
								plans[i] = new sched(plans[i].events + ", "
										+ event, plans[i].dates);
								flag = false;
							}
						}
						if (flag == true) {
							plans[cnt] = new sched(event, date);
							cnt++;
							Log.i("Net", "SCHE : " + date + event);
						}

					}
				} catch (PatternSyntaxException e) {
				}
			}

		} catch (IOException ioe) {
			ioe.printStackTrace();
		}

	}

	public void parse(int Y, int M, boolean flag) throws IOException {
		plans = new sched[300];
		int i;
		DbAdapter myHelper = new DbAdapter(mContext);
		if (M == 1 || M == 2 || M == 3) {
			parse_part(Y - 1, "2");
			parse_part(Y, "1");
		} else {
			parse_part(Y, "1");
			parse_part(Y, "2");
		}
		myHelper.open("WRITE");
		for (i = 0; i < cnt; i++) {
			Log.i("Net", "SORT : " + plans[i].events + " // " + plans[i].dates);
			myHelper.insertd("sche", plans[i].events, plans[i].dates);
		}
		myHelper.close();
	}

	public class sched implements Comparable<sched> {
		private String dates;
		private String events;

		public sched(String events, String dates) {
			this.dates = dates;
			this.events = events;
		}

		public String toString() {
			return events + "(dates:" + dates + ")";
		}

		public int compareTo(sched _sch) {
			Log.i("Net", "COMPARED TO " + this.dates + " // " + _sch.dates);
			if (Integer.valueOf(this.dates) < Integer.valueOf(_sch.dates)) {
				return 1;
			} else if (this.dates.equals(_sch.dates)) {
				return 0;
			} else {
				return -1;
			}
		}

	}

}