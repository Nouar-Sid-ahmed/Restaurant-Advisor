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

public interface MenuApi {
    @GET("menu")
    Call<List<Menu>> getAllMenu();

    @GET("menu/{id}")
    Call<Menu> getMenuById(@Path("id") String id);

    @GET("restaurants/{id}/menus")
    Call<List<Menu>> getAllMenuOf(@Path("id") String id);

    @POST("restaurants/{id}/menus")
    Call<JsonObject> postMenu(@Path("id") String id,@Body Menu menu);

    @PUT("menus/{id}")
    Call<JsonObject> updateMenu(@Path("id") String id,@Body Menu menu);

    @DELETE("menus/{id}")
    Call<String> deleteMenu(@Path("id") String id);
}
