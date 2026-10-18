package com.example.videoswiper;

import android.os.Bundle;
import android.provider.MediaStore;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.viewpager2.widget.ViewPager2;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        //
        final ViewPager2 videoViewPager = findViewById(R.id.videoViewPager);

        List<VideoItem> videoItemsList = new ArrayList<>();
        // Prepares videos into the list
        String[] urls = getResources().getStringArray(R.array.urls);
        String[] titles = getResources().getStringArray(R.array.titles);
        String[] ids = getResources().getStringArray(R.array.video_ids);
        String[] descriptions = getResources().getStringArray(R.array.descriptions);
        for (int i = 0; i < urls.length; i++) {
            VideoItem video = new VideoItem();
            video.setURL(urls[i]);
            video.setTitle(titles[i]);
            video.setVideoID(ids[i]);
            video.setDescription(descriptions[i]);
            videoItemsList.add(video);
        }
        videoViewPager.setAdapter(new VideoAdapter(videoItemsList));

    }
}