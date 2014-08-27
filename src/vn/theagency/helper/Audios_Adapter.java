package vn.theagency.helper;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import vn.theagency.getpregnant.Audios_Library;
import vn.theagency.getpregnantapplication.R;
import vn.theagency.objects.Audios;
import vn.theagency.sqlite.SQliteData;
import android.annotation.SuppressLint;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.drawable.AnimationDrawable;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaPlayer.OnErrorListener;
import android.media.MediaPlayer.OnPreparedListener;
import android.os.AsyncTask;
import android.os.Environment;
import android.os.IBinder;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.vending.billing.IInAppBillingService;

@SuppressLint("InflateParams")
public class Audios_Adapter extends BaseAdapter {

	public int layout;
	public Context mContext;
	public ArrayList<Audios> arr;
	public int sizeRow;
	private Helper mHelper;
	boolean isStatus = false;
	
	MediaPlayer mediaPlayer;

	//
	public String setPathByID(String name) {
		String url = Environment.getExternalStorageDirectory()
				+ "/GetPregnant/" + name;
		return url;
	}

	public Audios_Adapter(int layout, Context mContext, ArrayList<Audios> arr,
			int sizeRow) {
		super();
		this.layout = layout;
		this.mContext = mContext;
		this.arr = arr;
		this.sizeRow = sizeRow;
		this.mHelper = Helper.shareIns(mContext);

	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return arr.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return arr.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		View view = convertView;
		MyViewHolder mViewHolder;
		isStatus = false;
		

		if (view == null) {
			LayoutInflater inflater = (LayoutInflater) mContext
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			view = inflater.inflate(R.layout.audios_items, null);
			mViewHolder = new MyViewHolder();
			view.setTag(mViewHolder);
			//
			AbsListView.LayoutParams params = new AbsListView.LayoutParams(
					AbsListView.LayoutParams.MATCH_PARENT, sizeRow);

			view.setLayoutParams(params);
			int widthImage = (int) this.mContext.getResources().getDimension(
					R.dimen.AVARTA_WIDTH);
			int widthBtn = (int) this.mContext.getResources().getDimension(
					R.dimen.ItemText_Width);
			int margin = (int) this.mContext.getResources().getDimension(
					R.dimen.MarginLeft);
			int widthRow = (int) (this.mHelper.getAppWidth() - widthBtn
					- margin - widthImage);
			view.setPadding((int) widthRow / 2, 0, 0, 0);

		}
		mViewHolder = (MyViewHolder) view.getTag();

		mViewHolder.mTitle = mTextView(view, R.id.txtTitle, arr.get(position)
				.getmTitle());

		mViewHolder.mPrice = mTextView(view, R.id.txtprice, arr.get(position)
				.getmPrice());
		Typeface type = Typeface.createFromAsset(mContext.getAssets(),
				"MuseoSans_500.otf");

		mViewHolder.mPrice.setTypeface(type, Typeface.NORMAL);

		mViewHolder.imageView = mImage(view, R.id.img,
				arr.get(position).mImageURL);
		mViewHolder.mDec = mTextView(view, R.id.txtDec, arr.get(position)
				.getmDecription());
		mViewHolder.btnView = mButton(view, R.id.btnView, R.drawable.btn_view);
		mViewHolder.btnView.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (isStatus) {
					Toast.makeText(mContext,
							"Waiting 15 second for last view!",
							Toast.LENGTH_LONG).show();
					return;
				}
				isStatus = true;

				if (mediaPlayer != null && mediaPlayer.isPlaying()) {
					Toast.makeText(mContext,
							"Waiting 15 second for last view!",
							Toast.LENGTH_LONG).show();
				}
				if (mediaPlayer == null) {
					PlayOnlineUrl("http://transcode1.mediafire.com/6ckys3lajnpg/2os38v816o1e4vi/130d/Zuru%C2%A6%C3%AAck+kommen.mp3?container=mp3");
				} else {
					if (!mediaPlayer.isPlaying()) {
						PlayOnlineUrl("http://transcode1.mediafire.com/6ckys3lajnpg/2os38v816o1e4vi/130d/Zuru%C2%A6%C3%AAck+kommen.mp3?container=mp3");
					}
				}

			}
		});

		if (arr.get(position).getStatus() == 1) {
			if (arr.get(position).isActive) {
				mViewHolder.btnDownload = mButton(view, R.id.btn,
						R.drawable.btn_download);
				mViewHolder.btnDownload
						.setOnClickListener(new OnClickListener() {
							@Override
							public void onClick(View v) {
								// TODO Auto-generated method stub
								AsyntaskDownloadMp3 mp3 = new AsyntaskDownloadMp3(
										arr.get(position).getmID(), mContext);
								mp3.execute(arr.get(position).getmURLMp3());
								arr.get(position).setDownload(true);
								arr.get(position).setStatus(2);
								for(int i = 0; i < mHelper.audiosList.size();i++){
									if(mHelper.audiosList.get(i).getmID().equalsIgnoreCase(arr.get(position).getmID())){
										mHelper.audiosList.get(i).setStatus(2);
									}
								}
								SQliteData data = new SQliteData(mContext);
								data.open();
								data.removeAudiosByStatus(2);
								
								data.putAudios(arr.get(position));
								data.close();
								
								mHelper.isDownload=true;
								notifyDataSetChanged();
								
							}
						});
			} else {
				arr.get(position).setStatus(1);
				mViewHolder.btnDownload = mButton(view, R.id.btn,
						R.drawable.btn_buy);
				mViewHolder.btnDownload
						.setOnClickListener(new OnClickListener() {

							@Override
							public void onClick(View v) {
								// TODO Auto-generated method stub
								((Audios_Library) mContext).getHandler()
										.sendEmptyMessage(position);
							}
						});

			}
		} else {
			mViewHolder.btnDownload = mButton(view, R.id.btn,
					R.drawable.animation_download);
			AnimationDrawable drawable = (AnimationDrawable) mViewHolder.btnDownload
					.getBackground();
			drawable.start();
		}
		
		if (mHelper.isDownload) {
			mViewHolder.btnDownload.setEnabled(false);
		} else {
			mViewHolder.btnDownload.setEnabled(true);
		}
		//
		mViewHolder.mDec.setText(setTextRight(arr.get(position)
				.getmDecription()));

		if ((position % 2) != 0) {
			view.setBackgroundResource(R.drawable.bg_library);
		}

		return view;
	}
	
	
	
	
	

	public int testLenght(int textSize, String text) {
		Paint paint = new Paint();
		final float densityMultiplier = mContext.getResources()
				.getDisplayMetrics().density;
		final float scaledPx = mHelper.DpToPixel(textSize) * densityMultiplier;
		paint.setTextSize(scaledPx);
		final float size = paint.measureText(text);
		int testLenght = (int) (size / mHelper.DpToPixel(220));

		return testLenght;
	}

	private class MyViewHolder {
		TextView mTitle, mDec, mPrice;
		FrameLayout imageView;
		Button btnView, btnDownload;
		ImageView mIcon;

	}

	private TextView mTextView(View v, int resId, String textview) {
		TextView tv = (TextView) v.findViewById(resId);
		tv.setText(textview);
		return tv;
	}

	private Button mButton(View v, int resId, int button) {
		Button btn = (Button) v.findViewById(resId);
		btn.setBackgroundResource(button);
		return btn;
	}

	private FrameLayout mImage(View v, int resId, int image) {
		FrameLayout iv = (FrameLayout) v.findViewById(resId);
		iv.setBackgroundResource(image); //
		return iv;
	}

	private ImageView mImageIcon(View v, int resId, int image) {
		ImageView iv = (ImageView) v.findViewById(resId);
		iv.setBackgroundResource(image); //
		return iv;
	}

	public String setTextRight(String text) {
		int index = 0;
		text = text.trim();
		String newText = text;

		if (testLenght(12, text) > 4) {
			for (int j = (text.length() - 2); j > 0; j--) {
				char temp = text.charAt(j);
				if (String.valueOf(temp).equals(" ")) {
					index++;
					if (index == 2) {
						if (testLenght(12, text.substring(0, j)) == 4) {
							newText = text.substring(0, j) + "\n"
									+ text.substring(j + 1);
						}
					}
				}
			}

		}

		return newText;
	}

	public void setEnvironmentBilling() {
		((Audios_Library) mContext).mConnection = new ServiceConnection() {

			@Override
			public void onServiceDisconnected(ComponentName name) {
				// TODO Auto-generated method stub
				((Audios_Library) mContext).mService = null;
			}

			@Override
			public void onServiceConnected(ComponentName name, IBinder service) {
				// TODO Auto-generated method stub
				((Audios_Library) mContext).mService = IInAppBillingService.Stub
						.asInterface(service);
			}
		};
		mContext.bindService(new Intent(
				"com.android.vending.billing.InAppBillingService.BIND"),
				((Audios_Library) mContext).mConnection,
				Context.BIND_AUTO_CREATE);
	}

	private void PlayOnlineUrl(String url) {

		mediaPlayer = new MediaPlayer();
		mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
		try {
			mediaPlayer.setDataSource(url);
		} catch (Exception e) {
			e.printStackTrace();
		}
		mediaPlayer.prepareAsync();
		// You can show progress dialog here untill it prepared to play
		mediaPlayer.setOnPreparedListener(new OnPreparedListener() {
			@Override
			public void onPrepared(MediaPlayer mp) {
				// Now dismis progress dialog, Media palyer will start playing
				mp.start();
				Audios_Adapter.this.notifyDataSetChanged();
			}
		});
		mediaPlayer.setOnErrorListener(new OnErrorListener() {
			@Override
			public boolean onError(MediaPlayer mp, int what, int extra) {
				// dissmiss progress bar here. It will come here when
				// MediaPlayer
				// is not able to play file. You can show error message to user
				return false;
			}
		});
		mediaPlayer.setOnCompletionListener(new OnCompletionListener() {

			@Override
			public void onCompletion(MediaPlayer mp) {
				// TODO Auto-generated method stub
				isStatus = false;
			}
		});
	}
	
	
	

}
