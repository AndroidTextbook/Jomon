package org.android.textbook.lesson2.intentservicesample;

import android.app.IntentService;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class IntentServiceSample extends IntentService {
    private static final String LOGTAG = "IntentServiceSample";
	static int cnt = 0;
	
	public IntentServiceSample(String name) {
		super(name);
	}

	public IntentServiceSample() {
		super("IntentServiceSample");
		Log.i(LOGTAG,"Constractor");

	}
	
	@Override
	protected void onHandleIntent(Intent intent) {
        Log.i(LOGTAG,"onHandleIntent - start");

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
	    
        Log.i(LOGTAG,"onHandleIntent - end");
	}

    @Override
    public IBinder onBind(Intent intent) {
        Log.i(LOGTAG,"onBind");
        return super.onBind(intent);
    }

    @Override
    public void onCreate() {
        Log.i(LOGTAG,"onCreate");
        super.onCreate();
    }

    @Override
    public void onDestroy() {
        Log.i(LOGTAG,"onDestroy");
        super.onDestroy();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i(LOGTAG,"onStartCommand");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void setIntentRedelivery(boolean enabled) {
        Log.i(LOGTAG,"setIntentRedelivery");
        super.setIntentRedelivery(enabled);
    }
	
	
	
}
