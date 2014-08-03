package vn.theagency.layout;

import vn.theagency.getpregnant.R;
import vn.theagency.helper.Helper;
import vn.theagency.helper.Key;
import android.content.Context;
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

	private Helper mHelper;
	private Context context;
	private static UI_Cover _ins;
	private UI_Cover(Context _context) {
		this.context = _context;
		this.mHelper = Helper.shareIns(this.context);
		size();
	}

	
	public static UI_Cover shareIns(Context _context) {
		if (UI_Cover._ins == null) {
			UI_Cover._ins = new UI_Cover(_context);
		}
		return UI_Cover._ins;
	}
	private void size() {
		

		text_width= (int) mHelper.DpToPixel(303);
		text_heigt= (int) mHelper.DpToPixel(84);
		icon_top = (int) mHelper.DpToPixel(400);
		icon_width = (int) mHelper.DpToPixel(95);
		icon_height = (int) mHelper.DpToPixel(101);
		icon_left = (int) mHelper.DpToPixel(12);
		icon_left_parent =(int) mHelper.DpToPixel(25);
		icon_top_1 =(int) mHelper.DpToPixel(513);
		icon_left_ausg =(int) mHelper.DpToPixel(79);
		icon_info = (int) mHelper.DpToPixel(31);
		
			
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
		FrameLayout layout = new FrameLayout(this.context);
		FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(
				icon_info,
				icon_info);
		params.topMargin = icon_left;
		params.leftMargin = icon_left*2;
		layout.setLayoutParams(params);
		ImageView view = new ImageView(this.context);
		FrameLayout.LayoutParams viewParams = new FrameLayout.LayoutParams(
				FrameLayout.LayoutParams.WRAP_CONTENT,
				FrameLayout.LayoutParams.WRAP_CONTENT);
		view.setLayoutParams(viewParams);
		view.setBackgroundResource(R.drawable.btn_cover_info);
		
		layout.addView(view);
		
		return layout;
	}
	public FrameLayout initUIBottom() {
		
		FrameLayout layout = new FrameLayout(this.context);
		FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(
				FrameLayout.LayoutParams.WRAP_CONTENT,
				FrameLayout.LayoutParams.WRAP_CONTENT);
		params.topMargin =(int) ((82*mHelper.getAppHeight())/100);
		//params.gravity = Gravity.CENTER_HORIZONTAL;
		layout.setLayoutParams(params);
		//
		
		View view = new View(this.context);
		FrameLayout.LayoutParams viewParams = new FrameLayout.LayoutParams(
				icon_width,
				icon_height);
		viewParams.leftMargin = icon_left_parent;
		view.setLayoutParams(viewParams);
		view.setBackgroundResource(R.drawable.btn_cover_ver);
		view.setId(Key.linearVerbessern);
		//
		
		
		View view1 = new View(this.context);
		FrameLayout.LayoutParams viewParams1 = new FrameLayout.LayoutParams(
				icon_width,
				icon_height);
		viewParams1.leftMargin = icon_left+icon_left_parent+icon_width;
		view1.setLayoutParams(viewParams1);
		view1.setBackgroundResource(R.drawable.btn_cover_vor);
		view1.setId(Key.linearVorbereiten);
		//
		View view2 = new View(this.context);
		FrameLayout.LayoutParams viewParams2 = new FrameLayout.LayoutParams(
				icon_width,
				icon_height);
		viewParams2.leftMargin = (icon_left*2)+icon_left_parent +(icon_width*2);
		view2.setLayoutParams(viewParams2);
		view2.setBackgroundResource(R.drawable.btn_cover_unt);
		view2.setId(Key.linearUnterstutzen);
		//
		
		
		layout.addView(view);
		layout.addView(view1);
		layout.addView(view2);
		
		
		return layout;
	}
	public FrameLayout initUIBottom2() {
		
		FrameLayout layout = new FrameLayout(this.context);
		FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(
				FrameLayout.LayoutParams.WRAP_CONTENT,
				FrameLayout.LayoutParams.WRAP_CONTENT);
		params.topMargin =(int) ((63*mHelper.getAppHeight())/100);
		layout.setLayoutParams(params);
		
		
		View view = new View(this.context);
		FrameLayout.LayoutParams viewParams = new FrameLayout.LayoutParams(
				icon_width,
				icon_height);
		
		viewParams.leftMargin = icon_left_ausg;
		view.setLayoutParams(viewParams);
		view.setBackgroundResource(R.drawable.btn_cover_aus);
		view.setId(Key.linearAusgleichen);
		//
		View view1 = new View(this.context);
		FrameLayout.LayoutParams viewParams1 = new FrameLayout.LayoutParams(
				icon_width,
				icon_height);
	
		viewParams1.leftMargin = icon_left_ausg+icon_width+icon_left;
		view1.setLayoutParams(viewParams1);
		view1.setBackgroundResource(R.drawable.btn_cover_auf);
		view1.setId(Key.linearAuflosen);
		
		
		layout.addView(view);
		layout.addView(view1);
	
		
		
		return layout;
	}

}
