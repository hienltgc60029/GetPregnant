package vn.theagency.getpregnant;

import java.util.ArrayList;

import vn.theagency.helper.Key;
import vn.theagency.helper.Deine_Adapter;
import vn.theagency.helper.Helper;
import vn.theagency.layout.UI_Deneine;
import vn.theagency.objects.Audios;
import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListView;

public class Deine_Titel extends Activity implements OnClickListener {

	private Helper mHelper;
	public UI_Deneine mDeine;
	public FrameLayout initUIHeader;
	public FrameLayout wrapper;
	
	public View initUIBottom;
	public ListView initUIList;
	public ImageView initUIDown;
	public ListView list;
	public FrameLayout initUIText;
	Audios audios;
	View deine,back;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		this.mHelper = Helper.shareIns(getApplicationContext());
		this.mDeine = UI_Deneine.shareIns(getApplicationContext());
		
		this.initUIHeader = this.mDeine.initUIHeader();
		this.initUIBottom = this.mDeine.initUIBottom();
		this.initUIDown = this.mDeine.initUIDown();
		this.initUIList = this.mDeine.initUIList();
		this.initUIText = this.mDeine.initUIText();
		initUI();
		list = (ListView) findViewById(Key.LISTVIEW_LIBRARY);
		
		BitmapDrawable drawable = (BitmapDrawable) getResources().getDrawable(R.drawable.library_header);
		BitmapDrawable header = (BitmapDrawable) getResources()
				.getDrawable(R.drawable.header_deine_titel);
		int h =(int)((2*drawable.getBitmap().getHeight())/3);
		int header_height = (int) (2 * header.getBitmap().getHeight()) / 3;
		int height = (int) (mHelper.getAppHeight()-(h+header_height))/3;
		
		Deine_Adapter adapter = new Deine_Adapter(R.layout.items, getApplicationContext(), audiosArray(),height);
		list.setAdapter(adapter);
		
	}
	public void initUI() {
		this.wrapper = new FrameLayout(this.getApplicationContext());
		FrameLayout.LayoutParams para = new FrameLayout.LayoutParams(
				FrameLayout.LayoutParams.MATCH_PARENT,
				FrameLayout.LayoutParams.MATCH_PARENT);
		this.wrapper.setLayoutParams(para);
		
		this.wrapper.addView(initUIHeader);
		this.wrapper.addView(this.initUIText);
		this.wrapper.addView(this.initUIList);
		
		this.wrapper.addView(this.initUIDown);
		this.wrapper.addView(this.initUIBottom);
		setContentView(this.wrapper);
		back = findViewById(Key.btn_back);
		back.setOnClickListener(this);
		
		deine = findViewById(Key.btn_deine_musik);
		deine.setOnClickListener(this);
		
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
				"Ka Ki Ko",
				"Blindtext elitis endiatiu sincil lue mol est Uciam ipita int.",
				"", "");
		arr.add(audios);
		audios = new Audios(
				"Elitis Endiatiu",
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
			Intent intent = new Intent(getApplicationContext(),DeineSamlung.class);
			intent.putExtra("Audios", getIntent().getExtras().getString("Audios"));
			clearMemory();
			startActivity(intent);
			finish();
			Log.i("LTH", "Finish");
			overridePendingTransition(R.anim.slide_left_out, R.anim.slide_left_in);
			break;
		case Key.btn_back:
			this.onBackPressed();
			break;

		default:
			break;
		}
	}
	public void clearMemory(){
		back.setOnClickListener(null);
		deine.setOnClickListener(null);
		mHelper=null;
		mDeine=null;
		initUIHeader=null;
		wrapper=null;
		
		list = null;
		audios = null;
	}
	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
		clearMemory();
		Intent intent = new Intent(getApplicationContext(),
				Audios_Library.class);
		intent.putExtra("Audios", getIntent().getExtras().getString("Audios"));
		startActivity(intent);
		finish();
		overridePendingTransition(R.anim.slide_right_in,R.anim.slide_right_out);
	}
	@Override
	public void finish() {
		// TODO Auto-generated method stub
		super.finish();
		clearMemory();
		System.gc();
	}
	@Override
	protected void onDestroy() {
		
		super.onDestroy();
		clearMemory();
		System.gc();
	}
	
}
