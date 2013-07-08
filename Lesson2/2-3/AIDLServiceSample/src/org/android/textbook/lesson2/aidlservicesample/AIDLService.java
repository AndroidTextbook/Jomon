
package org.android.textbook.lesson2.aidlservicesample;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import android.widget.Toast;

public class AIDLService extends Service {
    private static final String LOGTAG = "AIDLService";
    static int cnt = 0;

    private ISampleInterface.Stub mBinder = new ISampleInterface.Stub() {

        @Override
        public void doSomething(int i) throws RemoteException {
            Toast.makeText(AIDLService.this, String.valueOf(i),
                    Toast.LENGTH_SHORT).show();
        }

        @Override
        public void setString(String s) throws RemoteException {
            // No Operation
        }
    };

    @Override
    public IBinder onBind(Intent intent) {
        Log.i(LOGTAG, "onBind");
        return mBinder;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.i(LOGTAG, "onUnbind");
        return super.onUnbind(intent);
    }

    @Override
    public void onCreate() {
        Log.i(LOGTAG, "onCreate");
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (intent == null) {
            Log.i(LOGTAG, "onStartCommand out@" + (cnt++) + "null");
        } else {
            Log.i(LOGTAG, "onStartCommand out@" + (cnt++) + "not null");
        }

        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        Log.i(LOGTAG, "onDestroy");
        super.onDestroy();
    }

}
