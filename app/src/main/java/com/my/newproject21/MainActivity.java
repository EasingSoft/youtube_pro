package com.my.newproject21;

import android.app.Activity;
import android.app.*;
import android.os.*;
import android.view.*;
import android.view.View.*;
import android.widget.*;
import android.content.*;
import android.content.res.*;
import android.graphics.*;
import android.graphics.drawable.*;
import android.media.*;
import android.net.*;
import android.text.*;
import android.text.style.*;
import android.util.*;
import android.webkit.*;
import android.animation.*;
import android.view.animation.*;
import java.util.*;
import java.util.regex.*;
import java.text.*;
import org.json.*;
import android.webkit.WebView;
import android.webkit.WebSettings;
import android.webkit.WebViewClient;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.DialogFragment;


public class MainActivity extends  Activity { 
	
	
	private double dopip = 0;
	
	private WebView web;
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.main);
		initialize(_savedInstanceState);
		initializeLogic();
	}
	
	private void initialize(Bundle _savedInstanceState) {
		
		web = (WebView) findViewById(R.id.web);
		web.getSettings().setJavaScriptEnabled(true);
		web.getSettings().setSupportZoom(true);
		
		web.setWebViewClient(new WebViewClient() {
			@Override
			public void onPageStarted(WebView _param1, String _param2, Bitmap _param3) {
				final String _url = _param2;
				
				super.onPageStarted(_param1, _param2, _param3);
			}
			
			@Override
			public void onPageFinished(WebView _param1, String _param2) {
				final String _url = _param2;
				web.loadUrl("javascript:(function(){"+"setInterval( () => {"+"Android.stopvid(document.getElementsByClassName('player-control-play-pause-icon')[0].getAttribute('aria-pressed'));"+"},50);})()");
				super.onPageFinished(_param1, _param2);
			}
		});
	}
	
	private void initializeLogic() {
		web.loadUrl("https://youtube.com");
		web.addJavascriptInterface(new WebAppInterface(this), "Android");
		web.setWebChromeClient(new CustomWebClient());
	}
	
	@Override
	protected void onActivityResult(int _requestCode, int _resultCode, Intent _data) {
		
		super.onActivityResult(_requestCode, _resultCode, _data);
		
		switch (_requestCode) {
			
			default:
			break;
		}
	}
	
	@Override
	public void onBackPressed() {
		if (web.canGoBack()) {
			web.goBack();
		}
		else {
			finish();
		}
	}
	
	@Override
	public void onPause() {
		super.onPause();
		if (web.getUrl().contains("https://m.youtube.com/watch?v=") && (dopip == 0)) {
			if (android.os.Build.VERSION.SDK_INT >= 26) {
				
				                //Trigger PiP mode
				
				                try {
					
					                    Rational rational = new Rational(web.getWidth(), 405);
					
					
					
					
					
					                    PictureInPictureParams mParams =
					
					                            new PictureInPictureParams.Builder()
					
					                                    .setAspectRatio(rational)
					
					                                    .build();
					
					
					
					                    enterPictureInPictureMode(mParams);
					
					                } catch (IllegalStateException e) {
					
					                    e.printStackTrace();
					
					                }
				
				            } else {
				SketchwareUtil.showMessage(getApplicationContext(), "PIP not Supported");
				              
				            }
		}
		else {
			
		}
	}
	public void _pip () {
		 }
	@Override
	public void onPictureInPictureModeChanged (boolean isInPictureInPictureMode, Configuration newConfig) {
		    if (isInPictureInPictureMode) {
			        
					web.loadUrl("javascript:(function(){"+"setTimeout( () => {"+"document.getElementById('player-container-id').style.position='fixed';"+"document.getElementById('player-container-id').style.top='0'; "+"document.getElementsByClassName('mobile-topbar-header')[0].style.display='none'; "+"},50);})()");
			    } else {
			        web.loadUrl("javascript:(function(){"+"setTimeout( () => {"+"document.getElementById('player-container-id').style.position='fixed';"+"document.getElementById('player-container-id').style.top='48px'; "+"document.getElementsByClassName('mobile-topbar-header')[0].style.display='flex'; "+"},50);})()");
			    }
	}
	
	
	
	
	public class WebAppInterface {
		    Context mContext;
		    WebAppInterface(Context c) {
			        mContext = c;
			    }
		
		    @JavascriptInterface
		    public void stopvid(String tfk) {
					
			if (tfk.contains("false")) {
				dopip = 0;
				
			}
			else {
				dopip = 1;
			}
			  }
	}
	
	
	{
	}
	
	
	public void _fullscreen () {
	}
	
	
	public class CustomWebClient extends WebChromeClient {
		
		private View mCustomView;
		
		private WebChromeClient.CustomViewCallback mCustomViewCallback;
		
		protected FrameLayout frame;
		
		
		// Initially mOriginalOrientation is set to Landscape
		
		private int mOriginalOrientation = android.content.pm.ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE;
		
		private int mOriginalSystemUiVisibility;
		
		
		// Constructor for CustomWebClient
		
		public CustomWebClient() {}
		
		
		public Bitmap getDefaultVideoPoster() {
			
			if (MainActivity.this == null) {
				
				return null; }
			
			return BitmapFactory.decodeResource(MainActivity.this.getApplicationContext().getResources(), 2130837573); }
		
		
		public void onShowCustomView(View paramView, WebChromeClient.CustomViewCallback viewCallback) {
			
			if (this.mCustomView != null) {
				
				onHideCustomView();
				
				return; }
			
			this.mCustomView = paramView;
			
			this.mOriginalSystemUiVisibility = MainActivity.this.getWindow().getDecorView().getSystemUiVisibility();
			
			// When CustomView is shown screen orientation changes to mOriginalOrientation (Landscape).
			
			MainActivity.this.setRequestedOrientation(this.mOriginalOrientation);
			this.mOriginalOrientation = android.content.pm.ActivityInfo.SCREEN_ORIENTATION_PORTRAIT;this.mCustomViewCallback = viewCallback; ((FrameLayout)MainActivity.this.getWindow().getDecorView()).addView(this.mCustomView, new FrameLayout.LayoutParams(-1, -1)); MainActivity.this.getWindow().getDecorView().setSystemUiVisibility(3846);
			
		}
		
		
		public void onHideCustomView() {
			
			((FrameLayout)MainActivity.this.getWindow().getDecorView()).removeView(this.mCustomView);
			
			this.mCustomView = null;
			
			MainActivity.this.getWindow().getDecorView().setSystemUiVisibility(this.mOriginalSystemUiVisibility);
			
			// When CustomView is hidden, screen orientation is set to mOriginalOrientation (portrait).
			MainActivity.this.setRequestedOrientation(this.mOriginalOrientation);
			
			// After that mOriginalOrientation is set to landscape.
			
			this.mOriginalOrientation = android.content.pm.ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE; this.mCustomViewCallback.onCustomViewHidden();
			
			this.mCustomViewCallback = null;
			
		}
		
	}
	
	
	{
	}
	
	
	@Deprecated
	public void showMessage(String _s) {
		Toast.makeText(getApplicationContext(), _s, Toast.LENGTH_SHORT).show();
	}
	
	@Deprecated
	public int getLocationX(View _v) {
		int _location[] = new int[2];
		_v.getLocationInWindow(_location);
		return _location[0];
	}
	
	@Deprecated
	public int getLocationY(View _v) {
		int _location[] = new int[2];
		_v.getLocationInWindow(_location);
		return _location[1];
	}
	
	@Deprecated
	public int getRandom(int _min, int _max) {
		Random random = new Random();
		return random.nextInt(_max - _min + 1) + _min;
	}
	
	@Deprecated
	public ArrayList<Double> getCheckedItemPositionsToArray(ListView _list) {
		ArrayList<Double> _result = new ArrayList<Double>();
		SparseBooleanArray _arr = _list.getCheckedItemPositions();
		for (int _iIdx = 0; _iIdx < _arr.size(); _iIdx++) {
			if (_arr.valueAt(_iIdx))
			_result.add((double)_arr.keyAt(_iIdx));
		}
		return _result;
	}
	
	@Deprecated
	public float getDip(int _input){
		return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, _input, getResources().getDisplayMetrics());
	}
	
	@Deprecated
	public int getDisplayWidthPixels(){
		return getResources().getDisplayMetrics().widthPixels;
	}
	
	@Deprecated
	public int getDisplayHeightPixels(){
		return getResources().getDisplayMetrics().heightPixels;
	}
	
}