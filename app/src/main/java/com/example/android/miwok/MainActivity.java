/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.android.miwok;

import android.app.Activity;
import  android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: 18-5-29 我的天啊这就是todo 

        // Set the content of the activity to use the activity_main.xml layout file
        setContentView(R.layout.activity_main);

        TextView phrasesOp = (TextView) findViewById(R.id.phrases);
        phrasesOp.setOnClickListener(new View.OnClickListener() {
            // The code in this method will be executed when the numbers View is clicked on.
            @Override
            public void onClick(View view) {
                Intent startPhrases = new Intent(MainActivity.this,PhraseActivity.class);
                startActivity(startPhrases) ;

            }
        });
        TextView familyOp = (TextView) findViewById(R.id.family);
        familyOp.setOnClickListener(new View.OnClickListener() {
            // The code in this method will be executed when the numbers View is clicked on.
            @Override
            public void onClick(View view) {
                Intent startFamily = new Intent(MainActivity.this,FamilyActivity.class);
                startActivity(startFamily) ;


            }
        });
        TextView numberOp = (TextView) findViewById(R.id.numbers);
        numberOp.setOnClickListener(new View.OnClickListener() {
            // The code in this method will be executed when the numbers View is clicked on.
            @Override
            public void onClick(View view) {
                Intent startNumber = new Intent(MainActivity.this,NumberActivity.class);
                startActivity(startNumber) ;


            }
        });
        TextView colorsOp = (TextView) findViewById(R.id.colors);
        colorsOp.setOnClickListener(new View.OnClickListener() {
            // The code in this method will be executed when the numbers View is clicked on.
            @Override
            public void onClick(View view) {
                Intent startColors = new Intent(MainActivity.this,ColorActivity.class);
                startActivity(startColors) ;


            }
        });

    }



}
