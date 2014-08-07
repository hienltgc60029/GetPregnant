package vn.theagency.fragment;

import java.util.Calendar;

import vn.theagency.getpregnant.Audios_Library;
import vn.theagency.getpregnant.Deine_Titel;
import vn.theagency.getpregnant.Musik;
import vn.theagency.getpregnant.R;
import vn.theagency.helper.Helper;
import vn.theagency.helper.Key;
import vn.theagency.layout.UI_Nohitaky;
import vn.theagency.objects.Audios;
import android.app.AlarmManager;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
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
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.Toast;

public class Fragment_Nohitaky extends Fragment implements OnClickListener,
		OnCheckedChangeListener {
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
	MusikAsyntask asyntask = null;
	int positionSong;
	
	public Fragment_Nohitaky(Audios pAudio){
		this.audio = pAudio;
	}

	public static Fragment_Nohitaky newInstance(Audios pAudio) {
		if (Fragment_Nohitaky._ins == null) {
			Fragment_Nohitaky._ins = new Fragment_Nohitaky(pAudio);
		}
		if(pAudio!=null){
			Fragment_Nohitaky._ins.audio = pAudio;
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
		this.initUITitle = this.mNohitaky.initUITitle(this.audio.getmImageURL(),this.audio.getmTitle(),this.audio.getmDecription());
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

		musikCheck = (CheckBox) this.wrapper
				.findViewById(Key.NOHITAKI_MusikCheck);
		musikCheck.setOnCheckedChangeListener(this);
		pdfCheck = (CheckBox) this.wrapper.findViewById(Key.NOHITAKI_PDFCheck);

		wecker = (ImageView) this.wrapper.findViewById(Key.NOHITAKI_Wecker);
		wecker.setOnClickListener(this);
		this.musik_line = (SeekBar) this.wrapper.findViewById(Key.SEEKBAR_LINE);

		changeBg = (Switch) this.wrapper.findViewById(Key.NOHITAKI_ChangeBg);
		changeBg.setChecked(true);
		changeBg.setOnCheckedChangeListener(this);
		back = (ImageView) this.wrapper.findViewById(Key.btn_back);
		back.setOnClickListener(this);
		play_audio = this.wrapper.findViewById(Key.PLAYAUDIO);
		play_audio.setOnClickListener(this);
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
			Toast.makeText(getActivity(),
					"This function isn't reponding. Coming soon",
					Toast.LENGTH_LONG).show();
			break;
		case Key.btn_back:
			try {
				msg.arg1 = 3;
				messenger.send(msg);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			break;
		case Key.PLAYAUDIO:
			//Play Audios
			if(player!=null){
				if(player.isPlaying()){
					play_audio.setBackgroundResource(R.drawable.btn_playaudio);
					player.pause();
					this.musik_line.setProgress(player.getCurrentPosition());
					asyntask.cancel(true);
				}else{
					play_audio.setBackgroundResource(R.drawable.btn_pause);
			//		player.start();
					asyntask.execute(positionSong);
				}
			}else{
				

				play_audio.setBackgroundResource(R.drawable.btn_pause);
				if (audio.mID.equalsIgnoreCase("1")) {
					positionSong = R.raw.wen;
					asyntask = new MusikAsyntask(this.musik_line);
					asyntask.execute(positionSong);
					
					
				} else if (audio.mID.equalsIgnoreCase("2")) {
					positionSong = R.raw.lie;
					asyntask = new MusikAsyntask(this.musik_line);
					asyntask.execute(positionSong);
					
				} else if (audio.mID.equalsIgnoreCase("3")) {
					positionSong = R.raw.zuru;
					asyntask = new MusikAsyntask(this.musik_line);
					asyntask.execute(R.raw.zuru);
					
				} else {
					positionSong = R.raw.gen;
					asyntask = new MusikAsyntask( this.musik_line);
					asyntask.execute(R.raw.gen);
					
				}
				
				
			}
			
			
			
			
			
			break;
		default:
			break;
		}
	}
	
	public void deleteFragment(){
		System.gc();
	}

	@Override
	public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
		// TODO Auto-generated method stub
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
	}
public class MusikAsyntask extends AsyncTask<Integer, Integer, Integer> {
		
		
		SeekBar line;

		


		public MusikAsyntask(SeekBar line) {
			super();
			
			this.line = line;
		}
		@Override
		protected Integer doInBackground(Integer... params) {
			try {
				if(player==null){
					player = MediaPlayer.create(getActivity(), R.raw.lie);
				}
				
					
					player.start();
					this.line.setMax(player.getDuration());
					for(int i = player.getCurrentPosition() ; i < player.getDuration();i++){
						publishProgress(i);
					}
				
				
				
			} catch (Exception ex) {
				Toast.makeText(getActivity(), "Musik cann't start",
						Toast.LENGTH_SHORT).show();
			}
			return null;
		}
		@Override
		protected void onProgressUpdate(Integer... values) {
		// TODO Auto-generated method stub
			
		super.onProgressUpdate(values[0]);
		Log.i("LTH", String.valueOf(values[0]));
		 this.line.setProgress(values[0]);
		}
	}
}
