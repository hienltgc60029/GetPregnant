package vn.theagency.helper;

import java.util.ArrayList;

import vn.theagency.getpregnant.R;
import vn.theagency.objects.Audios;
import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
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
	//


	public Audios_Adapter(int layout, Context mContext, ArrayList<Audios> arr,
			int sizeRow) {
		super();
		this.layout = layout;
		this.mContext = mContext;
		this.arr = arr;
		this.sizeRow = sizeRow;
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

    		mViewHolder.mTitle = mTextView(view, R.id.txtTitle, arr.get(position).getmTitle());
    		mViewHolder.imageView = mImage(view, R.id.img, R.drawable.avatar);
    		mViewHolder.mDec = mTextView(view, R.id.txtDec, arr.get(position).getmDecription());
    		mViewHolder.btnView = mButton(view, R.id.btnView, R.drawable.btn_view);
    		mViewHolder.btnDownload = mButton(view, R.id.btn, R.drawable.btn_download);
    		//
            
        	AbsListView.LayoutParams params = new AbsListView.LayoutParams(
    				AbsListView.LayoutParams.MATCH_PARENT, sizeRow);
        	
    		int imgHeight = (int) mViewHolder.imageView.getHeight();
    		
    		int h = (int) ((sizeRow/4) - imgHeight) / 2;    		
    		view.setLayoutParams(params);
    		view.setPadding(30, h, 30, h);
    		
    		
            
		}else{
			mViewHolder = (MyViewHolder) view.getTag();
		}
		
		
		
		
	

		if ((position % 2) != 0) {
		//	view.setBackgroundResource(R.drawable.bg_library);
		}

		return view;
	}

	private class MyViewHolder {
		TextView mTitle, mDec;
		ImageView imageView;
		Button btnView,btnDownload;
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