package com.example.android.miwok;

public class Word {
    private String mEnglish_item;
    private String mMiwok_item;
    private int mImage_item;
    private int mAudio_item;
    private int ifHasImage;
    public Word(String english_item,String miwok_item,int image_item,int audio_item){
        mEnglish_item = english_item;
        mMiwok_item = miwok_item;
        mImage_item = image_item;
        mAudio_item = audio_item;
        ifHasImage = 1;
    }
    public Word(String english_item,String miwok_item,int audio_item){
        mEnglish_item = english_item;
        mMiwok_item = miwok_item;
        mAudio_item = audio_item;
        ifHasImage = 0;
    }
    public String getmEnglish_item (){
        return mEnglish_item;
    }

    public String getmMiwok_item() {
        return mMiwok_item;
    }

    public int getmImage_item(){return  mImage_item;}

    public int getmAudio_item(){return  mAudio_item;}

    public int getIfHasImage(){ return ifHasImage;}

    @Override
    public String toString() {
        return "Word{" +
                "mEnglish_item='" + mEnglish_item + '\'' +
                ", mMiwok_item='" + mMiwok_item + '\'' +
                ", mImage_item=" + mImage_item +
                ", mAudio_item=" + mAudio_item +
                ", ifHasImage=" + ifHasImage +
                '}';
    }
}


