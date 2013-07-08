
package org.android.textbook.lesson3.themesample;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class ThemeSample extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_theme_sample);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.theme_sample, menu);
        return true;
    }

}
