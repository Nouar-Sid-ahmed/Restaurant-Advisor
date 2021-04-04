package net.etna.myapplication;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class addNewRestaurant extends AppCompatActivity {

    private Retrofit retrofit;

    private RestaurantApi restaurantApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_restaurant);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        this.configureRetrofit();

        Button addNewRestaurant = findViewById(R.id.addNewRestaurantBtn);
        addNewRestaurant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText pName = (EditText) findViewById(R.id.newRestaurantName);
                EditText pDescription = (EditText) findViewById(R.id.newRestaurantDescription);
                EditText pGrade = (EditText) findViewById(R.id.newRestaurantGrade);
                EditText pHours = (EditText) findViewById(R.id.newRestaurantHours);
                EditText pLocalization = (EditText) findViewById(R.id.newRestaurantLocalization);
                EditText pPhone = (EditText) findViewById(R.id.newRestaurantPhone);
                EditText pWebsite = (EditText) findViewById(R.id.newRestaurantWebsite);
                Restaurants restaurants = new Restaurants();
                restaurants.setRestaurantName(pName.getText().toString());
                restaurants.setDescription(pDescription.getText().toString());
                restaurants.setGrade(Float.valueOf(pGrade.getText().toString()));
                restaurants.setHours(pHours.getText().toString());
                restaurants.setLocalization(pLocalization.getText().toString());
                restaurants.setPhone_number(pPhone.getText().toString());
                restaurants.setWebsite(pWebsite.getText().toString());
                restaurantApi.postRestaurant(restaurants).enqueue(new Callback<JsonObject>() {
                    @Override
                    public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                        if (response.code() == 201) {
                            Gson gson = new Gson();
                            JsonObject json = response.body();
                            Restaurants restaurants = gson.fromJson(json, Restaurants.class);
                            Snackbar.make(v, "You successfully added the restaurant " + restaurants.getRestaurantName(), Snackbar.LENGTH_LONG).show();
                        } else {
                            Snackbar.make(v, "Message from API: " + response.message() + " status : "+ response.code(), Snackbar.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<JsonObject> call, Throwable t) {
                        Snackbar.make(v, "No response from API", Snackbar.LENGTH_LONG).show();
                    }
                });
            }
        });
    }
    private void configureRetrofit() {
        Gson gson = new GsonBuilder().setLenient().create();
        retrofit = new Retrofit.Builder().baseUrl(address.ip+"/api/")
                .addConverterFactory(GsonConverterFactory.create(gson)).build();
        restaurantApi = retrofit.create(RestaurantApi.class);
    }
}