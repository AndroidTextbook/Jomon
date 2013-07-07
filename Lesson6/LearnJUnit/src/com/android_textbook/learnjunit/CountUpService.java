
package com.android_textbook.learnjunit;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;

public class CountUpService extends Service {
    private int mCount = 0;

    private IBinder mBinder = new ICountUpService.Stub() {
        @Override
        public synchronized int getCount() throws RemoteException {
            return ++mCount;
        }
    };

    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }
}
