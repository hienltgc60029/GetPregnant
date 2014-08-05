package vn.theagency.bussiness;

import java.util.ArrayList;

import vn.theagency.objects.Songs;
import android.content.Context;

public class Store {
	private static Store _ins;
	private Context context;
	ArrayList<Songs> arr;
	public Store (Context _context, ArrayList<Songs> arr){
		this.context = _context;
		this.arr = arr;
		
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
}
