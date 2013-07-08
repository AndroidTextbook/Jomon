
package org.android.textbook.lesson3.stringsample;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class StringSample extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_string_sample);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.string_sample, menu);
        return true;
    }

}
