package vn.theagency.layout;

import vn.theagency.getpregnant.R;
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
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class UI_Audios {

	private Helper mHelper;
	private Context context;
	
	
	

	int plus;
	
	//
	public static int header_width,header_height,textsize;

	private static UI_Audios _ins;

	private UI_Audios(Context _context) {
		this.context = _context;
		this.mHelper = Helper.shareIns(this.context);
		size();
	}

	public static UI_Audios shareIns(Context _context) {
		if (UI_Audios._ins == null) {
			UI_Audios._ins = new UI_Audios(_context);
		}
		return UI_Audios._ins;
	}
	private void size() {
		
		plus = (int) mHelper.DpToPixel(46);
		
		
		//
		header_width = (int) this.mHelper.DpToPixel(360);
		header_height = (int) this.mHelper.DpToPixel(92);
		textsize = (int) this.mHelper.DpToSp(20);
	}

	public ListView initUIListView() {
		

		ListView view = new ListView(this.context);
		int height = (int) (mHelper.getAppHeight() - (header_height * 2));
		FrameLayout.LayoutParams viewPara = new FrameLayout.LayoutParams(
				LayoutParams.MATCH_PARENT, height, 500);
		viewPara.topMargin = header_height;
		view.setLayoutParams(viewPara);
		view.setId(Key.LISTVIEW_LIBRARY);
		return view;
	}

	public RelativeLayout initUIHeader() {
		LayoutTransition lt = new LayoutTransition();
	    lt.disableTransitionType(LayoutTransition.DISAPPEARING);

		RelativeLayout main = new RelativeLayout(this.context);
		
		FrameLayout.LayoutParams mainFrame = new FrameLayout.LayoutParams(
				FrameLayout.LayoutParams.MATCH_PARENT, this.header_height);
		main.setLayoutParams(mainFrame);
		main.setBackgroundResource(R.drawable.library_header);
		
		
		RelativeLayout layout = new RelativeLayout(this.context);
		RelativeLayout.LayoutParams paraFrame = new RelativeLayout.LayoutParams(
				(int) this.context.getResources().getDimension(R.dimen.AUSWAHL), (int)this.header_height/2);
		paraFrame.addRule(RelativeLayout.CENTER_IN_PARENT, RelativeLayout.TRUE);
		
		layout.setLayoutTransition(lt);

		View audios = new View(this.context);
		RelativeLayout.LayoutParams audiosPara = new RelativeLayout.LayoutParams(
				plus, plus);
		audiosPara.addRule(RelativeLayout.CENTER_VERTICAL,RelativeLayout.TRUE);

		audios.setBackgroundResource(R.drawable.btn_slip);
		audios.setId(Key.AUDIOS_NAME);
		audios.setVisibility(View.VISIBLE);
		

		TextView text = new TextView(this.context);
		
		text.setAlpha(0.5f);		
		text.setTextSize(this.textsize);
		
		Typeface type = Typeface.createFromAsset(this.context.getAssets(),"museosans_300.otf");
		text.setTypeface(type);
		text.setText("AUDIOS LIBRARY");
		text.setId(Key.AUSWAHL);
		RelativeLayout.LayoutParams textPara = new RelativeLayout.LayoutParams(
				RelativeLayout.LayoutParams.WRAP_CONTENT,
				RelativeLayout.LayoutParams.WRAP_CONTENT);
		textPara.addRule(RelativeLayout.RIGHT_OF, audios.getId());
		textPara.addRule(RelativeLayout.CENTER_VERTICAL, RelativeLayout.TRUE);
		layout.addView(audios,audiosPara);
		layout.addView(text,textPara);
		main.addView(layout,paraFrame);
		return main;
	}

	public View initUIBgView() {
		BitmapDrawable drawable = (BitmapDrawable) this.context.getResources()
				.getDrawable(R.drawable.library_header);
		int h = (int) ((2 * drawable.getBitmap().getHeight()) / 3);
		View header = new View(this.context);
		FrameLayout.LayoutParams para = new FrameLayout.LayoutParams(
				FrameLayout.LayoutParams.MATCH_PARENT,
				FrameLayout.LayoutParams.MATCH_PARENT);
		para.topMargin = h;
		header.setLayoutParams(para);
		header.setBackgroundResource(R.drawable.bg_list);
		return header;
	}

	public View initUIBottom() {
		BitmapDrawable drawable = (BitmapDrawable) this.context.getResources()
				.getDrawable(R.drawable.btn_deine_normal);
		int h = (int) ((2 * drawable.getBitmap().getHeight()) / 3);
		int hieght = (int) (mHelper.getAppHeight() - h);
		View view = new View(this.context);
		FrameLayout.LayoutParams paraHeader = new FrameLayout.LayoutParams(
				FrameLayout.LayoutParams.MATCH_PARENT,
				FrameLayout.LayoutParams.WRAP_CONTENT);
		paraHeader.topMargin = hieght;
		view.setLayoutParams(paraHeader);
		view.setBackgroundResource(R.drawable.btn_deine);
		view.setId(Key.btn_deine_musik);
		return view;
	}

	public ImageView initUIDown() {
		BitmapDrawable drawable1 = (BitmapDrawable) this.context.getResources()
				.getDrawable(R.drawable.btn_deine_normal);
		int h1 = (int) ((2 * drawable1.getBitmap().getHeight()) / 3);

		BitmapDrawable drawable = (BitmapDrawable) this.context.getResources()
				.getDrawable(R.drawable.btn_more_normal);
		int h = (int) ((2 * drawable.getBitmap().getHeight()) / 3);
		int w = (int) ((2 * drawable.getBitmap().getWidth()) / 3);
		int hieght = (int) (mHelper.getAppHeight() - h - h1 - 20);
		ImageView view = new ImageView(this.context);
		FrameLayout.LayoutParams paraHeader = new FrameLayout.LayoutParams(w, h);
		paraHeader.topMargin = hieght;
		paraHeader.gravity = Gravity.CENTER_HORIZONTAL;
		view.setLayoutParams(paraHeader);
		view.setBackgroundResource(R.drawable.btn_more);
		return view;
	}

}
