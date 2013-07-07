
package com.android_textbook.learnjunit;

import android.content.Intent;
import android.os.IBinder;
import android.test.ServiceTestCase;

import com.android_textbook.learnjunit.CountUpService;
import com.android_textbook.learnjunit.ICountUpService;

public class CountUpServiceTest extends ServiceTestCase<CountUpService> {
    public CountUpServiceTest() {
        super(CountUpService.class);
    }

    public void testGetCountUp() throws Exception {
        { // サービスを開始する
            Intent intent = new Intent();
            intent.setClass(getContext(), CountUpService.class);
            startService(intent);
        }

        { // １回目のバインドについて確認する
          // サービスをバインドする
            Intent intent = new Intent();
            intent.setClass(getContext(), CountUpService.class);
            IBinder binder = bindService(intent);
            ICountUpService iservice = ICountUpService.Stub.asInterface(binder);

            // 実行結果が想定通りか確認する
            assertEquals(1, iservice.getCount());
            assertEquals(2, iservice.getCount());
            assertEquals(3, iservice.getCount());
        }

        { // ２回目のバインドについて確認する
          // サービスをバインドする
            Intent intent = new Intent();
            intent.setClass(getContext(), CountUpService.class);
            IBinder binder = bindService(intent);
            ICountUpService iservice = ICountUpService.Stub.asInterface(binder);

            // 実行結果が想定通りか確認する
            assertEquals(4, iservice.getCount());
            assertEquals(5, iservice.getCount());
            assertEquals(6, iservice.getCount());
        }

        shutdownService();
    }
}
