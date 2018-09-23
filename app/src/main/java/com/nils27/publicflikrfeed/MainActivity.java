package com.nils27.publicflikrfeed;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}


    /*
    TODO - 1 - create the skeleton
    TODO - 2 - Create the POJOs
    TODO - 3 - implement retrofit to get the data
    TODO - 4 - implement glide for the images
    TODO - 5 - show the gallery/adapter
    TODO - 6 - store the json response string data (sharedPrefs?)
        TODO - 6b - test if online - else use last saved response - sharedPrefs
    TODO - 7 - testing
     */
