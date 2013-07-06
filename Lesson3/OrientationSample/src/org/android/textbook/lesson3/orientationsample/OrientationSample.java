
package org.android.textbook.lesson3.orientationsample;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class OrientationSample extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orientation_sample);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.orientation_sample, menu);
        return true;
    }

}
