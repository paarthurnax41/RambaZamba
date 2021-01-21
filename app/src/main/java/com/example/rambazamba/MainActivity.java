package com.example.rambazamba;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.rambazamba.bored.Bored;
import com.example.rambazamba.bored.BoredApi;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {


    private DataBaseHelper dataBaseHelper;

    FloatingActionButton generateButton;
    FloatingActionButton favoriteButton;
    TextView activityTxt;
    Menu menu;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        dataBaseHelper = new DataBaseHelper(this);

        favoriteButton = findViewById(R.id.addFavoriteButton);
        generateButton = findViewById(R.id.generateActivityButton);
        dataBaseHelper.open();

        activityTxt = findViewById(R.id.TextViewActivity);


    }


    public void onFavoriteButtonClicked(View view) {
        // Intent intent = new Intent();

        if (dataBaseHelper.getAllActivities().contains(activityTxt.getText().toString())) {

            // intent.putExtra("activity",actText);

            Toast.makeText(this, activityTxt.getText().toString() + " is already in Favourites", Toast.LENGTH_LONG).show();
        } else {
            String actText = activityTxt.getText().toString();
            dataBaseHelper.add(actText);
        }
    }

    public void generateActivityButton(View view) {

        BoredApi.getApi().getRandomActivity().enqueue(new Callback<Bored>() {
            @Override
            public void onResponse(Call<Bored> call, Response<Bored> response) {
                Bored boredApi = response.body();
                Log.d("ESFUNKTIONIERT", boredApi.getActivity());
                String activity = boredApi.getActivity();

                activityTxt.setText(activity);

                RotateAnimation rotate = new RotateAnimation(0, 180,
                        Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
                rotate.setDuration(500);
                rotate.setInterpolator(new LinearInterpolator());
                generateButton.startAnimation(rotate);
            }

            @Override
            public void onFailure(Call<Bored> call, Throwable t) {

                Toast.makeText(getBaseContext(),"Generating Activity failed (Check Internet Connection)",Toast.LENGTH_LONG).show();
                Log.e("WARUM-AMK", t.toString());
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbarmenu, menu);
        return true;
    }

    public void FavoritesClicked(MenuItem item) {
        Intent intent = new Intent(this, FavoritesActivity.class);
        startActivity(intent);

    }

    public void SettingsClicked(MenuItem item) {
        Intent intent = new Intent(this,SettingActivity.class);
        startActivity(intent);
    }
}