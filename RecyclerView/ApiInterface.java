package com.blogcahti.recyclerview;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {

    @GET("getlocation.php")
    Call<List<Location>> getLocation(
            @Query("item_type") String item_type,
            @Query("key") String keyword
    );
}
