package com.nils27.publicflikrfeed;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.widget.Toast;

import com.nils27.publicflikrfeed.adapter.PublicFeedAdapter;
import com.nils27.publicflikrfeed.databinding.ActivityMainBinding;
import com.nils27.publicflikrfeed.model.Example;
import com.nils27.publicflikrfeed.model.Item;
import com.nils27.publicflikrfeed.model.Media;
import com.nils27.publicflikrfeed.network.FlikrApiInterface;
import com.nils27.publicflikrfeed.network.RetroFitClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity implements PublicFeedAdapter.ImageOnClickHandler {

    private static final String TAG = MainActivity.class.getSimpleName();

    public static final String ITEM_PASSED_KEY = "Item Passed";
    public static final String MEDIA_PASSED_KEY = "Media Passed";

    private PublicFeedAdapter mAdapter;
    private List<Item> mItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        binding.rvMain.setLayoutManager(new LinearLayoutManager(this));

        mAdapter = new PublicFeedAdapter(this, null, this);
        binding.rvMain.setAdapter(mAdapter);

        //todo if no network show last saved data/response already in sharedPrefs? or error screen?


        if (savedInstanceState != null) {
            //data should already exist

        } else {
            //no previous data - fetch new data
            Retrofit retrofit = RetroFitClient.getClient();
            FlikrApiInterface client = retrofit.create(FlikrApiInterface.class);
            Call<Example> call = client.getPublicImagesJson();

            call.enqueue(new Callback<Example>() {
                @Override
                public void onResponse(Call<Example> call, Response<Example> response) {
                    Example resp = response.body(); //Data
                    if (resp==null) {
                        Log.i(TAG, "onResponse: response list is NULL!");
                    } else {
                        mItems = resp.getItems();
                        mAdapter.changeItemList(mItems);
                        // todo Store data into sharedPrefs?
                        Log.i(TAG, "onResponse: Public received and parsed");
                        Log.i(TAG, "onResponse: resp title - " + resp.getTitle());
                    }
                }

                @Override
                public void onFailure(Call<Example> call, Throwable t) {
                    Toast.makeText(getBaseContext(), "Error Retrieving The JSON Data", Toast.LENGTH_SHORT).show();
                    Log.e(TAG, "onFailure: Error getting the JSON image data");
                    Log.e(TAG, "onFailure: Call request - " + call.request().toString());
                    Log.e(TAG, "onFailure: Call URL request - " + call.request().url().toString());
                }
            });

        }

    }


    private void goToDetailsView(Item item) {
            Media media = item.getMedia();
            Intent detailIntent = new Intent(this, DetailsActivity.class);
            detailIntent.putExtra(ITEM_PASSED_KEY, item);
            detailIntent.putExtra(MEDIA_PASSED_KEY, media);
            startActivity(detailIntent);
    }

    @Override
    public void onImageClick(int position, String title) {
        Item item;
        if (mItems != null) {
            item = mItems.get(position);
            goToDetailsView(item);
        } else {
            Log.i(TAG, "onImageClick: No Item data on position - " + position);
        }
    }
}


    /*
    Done - 1 - create the skeleton
    Done - 2 - Create the POJOs
    Done - 3 - implement retrofit to get the data
    Done - 4 - implement glide for the images
    Done - 5 - show the gallery/adapter
    Done - 5b - show the details screen with all data
    TODO - 6 - handle conifguration changes (rotation)
    TODO - 7 - store the json response string data (sharedPrefs?)
        TODO - 6b - test if online - else use last saved response - sharedPrefs
    TODO - 7 - testing
     */
