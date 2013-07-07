
package org.android.textbook.lesson5.bundle;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;

public class BundleSampleActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bundle_sample);

        Intent intent = new Intent();
        intent.setClass(this, IntentLaunchActivity.class);

        // 起動するAcitivityに渡したい値をセットする
        intent.putExtra("string_key", "value");
        intent.putExtra("boolean_key", true);

        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.bundle_sample, menu);
        return true;
    }

}
