package vn.theagency.objects;

public class Audios {
	
	
	public static final String TITLE = "mTitle";
	public static final String DECRIPTION = "mDecription";
	public static final String PRICE = "mPrice";
	public static final String IMAGEURL = "mImageURL";
	public static final String ID = "mID";

	public String mID;
	public String mTitle;
	public String mDecription;
	public String mPrice;
	public int mImageURL;
	public String getmID() {
		return mID;
	}
	public void setmID(String mID) {
		this.mID = mID;
	}
	public String getmTitle() {
		return mTitle;
	}
	public void setmTitle(String mTitle) {
		this.mTitle = mTitle;
	}
	public String getmDecription() {
		return mDecription;
	}
	public void setmDecription(String mDecription) {
		this.mDecription = mDecription;
	}
	public String getmPrice() {
		return mPrice;
	}
	public void setmPrice(String mPrice) {
		this.mPrice = mPrice;
	}
	public int getmImageURL() {
		return mImageURL;
	}
	public void setmImageURL(int mImageURL) {
		this.mImageURL = mImageURL;
	}
	public Audios(String mID, String mTitle, String mDecription, String mPrice,
			int mImageURL) {
		super();
		this.mID = mID;
		this.mTitle = mTitle;
		this.mDecription = mDecription;
		this.mPrice = mPrice;
		this.mImageURL = mImageURL;
	}
	
}
