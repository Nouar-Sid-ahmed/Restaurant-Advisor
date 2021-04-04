package net.etna.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity2 extends AppCompatActivity {

    private RestaurantApi Restaurants;
    private static final String TAG = "Restaurant_advisor";
    private Retrofit retrofit;
    private List<Restaurants> listRestaurant ;
    private restaurantAdapter restaurantAdapter;
    private ListView listView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        listRestaurant = new ArrayList<>();

        this.listView = (ListView) findViewById(R.id.simpleListView);
        this.configureRetrofit();

        this.restaurantAdapter = new restaurantAdapter(getApplicationContext(),listRestaurant);
        this.listView.setAdapter(restaurantAdapter);

        Restaurants.getRestaurants().enqueue(new Callback<List<Restaurants>>() {
            @Override
            public void onResponse(Call<List<Restaurants>> call, Response<List<Restaurants>> response) {
                Log.d(TAG, "onResponse:");

                List<Restaurants> restaurantsList = response.body();
                if (restaurantsList != null) {
                    for (Restaurants restaurant : restaurantsList) {
                        Log.d(TAG, "restaurant: " + restaurant.getRestaurantName());
                        listRestaurant.add(restaurant);
                    }
                    restaurantAdapter.notifyDataSetChanged();
                } else {
                    Log.d(TAG, "onResponse: restaurants is empty");
                }
            }

            @Override
            public void onFailure(Call<List<Restaurants>> call, Throwable t) {
                Log.d(TAG, "onFailure:" + t.getMessage());
            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Restaurants restaurant1 = listRestaurant.get(position);
                startActivityMenu(restaurant1.getRestaurantId().toString());
            }
        });
        Button disconnection = (Button) findViewById(R.id.disconnection);
        Intent acceuil = new Intent(this, MainActivity.class);
        disconnection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                SharedPreferences pref = getSharedPreferences("MyPref", 0);
                SharedPreferences.Editor editor = pref.edit();
                if(pref.contains("isLogin")){
                    editor.remove("isLogin");
                    editor.commit();
                    startActivity(acceuil);
                }else {
                    Snackbar.make(v, "Your not connect", Snackbar.LENGTH_LONG).show();
                }
            }
        });
    }

    private void startActivityMenu(String restaurantId){
        Intent intent = new Intent(this,MenuRestaurant.class);
        intent.putExtra("restId",restaurantId);
        startActivity(intent);
    }
    private void configureRetrofit() {
        Gson gson = new GsonBuilder().setLenient().create();
        retrofit = new Retrofit.Builder().baseUrl(address.ip+"/api/")
                .addConverterFactory(GsonConverterFactory.create(gson)).build();
        Restaurants = retrofit.create(RestaurantApi.class);
    }

}
