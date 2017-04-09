package com.sstest.nick.samsunguiuxdesigner;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by NICK on 0008, April 4/08.
 */

public class FoldersListAdapter extends RecyclerView.Adapter<FoldersListAdapter.FolderViewHolder> {

    private static final String TAG = "FoldersListAdapter";

    private static Context mContext;
    private static File mBasePath;
    private final View mEmptyView;

    private ArrayList<File> mFiles;

    public FoldersListAdapter(@NonNull Context context, @NonNull File basePath, @NonNull View emptyView) {
        mContext = context;
        mBasePath = basePath;
        mEmptyView = emptyView;
        //todo: move this into custom loader?
        mFiles = new ArrayList<>(Arrays.asList(mBasePath.listFiles(new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                return pathname.isDirectory();
            }
        })));
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
        String folderName = mFiles.get(position).getName();
        holder.folderName.setText(folderName);
        holder.folderImage.setImageBitmap(Util.getFolderPreviewImage(mFiles.get(position).getAbsolutePath()));
    }

    @Override
    public int getItemCount() {
        return mFiles.size();
    }

    public static class FolderViewHolder extends RecyclerView.ViewHolder implements OnLongClickListener, OnClickListener{

        public final TextView folderName;
        public final ImageView folderImage;

        public FolderViewHolder(View itemView) {
            super(itemView);
            folderName = (TextView)itemView.findViewById(R.id.item_folder_name);

            folderImage = (ImageView)itemView.findViewById(R.id.item_folder_image);
            folderImage.setOnClickListener(this);
            folderImage.setOnLongClickListener(this);
        }

        /**
         * Launch a view showing folders in the image with
         * {@Link FolderViewActivity}
         * @param v
         */
        @Override
        public void onClick(View v) {

            //launch folder view for this foldername
            Intent folderViewIntent = new Intent(mContext, FolderViewActivity.class);
            folderViewIntent.putExtra(FolderViewActivity.FOLDER_PATH, mBasePath.getAbsolutePath() +"/"+ folderName.getText());
            mContext.startActivity(folderViewIntent);
        }

        /**
         * Display a pager preview overlay of images in the folder
         * @param v
         * @return
         */
        @Override
        public boolean onLongClick(View v) {
            Toast.makeText(mContext, "Long Click pressed", Toast.LENGTH_SHORT).show();
            return false;
        }
    }


}
