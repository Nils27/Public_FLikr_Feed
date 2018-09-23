package com.nils27.publicflikrfeed;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;

import com.nils27.publicflikrfeed.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        binding.rvMain.setLayoutManager(new LinearLayoutManager(this)); //todo Maybe change this to grid layout later

        binding.rvMain.setAdapter(null); //todo set adapter


    }


    private void goToDetailsView() {
            Intent detailIntent = new Intent(this, DetailsActivity.class);
            //detailIntent.putExtra() todo will prob need to send over the pojo of selected image (parcelable)
            startActivity(detailIntent);
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
