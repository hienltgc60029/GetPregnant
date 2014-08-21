package vn.theagency.fragment;

import vn.theagency.getpregnant.Home;
import vn.theagency.getpregnant.R;
import vn.theagency.helper.Helper;
import vn.theagency.helper.Key;
import vn.theagency.layout.UI_Home;
import android.animation.Animator;
import android.animation.Animator.AnimatorListener;
import android.app.Fragment;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Messenger;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.view.animation.ScaleAnimation;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Fragment_Home extends Fragment implements OnClickListener,
		AnimationListener {

	View view = null;
	public String textTitle, textDec;
	public FrameLayout wrapper, layoutTitles, initUIText;
	public UI_Home titles;
	LinearLayout layout;
	FrameLayout linearAuflosen, linearAusgleichen, linearVerbessern,
			linearVorbereiten, linearUnterstutzen;
	FrameLayout initUIAddition;
	private Helper mHelper;
	LinearLayout linearLayout;
	ImageView imgAusgleichen, imgAuflosen, imgVerbessern, imgVorbereiten,
			imgUnterstutzen, initUIHome;
	View plus;
	ImageView home;
	Messenger messenger;
	Message msg;
	TextView plusText;
	View bg, frist;
	Animation plusMove, plusUp,homeUp,zoomIn,plusFlip,plusTextMove,titleBounce,flipLeft,slideUp;
	boolean isFrist;
	ImageView mTitle;

	public static Fragment_Home newInstance(int pTop, int bg, int background,
			int pTitle, boolean isFrist) {
		
		Fragment_Home f = new Fragment_Home();
		Bundle args = new Bundle();
		args.putInt("TOP", pTop);
		args.putInt("BG", bg);
		args.putBoolean("Animation", isFrist);
		args.putInt("background", background);
		args.putInt("pTitle", pTitle);
		f.setArguments(args);
		return f;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		this.mHelper = Helper.shareIns(getActivity());
		this.titles = UI_Home.shareIns(getActivity());
		Home home = (Home) getActivity();
		messenger = home.getMessenger();
		msg = new Message();

		layoutTitles = this.titles.initUI(getArguments().getInt("pTitle"));
		initUIAddition = this.titles.initUIAddition(R.drawable.btn_ver_normal);
		initUIText = this.titles.initUIText(getArguments().getInt("TOP"),
				getArguments().getInt("BG"));
		initUIHome = this.titles.initUIHome();
		this.initUI();
		return wrapper;
	}

	public void preference() {

		linearAuflosen = (FrameLayout) wrapper.findViewById(Key.linearAuflosen);
		linearAusgleichen = (FrameLayout) wrapper
				.findViewById(Key.linearAusgleichen);
		linearVerbessern = (FrameLayout) wrapper
				.findViewById(Key.linearVerbessern);
		linearVorbereiten = (FrameLayout) wrapper
				.findViewById(Key.linearVorbereiten);
		linearUnterstutzen = (FrameLayout) wrapper
				.findViewById(Key.linearUnterstutzen);
		this.mTitle = (ImageView) this.wrapper.findViewById(Key.TITLE);

		switch (getArguments().getInt("background")) {
		case R.drawable.bg:
			linearAusgleichen.setVisibility(FrameLayout.GONE);
			break;
		case R.drawable.bg_auf:
			linearAuflosen.setVisibility(FrameLayout.GONE);
			break;
		case R.drawable.bg_unt:
			linearUnterstutzen.setVisibility(FrameLayout.GONE);
			break;
		case R.drawable.bg_ver:
			linearVerbessern.setVisibility(FrameLayout.GONE);
			break;
		case R.drawable.bg_vor:
			linearVorbereiten.setVisibility(FrameLayout.GONE);
			break;

		default:
			break;
		}

		imgAuflosen = (ImageView) wrapper.findViewById(Key.auflosen);
		imgAusgleichen = (ImageView) wrapper.findViewById(Key.ausgleichen);
		imgUnterstutzen = (ImageView) wrapper.findViewById(Key.unterstutzen);
		imgVerbessern = (ImageView) wrapper.findViewById(Key.verbessern);
		imgVorbereiten = (ImageView) wrapper.findViewById(Key.vorbereiten);
		plusText = (TextView) wrapper.findViewById(Key.AUDIOS_NAME);

	}

	public void initUI() {

		this.wrapper = new FrameLayout(getActivity());
		FrameLayout.LayoutParams para = new FrameLayout.LayoutParams(
				FrameLayout.LayoutParams.MATCH_PARENT,
				FrameLayout.LayoutParams.MATCH_PARENT);
		this.wrapper.setLayoutParams(para);
		this.wrapper.setBackgroundColor(Color.BLACK);
		// this.wrapper.setBackgroundResource(R.drawable.bg);
		// Image BG
		frist = new View(getActivity());
		FrameLayout.LayoutParams paraBg = new FrameLayout.LayoutParams(
				FrameLayout.LayoutParams.MATCH_PARENT,
				FrameLayout.LayoutParams.MATCH_PARENT);
		paraBg.topMargin = (int) this.mHelper.DpToPixel(92);
		frist.setBackgroundResource(R.drawable.bg_list);
		frist.setLayoutParams(paraBg);
		FrameLayout.LayoutParams bgPara = new FrameLayout.LayoutParams(
				FrameLayout.LayoutParams.MATCH_PARENT,
				FrameLayout.LayoutParams.MATCH_PARENT);
		bg = new View(getActivity());

		bg.setBackgroundResource(getArguments().getInt("background"));
		bg.setLayoutParams(bgPara);
		//
		
		// Image Home
		
		this.wrapper.addView(frist);
		this.wrapper.addView(bg);
		this.wrapper.addView(initUIHome);
		this.wrapper.addView(initUIText);
		this.wrapper.addView(initUIAddition);
		//

		this.wrapper.addView(layoutTitles);
		
		setMyAnimation();
		// getActivity().setContentView(this.wrapper);
		this.isFrist = getArguments().getBoolean("Animation");

	
		
		preference();
		
		plus = wrapper.findViewById(Key.AUSWAHL);
		if(isFrist){
			plusText.setVisibility(View.INVISIBLE);
			this.initUIAddition.startAnimation(plusMove);
			
		//	plus.startAnimation(plusMove);
			
			
		//	this.initUIAddition.startAnimation(plusUp);
		}else{
			plusText.setText("AUSWAHL");
			plus.startAnimation(plusFlip);
		}
		
		this.mTitle.startAnimation(titleBounce);
	//	handler2.sendEmptyMessage(1);
		
		
		plus.setOnClickListener(this);

		
		plusText.setOnClickListener(this);

		imgAuflosen.setOnClickListener(this);
		imgAusgleichen.setOnClickListener(this);
		imgUnterstutzen.setOnClickListener(this);
		imgVerbessern.setOnClickListener(this);
		imgVorbereiten.setOnClickListener(this);

		home = (ImageView) wrapper.findViewById(Key.HOME);
		home.setOnClickListener(this);

	}

	public void setMyAnimation() {
		plusMove = AnimationUtils.loadAnimation(getActivity(),
				R.anim.home_move_right50);
		plusUp = AnimationUtils.loadAnimation(getActivity(),
				R.anim.home_plus_up);
		homeUp = AnimationUtils.loadAnimation(getActivity(), R.anim.home_up);
		zoomIn = AnimationUtils.loadAnimation(getActivity(), R.anim.home_zoom_in);
		plusFlip = AnimationUtils.loadAnimation(getActivity(), R.anim.home_plus_flip);
		plusTextMove = AnimationUtils.loadAnimation(getActivity(), R.anim.home_move_plustext);
		titleBounce = AnimationUtils.loadAnimation(getActivity(), R.anim.home_bounce);
		slideUp = AnimationUtils.loadAnimation(getActivity(), R.anim.home_slideup);
		plusUp.setAnimationListener(this);
		plusMove.setAnimationListener(this);
	}
	

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		clearMemory();
		switch (v.getId()) {
		case Key.auflosen:

			try {
				
				clearMemory();
				msg.arg1 = 2;
				msg.arg2 = 0;
				messenger.send(msg);
			} catch (Exception ex) {
				ex.printStackTrace();
			}

			break;
		case Key.ausgleichen:
			try {
				
				clearMemory();
				msg.arg1 = 1;
				msg.arg2 = 0;
				messenger.send(msg);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			break;
		case Key.unterstutzen:
			try {
				
				clearMemory();
				msg.arg1 = 3;
				msg.arg2 = 0;
				messenger.send(msg);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			break;
		case Key.verbessern:
			try {
				
				clearMemory();
				msg.arg1 = 4;
				msg.arg2 = 0;
				messenger.send(msg);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			break;
		case Key.vorbereiten:
			try {
				
				clearMemory();
				msg.arg1 = 5;
				msg.arg2 = 0;
				messenger.send(msg);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			break;
		case Key.AUSWAHL:
			
			plus.clearAnimation();
			getActivity().runOnUiThread(new Runnable() {
				
				@Override
				public void run() {
					// TODO Auto-generated method stub
					
				//	initUIAddition.startAnimation(plusUp);
					
					initUIAddition.animate().translationYBy(0).translationY(0-titles.animUpPlus).setDuration(800).setListener(new AnimatorListener() {
						
						@Override
						public void onAnimationStart(Animator animation) {
							// TODO Auto-generated method stub
							
								getActivity().runOnUiThread(new Runnable() {
									
									@Override
									public void run() {
										// TODO Auto-generated method stub
										initUIHome.setVisibility(View.GONE);
										initUIText.setVisibility(View.GONE);
										bg.startAnimation(homeUp);
									
									}
								});
								
						
						}
						
						@Override
						public void onAnimationRepeat(Animator animation) {
							// TODO Auto-generated method stub
							
						}
						
						@Override
						public void onAnimationEnd(Animator animation) {
							// TODO Auto-generated method stub
								layoutTitles.animate().translationYBy(0).translationY(100).setDuration(200).start();
								getActivity().runOnUiThread(new Runnable() {
									
									@Override
									public void run() {
										plus.animate().rotationYBy(0).rotationY(90).setDuration(250).setListener(new AnimatorListener() {
											
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
												plus.setBackgroundResource(R.drawable.btn_slip);
												plus.animate().rotationYBy(90).rotationY(180).setDuration(250).setListener(new AnimatorListener() {
													
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
														try {
															
															clearMemory();

															switch (getArguments().getInt("background")) {
															case R.drawable.bg:
																msg.arg2 = 1;
																break;
															case R.drawable.bg_auf:
																msg.arg2 = 2;
																break;
															case R.drawable.bg_unt:
																msg.arg2 = 3;
																break;
															case R.drawable.bg_ver:
																msg.arg2 = 4;
																break;
															case R.drawable.bg_vor:
																msg.arg2 = 5;
																break;

															default:
																break;
															}

															msg.arg1 = 6;
															messenger.send(msg);
														} catch (Exception ex) {
															ex.printStackTrace();
														}

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
								});
								
							}
							
						
						
						@Override
						public void onAnimationCancel(Animator animation) {
							// TODO Auto-generated method stub
							
						}
					}).start();
				//	plus.startAnimation(plusUp);
					
				}
			});
			
			
			
			break;
		case Key.AUDIOS_NAME:
			plus.clearAnimation();
			getActivity().runOnUiThread(new Runnable() {
				
				@Override
				public void run() {
					// TODO Auto-generated method stub
					
				//	initUIAddition.startAnimation(plusUp);
					
					initUIAddition.animate().translationYBy(0).translationY(0-titles.animUpPlus).setDuration(800).setListener(new AnimatorListener() {
						
						@Override
						public void onAnimationStart(Animator animation) {
							// TODO Auto-generated method stub
							
								getActivity().runOnUiThread(new Runnable() {
									
									@Override
									public void run() {
										// TODO Auto-generated method stub
										initUIHome.setVisibility(View.GONE);
										initUIText.setVisibility(View.GONE);
										bg.startAnimation(homeUp);
									
									}
								});
								
						
						}
						
						@Override
						public void onAnimationRepeat(Animator animation) {
							// TODO Auto-generated method stub
							
						}
						
						@Override
						public void onAnimationEnd(Animator animation) {
							// TODO Auto-generated method stub
							
								getActivity().runOnUiThread(new Runnable() {
									
									@Override
									public void run() {
										plus.animate().rotationYBy(0).rotationY(90).setDuration(250).setListener(new AnimatorListener() {
											
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
												plus.setBackgroundResource(R.drawable.btn_slip);
												plus.animate().rotationYBy(90).rotationY(180).setDuration(250).setListener(new AnimatorListener() {
													
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
														try {
															
															clearMemory();

															switch (getArguments().getInt("background")) {
															case R.drawable.bg:
																msg.arg2 = 1;
																break;
															case R.drawable.bg_auf:
																msg.arg2 = 2;
																break;
															case R.drawable.bg_unt:
																msg.arg2 = 3;
																break;
															case R.drawable.bg_ver:
																msg.arg2 = 4;
																break;
															case R.drawable.bg_vor:
																msg.arg2 = 5;
																break;

															default:
																break;
															}

															msg.arg1 = 6;
															messenger.send(msg);
														} catch (Exception ex) {
															ex.printStackTrace();
														}

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
								});
								
							}
							
						
						
						@Override
						public void onAnimationCancel(Animator animation) {
							// TODO Auto-generated method stub
							
						}
					}).start();
				//	plus.startAnimation(plusUp);
					
				}
			});
			
			
			break;
		case Key.HOME:
			  try { 
				  msg.arg1 = 7; 
				  msg.arg2 = 0;
				  messenger.send(msg); 
				  } catch (Exception ex) {
					  ex.printStackTrace(); 
			  }
			 
			break;

		default:
			break;
		}
	}

	public void clearMemory() {
		imgAuflosen.setOnClickListener(null);
		imgAusgleichen.setOnClickListener(null);
		imgUnterstutzen.setOnClickListener(null);
		imgVerbessern.setOnClickListener(null);
		imgVorbereiten.setOnClickListener(null);
		plus.setOnClickListener(null);
	}

	Handler handler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			super.handleMessage(msg);
			switch (msg.what) {
			case R.anim.cover_move_left:
				break;
			case R.anim.cover_move_right:

			default:
				break;
			}

		}

	};

	@Override
	public void onAnimationStart(Animation animation) {
		// TODO Auto-generated method stub
	}

	@Override
	public void onAnimationEnd(Animation animation) {
		// TODO Auto-generated method stub
		
		if(animation == plusMove){
			plusText.setVisibility(View.VISIBLE);
			plusText.setText("AUSWAHL");
			plusText.startAnimation(plusTextMove);
			plus.startAnimation(plusFlip);
			
			
		}
		
			
		
		
		
	}

	@Override
	public void onAnimationRepeat(Animation animation) {
		// TODO Auto-generated method stub
		
	}

	

}
