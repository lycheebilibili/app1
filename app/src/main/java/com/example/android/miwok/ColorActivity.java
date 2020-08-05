package com.example.android.miwok;

import android.app.Activity;
import android.content.Context;
import android.media.AudioManager;
import android.media.Image;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.ArrayList;

import static android.media.AudioManager.AUDIOFOCUS_GAIN;
import static android.media.AudioManager.AUDIOFOCUS_LOSS;
import static android.media.AudioManager.AUDIOFOCUS_LOSS_TRANSIENT;
import static android.media.AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK;
import static android.media.AudioManager.AUDIOFOCUS_REQUEST_GRANTED;

public class ColorActivity extends AppCompatActivity {
    private MediaPlayer mplayer;
    private AudioManager mAudioManager;

    private MediaPlayer.OnCompletionListener onCompletionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mp) {
            mediaRelease();
        }
    };

    private AudioManager.OnAudioFocusChangeListener changeListener= new AudioManager.OnAudioFocusChangeListener() {
        @Override
        public void onAudioFocusChange(int focusChange) {
            if(focusChange==AUDIOFOCUS_GAIN){mplayer.start();
            }
            else if (focusChange==AUDIOFOCUS_LOSS){mediaRelease();}
            else if(focusChange==AUDIOFOCUS_LOSS_TRANSIENT||focusChange==AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK){
                mplayer.pause();
                mplayer.seekTo(0); }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_color);
        mAudioManager= (AudioManager)getSystemService(Context.AUDIO_SERVICE);
        final ArrayList<Word> words = new ArrayList<Word>();


        words.add(new Word("red","wetetti",R.drawable.color_red,R.raw.color_red));
        words.add(new Word("green","chokokki",R.drawable.color_green,R.raw.color_green));
        words.add(new Word("brown","takaakki",R.drawable.color_brown,R.raw.color_brown));
        words.add(new Word("gray","topoppi",R.drawable.color_gray,R.raw.color_gray));
        words.add(new Word("black","kululli",R.drawable.color_black,R.raw.color_black));
        words.add(new Word("white","ckelelli",R.drawable.color_white,R.raw.color_white));
        words.add(new Word("dusty yellow","topiise",R.drawable.color_dusty_yellow,R.raw.color_dusty_yellow));
        words.add(new Word("mustard yellow","chiwiite",R.drawable.color_mustard_yellow,R.raw.color_mustard_yellow));

        WordApter store = new WordApter(this,words);
        ListView liststore = (ListView)findViewById(R.id.listColor);
        liststore.setAdapter(store);

        liststore.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mediaRelease();
                Word nowWord = words.get(position);
                int result = mAudioManager.requestAudioFocus(changeListener,AudioManager.STREAM_MUSIC,AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);
                if(result == AUDIOFOCUS_REQUEST_GRANTED)
                {mplayer = MediaPlayer.create(ColorActivity.this,nowWord.getmAudio_item());
                    mplayer.start();}
                mplayer.setOnCompletionListener(onCompletionListener);
            }
        });


    }

    @Override
    protected void onPause() {
        super.onPause();
        mediaRelease();
    }

    public void mediaRelease(){
        if(mplayer!=null)
            mplayer.release();
        mplayer = null;
        mAudioManager.abandonAudioFocus(changeListener);
    }

}

