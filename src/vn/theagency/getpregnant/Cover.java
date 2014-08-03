package vn.theagency.getpregnant;

import vn.theagency.helper.Key;
import vn.theagency.helper.Helper;
import vn.theagency.layout.UI_Audios;
import vn.theagency.layout.UI_Cover;
import android.app.Activity;
import android.content.Intent;
import android.content.IntentSender.OnFinished;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;

public class Cover extends Activity implements OnClickListener {

	private Helper mHelper;
	public UI_Cover mCover;
	public FrameLayout wrapper;
	
	public View initUIBackGround;
	public FrameLayout initUIInfo;
	public FrameLayout initUIBottom;
	public View initUIText;
	FrameLayout initUIBottom2;
	View aus,auf,unt,vor,ver;
	
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
		this.initUIBottom2 = this.mCover.initUIBottom2();
		this.initUIText = this.mCover.initUIText();
		
		
		initUI();
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
	}
	
	public void preference(){
		aus = findViewById(Key.linearAusgleichen);
		auf = findViewById(Key.linearAuflosen);
		unt = findViewById(Key.linearUnterstutzen);
		vor = findViewById(Key.linearVorbereiten);
		ver = findViewById(Key.linearVerbessern);
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
		this.wrapper.addView(initUIBottom2);
		this.wrapper.addView(this.initUIBottom);
		setContentView(this.wrapper);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case Key.linearAuflosen:

			Intent intent = new Intent(getApplicationContext(), Home.class);
			clearMemory();
			intent.putExtra("HomeBG", String.valueOf(2));
			startActivity(intent);
			finish();
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
		

		default:
			break;
		}
	}
	public void clearMemory(){
		aus.setOnClickListener(null);
		auf.setOnClickListener(null);
		unt.setOnClickListener(null);
		vor.setOnClickListener(null);
		ver.setOnClickListener(null);
	}
	@Override
	public void finish() {
		// TODO Auto-generated method stub
		super.finish();
		this.overridePendingTransition(R.anim.fade_in, R.anim.move);
	}
}