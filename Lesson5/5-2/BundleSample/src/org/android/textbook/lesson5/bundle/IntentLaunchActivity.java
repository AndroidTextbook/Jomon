package org.android.textbook.lesson5.bundle;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.widget.TextView;

public class IntentLaunchActivity extends Activity {

	private boolean mBooleanValue;

	private String mStringValue;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_intent_launch);

		TextView textBoolean = (TextView) findViewById(R.id.text_boolean);
		TextView textString = (TextView) findViewById(R.id.text_string);

		Intent intent = getIntent();

		if (intent != null) {
			// インテントが持つBundleからデータを取り出す
			mBooleanValue = intent.getBooleanExtra("boolean_key", false);
			mStringValue = intent.getStringExtra("string_key");

			textBoolean.setText("Boolean: " + mBooleanValue);
			textString.setText("String: " + mStringValue);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.intent_launch, menu);
		return true;
	}

}
