
package org.android.textbook.lesson4.httpclientsample;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.params.HttpConnectionParams;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import android.net.http.AndroidHttpClient;
import android.os.AsyncTask;
import android.util.Xml;

public class RssLoadingAsyncTask extends AsyncTask<String, Void, List<RssItem>> {
    RssLoadingAsyncTaskListener mRssLoadingAsyncTaskListener;

    public interface RssLoadingAsyncTaskListener {
        public void onStartTask();

        public void onEndTask(List<RssItem> item);
    }
    
    public RssLoadingAsyncTask(RssLoadingAsyncTaskListener listener){
        mRssLoadingAsyncTaskListener = listener;
    }
    
    @Override
    protected void onPreExecute() {
        mRssLoadingAsyncTaskListener.onStartTask();
    }

    @Override
    protected List<RssItem> doInBackground(String... urls) {

        List<RssItem> rssItemList = null;
        if (isCancelled()) {
            // キャンセルされた場合は終了
        } else {

        }

        String url = "http://www.sbcr.jp/topics/atom.xml";

        rssItemList = getRssItems(url);

        return rssItemList;
    }
    
    @Override
    protected void onPostExecute(List<RssItem> result) {
        mRssLoadingAsyncTaskListener.onEndTask(result);
    }

    private List<RssItem> getRssItems(String url) {
        List<RssItem> items = null;
        InputStream inputStream;

        // HTTPクライアントのインスタンス取得
        AndroidHttpClient httpClinet =
                AndroidHttpClient.newInstance("Android");
        // パラメータの設定
        HttpConnectionParams.setConnectionTimeout(
                httpClinet.getParams(), 10000);
        HttpConnectionParams.setSoTimeout(httpClinet.getParams(), 10000);

        // HttpGetのリクエスト生成
        HttpGet request = new HttpGet(url);
        try {
            HttpResponse response;

            // HttpGetリクエストの実行
            response = httpClinet.execute(request);

            // レスポンスの取得
            if (response.getStatusLine().getStatusCode()
                    == HttpStatus.SC_OK) {
                // コンテンツの取得
                final HttpEntity entity = response.getEntity();
                inputStream = entity.getContent();
                if (inputStream != null) {
                    items = parseXmlContent(inputStream);
                    inputStream.close();
                }
            } else {
                // HttpStatusがエラーで返ってきた
            }

            // 切断
            httpClinet.close();

        } catch (IOException e) {
            // HttpGetの失敗
            e.printStackTrace();
        }

        return items;

    }

    private List<RssItem> parseXmlContent(InputStream inputStream) {
        List<RssItem> rssItemList = new ArrayList<RssItem>();
        XmlPullParser parser = null;
        try {
            // XmlPullParserのインスタンスを取得
            parser = Xml.newPullParser();
            // parser = XmlPullParserFactory.newInstance().newPullParser();
            parser.setInput(inputStream, "UTF-8");

            // 解析する
            RssItem item = null;
            int eventType = parser.getEventType();
            while (eventType != XmlPullParser.END_DOCUMENT) {
                switch (eventType) {
                    case XmlPullParser.START_DOCUMENT:
                        break;
                    case XmlPullParser.START_TAG:
                        // <title>タグを発見
                        if (parser.getName().equals("title")) {
                            item = new RssItem(); // テキストを取得
                            item.setTitle(parser.nextText());
                            rssItemList.add(item);
                            android.util.Log.e("", "" + item.getTitle());
                        }
                        break;
                    case XmlPullParser.END_TAG:
                        break;
                    case XmlPullParser.TEXT:
                        break;
                    default:
                        break;
                }

                // 次のイベントを取得
                eventType = parser.next();
            }

        } catch (XmlPullParserException e) {
            // ファイルフォーマットの不正など
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return rssItemList;
    }

}
