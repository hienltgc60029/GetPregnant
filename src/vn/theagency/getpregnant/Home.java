package vn.theagency.getpregnant;

import vn.theagency.customlayout.PhotoView;
import vn.theagency.fragment.Fragment_Home;
import vn.theagency.helper.Helper;
import android.app.Activity;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Messenger;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import vn.theagency.getpregnantapplication.R;
public class Home extends Activity {

	private Helper mHelper;
	boolean status = false;

	Handler handler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			super.handleMessage(msg);
			doAction(msg.arg1, msg.arg2,true);
		}
	};

	public Messenger getMessenger() {
		return new Messenger(handler);
	}

	private void doAction(int action, int index, boolean isFrist) {
		switch (action) {
		case 1:
			if (status) {
				FragmentTransaction ausg = getFragmentManager()
						.beginTransaction();
				/*ausg.setCustomAnimations(R.animator.alpha_up,
						R.animator.alpha_down);*/
				ausg.replace(R.id.container, Fragment_Home.newInstance(
						((int) ((19 * mHelper.getAppHeight()) / 30)),
						R.drawable.text, R.drawable.bg, 0,isFrist));
				ausg.commit();
			} else {
				status = true;
				FragmentTransaction ausg = getFragmentManager()
						.beginTransaction();

				ausg.replace(R.id.container, Fragment_Home.newInstance(
						((int) ((19 * mHelper.getAppHeight()) / 30)),
						R.drawable.text, R.drawable.bg, 0,isFrist));
				ausg.commit();
			}

			break;
		case 2:
			if (status) {
				FragmentTransaction auf = getFragmentManager()
						.beginTransaction();
				/*auf.setCustomAnimations(R.animator.alpha_up,
						R.animator.alpha_down);*/
				auf.replace(R.id.container, Fragment_Home.newInstance(
						((int) ((7 * mHelper.getAppHeight()) / 80)),
						R.drawable.txt_auflosen, R.drawable.bg_auf,
						(int) mHelper.getAppWidth(),isFrist));
				auf.commit();
			} else {
				status = true;
				FragmentTransaction auf = getFragmentManager()
						.beginTransaction();

				auf.replace(R.id.container, Fragment_Home.newInstance(
						((int) ((7 * mHelper.getAppHeight()) / 80)),
						R.drawable.txt_auflosen, R.drawable.bg_auf,
						(int) mHelper.getAppWidth(),isFrist));
				auf.commit();
			}
			break;
		case 3:
			if (status) {
				FragmentTransaction unt = getFragmentManager()
						.beginTransaction();
				/*unt.setCustomAnimations(R.animator.alpha_up,
						R.animator.alpha_down);*/
				unt.replace(R.id.container, Fragment_Home.newInstance(
						((int) ((5 * mHelper.getAppHeight()) / 40)),
						R.drawable.txt_unterstutzen, R.drawable.bg_unt,
						(int) (mHelper.getAppWidth() / 2),isFrist));
				unt.commit();
			} else {
				status = true;
				FragmentTransaction unt = getFragmentManager()
						.beginTransaction();

				unt.replace(R.id.container, Fragment_Home.newInstance(
						((int) ((5 * mHelper.getAppHeight()) / 40)),
						R.drawable.txt_unterstutzen, R.drawable.bg_unt,
						(int) (mHelper.getAppWidth() / 2),isFrist));
				unt.commit();
			}
			break;
		case 4:
			if (status) {
				FragmentTransaction ver = getFragmentManager()
						.beginTransaction();
				/*ver.setCustomAnimations(R.animator.alpha_up,
						R.animator.alpha_down);*/
				ver.replace(R.id.container, Fragment_Home.newInstance(
						((int) ((24 * mHelper.getAppHeight()) / 40)),
						R.drawable.txt_verbessern, R.drawable.bg_ver,
						(int) (3 * mHelper.getAppWidth() / 4),isFrist));
				ver.commit();
			} else {
				status = true;
				FragmentTransaction ver = getFragmentManager()
						.beginTransaction();

				ver.replace(R.id.container, Fragment_Home.newInstance(
						((int) ((24 * mHelper.getAppHeight()) / 40)),
						R.drawable.txt_verbessern, R.drawable.bg_ver,
						(int) (3 * mHelper.getAppWidth() / 4),isFrist));
				ver.commit();
			}
			break;
		case 5:
			if (status) {
				FragmentTransaction vor = getFragmentManager()
						.beginTransaction();
				/*vor.setCustomAnimations(R.animator.alpha_up,
						R.animator.alpha_down);*/
				vor.replace(R.id.container, Fragment_Home.newInstance(
						(int) (((24 * mHelper.getAppHeight()) / 40)),
						R.drawable.txt_vorbereiten, R.drawable.bg_vor,
						(int) mHelper.getAppWidth() / 4,isFrist));
				vor.commit();
			} else {
				status = true;
				FragmentTransaction vor = getFragmentManager()
						.beginTransaction();

				vor.replace(R.id.container, Fragment_Home.newInstance(
						(int) (((24 * mHelper.getAppHeight()) / 40)),
						R.drawable.txt_vorbereiten, R.drawable.bg_vor,
						(int) mHelper.getAppWidth() / 4,isFrist));
				vor.commit();
			}
			break;
		case 6:
			
			Intent intent = new Intent(getApplicationContext(),
					Audios_Library.class);
			intent.putExtra("Audios", String.valueOf(index));
		//	intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
			startActivity(intent);		
			finish();
			overridePendingTransition(R.anim.alpha_in,R.anim.alpha_in);
			break;
		case 7:
			handler = null;
			Intent intent1 = new Intent(getApplicationContext(), Cover.class);
			startActivity(intent1);
			finish();
			overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
			break;

		default:
			break;
		}
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		// this.overridePendingTransition(R.anim.fade_in,0);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		this.mHelper = Helper.shareIns(getApplicationContext());
		setContentView(R.layout.home);
		if(getIntent().getExtras().getString("Home")==null){
		doAction(Integer.parseInt(getIntent().getExtras().getString("HomeBG")),
				0,true);
		}else{
			doAction(Integer.parseInt(getIntent().getExtras().getString("HomeBG")),
					0,false);
		}

	}

	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
		handler = null;
		Intent intent = new Intent(getApplicationContext(), Cover.class);
		startActivity(intent);
		finish();
	}

	@Override
	public void finish() {
		// TODO Auto-generated method stub
		super.finish();		
	}
	

}
