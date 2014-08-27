package vn.theagency.layout;

import vn.theagency.customlayout.PhotoView;
import vn.theagency.getpregnantapplication.R;
import vn.theagency.helper.Helper;
import vn.theagency.helper.Key;
import android.content.Context;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

public class UI_Cover {
	
	int btn_ausg_width;
	int btn_ausg_height;
	int icon_top;
	int icon_top_1;
	int icon_width;
	int icon_height;
	int icon_left;
	int icon_left_parent;
	int icon_left_ausg;
	int icon_info;
	int text_width;
	int text_heigt;
	public int warning_height,warning_width;
	public int animationInfo;

	private Helper mHelper;
	private Context context;
	private static UI_Cover _ins;
	private UI_Cover(Context _context) {
		this.context = _context;
		this.mHelper = Helper.shareIns(this.context);
		size(context.getResources().getDimension(R.dimen.SIZE));
	}

	
	public static UI_Cover shareIns(Context _context) {
		if (UI_Cover._ins == null) {
			UI_Cover._ins = new UI_Cover(_context);
		}
		return UI_Cover._ins;
	}
	private void size(float a) {
		
		Log.i("LTH", "a= "+String.valueOf(a));
		text_width= (int) (mHelper.DpToPixel(303)*a);
		text_heigt= (int) ( mHelper.DpToPixel(84)*a);
		icon_top = (int) (mHelper.DpToPixel(400)*a);
		icon_width = (int) ( mHelper.DpToPixel(95)*a);
		icon_height = (int) ( mHelper.DpToPixel(101)*a);
		icon_left = (int) ( mHelper.DpToPixel(12)*a);
		icon_left_parent =(int) (mHelper.DpToPixel(25)*a);
		icon_top_1 =(int) ( mHelper.DpToPixel(513)*a);
		icon_left_ausg =(int) ( mHelper.DpToPixel(79)*a);
		icon_info = (int) ( mHelper.DpToPixel(40)*a);
		warning_height =(int) ( mHelper.DpToPixel(411)*a);
		warning_width = (int) ( mHelper.DpToPixel(324)*a);
		animationInfo = (int)((icon_info/2)+icon_left);
		
		
			
	}
	
	public View initUIBackGround() {
		View v = new View(this.context);
		FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(
				FrameLayout.LayoutParams.MATCH_PARENT,
				FrameLayout.LayoutParams.MATCH_PARENT);
		v.setLayoutParams(params);
		v.setBackgroundResource(R.drawable.bg_cover);
		return v;
	}
	public View initUIHideBackGround() {
		View v = new View(this.context);
		FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(
				FrameLayout.LayoutParams.MATCH_PARENT,
				FrameLayout.LayoutParams.MATCH_PARENT);
		v.setLayoutParams(params);
		v.setBackgroundResource(R.drawable.bg_nohitaky_2);
		v.setVisibility(View.GONE);
		v.setId(Key.HEADER);
		return v;
	}
	public FrameLayout initUIWarning() {
		
		
		FrameLayout layout = new FrameLayout(context);
		FrameLayout.LayoutParams paramsLayout = new FrameLayout.LayoutParams(
				warning_width,
				warning_height);
		paramsLayout.topMargin=icon_info+icon_left;
		paramsLayout.gravity = Gravity.CENTER_HORIZONTAL;
		layout.setLayoutParams(paramsLayout);
		View v = new View(this.context);
		FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(
				FrameLayout.LayoutParams.MATCH_PARENT,
				FrameLayout.LayoutParams.MATCH_PARENT);
		
		v.setLayoutParams(params);
		v.setBackgroundResource(R.drawable.warning_popup);
		
		v.setId(Key.WARNING);
		layout.addView(v);
		return layout;
	}
	public View initUIText() {
		View v = new View(this.context);
		FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(
				text_width,
				text_heigt);
		params.topMargin = (int) (mHelper.getAppHeight()/9);
		params.gravity = Gravity.CENTER_HORIZONTAL;
		v.setLayoutParams(params);
		v.setBackgroundResource(R.drawable.cover_text);
		return v;
	}
	public FrameLayout initUIInfo() {
		FrameLayout layout =new FrameLayout(context);
		FrameLayout.LayoutParams paramsLayout = new FrameLayout.LayoutParams(
				FrameLayout.LayoutParams.MATCH_PARENT,
				FrameLayout.LayoutParams.WRAP_CONTENT);
		ImageView view = new ImageView(this.context);
		FrameLayout.LayoutParams viewParams = new FrameLayout.LayoutParams(
				icon_info,
				icon_info);
		viewParams.topMargin = icon_left;
		viewParams.leftMargin = icon_left;
		view.setLayoutParams(viewParams);
		view.setBackgroundResource(R.drawable.btn_cover_info);
		view.setId(Key.HOME);
		layout.addView(view);
		
		return layout;
	}
	public FrameLayout initUIBottom() {
		int leftAusg = (int) ((this.mHelper.getAppWidth()/2)-icon_width-(icon_left/2));
		int leftAuf =leftAusg+icon_left+icon_width;
		int leftVer = (int) ((this.mHelper.getAppWidth()/2)-icon_left-(3*icon_width/2));
		int leftVor = leftVer+icon_left+icon_width;
		int leftUnt = leftVor+icon_left+icon_width;
		//
		int marginTop1 = (int) mHelper.getAppHeight()-(icon_height*2)-(icon_left*3);
		int marginTop2 = marginTop1+icon_height+icon_left;
		//
		
		FrameLayout layout = new FrameLayout(this.context);
		FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(
				FrameLayout.LayoutParams.MATCH_PARENT,
				FrameLayout.LayoutParams.MATCH_PARENT);
		layout.setLayoutParams(params);
		//
		
		View ver = new View(this.context);
		FrameLayout.LayoutParams verParams = new FrameLayout.LayoutParams(
				icon_width,
				icon_height);
		
		verParams.topMargin = marginTop2;
		verParams.leftMargin = leftVer;
		ver.setLayoutParams(verParams);
		ver.setBackgroundResource(R.drawable.btn_cover_ver);
		ver.setId(Key.linearVerbessern);
		//===
		View ver1 = new View(this.context);
		ver1.setBackgroundResource(R.drawable.bg_btn_ver);
		ver1.setLayoutParams(verParams);
		ver1.setId(Key.verbessern);
		//
		
		
		View vor = new View(this.context);
		FrameLayout.LayoutParams vorParams = new FrameLayout.LayoutParams(
				icon_width,
				icon_height);
		vorParams.topMargin = marginTop2;
		vorParams.leftMargin = leftVor;
		vor.setLayoutParams(vorParams);
		vor.setBackgroundResource(R.drawable.btn_cover_vor);
		vor.setId(Key.linearVorbereiten);
		//===
		View vor1 = new View(this.context);
		vor1.setBackgroundResource(R.drawable.bg_btn_vor);
		vor1.setLayoutParams(vorParams);
		vor1.setId(Key.vorbereiten);
		//
		View unt = new View(this.context);
		FrameLayout.LayoutParams untParams = new FrameLayout.LayoutParams(
				icon_width,
				icon_height);
		untParams.topMargin = marginTop2;
		untParams.leftMargin = leftUnt;
		unt.setLayoutParams(untParams);
		unt.setBackgroundResource(R.drawable.btn_cover_unt);
		unt.setId(Key.linearUnterstutzen);
		//==
		View unt1 = new View(this.context);
		unt1.setBackgroundResource(R.drawable.bg_btn_unt);
		unt1.setLayoutParams(untParams);
		unt1.setId(Key.unterstutzen);
		//
		
		View ausg = new View(this.context);
		FrameLayout.LayoutParams ausParams = new FrameLayout.LayoutParams(
				icon_width,
				icon_height);
		
		ausParams.topMargin = marginTop1;
		ausParams.leftMargin = leftAusg;
		ausg.setLayoutParams(ausParams);
		ausg.setBackgroundResource(R.drawable.btn_cover_aus);
		ausg.setId(Key.linearAusgleichen);
		//===
		View ausg1 = new View(this.context);
		ausg1.setBackgroundResource(R.drawable.bg_btn_aus);
		ausg1.setLayoutParams(ausParams);
		ausg1.setId(Key.ausgleichen);
		//
		View auf = new View(this.context);
		FrameLayout.LayoutParams aufParams = new FrameLayout.LayoutParams(
				icon_width,
				icon_height);
		aufParams.topMargin = marginTop1;
		aufParams.leftMargin = leftAuf;
		auf.setLayoutParams(aufParams);
		auf.setId(Key.linearAuflosen);
		auf.setBackgroundResource(R.drawable.btn_cover_auf);
		//
		View auf1 = new View(this.context);
		auf1.setBackgroundResource(R.drawable.bg_btn_auf);
		auf1.setLayoutParams(aufParams);
		auf1.setId(Key.auflosen);
		
		//
		layout.addView(ausg1);
		layout.addView(ausg);
		
		layout.addView(auf1);
		layout.addView(auf);
		layout.addView(ver1);
		layout.addView(ver);
		layout.addView(vor1);
		layout.addView(vor);
		layout.addView(unt1);
		layout.addView(unt);
		
		
		return layout;
	}
	
}
