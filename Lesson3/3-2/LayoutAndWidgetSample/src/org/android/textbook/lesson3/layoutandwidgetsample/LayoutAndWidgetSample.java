
package org.android.textbook.lesson3.layoutandwidgetsample;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class LayoutAndWidgetSample extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layout_and_widget_sample);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.layout_and_widget_sample, menu);
        return true;
    }

}
