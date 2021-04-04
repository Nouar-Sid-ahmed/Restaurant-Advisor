package net.etna.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.google.android.material.snackbar.Snackbar;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MenuRestaurant extends AppCompatActivity {

    private MenuApi menuApi;
    private static final String TAG = "Restaurant_advisor";
    private Retrofit retrofit;
    private List<Menu> listMenu ;
    private MenuAdapter menuAdapter;
    private ListView listView;
    private RestaurantApi restaurantApi;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_restaurant);

        String restaurantId = getIntent().getStringExtra("restId");

        listMenu = new ArrayList<>();

        this.listView = (ListView) findViewById(R.id.simpleListMenu);
        this.configureRetrofitMenu();


        this.menuAdapter = new MenuAdapter(getApplicationContext(),listMenu);
        this.listView.setAdapter(menuAdapter);

        menuApi.getAllMenuOf(restaurantId).enqueue(new Callback<List<Menu>>() {
            @Override
            public void onResponse(Call<List<Menu>> call, Response<List<Menu>> response) {
                Log.d(TAG, "onResponse:");

                List<Menu> menuList = response.body();
                if (menuList != null) {
                    for (Menu menu : menuList) {
                        Log.d(TAG, "Menu: " + menu.getMenuName());
                        listMenu.add(menu);
                    }
                    menuAdapter.notifyDataSetChanged();
                } else {
                    Snackbar.make(listView, "Message from API: " + response.message(), Snackbar.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<List<Menu>> call, Throwable t) {
                Snackbar.make(listView, "No response from API", Snackbar.LENGTH_LONG).show();
            }
        });
        /*listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Restaurants restaurant1 = listMenu.get(position);
            }
        });*/

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
               Menu menu = listMenu.get(position);
                startActivityMenu(menu.getMenuId().toString(), restaurantId);
            }
        });
        Button setNewMenu = (Button) findViewById(R.id.addMenu);
        setNewMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){startActivityAddMenu(restaurantId);}
        });
        Button delRestaurant = (Button) findViewById(R.id.delRestaurant);
        this.configureRetrofitRestaurant();
        delRestaurant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                restaurantApi.deleteRestaurants(restaurantId).enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        if (response.code() == 200){
                            startActivityRestaurantList();
                        } else {
                            Snackbar.make(v, "Message from API: " + response, Snackbar.LENGTH_LONG).show();
                        }

                    }
                    @Override
                    public void onFailure(Call<String> call, Throwable t) {
                        Snackbar.make(v, "No response from API", Snackbar.LENGTH_LONG).show();
                    }
                });
                startActivityRestaurantList();
            }
        });
        Button updateRestaurant = (Button) findViewById(R.id.updateRestaurant);
        updateRestaurant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){startActivityUpdateRestaurant(restaurantId);}
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

    private void startActivityUpdateRestaurant(String restaurantId){
        Intent intent = new Intent(this,ActivityUpdateRestaurant.class);
        startActivity(intent);
    }
    private void startActivityRestaurantList(){
        Intent intent = new Intent(this,MainActivity2.class);
        startActivity(intent);
    }
    private void startActivityMenu(String menuId, String restaurantId){
        Intent intent = new Intent(this,MenuPage.class);
        intent.putExtra("menuId",menuId);
        intent.putExtra("restaurantId",restaurantId);
        startActivity(intent);
    }
    private void startActivityAddMenu(String restaurantId){
        Intent intent = new Intent(this,addNewMenu.class);
        intent.putExtra("restId",restaurantId);
        startActivity(intent);
    }
    private void configureRetrofitRestaurant() {
        Gson gson = new GsonBuilder().setLenient().create();
        retrofit = new Retrofit.Builder().baseUrl(address.ip+"/api/")
                .addConverterFactory(GsonConverterFactory.create(gson)).build();
        restaurantApi = retrofit.create(RestaurantApi.class);
    }
    private void configureRetrofitMenu() {
        Gson gson = new GsonBuilder().setLenient().create();
        retrofit = new Retrofit.Builder().baseUrl(address.ip+"/api/")
                .addConverterFactory(GsonConverterFactory.create(gson)).build();
        menuApi = retrofit.create(MenuApi.class);
    }
}