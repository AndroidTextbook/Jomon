
package org.android.textbook.lesson2.foregroundservicesample;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ((Button) findViewById(R.id.button1)).setOnClickListener(buttonListener);
        ((Button) findViewById(R.id.button2)).setOnClickListener(buttonListener);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    OnClickListener buttonListener = new OnClickListener() {

        @Override
        public void onClick(View v) {
            Intent intent = new Intent();
            intent.setClassName("org.android.textbook.lesson2.foregroundservicesample", "org.android.textbook.lesson2.foregroundservicesample.ForegroundService");

            switch (v.getId()) {
                case R.id.button1:
                    startService(intent);
                    break;
                case R.id.button2:
                    stopService(intent);
                    break;
            }

        }
    };

}
