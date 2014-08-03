package vn.theagency.fragment;

import vn.theagency.getpregnant.Cover;
import vn.theagency.getpregnant.Home;
import vn.theagency.getpregnant.R;
import vn.theagency.helper.Helper;
import vn.theagency.helper.Key;
import vn.theagency.layout.UI_Home;
import android.animation.Animator;
import android.animation.Animator.AnimatorListener;
import android.animation.AnimatorInflater;
import android.app.Fragment;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Message;
import android.os.Messenger;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class Fragment_Home extends Fragment implements OnClickListener,
		AnimationListener, AnimatorListener {

	View view = null;
	public String textTitle, textDec;
	public FrameLayout wrapper, layoutTitles, initUIText;
	public UI_Home titles;
	LinearLayout layout;
	FrameLayout linearAuflosen, linearAusgleichen, linearVerbessern,
			linearVorbereiten, linearUnterstutzen;
	FrameLayout initUIAddition;
	private Helper mHelper;
	LinearLayout linearLayout;
	ImageView imgAusgleichen, imgAuflosen, imgVerbessern, imgVorbereiten,
			imgUnterstutzen, initUIHome, auswahl;
	ImageView home;
	Messenger messenger;
	Message msg;
	TextView plus;
	View bg, frist;

	public static Fragment_Home newInstance(int pTop, int bg, int background,
			int pTitle) {
		Fragment_Home f = new Fragment_Home();
		Bundle args = new Bundle();
		args.putInt("TOP", pTop);
		args.putInt("BG", bg);
		args.putInt("background", background);
		args.putInt("pTitle", pTitle);
		f.setArguments(args);
		return f;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		this.mHelper = Helper.shareIns(getActivity());
		this.titles = UI_Home.shareIns(getActivity());
		Home home = (Home) getActivity();
		messenger = home.getMessenger();
		msg = new Message();

		layoutTitles = this.titles.initUI(getArguments().getInt("pTitle"));
		initUIAddition = this.titles.initUIAddition(R.drawable.btn_ver_normal);
		initUIText = this.titles.initUIText(getArguments().getInt("TOP"),
				getArguments().getInt("BG"));
		initUIHome = this.titles.initUIHome();
		this.initUI();
		return wrapper;
	}

	public void preference() {

		linearAuflosen = (FrameLayout) wrapper.findViewById(Key.linearAuflosen);
		linearAusgleichen = (FrameLayout) wrapper
				.findViewById(Key.linearAusgleichen);
		linearVerbessern = (FrameLayout) wrapper
				.findViewById(Key.linearVerbessern);
		linearVorbereiten = (FrameLayout) wrapper
				.findViewById(Key.linearVorbereiten);
		linearUnterstutzen = (FrameLayout) wrapper
				.findViewById(Key.linearUnterstutzen);

		switch (getArguments().getInt("background")) {
		case R.drawable.bg:
			linearAusgleichen.setVisibility(FrameLayout.GONE);
			break;
		case R.drawable.bg_auf:
			linearAuflosen.setVisibility(FrameLayout.GONE);
			break;
		case R.drawable.bg_unt:
			linearUnterstutzen.setVisibility(FrameLayout.GONE);
			break;
		case R.drawable.bg_ver:
			linearVerbessern.setVisibility(FrameLayout.GONE);
			break;
		case R.drawable.bg_vor:
			linearVorbereiten.setVisibility(FrameLayout.GONE);
			break;

		default:
			break;
		}

		imgAuflosen = (ImageView) wrapper.findViewById(Key.auflosen);
		imgAusgleichen = (ImageView) wrapper.findViewById(Key.ausgleichen);
		imgUnterstutzen = (ImageView) wrapper.findViewById(Key.unterstutzen);
		imgVerbessern = (ImageView) wrapper.findViewById(Key.verbessern);
		imgVorbereiten = (ImageView) wrapper.findViewById(Key.vorbereiten);
		plus = (TextView) wrapper.findViewById(Key.AUDIOS_NAME);

	}

	public void initUI() {

		this.wrapper = new FrameLayout(getActivity());
		FrameLayout.LayoutParams para = new FrameLayout.LayoutParams(
				FrameLayout.LayoutParams.MATCH_PARENT,
				FrameLayout.LayoutParams.MATCH_PARENT);
		this.wrapper.setLayoutParams(para);
		// this.wrapper.setBackgroundResource(R.drawable.bg);
		// Image BG
		frist = new View(getActivity());
		FrameLayout.LayoutParams paraBg = new FrameLayout.LayoutParams(
				FrameLayout.LayoutParams.MATCH_PARENT,
				FrameLayout.LayoutParams.MATCH_PARENT);
		frist.setBackgroundColor(Color.BLACK);
		frist.setLayoutParams(paraBg);

		bg = new View(getActivity());

		bg.setBackgroundResource(getArguments().getInt("background"));
		bg.setLayoutParams(paraBg);

		// Image Home
		this.wrapper.addView(frist);
		this.wrapper.addView(bg);
		this.wrapper.addView(initUIHome);
		this.wrapper.addView(initUIText);
		//

		this.wrapper.addView(layoutTitles);
		this.wrapper.addView(initUIAddition);

		// getActivity().setContentView(this.wrapper);
		preference();

		auswahl = (ImageView) wrapper.findViewById(Key.AUSWAHL);
		auswahl.setOnClickListener(this);

		imgAuflosen.setOnClickListener(this);
		imgAusgleichen.setOnClickListener(this);
		imgUnterstutzen.setOnClickListener(this);
		imgVerbessern.setOnClickListener(this);
		imgVorbereiten.setOnClickListener(this);

		home = (ImageView) wrapper.findViewById(Key.HOME);
		home.setOnClickListener(this);

	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case Key.auflosen:

			try {

				clearMemory();
				msg.arg1 = 2;
				msg.arg2 = 0;
				messenger.send(msg);
			} catch (Exception ex) {
				ex.printStackTrace();
			}

			break;
		case Key.ausgleichen:
			try {
				clearMemory();
				msg.arg1 = 1;
				msg.arg2 = 0;
				messenger.send(msg);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			break;
		case Key.unterstutzen:
			try {
				clearMemory();
				msg.arg1 = 3;
				msg.arg2 = 0;
				messenger.send(msg);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			break;
		case Key.verbessern:
			try {
				clearMemory();
				msg.arg1 = 4;
				msg.arg2 = 0;
				messenger.send(msg);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			break;
		case Key.vorbereiten:
			try {
				clearMemory();
				msg.arg1 = 5;
				msg.arg2 = 0;
				messenger.send(msg);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			break;
		case Key.AUSWAHL:

			Animation animation = AnimationUtils.loadAnimation(getActivity(),
					R.anim.slide_up);
			Animation animationText = AnimationUtils.loadAnimation(
					getActivity(), R.anim.slide_text);

			this.initUIHome.startAnimation(animation);
			this.initUIText.startAnimation(animation);
			this.bg.startAnimation(animation);
			animationText.setAnimationListener(this);
			this.initUIAddition.startAnimation(animationText);
			break;
		case Key.HOME:
			try {
				clearMemory();
				msg.arg1 = 7;
				msg.arg2 = 0;
				messenger.send(msg);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			break;

		default:
			break;
		}
	}

	public void clearMemory() {
		imgAuflosen.setOnClickListener(null);
		imgAusgleichen.setOnClickListener(null);
		imgUnterstutzen.setOnClickListener(null);
		imgVerbessern.setOnClickListener(null);
		imgVorbereiten.setOnClickListener(null);
		auswahl.setOnClickListener(null);
	}

	@Override
	public void onAnimationStart(Animation animation) {
		// TODO Auto-generated method stub
		auswahl.setEnabled(false);
	}

	@Override
	public void onAnimationEnd(Animation animation) {
		// TODO Auto-generated method stub

		Animator animator = AnimatorInflater.loadAnimator(getActivity(),
				R.animator.card_flip_left_out);
		animator.addListener(this);
		animator.setTarget(auswahl);
		animator.start();

	}

	@Override
	public void onAnimationRepeat(Animation animation) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onAnimationStart(Animator animation) {
		// TODO Auto-generated method stub
		auswahl.setImageResource(R.drawable.btn_slip_normal);
	}

	@Override
	public void onAnimationEnd(Animator animation) {
		// TODO Auto-generated method stub
		try {
			//plus.setText("AUDIOS LIBRARY");
			clearMemory();

			switch (getArguments().getInt("background")) {
			case R.drawable.bg:
				msg.arg2 = 1;
				break;
			case R.drawable.bg_auf:
				msg.arg2 = 2;
				break;
			case R.drawable.bg_unt:
				msg.arg2 = 3;
				break;
			case R.drawable.bg_ver:
				msg.arg2 = 4;
				break;
			case R.drawable.bg_vor:
				msg.arg2 = 5;
				break;

			default:
				break;
			}

			msg.arg1 = 6;
			messenger.send(msg);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	@Override
	public void onAnimationCancel(Animator animation) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onAnimationRepeat(Animator animation) {
		// TODO Auto-generated method stub

	}
	
}
