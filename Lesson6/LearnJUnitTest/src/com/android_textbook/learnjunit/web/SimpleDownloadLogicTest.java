
package com.android_textbook.learnjunit.web;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import junit.framework.TestCase;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpUriRequest;

import com.android_textbook.learnjunit.web.SimpleDownloadLogic;
import com.android_textbook.learnjunit.web.SimpleDownloadLogic.ConnectionFailedExecption;

public class SimpleDownloadLogicTest extends TestCase {
    /** 成功すること確認 */
    public void testDownloadUri_success() throws Exception {
        final String testData = "13524";
        HttpClient httpClient;
        { // モックのHttpClientを作成する
            final StatusLine statusLine = new MockStatusLine() {
                @Override
                public int getStatusCode() {
                    // 成功を表すステータスコードを返す
                    return 200;
                }
            };
            final HttpEntity entity = new MockHttpEntity() {
                public java.io.InputStream getContent() throws IOException, IllegalStateException {
                    // 成功としてtestData変数の内容を返す
                    return new ByteArrayInputStream(testData.getBytes());
                };
            };
            final HttpResponse response = new MockHttpResponse() {
                public StatusLine getStatusLine() {
                    return statusLine;
                };

                @Override
                public HttpEntity getEntity() {
                    return entity;
                }
            };
            httpClient = new MockHttpClient() {
                @Override
                public HttpResponse execute(HttpUriRequest request) throws IOException,
                        ClientProtocolException {
                    return response;
                }
            };
        }
        SimpleDownloadLogic logic = new SimpleDownloadLogic(httpClient);
        String actual = logic.downloadUri("http://www.cattaka.net/");
        assertEquals(testData, actual);
    }

    /** HTTPステータスが異常の場合、IOExceptionがスローされることを確認 */
    public void testDownloadUri_badStatus() throws Exception {
        HttpClient httpClient;
        { // モックのHttpClientを作成する
            final StatusLine statusLine = new MockStatusLine() {
                @Override
                public int getStatusCode() {
                    // ステータスコード異常として404を返す
                    return 404;
                }
            };
            final HttpResponse response = new MockHttpResponse() {
                public StatusLine getStatusLine() {
                    return statusLine;
                };
            };
            httpClient = new MockHttpClient() {
                @Override
                public HttpResponse execute(HttpUriRequest request) throws IOException,
                        ClientProtocolException {
                    return response;
                }
            };
        }
        SimpleDownloadLogic logic = new SimpleDownloadLogic(httpClient);
        try {
            logic.downloadUri("http://www.cattaka.net/");
            fail(); // IOExceptionがスローされなければ失敗
        } catch (IOException e) {
            // 成功
        }
    }

    /** 接続失敗の場合、ConnectionFailedExecptionがスローされることを確認 */
    public void testDownloadUri_ioException() throws Exception {
        HttpClient httpClient;
        { // モックのHttpClientを作成する
            httpClient = new MockHttpClient() {
                @Override
                public HttpResponse execute(HttpUriRequest request) throws IOException,
                        ClientProtocolException {
                    // 接続失敗としてIOExceptionをスローする
                    throw new IOException();
                }
            };
        }
        SimpleDownloadLogic logic = new SimpleDownloadLogic(httpClient);
        try {
            logic.downloadUri("http://www.cattaka.net/");
            fail(); // ConnectionFailedExecptionがスローされなければ失敗
        } catch (ConnectionFailedExecption e) {
            // 成功
        }
    }
}
