package com.example.android.courtcounterdatabase;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.example.android.courtcounterdatabase.data.GameListContract;
import com.example.android.courtcounterdatabase.data.GameListDbHelper;

public class GameListActivity extends AppCompatActivity {
    private SQLiteDatabase mDdatabase;
    public CustomAdapter mAdapter;
    private RecyclerView recyclerView;
    public ScoreUpdateActivity scoreUpdateActivity;
    private final static String LOG_TAG = FinalScoreActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.v(LOG_TAG, "Game List Activity created");
        setContentView(R.layout.activity_game_list);

        recyclerView = (RecyclerView) findViewById(R.id.rec_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));



        GameListDbHelper dbHelper = new GameListDbHelper(this);
        mDdatabase = dbHelper.getReadableDatabase();



        Cursor cursor = getAllGames();
        // Keep a reference to the mDb until paused or killed. Get a writable database
        // because you will be adding restaurant customers



        mAdapter = new CustomAdapter(this, cursor);

        //Cursor cursor = scoreUpdateActivity.getAllGames();

        // Create an adapter for that cursor to display the data
        //mAdapter = new CustomAdapter(this, cursor);

        recyclerView.setAdapter(mAdapter);

    }

    public void newGame1 (View view){

        Intent intent = new Intent (this, MainActivity.class);
        startActivity(intent);
    }

    public void swappingCursor(){
        mAdapter.swapCursor(getAllGames());
    }



    public Cursor getAllGames() {               // TODO try putting this method in the GameListActivity
        return mDdatabase.query(
                GameListContract.GameListEntry.TABLE_NAME,
                null, // Column
                null, // Where clause
                null, // Arguments
                null, // Group by
                null, // having
                GameListContract.GameListEntry.COLUMN_TIMESTAMP // Sort_order
        );
    }
}
