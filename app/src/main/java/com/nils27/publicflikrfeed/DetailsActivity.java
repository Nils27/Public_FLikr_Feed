package com.nils27.publicflikrfeed;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.nils27.publicflikrfeed.databinding.ActivityDetailsBinding;
import com.nils27.publicflikrfeed.databinding.ActivityMainBinding;

public class DetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_details);
        ActivityDetailsBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_details);

    }
}
