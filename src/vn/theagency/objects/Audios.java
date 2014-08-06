package vn.theagency.objects;

public class Audios {

	public String mTitle;
	public String mDecription;
	public String mPrice;
	public int mImageURL;
	public Audios(String mTitle, String mDecription, String mPrice,
			int mImageURL) {
		super();
		this.mTitle = mTitle;
		this.mDecription = mDecription;
		this.mPrice = mPrice;
		this.mImageURL = mImageURL;
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
	public String getmURLDownload() {
		return mPrice;
	}
	public void setmURLDownload(String mURLDownload) {
		this.mPrice = mURLDownload;
	}
	public int getmImageURL() {
		return mImageURL;
	}
	public void setmImageURL(int mImageURL) {
		this.mImageURL = mImageURL;
	}
	
	
}
