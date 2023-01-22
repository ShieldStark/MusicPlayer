package com.example.musicplayer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class listActivity extends AppCompatActivity {
    private ImageView play;
    private Button next;
    private Button prev;
    private Button playList;
    private Button pause;
    private ImageView songImg;
    private TextView songName;
    private TextView artistName;
    private ArrayList<AudioAdapter> songList;
    AudioAdapter currentSong;
    MediaPlayer mediaPlayer= MyMediaPlayer.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        playList=findViewById(R.id.playlist);
        next=findViewById(R.id.next);
        prev=findViewById(R.id.btn_prev);
        play=findViewById(R.id.btn_ply);
        pause=findViewById(R.id.btn_pause);
        songImg=findViewById(R.id.song_image_view);
        songName=findViewById(R.id.song_name);
        artistName=findViewById(R.id.artist_name);

        songName.setSelected(true);
        songList=(ArrayList<AudioAdapter>) getIntent().getSerializableExtra("LIST");

        setResourceWithMusic();

        playList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(listActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });




    }
    void setResourceWithMusic(){
        if(songList!=null) {
            currentSong = songList.get(MyMediaPlayer.currentIndex);
            songName.setText(currentSong.getTitle());
            artistName.setText(currentSong.getartist());
        }
        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pausePlay();
            }
        });
        next.setOnClickListener(v->playNext());
        prev.setOnClickListener(v->playPrevious());
        playMusic();
    }
    private void playMusic(){
        mediaPlayer.reset();
        try {
            mediaPlayer.setDataSource(currentSong.getPath());
            mediaPlayer.prepare();
            mediaPlayer.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
        pause.setVisibility(View.VISIBLE);
        play.setVisibility(View.GONE);

    }
    private void playNext(){
        if(MyMediaPlayer.currentIndex==songList.size()-1)
            return;
        MyMediaPlayer.currentIndex+=1;
        mediaPlayer.reset();
        setResourceWithMusic();

    }
    private void playPrevious(){
        if(MyMediaPlayer.currentIndex==0)
            return;
        MyMediaPlayer.currentIndex-=1;
        mediaPlayer.reset();
        setResourceWithMusic();
    }
    private void pausePlay(){
        if(mediaPlayer.isPlaying()) {
            mediaPlayer.pause();
            play.setImageResource(R.drawable.media_pause);
        }
        else {
            mediaPlayer.start();
            play.setImageResource(R.drawable.media_play);
        }
    }
}