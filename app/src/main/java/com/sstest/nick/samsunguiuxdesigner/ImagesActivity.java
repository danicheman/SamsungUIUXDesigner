package com.sstest.nick.samsunguiuxdesigner;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import java.io.File;

/**
 * View the contents of a single folder
 */
public class ImagesActivity extends AppCompatActivity {

    public static final String FOLDER_PATH = "folderPath";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_folder_viewer);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        String folder = getIntent().getStringExtra(FOLDER_PATH);
        File fileFolder = new File(folder);

        getSupportActionBar().setTitle(fileFolder.getName());

        ImagesListAdapter mFoldersListAdapter = new ImagesListAdapter(this, fileFolder);
        RecyclerView recyclerView = ((RecyclerView) findViewById(R.id.list_folder_items));
        recyclerView.setAdapter(mFoldersListAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }
}
