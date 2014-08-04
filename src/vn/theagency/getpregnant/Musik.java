package vn.theagency.getpregnant;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import vn.theagency.fragment.Fragment_MusikListe;
import vn.theagency.fragment.Fragment_Nohitaky;
import vn.theagency.helper.GetSongsAll;
import vn.theagency.helper.Helper;
import vn.theagency.layout.UI_Musik;
import vn.theagency.objects.Songs;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Messenger;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.SeekBar;

@SuppressLint("HandlerLeak")
public class Musik extends Activity {

	private Helper mHelper;
	public UI_Musik mMusik;
	public View initUIBackGround, initUIHeader;
	public FrameLayout wrapper;
	public RelativeLayout initUIMusicBar;
	public SeekBar mLine, mVolume;
	public View volume;
	
	ArrayList<Songs> arr;
	int sliteVolume = 10;
	FragmentTransaction transaction;

	Handler handler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			super.handleMessage(msg);
			actionClickHander(msg.arg1);
			sliteVolume = msg.arg2;
		}
	};

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.musik);
		

		
		FragmentTransaction transaction1 = getFragmentManager()
				.beginTransaction();
		transaction1.add(R.id.musik, Fragment_MusikListe.newInstance(arr));
		transaction1.commit();

		FragmentTransaction transaction = getFragmentManager()
				.beginTransaction();
		transaction.add(R.id.musik, Fragment_Nohitaky.newInstance());
		transaction.commit();

	}

	

	public void actionClickHander(int action) {
		switch (action) {
		case 1:

			Fragment_Nohitaky.newInstance().getView().setVisibility(View.GONE);
			Fragment_MusikListe.newInstance(arr).getView()
					.setVisibility(View.VISIBLE);

			break;
		case 2:

			Fragment_Nohitaky.newInstance().getView()
			.setVisibility(View.VISIBLE);
	Fragment_MusikListe.newInstance(arr).getView()
			.setVisibility(View.GONE);
			break;
		case 3:
			onBackPressed();
			break;

		default:
			break;
		}
	}

	

	public Messenger getMessenger() {
		return new Messenger(handler);
	}

	public Messenger getMessenger1() {
		return new Messenger(handler);
	}

	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		
	}

	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
		Log.i("LTH", "Media: "+ getIntent().getExtras().getString("Audios"));
		
		clearMemory();
		Intent intent = new Intent(getApplicationContext(), Deine_Titel.class);

		intent.putExtra("Audios", getIntent().getExtras().getString("Audios"));
		startActivity(intent);
		finish();
	}

	private void clearMemory() {
		// TODO Auto-generated method stub
		getFragmentManager().beginTransaction()
				.remove(Fragment_Nohitaky.newInstance()).commit();
		getFragmentManager().beginTransaction()
				.remove(Fragment_MusikListe.newInstance(arr)).commit();
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		System.gc();
	}

}
