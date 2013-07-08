package org.android.textbook.lesson4.httpclientsample;

import java.util.List;

import org.android.textbook.lesson4.httpclientsample.RssLoadingAsyncTask.RssLoadingAsyncTaskListener;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.widget.ListView;

public class HttpClientSampleActivity extends Activity {

    private RssLoadingAsyncTaskListener mRssLoadingAsyncTaskListener = new RssLoadingAsyncTaskListener() {

        @Override
        public void onStartTask() {

        }

        @Override
        public void onEndTask(List<RssItem> items) {
            if (mRssItemAdaper == null) {
                mRssItemAdaper = new RssItemAdaper(
                        HttpClientSampleActivity.this, R.layout.list_row, items);
            }
            mListView.setAdapter(mRssItemAdaper);
        }

    };
    private ListView mListView;
    private RssItemAdaper mRssItemAdaper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_http_client_sample);

        mListView = (ListView) findViewById(R.id.listView);

        if (isConnected()) {
            RssLoadingAsyncTask task = new RssLoadingAsyncTask(
                    mRssLoadingAsyncTaskListener);
            task.execute();
        } else {

        }
    }

    private boolean isConnected() {
        boolean connected = false;
        ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        // ネットワークの接続状況を取得
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            // ネットワークと接続済み
            connected = true;
        }

        return connected;
    }

}
