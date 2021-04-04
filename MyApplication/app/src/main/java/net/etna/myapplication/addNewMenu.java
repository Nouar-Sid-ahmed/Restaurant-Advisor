package net.etna.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.snackbar.Snackbar;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class addNewMenu extends AppCompatActivity {

    private Retrofit retrofit;

    private MenuApi menuApi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_menu);

        this.configureRetrofit();
        String restaurantId = getIntent().getStringExtra("restId");

        Button addNewMenu = findViewById(R.id.addNewMenuBtn);
        addNewMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText pName = (EditText) findViewById(R.id.newMenuName);
                EditText pDescription = (EditText) findViewById(R.id.newMenuDescription);
                EditText pPrice = (EditText) findViewById(R.id.newMenuPrice);
                Menu menu = new Menu();
                menu.setMenuName(pName.getText().toString());
                menu.setMenuDescription(pDescription.getText().toString());
                menu.setPrice(Float.valueOf(pPrice.getText().toString()));

                menuApi.postMenu(restaurantId,menu).enqueue(new Callback<JsonObject>() {
                    @Override
                    public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                        if (response.code() == 201) {
                            Gson gson = new Gson();
                            JsonObject json = response.body();
                            Menu menu1 = gson.fromJson(json, Menu.class);
                            Snackbar.make(v, "You successfully added the menu " + menu1.getMenuName(), Snackbar.LENGTH_LONG).show();
                            startActivityMenu(restaurantId);
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
    private void startActivityMenu(String restaurantId){
        Intent intent = new Intent(this,MenuRestaurant.class);
        intent.putExtra("restId",restaurantId);
        startActivity(intent);
    }
    private void configureRetrofit() {
        Gson gson = new GsonBuilder().setLenient().create();
        retrofit = new Retrofit.Builder().baseUrl(address.ip+"/api/")
                .addConverterFactory(GsonConverterFactory.create(gson)).build();
        menuApi = retrofit.create(MenuApi.class);
    }
}