
package com.android_textbook.learnjunit.task;

import java.util.ArrayList;
import java.util.List;

import com.android_textbook.learnjunit.task.SimpleCountUpTask;

import android.test.InstrumentationTestCase;

public class SimpleCountUpTaskTest3 extends InstrumentationTestCase {
    public static class SimpleCountUpTaskEx extends SimpleCountUpTask {
        // private CountDownLatch signal = new CountDownLatch(1);
        boolean called = false;

        private List<Integer> progress = new ArrayList<Integer>();

        private Integer result;

        @Override
        protected void onProgressUpdate(Integer... values) {
            progress.add(values[0]);
        }

        @Override
        protected void onPostExecute(Integer result) {
            this.result = result;
            if (called) {
                throw new RuntimeException("called twice");
            }
            called = true;
            // signal.countDown();
        }
    }

    // UIスレッド上でインスタンスを生成させるためのクラス
    class RunnableImpl implements Runnable {
        SimpleCountUpTaskEx task;

        @Override
        public void run() {
            task = new SimpleCountUpTaskEx();
        };
    }

    SimpleCountUpTaskEx task;

    public void testSuccessPattern() throws Throwable {
        // UIスレッド上でインスタンスを生成する
        // RunnableImpl r = new RunnableImpl();
        // runTestOnUiThread(r);
        runTestOnUiThread(new Runnable() {
            @Override
            public void run() {
                task = new SimpleCountUpTaskEx();
                task.execute(10);
            }
        });
        task.get();
        assertEquals(10, task.progress.size());
        assertEquals(55, task.result.intValue());
        assertTrue(task.called);
    }
}
