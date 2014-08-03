package vn.theagency.helper;

import java.util.HashMap;

import vn.theagency.layout.UI_Home;
import android.content.Context;

public class Values {
	private Helper insHelper;
	private Context context;
	private static Values _ins;
	static HashMap<String, Integer> map;
	/**
	 * 
	 * @param _context
	 */
	
	
	
	private Values(Context _context) {
		this.context = _context;
		this.insHelper = Helper.shareIns(this.context);
		initValues();
	}

	public static Values shareIns(Context _context) {
		if (Values._ins == null) {
			Values._ins = new Values(_context);
			// Layout_Titles._ins.insHelper = Helper.shareIns(_context);
		}

		return Values._ins;
	}

	/**
	 * 
	 */
	public static void initValues(){
		
	}

}
