package com.ATeam.HangaramAPP;

import android.util.Log;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

// 추상클래스 android.database.sqlite.SQLiteOpenHelper 상속
public class DbAdapter {
	// 생성자

	private SQLiteDatabase mdb;
	private Context mcontext;
	private DBopenhelper mdbhelper;

	public DbAdapter(Context context) {
		this.mcontext = context;
	}

	private static class DBopenhelper extends SQLiteOpenHelper {
		DBopenhelper(Context context) {
			// 컨텍트스, DB파일, 커스텀커서(표준커서 null), 버전
			super(context, "HGRAPP.db", null, 1);
		}

		// DB가 처음 만들어 질때
		@Override
		public void onCreate(SQLiteDatabase db) {
			/*
			 * Log.i("Net", "테이블 생성!");
			 * db.execSQL("CREATE TABLE IF NOT EXISTS config " +
			 * "(_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
			 * "name TEXT, tf TEXT);");
			 */}

		@Override
		// 이미존재하는 DB를 업그레이드
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			/*
			 * Log.i("Net", "업그뤠이드!");
			 * db.execSQL("DROP TABLE IF EXISTS config"); onCreate(db);
			 */}
	}

	public DbAdapter open(String a) throws SQLException {
		mdbhelper = new DBopenhelper(mcontext);
		if (mdb != null)
			mdb.close();

		if (a.equals("READ") == true) {
			mdb = mdbhelper.getReadableDatabase();
		} else if (a.equals("WRITE") == true) {
			mdb = mdbhelper.getWritableDatabase();
		}
		return this;
	}

	public long insertd(String table, String name, String tf) {
		Log.i("Net", "테이블 " + table + " 에 " + name + " / " + tf + "를 추가하였습니다.");
		ContentValues values = new ContentValues();
		values.put("tf", tf);
		values.put("name", name);
		return mdb.insert(table, null, values);

	}

	public void create(String table) {
		Log.i("Net", table + "테이블 생성!");
		mdb.execSQL("CREATE TABLE IF NOT EXISTS " + table
				+ " (_id INTEGER PRIMARY KEY AUTOINCREMENT, "
				+ "name TEXT, tf TEXT);");

		Cursor cursor = mdb.query(table, null, null, null, null, null, null);
		
		// 초기화
		if (table.equals("config") == true) {
			insertd("config", "ISNOTICEUPDATE", "true");
			insertd("config", "ISFIRSTHELP", "true");
			insertd("config", "themeSet", "default");
		}
		if (table.equals("timeTableLock") == true) {
			if (cursor.getCount() != 1){
			insertd("timeTableLock", "isTimeTableLocked", "false");
			}
		}
		if (table.equals("note") == true) {
			if (cursor.getCount() != 1){
			insertd("note", "noteText", "메모를 입력하세요.");
			}
		}
		
	}

	public boolean deleted(String table, long rowId) {
		return mdb.delete(table, "_id=" + rowId, null) > 0;

	}

	public void deletetable(String table) {
		mdb.execSQL("drop table " + table);
		// return mdb.query("setting", null, null, null, null,
		// null, null);
	}

	public Cursor fetchd(String table) {
		return mdb.query(table, null, null, null, null, null, null);
	}

	public Cursor select(String table) {
		return mdb.rawQuery(table
				+ " name from sqlite_master WHERE type='table'", null);
	}

	public boolean update(String table, long rowId, String name, String tf) {
		Log.i("Net", "테이블 " + table + " 의 " + rowId + "번째 줄 정보인 " + name
				+ "값을 " + tf + "로 바꾸었습니다.");
		ContentValues args = new ContentValues();
		args.put("tf", tf);
		args.put("name", name);

		return mdb.update(table, args, "_id=" + rowId, null) > 0;
	}

	public String getid(String table, String name) {
		// create(table);
		Cursor cursor = mdb.query(table, null, null, null, null, null, null);
		cursor.moveToFirst();

		if (cursor.getCount() == 0) {
			return "nodata";
		}
		do {
			if (cursor.getString(1).equals(name) == true) {
				return cursor.getString(2);
			}
		} while (cursor.moveToNext());

		return "nodata";
	}

	public boolean isTableExists(String tableName) {
		Cursor cursor = mdb.rawQuery(
				"select DISTINCT tbl_name from sqlite_master where tbl_name = '"
						+ tableName + "'", null);
		if (cursor != null) {
			if (cursor.getCount() > 0) {
				return true;
			}
		}
		return false;
	}

	public boolean checktableinit(String table) {
		open("READ");
		Cursor csr_config = mdb.rawQuery("SELECT count(*) FROM " + table, null);
		csr_config.moveToFirst();
		int csr_cnt = csr_config.getInt(0);
		close();

		if (csr_cnt > 0)
			return false;
		return true;

		/*
		 * Log.i("Net", table+"의 요소 갯수 : "+csr_config.getCount()); if
		 * (csr_config.getCount() == 0) return true;
		 * 
		 * return false;
		 */
	}

	// SQLiteDatabase 자원정리
	public void close() {
		mdb.close();
	}

}