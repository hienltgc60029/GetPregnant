package vn.theagency.getpregnant;

import java.util.ArrayList;

import vn.theagency.helper.Audios_Adapter;
import vn.theagency.helper.Helper;
import vn.theagency.helper.Key;
import vn.theagency.layout.UI_Audios;
import vn.theagency.objects.Audios;
import android.animation.Animator;
import android.animation.Animator.AnimatorListener;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

public class Audios_Library extends Activity implements OnClickListener,OnScrollListener {

	private Helper mHelper;
	public UI_Audios mAudios;
	
	LinearLayout initUIItems;
	public FrameLayout wrapper;
	Audios audios;
	ListView libraries, initUIListView;
	View initUIBgView,initUIBottom;
	ImageView initUIDown; 
	FrameLayout initUIHeader;
	View deine,plus, initUIConnection;
	FrameLayout audio;
	int pos;
	TextView textView;
	ArrayList<Audios> audiosList;
	//
	View frist;
	Audios_Adapter adapter;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub

		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);

		
		this.mHelper = Helper.shareIns(getApplicationContext());
		
		this.mAudios = UI_Audios.shareIns(getApplicationContext());

		initUIListView = this.mAudios.initUIListView();
		initUIHeader = this.mAudios.initUIHeader();
		initUIConnection = this.mAudios.initUIConnection();
		//initUIHeader.setClipChildren(false);
		
		initUIBgView = this.mAudios.initUIBgView();
		initUIBottom = this.mAudios.initUIBottom();
		initUIDown = this.mAudios.initUIDown();
		 
		
		
		initUI();
		pos = Integer.parseInt(getIntent().getExtras().getString("Audios"));
		getArrayFormHome(pos);
		libraries = (ListView) findViewById(Key.LISTVIEW_LIBRARY);
		
		
		int height = (int) (mHelper.getAppHeight()-(UI_Audios.header_height*2))/3;
		
		adapter = new Audios_Adapter(R.layout.items, getApplicationContext(), audiosList,height);
		libraries.setAdapter(adapter);
		//libraries.setScrollBarSize(0);
		libraries.setScrollbarFadingEnabled(true);
		libraries.setHorizontalScrollBarEnabled(false);
		libraries.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
		libraries.setVerticalScrollBarEnabled(false);
		libraries.setHorizontalScrollBarEnabled(false);
		libraries.setOnScrollListener(this);
		int color = Color.parseColor("#e8d3a0");
		ColorDrawable drawable = new ColorDrawable(color);
		drawable.setAlpha(100);
		libraries.setDivider(drawable);
		libraries.setDividerHeight(1);
		
		deine = findViewById(Key.btn_deine_musik);
		audio = (FrameLayout) findViewById(Key.AUDIOS_NAME);
		handler.sendEmptyMessageDelayed(2, 2000);
		plus = findViewById(Key.HEADER);	
		deine.setEnabled(false);
		audio.setEnabled(false);
		libraries.setEnabled(false);
		
	}
	
	
	public void setbackgroungHome(int action,View view){
		switch (action) {
		case 1:
			view.setBackgroundResource(R.drawable.bg);
			break;
		case 2:
			view.setBackgroundResource(R.drawable.bg_auf);
			break;
		case 3:
			view.setBackgroundResource(R.drawable.bg_unt);
			break;
		case 4:
			view.setBackgroundResource(R.drawable.bg_ver);
			break;
		case 5:
			view.setBackgroundResource(R.drawable.bg_vor);
			break;
		default:
			break;
		}
	}
	
	public void getArrayFormHome(int posi){
		switch (posi) {
		case 1:
			//Ausgchen
			audiosAusgchen();
			break;
		case 2:
			//Auflosen
			audiosAuflosen();
			break;
		case 3:
			//Unterstutzen
			audiosUnterstutzen();
			break;
		case 4:
			//Verbessern
			audiosVerbessern();
			break;
		case 5:
			//Vorbereiten
			audiosVorbereiten();
			break;

		default:
			break;
		}
	}

	public void initUI() {

		this.wrapper = new FrameLayout(this.getApplicationContext());
		FrameLayout.LayoutParams para = new FrameLayout.LayoutParams(
				FrameLayout.LayoutParams.MATCH_PARENT,
				FrameLayout.LayoutParams.MATCH_PARENT);
		this.wrapper.setLayoutParams(para);
		this.wrapper.setBackgroundColor(Color.BLACK);
		
		frist = new View(getApplicationContext());
		FrameLayout.LayoutParams paraBg = new FrameLayout.LayoutParams(
				FrameLayout.LayoutParams.MATCH_PARENT,
				FrameLayout.LayoutParams.MATCH_PARENT);
		setbackgroungHome(Integer.parseInt(getIntent().getExtras().getString("Audios")), frist);
	
		frist.setLayoutParams(paraBg);
		frist.setVisibility(View.GONE);
		
		
		
		this.wrapper.addView(this.initUIBgView);
		this.wrapper.addView(frist);
		this.wrapper.addView(this.initUIListView);
		this.wrapper.addView(this.initUIConnection);
		
		this.wrapper.addView(this.initUIDown);
		this.wrapper.addView(this.initUIBottom);
		this.wrapper.addView(this.initUIHeader);
		setContentView(this.wrapper);
		
		
		
		textView = (TextView) findViewById(Key.AUSWAHL);
	
		

	}
	
	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		
	}
	
	private ArrayList<Audios> audiosAusgchen() {
		audiosList = new ArrayList<Audios>();
		audios = new Audios("","Wendeltreppe",
				"Hilft Dir dich noch tiefer zu entspannen, Du benötigst jedoch mehr Zeit.", "Gratis", R.drawable.wen01);
		audiosList.add(audios);
		
		audios = new Audios("",
				"Zurückkommen",
				"Sollte nach jeder Hypnose als Abschluss folgen, ausser Du möchtest nachher schlafen.",
				"Gratis", R.drawable.zur01);
		audiosList.add(audios);
		audios = new Audios("",
				"Farben atmen",
				"Perfekt für all die, welche Entspannung brauchen und Farben mögen.",
				"Gratis", R.drawable.far01);
		audiosList.add(audios);
		audios = new Audios("",
				"Fühle deine eigene Gelassenheit wieder",
				"",
				"Gratis", R.drawable.gel01);
		audiosList.add(audios);
		audios = new Audios("",
				"Lieblingsplatz",
				"Ein geheimer Platz, an welchem man vor allen Problemen geschützt ist.",
				"4CHF/3€", R.drawable.lie01);
		audiosList.add(audios);
		audios = new Audios("",
				"Kontrollzentrale",
				"In deiner persönlichen Kontrollzentrale kannst Du alles so einstellen, wie es sein sollte.",
				"4CHF/3€", R.drawable.kon01);
		audiosList.add(audios);
		
		audios = new Audios("",
				"Heilendes  \nweisses Licht",
				"Eine wunderbare Hypnose um Stress abzubauen.",
				"4CHF/3€",
			 R.drawable.hei01);
		audiosList.add(audios);
		
		return audiosList;
	}

	private ArrayList<Audios> audiosAuflosen() {
		audiosList = new ArrayList<Audios>();
		audios = new Audios("",
				"Wendeltreppe",
				"Hilft Dir dich noch tiefer zu entspannen, Du benötigst jedoch mehr Zeit.", "Gratis", R.drawable.wen01);
		audiosList.add(audios);
		
		audios = new Audios("",
				"Zurückkommen",
				"Sollte nach jeder Hypnose als Abschluss folgen, ausser Du möchtest nachher schlafen.",
				"Gratis", R.drawable.zur01);
		audiosList.add(audios);
		audios = new Audios("",
				"Türe der Erkenntnis",
				"Realisiere, dass nur Du entscheidest, wie Du.",
				"2CHF/1.50€", R.drawable.ture);
		audiosList.add(audios);
		
		audios = new Audios("",
				"Grenzen stärken",
				"Diese Hypnose wird Dir helfen, dich emotional abgegrenzter zu fühlen.",
				"4CHF/3€", R.drawable.gre01);
		audiosList.add(audios);
		audios = new Audios("",
				"Zoo der Emotionen",
				"Lass deinen Selbstzweifel und deinen Stress eingesperrt im Zoo zurück.",
				"4CHF/3€", R.drawable.zoo01);
		audiosList.add(audios);
		audios = new Audios("",
				"Heilendes  \nweisses Licht",
				"Eine wunderbare Hypnose um Stress abzubauen.",
				"4CHF/3€", R.drawable.hei01);
		audiosList.add(audios);
		
		return audiosList;
	}

	private ArrayList<Audios> audiosUnterstutzen() {
		audiosList = new ArrayList<Audios>();
		audios = new Audios("",
				"Wendeltreppe",
				"Hilft Dir dich noch tiefer zu entspannen, Du benötigst jedoch mehr Zeit.", "Gratis",R.drawable.wen01);
		audiosList.add(audios);
		
		audios = new Audios("",
				"Zurückkommen",
				"Sollte nach jeder Hypnose als Abschluss folgen, ausser Du möchtest nachher schlafen.",
				"Gratis", R.drawable.zur01);
		audiosList.add(audios);
		audios = new Audios("",
				"Einnistung",
				"Unterstütze deinen Körper und deinen Geist nach einem Transfer oder einer IUI.",
				"3CHF/2€", R.drawable.ein01);
		audiosList.add(audios);
		audios = new Audios("",
				"Sturmwolken",
				"Egal, wie das Ergebnis wird, das Leben geht weiter.",
				"3CHF/2€", R.drawable.stu01);
		audiosList.add(audios);
		
		return audiosList;
	}

	private ArrayList<Audios> audiosVerbessern() {
		audiosList = new ArrayList<Audios>();
		audios = new Audios("",
				"Wendeltreppe",
				"Hilft Dir dich noch tiefer zu entspannen, Du benötigst jedoch mehr Zeit.", "Gratis", R.drawable.wen01);
		audiosList.add(audios);
		audios = new Audios("",
				"Zurückkommen",
				"Sollte nach jeder Hypnose als Abschluss folgen, ausser Du möchtest nachher schlafen.",
				"Gratis", R.drawable.zur01);
		audiosList.add(audios);
		audios = new Audios("",
				"Gegensätze",
				"Spüre körperlich, wie sich negative Gedanken auf deineStimmung auswirken und was Du dagegen tun kannst.",
				"3CHF/2€", R.drawable.geg01);
		audiosList.add(audios);
		audios = new Audios("",
				"Fruchtbarkeitsgarten",
				"Bereite alles so in deinem Fruchtbarkeitsgarten vor, dass Du nur noch anzupﬂanzen brauchst.",
				"3CHF/2€", R.drawable.fru01);
		audiosList.add(audios);
		audios = new Audios("",
				"Heilendes \nweisses Licht",
				"Eine wunderbare Hypnose um Stress abzubauen.",
				"4CHF/3€", R.drawable.hei01);
		audiosList.add(audios);
		
		
		return audiosList;
	}
	private ArrayList<Audios> audiosVorbereiten() {
		audiosList = new ArrayList<Audios>();
		audios = new Audios("",
				"Wendeltreppe",
				"Hilft Dir dich noch tiefer zu entspannen, Du benötigst jedoch mehr Zeit.", "Gratis", R.drawable.wen01);
		audiosList.add(audios);
		audios = new Audios("",
				"Zurückkommen",
				"Sollte nach jeder Hypnose als Abschluss folgen, ausser Du möchtest nachher schlafen.",
				"Gratis", R.drawable.zur01);
		audiosList.add(audios);
		audios = new Audios("",
				"IVF Vorbereitung",
				"Hilf Dir und deinem Körper, die Behandlung optimal zu nutzen.",
				"4CHF/3€", R.drawable.ivf01);
		audiosList.add(audios);
		audios = new Audios("",
				"Kontrollzentrale",
				"In deiner persönlichen Kontrollzentrale kannst Du alles so einstellen, wie es sein sollte.",
				"4CHF/3€",R.drawable.kon01);
		audiosList.add(audios);		
		return audiosList;
	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case Key.btn_deine_musik:
			Intent intent = new Intent(getApplicationContext(),Deine_Titel.class);
			clearMemory();
			intent.putExtra("Audios", getIntent().getExtras().getString("Audios"));
			startActivity(intent);
			finish();
			overridePendingTransition(R.anim.silde_up_in, R.anim.slide_up_out);
			
			break;
		case Key.AUDIOS_NAME:
			//onBackPressed();
			runOnUiThread(new Runnable() {
				
				@Override
				public void run() {
					// TODO Auto-generated method stub
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
							
							
							plus.setBackgroundResource(R.drawable.plus);
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
									plus.clearAnimation();
									runOnUiThread(new Runnable() {
										
										@Override
										public void run() {
											// TODO Auto-generated method stub
											
											initUIListView.clearAnimation();
											initUIListView.animate().setInterpolator(new LinearInterpolator()).translationYBy(0).translationY(0-mHelper.getAppHeight()).setDuration(800).setListener(new AnimatorListener() {
												
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
													/*runOnUiThread(new Runnable() {	
														@Override
														public void run() {
															// TODO Auto-generated method stub
															initUIBgView.clearAnimation();
															initUIBgView.animate().setInterpolator(new LinearInterpolator()).translationYBy(0).translationY(mHelper.getAppHeight()).setDuration(800).start();
															
														}
													});*/
													runOnUiThread(new Runnable() {
														
														@Override
														public void run() {
															// TODO Auto-generated method stub
															frist.clearAnimation();
															frist.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.auswahl_down));
														
														}
													});
													frist.setVisibility(View.VISIBLE);
													initUIBottom.setVisibility(View.GONE);
													initUIDown.setVisibility(View.GONE);
													initUIHeader.animate().setInterpolator(new LinearInterpolator()).translationYBy(0).translationY(mAudios.animUpPlus).setDuration(800).setListener(new AnimatorListener() {
														
														@Override
														public void onAnimationStart(Animator animation) {
															// TODO Auto-generated method stub
															initUIHeader.setBackgroundColor(Color.TRANSPARENT);
														}
														
														@Override
														public void onAnimationRepeat(Animator animation) {
															// TODO Auto-generated method stub
															
														}
														
														@Override
														public void onAnimationEnd(Animator animation) {
															// TODO Auto-generated method stub
															Intent intent3 = new Intent(getApplicationContext(), Home.class);
															clearMemory();
															intent3.putExtra("HomeBG", getIntent().getExtras().getString("Audios"));
															intent3.putExtra("Home", "Home");
														//	intent3.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
															startActivity(intent3);
															finish();
															overridePendingTransition(R.anim.alpha_in,R.anim.alpha_in);
															
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
						}
						
						@Override
						public void onAnimationCancel(Animator animation) {
							// TODO Auto-generated method stub
							
						}
					}).start();
				}
			});
			
			
			
			break;
		default:
			break;
		}
	}
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		deine.setOnClickListener(this);
		audio.setOnClickListener(this);
		IntentFilter mNetworkStateIntentFilter = new IntentFilter();
		mNetworkStateIntentFilter
				.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
		registerReceiver(mNetworkStateIntentReceiver, mNetworkStateIntentFilter);

		
	}
	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		unregisterReceiver(mNetworkStateIntentReceiver);
	}
	public void clearMemory(){
		deine.setOnClickListener(null);
		audio.setOnClickListener(null);
	
	}
	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
		Intent intent3 = new Intent(getApplicationContext(), Home.class);
		clearMemory();
		intent3.putExtra("HomeBG", getIntent().getExtras().getString("Audios"));
		intent3.putExtra("Home", "Home");
		startActivity(intent3);
		finish();
		overridePendingTransition(R.anim.alpha_in, R.anim.alpha_out);
		
	}

	
	@Override
	public void onScrollStateChanged(AbsListView view, int scrollState) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onScroll(AbsListView view, int firstVisibleItem,
			int visibleItemCount, int totalItemCount) {
		// TODO Auto-generated method stub
		this.initUIDown.setVisibility(View.VISIBLE);
		if((firstVisibleItem+visibleItemCount)==totalItemCount){
			this.initUIDown.setVisibility(View.GONE);
		}
		
	}
	
	Handler handler = new Handler(){

		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			super.handleMessage(msg);
			switch (msg.what) {
			case 1:
				initUIHeader.setBackgroundColor(Color.TRANSPARENT);
				break;
			case 2:
				deine.setEnabled(true);
				audio.setEnabled(true);
				libraries.setEnabled(true);
				plus.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.home_plus_flip));
				break;
			default:
				break;
			}
		}
		
	};
	
	
	public class AuswahlAnimation extends AsyncTask<Integer, Integer, Void>{

		View viewAnimation;
		Animation anim;
		int animRes;
		
		
		public AuswahlAnimation(View viewAnimation, int animRes) {
			super();
			this.viewAnimation = viewAnimation;
			this.animRes = animRes;
			anim = AnimationUtils.loadAnimation(getApplicationContext(),
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
	
	BroadcastReceiver mNetworkStateIntentReceiver = new BroadcastReceiver() {

		@Override
		public void onReceive(Context context, Intent intent) {
			ConnectivityManager cm = ((ConnectivityManager) context
					.getSystemService(Context.CONNECTIVITY_SERVICE));
			
				if(mHelper.isOnline(getApplicationContext())){
					initUIListView.setVisibility(View.VISIBLE);
					initUIConnection.setVisibility(View.GONE);
					runOnUiThread(new Runnable() {
						
						@Override
						public void run() {
							// TODO Auto-generated method stub
							initUIListView.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.auswahl_down));
							
						}
					});
				}else{
					initUIListView.setVisibility(View.INVISIBLE);
					initUIConnection.setVisibility(View.VISIBLE);
					
				
				

			} 
		}
	};

	
	

}
