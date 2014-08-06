package vn.theagency.getpregnant;

import java.util.ArrayList;

import vn.theagency.helper.Helper;
import vn.theagency.helper.Key;
import vn.theagency.helper.SammlungAdapter;
import vn.theagency.layout.UI_Deneine;
import vn.theagency.objects.Audios;
import vn.theagency.sqlite.SQliteData;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Messenger;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.CalendarView.OnDateChangeListener;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListView;

public class DeineSamlung extends Activity implements OnClickListener,OnScrollListener {

	private Helper mHelper;
	public UI_Deneine mDeine;
	public FrameLayout initUIHeader;
	public FrameLayout wrapper;

	public View initUISammLungBottom;
	public ListView initUIList;
	public ImageView initUIDown;
	public ListView list;
	public FrameLayout initUIDeineSamlung;
	Audios audios;
	View btn_play,back,hinzu;
	ArrayList<Audios> arrAudios;

	

	boolean isPlay = false;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		Log.i("LTH", "Deine Sammlung");
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		this.mHelper = Helper.shareIns(getApplicationContext());
		this.mDeine = UI_Deneine.shareIns(getApplicationContext());

		this.initUIHeader = this.mDeine.initUIHeader();
		this.initUISammLungBottom = this.mDeine.initUISammLungBottom();
		this.initUIDown = this.mDeine.initUIDown();
		this.initUIList = this.mDeine.initUIList();
		this.initUIDeineSamlung = this.mDeine.initUIDeineSamlung();
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
		list.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
		list.setOnScrollListener(this);
		
		int height =(int) (this.mDeine.bottom+this.mDeine.bottom_down+this.mDeine.header_height) ;
		int rowSize = (int) (mHelper.getAppHeight()-height)/3;
		SQliteData data = new SQliteData(getApplicationContext());
		data.open();
		arrAudios = data.getAllAudiosCollections();
		data.close();
		SammlungAdapter adapter = new SammlungAdapter(R.layout.items,
				getApplicationContext(), arrAudios, rowSize);
		list.setAdapter(adapter);
	}

	public void initUI() {
		this.wrapper = new FrameLayout(this.getApplicationContext());
		FrameLayout.LayoutParams para = new FrameLayout.LayoutParams(
				FrameLayout.LayoutParams.MATCH_PARENT,
				FrameLayout.LayoutParams.MATCH_PARENT);
		this.wrapper.setLayoutParams(para);

		this.wrapper.addView(initUIHeader);
		this.wrapper.addView(this.initUIDeineSamlung);
		this.wrapper.addView(this.initUIList);

		this.wrapper.addView(this.initUIDown);
		this.wrapper.addView(this.initUISammLungBottom);
		setContentView(this.wrapper);
		back = findViewById(Key.btn_back);
		hinzu = findViewById(Key.btn_deine_musik);

		btn_play = findViewById(Key.PLAY);
		btn_play.setOnClickListener(this);

	}
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		back.setOnClickListener(this);
		hinzu.setOnClickListener(this);
	}

	
	

	

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case Key.btn_back:
			this.onBackPressed();
			break;
		case Key.btn_deine_musik:
			this.onBackPressed();
			break;
		default:
			break;
		}
	}
	public void clearMemory(){
		back.setOnClickListener(null);
		back.setOnClickListener(null);
	}
	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
		clearMemory();
		Intent intent = new Intent(getApplicationContext(),
				Deine_Titel.class);
		intent.putExtra("Audios", getIntent().getExtras().getString("Audios"));
		startActivity(intent);
		finish();
		overridePendingTransition(R.anim.slide_right_in,R.anim.slide_right_out);
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
		if((firstVisibleItem+visibleItemCount)== totalItemCount){
			this.initUIDown.setVisibility(View.GONE);
		}
		
	}
	
}
