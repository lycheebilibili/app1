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

public class FamilyActivity extends AppCompatActivity {
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
        setContentView(R.layout.activity_family);
        mAudioManager= (AudioManager)getSystemService(Context.AUDIO_SERVICE);
        final ArrayList<Word> words = new ArrayList<Word>();

        words.add(new Word("father","epe",R.drawable.family_father,R.raw.family_father));
        words.add(new Word("mother","eta",R.drawable.family_mother,R.raw.family_mother));
        words.add(new Word("son","angsi",R.drawable.family_son,R.raw.family_son));
        words.add(new Word("daughter","tune",R.drawable.family_daughter,R.raw.family_daughter));
        words.add(new Word("older brother","taachi",R.drawable.family_older_brother,R.raw.family_older_brother));
        words.add(new Word("youner brother","chalitti",R.drawable.family_younger_brother,R.raw.family_younger_brother));
        words.add(new Word("older sister","tete",R.drawable.family_older_sister,R.raw.family_older_sister));
        words.add(new Word("younger sister","kolliti",R.drawable.family_younger_sister,R.raw.family_younger_sister));
        words.add(new Word("grandmother","ama",R.drawable.family_grandmother,R.raw.family_grandmother));
        words.add(new Word("grandfather","paapa",R.drawable.family_grandfather,R.raw.family_grandfather));
        WordApter store = new WordApter(this,words);
        ListView liststore = (ListView)findViewById(R.id.listFamily);
        liststore.setAdapter(store);

        liststore.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mediaRelease();
                Word nowWord = words.get(position);
                int result = mAudioManager.requestAudioFocus(changeListener,AudioManager.STREAM_MUSIC,AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);
                if(result == AUDIOFOCUS_REQUEST_GRANTED)
                {mplayer = MediaPlayer.create(FamilyActivity.this,nowWord.getmAudio_item());
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


