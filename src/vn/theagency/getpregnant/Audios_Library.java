package vn.theagency.getpregnant;

import java.util.ArrayList;

import vn.theagency.helper.Audios_Adapter;
import vn.theagency.helper.Helper;
import vn.theagency.helper.Key;
import vn.theagency.layout.UI_Audios;
import vn.theagency.objects.Audios;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Messenger;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
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
	View deine,audio;
	int pos;
	TextView textView;
	ArrayList<Audios> audiosList;
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
		audio = findViewById(Key.AUDIOS_NAME);
		
	
		
		
		
		
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
		frist.setBackgroundColor(Color.BLACK);
		frist.setLayoutParams(paraBg);
		
		this.wrapper.addView(frist);
		
		this.wrapper.addView(this.initUIBgView);
		
		this.wrapper.addView(this.initUIListView);
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
				"Hilft Dir dich noch tiefer zu entspannen, Du benötigst", "", R.drawable.wen);
		audiosList.add(audios);
		
		audios = new Audios("",
				"Zurückkommen",
				"Sollte nach jeder Hypnose als Abschluss folgen...",
				"", R.drawable.zur);
		audiosList.add(audios);
		audios = new Audios("",
				"Farben atmen",
				"Blindtext orRovid moluptat mi, offictorro dolupta",
				"", R.drawable.far);
		audiosList.add(audios);
		audios = new Audios("",
				"Lieblingsplatz",
				"Blindtext orRovid moluptat mi, offictorro dolupta ",
				"2,99$", R.drawable.lie);
		audiosList.add(audios);
		audios = new Audios("",
				"Kontrollzentrale",
				"Blindtext elitis endiatiu sincil lue mol est Uciam ipita int.",
				"3,8$", R.drawable.kon);
		audiosList.add(audios);
		audios = new Audios("",
				"Gelassenheit \nals Hilfsmittel",
				"Blindtext orRovid moluptat mi",
				"", R.drawable.gela);
		audiosList.add(audios);
		audios = new Audios("",
				"Heilendes\nweisses Licht",
				"Blindtext orRovid moluptat mi",
				"2.99$", R.drawable.hei);
		audiosList.add(audios);
		
		return audiosList;
	}

	private ArrayList<Audios> audiosAuflosen() {
		audiosList = new ArrayList<Audios>();
		audios = new Audios("",
				"Wendeltreppe",
				"Hilft Dir dich noch tiefer zu entspannen, Du benötigst", "", R.drawable.wen);
		audiosList.add(audios);
		
		audios = new Audios("",
				"Zurückkommen",
				"Sollte nach jeder Hypnose als Abschluss folgen...",
				"", R.drawable.zur);
		audiosList.add(audios);
		audios = new Audios("",
				"Türe der Erkenntnis",
				"Realisiere, dass nur Du entscheidest, wie Du.",
				"2sFr/1.50", R.drawable.tur);
		audiosList.add(audios);
		audios = new Audios("",
				"Grenzen stärken",
				"Diese Hypnose wird Dir helfen, dich emotional abgegrenzter...",
				"4sFr", R.drawable.gre);
		audiosList.add(audios);
		audios = new Audios("",
				"Zoo der Emotionen",
				"Lass deinen Selbstzweifel und deinen Stress eingesperrt...",
				"4sFr", R.drawable.zoo);
		audiosList.add(audios);
		audios = new Audios("",
				"Heilendes \nweisses Licht",
				"Blindtext orRovid moluptat mi",
				"4sFr", R.drawable.hei02);
		audiosList.add(audios);
		
		return audiosList;
	}

	private ArrayList<Audios> audiosUnterstutzen() {
		audiosList = new ArrayList<Audios>();
		audios = new Audios("",
				"Wendeltreppe",
				"Hilft Dir dich noch tiefer zu entspannen, Du benötigst", "",R.drawable.wen);
		audiosList.add(audios);
		
		audios = new Audios("",
				"Zurückkommen",
				"Sollte nach jeder Hypnose als Abschluss folgen...",
				"", R.drawable.zur);
		audiosList.add(audios);
		audios = new Audios("",
				"Einnistung",
				"Unterstütze deinen Körper und deinen Geist nach einem Transfer...",
				"3sFr", R.drawable.ein);
		audiosList.add(audios);
		audios = new Audios("",
				"Sturmwolken",
				"Egal, wie das Ergebnis wird, das Leben geht weiter.",
				"3sFr", R.drawable.stue);
		audiosList.add(audios);
		
		return audiosList;
	}

	private ArrayList<Audios> audiosVerbessern() {
		audiosList = new ArrayList<Audios>();
		audios = new Audios("",
				"Wendeltreppe",
				"Hilft Dir dich noch tiefer zu entspannen, Du benötigst", "", R.drawable.wen);
		audiosList.add(audios);
		audios = new Audios("",
				"Zurückkommen",
				"Sollte nach jeder Hypnose als Abschluss folgen...",
				"", R.drawable.zur);
		audiosList.add(audios);
		audios = new Audios("",
				"Gegensätze",
				"Spüre körperlich, wie sich negative Gedanken auf deineStimmung...",
				"3sFr", R.drawable.ver03);
		audiosList.add(audios);
		audios = new Audios("",
				"Fruchtbarkeitsgarten",
				"Bereite alles so in deinem Fruchtbarkeitsgarten vor...",
				"3sFr", R.drawable.fru);
		audiosList.add(audios);
		audios = new Audios("",
				"Heilendes \nweisses Licht",
				"Pis ratur a del iniate necepera...",
				"3,8$", R.drawable.hei);
		audiosList.add(audios);
		
		
		return audiosList;
	}
	private ArrayList<Audios> audiosVorbereiten() {
		audiosList = new ArrayList<Audios>();
		audios = new Audios("",
				"Wendeltreppe",
				"Hilft Dir dich noch tiefer zu entspannen, Du benötigst", "", R.drawable.wen);
		audiosList.add(audios);
		audios = new Audios("",
				"Zurückkommen",
				"Sollte nach jeder Hypnose als Abschluss folgen...",
				"", R.drawable.zur);
		audiosList.add(audios);
		audios = new Audios("",
				"IVF Vorbereitung",
				"Hilf Dir und deinem Körper, die Behandlung optimal zu nutzen.",
				"4sFr", R.drawable.ivf);
		audiosList.add(audios);
		audios = new Audios("",
				"Kontrollzentrale",
				"Id modion nonet idipitis doluptatur mo cus corrum solorib ",
				"2,99",R.drawable.kon02);
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
			
			break;
		case Key.AUDIOS_NAME:
			onBackPressed();
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
		startActivity(intent3);
		finish();
		
		
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
	
	
	
	
	
	
	
	

}
