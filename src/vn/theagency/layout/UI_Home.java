package vn.theagency.layout;

import vn.theagency.customlayout.PhotoView;
import vn.theagency.getpregnantapplication.R;
import vn.theagency.getpregnant.Audios_Library.AuswahlAnimation;
import vn.theagency.helper.Key;
import vn.theagency.helper.Helper;
import android.animation.LayoutTransition;
import android.app.ActionBar.LayoutParams;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class UI_Home {
	private Helper mHelper;
	private Context context;
	private static UI_Home _ins;

	int icon_home;
	int icon_home_aligh;;
	
	int icon_plus;
	int bar_width;
	int plus;
	int plusMargin,plusTextMargin,textAddition,textsize;
	
	int titleWidth;
	int titleHeight;
	public static int animUpPlus;
	int topMargin;
	int barHeight;
	
	private UI_Home(Context _context) {
		this.context = _context;
		this.mHelper = Helper.shareIns(this.context);
		size(context.getResources().getDimension(R.dimen.SIZE));
	}

	public static UI_Home shareIns(Context _context) {
		if (UI_Home._ins == null) {
			UI_Home._ins = new UI_Home(_context);
			// Layout_Titles._ins.insHelper = Helper.shareIns(_context);
		}

		return UI_Home._ins;
	}

	/**
	 * 
	 */
	public void size(float a) {
		icon_home = (int) (mHelper.DpToPixel(40)*a) ;
		icon_home_aligh = (int) mHelper.DpToPixel(12) ;
		plus = (int) (mHelper.DpToPixel(55)*a);
		plusMargin = (int) mHelper.getAppWidth()/3;
		textAddition = (int) (mHelper.DpToPixel(100)*a);
		plusTextMargin= (int)(mHelper.DpToPixel(50)*a);
		textsize = (int) (mHelper.DpToSp(20)*a);
		
		
		titleWidth =(int) (8*this.mHelper.getAppWidth()/10);
		titleHeight =(int) (this.mHelper.DpToPixel(120)*a);
		
		topMargin = (int) ((4*mHelper.getAppHeight())/5);
		animUpPlus = topMargin; 
		
		barHeight = (int) (this.mHelper.DpToPixel(30)*a);
	}

	/**
	 * 
	 * @return
	 */
	public FrameLayout initUI(int pos) {
		
		
		barHeight = (int) this.mHelper.DpToPixel(30);

		// Auflosen
		FrameLayout linearAuflosen = new FrameLayout(this.context);
		linearAuflosen.setId(Key.linearAuflosen);
		FrameLayout.LayoutParams paralinearAuflosen =new FrameLayout.LayoutParams((int)(mHelper.getAppWidth()/4),
				LayoutParams.WRAP_CONTENT);
		paralinearAuflosen.topMargin =barHeight/3;
		paralinearAuflosen.leftMargin = pos;
		
		linearAuflosen.setLayoutParams(paralinearAuflosen);
		
		
		ImageView auflosen = new ImageView(this.context);
		FrameLayout.LayoutParams paraAuflosen = new FrameLayout.LayoutParams(
				(int)(mHelper.getAppWidth()/6), (int)(barHeight/2));
		paraAuflosen.gravity = Gravity.CENTER;
		auflosen.setLayoutParams(paraAuflosen);
		auflosen.setBackgroundResource(R.drawable.btn_auf);
		auflosen.setId(Key.auflosen);
	
		
		
		
		linearAuflosen.addView(auflosen);
		// Vorbereiten
		FrameLayout linearVorbereiten = new FrameLayout(this.context);
		linearVorbereiten.setId(Key.linearVorbereiten);
		
		FrameLayout.LayoutParams paralinearVorbereiten = new FrameLayout.LayoutParams((int)(mHelper.getAppWidth()/4),
				LayoutParams.WRAP_CONTENT);
		paralinearVorbereiten.topMargin =barHeight/3;
		paralinearVorbereiten.leftMargin = (int) (mHelper.getAppWidth()/4);
		linearVorbereiten.setLayoutParams(paralinearVorbereiten);
		

		ImageView vorbereiten = new ImageView(this.context);
		FrameLayout.LayoutParams paraVorbereiten = new FrameLayout.LayoutParams(
				(int)(mHelper.getAppWidth()/5), (int)(barHeight/2));
		paraVorbereiten.gravity = Gravity.CENTER;
		vorbereiten.setLayoutParams(paraVorbereiten);
		vorbereiten.setBackgroundResource(R.drawable.btn_vor);

		vorbereiten.setId(Key.vorbereiten);
		

		linearVorbereiten.addView(vorbereiten);
		// Unterstutzen
		FrameLayout linearUnterstutzen = new FrameLayout(this.context);
		linearUnterstutzen.setId(Key.linearUnterstutzen);
		
		FrameLayout.LayoutParams paralinearUnterstutzen = new FrameLayout.LayoutParams((int)(mHelper.getAppWidth()/4),
				LayoutParams.WRAP_CONTENT);
		paralinearUnterstutzen.topMargin =barHeight/3;
		paralinearUnterstutzen.leftMargin = (int) (mHelper.getAppWidth()/2);
		linearUnterstutzen.setLayoutParams(paralinearUnterstutzen);
		

		ImageView unterstutzen = new ImageView(this.context);
		FrameLayout.LayoutParams paraUnterstutzen = new FrameLayout.LayoutParams(
				(int)(mHelper.getAppWidth()/5), (int)(barHeight/2));
		paraUnterstutzen.gravity = Gravity.CENTER;
		unterstutzen.setLayoutParams(paraUnterstutzen);
		unterstutzen.setBackgroundResource(R.drawable.btn_unt);
		unterstutzen.setId(Key.unterstutzen);
		

		linearUnterstutzen.addView(unterstutzen);
		// Verbessern
		FrameLayout linearVerbessern = new FrameLayout(this.context);
		linearVerbessern.setId(Key.linearVerbessern);
	
		FrameLayout.LayoutParams paralinearVerbessern = new FrameLayout.LayoutParams((int)(mHelper.getAppWidth()/4),
				LayoutParams.WRAP_CONTENT);
		paralinearVerbessern.topMargin =barHeight/3;
		paralinearVerbessern.leftMargin = (int) (3*mHelper.getAppWidth()/4);
		linearVerbessern.setLayoutParams(paralinearVerbessern);
		

		ImageView verbessern = new ImageView(this.context);
		FrameLayout.LayoutParams paraVerbessern = new FrameLayout.LayoutParams(
				(int)(mHelper.getAppWidth()/5), (int)(barHeight/2));
		paraVerbessern.gravity = Gravity.CENTER;
		
		
		verbessern.setLayoutParams(paraVerbessern);
		verbessern.setBackgroundResource(R.drawable.btn_ver);
		verbessern.setId(Key.verbessern);
		

		linearVerbessern.addView(verbessern);
		// Ausgleichen
		
		
		FrameLayout linearAusgleichen = new FrameLayout(this.context);
		linearAusgleichen.setId(Key.linearAusgleichen);
		
		FrameLayout.LayoutParams paralinearAusgleichen = new FrameLayout.LayoutParams((int)(mHelper.getAppWidth()/4),
				LayoutParams.WRAP_CONTENT);
		paralinearAusgleichen.topMargin =barHeight/3;
		linearAusgleichen.setLayoutParams(paralinearAusgleichen);
	

		ImageView ausgleichen = new ImageView(this.context);
		FrameLayout.LayoutParams paraAusgleichen = new FrameLayout.LayoutParams(
				(int)(mHelper.getAppWidth()/5), (int)(barHeight/2));
		paraAusgleichen.gravity = Gravity.CENTER;
		ausgleichen.setLayoutParams(paraAusgleichen);
		ausgleichen.setBackgroundResource(R.drawable.btn_ausg);
		ausgleichen.setId(Key.ausgleichen);
		

		linearAusgleichen.addView(ausgleichen);
		
		

		FrameLayout header = new FrameLayout(this.context);
		FrameLayout.LayoutParams paraHeader = new FrameLayout.LayoutParams(
				FrameLayout.LayoutParams.MATCH_PARENT,
				FrameLayout.LayoutParams.WRAP_CONTENT);
		paraHeader.gravity = Gravity.BOTTOM;

		header.setLayoutParams(paraHeader);
		header.setBackgroundResource(R.drawable.bar);
		
		
		header.addView(linearAusgleichen);
		header.addView(linearAuflosen);
		header.addView(linearVorbereiten);
		header.addView(linearUnterstutzen);
		header.addView(linearVerbessern);
		
		
		return header;

	}

	public FrameLayout initUIAddition(int imgTitle) {	
		//
		
		
		
		FrameLayout main = new FrameLayout(this.context);
		
		FrameLayout.LayoutParams mainFrame = new FrameLayout.LayoutParams(
				FrameLayout.LayoutParams.MATCH_PARENT, textAddition);
		mainFrame.topMargin = topMargin;
		main.setLayoutParams(mainFrame);
		main.setId(Key.BG_ADDITION);

		//
		FrameLayout layout = new FrameLayout(this.context);
		layout.setId(Key.HEADER);
		
		FrameLayout.LayoutParams layoutFrame = new FrameLayout.LayoutParams(
				FrameLayout.LayoutParams.WRAP_CONTENT, FrameLayout.LayoutParams.WRAP_CONTENT);
		layoutFrame.gravity = Gravity.CENTER;
		layout.setLayoutParams(layoutFrame);
		//===
		
		View audios = new View(this.context);
		FrameLayout.LayoutParams audiosPara = new FrameLayout.LayoutParams(
				plus, plus);
		
		audiosPara.gravity = Gravity.CENTER_VERTICAL;
		audios.setLayoutParams(audiosPara);
		audios.setBackgroundResource(R.drawable.btn_plus);
		audios.setId(Key.AUSWAHL);
		
		

		TextView text = new TextView(this.context);
		text.setText("AUSWAHL");
		text.setTextSize(textsize);
		text.setTextColor(Color.parseColor("#5c5c5c"));
		Typeface type = Typeface.createFromAsset(this.context.getAssets(),"museosans_300.otf");
		text.setTypeface(type);
		
		text.setId(Key.AUDIOS_NAME);
		FrameLayout.LayoutParams textPara = new FrameLayout.LayoutParams(
				FrameLayout.LayoutParams.WRAP_CONTENT,
				FrameLayout.LayoutParams.WRAP_CONTENT);
		textPara.leftMargin = plusTextMargin;
		
		textPara.gravity = Gravity.CENTER_VERTICAL;
		text.setLayoutParams(textPara);
		//
		
		
	//	main.setBackgroundColor(Color.RED);
		
		layout.addView(audios);
		layout.addView(text);
		main.addView(layout);
		
		return main;
		
	}

	
	
	
	public FrameLayout initUIText(float pTop,int bgText) {
		
	
		
	FrameLayout layout = new FrameLayout(this.context);
	FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(
			titleWidth,
			titleHeight);
	params.topMargin = (int)pTop;
	params.gravity = Gravity.CENTER_HORIZONTAL;
	layout.setLayoutParams(params);

	ImageView mTitle1 = new ImageView(this.context);
	FrameLayout.LayoutParams mLayoutTitle1 = new FrameLayout.LayoutParams(
			FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT);
	
	mTitle1.setLayoutParams(mLayoutTitle1);
	mTitle1.setBackgroundResource(bgText);
	mTitle1.setId(Key.TITLE);

	layout.addView(mTitle1);

	return layout;
}
	public ImageView initUIHome() {

		
	ImageView home = new ImageView(this.context);
	FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(icon_home,icon_home);
	params.topMargin = icon_home_aligh;
	params.leftMargin = icon_home_aligh;
	home.setLayoutParams(params);
	
	home.setBackgroundResource(R.drawable.btn_home);
	home.setId(Key.HOME);

	return home;
}

}
