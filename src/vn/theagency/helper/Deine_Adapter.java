package vn.theagency.helper;

import java.util.ArrayList;

import vn.theagency.getpregnant.Musik;
import vn.theagency.getpregnant.R;

import vn.theagency.objects.Audios;
import vn.theagency.sqlite.SQliteData;
import vn.theagency.sqlite.SQliteData.SQdata;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

@SuppressLint("InflateParams")
public class Deine_Adapter extends BaseAdapter {

	public int layout;
	public Context mContext;
	public ArrayList<Audios> arr;
	public int sizeRow;
	public String indexHome;
	private Helper mHelper;
	public Audios audios;

	public Deine_Adapter(int layout, Context mContext, ArrayList<Audios> arr,
			int sizeRow,String indexHome) {
		super();
		this.layout = layout;
		this.mContext = mContext;
		this.arr = arr;
		this.sizeRow = sizeRow;
		this.indexHome = indexHome;
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
	public View getView(final int position, View convertView, ViewGroup parent) {
		View view = convertView;
		MyViewHolder mViewHolder;
		if (view == null) {
			LayoutInflater inflater = (LayoutInflater) mContext
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			view = inflater.inflate(R.layout.items, null);
			mViewHolder = new MyViewHolder();
            view.setTag(mViewHolder);
           
    		
    	
    		AbsListView.LayoutParams params = new AbsListView.LayoutParams(
    				AbsListView.LayoutParams.MATCH_PARENT, sizeRow);
    		view.setLayoutParams(params);
    		int widthImage = (int)this.mContext.getResources().getDimension(R.dimen.AVARTA_WIDTH);
			int widthBtn = (int)this.mContext.getResources().getDimension(R.dimen.ROW_BUTTON_WIDTH);
			int margin = (int)this.mContext.getResources().getDimension(R.dimen.MarginLeft);
			int widthRow =(int) (this.mHelper.getAppWidth()-((widthBtn*2)+(margin*2)+widthImage));
    		view.setPadding((int)widthRow/2,  0, 0, 0);
    		
    		
		}
			mViewHolder = (MyViewHolder) view.getTag();
			 mViewHolder.mTitle = mTextView(view, R.id.txtTitle, arr.get(position).getmTitle());
	    		mViewHolder.imageView = mImage(view, R.id.img, arr.get(position).getmImageURL());
	    		mViewHolder.mDec = mTextView(view, R.id.txtDec, arr.get(position).getmDecription());
	    		mViewHolder.btnView = mButton(view, R.id.btnView, R.drawable.btn_play);
	    		mViewHolder.btnDownload = mButton(view, R.id.btn, R.drawable.btn_collect);

	    		mViewHolder.btnView.setOnClickListener(new OnClickListener() {

	    			@Override
	    			public void onClick(View v) {
	    				// TODO Auto-generated method stub
	    				Intent intent = new Intent(mContext, Musik.class);
	    				intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
	    				intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
	    				intent.putExtra("Audios", indexHome);
	    				intent.putExtra(Audios.TITLE, arr.get(position).getmTitle());
	    				intent.putExtra(Audios.DECRIPTION, arr.get(position).getmDecription());
	    				intent.putExtra(Audios.IMAGEURL, String.valueOf(arr.get(position).getmImageURL()));
	    				intent.putExtra(Audios.IMAGEURL, String.valueOf(arr.get(position).getmImageURL()));
	    				intent.putExtra(Audios.ID, String.valueOf(arr.get(position).getmID()));
	    				mContext.startActivity(intent);
	    				
	    			}
	    		});
	    		mViewHolder.btnDownload.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						SQliteData data = new SQliteData(mContext);
						data.open();
						Log.i("LTH", "ID OF AUDIOS:" + arr.get(position).getmID());
						audios = new Audios(arr.get(position).getmID(),arr.get(position).getmTitle()
								, arr.get(position).getmDecription()
								, arr.get(position).getmPrice()
								, arr.get(position).getmImageURL());
						data.putAudiosCollections(audios);
						data.close();
						arr.remove(position);
						notifyDataSetChanged();
					}
				});
		
		if ((position % 2) != 0) {
			view.setBackgroundResource(R.drawable.bg_library);
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
