package vn.theagency.helper;

import java.util.ArrayList;

import vn.theagency.getpregnant.R;
import vn.theagency.objects.Songs;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class Musik_Adapter extends BaseAdapter {

	ArrayList<Songs> arr;
	int Layout;
	Context context;
	AnimationDrawable anim;
	
	
	public Musik_Adapter(ArrayList<Songs> arr, int layout, Context context) {
		super();
		this.arr = arr;
		Layout = layout;
		this.context = context;
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
	 private class MyViewHolder {
         TextView mTitle, mTime;
         ImageView mIcon;
 }
	 private TextView detail(View v, int resId, String text) {
         TextView tv = (TextView) v.findViewById(resId);
         tv.setText(text);
         return tv;
 }
 
 private ImageView detail(View v, int resId, int icon) {
         ImageView iv = (ImageView) v.findViewById(resId);
         iv.setImageResource(icon); //
         
         return iv;
 }

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		MyViewHolder mViewHolder;
		View view = convertView;
		
		if(view==null){
			LayoutInflater inflater = (LayoutInflater) context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			view = inflater.inflate(Layout, null);
			mViewHolder = new MyViewHolder();
            view.setTag(mViewHolder);
		}else{
			mViewHolder = (MyViewHolder) view.getTag();
		}
		
		 mViewHolder.mTitle = detail(view, R.id.title, arr.get(position).getmName());
         mViewHolder.mTime  = detail(view, R.id.counter,  arr.get(position).getmLine());
         
         mViewHolder.mIcon = detail(view, R.id.icon, R.drawable.animation_play);
         if(arr.get(position).ismStatus()){
        	 Log.i("LTH","pos"+ String.valueOf(position));
        	view.setBackgroundResource(R.drawable.bg_music_active);
        	 mViewHolder.mIcon.setVisibility(View.VISIBLE);
        	 mViewHolder.mTitle.setSelected(true);
             mViewHolder.mTitle.setEnabled(false);
            // mViewHolder.mTitle.setFocusable(true);
             mViewHolder.mTime.setTextColor(Color.parseColor("#fce500"));
             mViewHolder.mTime.setAlpha(0.8f);
             mViewHolder.mTitle.setTextColor(Color.parseColor("#fce500"));
             mViewHolder.mTitle.setAlpha(0.8f);
        	/* anim = (AnimationDrawable) mViewHolder.mIcon.getBackground(); 
    		 anim.start();*/
         }else{
        	 if(view !=null){
        		 view.setBackgroundColor(Color.TRANSPARENT);
        		 mViewHolder.mTitle.setSelected(false);
                 mViewHolder.mTitle.setEnabled(true);
                 mViewHolder.mTitle.setFocusable(false);
                 mViewHolder.mTime.setTextColor(Color.parseColor("#fffab2"));
                 mViewHolder.mTime.setAlpha(0.2f);
                 mViewHolder.mTitle.setTextColor(Color.parseColor("#fffab2"));
                 mViewHolder.mTitle.setAlpha(0.2f);
        	 }
        	 mViewHolder.mIcon.setVisibility(View.GONE);
         }
       //  mViewHolder.ivIcon  = detail(convertView, R.id.ivIcon,  myList.get(position).getImgResId());


	/*	TextView name = (TextView) view.findViewById(R.id.title);
		name.setText(arr.get(position).getmName());
		TextView time = (TextView) view.findViewById(R.id.counter);
		time.setText(arr.get(position).getmLine());*/
		
		return view;
	}

}
