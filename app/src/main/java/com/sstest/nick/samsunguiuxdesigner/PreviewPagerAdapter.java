package com.sstest.nick.samsunguiuxdesigner;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.io.File;

/**
 * Preview images in an image folder
 */
class PreviewPagerAdapter extends PagerAdapter {

    private final Context mContext;
    private final File[] imageFiles;

    PreviewPagerAdapter(Context context, File path) {
        mContext = context;
        imageFiles = Util.getImageFiles(path);
    }

    @Override
    public int getCount() {
        return imageFiles.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        ImageView imageView = new ImageView(mContext);
        imageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        Glide
            .with(mContext)
            .load(imageFiles[position])
            .into(imageView);
        container.addView(imageView, 0);
        return imageView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((ImageView) object);
    }
}
