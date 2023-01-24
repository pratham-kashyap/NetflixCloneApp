package com.example.netflixclone.ViewHolder;

import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.netflixclone.R;
import com.squareup.picasso.Picasso;

public class VideoViewHolder extends RecyclerView.ViewHolder {

    private ImageView videoIV;

    public void setVideoIV(String imgUrl){
        Picasso.get().load(imgUrl).into(videoIV);
    }

    public VideoViewHolder(@NonNull View itemView) {
        super(itemView);
        videoIV = itemView.findViewById(R.id.idIVVideo);
    }
}
