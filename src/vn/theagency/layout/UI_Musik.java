package vn.theagency.layout;

import vn.theagency.getpregnantapplication.R;
import vn.theagency.helper.Helper;
import vn.theagency.helper.Key;
import android.animation.LayoutTransition;
import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;

public class UI_Musik {

	private Helper mHelper;
	private Context context;
	public SeekBar mBarVolume, mLine;

	int pauseWidth, pauseHeight, playWidth, playHeight, nextWidth, nextHeight,
			prevWidth, prevHeight, repeatWidth, repeatHeight, shufferWidth,
			shufferHeight, volumeWidth, volumeHeight;

	int headerHeight, seekbar_width, seekbar_height;
	int musik_bar_width,musik_bar_height;
	public static int musik_list_height,musik_list_width;
	int marginMusik;

	int LEFT_VOLUME;
	private static UI_Musik _ins;

	public SeekBar mDec, line;

	private UI_Musik(Context _context) {
		this.context = _context;
		this.mHelper = Helper.shareIns(this.context);
		initValues();
		size(this.context.getResources().getDimension(R.dimen.SIZE));
	}

	public static UI_Musik shareIns(Context _context) {
		if (UI_Musik._ins == null) {
			UI_Musik._ins = new UI_Musik(_context);
		}
		return UI_Musik._ins;
	}

	public void initValues() {
		LEFT_VOLUME = (int) mHelper.DpToPixel(10);
	}

	private void size(float a) {
		// TODO Auto-generated method stub

		seekbar_width = (int) this.mHelper.DpToPixel(20);
		seekbar_height = (int) this.mHelper.DpToPixel(20);
		//
		headerHeight = (int) (this.mHelper.DpToPixel(101)*a);
		playWidth = (int) (this.mHelper.DpToPixel(46)*a);
		playHeight = (int) (this.mHelper.DpToPixel(46)*a);
		nextWidth = (int) (this.mHelper.DpToPixel(88)*a);
		nextHeight = (int) (this.mHelper.DpToPixel(45)*a);
		prevWidth = (int) (this.mHelper.DpToPixel(88)*a);
		prevHeight = (int) (this.mHelper.DpToPixel(45)*a);
		repeatWidth = (int) this.mHelper.DpToPixel(29);
		repeatHeight = (int) this.mHelper.DpToPixel(32);
		shufferWidth = (int) this.mHelper.DpToPixel(32);
		shufferHeight = (int) this.mHelper.DpToPixel(33);
		volumeWidth = (int) this.mHelper.DpToPixel(32);
		volumeHeight = (int) this.mHelper.DpToPixel(32);
		pauseWidth = (int) this.mHelper.DpToPixel(46);
		pauseHeight = (int) this.mHelper.DpToPixel(46);
		musik_bar_width = (int) this.mHelper.DpToPixel(304);
		musik_bar_height = (int) this.mHelper.DpToPixel(54);
		musik_list_width = (int) this.mHelper.DpToPixel(304); 
		
		marginMusik =(int) this.mHelper.DpToPixel(28);
		musik_list_height =  (int) (this.mHelper.getAppHeight()-musik_bar_height-(marginMusik + headerHeight)-playHeight-this.mHelper.DpToPixel(30));
	}

	public View initUIBackGround() {
		View v = new View(this.context);
		FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(
				FrameLayout.LayoutParams.MATCH_PARENT,
				FrameLayout.LayoutParams.MATCH_PARENT);
		params.topMargin = headerHeight;
		v.setLayoutParams(params);
		v.setBackgroundResource(R.drawable.bg_musik);
		return v;
	}

	public View initUIHeader() {
		View v = new View(this.context);
		FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(
				FrameLayout.LayoutParams.MATCH_PARENT, headerHeight);
		v.setLayoutParams(params);
		v.setBackgroundResource(R.drawable.musik_header);
		v.setId(Key.HEADER);
		return v;
	}

	public RelativeLayout initUIMusicBar() {
		RelativeLayout main = new RelativeLayout(this.context);
		
		
		main.setBackgroundResource(R.drawable.bg_music_tools);
		
		View shuffer = new View(this.context);
		shuffer.setBackgroundResource(R.drawable.btn_shuffer);
		shuffer.setId(Key.SHUFFER);
		//
		mLine = new SeekBar(this.context);
		mLine.setProgressDrawable(this.context.getResources().getDrawable(
				R.drawable.seekbar_process));
		mLine.setThumb(this.context.getResources().getDrawable(
				R.drawable.thumbler_small));
		mLine.setId(Key.SEEKBAR_LINE);
		mLine.setVisibility(View.VISIBLE);

		RelativeLayout.LayoutParams volumePara = new RelativeLayout.LayoutParams(
				volumeWidth, volumeHeight);
		volumePara.leftMargin = (int)marginMusik/2;
		volumePara.addRule(RelativeLayout.ALIGN_PARENT_LEFT,
				RelativeLayout.TRUE);
		volumePara.addRule(RelativeLayout.CENTER_VERTICAL, RelativeLayout.TRUE);

		View volume = new View(this.context);
		volume.setBackgroundResource(R.drawable.btn_volume);
		volume.setId(Key.VOLUME);
		//
		
		TextView timeStart = new TextView(this.context);
		timeStart.setAlpha(0.4f);
		timeStart.setText("00:00");
		timeStart.setTextColor(Color.parseColor("#fffac2"));
	//	timeStart.setGravity(Gravity.RIGHT);
		timeStart.setId(Key.START);	
//
		RelativeLayout.LayoutParams barVolumePara = new RelativeLayout.LayoutParams(
				RelativeLayout.LayoutParams.MATCH_PARENT,
				RelativeLayout.LayoutParams.WRAP_CONTENT);
		barVolumePara.addRule(RelativeLayout.RIGHT_OF, volume.getId());
		barVolumePara.addRule(RelativeLayout.LEFT_OF, shuffer.getId());
		barVolumePara.addRule(RelativeLayout.CENTER_VERTICAL, RelativeLayout.TRUE);

		mBarVolume = new SeekBar(this.context);
		mBarVolume.setProgressDrawable(this.context.getResources().getDrawable(
				R.drawable.seekbar_volume));
		mBarVolume.setThumb(this.context.getResources().getDrawable(
				R.drawable.thumbler_small));
		mBarVolume.setId(Key.SEEKBAR_VOLUME);
		mBarVolume.setVisibility(View.GONE);
		//Line Para
		RelativeLayout.LayoutParams LinePara = new RelativeLayout.LayoutParams(
				RelativeLayout.LayoutParams.MATCH_PARENT,
				RelativeLayout.LayoutParams.WRAP_CONTENT);
		LinePara.addRule(RelativeLayout.RIGHT_OF, volume.getId());
		LinePara.addRule(RelativeLayout.LEFT_OF, shuffer.getId());
		LinePara.addRule(RelativeLayout.ABOVE, timeStart.getId());
	//	LinePara.topMargin = (int) (musik_bar_height-mHelper.DpToPixel(50));

		
		//

		RelativeLayout.LayoutParams startPara = new RelativeLayout.LayoutParams(
				RelativeLayout.LayoutParams.WRAP_CONTENT,
				RelativeLayout.LayoutParams.WRAP_CONTENT);
		startPara.bottomMargin = (int) mHelper.DpToPixel(5);
		startPara.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM,
				RelativeLayout.TRUE);
		startPara.leftMargin = (int)marginMusik/2;
		startPara.addRule(RelativeLayout.ALIGN_LEFT, mLine.getId());
	//	startPara.addRule(RelativeLayout.RIGHT_OF, volume.getId());
	
		//startPara.addRule(RelativeLayout.ALIGN_PARENT_LEFT, RelativeLayout.TRUE);

		
		
		//
		TextView timeEnd = new TextView(this.context);
		RelativeLayout.LayoutParams endPara = new RelativeLayout.LayoutParams(
				RelativeLayout.LayoutParams.WRAP_CONTENT,
				RelativeLayout.LayoutParams.WRAP_CONTENT);
		endPara.bottomMargin = (int) mHelper.DpToPixel(5);
		endPara.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM, RelativeLayout.TRUE);
		endPara.addRule(RelativeLayout.LEFT_OF, shuffer.getId());
		endPara.rightMargin = (int)marginMusik/2;
		
		timeEnd.setText("00:00");
		timeEnd.setAlpha(0.4f);
		timeEnd.setTextColor(Color.parseColor("#fffac2"));
	//	timeEnd.setGravity(Gravity.LEFT);
		timeEnd.setId(Key.End);
		//
		RelativeLayout.LayoutParams repeatPara = new RelativeLayout.LayoutParams(
				repeatWidth, repeatHeight);
		repeatPara.rightMargin = (int)marginMusik/2;
		repeatPara.addRule(RelativeLayout.ALIGN_PARENT_RIGHT,
				RelativeLayout.TRUE);
		repeatPara.addRule(RelativeLayout.CENTER_VERTICAL, RelativeLayout.TRUE);

		View repeat = new View(this.context);
		repeat.setBackgroundResource(R.drawable.btn_repeat);
		repeat.setId(Key.REPEAT);
		//

		RelativeLayout.LayoutParams shufferPara = new RelativeLayout.LayoutParams(
				shufferWidth, shufferHeight);

		shufferPara.addRule(RelativeLayout.LEFT_OF, repeat.getId());
		shufferPara
				.addRule(RelativeLayout.CENTER_VERTICAL, RelativeLayout.TRUE);
		shufferPara.rightMargin = LEFT_VOLUME;

		//

		//

		FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(
				musik_bar_width, musik_bar_height);
		
		params.setMargins(0, (marginMusik + headerHeight), 0, 0);
		params.gravity = Gravity.CENTER_HORIZONTAL;

		main.setLayoutParams(params);
		main.addView(volume, volumePara);
		main.addView(mBarVolume, barVolumePara);
		main.addView(mLine, LinePara);
		main.addView(timeStart, startPara);
		main.addView(timeEnd, endPara);
		main.addView(repeat, repeatPara);
		main.addView(shuffer, shufferPara);

		return main;
	}

	public FrameLayout initUIBottom() {
		FrameLayout main = new FrameLayout(this.context);
		
		FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(
				FrameLayout.LayoutParams.MATCH_PARENT,
				FrameLayout.LayoutParams.WRAP_CONTENT);
		params.gravity = Gravity.BOTTOM;
		params.bottomMargin = (int) this.mHelper.DpToPixel(15);
		main.setLayoutParams(params);
		//
		View prev = new View(this.context);
		FrameLayout.LayoutParams prevPara = new FrameLayout.LayoutParams(
				prevWidth, prevHeight);
		prevPara.gravity = Gravity.LEFT;
		prevPara.gravity = Gravity.CENTER_VERTICAL;
		prevPara.leftMargin = (int) this.mHelper.DpToPixel(28);
		prev.setLayoutParams(prevPara);
		prev.setBackgroundResource(R.drawable.btn_prev);
		prev.setId(Key.PREVAUDIO);
		//
		View play = new View(this.context);
		FrameLayout.LayoutParams playPara = new FrameLayout.LayoutParams(
				playWidth, playHeight);
		playPara.gravity = Gravity.CENTER;
		play.setLayoutParams(playPara);
		play.setBackgroundResource(R.drawable.btn_musikplay);
		play.setId(Key.PLAYAUDIO);

		View pause = new View(this.context);
		FrameLayout.LayoutParams pausePara = new FrameLayout.LayoutParams(
				playWidth, playHeight);
		pausePara.gravity = Gravity.CENTER;
		pause.setLayoutParams(pausePara);
		pause.setBackgroundResource(R.drawable.btn_musikpause);
		pause.setId(Key.PAUSEAUDIO);
		pause.setVisibility(View.GONE);
		//
		View next = new View(this.context);
		FrameLayout.LayoutParams nextPara = new FrameLayout.LayoutParams(
				prevWidth, prevHeight);
		nextPara.rightMargin = (int) this.mHelper.DpToPixel(28);
		nextPara.gravity = Gravity.RIGHT | Gravity.CENTER_VERTICAL;
		next.setLayoutParams(nextPara);
		next.setBackgroundResource(R.drawable.btn_next);
		next.setId(Key.NEXTAUDIO);

		main.addView(prev);
		main.addView(play);
		main.addView(next);
		main.addView(pause);
		return main;

	}

	/**
	 * 
	 * @return
	 */
	public ListView initUIListView() {
		

		ListView view = new ListView(this.context);
		view.setBackgroundResource(R.drawable.bg_music_list);
		int margin = headerHeight+musik_bar_height+marginMusik;
		FrameLayout.LayoutParams viewPara = new FrameLayout.LayoutParams(
				musik_list_width, musik_list_height);
		viewPara.setMargins(0, margin, 0, 0);
		viewPara.gravity = Gravity.CENTER_HORIZONTAL;
		view.setLayoutParams(viewPara);
		view.setId(Key.LISTVIEW_LIBRARY);
		

		return view;
	}
	
	

}
