package vn.theagency.fragment;

import java.util.Calendar;

import vn.theagency.getpregnant.Musik;
import vn.theagency.helper.Helper;
import vn.theagency.helper.Key;
import vn.theagency.layout.UI_Nohitaky;
import android.app.AlarmManager;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.os.Message;
import android.os.Messenger;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.RelativeLayout;
import android.widget.Switch;

public class Fragment_Nohitaky extends Fragment implements OnClickListener,OnCheckedChangeListener {
	private Helper mHelper;
	public UI_Nohitaky mNohitaky;
	
	public FrameLayout wrapper;
	public FrameLayout initUIBack;
	public View initUIBackGround,initUIBackGroundDark;
	public FrameLayout initUIMusik;
	public LinearLayout initUIBottom;
	public RelativeLayout initUIMusicBar,initUITitle;
	Messenger messenger;
	Message msg;
	View play_musik;
	
	ImageView wecker;
	CheckBox musikCheck,pdfCheck;
	Switch changeBg;
	private static Fragment_Nohitaky _ins;
	
	public static Fragment_Nohitaky newInstance() {
		if(Fragment_Nohitaky._ins==null){
			Fragment_Nohitaky._ins = new Fragment_Nohitaky();
		}
        return Fragment_Nohitaky._ins;
    }
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		this.mHelper = Helper.shareIns(getActivity());
		this.mNohitaky = UI_Nohitaky.shareIns(getActivity());
		
		this.initUIBackGround = this.mNohitaky.initUIBackGround();
		this.initUIBack = this.mNohitaky.initUIBack();
		this.initUIMusik = this.mNohitaky.initUIMusik();
		this.initUIBottom = this.mNohitaky.initUIBottom();
		this.initUIMusicBar = this.mNohitaky.initUIMusicBar();
		this.initUITitle = this.mNohitaky.initUITitle();
		this.initUIBackGroundDark = this.mNohitaky.initUIBackGroundDark();
		
		initUI();
		preference();
		Musik musik = (Musik) getActivity();
		messenger = musik.getMessenger();
		msg = new Message();
		return wrapper;
	}
	public void preference(){
		onAttach(getActivity());
		play_musik = wrapper.findViewById(Key.PLAY);
		play_musik.setOnClickListener(this);
		
		musikCheck = (CheckBox) this.wrapper.findViewById(Key.NOHITAKI_MusikCheck);
		musikCheck.setOnCheckedChangeListener(this);
		pdfCheck = (CheckBox) this.wrapper.findViewById(Key.NOHITAKI_PDFCheck);
		
		wecker = (ImageView) this.wrapper.findViewById(Key.NOHITAKI_Wecker);
		wecker.setOnClickListener(this);
		
		changeBg = (Switch) this.wrapper.findViewById(Key.NOHITAKI_ChangeBg);
		changeBg.setChecked(true);
		changeBg.setOnCheckedChangeListener(this);
	}
	public void initUI() {
		this.wrapper = new FrameLayout(this.getActivity());
		FrameLayout.LayoutParams para = new FrameLayout.LayoutParams(
				FrameLayout.LayoutParams.MATCH_PARENT,
				FrameLayout.LayoutParams.MATCH_PARENT);
		this.wrapper.setLayoutParams(para);
		
		
		this.wrapper.addView(this.initUIBackGround);
		this.wrapper.addView(this.initUIBackGroundDark);
		this.wrapper.addView(initUIMusik);
		this.wrapper.addView(initUIBack);
		this.wrapper.addView(initUITitle);
		this.wrapper.addView(initUIBottom);
		this.wrapper.addView(initUIMusicBar);
		
	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case Key.PLAY:
			try{
				msg.arg1 =1;
				messenger.send(msg);
			}catch(Exception ex){
				ex.printStackTrace();
			}
			break;
		case Key.NOHITAKI_Wecker:
			PopupMenu menu = new PopupMenu(getActivity(), v);
			
			break;
		default:
			break;
		}
	}

	@Override
	public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
		// TODO Auto-generated method stub
		if(changeBg.isChecked()){
			initUIBackGroundDark.setVisibility(View.INVISIBLE);
		}else{
			initUIBackGroundDark.setVisibility(View.VISIBLE);
		}
	}
}
