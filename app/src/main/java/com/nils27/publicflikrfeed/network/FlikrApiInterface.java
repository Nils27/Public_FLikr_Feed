package com.nils27.publicflikrfeed.network;

import com.nils27.publicflikrfeed.model.Example;

import retrofit2.Call;
import retrofit2.http.GET;

public interface FlikrApiInterface {
    @GET("photos_public.gne?format=json&nojsoncallback=1")
        //Call<String> getPublicImagesJson();
    Call<Example> getPublicImagesJson();

}
