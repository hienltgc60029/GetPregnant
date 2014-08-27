package vn.theagency.fragment;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Timer;
import java.util.TimerTask;

import vn.theagency.getpregnant.Alarn;
import vn.theagency.getpregnant.DeineSamlung;
import vn.theagency.getpregnant.Deine_Titel;
import vn.theagency.getpregnant.Musik;
import vn.theagency.getpregnantapplication.R;
import vn.theagency.helper.Helper;
import vn.theagency.helper.Key;
import vn.theagency.layout.UI_Nohitaky;
import vn.theagency.objects.Audios;
import vn.theagency.sqlite.SQliteData;
import vn.theagency.sqlite.SQliteData.SQdata;
import android.app.Fragment;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.AssetManager;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaPlayer.OnPreparedListener;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.os.Message;
import android.os.Messenger;
import android.provider.AlarmClock;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class Fragment_Nohitaky extends Fragment implements OnClickListener,
		OnCheckedChangeListener, OnSeekBarChangeListener {
	private Helper mHelper;
	public UI_Nohitaky mNohitaky;

	public FrameLayout wrapper;
	public FrameLayout initUIBack;
	public View initUIBackGround, initUIBackGroundDark;
	public FrameLayout initUIMusik;
	public LinearLayout initUIBottom;
	public RelativeLayout initUITitle,initUIMusicBar;
	Messenger messenger;
	Message msg;
	View play_musik,play_audio;

	ImageView wecker, back;
	CheckBox musikCheck, pdfCheck;
	Switch changeBg;
	Audios audio;
	MediaPlayer player;
	SeekBar musik_line;
	private static Fragment_Nohitaky _ins;
	TextView timeStart,timeEnd;
	//
	int positionSong;
	private TimerTask task;
	private Timer timer = null;
	int progresss = 0;
	LinearLayout download,sammlung;
	String path;
	public Fragment_Nohitaky(Audios pAudio){
		this.audio = pAudio;
	}

	public static Fragment_Nohitaky newInstance(Audios pAudio, boolean isActive) {
		if (Fragment_Nohitaky._ins == null) {
			Fragment_Nohitaky._ins = new Fragment_Nohitaky(pAudio);
		}
		if(isActive){
			if(pAudio!=null){
				Fragment_Nohitaky._ins.audio = pAudio;
				
			}
		}else{
			if(Fragment_Nohitaky._ins.player!=null){
			
					Fragment_Nohitaky._ins.player.stop();
						
			}
			Fragment_Nohitaky._ins = null;
			
		}
		
		return Fragment_Nohitaky._ins;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		this.mHelper = Helper.shareIns(getActivity());
		this.mNohitaky = UI_Nohitaky.shareIns(getActivity());

		this.initUIBackGround = this.mNohitaky.initUIBackGround();
		this.initUIBack = this.mNohitaky.initUIBack();
		this.initUIMusik = this.mNohitaky.initUIMusik();
		this.initUIBottom = this.mNohitaky.initUIBottom();
		this.initUIMusicBar = this.mNohitaky.initUIMusicBar();
		this.initUITitle = this.mNohitaky.initUITitle(this.audio.getmImageURL(),this.audio.getmTitle(),("\n"+this.audio.getmDecription()));
		this.initUIBackGroundDark = this.mNohitaky.initUIBackGroundDark();

		initUI();
		preference();
		Musik musik = (Musik) getActivity();
		messenger = musik.getMessenger();
		
		msg = new Message();
		Message.obtain();
		return wrapper;
	}

	public void preference() {
		onAttach(getActivity());
		play_musik = wrapper.findViewById(Key.PLAY);
		play_musik.setOnClickListener(this);
		download = (LinearLayout) wrapper.findViewById(Key.DOWNLOAD_PDF);
		download.setOnClickListener(this);
		sammlung = (LinearLayout) wrapper.findViewById(Key.SAMMLUNG);
		sammlung.setOnClickListener(this);
		musikCheck = (CheckBox) this.wrapper
				.findViewById(Key.NOHITAKI_MusikCheck);
		musikCheck.setOnCheckedChangeListener(this);
		pdfCheck = (CheckBox) this.wrapper.findViewById(Key.NOHITAKI_PDFCheck);
		pdfCheck.setEnabled(false);
		path=Environment.getExternalStorageDirectory()+"/GetPregnant/";
		
		File file = new File(path,((Musik)getActivity()).namePDF);
		if (file.exists()) {
		  pdfCheck.setActivated(true);
		  download.setEnabled(true);
		}

		wecker = (ImageView) this.wrapper.findViewById(Key.NOHITAKI_Wecker);
		wecker.setOnClickListener(this);
		this.musik_line = (SeekBar) this.wrapper.findViewById(Key.SEEKBAR_LINE);
		this.musik_line.setOnSeekBarChangeListener(this);
		
		changeBg = (Switch) this.wrapper.findViewById(Key.NOHITAKI_ChangeBg);
		changeBg.setChecked(true);
		changeBg.setOnCheckedChangeListener(this);
		back = (ImageView) this.wrapper.findViewById(Key.btn_back);
		back.setOnClickListener(this);
		play_audio = this.wrapper.findViewById(Key.PLAYAUDIO);
		play_audio.setOnClickListener(this);
		
		timeStart = (TextView) this.wrapper.findViewById(Key.START);
		timeEnd = (TextView) this.wrapper.findViewById(Key.End);

		
		if(player!=null){
			if(player.isPlaying()){
				play_audio.setBackgroundResource(R.drawable.btn_pause);
			}else {
				play_audio.setBackgroundResource(R.drawable.btn_playaudio);
			}
		}
	}
	private String getDurationLength() {
		
		String number;
		String timePhut = null;
		String timeGiay = null;
		
	   

			int phut = (int) ((player.getDuration() / 60000) % 60);
			if (phut < 10) {
				timePhut = "0" + String.valueOf(phut);
			} else {
				timePhut = String.valueOf(phut);
			}

			int giay = (int) (((player.getDuration() - (phut * 60000)) / 1000) % 60);
			if (giay < 10) {
				timeGiay = "0" + String.valueOf(giay);
			} else {
				timeGiay = String.valueOf(giay);
			}
			number = timePhut+":"+timeGiay;
			
	    return number;
	}
	

	public void initUI() {
		this.wrapper = new FrameLayout(this.getActivity());
		FrameLayout.LayoutParams para = new FrameLayout.LayoutParams(
				FrameLayout.LayoutParams.MATCH_PARENT,
				FrameLayout.LayoutParams.MATCH_PARENT);
		this.wrapper.setLayoutParams(para);

		this.wrapper.addView(this.initUIBackGround);
		this.wrapper.addView(this.initUIBackGroundDark);
		this.wrapper.addView(initUIMusik);
		this.wrapper.addView(initUIBack);
		this.wrapper.addView(initUITitle);
		this.wrapper.addView(initUIBottom);
		this.wrapper.addView(initUIMusicBar);

	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case Key.PLAY:
			try {
				msg.arg1 = 1;
				messenger.send(msg);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			break;
		
		case Key.NOHITAKI_Wecker:
			
			((Musik)getActivity()).getHandler().sendEmptyMessage(1);
			
			break;
		case Key.btn_back:
			((Musik)getActivity()).onBackPressed();
			
		
			break;
		case Key.PLAYAUDIO:
			//Play Audios
			if(player!=null){
				
					if(player.isPlaying()){
						play_audio.setBackgroundResource(R.drawable.btn_playaudio);
						player.pause();		
					}else{
						play_audio.setBackgroundResource(R.drawable.btn_pause);
						player.start();				
					}
				}else{
				play_audio.setBackgroundResource(R.drawable.btn_pause);
				
				PlayResource(audio.getmPrice());
				
				timeEnd.setText(getDurationLength());
			}		
			break;
		case Key.DOWNLOAD_PDF:
			//((Musik)getActivity()).startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("www.education.gov.yk.ca/pdf/pdf-test.pdf")));
			coppyPDF();
			
			
			break;
		case Key.SAMMLUNG:
			Intent intent = new Intent(getActivity(),DeineSamlung.class);
			intent.putExtra("Audios", ((Musik)getActivity()).getIntent().getExtras().getString("Audios"));
			((Musik)getActivity()).startActivity(intent);
			((Musik)getActivity()).finish();
			
			break;
		default:
			break;
		}
	}
	
	void coppyPDF(){
		AssetManager assetManager= getActivity().getAssets();
	 try {
		InputStream inputStream=	assetManager.open(((Musik)getActivity()).namePDF);
		
		File file =new File(path, ((Musik)getActivity()).namePDF);
		
		FileOutputStream fileOutputStream =new FileOutputStream(file);
		byte[] pByte=new byte[1024];
		int lenght;
		while ( (lenght =inputStream.read(pByte)) >0) {
			fileOutputStream.write(pByte, 0, lenght);
			
		}
		fileOutputStream.close();
		Toast.makeText(getActivity(), "Download PDF Successfull", Toast.LENGTH_LONG).show();
			} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
		
	}


	@Override
	public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
		// TODO Auto-generated method stub
		switch (buttonView.getId()) {
		case Key.NOHITAKI_MusikCheck:
			
			if(isChecked){
				SQliteData data = new SQliteData(getActivity());
				data.open();
				data.removeAudios(audio.getmID());
				data.putAudiosCollections(audio);
				data.close();
			}else{
				SQliteData data = new SQliteData(getActivity());
				data.open();
				data.removeAudiosCollections(audio.getmID());
				data.putAudios(audio);
				data.close();
			}
			break;
		case Key.NOHITAKI_ChangeBg:
			if (changeBg.isChecked()) {
				
				/*WindowManager.LayoutParams lp = getActivity().getWindow().getAttributes();
				lp.screenBrightness = 1f;
				getActivity().getWindow().setAttributes(lp);*/
				
				this.initUIBackGroundDark.setVisibility(View.GONE);
				
				
			} else {
				
				/*WindowManager.LayoutParams lp = getActivity().getWindow().getAttributes();
				lp.screenBrightness = 0.2f;
				getActivity().getWindow().setAttributes(lp);*/
				
				this.initUIBottom.setAlpha(0.5f);
				this.initUIMusik.setAlpha(0.5f);
				this.initUIBackGroundDark.setVisibility(View.VISIBLE);
				
			}
			break;

		default:
			break;
		}
		
	}








private void PlayResource(String url) {
	if(timer!=null){
		timer.cancel();
		timer=null;
	}
	player = new MediaPlayer();
	player.setAudioStreamType(AudioManager.STREAM_MUSIC);
	try {
		player.setDataSource(url);
	} catch (Exception e) {
		e.printStackTrace();
	} 
	player.prepareAsync();
	//
	player.setOnPreparedListener(new OnPreparedListener() {
		@Override
		public void onPrepared(MediaPlayer mp) {
			// Now dismis progress dialog, Media palyer will start playing
			mp.start();
			musik_line.setProgress(0);
			
			int duration = mp.getDuration();
			
			final int period = duration / 1000;
			musik_line.setMax(period-1);
			task = new TimerTask() {

				@Override
				public void run() {
					musik_line.post(new Runnable() {
						@Override
						public void run() {
							if (player.isPlaying()) {
								progresss++;
								musik_line.setProgress(progresss);
								if(progresss==period){
									
									player.stop();
									player.release();
									progresss=0;
									play_audio.setBackgroundResource(R.drawable.btn_pause);
									
									PlayResource(audio.getmPrice());
									
									timeEnd.setText(getDurationLength());
								}
								
							}
						}
					});
				}
			};
			timer = new Timer();
			timer.schedule(task, 0, 1000);

		}
	});
	
}

public String count(int process){
	String number = null;
	String timePhut = null;
	String timeGiay = null;
	
	

		int phut = (int) (process / 60);
		if (phut < 10) {
			timePhut = "0" + String.valueOf(phut);
		} else {
			timePhut = String.valueOf(phut);
		}

		int giay = (int) (process - (phut * 60));
		if (giay < 10) {
			timeGiay = "0" + String.valueOf(giay);
		} else {
			timeGiay = String.valueOf(giay);
		}
		number = timePhut+ ":"+timeGiay;
	
	return number;
}

@Override
public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
	// TODO Auto-generated method stub
	if(progress!=progresss){
		if(player!=null && player.isPlaying()){
		progresss = progress;
		player.seekTo(progresss*1000);
		if((progresss*1000)==player.getDuration()){
			
			PlayResource(audio.getmPrice());
		}
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



}








