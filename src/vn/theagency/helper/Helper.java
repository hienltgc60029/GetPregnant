package vn.theagency.helper;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import vn.theagency.getpregnantapplication.R;
import vn.theagency.objects.Audios;

import android.R.bool;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Environment;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.WindowManager;

public class Helper {
	
	private Context context;
	private static Helper _ins;
	
	public float density;
	public float sDensity;
	public float appWidth;
	public float appHeight;
	public float dpWidth;
	public float dpHeight;
	public boolean isDownload=false;
	public ArrayList<Audios> audiosList;
	
	public Helper (Context _context){
		this.context = _context;
		getScreenActivity();
		getArray();
	}
	
	
	public static Helper shareIns(Context _context){
		if(Helper._ins==null){
			Helper._ins = new Helper(_context);
			
		}
		return Helper._ins;
	}
	
	/**
	  * 
	  * @param dp
	  * @return
	  */
	
	
	 public float DpToPixel(int dp) {
	  return (dp * this.density);
	 }
	 public float PixelToDp(int pixel){
		 return (pixel / this.density);
		 
	 }
	 public float DpToSp(int dp){
		 float pix = dp*this.density;
		 return (pix / this.sDensity);
	 }
	 public float PxToSp(int px){
		 
		 return (px / this.sDensity);
	 }
	 
	 
	 public void getScreenActivity() {
			Log.i("LTH", "set");
			Display display = ((WindowManager) this.context.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
			DisplayMetrics outMetrics = new DisplayMetrics();
			display.getMetrics(outMetrics);

			this.density = this.context.getResources().getDisplayMetrics().density;
			this.sDensity = this.context.getResources().getDisplayMetrics().scaledDensity;
			float _density = this.density;
			this.appWidth = outMetrics.widthPixels;
			this.appHeight = outMetrics.heightPixels;
			this.dpWidth = (int) (outMetrics.widthPixels / _density);
			this.dpHeight = (int) (outMetrics.heightPixels / _density);
			Log.i("LTH", String.valueOf(this.dpWidth));
			Log.i("LTH", String.valueOf(this.dpHeight));
			Log.i("LTH", String.valueOf(this.appWidth));
			Log.i("LTH", String.valueOf(this.appHeight));
			
		}


	public float getAppWidth() {
		return appWidth;
	}
	public float getAppHeight() {
		return appHeight;
	}
	public float getDpWidth() {
		return dpWidth;
	}	
	public float getDpHeight() {
		return dpHeight;
	}
	public int getNumberCharTextView(int width,int textsize){
		return (int)(width/textsize);
	}
	public String count(int process){
		String number = null;
		String timePhut = null;
		String timeGiay = null;
		
		

			int phut = (int) (process / 60);
			if (phut < 10) {
				timePhut = "0" + String.valueOf(phut);
			} else {
				timePhut = String.valueOf(phut);
			}

			int giay = (int) (process - (phut * 60));
			if (giay < 10) {
				timeGiay = "0" + String.valueOf(giay);
			} else {
				timeGiay = String.valueOf(giay);
			}
			number = timePhut+ ":"+timeGiay;
		
		return number;
	}

	public boolean isOnline(Context context) {
	    ConnectivityManager cm =
	        (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
	    NetworkInfo netInfo = cm.getActiveNetworkInfo();
	    if (netInfo != null && netInfo.isConnectedOrConnecting()) {
	        return true;
	    }
	    return false;
	}
	
	
	public void getArray(){
		audiosList = new ArrayList<Audios>();
		Audios audios;
		audios = new Audios("wen","Wendeltreppe",
				"Hilft Dir dich noch tiefer zu entspannen, Du benötigst jedoch mehr Zeit.", "Gratis", R.drawable.avarta13
				,Constant.URL_WENDELTREPPE,1);
		audiosList.add(audios);
		
		audios = new Audios("zuruckkommen",
				"Zurückkommen",
				"Sollte nach jeder Hypnose als Abschluss folgen, ausser Du möchtest nachher schlafen.",
				"Gratis", R.drawable.avarta15,Constant.URL_ZURU,1);
		audiosList.add(audios);
		audios = new Audios("farben",
				"Farben atmen",
				"Perfekt für all die, welche Entspannung brauchen und Farben mögen.",
				"Gratis", R.drawable.avarta02,Constant.URL_BREATHING,1);
		audiosList.add(audios);
		audios = new Audios("fuhle",
				"Fühle deine eigene Gelassenheit wieder",
				Constant.URL_RESSOURCEN,
				"Gratis", R.drawable.avarta05,"",1);
		audiosList.add(audios);
		audios = new Audios("lieblingsplatz",
				"Lieblingsplatz",
				"Ein geheimer Platz, an welchem man vor allen Problemen geschützt ist.",
				"4CHF/3€", R.drawable.avarta10,Constant.URL_LIEBLINGSPLATZ,1);
		audiosList.add(audios);	
		
		audios = new Audios("ture",
				"Türe der Erkenntnis",
				"Realisiere, dass nur Du entscheidest, wie Du.",
				"2CHF/1.50€", R.drawable.avarta12,Constant.URL_TUREDER,1);
		audiosList.add(audios);
		
		audios = new Audios("grenzen",
				"Grenzen stärken",
				"Diese Hypnose wird Dir helfen, dich emotional abgegrenzter zu fühlen.",
				"4CHF/3€", R.drawable.avarta06,"",1);
		audiosList.add(audios);
		audios = new Audios("zoo",
				"Zoo der Emotionen",
				"Lass deinen Selbstzweifel und deinen Stress eingesperrt im Zoo zurück.",
				"4CHF/3€", R.drawable.avarta14,Constant.URL_ZOO,1);
		audiosList.add(audios);
		audios = new Audios("einnistung",
				"Einnistung",
				"Unterstütze deinen Körper und deinen Geist nach einem Transfer oder einer IUI.",
				"3CHF/2€", R.drawable.avarta01,Constant.URL_EINNISTUNG,1);
		audiosList.add(audios);
		audios = new Audios("sturmwolken",
				"Sturmwolken",
				"Egal, wie das Ergebnis wird, das Leben geht weiter.",
				"3CHF/2€", R.drawable.avarta11,Constant.URL_STURMWOLKEN,1);
		audiosList.add(audios);
		audios = new Audios("gegensatze",
				"Gegensätze",
				"Spüre körperlich, wie sich negative Gedanken auf deineStimmung auswirken und was Du dagegen tun kannst.",
				"3CHF/2€", R.drawable.avarta04,Constant.URL_GEGENSA,1);
		audiosList.add(audios);
		audios = new Audios("fruchtbarkeitsgarten",
				"Fruchtbarkeitsgarten",
				"Bereite alles so in deinem Fruchtbarkeitsgarten vor, dass Du nur noch anzupﬂanzen brauchst.",
				"3CHF/2€", R.drawable.avarta03,Constant.URL_FRUCHTBARKEITSGARTEN,1);
		audiosList.add(audios);
		audios = new Audios("heilendes",
				"Heilendes \nweisses Licht",
				"Eine wunderbare Hypnose um Stress abzubauen.",
				"4CHF/3€", R.drawable.avarta07,Constant.URL_HEILENDES,1);
		audiosList.add(audios);
		audios = new Audios("ivf",
				"IVF Vorbereitung",
				"Hilf Dir und deinem Körper, die Behandlung optimal zu nutzen.",
				"4CHF/3€", R.drawable.avarta08,Constant.URL_IVF,1);
		audiosList.add(audios);
		audios = new Audios("kontrollzentrale",
				"Kontrollzentrale",
				"In deiner persönlichen Kontrollzentrale kannst Du alles so einstellen, wie es sein sollte.",
				"4CHF/3€",R.drawable.avarta09,Constant.URL_KOTROLLZENTRUS,1);
		audiosList.add(audios);		
		
	}
	
	
	/*public void mp3load(String urlRequest,String name) {
		try{
		URL url = new URL(urlRequest);
		            HttpURLConnection c = (HttpURLConnection) url.openConnection();
		            c.setRequestMethod("GET");
		            c.setDoOutput(true);
		            c.connect();

		            String PATH = Environment.getExternalStorageDirectory()
		                    + "/GetPregnant/";
		            
		            File file = new File(PATH);
		            file.mkdirs();

		             String fileName = name;


		            File outputFile = new File(file, fileName);
		            FileOutputStream fos = new FileOutputStream(outputFile);

		            InputStream is = c.getInputStream();

		            byte[] buffer = new byte[1024];
		            int len1 = 0;
		            while ((len1 = is.read(buffer)) != -1) {
		                fos.write(buffer, 0, len1);
		            }
		            fos.close();
		            is.close();
		}catch(Exception ex){
			ex.printStackTrace();
		}
		}*/
	 
}
