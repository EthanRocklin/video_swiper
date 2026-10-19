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
 * This class acts as an adapter for the video being played, including sizing it while
 * maintaining its aspect ratio and other functions needed for swiping between videos.
 *
 * @author Ethan Rocklin
 */

public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.VideoViewHolder> {

    private List<VideoItem> videoItems;

    public VideoAdapter(List<VideoItem> videoItems) {
        this.videoItems = videoItems;
    }

    /**
     * This method creates and returns the VideoViewHolder object needed to display
     * a VideoItem's content.
     * @param parent   The ViewGroup into which the new View will be added after it is bound to
     *                 an adapter position.
     * @param viewType The view type of the new View.
     * @return a VideoViewHolder object for the targeted video's content to be bound to
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
     * This method recycles the current VideoViewHolder and releases the resources
     * being used by the current video's MediaPlayer.
     * @param holder The ViewHolder for the view being recycled
     */
    @Override
    public void onViewRecycled(@NonNull VideoViewHolder holder) {
        super.onViewRecycled(holder);
        holder.videoView.stopPlayback();
    }

    /**
     * This method binds a VideoItem's data to its corresponding VideoViewHolder.
     * @param holder   The ViewHolder which should be updated to represent the contents of the
     *                 item at the given position in the data set.
     * @param position The position of the item within the adapter's data set.
     */
    @Override
    public void onBindViewHolder(@NonNull VideoViewHolder holder, int position) {
        holder.setVideoData(videoItems.get(position));
    }

    /**
     * This method is an overriden version of the getItemCount() method in
     * RecyclerView.Adapter<VideoAdapter.VideoViewHolder>
     * @return the number of videos currently listed
     */
    @Override
    public int getItemCount() {
        return videoItems.size();
    }

    /**
     * This class represents the holder for the current video's details to be
     * displayed in, including its streamed content.
     */
    static class VideoViewHolder extends RecyclerView.ViewHolder {
        TextView videoTitleTV;
        TextView videoDescriptionTV;
        TextView videoIdTV;
        VideoView videoView;
        ProgressBar progressBar;

        /**
         * This is the constructor for the VideoViewHolder class.
         * @param itemView the root UI layout
         */
        public VideoViewHolder(@NonNull View itemView) {
            super(itemView);
            //
            videoView = itemView.findViewById(R.id.videoView);
            videoTitleTV = itemView.findViewById(R.id.videoTitleTV);
            videoIdTV = itemView.findViewById(R.id.videoIdTV);
            videoDescriptionTV = itemView.findViewById(R.id.videoDescriptionTV);
            progressBar = itemView.findViewById(R.id.videoProgressBar);
        }

        /**
         * This method sets a video's data based on its available title, description,
         * identification number, and URL.
         * @param videoItem The video needing its details set to display in the UI
         */
        void setVideoData(VideoItem videoItem) {
            //
            videoTitleTV.setText(videoItem.getTitle());
            videoDescriptionTV.setText(videoItem.getDescription());
            String id = "ID: " + videoItem.getVideoID();
            videoIdTV.setText(id);
            videoView.setVideoPath(videoItem.getUrl());
            // Establishes a listener for playing the next video
            videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                /**
                 * This method plays a video once it is done buffering, and enables looping.
                 * @param mp the MediaPlayer object
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
