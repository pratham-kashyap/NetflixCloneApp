package com.example.netflixclone;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class VideoDisplayActivity extends AppCompatActivity {

    private ImageView videoIV;
    private ImageButton playIB;
    private TextView videoTitleTV,videoCategoryTV,videoDescTV;
    String videoTitle,videoDesc,videoCategory,videoID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_display);

        videoTitle = getIntent().getStringExtra("videoTitle");
        videoDesc = getIntent().getStringExtra("videoDesc");
        videoCategory = getIntent().getStringExtra("videoCategory");
        videoID = getIntent().getStringExtra("videoID");

        videoIV.findViewById(R.id.idIVVideo);
        playIB.findViewById(R.id.idIBPlay);
        videoTitleTV.findViewById(R.id.idTVTitle);
        videoCategoryTV.findViewById(R.id.idTVCategory);
        videoDescTV.findViewById(R.id.idTVVideoDesc);

        playIB.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(VideoDisplayActivity.this,VideoPlayerActivity.class);
                i.putExtra("videoID",videoID);
                startActivity(i);
            }
        }));

        String thumbnailurl = "http://img.youtube.com/vi"+videoID+"/hqdefault.jpg";
        Picasso.get().load(thumbnailurl).into(videoIV);
        videoTitleTV.setText(videoTitle);
        videoCategoryTV.setText(videoCategory);
        videoDescTV.setText(videoDesc);
    }
}