package com.nils27.publicflikrfeed;


import android.support.annotation.Nullable;
import android.support.test.espresso.IdlingResource;
import android.support.test.espresso.idling.CountingIdlingResource;

import com.bumptech.glide.request.ResourceCallback;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Created by Nilesh on 01/05/2018.
 */

public class SimpleIdlingResource {


    private static final String RESOURCE = "GLOBAL";

    private static CountingIdlingResource mCountingIdlingResource =
            new CountingIdlingResource(RESOURCE);

    public static void increment() {
        mCountingIdlingResource.increment();
    }

    public static void decrement() {
        mCountingIdlingResource.decrement();
    }

    public static IdlingResource getIdlingResource() {
        return mCountingIdlingResource;
    }

}
