package com.example.android.courtcounterdatabase.data;

import android.provider.BaseColumns;

/**
 * Created by ETORO on 10/05/2017.
 */

public class GameListContract {

    public static final class GameListEntry implements BaseColumns {
        public static final String TABLE_NAME = "courtcounterdatabase";
        public static final String COLUMN_TEAM_A = "teama";
        public static final String COLUMN_TEAM_B = "teamb";
        public static final String COLUMN_TEAM_A_SCORE = "teamaScore";
        public static final String COLUMN_TEAM_B_SCORE = "teambScore";
        public static final String COLUMN_TIMESTAMP = "timestamp";
    }

}
