package com.example.juanlu.comic;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by juanlu on 8/03/18.
 */

class ComicCharacterRecyclerViewAdapter extends RecyclerView.Adapter<ComicCharacterRecyclerViewHolder> {

    private static final String LOG_TAG = ComicCharacterRecyclerViewAdapter.class.getSimpleName();

    private List<ComicCharacter> mComicCharacterList;
    private Context mContext;

    public ComicCharacterRecyclerViewAdapter( Context mContext, List<ComicCharacter> mComicCharacterList) {
        this.mComicCharacterList = mComicCharacterList;
        this.mContext = mContext;
    }

    @Override
    public ComicCharacterRecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.browse, null, false);

        ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        );
        view.setLayoutParams(lp);

        ComicCharacterRecyclerViewHolder comicCharacterRecyclerViewHolder
                = new ComicCharacterRecyclerViewHolder(view);

        return comicCharacterRecyclerViewHolder;
    }

    @Override
    public int getItemCount() {
        return mComicCharacterList != null ? mComicCharacterList.size() : 0;
    }

    @Override
    public void onBindViewHolder(ComicCharacterRecyclerViewHolder holder, int position) {
        ComicCharacter comicCharacter = mComicCharacterList.get(position);

        Log.d(LOG_TAG, "Processing: " + comicCharacter.getmTitle() + " -> " + Integer.toString(position));

        Picasso.with(mContext).load(comicCharacter.getThumbnail())
                .error(R.drawable.placeholder)
                .placeholder(R.drawable.placeholder)
                .into(holder.getmThumbnail());
        holder.mTitle.setText(comicCharacter.getmTitle());
    }

    public void loadNewData(List<ComicCharacter> comicCharacters) {
        mComicCharacterList = comicCharacters;

        notifyDataSetChanged();
    }


    public ComicCharacter getComicCharacter(int position) {
        return mComicCharacterList != null ? mComicCharacterList.get(position) : null;
    }

}
