
package org.android.textbook.lesson2.localservicesample;

import org.android.textbook.lesson2.localservicesample.LocalService.LocalBinder;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {
    // �T�[�r�X�̃C���X�^���X�ێ��p
    private LocalService mService = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ((Button) findViewById(R.id.button1)).setOnClickListener(startServiceListener);
        ((Button) findViewById(R.id.button2)).setOnClickListener(callServiceMethodListener);
        ((Button) findViewById(R.id.button3)).setOnClickListener(stopServiceListener);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    // ServiceConnection�̎���
    private ServiceConnection mServiceConnection = new ServiceConnection() {

        // �T�[�r�X�̈Ӑ}���Ȃ��ؒf���ɌĂяo�����
        @Override
        public void onServiceDisconnected(ComponentName name) {
            mService = null;
        }

        // �T�[�r�X�Ƃ̐ڑ����ɌĂяo�����
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mService = ((LocalBinder) service).getService();
        }
    };

    // �T�[�r�X�N���{�^���������̏���
    OnClickListener startServiceListener = new OnClickListener() {

        @Override
        public void onClick(View v) {
            Intent startService = new Intent();
            startService.setClassName(
                    "org.android.textbook.lesson2.localservicesample",
                    "org.android.textbook.lesson2.localservicesample.LocalService");
            bindService(startService, mServiceConnection, 
                    Context.BIND_AUTO_CREATE);
        }
    };

    // �T�[�r�X��~�{�^���������̏���
    OnClickListener stopServiceListener = new OnClickListener() {

        @Override
        public void onClick(View v) {
            unbindService(mServiceConnection);
        }
    };

    // �T�[�r�X�̃��\�b�h���Ăяo���{�^���������̏���
    OnClickListener callServiceMethodListener = new OnClickListener() {

        @Override
        public void onClick(View v) {
            if (mService != null) {
                mService.showToast("Activity����̃��b�Z�[�W");
            }
        }
    };

}
