package com.example.android.miwok;

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

public class NumberActivity extends AppCompatActivity {
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
        setContentView(R.layout.activity_number);
        mAudioManager= (AudioManager)getSystemService(Context.AUDIO_SERVICE);
        final ArrayList<Word> words = new ArrayList<Word>();

        words.add(new Word("one","lutti",R.drawable.number_one,R.raw.number_one));
        words.add(new Word("two","otiiko",R.drawable.number_two,R.raw.number_two));
        words.add(new Word("three","tolookosu",R.drawable.number_three,R.raw.number_three));
        words.add(new Word("four","oyyisa",R.drawable.number_four,R.raw.number_four));
        words.add(new Word("five","massokka",R.drawable.number_five,R.raw.number_five));
        words.add(new Word("six","temmokka",R.drawable.number_six,R.raw.number_six));
        words.add(new Word("seven","kenekaku",R.drawable.number_seven,R.raw.number_seven));
        words.add(new Word("eight","kawinta",R.drawable.number_eight,R.raw.number_eight));
        words.add(new Word("nine","wo'e",R.drawable.number_nine,R.raw.number_nine));
        words.add(new Word("ten","na'aacha",R.drawable.number_ten,R.raw.number_ten));
        WordApter store = new WordApter(this,words);
        ListView liststore = (ListView)findViewById(R.id.listNumber);
        liststore.setAdapter(store);

        liststore.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mediaRelease();
                Word nowWord = words.get(position);
                int result = mAudioManager.requestAudioFocus(changeListener,AudioManager.STREAM_MUSIC,AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);
                if(result == AUDIOFOCUS_REQUEST_GRANTED)
                {mplayer = MediaPlayer.create(NumberActivity.this,nowWord.getmAudio_item());
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


