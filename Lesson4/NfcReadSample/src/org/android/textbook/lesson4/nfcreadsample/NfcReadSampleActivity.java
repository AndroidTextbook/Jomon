package org.android.textbook.lesson4.nfcreadsample;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.nfc.NdefMessage;
import android.nfc.NdefRecord;
import android.nfc.NfcAdapter;
import android.nfc.NfcManager;
import android.nfc.Tag;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.widget.TextView;

public class NfcReadSampleActivity extends Activity {
    private static final String TAG = "NfcReadSampleActivity";
    private NfcAdapter mNfcAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        NfcManager nfcManager = (NfcManager) getSystemService(Context.NFC_SERVICE);
        mNfcAdapter = nfcManager.getDefaultAdapter();
        if (mNfcAdapter == null) {
            // このデバイスはNFC機能を対応していない
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        // get EXTRA_TAG
        Tag tag = getIntent().getParcelableExtra(NfcAdapter.EXTRA_TAG);

        // get EXTRA_ID
        byte[] tagId = getIntent().getByteArrayExtra(NfcAdapter.EXTRA_ID);

        // get EXTRA_NDEF_MESSAGES
        Parcelable[] rawMsgs = getIntent().getParcelableArrayExtra(
                NfcAdapter.EXTRA_NDEF_MESSAGES);
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
