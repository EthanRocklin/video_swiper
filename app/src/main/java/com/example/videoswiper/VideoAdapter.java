package com.example.videoswiper;

import android.media.MediaPlayer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

/**
 *
 */

public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.VideoViewHolder> {

    private List<VideoItem> videoItems;

    public VideoAdapter(List<VideoItem> videoItems) {
        this.videoItems = videoItems;
    }

    /**
     *
     * @param parent   The ViewGroup into which the new View will be added after it is bound to
     *                 an adapter position.
     * @param viewType The view type of the new View.
     * @return
     */
    @NonNull
    @Override
    public VideoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new VideoViewHolder(
                LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.video_item_container, parent, false)
        );
    }

    /**
     *
     * @param holder   The ViewHolder which should be updated to represent the contents of the
     *                 item at the given position in the data set.
     * @param position The position of the item within the adapter's data set.
     */
    @Override
    public void onBindViewHolder(@NonNull VideoViewHolder holder, int position) {
        holder.setVideoData(videoItems.get(position));
    }

    /**
     *
     * @return
     */
    @Override
    public int getItemCount() {
        return videoItems.size();
    }

    /**
     *
     */
    static class VideoViewHolder extends RecyclerView.ViewHolder {
        TextView videoTitleTV;
        TextView videoDescriptionTV;
        VideoView videoView;
        ProgressBar progressBar;

        /**
         *
         * @param itemView
         */
        public VideoViewHolder(@NonNull View itemView) {
            super(itemView);
            //
            videoView = itemView.findViewById(R.id.videoView);
            videoTitleTV = itemView.findViewById(R.id.videoTitleTV);
            videoDescriptionTV = itemView.findViewById(R.id.videoDescriptionTV);
            progressBar = itemView.findViewById(R.id.videoProgressBar);
        }

        /**
         *
         * @param videoItem
         */
        void setVideoData(VideoItem videoItem) {
            //
            videoTitleTV.setText(videoItem.getTitle());
            videoDescriptionTV.setText(videoItem.getDescription());
            videoView.setVideoPath(videoItem.getUrl());
            // Establishes a listener for playing the next video
            videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                /**
                 *
                 * @param mp
                 */
                @Override
                public void onPrepared(MediaPlayer mp) {
                    progressBar.setVisibility(View.GONE);
                    mp.setLooping(true);
                    mp.start();
                }
            });
        }

    }

}
