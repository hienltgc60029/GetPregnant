package vn.theagency.getpregnant;

import java.util.ArrayList;

import vn.theagency.helper.Audios_Adapter;
import vn.theagency.helper.Helper;
import vn.theagency.helper.Key;
import vn.theagency.layout.UI_Audios;
import vn.theagency.layout.UI_Home;
import vn.theagency.objects.Audios;
import android.animation.Animator;
import android.animation.Animator.AnimatorListener;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

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
	View deine,plus;
	FrameLayout audio;
	int pos;
	TextView textView;
	ArrayList<Audios> audiosList;
	//
	
	
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
		//initUIHeader.setClipChildren(false);
		
		initUIBgView = this.mAudios.initUIBgView();
		initUIBottom = this.mAudios.initUIBottom();
		initUIDown = this.mAudios.initUIDown();
		 
		
		
		initUI();
		pos = Integer.parseInt(getIntent().getExtras().getString("Audios"));
		getArrayFormHome(pos);
		libraries = (ListView) findViewById(Key.LISTVIEW_LIBRARY);
		
		
		int height = (int) (mHelper.getAppHeight()-(UI_Audios.header_height*2))/3;
		
		Audios_Adapter adapter = new Audios_Adapter(R.layout.items, getApplicationContext(), audiosList,height);
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
		plus = findViewById(Key.HEADER);	
		plus.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.home_plus_flip));
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
		
		View frist = new View(getApplicationContext());
		FrameLayout.LayoutParams paraBg = new FrameLayout.LayoutParams(
				FrameLayout.LayoutParams.MATCH_PARENT,
				FrameLayout.LayoutParams.MATCH_PARENT);
		setbackgroungHome(Integer.parseInt(getIntent().getExtras().getString("Audios")), frist);
	
		frist.setLayoutParams(paraBg);
		
		this.wrapper.addView(frist);
		
		this.wrapper.addView(this.initUIBgView);
		
		this.wrapper.addView(this.initUIListView);
		this.wrapper.addView(this.initUIDown);
		this.wrapper.addView(this.initUIBottom);
		this.wrapper.addView(this.initUIHeader);
		setContentView(this.wrapper);
		new AuswahlAnimation(this.initUIListView, R.anim.auswahl_down).execute(0);
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
				"Ein geheimer Platz, an welchem  man vor allen Problemen geschützt ist.",
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
				"Spüre körperlich, wie sich negative Gedanken auf deineStimmung auswirken und was Du dagegen tun kannst.",
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
				"unterstütze deinen Körper und deinen Geist nach einem Transfer oder einer IUI.",
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
			overridePendingTransition(R.anim.slide_left_out, R.anim.slide_left_in);
			
			break;
		case Key.AUDIOS_NAME:
			//onBackPressed();
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
								initUIBgView.animate().translationYBy(0).translationY(1138).setDuration(1000).start();
								initUIListView.animate().translationYBy(0).translationY(1138).setDuration(1000).start();
								initUIDown.animate().translationYBy(0).translationY(1138).setDuration(1000).start();
								initUIBottom.animate().translationYBy(0).translationY(1138).setDuration(1000).start();
								initUIHeader.animate().setInterpolator(new AccelerateDecelerateInterpolator()).translationYBy(0).translationY(1038).setDuration(1000).setListener(new AnimatorListener() {
								
								@Override
								public void onAnimationStart(Animator animation) {
									// TODO Auto-generated method stub
									handler.sendEmptyMessageDelayed(1, 100);
								}
								
								@Override
								public void onAnimationRepeat(Animator animation) {
									// TODO Auto-generated method stub
									
								}
								
								@Override
								public void onAnimationEnd(Animator animation) {
									// TODO Auto-generated method stub
									onBackPressed();
									
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
	}
	public void clearMemory(){
		deine.setOnClickListener(null);
		audio.setOnClickListener(null);
		audios = null;
		wrapper=null;
		libraries = null;
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
	
	
	
	

}
