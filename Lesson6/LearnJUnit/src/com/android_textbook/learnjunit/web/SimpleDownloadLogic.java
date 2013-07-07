
package com.android_textbook.learnjunit.web;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;

import android.util.Log;

public class SimpleDownloadLogic {
    public static class ConnectionFailedExecption extends Exception {
        private static final long serialVersionUID = 1L;

        public ConnectionFailedExecption() {
            super();
        }

        public ConnectionFailedExecption(String detailMessage, Throwable throwable) {
            super(detailMessage, throwable);
        }

        public ConnectionFailedExecption(String detailMessage) {
            super(detailMessage);
        }

        public ConnectionFailedExecption(Throwable throwable) {
            super(throwable);
        }
    }

    private HttpClient mHttpClient;

    public SimpleDownloadLogic(HttpClient httpClient) {
        super();
        this.mHttpClient = httpClient;
    }

    /**
     * 指定されたUriを開き、byte配列として取得します
     * 
     * @param uri 対象となるUri
     * @return 取得された文字列
     * @throws ConnectionFailedExecption 接続に失敗した際にスローされます
     * @throws IOException ステータスコードや通信中に失敗した際にスローされます
     */
    public String downloadUri(String uri) throws ConnectionFailedExecption, IOException {
        String result;
        HttpGet request = new HttpGet(uri);
        HttpResponse response;
        try {
            response = mHttpClient.execute(request);
        } catch (IOException e) {
            throw new ConnectionFailedExecption(e);
        }
        int statusCode = response.getStatusLine().getStatusCode();
        if (statusCode == 200) {
            // 正常
            HttpEntity entry = response.getEntity();
            result = readAsString(entry);
        } else {
            // ステータスコードがおかしい
            throw new IOException("Bad status code:" + statusCode);
        }
        return result;
    }

    /** 与えられたHttpEntityの中身をStringとして取得します。 */
    public static String readAsString(HttpEntity entry) throws IOException {
        String result;
        Reader in = null;
        try {
            in = new InputStreamReader(entry.getContent());
            StringBuilder sb = new StringBuilder();
            char[] buffer = new char[1024];
            int r;
            while ((r = in.read(buffer)) > 0) {
                sb.append(buffer, 0, r);
            }
            result = sb.toString();
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    // ありえないけど念の為
                    Log.w("SimpleDownloadLogic", e.getMessage(), e);
                }
            }
        }
        return result;
    }
}
