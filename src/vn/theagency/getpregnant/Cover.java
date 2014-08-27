package vn.theagency.getpregnant;

import vn.theagency.helper.Helper;
import vn.theagency.helper.Key;
import vn.theagency.layout.UI_Cover;
import android.R.integer;
import android.animation.Animator;
import android.animation.Animator.AnimatorListener;
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
import android.view.animation.LinearInterpolator;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;
import vn.theagency.getpregnantapplication.R;
public class Cover extends Activity implements OnClickListener,OnTouchListener{

	private Helper mHelper;
	public UI_Cover mCover;
	public FrameLayout wrapper;

	public View initUIBackGround;
	public FrameLayout initUIInfo;
	public FrameLayout initUIBottom;
	public View initUIText;
	ImageView info;
	View aus, unt, vor, ver, home, hide,auf;
	View aus1, unt1, vor1, ver1, auf1;

	View warning, initUIHideBackGround;
	FrameLayout initUIWarning;
	boolean isInfo = true;
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
		info = (ImageView) findViewById(Key.HOME);
		warning = findViewById(Key.WARNING);
		preference();
	}

	

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		aus.setOnClickListener(this);
		 auf.setOnClickListener(this);
		unt.setOnClickListener(this);
		vor.setOnClickListener(this);
		ver.setOnClickListener(this);
		home.setOnClickListener(this);
		hide.setOnClickListener(this);
	

	}

	public void preference() {
		aus = findViewById(Key.linearAusgleichen);
		auf = findViewById(Key.linearAuflosen);
		
	
		unt = findViewById(Key.linearUnterstutzen);
		vor = findViewById(Key.linearVorbereiten);
		ver = findViewById(Key.linearVerbessern);
		home = findViewById(Key.HOME);
		hide = findViewById(Key.HEADER);
		auf1 = findViewById(Key.auflosen);
		aus1 = findViewById(Key.ausgleichen);
		unt1 = findViewById(Key.unterstutzen);
		ver1 = findViewById(Key.verbessern);
		vor1 = findViewById(Key.vorbereiten);
		
	}

	public void initUI() {

		this.wrapper = new FrameLayout(this.getApplicationContext());
		FrameLayout.LayoutParams para = new FrameLayout.LayoutParams(
				FrameLayout.LayoutParams.MATCH_PARENT,
				FrameLayout.LayoutParams.MATCH_PARENT);
		this.wrapper.setLayoutParams(para);

		this.wrapper.addView(initUIBackGround);
		
		this.wrapper.addView(this.initUIText);

		this.wrapper.addView(this.initUIBottom);
		this.wrapper.addView(this.initUIHideBackGround);
		this.wrapper.addView(this.initUIWarning);
		this.wrapper.addView(this.initUIInfo);
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
			new CoverAnimation(auf, R.anim.cover_flip_right,auf.getId()).execute(0);
			new CoverAnimation(aus,R.anim.cover_move_left,auf.getId()).execute(0);
			aus1.setVisibility(View.GONE);
			break;
		case Key.linearAusgleichen:
			aus.setBackgroundResource(R.drawable.icon_btn_ausg_active);
			new CoverAnimation(aus, R.anim.cover_flip_right,Key.linearAusgleichen).execute(0);
			new CoverAnimation(auf,R.anim.cover_move_right,Key.linearAusgleichen).execute(0);
			auf1.setVisibility(View.GONE);	
			break;
		case Key.linearUnterstutzen:
			unt.setBackgroundResource(R.drawable.icon_btn_unt_active);
			new CoverAnimation(unt, R.anim.cover_flip_right,Key.linearUnterstutzen).execute(0);
			new CoverAnimation(auf,R.anim.cover_move_right,Key.linearUnterstutzen).execute(0);
			new CoverAnimation(aus,R.anim.cover_move_right01,Key.linearUnterstutzen).execute(0);
			auf1.setVisibility(View.GONE);	
			aus1.setVisibility(View.GONE);	
			
			//
			
			break;
		case Key.linearVerbessern:
			ver.setBackgroundResource(R.drawable.icon_btn_ver_active);
			new CoverAnimation(ver, R.anim.cover_flip_right,Key.linearVerbessern).execute(0);
			new CoverAnimation(auf,R.anim.cover_move_left,Key.linearVerbessern).execute(0);
			new CoverAnimation(aus,R.anim.cover_move_left01,Key.linearVerbessern).execute(0);
			auf1.setVisibility(View.GONE);	
			aus1.setVisibility(View.GONE);	
			
			
			//
			

			break;
		case Key.linearVorbereiten:
			vor.setBackgroundResource(R.drawable.icon_btn_vor_active);
			new CoverAnimation(vor, R.anim.cover_flip_right,Key.linearVorbereiten).execute(0);
			new CoverAnimation(ver,R.anim.cover_move_left,Key.linearVorbereiten).execute(0);
			new CoverAnimation(aus,R.anim.cover_move_left01,Key.linearVorbereiten).execute(0);
			new CoverAnimation(auf,R.anim.cover_move_right,Key.linearVorbereiten).execute(0);
			new CoverAnimation(unt,R.anim.cover_move_right01,Key.linearVorbereiten).execute(0);
			auf1.setVisibility(View.GONE);	
			aus1.setVisibility(View.GONE);
			ver1.setVisibility(View.GONE);
			unt1.setVisibility(View.GONE);
			
			
			

			break;
		case Key.HOME:
		
			if(isInfo){
				this.info.animate().xBy(0).translationX((int)(mHelper.getAppWidth()/2 - mCover.animationInfo)).setDuration(250).setListener(new AnimatorListener() {
					
					@Override
					public void onAnimationStart(Animator animation) {
						// TODO Auto-generated method stub
						isInfo = false;
					}
					
					@Override
					public void onAnimationRepeat(Animator animation) {
						// TODO Auto-generated method stub
						
					}
					
					@Override
					public void onAnimationEnd(Animator animation) {
						// TODO Auto-generated method stub
						info.animate().rotationYBy(0).rotationY(90).setDuration(250).setListener(new AnimatorListener() {
							
							@Override
							public void onAnimationStart(Animator animation) {
								// TODO Auto-generated method stub
								
							}
							
							@Override
							public void onAnimationRepeat(Animator animation) {
								// TODO Auto-generated method stub
								
							}
							
							@Override
							public void onAnimationEnd(Animator animation) {
								// TODO Auto-generated method stub
								info.setBackgroundResource(R.drawable.btn_close);
								
								
								
								info.animate().rotationYBy(90).rotationY(180).setDuration(250).setListener(new AnimatorListener() {
									
									@Override
									public void onAnimationStart(Animator animation) {
										// TODO Auto-generated method stub
										initUIHideBackGround.setVisibility(View.VISIBLE);
										initUIHideBackGround.setAlpha(0.5f);
									}
									
									@Override
									public void onAnimationRepeat(Animator animation) {
										// TODO Auto-generated method stub
										
									}
									
									@Override
									public void onAnimationEnd(Animator animation) {
										// TODO Auto-generated method stub
										initUIWarning.clearAnimation();
										initUIWarning.setVisibility(View.VISIBLE);
									//	warning.setVisibility(View.VISIBLE);
										warning.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.auswahl_down));
									//	new CoverAnimation(initUIWarning, R.anim.auswahl_down, initUIWarning.getId());
										
										
									//	new CoverAnimation(initUIWarning, R.anim.auswahl_down, initUIWarning.getId());
									}
									
									@Override
									public void onAnimationCancel(Animator animation) {
										// TODO Auto-generated method stub
										
									}
								}).start();
							}
							
							@Override
							public void onAnimationCancel(Animator animation) {
								// TODO Auto-generated method stub
								
							}
						}).start();
					}
					
					@Override
					public void onAnimationCancel(Animator animation) {
						// TODO Auto-generated method stub
						
					}
				}).start();
			}else{
				isInfo = true;
				this.info.clearAnimation();
				info.animate().rotationYBy(0).rotationY(180).setDuration(250).setListener(new AnimatorListener() {
					
					@Override
					public void onAnimationStart(Animator animation) {
						// TODO Auto-generated method stub
						
					}
					
					@Override
					public void onAnimationRepeat(Animator animation) {
						// TODO Auto-generated method stub
						
					}
					
					@Override
					public void onAnimationEnd(Animator animation) {
						// TODO Auto-generated method stub
						info.setBackgroundResource(R.drawable.btn_cover_info);
						info.animate().rotationYBy(180).rotationY(360).setDuration(250).setListener(new AnimatorListener() {
							
							@Override
							public void onAnimationStart(Animator animation) {
								// TODO Auto-generated method stub
								
							}
							
							@Override
							public void onAnimationRepeat(Animator animation) {
								// TODO Auto-generated method stub
								
							}
							
							@Override
							public void onAnimationEnd(Animator animation) {
								// TODO Auto-generated method stub
								
								info.animate().translationX(0).setDuration(250).setListener(new AnimatorListener() {
									
									@Override
									public void onAnimationStart(Animator animation) {
										// TODO Auto-generated method stub
										
									}
									
									@Override
									public void onAnimationRepeat(Animator animation) {
										// TODO Auto-generated method stub
										
									}
									
									@Override
									public void onAnimationEnd(Animator animation) {
										// TODO Auto-generated method stub
										warning.setVisibility(View.GONE);
									}
									
									@Override
									public void onAnimationCancel(Animator animation) {
										// TODO Auto-generated method stub
										
									}
								}).start();
							}
							
							@Override
							public void onAnimationCancel(Animator animation) {
								// TODO Auto-generated method stub
								
							}
						}).start();
					}
					
					@Override
					public void onAnimationCancel(Animator animation) {
						// TODO Auto-generated method stub
						
					}
				}).start();
				
				
				this.home.setVisibility(View.VISIBLE);
				this.initUIWarning.setVisibility(View.GONE);
				this.initUIHideBackGround.setVisibility(View.GONE);
			}
			
			
			
			
			/*this.initUIWarning.setVisibility(View.VISIBLE);
			this.initUIHideBackGround.setVisibility(View.VISIBLE);
			this.home.setVisibility(View.GONE);*/
			break;
		case Key.HEADER:
			isInfo = true;
			this.info.clearAnimation();
			info.animate().rotationYBy(0).rotationY(180).setDuration(250).setListener(new AnimatorListener() {
				
				@Override
				public void onAnimationStart(Animator animation) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void onAnimationRepeat(Animator animation) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void onAnimationEnd(Animator animation) {
					// TODO Auto-generated method stub
					info.setBackgroundResource(R.drawable.btn_cover_info);
					info.animate().rotationYBy(180).rotationY(360).setDuration(250).setListener(new AnimatorListener() {
						
						@Override
						public void onAnimationStart(Animator animation) {
							// TODO Auto-generated method stub
							
						}
						
						@Override
						public void onAnimationRepeat(Animator animation) {
							// TODO Auto-generated method stub
							
						}
						
						@Override
						public void onAnimationEnd(Animator animation) {
							// TODO Auto-generated method stub
							
							info.animate().translationX(0).setDuration(250).setListener(new AnimatorListener() {
								
								@Override
								public void onAnimationStart(Animator animation) {
									// TODO Auto-generated method stub
									
								}
								
								@Override
								public void onAnimationRepeat(Animator animation) {
									// TODO Auto-generated method stub
									
								}
								
								@Override
								public void onAnimationEnd(Animator animation) {
									// TODO Auto-generated method stub
									warning.setVisibility(View.GONE);
								}
								
								@Override
								public void onAnimationCancel(Animator animation) {
									// TODO Auto-generated method stub
									
								}
							}).start();
						}
						
						@Override
						public void onAnimationCancel(Animator animation) {
							// TODO Auto-generated method stub
							
						}
					}).start();
				}
				
				@Override
				public void onAnimationCancel(Animator animation) {
					// TODO Auto-generated method stub
					
				}
			}).start();
			
			
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
			
			switch (msg.arg2) {
			case Key.linearAuflosen:
				if(msg.arg1==R.anim.cover_flip_right){
					new CoverAnimation(auf,R.anim.cover_flip_left,auf.getId()).execute(0);
				}
				if(msg.arg1==R.anim.cover_move_left){
					new CoverAnimation(ver,R.anim.cover_move_right,auf.getId()).execute(0);
					new CoverAnimation(vor,R.anim.cover_move_right01,auf.getId()).execute(0);
					new CoverAnimation(unt,R.anim.cover_move_right02,auf.getId()).execute(0);
					ver1.setVisibility(View.GONE);
					vor1.setVisibility(View.GONE);
					unt1.setVisibility(View.GONE);
				}
				if(msg.arg1==R.anim.cover_move_right){
					auf1.setVisibility(View.GONE);
					new CoverAnimation(auf, R.anim.cover_zoom_out,auf.getId()).execute(250);
					initUIText.setVisibility(View.GONE);
					new CoverAnimation(wrapper, R.anim.cover_alpha_out,auf.getId()).execute(0);
					
				}
				if(msg.arg1==R.anim.cover_zoom_out){
					Intent intent = new Intent(getApplicationContext(), Home.class);
					 intent.putExtra("HomeBG", String.valueOf(2));
					
					 startActivity(intent); 
					 clearMemory(); 
					 finish();
					 overridePendingTransition(R.anim.alpha_in,R.anim.alpha_in);
				}
				break;
			case Key.linearAusgleichen:
				if(msg.arg1==R.anim.cover_flip_right){
					new CoverAnimation(aus,R.anim.cover_flip_left,Key.linearAusgleichen).execute(0);
				}
				if(msg.arg1==R.anim.cover_move_right){
					new CoverAnimation(ver,R.anim.cover_move_left,Key.linearAusgleichen).execute(0);
					new CoverAnimation(vor,R.anim.cover_move_left01,Key.linearAusgleichen).execute(0);
					new CoverAnimation(unt,R.anim.cover_move_left02,Key.linearAusgleichen).execute(0);
					ver1.setVisibility(View.GONE);
					vor1.setVisibility(View.GONE);
					unt1.setVisibility(View.GONE);	
				}
				if(msg.arg1==R.anim.cover_move_left){
					aus1.setVisibility(View.GONE);
					new CoverAnimation(aus, R.anim.cover_zoom_out,Key.linearAusgleichen).execute(250);
					initUIText.setVisibility(View.GONE);
					new CoverAnimation(wrapper, R.anim.cover_alpha_out,Key.linearAusgleichen).execute(0);	
				}
				if(msg.arg1==R.anim.cover_zoom_out){
					Intent intent = new Intent(getApplicationContext(), Home.class);
					 intent.putExtra("HomeBG", String.valueOf(1));
					 intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
					 startActivity(intent); 
					 clearMemory(); 
					 finish();
					 overridePendingTransition(0,0);
				}
				break;
			case Key.linearUnterstutzen:
				if(msg.arg1==R.anim.cover_flip_right){
					new CoverAnimation(unt,R.anim.cover_flip_left,Key.linearUnterstutzen).execute(0);
				}
				if(msg.arg1==R.anim.cover_move_right){
					new CoverAnimation(ver,R.anim.cover_move_left,Key.linearUnterstutzen).execute(0);
					new CoverAnimation(vor,R.anim.cover_move_left01,Key.linearUnterstutzen).execute(0);
					ver1.setVisibility(View.GONE);
					vor1.setVisibility(View.GONE);		
				}
				if(msg.arg1==R.anim.cover_move_left){
					unt1.setVisibility(View.GONE);
					new CoverAnimation(unt, R.anim.cover_zoom_out,Key.linearUnterstutzen).execute(250);
					initUIText.setVisibility(View.GONE);
					new CoverAnimation(wrapper, R.anim.cover_alpha_out,Key.linearUnterstutzen).execute(0);	
				}
				if(msg.arg1==R.anim.cover_zoom_out){
					Intent intent = new Intent(getApplicationContext(), Home.class);
					 intent.putExtra("HomeBG", String.valueOf(3));
					 intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
					 startActivity(intent); 
					 clearMemory(); 
					 finish();
					 overridePendingTransition(0,0);
				}
				break;
			case Key.linearVerbessern:
				if(msg.arg1==R.anim.cover_flip_right){
					new CoverAnimation(ver,R.anim.cover_flip_left,Key.linearVerbessern).execute(0);
				}
				if(msg.arg1==R.anim.cover_move_left){
					new CoverAnimation(unt,R.anim.cover_move_right,Key.linearVerbessern).execute(0);
					new CoverAnimation(vor,R.anim.cover_move_right01,Key.linearVerbessern).execute(0);
					unt1.setVisibility(View.GONE);
					vor1.setVisibility(View.GONE);		
				}
				if(msg.arg1==R.anim.cover_move_right){
					ver1.setVisibility(View.GONE);
					new CoverAnimation(ver, R.anim.cover_zoom_out,Key.linearVerbessern).execute(250);
					initUIText.setVisibility(View.GONE);
					new CoverAnimation(wrapper, R.anim.cover_alpha_out,Key.linearVerbessern).execute(0);	
				}
				if(msg.arg1==R.anim.cover_zoom_out){
					Intent intent = new Intent(getApplicationContext(), Home.class);
					 intent.putExtra("HomeBG", String.valueOf(4));
					 intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
					 startActivity(intent); 
					 clearMemory(); 
					 finish();
					 overridePendingTransition(0,0);
				}
				break;
			case Key.linearVorbereiten:
				if(msg.arg1==R.anim.cover_flip_right){
					new CoverAnimation(vor,R.anim.cover_flip_left,Key.linearVorbereiten).execute(0);
				}
				if(msg.arg1==R.anim.cover_flip_left){
					vor1.setVisibility(View.GONE);
					new CoverAnimation(vor, R.anim.cover_zoom_out,Key.linearVorbereiten).execute(250);
					initUIText.setVisibility(View.GONE);
					new CoverAnimation(wrapper, R.anim.cover_alpha_out,Key.linearVorbereiten).execute(0);	
				}
				if(msg.arg1==R.anim.cover_zoom_out){
					Intent intent = new Intent(getApplicationContext(), Home.class);
					 intent.putExtra("HomeBG", String.valueOf(5));
					 intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
					 startActivity(intent); 
					 clearMemory(); 
					 finish();
					 overridePendingTransition(0,0);
				}
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
		int viewParentId;
		
		
		public CoverAnimation(View viewAnimation, int animRes,int viewParentId) {
			super();
			this.viewAnimation = viewAnimation;
			this.animRes = animRes;
			anim = AnimationUtils.loadAnimation(Cover.this,
					animRes);
			this.viewParentId = viewParentId;
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
					
					Message msg = new Message();
					msg.arg1 = animRes;
					msg.arg2 = viewParentId;
					
					handler.sendMessageDelayed(msg, (anim.getDuration()-params[0]));
					
				//	handler.sendEmptyMessageDelayed(animRes, (anim.getDuration()-params[0]));
					
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
