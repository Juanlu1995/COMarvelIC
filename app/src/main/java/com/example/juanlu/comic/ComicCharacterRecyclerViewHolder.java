package com.example.juanlu.comic;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by juanlu on 8/03/18.
 */

class ComicCharacterRecyclerViewHolder extends RecyclerView.ViewHolder{

    private ImageView mThumbnail;
    public TextView mTitle;

    public ComicCharacterRecyclerViewHolder(View itemView) {
        super(itemView);
        this.mThumbnail = itemView.findViewById(R.id.thumbnail);
        this.mTitle = itemView.findViewById(R.id.title);
    }

    public ImageView getmThumbnail(){
        return mThumbnail;
    }
}
