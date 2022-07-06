package com.example.musicplayer;

import java.io.Serializable;

public class AudioAdapter implements Serializable {
    String path;
    String title;
    String artist;

    public AudioAdapter(String path, String title, String artist) {
        this.path = path;
        this.title = title;
        this.artist = artist;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getartist() {
        return artist;
    }

    public void setartist(String artist) {
        this.artist = artist;
    }
}
