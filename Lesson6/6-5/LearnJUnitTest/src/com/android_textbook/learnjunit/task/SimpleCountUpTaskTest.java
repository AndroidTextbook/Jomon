
package com.android_textbook.learnjunit.task;

import java.util.ArrayList;
import java.util.List;

import com.android_textbook.learnjunit.task.SimpleCountUpTask;

import android.test.InstrumentationTestCase;

public class SimpleCountUpTaskTest extends InstrumentationTestCase {
    public static class SimpleCountUpTaskEx extends SimpleCountUpTask {
        private List<Integer> progress = new ArrayList<Integer>();

        private Integer result;

        @Override
        protected void onProgressUpdate(Integer... values) {
            progress.add(values[0]);
        }

        @Override
        protected void onPostExecute(Integer result) {
            this.result = result;
        }
    }

    public void testSuccessPattern() throws Exception {
        SimpleCountUpTaskEx task = new SimpleCountUpTaskEx();
        task.execute(10); // タスクを実行
        task.get(); // 停止するまで待つ

        // onProgressUpdate経由で通知された値が想定通りであることを確認する
        assertEquals(10, task.progress.size());
        assertEquals(1, task.progress.get(0).intValue());
        assertEquals(2, task.progress.get(1).intValue());
        // 3〜9は省略
        assertEquals(10, task.progress.get(9).intValue());

        // onPostExecute経由で通知された値が想定通りであることを確認する
        assertEquals(55, task.result.intValue());

    }
}
