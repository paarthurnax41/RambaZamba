package com.example.rambazamba;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.rambazamba.MainActivity;
import com.example.rambazamba.R;

public class ActivityStart extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.startscreenlayout);
    }

    public void onStartButtonClicked(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);


    }
}