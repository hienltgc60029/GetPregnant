package vn.theagency.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SQLiteData {
	Context oucontext;
	private SQdata ouSQdata;
	SQLiteDatabase ouDatabase;
	public static final String CUSTOMMER = "tblCustommer";
	public static final String DATA_DISH = "tblDish";
	public static final String CART = "tblCart";
	public static final String ORDER = "tblOrder";
	public static final String ORDER_DETAIL = "tblOrderDetail";

	public static class SQdata extends SQLiteOpenHelper {
		private static final int DATABASE_VERSION = 1;
		private static final String DATABASE_NAME = "RedSunDataBase";
		

		

		

		public SQdata(Context context) {
			super(context, DATABASE_NAME, null, DATABASE_VERSION);
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			// TODO Auto-generated method stub
			
		/*	String order = "CREATE TABLE " + ORDER + " ("
					+ Order.cusOrderID 
					+ " text," 
					+ Order.cusID
					+ " text," 
					+ Order.cusPhone 
					+ " text,"
					+ Order.cusDate 
					+ " text," 
					+ Order.cusShipAddress
					+ " text,"
					+ Order.cusShipName
					+ " text );";
			db.execSQL(order);
			
			String orderDetail = "CREATE TABLE " + ORDER_DETAIL + " ("
					+ OrderDetail.cusOrderID 
					+ " text," 
					+ OrderDetail.cusDishID
					+ " text," 
					+ OrderDetail.cusQuantity 
					+ " text,"
					+ OrderDetail.cusUnitPrice 
					+ " text,"
					+ OrderDetail.cusShowDishImageURL 
					+ " text,"
					+ OrderDetail.cusShowDishName 
					+ " text,"
					+ OrderDetail.cusDiscount
					+ " text );";
			db.execSQL(orderDetail);
			
			String data = "CREATE TABLE " + CUSTOMMER + " ("
					+ Custommer.O_ID 
					+ " text," 
					+ Custommer.O_USERNAME 
					+ " text," 
					+ Custommer.O_PASSWORD
					+ " text," 
					+ Custommer.O_PHONE 
					+ " text,"
					+ Custommer.O_IMAGE 
					+ " text," 
					+ Custommer.O_EMAIL
					+ " text,"
					+ Custommer.O_FULLNAME
					+ " text,"
					+ Custommer.O_CONTENT
					+ " text );";
			db.execSQL(data);

			

			String customer = "CREATE TABLE " + DATA_DISH + " ("
					+ Dish.DISH_ID 
					+ " text," 
					+ Dish.DISH_NAME
					+ "  text," 
					+ Dish.DISH_PRICE 
					+ " text,"
					+ Dish.DISH_DETAIL 
					+ " text,"
					+ Dish.DISH_BIGIMAGE 
					+ " text,"
					+ Dish.DISH_IMAGE
					+ " text );";
			db.execSQL(customer);

			String data1 = "CREATE TABLE " + CART + " (" + ObjectCart.DISH_NAME
					+ " text," + ObjectCart.DISH_PRICE + " text,"
					+ ObjectCart.DISH_COUNT + " text );";
			db.execSQL(data1);*/
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			// TODO Auto-generated method stub
			db.execSQL("DROP TABLE IF EXISTS " + DATA_DISH);
			db.execSQL("DROP TABLE IF EXISTS " + CART);
			db.execSQL("DROP TABLE IF EXISTS " + CUSTOMMER);
			db.execSQL("DROP TABLE IF EXISTS " + ORDER);
			db.execSQL("DROP TABLE IF EXISTS " + ORDER_DETAIL);
			onCreate(db);

		}
	}

	public SQLiteData(Context c) {
		oucontext = c;
	}

	public SQLiteData open() {
		ouSQdata = new SQdata(oucontext);
		ouDatabase = ouSQdata.getWritableDatabase();
		return this;
	}

	public void close() {
		ouSQdata.close();
	}
}

