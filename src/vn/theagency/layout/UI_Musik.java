package vn.theagency.layout;

import vn.theagency.getpregnant.R;
import vn.theagency.helper.Key;
import vn.theagency.helper.Helper;
import vn.theagency.helper.Values;
import android.animation.LayoutTransition;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.view.Gravity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.FrameLayout.LayoutParams;

public class UI_Musik {

	private Helper mHelper;
	private Values mValues;
	private Context context;
	public SeekBar mBarVolume, mLine;

	int pauseWidth, pauseHeight, playWidth, playHeight, nextWidth, nextHeight,
			prevWidth, prevHeight, repeatWidth, repeatHeight, shufferWidth,
			shufferHeight, volumeWidth, volumeHeight;

	int headerHeight, seekbar_width, seekbar_height;

	int LEFT_VOLUME;
	private static UI_Musik _ins;

	public SeekBar mDec, line;

	private UI_Musik(Context _context) {
		this.context = _context;
		this.mHelper = Helper.shareIns(this.context);
		initValues();
		size(1);
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
		headerHeight = (int) this.mHelper.DpToPixel(101);
		playWidth = (int) this.mHelper.DpToPixel(46);
		playHeight = (int) this.mHelper.DpToPixel(46);
		nextWidth = (int) this.mHelper.DpToPixel(88);
		nextHeight = (int) this.mHelper.DpToPixel(45);
		prevWidth = (int) this.mHelper.DpToPixel(88);
		prevHeight = (int) this.mHelper.DpToPixel(45);
		repeatWidth = (int) this.mHelper.DpToPixel(29);
		repeatHeight = (int) this.mHelper.DpToPixel(32);
		shufferWidth = (int) this.mHelper.DpToPixel(32);
		shufferHeight = (int) this.mHelper.DpToPixel(33);
		volumeWidth = (int) this.mHelper.DpToPixel(32);
		volumeHeight = (int) this.mHelper.DpToPixel(32);
		pauseWidth = (int) this.mHelper.DpToPixel(46);
		pauseHeight = (int) this.mHelper.DpToPixel(46);

	}

	public View initUIBackGround() {
		View v = new View(this.context);
		FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(
				FrameLayout.LayoutParams.MATCH_PARENT,
				FrameLayout.LayoutParams.MATCH_PARENT);
		params.topMargin = headerHeight;
		v.setLayoutParams(params);
		v.setBackgroundResource(R.drawable.bg_9);
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
		LayoutTransition lt = new LayoutTransition();
		lt.disableTransitionType(LayoutTransition.DISAPPEARING);
		main.setLayoutTransition(lt);
		View shuffer = new View(this.context);
		shuffer.setBackgroundResource(R.drawable.btn_shuffer);
		shuffer.setId(Key.SHUFFER);
		//
		mLine = new SeekBar(this.context);
		mLine.setProgressDrawable(this.context.getResources().getDrawable(
				R.drawable.seekbar_process));
		mLine.setThumb(this.context.getResources().getDrawable(
				R.drawable.seekbar_point));
		mLine.setId(Key.SEEKBAR_LINE);
		mLine.setVisibility(View.VISIBLE);

		RelativeLayout.LayoutParams volumePara = new RelativeLayout.LayoutParams(
				volumeWidth, volumeHeight);
		volumePara.addRule(RelativeLayout.ALIGN_PARENT_LEFT,
				RelativeLayout.TRUE);
		volumePara.addRule(RelativeLayout.CENTER_VERTICAL, RelativeLayout.TRUE);

		View volume = new View(this.context);
		volume.setBackgroundResource(R.drawable.btn_volume);
		volume.setId(Key.VOLUME);
		//

		RelativeLayout.LayoutParams barVolumePara = new RelativeLayout.LayoutParams(
				RelativeLayout.LayoutParams.MATCH_PARENT,
				RelativeLayout.LayoutParams.WRAP_CONTENT);
		barVolumePara.topMargin = (int) (2 * LEFT_VOLUME / 3);

		barVolumePara.addRule(RelativeLayout.RIGHT_OF, volume.getId());
		barVolumePara.addRule(RelativeLayout.LEFT_OF, shuffer.getId());
		barVolumePara.addRule(RelativeLayout.ALIGN_PARENT_TOP,
				RelativeLayout.TRUE);

		mBarVolume = new SeekBar(this.context);
		mBarVolume.setProgressDrawable(this.context.getResources().getDrawable(
				R.drawable.seekbar_volume));
		mBarVolume.setThumb(this.context.getResources().getDrawable(
				R.drawable.seekbar_point));
		mBarVolume.setId(Key.SEEKBAR_VOLUME);
		mBarVolume.setVisibility(View.GONE);
		//

		RelativeLayout.LayoutParams startPara = new RelativeLayout.LayoutParams(
				RelativeLayout.LayoutParams.WRAP_CONTENT,
				RelativeLayout.LayoutParams.WRAP_CONTENT);
		startPara.leftMargin = LEFT_VOLUME + 20;
		startPara.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM,
				RelativeLayout.TRUE);
		startPara.addRule(RelativeLayout.RIGHT_OF, volume.getId());

		TextView timeStart = new TextView(this.context);
		timeStart.setAlpha(0.5f);
		timeStart.setText("25:58");
		//
		TextView timeEnd = new TextView(this.context);
		RelativeLayout.LayoutParams endPara = new RelativeLayout.LayoutParams(
				RelativeLayout.LayoutParams.WRAP_CONTENT,
				RelativeLayout.LayoutParams.WRAP_CONTENT);
		endPara.rightMargin = LEFT_VOLUME + 20;
		endPara.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM, RelativeLayout.TRUE);
		endPara.addRule(RelativeLayout.LEFT_OF, shuffer.getId());

		timeEnd.setText("57:30");
		timeEnd.setAlpha(0.5f);
		//
		RelativeLayout.LayoutParams repeatPara = new RelativeLayout.LayoutParams(
				repeatWidth, repeatHeight);
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
				FrameLayout.LayoutParams.MATCH_PARENT, 80);
		int margin = (int) (this.mHelper.getAppWidth() / 10);
		params.setMargins(margin, (margin + headerHeight), margin, 0);

		main.setLayoutParams(params);
		main.addView(volume, volumePara);
		main.addView(mBarVolume, barVolumePara);
		main.addView(mLine, barVolumePara);
		main.addView(timeStart, startPara);
		main.addView(timeEnd, endPara);
		main.addView(repeat, repeatPara);
		main.addView(shuffer, shufferPara);

		return main;
	}

	public FrameLayout initUIBottom() {
		FrameLayout main = new FrameLayout(this.context);
		int w = (int) (mHelper.getAppWidth() - 112);
		FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(
				FrameLayout.LayoutParams.MATCH_PARENT,
				(int) (mHelper.getAppHeight() / 6));
		params.gravity = Gravity.BOTTOM;
		main.setLayoutParams(params);
		//
		View prev = new View(this.context);
		FrameLayout.LayoutParams prevPara = new FrameLayout.LayoutParams(
				prevWidth, prevHeight);
		prevPara.gravity = Gravity.LEFT;
		prevPara.gravity = Gravity.CENTER_VERTICAL;
		prevPara.leftMargin = 56;
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
		nextPara.rightMargin = 56;
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
	public FrameLayout initUIListView() {
		FrameLayout main = new FrameLayout(this.context);
		main.setBackgroundColor(Color.RED);
		FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(
				FrameLayout.LayoutParams.MATCH_PARENT,
				FrameLayout.LayoutParams.WRAP_CONTENT);
		int margin = (int) (this.mHelper.getAppWidth() / 10);
		params.setMargins(margin, (int) this.mHelper.DpToPixel(174), margin, 0);

		params.gravity = Gravity.CENTER_HORIZONTAL;
		main.setLayoutParams(params);

		ListView view = new ListView(this.context);
		//int h = (int) (55 * mHelper.getAppHeight() / 100 + 8);
		FrameLayout.LayoutParams viewPara = new FrameLayout.LayoutParams(
				FrameLayout.LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
		viewPara.setMargins(margin, (int) this.mHelper.DpToPixel(174), margin, 0);
		view.setLayoutParams(viewPara);
		view.setId(Key.LISTVIEW_LIBRARY);
		main.addView(view);

		return main;
	}
	
	

}
