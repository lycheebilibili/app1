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

public class PhraseActivity extends AppCompatActivity {
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
        setContentView(R.layout.activity_phrase);
        mAudioManager= (AudioManager)getSystemService(Context.AUDIO_SERVICE);
        final ArrayList<Word> words = new ArrayList<Word>();

        words.add(new Word("Where are you going?","minto wuksus",R.raw.phrase_where_are_you_going));
        words.add(new Word("What is your name?","tinne oyaase'ne",R.raw.phrase_what_is_your_name));
        words.add(new Word("My name is...","oyaasel",R.raw.phrase_my_name_is));
        words.add(new Word("How are you feeling?","michekses?",R.raw.phrase_how_are_you_feeling));
        words.add(new Word("I'm feeling good.","kuchi achit",R.raw.phrase_im_feeling_good));
        words.add(new Word("Are you coming?","eenes'aa",R.raw.phrase_are_you_coming));
        words.add(new Word("Yes,I'm coming.","hee'eenem",R.raw.phrase_yes_im_coming));
        words.add(new Word("I'm coming.","eenem",R.raw.phrase_im_coming));
        words.add(new Word("Let's go.","yoowutis",R.raw.phrase_lets_go));
        words.add(new Word("Come here.","enni'nem",R.raw.phrase_come_here));

        WordApter store = new WordApter(this,words);
        ListView liststore = (ListView)findViewById(R.id.listPhrase);
        liststore.setAdapter(store);

        liststore.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mediaRelease();
                Word nowWord = words.get(position);
                int result = mAudioManager.requestAudioFocus(changeListener,AudioManager.STREAM_MUSIC,AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);
                if(result == AUDIOFOCUS_REQUEST_GRANTED)
                {mplayer = MediaPlayer.create(PhraseActivity.this,nowWord.getmAudio_item());
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

