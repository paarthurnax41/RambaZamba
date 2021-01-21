package com.example.rambazamba;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class SettingActivity extends AppCompatActivity {

    Spinner typeSpinner;
    Spinner participantSpinner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        typeSpinner = findViewById(R.id.spinnerType);
        participantSpinner = findViewById(R.id.spinnerParticipant);

        ArrayAdapter<CharSequence> adapterType = ArrayAdapter.createFromResource(this,
                R.array.type_array, android.R.layout.simple_spinner_item);
        ArrayAdapter<CharSequence> adapterParticipant = ArrayAdapter.createFromResource(this,
                R.array.participant_array, android.R.layout.simple_spinner_item);

// Specify the layout to use when the list of choices appears
        adapterParticipant.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapterType.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        typeSpinner.setAdapter(adapterType);
        participantSpinner.setAdapter(adapterParticipant);

    }

}