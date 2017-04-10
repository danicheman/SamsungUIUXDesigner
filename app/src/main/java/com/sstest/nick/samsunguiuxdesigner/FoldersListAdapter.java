package com.sstest.nick.samsunguiuxdesigner;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.io.File;
import java.io.FileFilter;

/**
 * Display Folders in the given path
 */

class FoldersListAdapter extends RecyclerView.Adapter<FoldersListAdapter.FolderViewHolder> {


    private final Context mContext;
    private final ViewPager mPager;

    private static File[] mFiles;

    public FoldersListAdapter(@NonNull Context context, @NonNull File basePath, ViewPager pager) {
        mContext = context;
        mPager = pager;

        mFiles = basePath.listFiles(new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                return pathname.isDirectory();
            }
        });
    }

    @Override
    public FolderViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater
                        .from(parent.getContext())
                        .inflate(R.layout.list_item_folder, parent, false);


        return new FolderViewHolder(view);
    }

    @Override
    public void onBindViewHolder(FolderViewHolder holder, int position) {
        String folderName = mFiles[position].getName();
        holder.folderName.setText(folderName);
        Glide
            .with(mContext)
            .load(Util.getFolderPreviewImage(mFiles[position]))
            .into(holder.folderImage);
    }

    @Override
    public int getItemCount() {
        return mFiles.length;
    }


    class FolderViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {

        final TextView folderName;
        final ImageView folderImage;

        FolderViewHolder(View itemView) {
            super(itemView);
            folderName = (TextView)itemView.findViewById(R.id.item_folder_name);
            folderImage = (ImageView)itemView.findViewById(R.id.item_folder_image);
            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
        }

        //launch folder images viewing activity
        @Override
        public void onClick(View v) {
            Intent folderViewIntent = new Intent(mContext, ImagesActivity.class);
            folderViewIntent.putExtra(ImagesActivity.FOLDER_PATH, mFiles[getAdapterPosition()].getAbsolutePath());
            mContext.startActivity(folderViewIntent);
        }

        //launch folder images preview
        @Override
        public boolean onLongClick(View v) {

            mPager.setVisibility(View.VISIBLE);
            mPager.setAdapter(new PreviewPagerAdapter(mContext, mFiles[getAdapterPosition()]));
            return true;
        }
    }
}
