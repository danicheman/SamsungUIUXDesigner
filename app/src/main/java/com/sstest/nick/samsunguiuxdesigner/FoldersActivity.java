package com.sstest.nick.samsunguiuxdesigner;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.MotionEventCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;
import android.view.View;

import java.io.File;

public class FoldersActivity extends AppCompatActivity {

    private static final String BASE_PATH = "Pictures";

    private static final int SCROLL_DISTANCE = 25;

    private ViewPager viewPager;
    private float x1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            checkPermissions();
        }

        File file = new File(Environment.getExternalStorageDirectory().getAbsolutePath(), BASE_PATH);

        viewPager = (ViewPager) findViewById(R.id.pager_folder_preview);
        viewPager.setOffscreenPageLimit(5);

        final FoldersListAdapter mFoldersListAdapter = new FoldersListAdapter(
                this,
                file,
                viewPager
        );

        RecyclerView recyclerView = ((RecyclerView) findViewById(R.id.list_folders));
        recyclerView.setAdapter(mFoldersListAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (viewPager.getVisibility() == View.GONE) {
            return super.dispatchTouchEvent(ev);
        }

        int action = MotionEventCompat.getActionMasked(ev);

        switch (action) {
            case (MotionEvent.ACTION_DOWN):
                x1 = ev.getX();
                return false;
            case (MotionEvent.ACTION_MOVE):
                float x2, dx;
                x2 = ev.getX();
                dx = x2 - x1;
                if (dx > SCROLL_DISTANCE) {
                    viewPager.setCurrentItem(viewPager.getCurrentItem() + 1, true);
                    x1 = x2;
                } else if (dx < -SCROLL_DISTANCE) {
                    x1 = x2;
                    viewPager.setCurrentItem(viewPager.getCurrentItem() - 1, true);
                }
                return false;
            case (MotionEvent.ACTION_UP):
                viewPager.setVisibility(View.GONE);
                return false;
            default:
                return super.dispatchTouchEvent(ev);
        }
    }


    private void checkPermissions() {
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this,
                    new String[]{
                            Manifest.permission.READ_EXTERNAL_STORAGE
                    },
                    1052);

        }
    }
}
