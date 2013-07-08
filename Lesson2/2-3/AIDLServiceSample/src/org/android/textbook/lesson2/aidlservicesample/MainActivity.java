package org.android.textbook.lesson2.aidlservicesample;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
public class MainActivity extends Activity implements OnClickListener {

	private Intent startIntent = null;
	private ISampleInterface mService = null;
	
	private ServiceConnection mServiceConnection = new ServiceConnection() {
		
		@Override
		public void onServiceDisconnected(ComponentName name) {
			Log.i("koba","onServiceDisconnected");
			mService = null;
		}
		
		@Override
		public void onServiceConnected(ComponentName name, IBinder service) {
			Log.i("koba","onServiceConnected");
			mService = ISampleInterface.Stub.asInterface(service);
		}
	};
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		((Button)findViewById(R.id.button1)).setOnClickListener(this);
		((Button)findViewById(R.id.button2)).setOnClickListener(this);
		((Button)findViewById(R.id.button3)).setOnClickListener(this);

		startIntent = new Intent();
		startIntent.setClass(this, AIDLService.class);
		startService(startIntent);
	
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void onClick(View v) {

		switch(v.getId()){
		case R.id.button1:
			Intent bindIntent = new Intent();
			bindIntent.setClass(this, AIDLService.class);

			bindService(bindIntent,mServiceConnection,Context.BIND_AUTO_CREATE);
			break;
		
		case R.id.button2:
			if(mService != null){
				try {
					mService.doSomething(123);
				} catch (RemoteException e) {
					e.printStackTrace();
				}				
			}
			break;
		case R.id.button3:
			unbindService(mServiceConnection);
			break;
		}
	}

	@Override
	protected void onPause() {
		super.onPause();
		stopService(startIntent);
	}	
	
	

}
