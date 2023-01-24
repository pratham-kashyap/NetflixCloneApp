package com.example.netflixclone.ViewHolder;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.netflixclone.R;
import com.firebase.ui.firestore.paging.FirestorePagingAdapter;

public class CategoryViewHolder extends RecyclerView.ViewHolder {

    public TextView categoryTV;
    private RecyclerView videosRV;
    private Context ctx;

    public CategoryViewHolder(@NonNull View itemView) {
        super(itemView);
        categoryTV = itemView.findViewById(R.id.idTVCategory);
        videosRV = itemView.findViewById(R.id.idRVVideos);
    }

    public TextView getCategoryTV() {
        return categoryTV;
    }

    public void setCategoryTV(String category) {
        this.categoryTV.setText(category);
    }

    public RecyclerView getVideosRV() {
        return videosRV;
    }

    public void setVideosTv(FirestorePagingAdapter adapter, RecyclerView.LayoutManager layoutManager) {
        this.videosRV.setLayoutManager(layoutManager);
        this.videosRV.setAdapter(adapter);
    }
}
