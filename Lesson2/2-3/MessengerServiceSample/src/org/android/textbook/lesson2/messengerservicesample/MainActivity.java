
package org.android.textbook.lesson2.messengerservicesample;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {
    Messenger mService = null;

    private ServiceConnection mConnection = new ServiceConnection() {

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mService = new Messenger(service);
            sendMessage(MessengerService.MSG_ACTIVITY_CONNECTED, "MainActivityÇ∆ê⁄ë±ÇµÇ‹ÇµÇΩ");

        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            mService = null;
        }

    };

    private void sendMessage(int what, Object obj) {
        if (mService != null) {
            try {
                Message msg = Message.obtain();
                msg.what = what;
                msg.obj = obj;
                mService.send(msg);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ((Button) findViewById(R.id.button1)).setOnClickListener(startListener);
        ((Button) findViewById(R.id.button2)).setOnClickListener(eventListener);
        ((Button) findViewById(R.id.button3)).setOnClickListener(stopListener);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    private OnClickListener startListener = new OnClickListener() {

        @Override
        public void onClick(View v) {
            Intent bindIntent = new Intent(MainActivity.this, MessengerService.class);
            bindService(bindIntent, mConnection, Context.BIND_AUTO_CREATE);
        }

    };

    private OnClickListener stopListener = new OnClickListener() {

        @Override
        public void onClick(View v) {
            sendMessage(MessengerService.MSG_ACTIVITY_DISCONNECTED, "dissconnected from user");
            unbindService(mConnection);
        }

    };

    private OnClickListener eventListener = new OnClickListener() {

        @Override
        public void onClick(View v) {
            sendMessage(MessengerService.MSG_COMMAND, "send message from activity");
        }

    };

}
