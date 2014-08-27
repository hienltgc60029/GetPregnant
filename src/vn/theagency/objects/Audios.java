package vn.theagency.objects;

public class Audios {
	
	
	public static final String TITLE = "Audio_Title";
	public static final String DECRIPTION = "mDecription";
	public static final String PRICE = "mPrice";
	public static final String IMAGEURL = "mImageURL";
	public static final String ID = "Audio_ID";
	public static final String STATUS = "Audio_Status";

	public String mID;
	public String mTitle;
	public String mDecription;
	public String mPrice;
	public int mImageURL;
	public boolean isActive;
	public boolean isDownload;
	public String mURLMp3;
	public int Status;
	
	public int getStatus() {
		return Status;
	}
	public void setStatus(int status) {
		Status = status;
	}
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
			int mImageURL,String urlMp3,int Status) {
		super();
		this.mID = mID;
		this.mTitle = mTitle;
		this.mDecription = mDecription;
		this.mPrice = mPrice;
		this.mImageURL = mImageURL;
		this.mURLMp3 = urlMp3;
		this.isActive = false;
		this.isDownload = false;
		this.Status = Status;
	}
	
	public String getmURLMp3() {
		return mURLMp3;
	}
	public void setmURLMp3(String mURLMp3) {
		this.mURLMp3 = mURLMp3;
	}
	public boolean isActive() {
		return isActive;
	}
	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}
	public boolean isDownload() {
		return isDownload;
	}
	public void setDownload(boolean isDownload) {
		this.isDownload = isDownload;
	}
	
}
