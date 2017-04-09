package com.sstest.nick.samsunguiuxdesigner;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import java.io.File;
import java.io.IOException;

public class FoldersViewActivity extends AppCompatActivity {

    private static final String TAG = "FoldersViewActivity";
    private static final String BASE_PATH = "Pictures";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            checkPermissions();
        }

        File file = new File(Environment.getExternalStorageDirectory().getAbsolutePath(), BASE_PATH);

        FoldersListAdapter mFoldersListAdapter = new FoldersListAdapter(this, file, findViewById(R.id.list_folders_empty_view));

        RecyclerView recyclerView = ((RecyclerView) findViewById(R.id.list_folders));
        recyclerView.setAdapter(mFoldersListAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }

    private void checkPermissions() {

        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED ||
                ContextCompat.checkSelfPermission(this,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE)
                        != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this,
                    new String[]{
                            Manifest.permission.READ_EXTERNAL_STORAGE,
                            Manifest.permission.WRITE_EXTERNAL_STORAGE
                    },
                    1052);

        }
    }
}
