
package org.android.textbook.lesson2.contextsample;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ContextSampleActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // アクティビティコンテキスト
        Context activity = this;

        // アプリケーションコンテキスト
        Context application = getApplicationContext();

        // リストに表示するアイテム
        String[] item = {
                "aaa", "bbb", "ccc", "ddd"
        };

        ListView activityList = (ListView) findViewById(R.id.listView1);
        ListView applicationList = (ListView) findViewById(R.id.listView2);

        // リスト表示用のArrayAdapterの作成
        ArrayAdapter<String> activityAdapter = 
                new ArrayAdapter<String>(activity,
                android.R.layout.simple_list_item_1, item);
        ArrayAdapter<String> applicationAdapter = 
                new ArrayAdapter<String>(application,
                android.R.layout.simple_list_item_1, item);

        activityList.setAdapter(activityAdapter);
        applicationList.setAdapter(applicationAdapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

}
