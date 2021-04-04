package net.etna.myapplication;

import android.content.Intent;
import android.os.Bundle;

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

public class Register extends AppCompatActivity {

    private UserApi User;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registeur);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        this.configureRetrofit();

        Intent restaurantsView = new Intent(this, MainActivity2.class);
        Button postLogin = (Button) findViewById(R.id.registerNew);
        postLogin.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                EditText pName = (EditText) findViewById(R.id.newUserName);
                EditText pFirstname = (EditText) findViewById(R.id.newUserFirstname);
                EditText pUsername = (EditText) findViewById(R.id.newUserUsername);
                EditText pAge = (EditText) findViewById(R.id.newUserAge);
                EditText pEmail = (EditText) findViewById(R.id.newUserEmail);
                EditText pPassword = (EditText) findViewById(R.id.newUserPassword);
                    User user = new User();
                    user.setName(pName.getText().toString());
                    user.setFirstname(pFirstname.getText().toString());
                    user.setUsername(pUsername.getText().toString());
                    user.setEmail(pEmail.getText().toString());
                    user.setPassword(pPassword.getText().toString());
                    user.setAge(Integer.valueOf(pAge.getText().toString()));
                    User.postRegister(user).enqueue(new Callback<JsonObject>() {
                        @Override
                        public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                            if (response.code() == 200) {
                                Gson gson = new Gson();
                                JsonObject json = response.body();
                                User user = gson.fromJson(json, User.class);
                                Snackbar.make(v, "Welcome " + user.getUsername(), Snackbar.LENGTH_LONG).show();
                                startActivity(restaurantsView);
                            } else {
                                Snackbar.make(v, "Message from API: " + response.message(), Snackbar.LENGTH_LONG).show();
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
        Retrofit retrofit = new Retrofit.Builder().baseUrl(address.ip+"/api/")
                .addConverterFactory(GsonConverterFactory.create(gson)).build();
        User = retrofit.create(UserApi.class);
    }
}