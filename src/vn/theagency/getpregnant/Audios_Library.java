package vn.theagency.getpregnant;

import java.util.ArrayList;

import org.json.JSONException;
import org.json.JSONObject;

import com.android.vending.billing.IInAppBillingService;

import vn.theagency.getpregnantapplication.R;
import vn.theagency.helper.Audios_Adapter;
import vn.theagency.helper.Constant;
import vn.theagency.helper.Helper;
import vn.theagency.helper.Key;
import vn.theagency.layout.UI_Audios;
import vn.theagency.objects.Audios;
import vn.theagency.sqlite.SQliteData;
import android.animation.Animator;
import android.animation.Animator.AnimatorListener;
import android.app.Activity;
import android.app.DownloadManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class Audios_Library extends Activity implements OnClickListener,OnScrollListener {

	private Helper mHelper;
	public UI_Audios mAudios;
	
	LinearLayout initUIItems;
	public FrameLayout wrapper;
	Audios audios;
	ListView libraries, initUIListView;
	View initUIBgView,initUIBottom;
	ImageView initUIDown; 
	FrameLayout initUIHeader;
	View deine,plus, initUIConnection;
	FrameLayout audio;
	int pos;
	TextView textView;
	ArrayList<Audios> audiosList;
	public ArrayList<String> arrayActiveProduct;
	//
	View frist;
	Audios_Adapter adapter;
	public static Audios_Library mThis=null;
	//
	public IInAppBillingService mService;
	public ServiceConnection mConnection;
	public String inappid = "vn.theagency.getpregnantapp";
	
	public String[] ausgchen = new String[]{"wen","zuruckkommen","farben","fuhle"
			,"lieblingsplatz","kontrollzentrale","heilendes"};
	public String[] auflonsen = new String[]{"wen","zuruckkommen","ture","grenzen"
			,"zoo","heilendes"};
	public String[] unterstutzen = new String[]{"wen","zuruckkommen","einnistung","sturmwolken"
			};
	public String[] verbessern = new String[]{"wen","zuruckkommen","gegensatze","fruchtbarkeitsgarten"
	,"heilendes"};
	public String[] vorbereiten = new String[]{"wen","zuruckkommen","ivf","kontrollzentrale"
			};
	Handler handlerDownload = new Handler(){

		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			super.handleMessage(msg);
			for(int i = 0 ; i <  audiosList.size();i++){
				audiosList.get(i).setStatus(1);
			}
			adapter.notifyDataSetChanged();
		}
		
		
	};
	public Handler getHandlerDownload(){
		return handlerDownload;
	}
	public String setPathByID(String name){
		String url = Environment.getExternalStorageDirectory()+"/GetPregnant/"+name;
		return url;
	}
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub

		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);

		
		this.mHelper = Helper.shareIns(getApplicationContext());
		
		this.mAudios = UI_Audios.shareIns(getApplicationContext());

		initUIListView = this.mAudios.initUIListView();
		initUIHeader = this.mAudios.initUIHeader();
		initUIConnection = this.mAudios.initUIConnection();
		//initUIHeader.setClipChildren(false);
		
		initUIBgView = this.mAudios.initUIBgView();
		initUIBottom = this.mAudios.initUIBottom();
		initUIDown = this.mAudios.initUIDown();
		
		
		initUI();
	}
	public ArrayList<Audios> getArray(){
		for(int i = 0; i < audiosList.size();i++){
			if(audiosList.get(i).getmPrice().equalsIgnoreCase("Gratis")){
				audiosList.get(i).setActive(true);
			}
			for(int j = 0 ; j < arrayActiveProduct.size(); j++){
				if(audiosList.get(i).getmID().equalsIgnoreCase(arrayActiveProduct.get(j))){
					audiosList.get(i).setActive(true);
				}
			}
			
		}
		return audiosList;
	}
	
	
	public void setbackgroungHome(int action,View view){
		switch (action) {
		case 1:
			view.setBackgroundResource(R.drawable.bg);
			break;
		case 2:
			view.setBackgroundResource(R.drawable.bg_auf);
			break;
		case 3:
			view.setBackgroundResource(R.drawable.bg_unt);
			break;
		case 4:
			view.setBackgroundResource(R.drawable.bg_ver);
			break;
		case 5:
			view.setBackgroundResource(R.drawable.bg_vor);
			break;
		default:
			break;
		}
	}
	
	public void getArrayFormHome(int posi){
		switch (posi) {
		case 1:
			//Ausgchen
			for(int i =0; i < this.mHelper.audiosList.size();i++){
				for(int j = 0 ; j < ausgchen.length;j++){
					if(this.mHelper.audiosList.get(i).getmID().equalsIgnoreCase(ausgchen[j])){
						audiosList.add(this.mHelper.audiosList.get(i));
					}
				}
			}
			
			break;
		case 2:
			for(int i =0; i < this.mHelper.audiosList.size();i++){
				for(int j = 0 ; j < auflonsen.length;j++){
					if(this.mHelper.audiosList.get(i).getmID().equalsIgnoreCase(auflonsen[j])){
						audiosList.add(this.mHelper.audiosList.get(i));
					}
				}
			}
			
			break;
		case 3:
			for(int i =0; i < this.mHelper.audiosList.size();i++){
				for(int j = 0 ; j < unterstutzen.length;j++){
					if(this.mHelper.audiosList.get(i).getmID().equalsIgnoreCase(unterstutzen[j])){
						audiosList.add(this.mHelper.audiosList.get(i));
					}
				}
			}
			
			break;
		case 4:
			for(int i =0; i < this.mHelper.audiosList.size();i++){
				for(int j = 0 ; j < verbessern.length;j++){
					if(this.mHelper.audiosList.get(i).getmID().equalsIgnoreCase(verbessern[j])){
						audiosList.add(this.mHelper.audiosList.get(i));
					}
				}
			}
			break;
		case 5:
			for(int i =0; i < this.mHelper.audiosList.size();i++){
				for(int j = 0 ; j < vorbereiten.length;j++){
					if(this.mHelper.audiosList.get(i).getmID().equalsIgnoreCase(vorbereiten[j])){
						audiosList.add(this.mHelper.audiosList.get(i));
					}
				}
			}
			
			break;

		default:
			break;
		}
	}

	public void initUI() {

		this.wrapper = new FrameLayout(this.getApplicationContext());
		FrameLayout.LayoutParams para = new FrameLayout.LayoutParams(
				FrameLayout.LayoutParams.MATCH_PARENT,
				FrameLayout.LayoutParams.MATCH_PARENT);
		this.wrapper.setLayoutParams(para);
		this.wrapper.setBackgroundColor(Color.BLACK);
		
		frist = new View(getApplicationContext());
		FrameLayout.LayoutParams paraBg = new FrameLayout.LayoutParams(
				FrameLayout.LayoutParams.MATCH_PARENT,
				FrameLayout.LayoutParams.MATCH_PARENT);
		setbackgroungHome(Integer.parseInt(getIntent().getExtras().getString("Audios")), frist);
		frist.setLayoutParams(paraBg);
		frist.setVisibility(View.GONE);
		this.wrapper.addView(this.initUIBgView);
		this.wrapper.addView(frist);
		this.wrapper.addView(this.initUIListView);
		this.wrapper.addView(this.initUIConnection);
		
		this.wrapper.addView(this.initUIDown);
		this.wrapper.addView(this.initUIBottom);
		this.wrapper.addView(this.initUIHeader);
		setContentView(this.wrapper);
		audiosList = new ArrayList<Audios>();
	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case Key.btn_deine_musik:
			Intent intent = new Intent(getApplicationContext(),Deine_Titel.class);
			clearMemory();
			intent.putExtra("Audios", getIntent().getExtras().getString("Audios"));
			startActivity(intent);
			//finish();
			overridePendingTransition(R.anim.silde_up_in, R.anim.slide_up_out);
			
			break;
		case Key.AUDIOS_NAME:
			//onBackPressed();
			runOnUiThread(new Runnable() {
				
				@Override
				public void run() {
					// TODO Auto-generated method stub
					plus.animate().rotationYBy(0).rotationY(90).setDuration(250).setListener(new AnimatorListener() {
						
						@Override
						public void onAnimationStart(Animator animation) {
							// TODO Auto-generated method stub
							
						}
						
						@Override
						public void onAnimationRepeat(Animator animation) {
							// TODO Auto-generated method stub
							
						}
						
						@Override
						public void onAnimationEnd(Animator animation) {
							// TODO Auto-generated method stub
							
							
							plus.setBackgroundResource(R.drawable.plus);
							plus.animate().rotationYBy(90).rotationY(180).setDuration(250).setListener(new AnimatorListener() {
								
								@Override
								public void onAnimationStart(Animator animation) {
									// TODO Auto-generated method stub
									
								}
								
								@Override
								public void onAnimationRepeat(Animator animation) {
									// TODO Auto-generated method stub
									
								}
								
								@Override
								public void onAnimationEnd(Animator animation) {
									// TODO Auto-generated method stub
									plus.clearAnimation();
									runOnUiThread(new Runnable() {
										
										@Override
										public void run() {
											// TODO Auto-generated method stub
											
											initUIListView.clearAnimation();
											initUIListView.animate().setInterpolator(new LinearInterpolator()).translationYBy(0).translationY(0-mHelper.getAppHeight()).setDuration(800).setListener(new AnimatorListener() {
												
												@Override
												public void onAnimationStart(Animator animation) {
													// TODO Auto-generated method stub
													
												}
												
												@Override
												public void onAnimationRepeat(Animator animation) {
													// TODO Auto-generated method stub
													
												}
												
												@Override
												public void onAnimationEnd(Animator animation) {
													// TODO Auto-generated method stub
													/*runOnUiThread(new Runnable() {	
														@Override
														public void run() {
															// TODO Auto-generated method stub
															initUIBgView.clearAnimation();
															initUIBgView.animate().setInterpolator(new LinearInterpolator()).translationYBy(0).translationY(mHelper.getAppHeight()).setDuration(800).start();
															
														}
													});*/
													runOnUiThread(new Runnable() {
														
														@Override
														public void run() {
															// TODO Auto-generated method stub
															frist.clearAnimation();
															frist.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.auswahl_down));
														
														}
													});
													frist.setVisibility(View.VISIBLE);
													initUIBottom.setVisibility(View.GONE);
													initUIDown.setVisibility(View.GONE);
													initUIHeader.animate().setStartDelay(20).setInterpolator(new LinearInterpolator()).translationYBy(0).translationY(mAudios.animUpPlus).setDuration(800).setListener(new AnimatorListener() {
														
														@Override
														public void onAnimationStart(Animator animation) {
															// TODO Auto-generated method stub
															initUIHeader.setBackgroundColor(Color.TRANSPARENT);
														}
														
														@Override
														public void onAnimationRepeat(Animator animation) {
															// TODO Auto-generated method stub
															
														}
														
														@Override
														public void onAnimationEnd(Animator animation) {
															// TODO Auto-generated method stub
															Intent intent3 = new Intent(getApplicationContext(), Home.class);
															clearMemory();
															intent3.putExtra("HomeBG", getIntent().getExtras().getString("Audios"));
															intent3.putExtra("Home", "Home");
														//	intent3.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
															startActivity(intent3);
															finish();
															overridePendingTransition(R.anim.alpha_in,R.anim.alpha_in);
															
														}
														
														@Override
														public void onAnimationCancel(Animator animation) {
															// TODO Auto-generated method stub
															
														}
													}).start();
												}
												
												@Override
												public void onAnimationCancel(Animator animation) {
													// TODO Auto-generated method stub
													
												}
											}).start();
											
										}
									});
									
										
								}
								
								@Override
								public void onAnimationCancel(Animator animation) {
									// TODO Auto-generated method stub
									
								}
							}).start();
						}
						
						@Override
						public void onAnimationCancel(Animator animation) {
							// TODO Auto-generated method stub
							
						}
					}).start();
				}
			});
			
			
			
			break;
		default:
			break;
		}
	}
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
mConnection = new ServiceConnection() {
			
			@Override
			public void onServiceDisconnected(ComponentName name) {
				// TODO Auto-generated method stub
				mService =null;
			}
			
			@Override
			public void onServiceConnected(ComponentName name, IBinder service) {
				// TODO Auto-generated method stub
				mService = IInAppBillingService.Stub.asInterface(service);
				  Log.i("LTH", "purchaseData");
					 try {
						 arrayActiveProduct = new ArrayList<String>();
						Bundle ownedItems = mService.getPurchases(3, getPackageName(), "inapp", null);
						int response = ownedItems.getInt("RESPONSE_CODE");
						
						if (response == 0) {
							
						   ArrayList<String> ownedSkus =
						      ownedItems.getStringArrayList("INAPP_PURCHASE_ITEM_LIST");
						   ArrayList<String>  purchaseDataList =
						      ownedItems.getStringArrayList("INAPP_PURCHASE_DATA_LIST");
						   ArrayList<String>  signatureList =
						      ownedItems.getStringArrayList("INAPP_DATA_SIGNATURE_LIST");
						   String continuationToken = 
						      ownedItems.getString("INAPP_CONTINUATION_TOKEN");
						   
						   for (int i = 0; i < purchaseDataList.size(); ++i) {
						      String purchaseData = purchaseDataList.get(i);
						      String signature = signatureList.get(i);
						      String sku = ownedSkus.get(i);
						      JSONObject jo = new JSONObject(purchaseData);
					            String productId = jo.getString("productId");
					           arrayActiveProduct.add(productId);
						      
						   } 

						
						}
						pos = Integer.parseInt(getIntent().getExtras().getString("Audios"));
						getArrayFormHome(pos);
						libraries = (ListView) findViewById(Key.LISTVIEW_LIBRARY);
						
						
						int height = (int) (mHelper.getAppHeight()-(UI_Audios.header_height*2))/3;
						
						adapter = new Audios_Adapter(R.layout.items, Audios_Library.this, getArray(),height);
						libraries.setAdapter(adapter);
						//libraries.setScrollBarSize(0);
						libraries.setScrollbarFadingEnabled(true);
						libraries.setHorizontalScrollBarEnabled(false);
						libraries.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
						libraries.setVerticalScrollBarEnabled(false);
						libraries.setHorizontalScrollBarEnabled(false);
						libraries.setOnScrollListener(Audios_Library.this);
						int color = Color.parseColor("#e8d3a0");
						ColorDrawable drawable = new ColorDrawable(color);
						drawable.setAlpha(100);
						libraries.setDivider(drawable);
						libraries.setDividerHeight(1);
						
						deine = findViewById(Key.btn_deine_musik);
						audio = (FrameLayout) findViewById(Key.AUDIOS_NAME);
						handler.sendEmptyMessageDelayed(2, 2000);
						plus = findViewById(Key.HEADER);	
						deine.setEnabled(false);
						audio.setEnabled(false);
						libraries.setEnabled(false);
						textView = (TextView) findViewById(Key.AUSWAHL);
						deine.setOnClickListener(Audios_Library.this);
						audio.setOnClickListener(Audios_Library.this);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			}
		};
		
		 bindService(new 
			        Intent("com.android.vending.billing.InAppBillingService.BIND"),
			        mConnection, Context.BIND_AUTO_CREATE);
		IntentFilter mNetworkStateIntentFilter = new IntentFilter();
		mNetworkStateIntentFilter
				.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
		registerReceiver(mNetworkStateIntentReceiver, mNetworkStateIntentFilter);
		
		mThis=this;
		
	}
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		mThis=null;
	}
	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		unregisterReceiver(mNetworkStateIntentReceiver);
		
	}
	public void clearMemory(){
		deine.setOnClickListener(null);
		audio.setOnClickListener(null);
	
	}
	
	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
		Intent intent3 = new Intent(getApplicationContext(), Home.class);
		clearMemory();
		intent3.putExtra("HomeBG", getIntent().getExtras().getString("Audios"));
		intent3.putExtra("Home", "Home");
		startActivity(intent3);
		finish();
		overridePendingTransition(R.anim.alpha_in, R.anim.alpha_out);
		
	}

	
	@Override
	public void onScrollStateChanged(AbsListView view, int scrollState) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onScroll(AbsListView view, int firstVisibleItem,
			int visibleItemCount, int totalItemCount) {
		// TODO Auto-generated method stub
		this.initUIDown.setVisibility(View.VISIBLE);
		if((firstVisibleItem+visibleItemCount)==totalItemCount){
			this.initUIDown.setVisibility(View.GONE);
		}
		
	}
	
	Handler handler = new Handler(){

		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			super.handleMessage(msg);
			switch (msg.what) {
			case 1:
				initUIHeader.setBackgroundColor(Color.TRANSPARENT);
				break;
			case 2:
				deine.setEnabled(true);
				audio.setEnabled(true);
				libraries.setEnabled(true);
				plus.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.home_plus_flip));
				break;
			default:
				break;
			}
		}
		
	};
	
	Handler handler2 = new Handler(){

		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			super.handleMessage(msg);
			try{
				
				ArrayList<String> skuList = new ArrayList<String> ();
				skuList.add(audiosList.get(msg.what).getmID());
				Log.i("LTH", audiosList.get(msg.what).getmID().trim());
				Bundle querySkus = new Bundle();
				querySkus.putStringArrayList("ITEM_ID_LIST", skuList);
				
				Bundle skuDetails = mService.getSkuDetails(3, 
						   getPackageName(), "inapp", querySkus);
				
				int response = skuDetails.getInt("RESPONSE_CODE");
				
				if (response == 0) {
				   ArrayList<String> responseList
				      = skuDetails.getStringArrayList("DETAILS_LIST");
				  Log.i("LTH", "responseList");
				   for (String thisResponse : responseList) {
					  
				      JSONObject object = new JSONObject(thisResponse);
				      String sku = object.getString("productId");
				      String price = object.getString("price");
				      if (sku.equals(audiosList.get(msg.what).getmID().trim())) {
				    	  Bundle buyIntentBundle = mService.getBuyIntent(3, getPackageName(),
				    			   sku, "inapp", "bGoa+V7g/yqDXvKRqq+JTFn4uQZbPiQJo4pf9RzJ");
				    	  PendingIntent pendingIntent = buyIntentBundle.getParcelable("BUY_INTENT");
				    	  startIntentSenderForResult(pendingIntent.getIntentSender(),
				    			   1001, new Intent(), Integer.valueOf(0), Integer.valueOf(0),
				    			   Integer.valueOf(0));
				    	  Toast.makeText(getApplicationContext(), price, Toast.LENGTH_LONG).show();
				      }
				      }
				}
			}catch(Exception ex){
				ex.printStackTrace();
			}
		}
		
	};
	public Handler getHandler(){
		return handler2;
	}
	
	
	public class AuswahlAnimation extends AsyncTask<Integer, Integer, Void>{

		View viewAnimation;
		Animation anim;
		int animRes;
		
		
		public AuswahlAnimation(View viewAnimation, int animRes) {
			super();
			this.viewAnimation = viewAnimation;
			this.animRes = animRes;
			anim = AnimationUtils.loadAnimation(getApplicationContext(),
					animRes);
		}

		@Override
		protected Void doInBackground(final Integer... params) {
			// TODO Auto-generated method stub
			runOnUiThread(new Runnable() {
				
				@Override
				public void run() {
					// TODO Auto-generated method stub
					viewAnimation.clearAnimation();
					viewAnimation.setAnimation(anim);
					viewAnimation.startAnimation(anim);	
					handler.sendEmptyMessageDelayed(animRes, (anim.getDuration()-params[0]));
				}
			});
			return null;
		}				
	}
	
	BroadcastReceiver mNetworkStateIntentReceiver = new BroadcastReceiver() {

		@Override
		public void onReceive(Context context, Intent intent) {
			ConnectivityManager cm = ((ConnectivityManager) context
					.getSystemService(Context.CONNECTIVITY_SERVICE));
			
				if(cm.getActiveNetworkInfo()!=null){
					
					initUIListView.setVisibility(View.GONE);
				//	initUIConnection.setVisibility(View.GONE);
			//		new AuswahlAnimation(initUIListView, R.anim.auswahl_down).execute(0);
			//		new AuswahlAnimation(initUIConnection, R.anim.auswahl_up).execute(0);
					runOnUiThread(new Runnable() {
						
						@Override
						public void run() {
							// TODO Auto-generated method stub
							Animation up = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.auswahl_up);
							up.setAnimationListener(new AnimationListener() {
								
								@Override
								public void onAnimationStart(Animation animation) {
									// TODO Auto-generated method stub
									
								}
								
								@Override
								public void onAnimationRepeat(Animation animation) {
									// TODO Auto-generated method stub
									
								}
								
								@Override
								public void onAnimationEnd(Animation animation) {
									// TODO Auto-generated method stub
									initUIListView.setVisibility(View.VISIBLE);
									initUIConnection.setVisibility(View.GONE);
									initUIListView.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.auswahl_down));
								}
							});
							initUIConnection.startAnimation(up);
							
						}
					});
				}else{
					initUIListView.setVisibility(View.GONE);
					//initUIListView.setEnabled(false);
					initUIConnection.setVisibility(View.VISIBLE);
					new AuswahlAnimation(initUIConnection, R.anim.auswahl_down).execute(0);
					new AuswahlAnimation(initUIListView, R.anim.auswahl_up).execute(0);
					runOnUiThread(new Runnable() {
						
						@Override
						public void run() {
							Animation up = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.auswahl_up);
							up.setAnimationListener(new AnimationListener() {
								
								@Override
								public void onAnimationStart(Animation animation) {
									// TODO Auto-generated method stub
									
								}
								
								@Override
								public void onAnimationRepeat(Animation animation) {
									// TODO Auto-generated method stub
									
								}
								
								@Override
								public void onAnimationEnd(Animation animation) {
									// TODO Auto-generated method stub
								
									initUIConnection.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.auswahl_down));
								}
							});
							initUIListView.startAnimation(up);
						}
					});
				
				

			} 
		}
	};

	
	@Override
	public void onDestroy() {
	    super.onDestroy();
	    try{
	    if (mService != null) {
	        unbindService(mConnection);
	    }}catch(Exception ex){
	    	ex.printStackTrace();
	    }
	}
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) { 
	   if (requestCode == 1001) {           
	      int responseCode = data.getIntExtra("RESPONSE_CODE", 0);
	      String purchaseData = data.getStringExtra("INAPP_PURCHASE_DATA");
	      String dataSignature = data.getStringExtra("INAPP_DATA_SIGNATURE");
	        
	      if (resultCode == RESULT_OK) {
	         try {
	            JSONObject jo = new JSONObject(purchaseData);
	            String productId = jo.getString("productId");
	            String purchaseTime = jo.getString("purchaseTime");
	            String developerPayload = jo.getString("developerPayload");
	            String purchaseToken = jo.getString("purchaseToken");
	            Toast.makeText(getApplicationContext(), productId, Toast.LENGTH_LONG).show();
	            Toast.makeText(getApplicationContext(), purchaseTime, Toast.LENGTH_LONG).show();
	            Toast.makeText(getApplicationContext(), developerPayload, Toast.LENGTH_LONG).show();
	            Toast.makeText(getApplicationContext(), purchaseToken, Toast.LENGTH_LONG).show();
	            for(int i = 0; i < getArray().size();i++){
	            	if(productId.equalsIgnoreCase(getArray().get(i).getmID())){
	            		getArray().get(i).setmPrice("Gratis");
	            	}
	            }
	            adapter.notifyDataSetChanged();
	          }
	          catch (JSONException e) {
	             alert("Failed to parse purchase data.");
	             e.printStackTrace();
	          }
	      }
	   }
	}
	public void alert(String alert){
		Toast.makeText(getApplicationContext(), alert, Toast.LENGTH_SHORT).show();
	}

}
