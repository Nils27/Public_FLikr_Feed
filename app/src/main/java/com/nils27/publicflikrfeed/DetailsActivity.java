package com.nils27.publicflikrfeed;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.annotation.VisibleForTesting;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.nils27.publicflikrfeed.databinding.ActivityDetailsBinding;
import com.nils27.publicflikrfeed.model.Item;
import com.nils27.publicflikrfeed.model.Media;

//for testing purposes
import android.support.test.espresso.IdlingResource;


public class DetailsActivity extends AppCompatActivity {

    private static final String TAG = DetailsActivity.class.getSimpleName();

    private static final String ITEM_KEY = "Item_Key";
    private static final String MEDIA_KEY = "Media_Key";


    private Item mItemPassed;
    private Media mMediaPassed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_details);
        ActivityDetailsBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_details);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle(R.string.details_title);
        }

        SimpleIdlingResource.increment(); //tell Expresso to wait

        if (savedInstanceState!=null) {
            // get data from bundle
            mItemPassed = savedInstanceState.getParcelable(ITEM_KEY);
            mMediaPassed = savedInstanceState.getParcelable(MEDIA_KEY);
        } else {
            Bundle data = getIntent().getExtras();
            mItemPassed = data.getParcelable(MainActivity.ITEM_PASSED_KEY);
            mMediaPassed = data.getParcelable(MainActivity.MEDIA_PASSED_KEY);
        }

        if (mItemPassed !=null) {
            binding.setItemPassedData(mItemPassed);
            //actionBar.setTitle(mItemPassed.getTitle());
        } else {
            Log.d(TAG, "onCreate: No Item data found");
        }
        if (mMediaPassed !=null) {
            binding.setMediaPassedData(mMediaPassed);
        } else {
            Log.d(TAG, "onCreate: No Media data found");
        }

        SimpleIdlingResource.decrement(); //Expresso can now resume as data passed has been used
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        Log.i(TAG, "onSaveInstanceState: called");
        outState.putParcelable(ITEM_KEY, mItemPassed);
        outState.putParcelable(MEDIA_KEY, mMediaPassed);


        super.onSaveInstanceState(outState);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    @VisibleForTesting
    @NonNull
    public IdlingResource getIdlingResource() {
        return SimpleIdlingResource.getIdlingResource();
    }

}
