package vn.theagency.fragment;

import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

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
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaPlayer.OnErrorListener;
import android.media.MediaPlayer.OnPreparedListener;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
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
	public int mPosition=0;
	int mMaxVolume = 10;
	int curVolume=5;
	Messenger messenger;
	Message mg;
	TextView start,end;
	
	private TimerTask task;
	private Timer timer = null;
	int progresss = 0;
	boolean isProcess=true;
	boolean isShuffer = false;
	boolean isRepeat = false;
	int[] intArray;
	int shufferSong = 0;
	private static Fragment_MusikListe _ins;

	public Fragment_MusikListe(ArrayList<Songs> arr){
		this.arr = arr;
		
		
	}

	public static Fragment_MusikListe newInstance(boolean isActive, ArrayList<Songs> arr) {
		if (Fragment_MusikListe._ins == null) {
			Fragment_MusikListe._ins = new Fragment_MusikListe(arr);
			
		}else{
			Fragment_MusikListe._ins.adapter.notifyDataSetChanged();
		}
		if(!isActive){
			if(Fragment_MusikListe._ins.media!=null){
				
				Fragment_MusikListe._ins.media.stop();
						
			}
			Fragment_MusikListe._ins = null;
		}

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
		if(isShuffer){
			shuffer.setBackgroundResource(R.drawable.btn_shuffer_actice);
		}else{
			shuffer.setBackgroundResource(R.drawable.btn_shuffer_normal);
		}
		if(isRepeat){
			
			repeat.setBackgroundResource(R.drawable.btn_repeat_active);
		}else{
			repeat.setBackgroundResource(R.drawable.btn_repeat_normal);
		}
		
		
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
		seekLine.setOnSeekBarChangeListener(this);

		// seekLine.setVisibility(View.GONE);
		seekVolume = (SeekBar) wrapper.findViewById(Key.SEEKBAR_VOLUME);

		list = (ListView) wrapper.findViewById(Key.LISTVIEW_LIBRARY);
		
		
		
		adapter = new Musik_Adapter((int)(mMusik.musik_list_height/7),arr, R.layout.items_musik, getActivity());
		

		
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
		
	
		if(media!=null){
			if(media.isPlaying()){
				
				if(list!=null){
					//start.setText(mHelper.count(progresss));
					//end.setText(mHelper.count((media.getDuration() - (progresss*1000))/1000));
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
			
			play.setVisibility(View.VISIBLE);
			play.setEnabled(false);
			pause.setVisibility(View.GONE);
			next.setEnabled(false);
			prev.setEnabled(false);
		}
		
			
		
	
	}
	
	
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
		
		try {
			if(isProcess){
			isProcess = false;
			if(timer!=null){
				timer.cancel();
				timer = null;
			}
			
			if(media!=null){
				if(media.isPlaying()){
					media.stop();
				//	media.release();
				}
			}
			progresss = 0;
			this.mMusik.mLine.setProgress(0);
			play.setEnabled(true);
			mPosition = position;
			if (mPosition == 0) {
				prev.setEnabled(false);
			} else {
				prev.setEnabled(true);
			}
			if (mPosition == (arr.size()-1)) {
				next.setEnabled(false);
			} else {
				next.setEnabled(true);
			}
			PlayLocalUrl(arr.get(mPosition).getmURL());

			

			for (int i = 0; i < arr.size(); i++) {
				arr.get(i).setmStatus(false);
			}
			arr.get(mPosition).setmStatus(true);
			adapter.notifyDataSetChanged();

			play.setVisibility(View.GONE);
			pause.setVisibility(View.VISIBLE);
			
			float log1=(float)(Math.log(mMaxVolume-curVolume)/Math.log(mMaxVolume));
			media.setVolume((1-log1),(1-log1));
			handler.sendEmptyMessageDelayed(1, 500);
			}
		} catch (Exception ex) {
			Log.i("LTH", "Listview error");
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

			if (mPosition < (arr.size()-1)) {
				
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
			if(!isRepeat){
				isRepeat = true;
				repeat.setBackgroundResource(R.drawable.btn_repeat_active);
			}else{
				isRepeat = false;
				repeat.setBackgroundResource(R.drawable.btn_repeat_normal);
			}
			break;
		case Key.SHUFFER:
			if(!isShuffer){
				intArray = new int[arr.size()];
				isShuffer = true;
				shuffer.setBackgroundResource(R.drawable.btn_shuffer_actice);
			}else{
				isShuffer = false;
				shuffer.setBackgroundResource(R.drawable.btn_shuffer_normal);
				intArray = null;
			}
			
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
	Handler handler = new Handler(){

		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			super.handleMessage(msg);
			if(msg.what == 1){
				isProcess = true;
			}
			
			adapter.notifyDataSetChanged();
		}
		
	};

	@Override
	public void onProgressChanged(SeekBar seekBar, int progress,
			boolean fromUser) {
		// TODO Auto-generated method stub
		switch (seekBar.getId()) {
		case Key.SEEKBAR_VOLUME:
			curVolume = progress;
			if(media!=null){
				if(media.isPlaying()){
					float log1=(float)(Math.log(mMaxVolume-curVolume)/Math.log(mMaxVolume));
					media.setVolume((1-log1),(1-log1));
				}
			}
		//	media.setVolume((float)(curVolume/10),(float)(curVolume/10));
			break;
		case Key.SEEKBAR_LINE:
			
			if(progress!=progresss){
				if(media!=null && media.isPlaying()){
					seekLine.setMax((int)media.getDuration()/1000);
				progresss = progress;
				media.seekTo(progresss*1000);
				}
			}
			//progresss =(int) (media.getCurrentPosition()/1000);
			if(media!=null && media.isPlaying()){
			seekLine.setMax((int)media.getDuration()/1000);
			start.setText(mHelper.count(progress));
			end.setText(mHelper.count((media.getDuration() - (progress*1000))/1000));
			}
			
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


	

	public void deleteMusik(){
		if(media!=null){
			if(media.isPlaying()){
				media.stop();
				media.release();
			}		
		}
	}
	
	
	private void PlayLocalUrl(String url) {
		
		if(media!=null){
			if(media.isPlaying()){
				media.stop();
			}
		}
		
		media = new MediaPlayer();
		media.setAudioStreamType(AudioManager.STREAM_MUSIC);
		try {
			media.setDataSource(url);
		} catch (Exception e) {
			e.printStackTrace();
		} 
		media.prepareAsync();
		// You can show progress dialog here untill it prepared to play
		media.setOnPreparedListener(new OnPreparedListener() {
			@Override
			public void onPrepared(MediaPlayer mp) {
				
				mp.start();
				int duration = mp.getDuration();
				int period = duration / 1000;
				mMusik.mLine.setMax(period);
				task = new TimerTask() {

					@Override
					public void run() {
						mMusik.mLine.post(new Runnable() {
							@Override
							public void run() {
								if (media.isPlaying()) {
									
									progresss++;
									mMusik.mLine.setProgress(progresss);
									
								}
							}
						});
					}
				};
				timer = new Timer();
				timer.schedule(task, 0, 1000);
			}
		});
		media.setOnErrorListener(new OnErrorListener() {
			@Override
			public boolean onError(MediaPlayer mp, int what, int extra) {
				// dissmiss progress bar here. It will come here when
				// MediaPlayer
				// is not able to play file. You can show error message to user
				return false;
			}
		});
		media.setOnCompletionListener(new OnCompletionListener() {
			
			@Override
			public void onCompletion(MediaPlayer mp) {
				// TODO Auto-generated method stub
				mMusik.mLine.setProgress(0);
				if(isShuffer){
					boolean shufferPlay = false;
					if(isRepeat){
						for(int i = 0 ; i < intArray.length;i++){
							intArray[i]=0;
						}
					}
					
					Random random = new Random();
					int a = random.nextInt(arr.size()-1);
					
						
						for(int i = 0 ; i < intArray.length;i++){
								if(intArray[i] == a){	
									a = random.nextInt(arr.size()-1);
									i=0;
								}
								if(intArray[intArray.length-1]!=0){
									shufferPlay = true;
									i = intArray.length;
								}
						}
							
				if(!shufferPlay){
					intArray[shufferSong]= a;
					shufferSong++;
				
				media.stop();
				mPosition = a;
				list.setSelection(a);
				list.requestFocus();
				list.smoothScrollToPosition(a);
				if (media.isPlaying()) {
					play.setVisibility(View.GONE);
					pause.setVisibility(View.VISIBLE);
				}
				list.performItemClick(list.getChildAt(mPosition), mPosition,
						list.getAdapter().getItemId(mPosition));
				adapter.notifyDataSetChanged();
				}
						
				}else{
					if (mPosition < (arr.size()-1)) {
						
						media.stop();
						mPosition = mPosition + 1;
						if (media.isPlaying()) {
							play.setVisibility(View.GONE);
							pause.setVisibility(View.VISIBLE);
						}
						list.performItemClick(list.getChildAt(mPosition), mPosition,
								list.getAdapter().getItemId(mPosition));
						adapter.notifyDataSetChanged();

						
					}else{
						if(isRepeat){
							list.setSelection(0);
							list.requestFocus();
							list.smoothScrollToPosition(0);
							
							media.stop();
							mPosition = 0;
							if (media.isPlaying()) {
								play.setVisibility(View.GONE);
								pause.setVisibility(View.VISIBLE);
							}
							list.performItemClick(list.getChildAt(mPosition), mPosition,
									list.getAdapter().getItemId(mPosition));
							adapter.notifyDataSetChanged();
						}
						arr.get(arr.size()-1).setmStatus(false);
						adapter.notifyDataSetChanged();
						end.setText("00:00");
						prev.setEnabled(false);
						next.setEnabled(false);
						play.setVisibility(View.VISIBLE);
						pause.setVisibility(View.GONE);
						
					}
				}
			
				
			}
		});
	}



}
