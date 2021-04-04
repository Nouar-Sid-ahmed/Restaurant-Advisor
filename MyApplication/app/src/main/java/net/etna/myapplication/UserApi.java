package net.etna.myapplication;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.internal.$Gson$Preconditions;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface UserApi {

    @GET("users")
    Call<List<User>> getUsers();

    @POST("auth")
    Call<JsonObject> postLogin(@Body Login login);

    @POST("register")
    Call<JsonObject> postRegister(@Body User user);

}
