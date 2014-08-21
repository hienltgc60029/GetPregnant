package vn.theagency.helper;

import java.util.ArrayList;

import vn.theagency.getpregnant.R;
import vn.theagency.objects.Audios;
import android.annotation.SuppressLint;
import android.app.DownloadManager;
import android.app.DownloadManager.Request;
import android.content.Context;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

@SuppressLint("InflateParams")
public class Audios_Adapter extends BaseAdapter {

	public int layout;
	public Context mContext;
	public ArrayList<Audios> arr;
	public int sizeRow;
	private Helper mHelper;

	//

	public Audios_Adapter(int layout, Context mContext, ArrayList<Audios> arr,
			int sizeRow) {
		super();
		this.layout = layout;
		this.mContext = mContext;
		this.arr = arr;
		this.sizeRow = sizeRow;
		this.mHelper = Helper.shareIns(mContext);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return arr.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return arr.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View view = convertView;
		MyViewHolder mViewHolder;
		if (view == null) {
			LayoutInflater inflater = (LayoutInflater) mContext
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			view = inflater.inflate(R.layout.audios_items, null);
			mViewHolder = new MyViewHolder();
			view.setTag(mViewHolder);
			//
			AbsListView.LayoutParams params = new AbsListView.LayoutParams(
					AbsListView.LayoutParams.MATCH_PARENT, sizeRow);

			view.setLayoutParams(params);
			int widthImage = (int) this.mContext.getResources().getDimension(
					R.dimen.AVARTA_WIDTH);
			int widthBtn = (int) this.mContext.getResources().getDimension(
					R.dimen.ItemText_Width);
			int margin = (int) this.mContext.getResources().getDimension(
					R.dimen.MarginLeft);
			int widthRow = (int) (this.mHelper.getAppWidth() - widthBtn
					- margin - widthImage);
			view.setPadding((int) widthRow / 2, 0, 0, 0);

		}
		mViewHolder = (MyViewHolder) view.getTag();

		mViewHolder.mTitle = mTextView(view, R.id.txtTitle, arr.get(position)
				.getmTitle());

		mViewHolder.mPrice = mTextView(view, R.id.txtprice, arr.get(position)
				.getmPrice());
		Typeface type = Typeface.createFromAsset(mContext.getAssets(),
				"MuseoSans_500.otf");

		mViewHolder.mPrice.setTypeface(type, Typeface.NORMAL);

		mViewHolder.imageView = mImage(view, R.id.img,
				arr.get(position).mImageURL);
		mViewHolder.mDec = mTextView(view, R.id.txtDec, arr.get(position)
				.getmDecription());
		mViewHolder.btnView = mButton(view, R.id.btnView, R.drawable.btn_view);
		if (arr.get(position).getmPrice().equalsIgnoreCase("Gratis")) {
			mViewHolder.btnDownload = mButton(view, R.id.btn,
					R.drawable.btn_download);
		} else {
			mViewHolder.btnDownload = mButton(view, R.id.btn,
					R.drawable.btn_buy);
		}
		mViewHolder.btnDownload.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				AsyntaskDownloadMp3 mp3 = new AsyntaskDownloadMp3("Wen");
				mp3.execute("http://programmerguru.com/android-tutorial/wp-content/uploads/2014/01/jai_ho.mp3");
			//	mp3.execute("https://dl-web.dropbox.com/get/Wendeltreppe.mp3?_subject_uid=284654301&w=AAB0WsvwxlIyN0ZsQixzIvbD1ftooCjZ6MEWaAuisPAgvA");	
			}
		});
		//
		mViewHolder.mDec.setText(setTextRight(arr.get(position).getmDecription()));
		

		if ((position % 2) != 0) {
			view.setBackgroundResource(R.drawable.bg_library);
		}

		return view;
	}

	public int testLenght(int textSize, String text) {
		Paint paint = new Paint();
		final float densityMultiplier = mContext.getResources()
				.getDisplayMetrics().density;
		final float scaledPx = mHelper.DpToPixel(textSize) * densityMultiplier;
		paint.setTextSize(scaledPx);
		final float size = paint.measureText(text);
		int testLenght = (int) (size / mHelper.DpToPixel(220));
		Log.i("LTH", String.valueOf(textSize) + ":" + String.valueOf(size)
				+ "-" + String.valueOf(testLenght));
		return testLenght;
	}

	private class MyViewHolder {
		TextView mTitle, mDec, mPrice;
		FrameLayout imageView;
		Button btnView, btnDownload;
		ImageView mIcon;

	}

	private TextView mTextView(View v, int resId, String textview) {
		TextView tv = (TextView) v.findViewById(resId);
		tv.setText(textview);
		return tv;
	}

	private Button mButton(View v, int resId, int button) {
		Button btn = (Button) v.findViewById(resId);
		btn.setBackgroundResource(button);
		return btn;
	}

	private FrameLayout mImage(View v, int resId, int image) {
		FrameLayout iv = (FrameLayout) v.findViewById(resId);
		iv.setBackgroundResource(image); //
		return iv;
	}

	private ImageView mImageIcon(View v, int resId, int image) {
		ImageView iv = (ImageView) v.findViewById(resId);
		iv.setBackgroundResource(image); //
		return iv;
	}

	public String setTextRight(String text) {
		int index=0;
		text = text.trim();
		String newText = text;
		
			if (testLenght(12, text) > 4) {
				for (int j = (text.length() - 2); j > 0; j--) {
					char temp = text.charAt(j);
					if (String.valueOf(temp).equals(" ")) {
						index++;
						if(index==2){				
							if(testLenght(12, text.substring(0,j))==4){
								newText = text.substring(0, j)+"\n"+text.substring(j+1);
							}	
						}
					}
				}
			
		}
		
		return newText;
	}
}
