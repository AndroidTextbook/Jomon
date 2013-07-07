
package com.android_textbook.learnjunit;

import com.android_textbook.learnjunit.CountUpService;
import com.android_textbook.learnjunit.PowerConnectedReceiver;

import junit.framework.TestCase;
import android.content.ComponentName;
import android.content.Intent;
import android.test.mock.MockContext;

public class PowerConnectedReceiverTest extends TestCase {
    /** ユニットテストのために引数を横取りするためのクラス */
    static class MockCotextEx extends MockContext {
        private Intent startIntent;

        private Intent stopIntent;

        @Override
        public String getPackageName() {
            return "";
        }

        @Override
        public ComponentName startService(Intent service) {
            startIntent = service;
            return null;
        }

        @Override
        public boolean stopService(Intent service) {
            stopIntent = service;
            return true;
        }
    }

    /** 電源接続時にサービスが開始することを確認する */
    public void testOnReceive_startService() throws Exception {
        MockCotextEx mockCotext = new MockCotextEx();
        Intent intent = new Intent(Intent.ACTION_POWER_CONNECTED);
        PowerConnectedReceiver receive = new PowerConnectedReceiver();
        receive.onReceive(mockCotext, intent);

        // startServiceメソッドが呼ばれていることを確認する
        assertNotNull(mockCotext.startIntent);
        assertEquals(CountUpService.class.getName(), mockCotext.startIntent.getComponent()
                .getClassName());
        // stopServiceメソッドが呼ばれていないことを確認する
        assertNull(mockCotext.stopIntent);
    }

    /** 電源切断時にサービスが停止することを確認する */
    public void testOnReceive_stopService() throws Exception {
        MockCotextEx mockCotext = new MockCotextEx();
        Intent intent = new Intent(Intent.ACTION_POWER_DISCONNECTED);
        PowerConnectedReceiver receive = new PowerConnectedReceiver();
        receive.onReceive(mockCotext, intent);

        // stopServiceメソッドが呼ばれていることを確認する
        assertNotNull(mockCotext.stopIntent);
        assertEquals(CountUpService.class.getName(), mockCotext.stopIntent.getComponent()
                .getClassName());
        // startServiceメソッドが呼ばれていないことを確認する
        assertNull(mockCotext.startIntent);
    }
}
