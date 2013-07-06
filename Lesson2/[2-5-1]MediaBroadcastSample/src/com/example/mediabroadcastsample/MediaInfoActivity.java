
package com.example.mediabroadcastsample;

import java.io.File;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.media.MediaScannerConnection;
import android.media.MediaScannerConnection.OnScanCompletedListener;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MediaInfoActivity extends Activity implements OnClickListener {

    private static final String LOGTAG = "MediaBroadcastSample";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button bt1, bt2,bt3;
        bt1 = (Button) findViewById(R.id.button1);
        bt1.setOnClickListener(this);
        bt2 = (Button) findViewById(R.id.button2);
        bt2.setOnClickListener(this);
        bt3 = (Button) findViewById(R.id.button3);
        bt3.setOnClickListener(this);
        
        
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button1: {
                // �u���[�h�L���X�g�A�N�V�������g�����R���e���g�v���o�C�_�[�ւ̓o�^
                String filePath = Environment.getExternalStorageDirectory() + "/sample.jpg";

                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
                // Data�t�B�[���h�Ƀt�@�C���p�X��URI�`���Ŏw��
                intent.setData(Uri.fromFile(new File(filePath)));

                sendBroadcast(intent);
                break;
            }
            case R.id.button2: {
                // scanFile API���g�����R���e���g�v���o�C�_�[�ւ̓o�^
                String[] filePaths = {
                        Environment.getExternalStorageDirectory() + "/sample2.jpg"
                };
                String[] mimeTypes = {
                        "media/jpeg"
                };

                MediaScannerConnection.scanFile(getApplicationContext(), filePaths, mimeTypes,
                        new OnScanCompletedListener() {

                            @Override
                            public void onScanCompleted(String path, Uri uri) {
                                // ���f�B�A�o�^���I��������Ƃ�ʒm����
                                Log.v(LOGTAG, "uri : " + uri.getPath() + " is completed");
                            }
                        });

                break;
            }
            case R.id.button3: {
                // �R���e���g�v���o�C�_�[�̏��ւ̃A�N�Z�X
                String[] projection = new String[] {
                        MediaStore.MediaColumns.TITLE,
                        MediaStore.MediaColumns.DATA
                };

                // �Ώۂ̃R���e���g�v���o�C�_��ݒ肵�A�J�[�\�����擾����
                Cursor cursor = getContentResolver().query(
                        MediaStore.Audio.Media.INTERNAL_CONTENT_URI,
                        projection,
                        null, null, null);

                if (null == cursor) {
                    // �J�[�\���̎擾�Ɏ��s����
                } else if (cursor.getCount() < 1) {
                    // �擾�������R�[�h��0��
                } else {
                    // �擾�������R�[�h�ɑ΂��鏈��

                    // �J�[�\���ʒu��擪�Ɉړ�
                    if (!cursor.moveToFirst()) {
                        cursor.close();
                        return;
                    }

                    // �擾�������J�����̃C���f�b�N�X��ێ�
                    int titleIndex =
                            cursor.getColumnIndex(MediaStore.Audio.AudioColumns.TITLE);

                    int pathIndex =
                            cursor.getColumnIndex(MediaStore.Audio.AudioColumns.DATA);

                    // �J�[�\�����ړ������Ȃ���A�^�C�g���J�����ƃf�[�^�J�����̏������O�o�͂���
                    do {
                        Log.d(LOGTAG, "title : " + cursor.getString(titleIndex));
                        Log.d(LOGTAG, "path : " + cursor.getString(pathIndex));
                    } while (cursor.moveToNext());

                    cursor.close();
                }

            }
        }
    }
}
