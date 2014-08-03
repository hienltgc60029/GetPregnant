package vn.theagency.objects;

public class Audios {

	public String mTitle;
	public String mDecription;
	public String mURLDownload;
	public String mImageURL;
	public Audios(String mTitle, String mDecription, String mURLDownload,
			String mImageURL) {
		super();
		this.mTitle = mTitle;
		this.mDecription = mDecription;
		this.mURLDownload = mURLDownload;
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
		return mURLDownload;
	}
	public void setmURLDownload(String mURLDownload) {
		this.mURLDownload = mURLDownload;
	}
	public String getmImageURL() {
		return mImageURL;
	}
	public void setmImageURL(String mImageURL) {
		this.mImageURL = mImageURL;
	}
	
}
