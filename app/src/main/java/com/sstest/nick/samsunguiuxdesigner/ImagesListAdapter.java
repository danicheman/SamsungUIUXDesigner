package com.sstest.nick.samsunguiuxdesigner;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.io.File;

/**
 * Display Images in a specified folder
 */

public class ImagesListAdapter extends RecyclerView.Adapter<ImagesListAdapter.ImageViewHolder> {

    private final Context mContext;
    private final File[] mImageFiles;

    public ImagesListAdapter(Context context, File folder) {
        mContext = context;
        mImageFiles = Util.getImageFiles(folder);
    }

    @Override
    public ImageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_image, parent, false);
        return new ImageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ImageViewHolder holder, int position) {
        Glide
            .with(mContext)
            .load(mImageFiles[position])
            .into(holder.image);
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
