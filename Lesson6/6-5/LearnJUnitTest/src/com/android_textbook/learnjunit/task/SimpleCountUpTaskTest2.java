
package com.android_textbook.learnjunit.task;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import com.android_textbook.learnjunit.task.SimpleCountUpTask;

import android.test.InstrumentationTestCase;

public class SimpleCountUpTaskTest2 extends InstrumentationTestCase {
    public static class SimpleCountUpTaskEx extends SimpleCountUpTask {
        private CountDownLatch signal = new CountDownLatch(1);

        private List<Integer> progress = new ArrayList<Integer>();

        private Integer result;

        @Override
        protected void onProgressUpdate(Integer... values) {
            progress.add(values[0]);
        }

        @Override
        protected void onPostExecute(Integer result) {
            this.result = result;
            signal.countDown();
        }
    }

    // UIスレッド上でインスタンスを生成させるためのクラス
    class RunnableImpl implements Runnable {
        SimpleCountUpTaskEx task;

        int param;

        public RunnableImpl(int param) {
            this.param = param;
        }

        @Override
        public void run() {
            task = new SimpleCountUpTaskEx();
            task.execute(param);
        };
    }

    public void testSuccessPattern() throws Throwable {
        // UIスレッド上でインスタンスを生成する
        RunnableImpl r = new RunnableImpl(10);
        runTestOnUiThread(r);
        r.task.signal.await(10, TimeUnit.SECONDS);

        // onProgressUpdate経由で通知された値が想定通りであることを確認する
        assertEquals(10, r.task.progress.size());
        assertEquals(1, r.task.progress.get(0).intValue());
        assertEquals(2, r.task.progress.get(1).intValue());
        /* 3〜9は省略 */
        assertEquals(10, r.task.progress.get(9).intValue());

        // onPostExecute経由で通知された値が想定通りであることを確認する
        assertEquals(55, r.task.result.intValue());
    }
}
