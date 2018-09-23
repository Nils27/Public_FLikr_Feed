package com.nils27.publicflikrfeed;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;

import com.nils27.publicflikrfeed.adapter.PublicFeedAdapter;
import com.nils27.publicflikrfeed.databinding.ActivityMainBinding;
import com.nils27.publicflikrfeed.model.Item;

import java.util.List;

public class MainActivity extends AppCompatActivity implements PublicFeedAdapter.ImageOnClickHandler {

    private static final String TAG = MainActivity.class.getSimpleName();

    private PublicFeedAdapter mAdapter;
    private List<Item> mItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        binding.rvMain.setLayoutManager(new LinearLayoutManager(this)); //todo Maybe change this to grid layout later

        mAdapter = new PublicFeedAdapter(this, null, this);
        binding.rvMain.setAdapter(null); //todo set adapter null - change data after network call retrieves data

    }


    private void goToDetailsView(Item item) {
            Intent detailIntent = new Intent(this, DetailsActivity.class);
            //detailIntent.putExtra("Item Passed", item) todo will prob need to send over the pojo of selected image (parcelable)
            startActivity(detailIntent);
    }

    @Override
    public void onImageClick(int position, String title) {
        Item item;

        if (mItems != null) {
            item = mItems.get(position);
            goToDetailsView(item);
        }
    }
}


    /*
    Done - 1 - create the skeleton
    Done - 2 - Create the POJOs
    TODO - 3 - implement retrofit to get the data
    Done - 4 - implement glide for the images
    TODO - 5 - show the gallery/adapter
    TODO - 6 - store the json response string data (sharedPrefs?)
        TODO - 6b - test if online - else use last saved response - sharedPrefs
    TODO - 7 - testing
     */
