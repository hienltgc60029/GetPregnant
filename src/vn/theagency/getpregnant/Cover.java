package vn.theagency.getpregnant;

import vn.theagency.helper.Helper;
import vn.theagency.helper.Key;
import vn.theagency.layout.UI_Cover;
import android.R.integer;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.AnimationDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Messenger;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;

public class Cover extends Activity implements OnClickListener,OnTouchListener{

	private Helper mHelper;
	public UI_Cover mCover;
	public FrameLayout wrapper;

	public View initUIBackGround;
	public ImageView initUIInfo;
	public FrameLayout initUIBottom;
	public View initUIText;

	View aus, unt, vor, ver, home, hide,auf;
	View aus1, unt1, vor1, ver1, auf1;

	View initUIWarning, initUIHideBackGround;
	Animation animDown, animDownLeft, animDownRight, animLeft,
	animZoom, animAlpha, animFlipLeft, animFlipRight, animMoveRight;
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

	public void activeAnimation() {
		animDown = AnimationUtils.loadAnimation(getApplicationContext(),
				R.anim.cover_move_down);
		animDownLeft = AnimationUtils.loadAnimation(getApplicationContext(),
				R.anim.cover_move_downleft);
		animDownRight = AnimationUtils.loadAnimation(getApplicationContext(),
				R.anim.cover_move_downright);
		animLeft = AnimationUtils.loadAnimation(getApplicationContext(),
				R.anim.cover_move_left);
		
		animZoom = AnimationUtils.loadAnimation(getApplicationContext(),
				R.anim.cover_zoom_out);
		animAlpha = AnimationUtils.loadAnimation(getApplicationContext(),
				R.anim.cover_alpha_out);
		
		
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
	//	aus.setOnClickListener(this);
		 auf.setOnClickListener(this);
	//	unt.setOnClickListener(this);
	//	vor.setOnClickListener(this);
	//	ver.setOnClickListener(this);
	//	home.setOnClickListener(this);
	//	hide.setOnClickListener(this);
	//	auf.setOnTouchListener(this);

	}

	public void preference() {
		aus = findViewById(Key.linearAusgleichen);
		auf = findViewById(Key.linearAuflosen);
		auf1 = findViewById(Key.auflosen);
	
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
		SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        if(!prefs.getBoolean("first_time", false))
        {
            SharedPreferences.Editor editor = prefs.edit();
            editor.putBoolean("first_time", true);
            editor.commit();
            this.initUIHideBackGround.setVisibility(View.VISIBLE);
            this.initUIWarning.setVisibility(View.VISIBLE);
        }else{
        	this.initUIHideBackGround.setVisibility(View.GONE);
            this.initUIWarning.setVisibility(View.GONE);
        }
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case Key.linearAuflosen:	
			auf.setBackgroundResource(R.drawable.icon_btn_auf_active);
			new CoverAnimation(auf, R.anim.cover_flip_right).execute(0);
			
			
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

	public void clearMemory() {
		aus.setOnClickListener(null);
		 auf.setOnClickListener(null);
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


	
	Handler handler = new Handler(){

		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			super.handleMessage(msg);
			switch (msg.what) {
			case R.anim.cover_flip_right:
				new CoverAnimation(auf,R.anim.cover_flip_left).execute(0);
				break;
			case R.anim.cover_move_left:
				new CoverAnimation(ver,R.anim.cover_move_right).execute(0);
				new CoverAnimation(vor,R.anim.cover_move_right).execute(0);
				new CoverAnimation(unt,R.anim.cover_move_right).execute(0);
				break;
			case R.anim.cover_move_right:
				auf1.setVisibility(View.GONE);
				new CoverAnimation(auf, R.anim.cover_zoom_out).execute(250);
				new CoverAnimation(wrapper, R.anim.cover_alpha_out).execute(0);
				break;
			case R.anim.cover_zoom_out:
				Intent intent = new Intent(getApplicationContext(), Home.class);
				 intent.putExtra("HomeBG", String.valueOf(2));
				 intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
				 startActivity(intent); 
				 clearMemory(); 
				 finish();
				 overridePendingTransition(0,0);
				break;
			case R.anim.cover_flip_left:
				
				new CoverAnimation(aus,R.anim.cover_move_left).execute(0);
				break;
			default:
				break;
			}
			
			
		}
		
	};
	
	public class CoverAnimation extends AsyncTask<Integer, Integer, Void>{

		View viewAnimation;
		Animation anim;
		int animRes;
		
		
		public CoverAnimation(View viewAnimation, int animRes) {
			super();
			this.viewAnimation = viewAnimation;
			this.animRes = animRes;
			anim = AnimationUtils.loadAnimation(Cover.this,
					animRes);
		}

		@Override
		protected Void doInBackground(final Integer... params) {
			// TODO Auto-generated method stub
			runOnUiThread(new Runnable() {
				
				@Override
				public void run() {
					// TODO Auto-generated method stub
					viewAnimation.clearAnimation();
					viewAnimation.setAnimation(anim);
					viewAnimation.startAnimation(anim);	
					handler.sendEmptyMessageDelayed(animRes, (anim.getDuration()-params[0]));
				}
			});
			return null;
		}				
	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		switch (v.getId()) {
		case Key.linearAuflosen:	
			if(event.getAction() == MotionEvent.ACTION_DOWN){
				
				
			}
			
			return true;
		//	new CoverAnimation(aus,R.anim.cover_move_left).execute();
			
		case Key.linearAusgleichen:		
			break;
		case Key.linearUnterstutzen:
			break;
		case Key.linearVerbessern:
			break;
		case Key.linearVorbereiten:
			break;
		case Key.HOME:
			break;
		case Key.HEADER:
			break;

		default:
			break;
		}
		return true;
	}
	

}
