package com.example.android.courtcounterdatabase;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.android.courtcounterdatabase.data.GameListContract;
import com.example.android.courtcounterdatabase.data.GameListDbHelper;

import static android.os.Build.VERSION_CODES.M;
import static com.example.android.courtcounterdatabase.R.id.finalTeamAName;
import static com.example.android.courtcounterdatabase.R.id.finalTeamAScore;
import static com.example.android.courtcounterdatabase.R.id.finalTeamBName;
import static com.example.android.courtcounterdatabase.R.id.finalTeamBScore;

public class ScoreUpdateActivity extends AppCompatActivity {


    private CustomAdapter mAdapter;
    private SQLiteDatabase mDb;
    private TextView teamAName;
    private TextView teamBName;
    private TextView teamAScore;
    private TextView teamBScore;
    private final static String LOG_TAG = FinalScoreActivity.class.getSimpleName();



    private RecyclerView recyclerView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score_update); // gets the xml layout of activity_main


        Bundle bundle = this.getIntent().getExtras(); // creates a new bundle object to get the intent and extras
        // from the former one
        teamAName = (TextView) findViewById(R.id.teamADisplay);
        teamBName = (TextView) findViewById(R.id.teamBDisplay);

        //String teama = bundle.getString("teamA");
        String teamb = bundle.getString("teamB");


        teamAName.setText(bundle.getString("teamA"));  //same as saving it in a variable before setting it: String teama = bundle.getString("teamA");
        teamBName.setText(teamb);



        recyclerView = (RecyclerView) findViewById(R.id.rec_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        // Create a DB helper (this will create the DB if run for the first time)
        GameListDbHelper dbHelper = new GameListDbHelper(this);

        // Keep a reference to the mDb until paused or killed. Get a writable database
        // because you will be adding restaurant customers
        mDb = dbHelper.getWritableDatabase();

        // Get all guest info from the database and save in a cursor
        Cursor cursor = getAllGames();

        // Create an adapter for that cursor to display the data
        mAdapter = new CustomAdapter(this, cursor);

        // Create an adapter for that cursor to display the data
        //mAdapter = new CustomAdapter(this, cursor);

        recyclerView.setAdapter(mAdapter);





    }
    int scoreTeamA = 0;
    int scoreTeamB = 0;


    public void addThreeForTeamA(View v){
        scoreTeamA+=3;
        displayForTeamA(scoreTeamA);
    }

    public void displayForTeamA(int score){
        teamAScore = (TextView) findViewById(R.id.teamAscore);

        teamAScore.setText(String.valueOf(score));
    }

    public void addTwoForTeamA(View view){
        scoreTeamA+=2;
        displayForTeamA(scoreTeamA);
    }

    public void addOneForTeamA(View v){
        scoreTeamA+=1;
        displayForTeamA(scoreTeamA);
    }


    public void displayForTeamB(int score){
        teamBScore = (TextView) findViewById(R.id.teamBscore);
        teamBScore.setText(String.valueOf(score));

    }

    public void addThreeForTeamB(View f){
        scoreTeamB+=3;
        displayForTeamB(scoreTeamB);
    }

    public void addTwoForTeamB(View view){

        scoreTeamB= scoreTeamB+2;

        displayForTeamB(scoreTeamB);

    }

    public void addOneForTeamB(View view){
        scoreTeamB+=1;
        displayForTeamB(scoreTeamB);
    }

    public void resetScore(View view){
        scoreTeamB = 0;
        scoreTeamA = 0;
        displayForTeamA(scoreTeamA);
        displayForTeamB(scoreTeamB);
    }

//        public void newGame1(View v){
//        teamAName = (TextView) findViewById(R.id.teamADisplay);
//        teamAScore = (TextView) findViewById(R.id.teamAscore);
//        teamBName = (TextView) findViewById(R.id.teamBDisplay);
//        teamBScore = (TextView) findViewById(R.id.teamBscore);
//
//        Intent intent1 = new Intent(ScoreUpdateActivity.this, FinalScoreActivity.class);
//
//        Bundle bundle = new Bundle();
//        bundle.putString("teamAName", String.valueOf(teamAName.getText()));
//        bundle.putString("teamAscore", String.valueOf(teamAScore.getText()));
//        bundle.putString("teamBName", String.valueOf(teamBName.getText()));
//        bundle.putString("teamBscore", String.valueOf(teamBScore.getText()));
//        intent1.putExtras(bundle);
//        startActivity(intent1);
//    }
    @Override

    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu1, menu);
        return true;


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){

        int id1 = item.getItemId();
        if (id1 == R.id.newGame){
            addToDatabase1();

//            teamAName = (TextView) findViewById(R.id.teamADisplay);
//            teamAScore = (TextView) findViewById(R.id.teamAscore);
//            teamBName = (TextView) findViewById(R.id.teamBDisplay);
//            teamBScore = (TextView) findViewById(R.id.teamBscore);

            Intent intent1 = new Intent(ScoreUpdateActivity.this, MainActivity.class);

//            Bundle bundle = new Bundle();
//            bundle.putString("teamAName", String.valueOf(teamAName.getText()));
//            bundle.putString("teamAscore", String.valueOf(teamAScore.getText()));
//            bundle.putString("teamBName", String.valueOf(teamBName.getText()));
//            bundle.putString("teamBscore", String.valueOf(teamBScore.getText()));
//            intent1.putExtras(bundle);
            startActivity(intent1);        }

        else if (id1 == R.id.finalScore){


            addToDatabase1();

//            Intent intent1 = new Intent(ScoreUpdateActivity.this, GameListActivity.class);
//
//            Bundle bundle = new Bundle();
//            bundle.putString("teamAName", String.valueOf(teamAName.getText()));
//            bundle.putString("teamAscore", String.valueOf(teamAScore.getText()));
//            bundle.putString("teamBName", String.valueOf(teamBName.getText()));
//            bundle.putString("teamBscore", String.valueOf(teamBScore.getText()));
//            intent1.putExtras(bundle);
//            startActivity(intent1);
//            finish();
        }
        return super.onOptionsItemSelected(item);

        // return super.onCreate(item);
        //can we have super.anotherMethod
        // same as "return true"
    }


    //DATABASE SEGMENT

    public Cursor getAllGames() {               // TODO try putting this method in the GameListActivity
        return mDb.query(
                GameListContract.GameListEntry.TABLE_NAME,
                null, // Column
                null, // Where clause
                null, // Arguments
                null, // Group by
                null, // having
                GameListContract.GameListEntry.COLUMN_TIMESTAMP // Sort_order
        );
    }


    private long addGameResult(String teamA, int teamAScore, String teamB, int teamBScore) {
        ContentValues cv = new ContentValues();
        cv.put(GameListContract.GameListEntry.COLUMN_TEAM_A, teamA);
        cv.put(GameListContract.GameListEntry.COLUMN_TEAM_A_SCORE, teamAScore);
        cv.put(GameListContract.GameListEntry.COLUMN_TEAM_B, teamB);
        cv.put(GameListContract.GameListEntry.COLUMN_TEAM_B_SCORE, teamBScore);

        return mDb.insert(GameListContract.GameListEntry.TABLE_NAME, null, cv);
    }


    public void addToDatabase1() {
        if (teamAName.getText().length() == 0 ||
                teamBName.getText().length() == 0) {
            return;
        }
        //default party size to 1
        int teamAScore = 0;
        int teamBScore = 0;
        try {
            //mNewPartyCountEditText inputType
            //="number", so this should always work
            teamAScore= Integer.parseInt(this.teamAScore.getText().toString());
            teamBScore= Integer.parseInt(this.teamBScore.getText().toString());

            Log.v(LOG_TAG, "team A Score = "+ teamAScore);
            Log.v(LOG_TAG, "team B Score = "+ teamBScore);

        } catch (NumberFormatException ex) {
            Log.e(LOG_TAG, "Score must be integer: " + ex.getMessage());
        }

        // Add guest info to mDb
        addGameResult(this.teamAName.getText().toString(), teamAScore, teamBName.getText().toString(), teamBScore);

        // Update the cursor in the adapter to trigger UI to display the new list
        mAdapter.swapCursor(getAllGames());
    }

}
