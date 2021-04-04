package net.etna.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.snackbar.Snackbar;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MenuPage extends AppCompatActivity {

    private Retrofit retrofit;
    private MenuApi menuApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_page);

        this.configureRetrofitMenu();
        String menuId = getIntent().getStringExtra("menuId");
        String restaurantId = getIntent().getStringExtra("restaurantId");
        Button delMenu = (Button) findViewById(R.id.delMenu);
        delMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                menuApi.deleteMenu(menuId).enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        if (response.code() == 200){
                            startActivityMenuList(restaurantId);
                        } else {
                            Snackbar.make(v, "Message from API: " + response, Snackbar.LENGTH_LONG).show();
                        }

                    }
                    @Override
                    public void onFailure(Call<String> call, Throwable t) {
                        Snackbar.make(v, "No response from API", Snackbar.LENGTH_LONG).show();
                    }
                });
                startActivityMenuList(restaurantId);
            }
        });
        Button updateRestaurant = (Button) findViewById(R.id.updateMenu);
        updateRestaurant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){}
        });

    }
    private void startActivityMenuList(String restaurantId){
        Intent intent = new Intent(this,MenuRestaurant.class);
        intent.putExtra("restId",restaurantId);
        startActivity(intent);
    }
    private void configureRetrofitMenu() {
        Gson gson = new GsonBuilder().setLenient().create();
        retrofit = new Retrofit.Builder().baseUrl(address.ip+"/api/")
                .addConverterFactory(GsonConverterFactory.create(gson)).build();
        menuApi = retrofit.create(MenuApi.class);
    }
}