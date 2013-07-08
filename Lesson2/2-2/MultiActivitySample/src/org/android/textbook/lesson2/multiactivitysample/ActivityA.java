
package org.android.textbook.lesson2.multiactivitysample;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class ActivityA extends Activity {
    private static final String LOGTAG = "MultiActivitySample";
    private static final int REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_a);

        ((Button) findViewById(R.id.button1)).setOnClickListener(startActivityB);
        ((Button) findViewById(R.id.button2)).setOnClickListener(startActivityBWithData);
        ((Button) findViewById(R.id.button3)).setOnClickListener(startActivityBForResult);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity, menu);
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case REQUEST_CODE:
                switch (resultCode) {
                    case RESULT_OK:
                        // �������̏���
                        Log.i(LOGTAG, "RESULT_OK");
                        break;
                    case RESULT_CANCELED:
                        // �L�����Z���̏���
                        Log.i(LOGTAG, "RESULT_CANCELED");
                        break;
                    case RESULT_FIRST_USER:
                        // ���[�U�[�w��̏���1
                        Log.i(LOGTAG, "RESULT_���[�U�[�w��P");
                        break;
                    case RESULT_FIRST_USER + 1:
                        // ���[�U�[�w��̏���2
                        Log.i(LOGTAG, "RESULT_���[�U�[�w��Q");
                    break;
                }
                break;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    // ActivityB���N������Listener
    OnClickListener startActivityB = new OnClickListener() {

        @Override
        public void onClick(View v) {
            Intent intent = new Intent();
            intent.setClass(getApplicationContext(),
                    org.android.textbook.lesson2.multiactivitysample.
                    ActivityB.class);
            startActivity(intent);
        }

    };

    // Data�Ƌ���ActivityB���N������Listener
    OnClickListener startActivityBWithData = new OnClickListener() {

        @Override
        public void onClick(View v) {
            String sendData = "value";
            Intent intent = new Intent();
            intent.setClass(getApplicationContext(),
                    org.android.textbook.lesson2.multiactivitysample.
                    ActivityB.class);
            intent.putExtra("SEND_TEXT_KEY", sendData);
            startActivity(intent);
        }
    };

    // ActivityB����f�[�^���󂯎�邽�߂�Listener
    OnClickListener startActivityBForResult = new OnClickListener() {

        @Override
        public void onClick(View v) {
            Intent intent = new Intent();
            intent.setClass(getApplicationContext(),
                    org.android.textbook.lesson2.multiactivitysample.
                    ActivityB.class);
            startActivityForResult(intent, REQUEST_CODE);

        }
    };

}
