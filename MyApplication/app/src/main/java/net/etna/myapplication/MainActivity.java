package net.etna.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

import android.util.Log;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private Retrofit retrofit;
    private UserApi Login;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent restaurantsView = new Intent(this, MainActivity2.class);

        SharedPreferences pref = getSharedPreferences("MyPref", 0);
        SharedPreferences.Editor editor = pref.edit();
        if (pref.contains("isLogin")){
            startActivity(restaurantsView);
        }

        this.configureRetrofit();
        Button logMe = (Button) findViewById(R.id.postLogin);
        logMe.setOnClickListener(new View.OnClickListener(){
           @Override
           public void onClick(View v) {
               EditText pEmail = (EditText) findViewById (R.id.postEmail);
               EditText pPassword = (EditText) findViewById (R.id.postPassword);
               Login login = new Login();
               login.setEmail(pEmail.getText().toString());
               login.setPassword(pPassword.getText().toString());
               Login.postLogin(login).enqueue(new Callback<JsonObject>() {
                   @Override
                   public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                       if(response.code() == 200){
                           Gson gson = new Gson();
                           JsonObject json = response.body();
                           User user = gson.fromJson(json, User.class);
                           Snackbar.make(v,"Happy to see you again " + user.getUsername(), Snackbar.LENGTH_LONG)
                                   .show();
                           editor.putBoolean("isLogin",true);
                           editor.commit();
                           startActivity(restaurantsView);
                       } else {
                           Snackbar.make(v,"Message from API: " + response.message(), Snackbar.LENGTH_LONG)
                                   .show();
                       }
                   }
                   @Override
                   public void onFailure(Call<JsonObject> call, Throwable t) {
                       Snackbar.make(v,"No response from API", Snackbar.LENGTH_LONG)
                               .show();
                   }
               });
           }
        });

        Intent setNewRestaurantView = new Intent(this, addNewRestaurant.class);
        Button setNewRestaurant = (Button) findViewById(R.id.setNewRestaurant);
        setNewRestaurant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){startActivity(setNewRestaurantView);}
        });

        Button button = (Button) findViewById(R.id.move);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){startActivity(restaurantsView);}
        });

        Intent register = new Intent(this, Register.class);
        Button registerBtn = (Button) findViewById(R.id.registerBtn);
        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){startActivity(register);}
        });

    }
    private void configureRetrofit() {
        Gson gson = new GsonBuilder().setLenient().create();
        retrofit = new Retrofit.Builder().baseUrl(address.ip+"/api/")
                .addConverterFactory(GsonConverterFactory.create(gson)).build();
        Login = retrofit.create(UserApi.class);
    }
}