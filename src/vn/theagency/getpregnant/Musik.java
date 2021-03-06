package vn.theagency.getpregnant;

import java.util.ArrayList;

import vn.theagency.fragment.Fragment_MusikListe;
import vn.theagency.fragment.Fragment_Nohitaky;
import vn.theagency.getpregnantapplication.R;
import vn.theagency.helper.GetSongsAll;
import vn.theagency.helper.Helper;
import vn.theagency.layout.UI_Musik;
import vn.theagency.objects.Audios;
import vn.theagency.objects.Songs;
import vn.theagency.sqlite.SQliteData;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.FragmentTransaction;
import android.content.Intent;
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
import android.widget.Toast;

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
	Audios audio;
	public String index;
	
	FragmentTransaction transaction;
	public String namePDF="";

	Handler handler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			super.handleMessage(msg);
			Log.i("LTH", String.valueOf(msg.arg1));
			actionClickHander(msg.arg1);	
		}
	};
	Handler handler2 = new Handler(){

		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			super.handleMessage(msg);
			
			Intent openClockIntent = new Intent("android.intent.action.SET_ALARM");
			openClockIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			startActivity(openClockIntent);
		}
		
	};
	public Handler getHandler(){
		return handler2;
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.musik);
		
		try{
			index = getIntent().getExtras().getString("Audios");
			setPDFbyIndex(Integer.parseInt(index));
			getArray();
			if(getIntent().getExtras().getString(Audios.ID)!=null){
				
				SQliteData data = new SQliteData(getApplicationContext());
				data.open();
				audio =data.getAudioByID(getIntent().getExtras().getString(Audios.ID));
				data.close();
				
				

				FragmentTransaction transaction = getFragmentManager()
						.beginTransaction();
				transaction.add(R.id.musik, Fragment_Nohitaky.newInstance(audio,true));
				transaction.commit();
		}
		}catch(Exception ex){
			Toast.makeText(getApplicationContext(), "This Function", Toast.LENGTH_LONG).show();
			Intent intent = new Intent(getApplicationContext(),Cover.class);
			startActivity(intent);
			System.exit(0);
		}
	}
	public void setPDFbyIndex(int posi){
		switch (posi) {
		case 1:
			//Ausgchen
			namePDF = "ausgleichen.pdf";
			break;
		case 2:
			//auflonsen
			namePDF = "auflosen.pdf";
			break;
		case 3:
			//unterstutzen
			namePDF = "unterstutzen.pdf";
			break;
		case 4:
			//verbessern
			namePDF = "verbessern.pdf";
			break;
		case 5:
			//vorbereiten
			namePDF = "vorbereiten.pdf";
			break;

		default:
			break;
		}
	}
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		
	}
	public void actionClickHander(int action) {
		switch (action) {
		case 1:
			FragmentTransaction transaction1 = getFragmentManager()
					.beginTransaction();
			transaction1.replace(R.id.musik,
					Fragment_MusikListe.newInstance(true, arr));
			transaction1.commit();

			break;
		case 2:
			FragmentTransaction transaction = getFragmentManager()
					.beginTransaction();
			transaction.replace(R.id.musik,
					Fragment_Nohitaky.newInstance(audio,true));
			transaction.commit();
			break;
		case 4:
			Fragment_Nohitaky.newInstance(null,false);
			FragmentTransaction transaction2 = getFragmentManager()
					.beginTransaction();
			transaction2.replace(R.id.musik,
					Fragment_Nohitaky.newInstance(audio,true));
			transaction2.commit();
			break;
		case 3:
			Fragment_MusikListe.newInstance(false, null);
			FragmentTransaction transaction3 = getFragmentManager()
					.beginTransaction();
			transaction3.replace(R.id.musik,
					Fragment_MusikListe.newInstance(true, arr));
			transaction3.commit();
			break;
		default:
			break;
		}
	}

	public Messenger getMessenger() {
		return new Messenger(handler);
	}

	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		
		

	}

	public void getArray() {
		String timePhut = null;
		String timeGiay = null;
		GetSongsAll all = new GetSongsAll();
		arr = all.getPlayList(getApplicationContext());

		for (int i = 0; i < arr.size(); i++) {

			int phut = (int) ((Integer.parseInt(arr.get(i).getmLine()) / 60000) % 60);
			if (phut < 10) {
				timePhut = "0" + String.valueOf(phut);
			} else {
				timePhut = String.valueOf(phut);
			}

			int giay = (int) (((Integer.parseInt(arr.get(i).getmLine()) - (phut * 60000)) / 1000) % 60);
			if (giay < 10) {
				timeGiay = "0" + String.valueOf(giay);
			} else {
				timeGiay = String.valueOf(giay);
			}
			arr.get(i).setmLine(timePhut + ":" + timeGiay);
		}
	}

	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
		Fragment_Nohitaky.newInstance(null,false);
		Fragment_MusikListe.newInstance(false, null);
		
		
	}

	

	

}
