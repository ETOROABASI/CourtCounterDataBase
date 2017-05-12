package com.example.android.courtcounterdatabase;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.android.courtcounterdatabase.data.GameListContract;
import com.example.android.courtcounterdatabase.data.GameListDbHelper;

public class FinalScoreActivity extends AppCompatActivity {

//    private CustomAdapter mAdapter;
//    private SQLiteDatabase mDb;
    private TextView teamAName;
    private TextView teamBName;
    private TextView teamAScore;
    private TextView teamBScore;
    private final static String LOG_TAG = FinalScoreActivity.class.getSimpleName();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final_score);
        Bundle bundle = this.getIntent().getExtras();


         teamAName = (TextView) findViewById(R.id.finalTeamAName);
        teamBName = (TextView) findViewById(R.id.finalTeamBName);
        teamAScore = (TextView) findViewById(R.id.finalTeamAScore);
        teamBScore = (TextView) findViewById(R.id.finalTeamBScore);


        String nameTeamA = bundle.getString("teamAName");
        String nameTeamB = bundle.getString("teamBName");
        String scoreTeamA = bundle.getString("teamAscore");
        String scoreTeamB = bundle.getString("teamBscore");


        teamAName.setText(nameTeamA);
        teamBName.setText(nameTeamB);
        teamAScore.setText(scoreTeamA);
        teamBScore.setText(scoreTeamB);

    }


    public void newGame(View v){

        // Intent intention = new Intent(this, Main2Activity.class);
        // startActivity(intention);

        startActivity(new Intent(getApplicationContext(),MainActivity.class));  // same as startActivity(new Intent (this, Main2Activity.class));

    }

    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu1, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id1 = item.getItemId();
        if (id1 == R.id.newGame) {

            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
        } else if (id1 == R.id.finalScore) {

            finish();
        }
        return super.onOptionsItemSelected(item);
    }

//
//    //DATABASE SEGMENT
//
//    private Cursor getAllGames() {
//        return mDb.query(
//                GameListContract.GameListEntry.TABLE_NAME,
//                null, // Column
//                null, // Where clause
//                null, // Arguments
//                null, // Group by
//                null, // having
//                GameListContract.GameListEntry.COLUMN_TIMESTAMP // Sort_order
//        );
//    }
//
//
//    private long addGameResult(String teamA, int teamAScore, String teamB, int teamBScore) {
//        ContentValues cv = new ContentValues();
//        cv.put(GameListContract.GameListEntry.COLUMN_TEAM_A, teamA);
//        cv.put(GameListContract.GameListEntry.COLUMN_TEAM_A_SCORE, teamAScore);
//        cv.put(GameListContract.GameListEntry.COLUMN_TEAM_B, teamB);
//        cv.put(GameListContract.GameListEntry.COLUMN_TEAM_B_SCORE, teamBScore);
//        return mDb.insert(GameListContract.GameListEntry.TABLE_NAME, null, cv);
//    }
//
}
