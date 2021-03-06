package vn.theagency.layout;

import vn.theagency.getpregnantapplication.R;
import vn.theagency.helper.Helper;
import vn.theagency.helper.Key;
import android.app.ActionBar.LayoutParams;
import android.content.Context;
import android.graphics.Typeface;
import android.view.Gravity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;

public class UI_Deneine {

	private Helper mHelper;
	private Context context;

	private static UI_Deneine _ins;
	//
	int bottom_height;
	int header_width;
	public static int header_height;
	int mTitleSize, mDecSize;
	int play_width, play_height;
	int seekbar_width, seekbar_height;
	int btn_back;
	public static int bottom, bottom_down;

	private UI_Deneine(Context _context) {
		this.context = _context;
		this.mHelper = Helper.shareIns(this.context);
		size(context.getResources().getDimension(R.dimen.SIZE));
	}

	public static UI_Deneine shareIns(Context _context) {
		if (UI_Deneine._ins == null) {
			UI_Deneine._ins = new UI_Deneine(_context);
		}
		return UI_Deneine._ins;
	}

	public void size(float a) {
		//
		btn_back = (int) this.context.getResources().getDimension(R.dimen.BACK);

		//
		header_width = (int) mHelper.DpToPixel(360);
		header_height = (int) mHelper.getAppHeight() / 5;
		mTitleSize = (int) this.context.getResources().getDimension(R.dimen.TITLE);
		mDecSize = (int) this.context.getResources().getDimension(R.dimen.DEC);
		play_width = (int) (mHelper.DpToPixel(27)*a);
		play_height = (int) (mHelper.DpToPixel(20)*a);
		seekbar_width = (int) (mHelper.getAppWidth()/2);
		seekbar_height = (int) (mHelper.DpToPixel(8));
		bottom_height = (int) (this.mHelper.DpToPixel(92)*a);
		bottom = (int) (this.mHelper.DpToPixel(52)*a);
		bottom_down = (int) (this.mHelper.DpToPixel(40)*a);
		

	}

	public FrameLayout initUIHeader() {
		FrameLayout background = new FrameLayout(this.context);
		FrameLayout.LayoutParams backgroundPara = new FrameLayout.LayoutParams(
				RelativeLayout.LayoutParams.MATCH_PARENT,
				RelativeLayout.LayoutParams.MATCH_PARENT);
		background.setLayoutParams(backgroundPara);

		FrameLayout layout = new FrameLayout(this.context);
		FrameLayout.LayoutParams layoutPara = new FrameLayout.LayoutParams(
				FrameLayout.LayoutParams.MATCH_PARENT, header_height);
		layout.setLayoutParams(layoutPara);
		layout.setBackgroundResource(R.drawable.header_deine_titel);
		// button back
		View view = new View(this.context);
		view.setId(Key.btn_back);
		FrameLayout.LayoutParams viewPara = new FrameLayout.LayoutParams(
				btn_back, btn_back);
		viewPara.setMargins((int)mHelper.DpToPixel(12),(int) mHelper.DpToPixel(12), 0, 0);
		view.setLayoutParams(viewPara);
		view.setBackgroundResource(R.drawable.btn_back);
		//

		// framelayout bg
		FrameLayout bg = new FrameLayout(this.context);
		FrameLayout.LayoutParams bgPara = new FrameLayout.LayoutParams(
				FrameLayout.LayoutParams.MATCH_PARENT,
				FrameLayout.LayoutParams.MATCH_PARENT);
		bgPara.topMargin = header_height;
		bg.setLayoutParams(bgPara);
		bg.setBackgroundResource(R.drawable.bg_list);

		layout.addView(view);
		background.addView(layout);
		background.addView(bg);

		return background;
	}

	public void initUITitle() {
		TextView mTitle = new TextView(this.context);
		FrameLayout.LayoutParams titlePara = new FrameLayout.LayoutParams(
				FrameLayout.LayoutParams.WRAP_CONTENT,
				FrameLayout.LayoutParams.WRAP_CONTENT);
		mTitle.setLayoutParams(titlePara);
		mTitle.setTextSize(mTitleSize);
	}

	public ListView initUIList() {
		ListView view = new ListView(this.context);
		int height = (int) (mHelper.getAppHeight() - (bottom_height + header_height));
		FrameLayout.LayoutParams viewPara = new FrameLayout.LayoutParams(
				LayoutParams.MATCH_PARENT, height);
		viewPara.topMargin = header_height;
		view.setLayoutParams(viewPara);
		view.setId(Key.LISTVIEW_LIBRARY);
		return view;
	}

	public View initUIBottom() {

		int hieght = (int) (mHelper.getAppHeight() - bottom);
		View view = new View(this.context);
		FrameLayout.LayoutParams paraHeader = new FrameLayout.LayoutParams(
				FrameLayout.LayoutParams.MATCH_PARENT,
				bottom);
		paraHeader.topMargin = hieght;
		view.setLayoutParams(paraHeader);
		view.setBackgroundResource(R.drawable.btn_deine_samlung);
		view.setId(Key.btn_deine_musik);
		return view;
	}

	public ImageView initUIDown() {

		int hieght = (int) (mHelper.getAppHeight() - bottom - (bottom_down/2)-10);
		ImageView view = new ImageView(this.context);
		FrameLayout.LayoutParams paraHeader = new FrameLayout.LayoutParams(
				FrameLayout.LayoutParams.WRAP_CONTENT,
				FrameLayout.LayoutParams.WRAP_CONTENT);
		paraHeader.topMargin = hieght;
		paraHeader.gravity = Gravity.CENTER_HORIZONTAL;
		view.setLayoutParams(paraHeader);
		view.setBackgroundResource(R.drawable.btn_more);
		view.setAlpha(0.3f);
		return view;
	}

	public FrameLayout initUIText() {
		FrameLayout frameLayout = new FrameLayout(this.context);
		FrameLayout.LayoutParams framePara = new FrameLayout.LayoutParams(
				FrameLayout.LayoutParams.WRAP_CONTENT,
				FrameLayout.LayoutParams.WRAP_CONTENT);
		framePara.topMargin = (int) (header_height / 7);

		frameLayout.setLayoutParams(framePara);

		LinearLayout layout = new LinearLayout(this.context);
		LinearLayout.LayoutParams layoutPara = new LinearLayout.LayoutParams(
				LinearLayout.LayoutParams.WRAP_CONTENT,
				LinearLayout.LayoutParams.WRAP_CONTENT);

		layout.setLayoutParams(layoutPara);
		layout.setOrientation(LinearLayout.VERTICAL);
		layout.setGravity(Gravity.CENTER);

		// Title
		TextView mTitle = new TextView(this.context);
		LinearLayout.LayoutParams titlePara = new LinearLayout.LayoutParams(
				LinearLayout.LayoutParams.WRAP_CONTENT,
				LinearLayout.LayoutParams.WRAP_CONTENT);
		mTitle.setLayoutParams(titlePara);
		mTitle.setTextSize(mTitleSize);
		mTitle.setText("DEINE TITEL");
		mTitle.setGravity(Gravity.CENTER_HORIZONTAL);
		Typeface type = Typeface.createFromAsset(this.context.getAssets(),
				"museosans_300.otf");
		mTitle.setTypeface(type);
		mTitle.setAlpha(0.8f);
		// Decription
		TextView mDec = new TextView(this.context);
		LinearLayout.LayoutParams decPara = new LinearLayout.LayoutParams(
				LinearLayout.LayoutParams.WRAP_CONTENT,
				LinearLayout.LayoutParams.WRAP_CONTENT);

		mDec.setLayoutParams(titlePara);
		mDec.setTextSize(mDecSize);
		mDec.setText("Omnihit ad esti in cusandi ut aut arumque asint rempermaturui consent");
		mDec.setGravity(Gravity.CENTER_HORIZONTAL);
		mDec.setAlpha(0.8f);
		//
		layout.addView(mTitle);
		layout.addView(mDec);
		frameLayout.addView(layout);

		return frameLayout;
	}

	public FrameLayout initUIDeineSamlung() {
		FrameLayout frameLayout = new FrameLayout(this.context);
		FrameLayout.LayoutParams framePara = new FrameLayout.LayoutParams(
				FrameLayout.LayoutParams.WRAP_CONTENT,
				FrameLayout.LayoutParams.WRAP_CONTENT);
		framePara.topMargin = (int) (header_height / 3);

		frameLayout.setLayoutParams(framePara);

		LinearLayout layout = new LinearLayout(this.context);
		LinearLayout.LayoutParams layoutPara = new LinearLayout.LayoutParams(
				LinearLayout.LayoutParams.MATCH_PARENT,
				(int) (2 * header_height / 3));

		layoutPara.gravity = Gravity.CENTER;
		layout.setLayoutParams(layoutPara);
		layout.setOrientation(LinearLayout.VERTICAL);
		layout.setGravity(Gravity.CENTER);
		layout.setPadding(0, 0, 0, 10);

		// Title
		TextView mTitle = new TextView(this.context);
		LinearLayout.LayoutParams titlePara = new LinearLayout.LayoutParams(
				LinearLayout.LayoutParams.WRAP_CONTENT,
				LinearLayout.LayoutParams.WRAP_CONTENT);
		titlePara.bottomMargin = 20;
		mTitle.setLayoutParams(titlePara);
		mTitle.setTextSize(mTitleSize);
		Typeface type = Typeface.createFromAsset(this.context.getAssets(),
				"museosans_300.otf");
		mTitle.setTypeface(type);
		mTitle.setText("DEINE SAMMLUNG");
		mTitle.setGravity(Gravity.CENTER_HORIZONTAL);
		mTitle.setAlpha(0.8f);
		// play audios
		FrameLayout playLayout = new FrameLayout(this.context);
		FrameLayout.LayoutParams paraPlay = new FrameLayout.LayoutParams(
				(int) mHelper.getAppWidth(),
				FrameLayout.LayoutParams.WRAP_CONTENT);

		playLayout.setLayoutParams(paraPlay);

		View view = new View(this.context);
		FrameLayout.LayoutParams viewPara = new FrameLayout.LayoutParams(
				play_width, play_height);
		viewPara.leftMargin = (int) mHelper.DpToPixel(30);
		viewPara.gravity = Gravity.CENTER_VERTICAL;
		view.setLayoutParams(viewPara);
		view.setId(Key.PLAY);
		view.setBackgroundResource(R.drawable.btn_playaudio);

		TextView timeStart = new TextView(this.context);
		FrameLayout.LayoutParams startPara = new FrameLayout.LayoutParams(
				FrameLayout.LayoutParams.WRAP_CONTENT,
				FrameLayout.LayoutParams.WRAP_CONTENT);
		startPara.leftMargin = (int) mHelper.DpToPixel(70);
		startPara.gravity = Gravity.CENTER_VERTICAL;
		timeStart.setLayoutParams(startPara);
		timeStart.setText("25:58");
		timeStart.setId(Key.START);

		TextView timeEnd = new TextView(this.context);
		FrameLayout.LayoutParams endPara = new FrameLayout.LayoutParams(
				FrameLayout.LayoutParams.WRAP_CONTENT,
				FrameLayout.LayoutParams.WRAP_CONTENT);
		endPara.leftMargin = (int) (mHelper.getAppWidth() - mHelper.DpToPixel(60));
		endPara.gravity = Gravity.CENTER_VERTICAL;
		timeEnd.setLayoutParams(endPara);
		timeEnd.setText("57:30");
		timeEnd.setId(Key.End);

		SeekBar mDec = new SeekBar(this.context);
		FrameLayout.LayoutParams decPara = new FrameLayout.LayoutParams(
				(int) (mHelper.getAppWidth()-mHelper.DpToPixel(170)), FrameLayout.LayoutParams.WRAP_CONTENT);
		decPara.leftMargin = (int) (mHelper.DpToPixel(110));
		decPara.gravity = Gravity.CENTER_VERTICAL;
		mDec.setLayoutParams(decPara);
		mDec.setProgressDrawable(this.context.getResources().getDrawable(
				R.drawable.seekbar_process));
		mDec.setThumb(this.context.getResources().getDrawable(
				R.drawable.thumbler_small));
		mDec.setId(Key.SEEKBAR_LINE);

		playLayout.addView(timeStart);
		playLayout.addView(timeEnd);
		playLayout.addView(view);
		playLayout.addView(mDec);

		//
		layout.addView(mTitle);
		layout.addView(playLayout);
		frameLayout.addView(layout);

		return frameLayout;
	}

	public View initUISammLungBottom() {
		
		
		int hieght = (int) (mHelper.getAppHeight() - bottom);
		View view = new View(this.context);
		FrameLayout.LayoutParams paraHeader = new FrameLayout.LayoutParams(
				FrameLayout.LayoutParams.MATCH_PARENT,
				bottom);
		paraHeader.topMargin = hieght;
		view.setLayoutParams(paraHeader);
		view.setBackgroundResource(R.drawable.btn_hinzu);
		view.setId(Key.btn_deine_musik);
		return view;
	}
}
