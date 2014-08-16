package vn.theagency.helper;

import java.util.HashMap;

import android.content.Context;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.WindowManager;

public class Helper {
	
	private Context context;
	private static Helper _ins;
	
	public float density;
	public float sDensity;
	public float appWidth;
	public float appHeight;
	public float dpWidth;
	public float dpHeight;
	
	public Helper (Context _context){
		this.context = _context;
		getScreenActivity();
	}
	
	
	public static Helper shareIns(Context _context){
		if(Helper._ins==null){
			Helper._ins = new Helper(_context);
		}
		return Helper._ins;
	}
	
	/**
	  * 
	  * @param dp
	  * @return
	  */
	 public float DpToPixel(int dp) {
	  return (dp * this.density);
	 }
	 public float PixelToDp(int pixel){
		 return (pixel / this.density);
		 
	 }
	 public float DpToSp(int dp){
		 float pix = dp*this.density;
		 return (pix / this.sDensity);
	 }
	 public float PxToSp(int px){
		 
		 return (px / this.sDensity);
	 }
	 
	 
	 public void getScreenActivity() {
			Log.i("LTH", "set");
			Display display = ((WindowManager) this.context.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
			DisplayMetrics outMetrics = new DisplayMetrics();
			display.getMetrics(outMetrics);

			this.density = this.context.getResources().getDisplayMetrics().density;
			this.sDensity = this.context.getResources().getDisplayMetrics().scaledDensity;
			float _density = this.density;
			this.appWidth = outMetrics.widthPixels;
			this.appHeight = outMetrics.heightPixels;
			this.dpWidth = (int) (outMetrics.widthPixels / _density);
			this.dpHeight = (int) (outMetrics.heightPixels / _density);
			Log.i("LTH", String.valueOf(this.dpWidth));
			Log.i("LTH", String.valueOf(this.dpHeight));
		}


	public float getAppWidth() {
		return appWidth;
	}
	public float getAppHeight() {
		return appHeight;
	}
	public float getDpWidth() {
		return dpWidth;
	}	
	public float getDpHeight() {
		return dpHeight;
	}
	public int getNumberCharTextView(int width,int textsize){
		return (int)(width/textsize);
	}
	public String count(int process){
		String number = null;
		String timePhut = null;
		String timeGiay = null;
		
		

			int phut = (int) (process / 60);
			if (phut < 10) {
				timePhut = "0" + String.valueOf(phut);
			} else {
				timePhut = String.valueOf(phut);
			}

			int giay = (int) (process - (phut * 60));
			if (giay < 10) {
				timeGiay = "0" + String.valueOf(giay);
			} else {
				timeGiay = String.valueOf(giay);
			}
			number = timePhut+ ":"+timeGiay;
		
		return number;
	}

	
	 
	 
}
