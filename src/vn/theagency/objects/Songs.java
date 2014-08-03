package vn.theagency.objects;

import android.R.bool;

public class Songs{

	String mName;
	String mLine;
	String mURL;
	boolean mStatus;
	public Songs(String mName, String mLine, String mURL) {
		super();
		this.mName = mName;
		this.mLine = mLine;
		this.mURL = mURL;
		this.mStatus=false;
	}
	public String getmName() {
		return mName;
	}
	public void setmName(String mName) {
		this.mName = mName;
	}
	public String getmLine() {
		return mLine;
	}
	public void setmLine(String mLine) {
		this.mLine = mLine;
	}
	public String getmURL() {
		return mURL;
	}
	public void setmURL(String mURL) {
		this.mURL = mURL;
	}
	public boolean ismStatus() {
		return mStatus;
	}
	public void setmStatus(boolean mStatus) {
		this.mStatus = mStatus;
	}
	
	
	
	
	
}
