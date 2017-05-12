package com.example.android.courtcounterdatabase;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {private EditText teamAEditText;
    private EditText teamBEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        teamAEditText = (EditText) findViewById(R.id.teamAName);
        teamBEditText = (EditText) findViewById(R.id.teamBName);
    }

    public void submit(View viewing){
        Intent intent = new Intent(this, ScoreUpdateActivity.class);

        Bundle bundle1 = new Bundle();
        bundle1.putString("teamA", String.valueOf(teamAEditText.getText()));
        bundle1.putString("teamB", String.valueOf(teamBEditText.getText()));
        intent.putExtras(bundle1);
        startActivity(intent);
    }
}
