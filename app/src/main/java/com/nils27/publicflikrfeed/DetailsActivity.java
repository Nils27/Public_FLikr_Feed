package com.nils27.publicflikrfeed;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.nils27.publicflikrfeed.databinding.ActivityDetailsBinding;
import com.nils27.publicflikrfeed.databinding.ActivityMainBinding;
import com.nils27.publicflikrfeed.model.Item;
import com.nils27.publicflikrfeed.model.Media;

public class DetailsActivity extends AppCompatActivity {

    private static final String TAG = DetailsActivity.class.getSimpleName();

    private Item mItemPassed;
    private Media mMediaPassed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_details);
        ActivityDetailsBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_details);

        if (savedInstanceState!=null) {
            // get data from bundle

        } else {
            Bundle data = getIntent().getExtras();
            mItemPassed = data.getParcelable(MainActivity.ITEM_PASSED_KEY);
            mMediaPassed = data.getParcelable(MainActivity.MEDIA_PASSED_KEY);
        }

        if (mItemPassed !=null) {
            binding.setItemPassedData(mItemPassed);
            binding.setMediaPassedData(mMediaPassed);
        } else {
            Log.d(TAG, "onCreate: No Item data found");
        }

    }
}


//TODO - back navigation