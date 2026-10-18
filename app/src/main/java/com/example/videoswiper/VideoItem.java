package com.example.videoswiper;

/**
 *
 */

public class VideoItem {
    private String description;
    private String title;
    private String url;
    private String videoID;



    public String getDescription() {
        return description;
    }
    public String getTitle() {
        return title;
    }
    public String getUrl() {
        return url;
    }
    public String getVideoID() { return videoID; }

    public void setDescription(String description) {
        this.description = description;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public void setURL(String url) {
        this.url = url;
    }
    public void setVideoID(String videoID) { this.videoID = videoID; }
}
