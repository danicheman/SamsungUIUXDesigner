package com.sstest.nick.samsunguiuxdesigner;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import java.io.File;

/**
 * View the contents of a single folder
 */
public class FolderViewActivity extends AppCompatActivity {

    public static final String FOLDER_PATH = "folderPath";
    private static final String TAG = "FolderViewActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_folder_viewer);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        //getSupportActionBar().setBackgroundDrawable();
        String folder = getIntent().getStringExtra(FOLDER_PATH);
        File fileFolder = new File(folder);

        getSupportActionBar().setTitle(fileFolder.getName());
        //todo: if no file display an error message

        FolderListAdapter mFoldersListAdapter = new FolderListAdapter(this, fileFolder);
        RecyclerView recyclerView = ((RecyclerView) findViewById(R.id.list_folder_items));
        recyclerView.setAdapter(mFoldersListAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }
}
