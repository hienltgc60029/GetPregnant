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
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class Audios_Library extends Activity implements OnClickListener, AnimationListener {

	private Helper mHelper;
	public UI_Audios mAudios;
	
	LinearLayout initUIItems;
	public FrameLayout wrapper;
	Audios audios;
	ListView libraries, initUIListView;
	View initUIBgView,initUIBottom;
	ImageView initUIDown; 
	RelativeLayout initUIHeader;
	View deine,audio;
	int pos;
	TextView textView;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub

		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.down_in);
		
		animation.setAnimationListener(this);
		
		this.mHelper = Helper.shareIns(getApplicationContext());
		this.mAudios = UI_Audios.shareIns(getApplicationContext());

		initUIListView = this.mAudios.initUIListView();
		initUIHeader = this.mAudios.initUIHeader();
		//initUIHeader.setClipChildren(false);
		
		initUIBgView = this.mAudios.initUIBgView();
		initUIBottom = this.mAudios.initUIBottom();
		initUIDown = this.mAudios.initUIDown();
		
		
		
		initUI();
		
		libraries = (ListView) findViewById(Key.LISTVIEW_LIBRARY);
		
		
		int height = (int) (mHelper.getAppHeight()-(UI_Audios.header_height*2))/3;
		
		Audios_Adapter adapter = new Audios_Adapter(R.layout.items, getApplicationContext(), audiosArray(),height);
		libraries.setAdapter(adapter);
		//libraries.setScrollBarSize(0);
		libraries.setScrollbarFadingEnabled(true);
		libraries.setHorizontalScrollBarEnabled(false);
		libraries.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
		libraries.setVerticalScrollBarEnabled(false);
		libraries.setHorizontalScrollBarEnabled(false);
		
		deine = findViewById(Key.btn_deine_musik);
		audio = findViewById(Key.AUDIOS_NAME);
		pos = Integer.parseInt(getIntent().getExtras().getString("Audios"));
		
		initUIBgView.startAnimation(animation);
		initUIListView.startAnimation(animation);
		
		
		
		
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
		overridePendingTransition(0, 0);
	}

	private ArrayList<Audios> audiosArray() {
		ArrayList<Audios> arr = new ArrayList<Audios>();
		audios = new Audios("Wendeltreppe",
				"Einleitung (kann vor jede Hypnose gesetzt werden)", "", "");
		arr.add(audios);
		audios = new Audios("Farben atmen", "Breathing colours", "", "");
		arr.add(audios);
		audios = new Audios(
				"Lieblingsplatz",
				"Blindtext elitis endiatiu sincil lue mol est Uciam ipita int.",
				"", "");
		arr.add(audios);
		audios = new Audios(
				"Who I Am",
				"Blindtext elitis endiatiu sincil lue mol est Uciam ipita int.",
				"", "");
		arr.add(audios);
		audios = new Audios(
				"Remember When",
				"Blindtext elitis endiatiu sincil lue mol est Uciam ipita int.",
				"", "");
		arr.add(audios);
		return arr;
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
		overridePendingTransition(R.anim.down_in,R.anim.down_out);
		
	}

	@Override
	public void onAnimationStart(Animation animation) {
		textView.setVisibility(View.VISIBLE);
	}

	@Override
	public void onAnimationEnd(Animation animation) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onAnimationRepeat(Animation animation) {
		// TODO Auto-generated method stub
		
	}

}
