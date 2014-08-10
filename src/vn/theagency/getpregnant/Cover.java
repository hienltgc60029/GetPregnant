package vn.theagency.getpregnant;

import vn.theagency.customlayout.PhotoView;
import vn.theagency.helper.Helper;
import vn.theagency.helper.Key;
import vn.theagency.layout.UI_Cover;
import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.MotionEvent;
import android.view.View.OnTouchListener;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.ImageView;

public class Cover extends Activity implements OnClickListener, AnimationListener {

	private Helper mHelper;
	public UI_Cover mCover;
	public FrameLayout wrapper;
	
	public View initUIBackGround;
	public ImageView initUIInfo;
	public FrameLayout initUIBottom;
	public View initUIText;
	
	View aus,unt,vor,ver,home,hide;
	PhotoView auf;
	View initUIWarning,initUIHideBackGround;
	Animation animDown,animDownLeft,animDownRight,animLeft,animMain,animZoom,animAlpha;
	AnimationDrawable frameAnimation;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		this.mHelper = Helper.shareIns(getApplicationContext());
		this.mCover = UI_Cover.shareIns(getApplicationContext());
		
		this.initUIBackGround = this.mCover.initUIBackGround();
		this.initUIBottom = this.mCover.initUIBottom();
		this.initUIInfo = this.mCover.initUIInfo();
		
		this.initUIText = this.mCover.initUIText();
		this.initUIWarning = this.mCover.initUIWarning();
		this.initUIHideBackGround = this.mCover.initUIHideBackGround();
		
		
		initUI();
		activeAnimation();
		preference();
	}
	
	public void activeAnimation(){
		animDown  = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.cover_move_down);
		animDownLeft = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.cover_move_downleft);
		animDownRight = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.cover_move_downright);
		animLeft = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.cover_move_left);
		animMain = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.cover_main);
		animZoom = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.cover_zoom_out);
		animAlpha = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.cover_alpha_out);
		animMain.setAnimationListener(this);
		animZoom.setAnimationListener(this);
	}
	
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		aus.setOnClickListener(this);
	//	auf.setOnClickListener(this);
		unt.setOnClickListener(this);
		vor.setOnClickListener(this);
		ver.setOnClickListener(this);
		home.setOnClickListener(this);
		hide.setOnClickListener(this);
		
	}
	
	
	public void preference(){
		aus = findViewById(Key.linearAusgleichen);
		auf = (PhotoView) findViewById(Key.linearAuflosen);
		auf.setBackgroundResource(R.drawable.cover_auf);
		auf.setOnTouchListener(new OnTouchListener() {
			
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				Log.i("LTH", "onTough");
				auf.setBackgroundResource(R.drawable.btn_auf_cover_active);
				if(event.getAction() == MotionEvent.ACTION_UP){
					
					auf.setBackgroundResource(R.drawable.cover_auf);
					aus.startAnimation(animLeft);
					ver.startAnimation(animDownLeft);
					vor.startAnimation(animDown);
					unt.startAnimation(animDownRight);
					
				//	auf.setAnimation(animMain);
					auf.startAnimation(animZoom);
					return true;
				}
				return true;
			}
		});
	   // AnimationDrawable frameAnimation = (AnimationDrawable) auf.getBackground();
	   // thread.start();
		unt = findViewById(Key.linearUnterstutzen);
		vor = findViewById(Key.linearVorbereiten);
		ver = findViewById(Key.linearVerbessern);
		home = findViewById(Key.HOME);
		hide = findViewById(Key.HEADER);
	}
	
	public void initUI() {

		this.wrapper = new FrameLayout(this.getApplicationContext());
		FrameLayout.LayoutParams para = new FrameLayout.LayoutParams(
				FrameLayout.LayoutParams.MATCH_PARENT,
				FrameLayout.LayoutParams.MATCH_PARENT);
		this.wrapper.setLayoutParams(para);
		
		this.wrapper.addView(initUIBackGround);
		this.wrapper.addView(this.initUIInfo);
		this.wrapper.addView(this.initUIText);
		
		this.wrapper.addView(this.initUIBottom);
		this.wrapper.addView(this.initUIHideBackGround);
		this.wrapper.addView(this.initUIWarning);
		setContentView(this.wrapper);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case Key.linearAuflosen:

			/*Intent intent = new Intent(getApplicationContext(), Home.class);
			clearMemory();
			intent.putExtra("HomeBG", String.valueOf(2));
			startActivity(intent);
			finish();*/
			
			
			
			break;
		case Key.linearAusgleichen:
			Intent intent4 = new Intent(getApplicationContext(), Home.class);
			clearMemory();
			intent4.putExtra("HomeBG", String.valueOf(1));
			startActivity(intent4);
			finish();
			break;
		case Key.linearUnterstutzen:
			Intent intent1 = new Intent(getApplicationContext(), Home.class);
			clearMemory();
			intent1.putExtra("HomeBG", String.valueOf(3));
			startActivity(intent1);
			finish();
			break;
		case Key.linearVerbessern:
			Intent intent2 = new Intent(getApplicationContext(), Home.class);
			clearMemory();
			intent2.putExtra("HomeBG", String.valueOf(4));
			startActivity(intent2);
			finish();
			
			break;
		case Key.linearVorbereiten:
			Intent intent3 = new Intent(getApplicationContext(), Home.class);
			clearMemory();
			intent3.putExtra("HomeBG", String.valueOf(5));
			startActivity(intent3);
			finish();
			
			break;
		case Key.HOME:
			this.initUIWarning.setVisibility(View.VISIBLE);
			this.initUIHideBackGround.setVisibility(View.VISIBLE);
			this.home.setVisibility(View.GONE);
			break;
		case Key.HEADER:
			this.home.setVisibility(View.VISIBLE);
			this.initUIWarning.setVisibility(View.GONE);
			this.initUIHideBackGround.setVisibility(View.GONE);	
			break;

		default:
			break;
		}
	}
	public void clearMemory(){
		aus.setOnClickListener(null);
	//	auf.setOnClickListener(null);
		unt.setOnClickListener(null);
		vor.setOnClickListener(null);
		ver.setOnClickListener(null);
		home.setOnClickListener(null);
		hide.setOnClickListener(null);
	}
	@Override
	public void finish() {
		// TODO Auto-generated method stub
		super.finish();
		this.overridePendingTransition(0, 0);
	}

	@Override
	public void onAnimationStart(Animation animation) {
		// TODO Auto-generated method stub
		/*initUIBackGround.startAnimation(animAlpha);
		initUIText.startAnimation(animAlpha);
		initUIInfo.startAnimation(animAlpha);*/
		if(animation == animZoom){
			Log.i("LTH", "animZoom");
			Intent intent = new Intent(getApplicationContext(), Home.class);
			clearMemory();
			intent.putExtra("HomeBG", String.valueOf(2));
		//	intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
			startActivity(intent);
			finish();
			overridePendingTransition(R.anim.cover_alpha_in,R.anim.cover_alpha_out);
		}
	}

	@Override
	public void onAnimationEnd(Animation animation) {
		// TODO Auto-generated method stub
		//this.auf.startAnimation(animZoom);
		
	}

	@Override
	public void onAnimationRepeat(Animation animation) {
		// TODO Auto-generated method stub
		
	}
	
}
