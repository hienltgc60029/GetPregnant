package vn.theagency.fragment;

import java.util.ArrayList;

import vn.theagency.bussiness.Store;
import vn.theagency.getpregnant.Musik;
import vn.theagency.getpregnant.R;
import vn.theagency.helper.GetSongsAll;
import vn.theagency.helper.Helper;
import vn.theagency.helper.Key;
import vn.theagency.helper.Musik_Adapter;
import vn.theagency.layout.UI_Musik;
import vn.theagency.objects.Songs;
import android.app.Fragment;
import android.graphics.drawable.AnimationDrawable;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaPlayer.OnPreparedListener;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Message;
import android.os.Messenger;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.FrameLayout.LayoutParams;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;
import android.widget.Toast;

public class Fragment_MusikListe extends Fragment implements
		OnItemClickListener, OnClickListener, OnSeekBarChangeListener
		 {
	private Store mStore;
	private Helper mHelper;
	public UI_Musik mMusik;
	public FrameLayout wrapper;
	public View initUIBackGround, initUIHeader;
	public ListView initUIListView;
	public RelativeLayout initUIMusicBar;
	public FrameLayout initUIBottom;
	ListView list;
	ArrayList<Songs> arr;
	Musik_Adapter adapter;
	MediaPlayer media;
	View prev, next, play, pause, volume, repeat, shuffer, header;
	AnimationDrawable anim;
	SeekBar seekLine, seekVolume;
	public int mPosition;
	int mMaxVolume = 20;
	int curVolume;
	Messenger messenger;
	Message mg;
	TextView start,end;
	

	private static Fragment_MusikListe _ins;

	public Fragment_MusikListe(ArrayList<Songs> arr){
		this.arr = arr;
		curVolume = 10;
		
	}

	public static Fragment_MusikListe newInstance(boolean isActive, ArrayList<Songs> arr) {
		if (Fragment_MusikListe._ins == null) {
			Fragment_MusikListe._ins = new Fragment_MusikListe(arr);
			
		}else{
			Fragment_MusikListe._ins.adapter.notifyDataSetChanged();
		}
	//	createListenner();

		return Fragment_MusikListe._ins;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		Log.i("LTH", "Fragment Musik");
		
		this.mHelper = Helper.shareIns(getActivity());
		this.mMusik = UI_Musik.shareIns(getActivity());

		this.initUIHeader = this.mMusik.initUIHeader();
		this.initUIBackGround = this.mMusik.initUIBackGround();
		this.initUIMusicBar = this.mMusik.initUIMusicBar();
		this.initUIBottom = this.mMusik.initUIBottom();
		this.initUIListView = this.mMusik.initUIListView();

		initUI();
		
		
		
		Musik musik = (Musik) getActivity();
		messenger = musik.getMessenger();
		
		
		
		mg = new Message();
		Message.obtain();
		preference();
		return wrapper;
	}
	


	public void initUI() {

		this.wrapper = new FrameLayout(this.getActivity());
		FrameLayout.LayoutParams para = new FrameLayout.LayoutParams(
				FrameLayout.LayoutParams.MATCH_PARENT,
				FrameLayout.LayoutParams.MATCH_PARENT);
		this.wrapper.setLayoutParams(para);
		this.wrapper.addView(this.initUIHeader);
		this.wrapper.addView(initUIBackGround);

		this.wrapper.addView(initUIMusicBar);
		this.wrapper.addView(initUIListView);
		this.wrapper.addView(this.initUIBottom);
	}
	

	public void preference() {

		prev = wrapper.findViewById(Key.PREVAUDIO);
		
		next = wrapper.findViewById(Key.NEXTAUDIO);
		
		play = wrapper.findViewById(Key.PLAYAUDIO);
		
		pause = wrapper.findViewById(Key.PAUSEAUDIO);
		
		volume = wrapper.findViewById(Key.VOLUME);
		
		repeat = wrapper.findViewById(Key.REPEAT);
		
		shuffer = wrapper.findViewById(Key.SHUFFER);
		
		header = wrapper.findViewById(Key.HEADER);
		
		start = (TextView) this.wrapper.findViewById(Key.START);
		end =(TextView) this.wrapper.findViewById(Key.End);

		// ///////////////////////

		seekLine = (SeekBar) wrapper.findViewById(Key.SEEKBAR_LINE);

		// seekLine.setVisibility(View.GONE);
		seekVolume = (SeekBar) wrapper.findViewById(Key.SEEKBAR_VOLUME);

		list = (ListView) wrapper.findViewById(Key.LISTVIEW_LIBRARY);
		
		
		
		adapter = new Musik_Adapter(arr, R.layout.items_musik, getActivity());
		

		
		seekVolume.setProgress(curVolume);
		seekVolume.setMax(mMaxVolume);
		seekVolume.setOnSeekBarChangeListener(this);
		//
		list.setAdapter(adapter);
		list.setScrollbarFadingEnabled(true);
		list.setHorizontalScrollBarEnabled(false);
		list.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
		list.setVerticalScrollBarEnabled(false);
		list.setSmoothScrollbarEnabled(true);
		list.setDivider(null);
		list.setChoiceMode(ListView.CHOICE_MODE_SINGLE);

		createListenner();
		
		mPosition = 0;
		if(media!=null){
			if(media.isPlaying()){
				if(list!=null){
					Log.i("LTH", "1");
					pause.setVisibility(View.VISIBLE);
					play.setVisibility(View.GONE);
					if(list.getSelectedItemPosition()==0){
						prev.setEnabled(false);
					}else{
						prev.setEnabled(true);
					}
					if(list.getLastVisiblePosition()==arr.size()){
						next.setEnabled(false);
					}else{
						next.setEnabled(true);
					}
					
				}
			}else{
				Log.i("LTH", "2");
				play.setVisibility(View.VISIBLE);
				play.setEnabled(true);
				pause.setVisibility(View.GONE);
				if(list.getSelectedItemPosition()==0){
					prev.setEnabled(false);
				}else{
					prev.setEnabled(true);
				}
				if(list.getLastVisiblePosition()==arr.size()){
					next.setEnabled(false);
				}else{
					next.setEnabled(true);
				}
				
			}
		}else{
			Log.i("LTH", "3");
			play.setVisibility(View.VISIBLE);
			play.setEnabled(false);
			pause.setVisibility(View.GONE);
			next.setEnabled(false);
			prev.setEnabled(false);
		}
		
			
		
	
	}
	/*public void destroyListenner(){
		list.setOnItemClickListener(null);
		header.setOnClickListener(null);
		shuffer.setOnClickListener(null);
		repeat.setOnClickListener(null);
		volume.setOnClickListener(null);
		pause.setOnClickListener(null);
		play.setOnClickListener(null);
		next.setOnClickListener(null);
		prev.setOnClickListener(null);
	}*/
	
	public void createListenner(){
		list.setOnItemClickListener(this);
		header.setOnClickListener(this);
		shuffer.setOnClickListener(this);
		repeat.setOnClickListener(this);
		volume.setOnClickListener(this);
		pause.setOnClickListener(this);
		play.setOnClickListener(this);
		next.setOnClickListener(this);
		prev.setOnClickListener(this);
	}

	
	

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		Log.i("LTH", String.valueOf(position));
		// setListviewSelection(this.list, position);
		try {
			play.setEnabled(true);
			mPosition = position;
			if (mPosition == 0) {
				prev.setEnabled(false);
			} else {
				prev.setEnabled(true);
			}
			if (mPosition == arr.size()) {
				next.setEnabled(false);
			} else {
				next.setEnabled(true);
			}

			MusikAsyntask asyntask = new MusikAsyntask(null,curVolume,mMaxVolume);
			asyntask.execute(arr.get(mPosition).getmURL());

			for (int i = 0; i < arr.size(); i++) {
				arr.get(i).setmStatus(false);
			}
			arr.get(mPosition).setmStatus(true);
			adapter.notifyDataSetChanged();

			play.setVisibility(View.GONE);
			pause.setVisibility(View.VISIBLE);
			

		} catch (Exception ex) {
			Log.i("LTH", "Listview error");
		}
	}

	public void returnFalseStatus(){
		for (int i = 0; i < arr.size(); i++) {
			arr.get(i).setmStatus(false);
		}
	}
	

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case Key.PLAYAUDIO:
			if (!media.isPlaying()) {
				play.setVisibility(View.GONE);
				pause.setVisibility(View.VISIBLE);

				media.start();
			}
			break;
		case Key.NEXTAUDIO:

			if (mPosition < arr.size()) {
				
				media.stop();
				mPosition = mPosition + 1;
				if (media.isPlaying()) {
					play.setVisibility(View.GONE);
					pause.setVisibility(View.VISIBLE);
				}
				list.performItemClick(list.getChildAt(mPosition), mPosition,
						list.getAdapter().getItemId(mPosition));
				adapter.notifyDataSetChanged();

				
			}

			break;
		case Key.PREVAUDIO:
			if (mPosition > 0) {
				media.stop();
				mPosition = mPosition - 1;
				MusikAsyntask asyntask = new MusikAsyntask(null,curVolume,mMaxVolume);
				asyntask.execute(arr.get(mPosition).getmURL());
				

				media.start();
				if (media.isPlaying()) {
					play.setVisibility(View.GONE);
					pause.setVisibility(View.VISIBLE);
				}
				list.performItemClick(list.getChildAt(mPosition), mPosition,
						list.getAdapter().getItemId(mPosition));
				adapter.notifyDataSetChanged();
			}

			break;
		case Key.PAUSEAUDIO:

			if (media.isPlaying()) {
				play.setVisibility(View.VISIBLE);
				pause.setVisibility(View.GONE);
				media.pause();
			}
			break;
		case Key.VOLUME:
			if (this.mMusik.mBarVolume.getVisibility() == View.GONE) {
				this.mMusik.mBarVolume.setVisibility(View.VISIBLE);
				start.setVisibility(View.GONE);
				end.setVisibility(View.GONE);
			} else {
				this.mMusik.mBarVolume.setVisibility(View.GONE);
				start.setVisibility(View.VISIBLE);
				end.setVisibility(View.VISIBLE);
			}
			if (this.mMusik.mLine.getVisibility() == View.GONE) {
				this.mMusik.mLine.setVisibility(View.VISIBLE);
			} else {
				
				this.mMusik.mLine.setVisibility(View.GONE);
			}
			break;
		case Key.REPEAT:
			break;
		case Key.SHUFFER:
			
			break;
		case Key.HEADER:
			try {
				
				mg.arg1 = 2;
				messenger.send(mg);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			break;
		default:
			break;
		}
	}

	@Override
	public void onProgressChanged(SeekBar seekBar, int progress,
			boolean fromUser) {
		// TODO Auto-generated method stub
		switch (seekBar.getId()) {
		case Key.SEEKBAR_VOLUME:
			curVolume = progress;
			if(media!=null){
				if(media.isPlaying()){
					media.setVolume((float) progress / mMaxVolume, (float) progress
							/ mMaxVolume);
				}
			}
			
			
			break;

		default:
			break;
		}
	}

	@Override
	public void onStartTrackingTouch(SeekBar seekBar) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onStopTrackingTouch(SeekBar seekBar) {
		// TODO Auto-generated method stub

	}


	public class MusikAsyntask extends AsyncTask<String, Integer, Integer> {
		
		SeekBar mLime;
		int curVolume;
		int maxVolume;
		
		
		

		public MusikAsyntask(SeekBar mLime, int curVolume, int maxVolume) {
			super();
			this.mLime = mLime;
			this.curVolume = curVolume;
			this.maxVolume = maxVolume;
		}




		@Override
		protected Integer doInBackground(String... params) {
			try {
				if (media!=null) {
					media.stop();
					media.release();
				}
				media = MediaPlayer.create(getActivity(), Uri.parse(params[0]));
				media.start();
				media.setVolume((float) curVolume / maxVolume, (float) curVolume
						/ maxVolume);
		//		setVolume(mMaxVolume);
			} catch (Exception ex) {
				Toast.makeText(getActivity(), "Musik cann't start",
						Toast.LENGTH_SHORT).show();
			}
			return null;
		}
	}

	public void deleteMusik(){
		if(media!=null){
			if(media.isPlaying()){
				media.stop();
				media.release();
			}		
		}
	}


}
