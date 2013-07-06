
package org.android.textbook.lesson2.broadcastreceiversample;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class BroadCastReceiverMainActivity extends Activity {
    public static final String SAMPLE_ACTION = "org.android.textbook.lesson2.broadcastreceiversample.SAMPLE_ACTION";
    
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_broad_cast_receiver_main);

        ((Button)findViewById(R.id.button1)).setOnClickListener(buttonListener);
        
        
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.broad_cast_receiver_main, menu);
        return true;
    }

    private OnClickListener buttonListener = new OnClickListener() {
        
        @Override
        public void onClick(View v) {
            Intent broadcast = new Intent();
            broadcast.setAction(SAMPLE_ACTION);
            sendBroadcast(broadcast);
        }
    };
        

    
    
}
