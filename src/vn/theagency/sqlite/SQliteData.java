package vn.theagency.sqlite;

import java.util.ArrayList;

import vn.theagency.objects.Audios;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SQliteData {
	Context oucontext;
	private SQdata ouSQdata;
	SQLiteDatabase ouDatabase;
	public static final String AUDIOS_COLLECTIONS = "tblcollections";
	public static final String AUDIOS = "tblaudio";
	

	public static class SQdata extends SQLiteOpenHelper {
		private static final int DATABASE_VERSION = 1;
		private static final String DATABASE_NAME = "pregnant";
	

		public SQdata(Context context) {
			super(context, DATABASE_NAME, null, DATABASE_VERSION);
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			// TODO Auto-generated method stub
			
			
			
			String order = "CREATE TABLE " + AUDIOS_COLLECTIONS + " ("
					+ Audios.ID
					+ " text,"
					+ Audios.TITLE
					+ " text," 
					+ Audios.DECRIPTION
					+ " text," 
					+ Audios.PRICE 
					+ " text,"
					+ Audios.IMAGEURL 
					+ " text );";
			db.execSQL(order);
			String order1 = "CREATE TABLE " + AUDIOS + " ("
					+ Audios.ID
					+ " text,"
					+ Audios.TITLE
					+ " text," 
					+ Audios.DECRIPTION
					+ " text," 
					+ Audios.PRICE 
					+ " text,"
					+ Audios.IMAGEURL 
					+ " text );";
			db.execSQL(order1);
			
			
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			// TODO Auto-generated method stub
			db.execSQL("DROP TABLE IF EXISTS " + AUDIOS_COLLECTIONS);
			db.execSQL("DROP TABLE IF EXISTS " + AUDIOS);
			
			onCreate(db);

		}
	}

	public SQliteData(Context c) {
		oucontext = c;
	}

	public SQliteData open() {
		ouSQdata = new SQdata(oucontext);
		ouDatabase = ouSQdata.getWritableDatabase();
		return this;
	}

	public void close() {
		ouSQdata.close();
	}
	
public void putAudiosCollections(Audios detail){		
		ContentValues cv = new ContentValues();
		cv.put(Audios.ID, detail.getmID());
		cv.put(Audios.TITLE, detail.getmTitle());
		cv.put(Audios.DECRIPTION, detail.getmDecription());
		cv.put(Audios.PRICE, detail.getmPrice());
		cv.put(Audios.IMAGEURL, String.valueOf(detail.getmImageURL()));
		ouDatabase.insert(AUDIOS_COLLECTIONS, null, cv);	
	}
public ArrayList<Audios> getAllAudiosCollections(){
	ArrayList<Audios> audios = new ArrayList<Audios>();
	Cursor c = ouDatabase.rawQuery("SELECT * FROM " + AUDIOS_COLLECTIONS, null);
	
	int cusID = c.getColumnIndex(Audios.ID);
	int cusTitle = c.getColumnIndex(Audios.TITLE);
	int cusDecription = c.getColumnIndex(Audios.DECRIPTION);
	int cusPrice = c.getColumnIndex(Audios.PRICE);
	int cusImageUrl = c.getColumnIndex(Audios.IMAGEURL);
	for (c.moveToFirst(); !c.isAfterLast(); c.moveToNext()) {
		Audios detail = new Audios(c.getString(cusID),c.getString(cusTitle), c.getString(cusDecription),
				c.getString(cusPrice),Integer.parseInt(c.getString(cusImageUrl)));
		audios.add(detail);
	}

	return audios;
}
public void removeAudiosCollections(String ID) {
	ouDatabase.delete(AUDIOS_COLLECTIONS, Audios.ID+"='"+ID+"'" , null);
}
public void removeAllAudiosCollections() {
	ouDatabase.delete(AUDIOS_COLLECTIONS, null, null);
}

//
public void putAudios(Audios detail){		
	ContentValues cv = new ContentValues();
	cv.put(Audios.ID, detail.getmID());
	cv.put(Audios.TITLE, detail.getmTitle());
	cv.put(Audios.DECRIPTION, detail.getmDecription());
	cv.put(Audios.PRICE, detail.getmPrice());
	cv.put(Audios.IMAGEURL, String.valueOf(detail.getmImageURL()));
	ouDatabase.insert(AUDIOS, null, cv);	
}
public ArrayList<Audios> getAllAudios(){
ArrayList<Audios> audios = new ArrayList<Audios>();
Cursor c = ouDatabase.rawQuery("SELECT * FROM " + AUDIOS, null);
int cusID = c.getColumnIndex(Audios.ID);
int cusTitle = c.getColumnIndex(Audios.TITLE);
int cusDecription = c.getColumnIndex(Audios.DECRIPTION);
int cusPrice = c.getColumnIndex(Audios.PRICE);
int cusImageUrl = c.getColumnIndex(Audios.IMAGEURL);
for (c.moveToFirst(); !c.isAfterLast(); c.moveToNext()) {
	Audios detail = new Audios(c.getString(cusID),c.getString(cusTitle), c.getString(cusDecription),
			c.getString(cusPrice),Integer.parseInt(c.getString(cusImageUrl)));
	audios.add(detail);
}

return audios;
}
public void removeAudios(String ID) {
ouDatabase.delete(AUDIOS, Audios.ID+"='"+ID+"'" , null);
}


	
}

