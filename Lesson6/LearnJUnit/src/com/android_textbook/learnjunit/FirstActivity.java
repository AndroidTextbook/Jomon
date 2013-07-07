
package com.android_textbook.learnjunit;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import com.android_textbook.learnjunit.R;

public class FirstActivity extends Activity implements OnClickListener {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);

        findViewById(R.id.goToNextButton).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.goToNextButton) {
            Intent intent = new Intent(this, SimpleTextActivity.class);
            intent.putExtra(SimpleTextActivity.EXTRA_TEXT, "Something");
            startActivity(intent);
        }
    }
}
