package com.ATeam.HangaramAPP;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;

public final class hgrlib extends Activity {

	/** Called when the activity is first created. */

	public boolean NetStatCHK() {
		int IsAPhone = 0;
		
		ConnectivityManager manager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo mobile = manager
				.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
		NetworkInfo wifi = manager
				.getNetworkInfo(ConnectivityManager.TYPE_WIFI);

		try {

			if (mobile != null) {
				IsAPhone = 1;
				if (mobile.isConnected() || wifi.isConnected()) {
					return true;
				}
				return false;
			}
		} catch (Exception e) {
		}
		return false;
	}

}
