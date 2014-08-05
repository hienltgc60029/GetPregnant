package vn.theagency.helper;

import java.util.ArrayList;

import vn.theagency.getpregnant.R;
import vn.theagency.objects.Audios;
import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

@SuppressLint("InflateParams")
public class SammlungAdapter extends BaseAdapter {

	public int layout;
	public Context mContext;
	public ArrayList<Audios> arr;
	public int sizeRow;

	public SammlungAdapter(int layout, Context mContext, ArrayList<Audios> arr,
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
	public View getView(final int position, View convertView, ViewGroup parent) {

		View view = convertView;
		MyViewHolder mViewHolder;
		if (view == null) {
			LayoutInflater inflater = (LayoutInflater) mContext
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			view = inflater.inflate(R.layout.items_sam, null);
			mViewHolder = new MyViewHolder();
			view.setTag(mViewHolder);
			//
			AbsListView.LayoutParams params = new AbsListView.LayoutParams(
					AbsListView.LayoutParams.MATCH_PARENT, sizeRow);
			
			view.setLayoutParams(params);
			view.setPadding(30, 0, 30, 0);
		}
			mViewHolder = (MyViewHolder) view.getTag();
			mViewHolder.btnDownload = mButton(view, R.id.btn_down);
			mViewHolder.btnUp = mButton(view, R.id.btn_up);
			mViewHolder.btnLos = mButton(view, R.id.btn_los);
			mViewHolder.mTitle = mTextView(view, R.id.txtTitle,
					arr.get(position).getmTitle());
			mViewHolder.mDec = mTextView(view, R.id.txtDec, arr.get(position)
					.getmDecription());

			
			

			mViewHolder.btnLos.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					arr.remove(position);
					notifyDataSetChanged();
				}
			});

			mViewHolder.btnUp.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					upAction(arr, position);
					notifyDataSetChanged();
				}
			});

			mViewHolder.btnDownload.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					upAction(arr, position + 1);
					notifyDataSetChanged();
				}
			});

			//
			if ((position % 2) != 0) {
				view.setBackgroundResource(R.drawable.bg_library);
				// view.setBackgroundColor(Color.WHITE);
			}

			if (position == 0) {
				mViewHolder.btnUp.setEnabled(false);
			}

			if (position == (arr.size() - 1)) {
				mViewHolder.btnUp.setEnabled(true);
			}
			if (position == (arr.size() - 1)) {
				mViewHolder.btnDownload.setEnabled(false);
			}
			if (position == 0) {
				mViewHolder.btnDownload.setEnabled(true);
			}

		return view;
	}

	private class MyViewHolder {
		TextView mTitle, mDec;
		ImageView imageView;
		Button btnLos, btnUp, btnDownload;
	}

	private TextView mTextView(View v, int resId, String textview) {
		TextView tv = (TextView) v.findViewById(resId);
		tv.setText(textview);
		return tv;
	}

	private Button mButton(View v, int resId) {
		Button btn = (Button) v.findViewById(resId);

		return btn;
	}

	private ImageView mImage(View v, int resId, int image) {
		ImageView iv = (ImageView) v.findViewById(resId);
		iv.setImageResource(image); //

		return iv;
	}

	public void upAction(ArrayList<Audios> array, int position) {
		ArrayList<Audios> mtemp = new ArrayList<Audios>();
		Log.i("LTH", array.get(position).getmTitle());
		for (int i = 0; i < array.size(); i++) {
			if (i == (position - 1)) {
				mtemp.add(array.get(position));
				mtemp.add(array.get(i));
				i++;

			} else {
				mtemp.add(array.get(i));
			}
		}
		Log.i("LTH", mtemp.get(position - 1).getmTitle());
		array.clear();
		for (int i = 0; i < mtemp.size(); i++) {
			array.add(mtemp.get(i));
		}
		mtemp.clear();
	}

}
