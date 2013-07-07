
package org.android.textbook.lesson4.locationsample;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.provider.Settings;

public class EnableLocationDialogFragment extends DialogFragment implements OnClickListener {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Location");
        builder.setMessage("Location機能がOFFです。有効にしくてださい");
        builder.setPositiveButton("はい", this);

        return builder.create();
    }

    public void onClick(DialogInterface dialog, int which) {
        // Settingアプリを起動する
        Intent settingsIntent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
        startActivity(settingsIntent);
    }

}
