package vn.theagency.getpregnant;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import vn.theagency.getpregnantapplication.R;
import vn.theagency.helper.Helper;
import vn.theagency.helper.Key;
import vn.theagency.helper.SammlungAdapter;
import vn.theagency.layout.UI_Deneine;
import vn.theagency.objects.Audios;
import vn.theagency.sqlite.SQliteData;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaPlayer.OnErrorListener;
import android.media.MediaPlayer.OnPreparedListener;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.os.Messenger;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.CalendarView.OnDateChangeListener;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;
import android.widget.Toast;

public class DeineSamlung extends Activity implements OnClickListener,
		OnScrollListener, OnSeekBarChangeListener,OnCompletionListener,OnPreparedListener {

	private Helper mHelper;
	public UI_Deneine mDeine;
	public FrameLayout initUIHeader;
	public FrameLayout wrapper;

	public View initUISammLungBottom;
	public ListView initUIList;
	public ImageView initUIDown;
	public ListView list;
	public FrameLayout initUIDeineSamlung;
	Audios audios;
	View btn_play, back, hinzu;
	ArrayList<Audios> arrAudios;

	boolean isPlay = false;
	//
	MediaPlayer player;
	SeekBar musik_line;
	int positionSong = 0;
	private TimerTask task;
	private Timer timer = null;
	int progresss = 0;
	TextView timeStart, timeEnd;
	SammlungAdapter adapter;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		Log.i("LTH", "Deine Sammlung");
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		this.mHelper = Helper.shareIns(getApplicationContext());
		this.mDeine = UI_Deneine.shareIns(getApplicationContext());

		this.initUIHeader = this.mDeine.initUIHeader();
		this.initUISammLungBottom = this.mDeine.initUISammLungBottom();
		this.initUIDown = this.mDeine.initUIDown();
		this.initUIList = this.mDeine.initUIList();
		this.initUIDeineSamlung = this.mDeine.initUIDeineSamlung();
		initUI();
		
		
		
	//	player.setOnCompletionListener(this);
		
		//
		list = (ListView) findViewById(Key.LISTVIEW_LIBRARY);
		list.setScrollbarFadingEnabled(true);
		list.setHorizontalScrollBarEnabled(false);
		list.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
		list.setVerticalScrollBarEnabled(false);
		list.setSmoothScrollbarEnabled(true);
		int color = Color.parseColor("#e8d3a0");
		ColorDrawable drawable = new ColorDrawable(color);
		drawable.setAlpha(100);
		list.setDivider(drawable);
		list.setDividerHeight(1);
		list.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
		list.setOnScrollListener(this);

		int height = (int) (this.mDeine.bottom + this.mDeine.bottom_down + this.mDeine.header_height);
		int rowSize = (int) (mHelper.getAppHeight() - height) / 3;
		arrAudios = new ArrayList<Audios>();
		SQliteData data = new SQliteData(getApplicationContext());
		data.open();
		
		for(int i = 0 ; i < data.getAllAudiosCollections().size();i++){
			arrAudios.add(data.getAllAudiosCollections().get(i));
		}
		
		
		if(arrAudios== null){
			btn_play.setOnClickListener(null);
		}
		data.close();
		adapter = new SammlungAdapter(R.layout.items,
				getApplicationContext(), arrAudios, rowSize);
		list.setAdapter(adapter);
		
	}

	public void initUI() {
		this.wrapper = new FrameLayout(this.getApplicationContext());
		FrameLayout.LayoutParams para = new FrameLayout.LayoutParams(
				FrameLayout.LayoutParams.MATCH_PARENT,
				FrameLayout.LayoutParams.MATCH_PARENT);
		this.wrapper.setLayoutParams(para);

		this.wrapper.addView(initUIHeader);
		this.wrapper.addView(this.initUIDeineSamlung);
		this.wrapper.addView(this.initUIList);

		this.wrapper.addView(this.initUIDown);
		this.wrapper.addView(this.initUISammLungBottom);
		setContentView(this.wrapper);
		
		back = findViewById(Key.btn_back);
		hinzu = findViewById(Key.btn_deine_musik);

		btn_play = findViewById(Key.PLAY);
		btn_play.setOnClickListener(this);
		SQliteData data = new SQliteData(getApplicationContext());
		data.open();
		if(data.getAllAudiosCollections().size()==0){
			btn_play.setEnabled(false);
		}
		data.close();
		musik_line = (SeekBar) findViewById(Key.SEEKBAR_LINE);
		musik_line.setOnSeekBarChangeListener(this);
		timeStart = (TextView) findViewById(Key.START);

		timeStart.setText("00:00");
		timeEnd = (TextView) findViewById(Key.End);
		timeEnd.setText("00:00");

	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		back.setOnClickListener(this);
		hinzu.setOnClickListener(this);
	}

	

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case Key.btn_back:
			this.onBackPressed();
			overridePendingTransition(R.anim.cover_alpha_in, R.anim.cover_alpha_out);
			break;
		case Key.btn_deine_musik:
			this.onBackPressed();
			overridePendingTransition(R.anim.cover_alpha_in, R.anim.cover_alpha_out);
			break;
		case Key.PLAY:
							if (player != null) {
				if (player.isPlaying()) {
					btn_play.setBackgroundResource(R.drawable.btn_playaudio);
					player.pause();
					
				} else {
					btn_play.setBackgroundResource(R.drawable.btn_pause);
					player.start();
				}
			}else{
				for(int i = 0; i < arrAudios.size() ; i++){
					arrAudios.get(i).setActive(false);
				}
				arrAudios.get(positionSong).setActive(true);
				btn_play.setBackgroundResource(R.drawable.btn_pause);
				Log.i("LTH", "positionSong");
				Log.i("LTH", String.valueOf(positionSong));
				Log.i("LTH", arrAudios.get(positionSong).getmURLMp3());
				playAudio(arrAudios.get(positionSong).getmPrice());
				adapter.notifyDataSetChanged();
				
			}
						
			break;
		default:
			break;
		}
	}

	public void clearMemory() {
		back.setOnClickListener(null);
		
	}

	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
		clearMemory();
		
		Intent intent = new Intent(getApplicationContext(), Deine_Titel.class);
		intent.putExtra("Audios", getIntent().getExtras().getString("Audios"));
		startActivity(intent);
		finish();
		System.gc();
		System.exit(0);

	}

	@Override
	public void onScrollStateChanged(AbsListView view, int scrollState) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onScroll(AbsListView view, int firstVisibleItem,
			int visibleItemCount, int totalItemCount) {
		// TODO Auto-generated method stub
		this.initUIDown.setVisibility(View.VISIBLE);
		if ((firstVisibleItem + visibleItemCount) == totalItemCount) {
			this.initUIDown.setVisibility(View.GONE);
		}

	}

	
	


	

	@Override
	public void onProgressChanged(SeekBar seekBar, int progress,
			boolean fromUser) {
		// TODO Auto-generated method stub
		if(progress!=progresss){
			if(player!=null && player.isPlaying()){
				progresss = progress;
				player.seekTo(progresss*1000);
			}else{
				progress=0;
			}
			
		}
		if(player!=null && player.isPlaying()){
		timeStart.setText(mHelper.count(progress));
		timeEnd.setText(mHelper.count((player.getDuration() - (progress*1000))/1000));
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

	@Override
	public void onCompletion(MediaPlayer mp) {
		// TODO Auto-generated method stub
		Log.i("LTH", "onCompletion");
		try{
			
			timer.cancel();
			timer = null;
			progresss = 0;
			player.stop();
		//	player.release();
			
			
		
		Log.i("LTH", "current: "+ String.valueOf(positionSong));
		SQliteData data = new SQliteData(getApplicationContext());
		data.open();
		arrAudios.clear();
		for(int i = 0 ; i < data.getAllAudiosCollections().size();i++){
			arrAudios.add(data.getAllAudiosCollections().get(i));
		}
		
		if (arrAudios.size() != 0) {
			if (arrAudios.size() == 1) {

				positionSong = 0;

			}
			if (arrAudios.size() > 1) {
				Log.i("LTH", ">1");
				
						if (positionSong < arrAudios.size()) {
							if(positionSong == (arrAudios.size()-1)){
								positionSong=0;
							}else{
								positionSong++;	
							}
							
							Log.i("LTH", "=1");
						} else {
							Log.i("LTH", "<1");
							positionSong = 0;
						}
			}

		}
		
		
		
		Log.i("LTH", "next song: "+ String.valueOf(positionSong));
			
			btn_play.setBackgroundResource(R.drawable.btn_pause);
			
			for(int i = 0; i < arrAudios.size() ; i++){
				arrAudios.get(i).setActive(false);
			}
			
				arrAudios.get(positionSong).setActive(true);
				playAudio(arrAudios.get(positionSong).getmPrice());
				
			
						
			
			//
			
			
			
		
		data.close();
		
		}catch(Exception ex){
			ex.printStackTrace();
			
			DeineSamlung.this.onBackPressed();
		}
		Log.i("LTH", "Commple");
		adapter.notifyDataSetChanged();
	}

	@Override
	public void onPrepared(MediaPlayer mp) {
		// TODO Auto-generated method stub
	//	adapter.notifyDataSetChanged();
		player.start();
		musik_line.setProgress(0);

		int duration = player.getDuration();
		musik_line.setMax((int) (duration / 1000));
		final int period = duration / 1000;
		
		task = new TimerTask() {

			@Override
			public void run() {
				musik_line.post(new Runnable() {
					@Override
					public void run() {
						if (player.isPlaying()) {
							progresss++;
							musik_line.setProgress(progresss);
							
							if (progresss == (period-1)) {
								player.setOnCompletionListener(DeineSamlung.this);
								progresss = 0;
								SQliteData data = new SQliteData(getApplicationContext());
								data.open();
								if (data.getAllAudiosCollections() == null) {
									Log.i("LTH", "Press 01");
									onBackPressed();
									
								}
								data.close();
							}
							
							// timeStart.setText(count(progresss));
							// timeEnd.setText(count(period-progresss));
						}
					}
				});
			}
		};
		timer = new Timer();
		timer.schedule(task, 0, 1000);
	}

	public void playAudio(String url){
		player = new MediaPlayer();
		player.setAudioStreamType(AudioManager.STREAM_MUSIC);
		try {
			player.setDataSource(url);
		} catch (Exception e) {
			e.printStackTrace();
		}
		//	player.start();
		player.prepareAsync();
		player.setOnPreparedListener(this);
	//	player.setOnCompletionListener(this);
	}
	
	
}
