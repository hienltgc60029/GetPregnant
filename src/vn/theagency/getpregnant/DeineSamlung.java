package vn.theagency.getpregnant;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

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
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaPlayer.OnPreparedListener;
import android.os.Bundle;
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
import android.widget.TextView;
import android.widget.Toast;

public class DeineSamlung extends Activity implements OnClickListener,
		OnScrollListener, OnPreparedListener, OnCompletionListener {

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
		// player = new MediaPlayer();

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
		SQliteData data = new SQliteData(getApplicationContext());
		data.open();
		arrAudios = data.getAllAudiosCollections();
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

	private String getDurationLength() {
		int pos;
		String number;
		String timePhut = null;
		String timeGiay = null;
		if (arrAudios.get(positionSong).mID.equalsIgnoreCase("1")) {
			pos = R.raw.wen;
		} else if (arrAudios.get(positionSong).mID.equalsIgnoreCase("2")) {
			pos = R.raw.lie;
		} else if (arrAudios.get(positionSong).mID.equalsIgnoreCase("3")) {
			pos = R.raw.zuru;
		} else {
			pos = R.raw.gen;
		}
		MediaPlayer mp = MediaPlayer.create(getApplicationContext(), pos);

		int phut = (int) ((mp.getDuration() / 60000) % 60);
		if (phut < 10) {
			timePhut = "0" + String.valueOf(phut);
		} else {
			timePhut = String.valueOf(phut);
		}

		int giay = (int) (((mp.getDuration() - (phut * 60000)) / 1000) % 60);
		if (giay < 10) {
			timeGiay = "0" + String.valueOf(giay);
		} else {
			timeGiay = String.valueOf(giay);
		}
		number = timePhut + ":" + timeGiay;

		return number;
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
			boolean sizeArr = true;
			SQliteData data = new SQliteData(getApplicationContext());
			data.open();
			if(data.getAllAudiosCollections().size()==0){
				sizeArr = false;
			}
			data.close();
			if(sizeArr){
				if (player != null) {
					if (player.isPlaying()) {
						btn_play.setBackgroundResource(R.drawable.btn_playaudio);
						player.pause();

					} else {
						btn_play.setBackgroundResource(R.drawable.btn_pause);
						player.start();

					}
				} else {

					btn_play.setBackgroundResource(R.drawable.btn_pause);
					if (arrAudios.get(positionSong).mID.equalsIgnoreCase("1")) {
						player = MediaPlayer.create(getApplicationContext(),
								R.raw.wen);
						player.start();
						
						// positionSong = R.raw.wen;
					} else if (arrAudios.get(positionSong).mID
							.equalsIgnoreCase("2")) {
						player = MediaPlayer.create(getApplicationContext(),
								R.raw.lie);
						player.start();

					} else if (arrAudios.get(positionSong).mID
							.equalsIgnoreCase("3")) {
						player = MediaPlayer.create(getApplicationContext(),
								R.raw.zuru);
						player.start();
					} else {
						player = MediaPlayer.create(getApplicationContext(),
								R.raw.gen);
						player.start();
					}
					
					timeEnd.setText(getDurationLength());

					// PlayResource(positionSong);
					player.setOnPreparedListener(this);
					player.setOnCompletionListener(this);
					
				}

			}
			
			break;
		default:
			break;
		}
	}

	public void clearMemory() {
		back.setOnClickListener(null);
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
	public void onPrepared(final MediaPlayer mp) {
		// TODO Auto-generated method stub
		Log.i("LTH", ":"+String.valueOf(positionSong));
		for(int i = 0; i < arrAudios.size() ; i++){
			arrAudios.get(i).setActive(false);
		}
		arrAudios.get(positionSong).setActive(true);
		adapter.notifyDataSetChanged();
		mp.start();
		musik_line.setProgress(0);

		int duration = mp.getDuration();
		musik_line.setMax((int) (duration / 1000));
		final int period = duration / 1000;
		task = new TimerTask() {

			@Override
			public void run() {
				musik_line.post(new Runnable() {
					@Override
					public void run() {
						if (mp.isPlaying()) {
							progresss++;
							musik_line.setProgress(progresss);
							timeStart.setText(mHelper.count(progresss));
							timeEnd.setText(mHelper.count(period - progresss));
							if (progresss == (period-1)) {
								progresss = 0;
								SQliteData data = new SQliteData(getApplicationContext());
								data.open();
								if (data.getAllAudiosCollections() == null) {
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
	

	@Override
	public void onCompletion(MediaPlayer mp) {
		try{
		mp.stop();
		
		Log.i("LTH", "current: "+ String.valueOf(positionSong));
		SQliteData data = new SQliteData(getApplicationContext());
		data.open();
		
		arrAudios = data.getAllAudiosCollections();
		
		if (arrAudios.size() != 0) {
			if (arrAudios.size() == 1) {

				positionSong = 0;

			}
			if (arrAudios.size() > 1) {
				Log.i("LTH", ">1");
				
						if (positionSong < arrAudios.size()) {
							positionSong++;
							Log.i("LTH", "=1");
						} else {
							Log.i("LTH", "<1");
							positionSong = 0;
						}
			}

		}
		
		
		
		Log.i("LTH", "next song: "+ String.valueOf(positionSong));
			timeEnd.setText(getDurationLength());
			btn_play.setBackgroundResource(R.drawable.btn_pause);
			if (arrAudios.get(positionSong).mID.equalsIgnoreCase("1")) {
				mp = MediaPlayer.create(getApplicationContext(), R.raw.wen);
				mp.start();
				// positionSong = R.raw.wen;
			} else if (arrAudios.get(positionSong).mID.equalsIgnoreCase("2")) {
				mp = MediaPlayer.create(getApplicationContext(), R.raw.lie);
				mp.start();

			} else if (arrAudios.get(positionSong).mID.equalsIgnoreCase("3")) {
				mp = MediaPlayer
						.create(getApplicationContext(), R.raw.zuru);
				mp.start();
			} else {
				mp = MediaPlayer.create(getApplicationContext(), R.raw.gen);
				mp.start();
			}
			
			timeEnd.setText(getDurationLength());
			mp.setOnPreparedListener(this);
			mp.setOnCompletionListener(this);
			
			
		
		data.close();
		
		}catch(Exception ex){
			this.onBackPressed();
		}
	}

}
