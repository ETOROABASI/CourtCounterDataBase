package com.example.android.courtcounterdatabase;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

public class GameListActivity extends AppCompatActivity {

    private CustomAdapter mAdapter;
    private RecyclerView recyclerView;
    private ScoreUpdateActivity scoreUpdateActivity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_list);

        recyclerView = (RecyclerView) findViewById(R.id.rec_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        Cursor cursor = scoreUpdateActivity.getAllGames();

        // Create an adapter for that cursor to display the data
        //mAdapter = new CustomAdapter(this, cursor);

        recyclerView.setAdapter(mAdapter);

    }

    public void newGame1 (View view){

        Intent intent = new Intent (this, MainActivity.class);
        startActivity(intent);
    }
}
