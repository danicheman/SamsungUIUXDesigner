package com.sstest.nick.samsunguiuxdesigner;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;
import java.io.FilenameFilter;

/**
 * Created by NICK on 0008, April 4/08.
 */

public class FolderListAdapter extends RecyclerView.Adapter<FolderListAdapter.ImageViewHolder> {

    private final Context mContext;
    private final File[] mImageFiles;

    public FolderListAdapter(Context context, File folder) {

        mContext = context;
        mImageFiles = folder.listFiles(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                return (name.endsWith(".jpg") || name.endsWith(".jpeg"));
            }
        });

    }

    @Override
    public ImageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_image, parent, false);
        return new ImageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ImageViewHolder holder, int position) {
        holder.image.setImageBitmap(Util.getBitmap(mImageFiles[position]));
        holder.imageName.setText(mImageFiles[position].getName());
    }

    @Override
    public int getItemCount() {
        if (mImageFiles == null) return 0;
        return mImageFiles.length;
    }

    public class ImageViewHolder extends RecyclerView.ViewHolder {
        public final TextView imageName;
        public final ImageView image;

        public ImageViewHolder(View itemView) {
            super(itemView);

            image = (ImageView) itemView.findViewById(R.id.item_image);
            imageName = (TextView) itemView.findViewById(R.id.item_image_name);
        }
    }
}
