package org.android.textbook.lesson2.messengerservicesample;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.util.Log;
import android.widget.Toast;

public class MessengerService extends Service {
	static final int MSG_ACTIVITY_CONNECTED = 1;
	static final int MSG_ACTIVITY_DISCONNECTED = 2;
	static final int MSG_COMMAND = 3;
	
	
	final Messenger mService = new Messenger(new Handler(){

		@Override
		public void handleMessage(Message msg) {
			switch(msg.what){
			case MSG_ACTIVITY_CONNECTED:
			case MSG_ACTIVITY_DISCONNECTED:
			case MSG_COMMAND:
				showToast((String)msg.obj,Toast.LENGTH_SHORT);
				break;
			default:
				super.handleMessage(msg);
				break;
			}
		}
		
	});
	
	
	@Override
	public IBinder onBind(Intent intent) {
		return mService.getBinder();
	}
	
	private void showToast(String msg,int duration){
		Toast.makeText(this, msg, duration).show();
	}

}
