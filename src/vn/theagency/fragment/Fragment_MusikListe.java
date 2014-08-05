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
	int curVolume = 10;
	Messenger messenger;
	Message mg;
	

	private static Fragment_MusikListe _ins;

	

	public static Fragment_MusikListe newInstance() {
		if (Fragment_MusikListe._ins == null) {
			Fragment_MusikListe._ins = new Fragment_MusikListe();
		}

		return Fragment_MusikListe._ins;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		this.mHelper = Helper.shareIns(getActivity());
		this.mMusik = UI_Musik.shareIns(getActivity());

		this.initUIHeader = this.mMusik.initUIHeader();
		this.initUIBackGround = this.mMusik.initUIBackGround();
		this.initUIMusicBar = this.mMusik.initUIMusicBar();
		this.initUIBottom = this.mMusik.initUIBottom();
		this.initUIListView = this.mMusik.initUIListView();

		initUI();
		this.mStore = Store.shareIns(getActivity(), arr);
		arr = this.mStore.getMusikSlite();
		
		Musik musik = (Musik) getActivity();
		messenger = musik.getMessenger();
		
		

		mg = new Message();
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
		prev.setOnClickListener(this);
		next = wrapper.findViewById(Key.NEXTAUDIO);
		next.setOnClickListener(this);
		play = wrapper.findViewById(Key.PLAYAUDIO);
		play.setOnClickListener(this);
		pause = wrapper.findViewById(Key.PAUSEAUDIO);
		pause.setOnClickListener(this);
		volume = wrapper.findViewById(Key.VOLUME);
		volume.setOnClickListener(this);
		repeat = wrapper.findViewById(Key.REPEAT);
		repeat.setOnClickListener(this);
		shuffer = wrapper.findViewById(Key.SHUFFER);
		shuffer.setOnClickListener(this);
		header = wrapper.findViewById(Key.HEADER);
		header.setOnClickListener(this);

		// ///////////////////////

		seekLine = (SeekBar) wrapper.findViewById(Key.SEEKBAR_LINE);

		// seekLine.setVisibility(View.GONE);
		seekVolume = (SeekBar) wrapper.findViewById(Key.SEEKBAR_VOLUME);

		list = (ListView) wrapper.findViewById(Key.LISTVIEW_LIBRARY);
		
		
		
		adapter = new Musik_Adapter(arr, R.layout.items_musik, getActivity());
		

		

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

		list.setOnItemClickListener(this);

		mPosition = 0;
		
			play.setVisibility(View.VISIBLE);
			play.setEnabled(false);
			pause.setVisibility(View.GONE);
		
		
	
	}

	public void setVolume(int Max) {
		seekVolume.setMax(mMaxVolume);
		seekVolume.setProgress(curVolume);
		media.setVolume((float) curVolume / mMaxVolume, (float) curVolume
				/ mMaxVolume);
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

			MusikAsyntask asyntask = new MusikAsyntask();
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
				MusikAsyntask asyntask = new MusikAsyntask();
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
			} else {
				this.mMusik.mBarVolume.setVisibility(View.GONE);
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
			media.setVolume((float) progress / mMaxVolume, (float) progress
					/ mMaxVolume);
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

	
	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		media.release();
	}

	public class MusikAsyntask extends AsyncTask<String, Integer, Integer> {
		UI_Musik mMusik = UI_Musik.shareIns(getActivity());

		@Override
		protected Integer doInBackground(String... params) {
			try {
				if (media!=null) {
					media.stop();
					media.release();
				}
				media = MediaPlayer.create(getActivity(), Uri.parse(params[0]));

				media.start();
				setVolume(mMaxVolume);
		//		this.mMusik.mLine.setMax(media.getDuration());
				
				/*media.setOnCompletionListener(new OnCompletionListener() {

					@Override
					public void onCompletion(MediaPlayer mp) {
						// TODO Auto-generated method stub
						if (mPosition < arr.size()) {
							mPosition++;
							MusikAsyntask asyntask = new MusikAsyntask();
							asyntask.execute(arr.get(mPosition).getmURL());
						}

					}
				});
*/
			} catch (Exception ex) {
				Toast.makeText(getActivity(), "Musik cann't start",
						Toast.LENGTH_SHORT).show();
			}
			return null;
		}

		

	}



}
