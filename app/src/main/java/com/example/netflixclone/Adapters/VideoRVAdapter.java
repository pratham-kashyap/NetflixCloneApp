package com.example.netflixclone.Adapters;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;

import com.example.netflixclone.Data.VideoRVModel;
import com.example.netflixclone.R;
import com.example.netflixclone.VideoDisplayActivity;
import com.example.netflixclone.ViewHolder.VideoViewHolder;
import com.firebase.ui.firestore.paging.FirestorePagingAdapter;
import com.firebase.ui.firestore.paging.FirestorePagingOptions;

public class VideoRVAdapter extends FirestorePagingAdapter<VideoRVModel, VideoViewHolder> {

    private ImageView videoIV;

    /**
     * Construct a new FirestorePagingAdapter from the given {@link FirestorePagingOptions}.
     *
     * @param options
     */
    public VideoRVAdapter(@NonNull FirestorePagingOptions<VideoRVModel> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull VideoViewHolder videoViewHolder, int position, @NonNull VideoRVModel model) {
        videoIV = videoViewHolder.itemView.findViewById(R.id.idIVVideo);
        String thumburl = "http://img.youtube.com/vi"+model.getVideoID()+"/hqdefault.jpg";
        videoViewHolder.setVideoIV(thumburl);
        videoViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(videoViewHolder.itemView.getContext(),VideoDisplayActivity.class);
                i.putExtra("videoTitle",model.getVideoTitle());
                i.putExtra("videoCategory",model.getVideoCategory());
                i.putExtra("videoDesc",model.getVideoDesc());
                i.putExtra("videoID",model.getVideoID());
                videoViewHolder.itemView.getContext().startActivity(i);


            }
        });
    }

    @NonNull
    @Override
    public VideoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.video_rv_item,parent,false);
        return new VideoViewHolder(view);
    }
}
