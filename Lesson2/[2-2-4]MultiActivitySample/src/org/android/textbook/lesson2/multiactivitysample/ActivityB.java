
package org.android.textbook.lesson2.multiactivitysample;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class ActivityB extends Activity {
    private final static String LOGTAG = "ActivityB";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_b);
        
        ((Button) findViewById(R.id.button1)).setOnClickListener(backActivityAForResult);

        Intent intent = getIntent();
        String data = (String) intent.getStringExtra("SEND_TEXT_KEY");
        Log.i(LOGTAG, "getData : " + data);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_b, menu);
        return true;
    }

    OnClickListener backActivityAForResult = new OnClickListener() {
        
        @Override
        public void onClick(View v) {
            Intent result = new Intent();
            setResult(RESULT_OK, result);
            finish();
        }
    };
    
}
