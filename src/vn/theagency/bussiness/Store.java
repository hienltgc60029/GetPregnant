package vn.theagency.bussiness;

import java.util.ArrayList;

import vn.theagency.getpregnant.R;
import vn.theagency.objects.Audios;
import vn.theagency.objects.Songs;
import android.content.Context;

public class Store {
	private static Store _ins;
	private Context context;
	ArrayList<Songs> arr;
	public ArrayList<Audios> audiosList;
	public Store (Context _context, ArrayList<Songs> arr){
		this.context = _context;
		this.arr = arr;
		audiosArray();
	}
	
	
	public static Store shareIns(Context _context, ArrayList<Songs> arr){
		if(Store._ins==null){
			Store._ins = new Store(_context,arr);
			
		}
		return Store._ins;
	}
	public ArrayList<Songs> getMusikSlite(){
		return arr;
	}
	public ArrayList<Audios> audiosArray() {
		audiosList = new ArrayList<Audios>();
		Audios audios= null;
		audios = new Audios("1",
				"Wendeltreppe",
				"Einleitung (kann vor jede Hypnose gesetzt werden)", "", R.drawable.wen01);
		audiosList.add(audios);
		audios = new Audios("2",
				"Lieblingsplatz",
				"Blindtext elitis endiatiu sincil lue mol est Uciam ipita int.",
				"", R.drawable.lie01);
		audiosList.add(audios);
		audios = new Audios("3",
				"Zurückkommen",
				"Sollte nach jeder Hypnose als Abschluss folgen,... ",
				"", R.drawable.zur01);
		audiosList.add(audios);
		audios = new Audios("4",
				"Gegensätze",
				"Spüre körperlich, wie sich negative Gedanken auf deineStimmung..",
				"", R.drawable.geg01);
		audiosList.add(audios);
		return audiosList;
	}
}
