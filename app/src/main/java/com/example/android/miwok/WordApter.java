package com.example.android.miwok;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.ArrayList;

public class WordApter extends ArrayAdapter<Word> {
    public WordApter(Context context, ArrayList<Word> word) {
        super(context, 0, word);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        // Check if the existing view is being reused, otherwise inflate the view
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);
        }
        Word currentWord = getItem(position);
        if (currentWord.getIfHasImage() == 1) {

            TextView eng = (TextView) listItemView.findViewById(R.id.EnglishItem);
            TextView miw = (TextView) listItemView.findViewById(R.id.MiwokItem);
            ImageView ima = (ImageView) listItemView.findViewById(R.id.showing);
            ima.setImageResource(currentWord.getmImage_item());
            eng.setText(currentWord.getmEnglish_item());
            miw.setText(currentWord.getmMiwok_item());
            ima.setVisibility(View.VISIBLE);
            return listItemView;
        }
        else   {
            TextView eng = (TextView) listItemView.findViewById(R.id.EnglishItem);
            TextView miw = (TextView) listItemView.findViewById(R.id.MiwokItem);
            ImageView ima = (ImageView) listItemView.findViewById(R.id.showing);
            eng.setText(currentWord.getmEnglish_item());
            miw.setText(currentWord.getmMiwok_item());
            ima.setVisibility(View.GONE);
            return listItemView;

        }
    }
}

