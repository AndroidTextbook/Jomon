package org.android.textbook.lesson4.nfcreadsample;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.nfc.NdefMessage;
import android.nfc.NdefRecord;
import android.nfc.NfcAdapter;
import android.nfc.NfcManager;
import android.nfc.Tag;
import android.nfc.tech.NfcA;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.widget.TextView;

public class NfcReadForegroundActivity extends Activity {
    private static final String TAG = "NfcReadForegroundActivity";
    private NfcAdapter mNfcAdapter;
    private PendingIntent mPendingIntent;
    private IntentFilter[] mIntentFiltersArray;
    private String[][] mTechListsArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_foreground);

        NfcManager nfcManager = (NfcManager) getSystemService(Context.NFC_SERVICE);
        mNfcAdapter = nfcManager.getDefaultAdapter();
        if (mNfcAdapter == null) {
            // このデバイスはNFC機能を対応していない
        }

        mPendingIntent = PendingIntent.getActivity(this, 0, new Intent(this,
                getClass()).addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP), 0);
        IntentFilter ndefIntetFilter = new IntentFilter(
                NfcAdapter.ACTION_NDEF_DISCOVERED);
        ndefIntetFilter.addDataScheme("http");
        mIntentFiltersArray = new IntentFilter[] { ndefIntetFilter, };

        mTechListsArray = new String[][] { new String[] { NfcA.class.getName() } };

    }

    @Override
    public void onResume() {
        super.onResume();
        if (mNfcAdapter != null) {
            // start Foreground dispatch system
            mNfcAdapter.enableForegroundDispatch(this, mPendingIntent,
                    mIntentFiltersArray, mTechListsArray);
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if (mNfcAdapter != null) {
            // end Foreground dispatch system
            mNfcAdapter.disableForegroundDispatch(this);
        }
    }

    @Override
    public void onNewIntent(Intent intent) {
        // get EXTRA_TAG
        Tag tag = intent.getParcelableExtra(NfcAdapter.EXTRA_TAG);

        // get EXTRA_ID
        byte[] tagId = intent.getByteArrayExtra(NfcAdapter.EXTRA_ID);

        // get EXTRA_NDEF_MESSAGES
        Parcelable[] rawMsgs = intent
                .getParcelableArrayExtra(NfcAdapter.EXTRA_NDEF_MESSAGES);
        if (rawMsgs != null) {
            for (Parcelable pa : rawMsgs) {
                NdefMessage ndefMsg = (NdefMessage) pa;
                NdefRecord[] records = ndefMsg.getRecords();
                for (NdefRecord rec : records) {
                    TextView textView = (TextView) findViewById(R.id.textView);
                    Uri uri = rec.toUri();
                    textView.setText(uri.toString());
                    break;
                }
            }
        }
        Log.e(TAG, tag.toString());
        Log.e(TAG, tagId.toString());
    }

}
