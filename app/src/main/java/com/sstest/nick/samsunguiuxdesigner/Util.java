package com.sstest.nick.samsunguiuxdesigner;

import java.io.File;
import java.io.FilenameFilter;


class Util {
    static File getFolderPreviewImage(File folderName) {
        File[] images = getImageFiles(folderName);

        if (images == null || images.length == 0) return null;

        return images[0];
    }

    static File[] getImageFiles(File folder) {
        return folder.listFiles(new FilenameFilter() {
                @Override
                public boolean accept(File dir, String name) {
                    return (name.endsWith(".jpg") || name.endsWith(".jpeg"));
                }
            });
    }

}
