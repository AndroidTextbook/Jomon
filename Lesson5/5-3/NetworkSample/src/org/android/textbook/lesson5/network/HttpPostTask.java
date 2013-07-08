package org.android.textbook.lesson5.network;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import android.os.AsyncTask;
import android.widget.TextView;

public class HttpPostTask extends AsyncTask<Void, Void, Boolean> {

    private TextView mTextView;

    private String mUrl;

    public HttpPostTask(String url, TextView view) {
        mTextView = view;
        mUrl = url;
    }

    @Override
    protected void onPreExecute() {
        mTextView.setText("Post中");
        super.onPreExecute();
    }


    @Override
    protected Boolean doInBackground(Void... param) {
        try {
            HttpPost httppost = new HttpPost(mUrl);
            DefaultHttpClient client = new DefaultHttpClient();

            // リクエストパラメータの設定
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("value", "value"));
            params.add(new BasicNameValuePair("key", "key"));

            // POST データの設定
            httppost.setEntity(new UrlEncodedFormEntity(params));

            HttpResponse response = client.execute(httppost);
            int status = response.getStatusLine().getStatusCode();

            if (status != HttpStatus.SC_OK) {
                return false;
            }
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    @Override
    protected void onPostExecute(Boolean result) {

        if (result) {
            mTextView.setText("Post 完了");
        } else {
            mTextView.setText("Post 失敗");
        }
        mTextView = null;
        super.onPostExecute(result);
    }
}
