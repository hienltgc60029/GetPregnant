package vn.theagency.getpregnant;

import java.util.ArrayList;

import vn.theagency.fragment.Fragment_Home;
import vn.theagency.fragment.Fragment_MusikListe;
import vn.theagency.fragment.Fragment_Nohitaky;
import vn.theagency.helper.GetSongsAll;
import vn.theagency.helper.Helper;
import vn.theagency.helper.Key;
import vn.theagency.layout.UI_Musik;
import vn.theagency.objects.Songs;
import android.app.Activity;
import android.app.FragmentTransaction;

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
import android.view.View.OnClickListener;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;

public class Musik extends Activity {

	private Helper mHelper;
	public UI_Musik mMusik;
	public View initUIBackGround, initUIHeader;
	public FrameLayout wrapper;
	public RelativeLayout initUIMusicBar;
	public SeekBar mLine, mVolume;
	public View volume;
	public MediaPlayer media;
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

	public MediaPlayer getMedia() {
		return media;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.musik);
		getArray();

		FragmentTransaction transaction1 = getFragmentManager()
				.beginTransaction();
		transaction1.add(R.id.musik,
				Fragment_MusikListe.newInstance(arr, sliteVolume));
		transaction1.commit();

		FragmentTransaction transaction = getFragmentManager()
				.beginTransaction();
		transaction.add(R.id.musik, Fragment_Nohitaky.newInstance());
		transaction.commit();

	}

	public void getArray() {
		String timePhut = null;
		String timeGiay = null;
		GetSongsAll all = new GetSongsAll();
		arr = new ArrayList<Songs>();
		arr = all.getPlayList(getApplicationContext());

		for (int i = 0; i < arr.size(); i++) {
			media = MediaPlayer.create(getApplicationContext(),
					Uri.parse(arr.get(i).getmURL()));
			int phut = (int) ((media.getDuration() / 60000) % 60);
			if (phut < 10) {
				timePhut = "0" + String.valueOf(phut);
			} else {
				timePhut = String.valueOf(phut);
			}

			int giay = (int) (((media.getDuration() - (phut * 60000)) / 1000) % 60);
			if (giay < 10) {
				timeGiay = "0" + String.valueOf(giay);
			} else {
				timeGiay = String.valueOf(giay);
			}
			arr.get(i).setmLine(timePhut + ":" + timeGiay);

		}

	}

	public void actionClickHander(int action) {
		switch (action) {
		case 1:

			/*FragmentTransaction transaction = getFragmentManager()
					.beginTransaction();
			transaction.setCustomAnimations(R.animator.slide_in_left,
					R.animator.slide_out_right);
			transaction.hide(Fragment_Nohitaky.newInstance());
			transaction.show(Fragment_MusikListe.newInstance(arr, sliteVolume));
			transaction
					.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
			transaction.commit();*/
			this.runOnUiThread(new Runnable() {
				
				@Override
				public void run() {
					// TODO Auto-generated method stub
					Fragment_Nohitaky.newInstance().getView().setVisibility(View.INVISIBLE);
					Fragment_MusikListe.newInstance(arr, sliteVolume).getView().setVisibility(View.VISIBLE);
				}
			});
			

			break;
		case 2:

			/*FragmentTransaction transaction1 = getFragmentManager()
					.beginTransaction();
			transaction1.setCustomAnimations(R.animator.slide_in_right,
					R.animator.slide_out_left);
			transaction1.hide(Fragment_MusikListe.newInstance(arr, sliteVolume));
			transaction1.show(Fragment_Nohitaky.newInstance());
			transaction1
					.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
			transaction1.addToBackStack(null);
			transaction1.commit();*/

			this.runOnUiThread(new Runnable() {
				
				@Override
				public void run() {
					// TODO Auto-generated method stub
					Fragment_Nohitaky.newInstance().getView().setVisibility(View.VISIBLE);
					Fragment_MusikListe.newInstance(arr, sliteVolume).getView().setVisibility(View.INVISIBLE);
				}
			});

			
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
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		System.gc();
	}

}
