package com.sstest.nick.samsunguiuxdesigner;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.File;
import java.io.FilenameFilter;


class Util {
    static Bitmap getFolderPreviewImage(String folderName) {
        // todo: use glide or not?
        File[] images = new File(folderName).listFiles(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                return (name.endsWith(".jpg") || name.endsWith(".jpeg"));
            }
        });

        if (images == null || images.length == 0) return null;

        return getBitmap(images[0]);
    }

    static Bitmap getBitmap(File image) {
        return BitmapFactory.decodeFile(image.getAbsolutePath());
    }
}
