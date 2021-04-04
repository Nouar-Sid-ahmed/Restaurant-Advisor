package net.etna.myapplication;

import com.google.gson.JsonObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface RestaurantApi {

    @GET("restaurants")
    Call<List<Restaurants>> getRestaurants();

    @POST("restaurants")
    Call<JsonObject> postRestaurant(@Body Restaurants restaurants);

    @GET("restaurants/{id}")
    Call<Restaurants> getByIdRestaurants(@Path("id") String id);

    @PUT("restaurant/{id}")
    Call<Restaurants> updateRestaurants(@Path("id") String id, @Body Restaurants restaurants);

    @DELETE("restaurant/{id}")
    Call<String> deleteRestaurants(@Path("id") String id);
}
