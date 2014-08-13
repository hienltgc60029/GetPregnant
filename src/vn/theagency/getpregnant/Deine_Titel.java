package vn.theagency.getpregnant;

import java.util.ArrayList;

import vn.theagency.bussiness.Store;
import vn.theagency.helper.Key;
import vn.theagency.helper.Deine_Adapter;
import vn.theagency.helper.Helper;
import vn.theagency.layout.UI_Deneine;
import vn.theagency.objects.Audios;
import vn.theagency.sqlite.SQliteData;
import android.app.Activity;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListView;

public class Deine_Titel extends Activity implements OnClickListener,OnScrollListener {

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
	ArrayList<Audios> arr;
	
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
		list.setScrollbarFadingEnabled(true);
		list.setHorizontalScrollBarEnabled(false);
		list.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
		list.setVerticalScrollBarEnabled(false);
		list.setSmoothScrollbarEnabled(true);
		int color = Color.parseColor("#e8d3a0");
		ColorDrawable drawable = new ColorDrawable(color);
		drawable.setAlpha(100);
		list.setDivider(drawable);
		list.setDividerHeight(1);
		
	//	list.setDivider(null);
		list.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
		
		int height01 =(int) (this.mDeine.bottom+this.mDeine.bottom_down+this.mDeine.header_height) ;
		int height = (int) (mHelper.getAppHeight()-height01)/3;
		String indexHome = getIntent().getExtras().getString("Audios");
		SQliteData data = new SQliteData(getApplicationContext());
		data.open();
		arr =data.getAllAudios();
		data.close();
		
		Deine_Adapter adapter = new Deine_Adapter(R.layout.items, getApplicationContext(), arr,height,indexHome);
		list.setAdapter(adapter);
		list.setOnScrollListener(this);
		
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
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case Key.btn_deine_musik:
			Log.i("LTH", "Deine Title");
			Intent intent = new Intent(getApplicationContext(),DeineSamlung.class);
			intent.putExtra("Audios", getIntent().getExtras().getString("Audios"));
			clearMemory();
			startActivity(intent);
			finish();
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
	@Override
	public void onScrollStateChanged(AbsListView view, int scrollState) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void onScroll(AbsListView view, int firstVisibleItem,
			int visibleItemCount, int totalItemCount) {
		this.initUIDown.setVisibility(View.VISIBLE);
		if((firstVisibleItem+visibleItemCount)== totalItemCount){
			this.initUIDown.setVisibility(View.GONE);
		}
	}
	
}
