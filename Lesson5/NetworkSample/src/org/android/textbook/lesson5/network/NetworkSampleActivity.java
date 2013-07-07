
package org.android.textbook.lesson5.network;

import android.app.Activity;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class NetworkSampleActivity extends Activity {

    private final static String POST_URL = "http://textbook-lesson5.appspot.com/textbook_lesson5";

    private TextView mStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_network_sample);

        mStatus = (TextView) findViewById(R.id.text_post_status);

        Button button = (Button) findViewById(R.id.button_post);
        button.setOnClickListener(mOnClickLisnter);
    }

    private OnClickListener mOnClickLisnter = new OnClickListener() {

        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.button_post:
                    post();
                    break;
                default:
                    break;
            }
        }
    };

    private boolean post() {
        if (checkNetworkStatus()) {
            HttpPostTask task = new HttpPostTask(POST_URL, mStatus);
            task.execute();
        } else {
            mStatus.setText("ネットワークに繋がっていません");
            return false;
        }
        return true;
    }

    private boolean checkNetworkStatus() {
        ConnectivityManager conectivityManager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = conectivityManager.getActiveNetworkInfo();
        if (networkInfo == null) {
            return false;
        }

        if (!networkInfo.isConnected()) {
            /* ネットワークに接続できる機器がない */
            return false;
        }

        return true;
    }

}
