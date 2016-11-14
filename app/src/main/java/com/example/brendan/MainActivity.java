package com.example.brendan;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.android.gms.samples.vision.face.googlyeyes.R;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getActionBar().setTitle("eye Spy");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        View tutorialButton = findViewById(R.id.tutorialButton);
        tutorialButton.setOnClickListener(this);

        View optionButton = findViewById(R.id.optionButton);
        optionButton.setOnClickListener(this);

        View beginButton = findViewById(R.id.beginButton);
        beginButton.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.optionButton:
                Log.i("opt","opt pressed");
                Intent intentOpt = new Intent(this, OptionMenu.class);
                startActivity(intentOpt);
                break;
            case R.id.tutorialButton:
                Log.i("tut","tutorial pressed");
                Intent intent = new Intent(this, com.example.brendan.TutorialMenu.class);
                startActivity(intent);
                break;
            case R.id.beginButton:

                break;

        }
    }




}
