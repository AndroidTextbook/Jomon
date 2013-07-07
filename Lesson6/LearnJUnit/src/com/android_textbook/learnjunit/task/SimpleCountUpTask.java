
package com.android_textbook.learnjunit.task;

import android.os.AsyncTask;

public class SimpleCountUpTask extends AsyncTask<Integer, Integer, Integer> {
    @Override
    protected Integer doInBackground(Integer... params) {
        if (params.length == 0) {
            return -1;
        }
        int result = 0;
        int last = params[0];
        for (int i = 1; i <= last; i++) {
            result += i;
            publishProgress(i);
        }
        return result;
    }
}
