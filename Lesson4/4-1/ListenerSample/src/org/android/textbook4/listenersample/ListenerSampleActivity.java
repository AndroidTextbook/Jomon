package org.android.textbook4.listenersample;

import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class ListenerSampleActivity extends Activity {

    /**
     * ボタンウィジェットに登録するリスナー
     * 
     * @note ボタンウィジェットでクリックイベントが発生すると、onClickメソッドが呼ばれます
     * */
    private OnClickListener mClickListener = new OnClickListener() {

        public void onClick(View v) {
            // ボタンをクリックすると、このメソッドが呼ばれる
            Toast.makeText(ListenerSampleActivity.this, "onClick",
                    Toast.LENGTH_LONG).show();
        }

    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // レイアウトからボタンウィジェットを取得
        Button button = (Button) findViewById(R.id.button);
        // クリックイベントのリスナーを登録
        button.setOnClickListener(mClickListener);
    }

}
