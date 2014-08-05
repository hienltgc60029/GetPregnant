package vn.theagency.layout;

import vn.theagency.getpregnant.R;
import vn.theagency.helper.Helper;
import vn.theagency.helper.Key;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.CheckBox;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;

public class UI_Nohitaky {

	private Helper mHelper;
	private Context context;

	private static UI_Nohitaky _ins;

	int musik_height;
	int musik_width;
	int btn_back;
	

	int bottomHeight;
	int checkHeight;
	int checkWidth;
	int swHeight;
	int swWidth;
	int playHeight;
	int playWidth;
	int seekHeight;
	int seekWidth;
	int avartaHeight, avartaWidth;
	int mTitleSize,mDecSize;

	int DP_10;

	private UI_Nohitaky(Context _context) {
		this.context = _context;
		this.mHelper = Helper.shareIns(this.context);
		size();
		Log.i("LTH", "HH1");
	}

	public static UI_Nohitaky shareIns(Context _context) {
		if (UI_Nohitaky._ins == null) {
			Log.i("LTH", "HH");
			UI_Nohitaky._ins = new UI_Nohitaky(_context);

		}
		return UI_Nohitaky._ins;
	}

	private void size() {
	
		musik_height =(int) this.mHelper.DpToPixel(73);
		musik_width = (int)this.mHelper.DpToPixel(360);
		

		
		btn_back = (int)this.mHelper.DpToPixel(30);
		

		
		bottomHeight =(int) this.mHelper.DpToPixel(293);
	

		
		checkHeight = (int)this.mHelper.DpToPixel(39);
		checkWidth = (int)this.mHelper.DpToPixel(39);
		
		DP_10 = (int) this.context.getResources().getDimension(R.dimen.DP_10);

		
		swHeight = (int) this.mHelper.DpToPixel(200);
		swWidth = (int) this.mHelper.DpToPixel(220);
		
		playHeight = (int)this.mHelper.DpToPixel(20);
		playWidth = (int)this.mHelper.DpToPixel(27);
	

	
		seekHeight = (int)this.mHelper.DpToPixel(12);
		seekWidth = (int)this.mHelper.DpToPixel(12);
		

		avartaHeight = (int)this.mHelper.DpToPixel(137);
		avartaWidth = (int) this.mHelper.DpToPixel(137);
		mTitleSize = (int) this.mHelper.DpToSp(30);
		mDecSize = (int) this.mHelper.DpToSp(15);
		
		
	}

	public View initUIBackGround() {
		View v = new View(this.context);
		FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(
				FrameLayout.LayoutParams.MATCH_PARENT,
				FrameLayout.LayoutParams.MATCH_PARENT);
		v.setLayoutParams(params);
		v.setBackgroundResource(R.drawable.bg_nohitaky_1);

		return v;
	}

	public View initUIBackGroundDark() {
		View v = new View(this.context);
		FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(
				FrameLayout.LayoutParams.MATCH_PARENT,
				FrameLayout.LayoutParams.MATCH_PARENT);
		v.setLayoutParams(params);
		v.setBackgroundResource(R.drawable.bg_nohitaky_2);
		v.setVisibility(View.INVISIBLE);

		return v;
	}

	public FrameLayout initUIBack() {
		FrameLayout layout = new FrameLayout(this.context);
		FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(
				btn_back, btn_back);
		params.topMargin = (int) this.mHelper.DpToPixel(10);
		params.leftMargin = (int) this.mHelper.DpToPixel(10);
		layout.setLayoutParams(params);

		ImageView view = new ImageView(this.context);
		FrameLayout.LayoutParams viewParams = new FrameLayout.LayoutParams(
				FrameLayout.LayoutParams.WRAP_CONTENT,
				FrameLayout.LayoutParams.WRAP_CONTENT);
		view.setLayoutParams(viewParams);
		view.setBackgroundResource(R.drawable.btn_back);
		view.setId(Key.btn_back);

		layout.addView(view);

		return layout;
	}

	public RelativeLayout initUITitle() {
		RelativeLayout main = new RelativeLayout(this.context);

		FrameLayout.LayoutParams mainPara = new FrameLayout.LayoutParams(
				FrameLayout.LayoutParams.MATCH_PARENT, avartaHeight);
		mainPara.topMargin = (int) this.mHelper.DpToPixel(75);
		main.setLayoutParams(mainPara);

		ImageView avarta = new ImageView(this.context);
		avarta.setId(Key.AUDIOS_NAME);
		avarta.setImageResource(R.drawable.music_avatar);
		RelativeLayout.LayoutParams avarPara = new RelativeLayout.LayoutParams(
				avartaWidth, avartaHeight);
		avarPara.leftMargin = (int) this.mHelper.DpToPixel(20);

		Typeface type = Typeface.createFromAsset(this.context.getAssets(),
				"museosans_300.otf");

		TextView title = new TextView(this.context);
		title.setTypeface(type);
		title.setTextSize(mTitleSize);
		title.setTextColor(Color.WHITE);
		title.setAlpha(0.8f);
		title.setText("Nohitaky");

		TextView dec = new TextView(this.context);
		dec.setId(Key.AUDIOS_DECRIPTION);
		dec.setAlpha(0.5f);
		dec.setTextColor(Color.WHITE);
		dec.setTypeface(type);
		dec.setText("Lescient elitis endia tiu a sin ciluemol est ciam et ita int. Lga ris sime volutar");

		RelativeLayout.LayoutParams decPara = new RelativeLayout.LayoutParams(
				RelativeLayout.LayoutParams.WRAP_CONTENT,
				RelativeLayout.LayoutParams.WRAP_CONTENT);
		decPara.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM, RelativeLayout.TRUE);
		decPara.addRule(RelativeLayout.RIGHT_OF, avarta.getId());
		decPara.rightMargin = 20;
		decPara.leftMargin = 20;

		RelativeLayout.LayoutParams titlePara = new RelativeLayout.LayoutParams(
				RelativeLayout.LayoutParams.WRAP_CONTENT,
				RelativeLayout.LayoutParams.WRAP_CONTENT);
		titlePara.addRule(RelativeLayout.ABOVE, dec.getId());
		titlePara.addRule(RelativeLayout.RIGHT_OF, avarta.getId());
		titlePara.rightMargin = 20;
		titlePara.leftMargin = 20;

		main.addView(avarta, avarPara);
		main.addView(dec, decPara);
		main.addView(title, titlePara);
		return main;

	}

	public FrameLayout initUIMusik() {
		FrameLayout layout = new FrameLayout(this.context);
		
		FrameLayout.LayoutParams para = new FrameLayout.LayoutParams(
				FrameLayout.LayoutParams.MATCH_PARENT,
				FrameLayout.LayoutParams.WRAP_CONTENT);
		para.gravity = Gravity.CENTER_VERTICAL;
		layout.setLayoutParams(para);

		// UI musik
		View v = new View(this.context);
		FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(
				FrameLayout.LayoutParams.MATCH_PARENT, musik_height);
		v.setId(Key.PLAY);
		v.setLayoutParams(params);
		
		v.setBackgroundResource(R.drawable.musik_liste_background_full);

		layout.addView(v);
		return layout;
	}

	public LinearLayout initUIBottom() {
		// Height for this UI
		int height = (int) ((mHelper.getAppHeight() / 2) - (musik_height / 2));
		LinearLayout main = new LinearLayout(this.context);
		FrameLayout.LayoutParams mainParams = new FrameLayout.LayoutParams(
				FrameLayout.LayoutParams.MATCH_PARENT, height);
		mainParams.gravity = Gravity.BOTTOM;
		main.setLayoutParams(mainParams);
		main.setWeightSum(4f);
		main.setOrientation(LinearLayout.VERTICAL);
		main.setBackgroundResource(R.drawable.bg_nohitaki);

		// Download Music
		LinearLayout layout = new LinearLayout(this.context);
		LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
				FrameLayout.LayoutParams.WRAP_CONTENT, 0, 1f);
		layout.setLayoutParams(layoutParams);
		layoutParams.gravity = Gravity.RIGHT;
		layoutParams.rightMargin = DP_10;
		layout.setOrientation(LinearLayout.HORIZONTAL);
		CheckBox musik = new CheckBox(this.context);
		musik.setId(Key.NOHITAKI_MusikCheck);
		LinearLayout.LayoutParams musikPara = new LinearLayout.LayoutParams(
				checkWidth, checkHeight);

		musikPara.gravity = Gravity.CENTER;
		musik.setLayoutParams(musikPara);
		musik.setBackgroundResource(R.drawable.btn_check_normal);
		// musik.setBackgroundResource(R.drawable.btn_check);
		musik.setButtonDrawable(R.drawable.btn_check);
		layout.addView(musik);
		// ==========
		LinearLayout layout1 = new LinearLayout(this.context);
		LinearLayout.LayoutParams layoutParams1 = new LinearLayout.LayoutParams(
				FrameLayout.LayoutParams.WRAP_CONTENT, 0, 1f);
		layoutParams1.gravity = Gravity.RIGHT;
		layoutParams1.rightMargin = DP_10;
		layout1.setLayoutParams(layoutParams1);
		layout1.setOrientation(LinearLayout.HORIZONTAL);
		CheckBox musik1 = new CheckBox(this.context);
		musik1.setId(Key.NOHITAKI_PDFCheck);
		musik1.setBackgroundResource(R.drawable.btn_check_normal);
		LinearLayout.LayoutParams musikPara1 = new LinearLayout.LayoutParams(
				checkWidth, checkHeight);
		musikPara1.gravity = Gravity.CENTER;
		musik1.setLayoutParams(musikPara1);
		// musik1.setBackgroundResource(R.drawable.btn_check);
		musik1.setButtonDrawable(R.drawable.btn_check);
		layout1.addView(musik1);
		// ==========
		LinearLayout layout2 = new LinearLayout(this.context);
		LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(
				FrameLayout.LayoutParams.WRAP_CONTENT, 0, 1f);
		layoutParams2.gravity = Gravity.RIGHT;
		layoutParams2.rightMargin = DP_10;
		layout2.setLayoutParams(layoutParams2);
		layout2.setOrientation(LinearLayout.HORIZONTAL);
		ImageView musik2 = new ImageView(this.context);
		musik2.setId(Key.NOHITAKI_Wecker);
		LinearLayout.LayoutParams musikPara2 = new LinearLayout.LayoutParams(
				checkWidth, checkHeight);
		musikPara2.gravity = Gravity.CENTER;
		musik2.setLayoutParams(musikPara2);
		musik2.setImageResource(R.drawable.btn_wecker);
		layout2.addView(musik2);
		// ==========
		LinearLayout layout3 = new LinearLayout(this.context);
		LinearLayout.LayoutParams layoutParams3 = new LinearLayout.LayoutParams(
				FrameLayout.LayoutParams.WRAP_CONTENT, 0, 1f);
		layoutParams3.gravity = Gravity.RIGHT;
		layoutParams3.rightMargin = DP_10;
		layout3.setLayoutParams(layoutParams3);
		layout3.setOrientation(LinearLayout.HORIZONTAL);
		Switch musik3 = new Switch(this.context);
		musik3.setId(Key.NOHITAKI_ChangeBg);
		LinearLayout.LayoutParams musikPara3 = new LinearLayout.LayoutParams(
				swWidth, swHeight);
		musikPara3.gravity = Gravity.CENTER;
		musik3.setLayoutParams(musikPara3);

		musik3.setThumbResource(R.drawable.apptheme_switch_inner_holo_light);
		musik3.setTrackResource(R.drawable.apptheme_switch_track_holo_light);

		musik3.setTextOn("   ");
		musik3.setTextOff("            ");
		

		layout3.addView(musik3);
		// ==========
		main.addView(layout);
		main.addView(layout1);
		main.addView(layout2);
		main.addView(layout3);

		return main;
	}

	public RelativeLayout initUIMusicBar() {
		// Top in parent
		int pTop = (int) ((mHelper.getAppHeight() / 2) - musik_height - DP_10);
		RelativeLayout main = new RelativeLayout(this.context);
		// time start
		TextView timeStart = new TextView(this.context);
		timeStart.setTextColor(Color.WHITE);
		timeStart.setAlpha(0.8f);
		timeStart.setText("25:58");
		timeStart.setId(Key.HEADER);
		// time end
		TextView timeEnd = new TextView(this.context);
		timeEnd.setText("57:30");
		timeEnd.setAlpha(0.8f);
		timeEnd.setTextColor(Color.WHITE);
		timeEnd.setId(Key.AUDIOS_NAME);
		//
		SeekBar mLine = new SeekBar(this.context);
		mLine.setProgressDrawable(this.context.getResources().getDrawable(
				R.drawable.seekbar_process));
		mLine.setThumb(this.context.getResources().getDrawable(
				R.drawable.seekbar_point));
		mLine.setId(Key.SEEKBAR_LINE);
		mLine.setVisibility(View.VISIBLE);
		// volume image
		View volume = new View(this.context);
		volume.setBackgroundResource(R.drawable.btn_playaudio);
		volume.setId(Key.VOLUME);

		RelativeLayout.LayoutParams volumePara = new RelativeLayout.LayoutParams(
				playWidth, playHeight);
		volumePara.addRule(RelativeLayout.ALIGN_PARENT_LEFT,
				RelativeLayout.TRUE);
		volumePara.addRule(RelativeLayout.CENTER_VERTICAL, RelativeLayout.TRUE);
		//
		
		//
		RelativeLayout.LayoutParams barVolumePara = new RelativeLayout.LayoutParams(
				RelativeLayout.LayoutParams.MATCH_PARENT,
				RelativeLayout.LayoutParams.WRAP_CONTENT
				);
	
		barVolumePara.addRule(RelativeLayout.RIGHT_OF, timeStart.getId());
		barVolumePara.addRule(RelativeLayout.LEFT_OF, timeEnd.getId());
		barVolumePara.addRule(RelativeLayout.CENTER_VERTICAL,
				RelativeLayout.TRUE);
		//
		RelativeLayout.LayoutParams startPara = new RelativeLayout.LayoutParams(
				RelativeLayout.LayoutParams.WRAP_CONTENT,
				RelativeLayout.LayoutParams.WRAP_CONTENT);
		startPara.leftMargin = DP_10;
		startPara.addRule(RelativeLayout.CENTER_VERTICAL, RelativeLayout.TRUE);
		startPara.addRule(RelativeLayout.RIGHT_OF, volume.getId());
		//
		RelativeLayout.LayoutParams endPara = new RelativeLayout.LayoutParams(
				RelativeLayout.LayoutParams.WRAP_CONTENT,
				RelativeLayout.LayoutParams.WRAP_CONTENT);
		endPara.rightMargin = DP_10;
		endPara.addRule(RelativeLayout.CENTER_VERTICAL, RelativeLayout.TRUE);
		endPara.addRule(RelativeLayout.ALIGN_PARENT_RIGHT, RelativeLayout.TRUE);

		FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(
				FrameLayout.LayoutParams.MATCH_PARENT, 80);
		params.topMargin = pTop;
		params.leftMargin = DP_10 * 2;
		params.rightMargin = DP_10 * 2;
		main.setLayoutParams(params);
		main.addView(volume, volumePara);
		main.addView(timeStart, startPara);
		main.addView(timeEnd, endPara);
		main.addView(mLine, barVolumePara);

		return main;
	}


	
	
}
