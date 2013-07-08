
package org.android.textbook.lesson3.alertdialogsample;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Menu;

public class AlertDialogSample extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alert_dialog_sample);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setIcon(android.R.drawable.ic_dialog_alert);
        builder.setTitle(R.string.dialog_title);
        builder.setMessage(R.string.dialog_message);
        builder.setPositiveButton(android.R.string.ok,
                new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // OKボタンが押された
            }
        });
        builder.setNegativeButton(android.R.string.cancel,
                new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // キャンセルボタンが押された
            }
        });
        
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.alert_dialog_sample, menu);
        return true;
    }

}
