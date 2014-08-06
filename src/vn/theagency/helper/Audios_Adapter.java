package vn.theagency.helper;

import java.util.ArrayList;

import vn.theagency.getpregnant.R;
import vn.theagency.objects.Audios;
import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
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
			view = inflater.inflate(R.layout.items, null);
			mViewHolder = new MyViewHolder();
			view.setTag(mViewHolder);
			//
			AbsListView.LayoutParams params = new AbsListView.LayoutParams(
					AbsListView.LayoutParams.MATCH_PARENT, sizeRow);
			
			view.setLayoutParams(params);
			int widthImage = (int)this.mContext.getResources().getDimension(R.dimen.AVARTA_WIDTH);
			int widthBtn = (int)this.mContext.getResources().getDimension(R.dimen.ROW_BUTTON_WIDTH);
			int margin = (int)this.mContext.getResources().getDimension(R.dimen.MarginLeft);
			int widthRow =(int) (this.mHelper.getAppWidth()-((widthBtn*2)+(margin*2)+widthImage));
			view.setPadding((int)widthRow/2, 0, 0, 0);
			
			

		} 
			mViewHolder = (MyViewHolder) view.getTag();
			mViewHolder.mTitle = mTextView(view, R.id.txtTitle,
					arr.get(position).getmTitle());
			mViewHolder.imageView = mImage(view, R.id.img, arr.get(position).mImageURL);
			mViewHolder.mDec = mTextView(view, R.id.txtDec, arr.get(position)
					.getmDecription());
			mViewHolder.btnView = mButton(view, R.id.btnView,
					R.drawable.btn_view);
			if(arr.get(position).getmURLDownload().equalsIgnoreCase("")){
				mViewHolder.btnDownload = mButton(view, R.id.btn,
						R.drawable.btn_download);
			}else{
				mViewHolder.btnDownload = mButton(view, R.id.btn,
						R.drawable.btn_buy);
			}
			
			//

			
		

		if ((position % 2) != 0) {
			view.setBackgroundResource(R.drawable.bg_library);
		}

		return view;
	}

	private class MyViewHolder {
		TextView mTitle, mDec;
		ImageView imageView;
		Button btnView, btnDownload;
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

	private ImageView mImage(View v, int resId, int image) {
		ImageView iv = (ImageView) v.findViewById(resId);
		iv.setImageResource(image); //

		return iv;
	}
}
